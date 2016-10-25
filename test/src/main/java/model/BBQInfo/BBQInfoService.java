package model.BBQInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component(value="bbqInfoService")
public class BBQInfoService {
	@Autowired
	private BBQInfoDAO bbqInfoDAO;

	public BBQInfoService(BBQInfoDAO bbqInfoDAO) {
		this.bbqInfoDAO =bbqInfoDAO;
	}
	
	public BBQInfoBean selectByBBQID(int bbqID) {
		return bbqInfoDAO.selectByBBQID(bbqID);
		
	}
	
	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
//			
//			BBQInfoService service = new BBQInfoService();
//			BBQInfoBean result=service.selectByBBQID(1);
//			
//			System.out.println(result);
//
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		BBQInfoService service = (BBQInfoService) context.getBean("bbqInfoService");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			BBQInfoBean result=service.selectByBBQID(1);		
			System.out.println(result);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}


	}

}
