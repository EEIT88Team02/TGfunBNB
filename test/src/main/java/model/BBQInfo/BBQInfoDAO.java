package model.BBQInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component(value="bbqInfoDAO")
public class BBQInfoDAO implements BBQInfoInterface {

	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
//			
//			BBQInfoDAO dao = new BBQInfoDAO(session);
//			BBQInfoBean result=dao.selectByBBQID(1);
//			
//			System.out.println(result);
//
//
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		BBQInfoDAO dao = (BBQInfoDAO) context.getBean("bbqInfoDAO");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			BBQInfoBean result=dao.selectByBBQID(1);
			System.out.println(result);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
		
	}
	
	private SessionFactory sessionFactory = null;

	public BBQInfoDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BBQInfoBean selectByBBQID(int bbqID) {
		String select_by_bbqID = 
				"FROM BBQInfoBean WHERE bbqID =:bbqID";
		BBQInfoBean result =(BBQInfoBean) this.getSession()
							.createQuery(select_by_bbqID)
							.setInteger("bbqID",bbqID)
							.uniqueResult();
		return result;
	}


}
