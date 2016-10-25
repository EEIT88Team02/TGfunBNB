package model.BBQOrder;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component(value="bbqOrderService")
public class BBQOrderService {
	
	@Autowired
	private BBQOrderDAO bbqOrderDAO;

	public BBQOrderService(BBQOrderDAO bbqOrderDAO) {
		this.bbqOrderDAO =bbqOrderDAO;
	}
	
	public List<BBQOrderBean> selectByOrderID(int orderID,boolean haveDelete) {
		return bbqOrderDAO.selectByOrderID(orderID,haveDelete);
	}
	
	public boolean insert(BBQOrderBean bbqOrderBean) {
		boolean result=bbqOrderDAO.insert(bbqOrderBean);
		if(result==true)
			return true;
		else
			return false;		
	}
	
	public BBQOrderBean updateHaveDelete(BBQOrderBean bbqOrderBean) {
		return bbqOrderDAO.updateHaveDelete(bbqOrderBean);
	}
	
	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
//			
//			BBQOrderService service = new BBQOrderService();
//			List<BBQOrderBean> result=service.selectByOrderID(1,false);
//			for(BBQOrderBean bean:result){
//					System.out.println(bean);
//			}
			
//			BBQOrderService service=new BBQOrderService();
//			BBQOrderBean bbqOrderBean=new BBQOrderBean();
//			bbqOrderBean.setOrderID(1);  
//			bbqOrderBean.setBbqID(3);     
//			bbqOrderBean.setBbqDate(new Date());
//			bbqOrderBean.setHaveDelete(false);
//			service.insert(bbqOrderBean);
			
//			BBQOrderService service=new BBQOrderService();
//			BBQOrderBean bbqOrderBean=new BBQOrderBean();
//			bbqOrderBean.setBbqOrderID(1);
//			bbqOrderBean.setOrderID(1);
//			bbqOrderBean.setBbqID(1);
//			bbqOrderBean.setBbqDate(new Date());
//			bbqOrderBean.setHaveDelete(true);
//			service.updateHaveDelete(bbqOrderBean);

//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		BBQOrderService service = (BBQOrderService) context.getBean("bbqOrderService");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			List<BBQOrderBean> result=service.selectByOrderID(1,false);
			for(BBQOrderBean bean:result){
					System.out.println(bean);
			}

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

}
