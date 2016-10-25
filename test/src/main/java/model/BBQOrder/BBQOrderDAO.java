package model.BBQOrder;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component(value="bbqOrderDAO")
public class BBQOrderDAO implements BBQOrderInterface {

	
	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
			
//			BBQOrderDAO dao = new BBQOrderDAO(session);
//			List<BBQOrderBean> result=dao.selectByOrderID(1,true);
//			for(BBQOrderBean bean:result){
//					System.out.println(bean);
//			}				
			
//			BBQOrderDAO dao=new BBQOrderDAO(session);
//			BBQOrderBean bbqOrderBean=new BBQOrderBean();
//			bbqOrderBean.setOrderID(1);  
//			bbqOrderBean.setBbqID(3);     
//			bbqOrderBean.setBbqDate(new Date());
//			bbqOrderBean.setHaveDelete(false);
//			dao.insert(bbqOrderBean);
			
//			BBQOrderDAO dao=new BBQOrderDAO(session);
//			BBQOrderBean bbqOrderBean=new BBQOrderBean();
//			bbqOrderBean.setBbqOrderID(1);
//			bbqOrderBean.setOrderID(1);
//			bbqOrderBean.setBbqID(1);
//			bbqOrderBean.setBbqDate(new Date());
//			bbqOrderBean.setHaveDelete(true);
//			dao.update(bbqOrderBean);

//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		BBQOrderDAO dao = (BBQOrderDAO) context.getBean("bbqOrderDAO");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			List<BBQOrderBean> result=dao.selectByOrderID(1,false);
			for(BBQOrderBean bean:result){
					System.out.println(bean);
			}	

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
		
	}
	
	private SessionFactory sessionFactory = null;

	public BBQOrderDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BBQOrderBean> selectByOrderID(int orderID,boolean haveDelete) {
		String select_by_orderID = "FROM BBQOrderBean WHERE orderID =:orderID AND haveDelete=:haveDelete";
		List<BBQOrderBean> result =this.getSession()
									.createQuery(select_by_orderID)
									.setInteger("orderID",orderID)
									.setBoolean("haveDelete",haveDelete)
									.list();
		return result;
	}

	@Override
	public boolean insert(BBQOrderBean bbqOrderBean) {

		try {
			this.getSession().save(bbqOrderBean);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public BBQOrderBean updateHaveDelete(BBQOrderBean bbqOrderBean) {
		this.getSession().saveOrUpdate(bbqOrderBean);
		return bbqOrderBean;
	}

}
