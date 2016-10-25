package model.OrderRoomInfo;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OrderRoomInfoService {
	@Autowired
	private OrderRoomInfoDAO orderRoomInfoDAO;

	public OrderRoomInfoService(OrderRoomInfoDAO orderRoomInfoDAO) {	
		this.orderRoomInfoDAO = orderRoomInfoDAO;
	}
	
	public List<OrderRoomInfoBean> selectByOrderID(int orderID) {
		return orderRoomInfoDAO.selectByOrderID(orderID);	
	}
	
	public List<OrderRoomInfoBean> selectByIn_OutDate(String inDate, String outDate, int roomCode) {
		return orderRoomInfoDAO.selectByIn_OutDate(inDate,outDate,roomCode);
	}
	
	public boolean insert(OrderRoomInfoBean orderRoomInfoBean) {
		boolean result=orderRoomInfoDAO.insert(orderRoomInfoBean);
		if(result==true)
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) throws ParseException {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//			OrderRoomInfoService service= new OrderRoomInfoService();
//			List<OrderRoomInfoBean> result=service.selectByOrderID(1);
//			for(OrderRoomInfoBean bean:result){
//					System.out.println(bean);
//			}
			
//			OrderRoomInfoService service = new OrderRoomInfoService();
//			OrderRoomInfoBean orderRoomInfoBean=new OrderRoomInfoBean();
//			orderRoomInfoBean.setOrderID(2);
//			orderRoomInfoBean.setRoomCode(102);
//			orderRoomInfoBean.setRoomSum(25000);
//			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");			 
//			orderRoomInfoBean.setInDate(dateFormat.parse("2016-10-14"));
//			orderRoomInfoBean.setOutDate(dateFormat.parse("2016-11-15"));
//			service.insert(orderRoomInfoBean);

//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		OrderRoomInfoService service=(OrderRoomInfoService)context.getBean("orderRoomInfoService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			List<OrderRoomInfoBean> result=service.selectByOrderID(1);
			for(OrderRoomInfoBean bean:result){
					System.out.println(bean);
			}

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

}
