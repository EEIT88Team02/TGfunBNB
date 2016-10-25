package model.OrderRoomInfo;

import java.text.ParseException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class OrderRoomInfoDAO implements OrderRoomInfoInterface{

	
	private SessionFactory sessionFactory = null;

	public OrderRoomInfoDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderRoomInfoBean> selectByOrderID(int orderID) {
		String select_by_orderID = 
				"FROM OrderRoomInfoBean WHERE orderID =:orderID ORDER BY inDate DESC, outDate";
		List<OrderRoomInfoBean> result =this.getSession()
							.createQuery(select_by_orderID)
							.setInteger("orderID",orderID)
							.list();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderRoomInfoBean> selectByIn_OutDate(String inDate, String outDate, int roomCode) {
		String select_by_in_outDate = 
				"from OrderRoomInfoBean where inDate between :inDate and :outDate or outDate between :inDate and :outDate and roomCode = :roomCode";
			List<OrderRoomInfoBean> result =this.getSession()
										.createQuery(select_by_in_outDate)
										.setString("inDate",inDate)
										.setString("outDate",outDate)
										.setInteger("roomCode",roomCode)
										.list();	
			return result;
	}

	@Override
	public boolean insert(OrderRoomInfoBean orderRoomInfoBean) {

		try {
			this.getSession().save(orderRoomInfoBean);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static void main(String[] args) throws ParseException {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
			
//				OrderRoomInfoDAO dao = new OrderRoomInfoDAO(session);
//				List<OrderRoomInfoBean> result=dao.selectByOrderID(2);
//				for(OrderRoomInfoBean bean:result){
//					System.out.println(bean);
//				}
			
			
//				OrderRoomInfoDAO dao = new OrderRoomInfoDAO(session);
//				List<OrderRoomInfoBean> result=dao.selecTByIn_OutDate("2016-01-01","2016-01-05",102);			
//				for(OrderRoomInfoBean bean:result){
//					System.out.println(bean);
//				}
			
//				OrderRoomInfoDAO dao = new OrderRoomInfoDAO(session);
//				OrderRoomInfoBean orderRoomInfoBean=new OrderRoomInfoBean();
//				orderRoomInfoBean.setOrderID(2);
//				orderRoomInfoBean.setRoomCode(101);
//				orderRoomInfoBean.setRoomSum(25000);
//				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");			 
//				orderRoomInfoBean.setInDate(dateFormat.parse("2016-10-14"));
//				orderRoomInfoBean.setOutDate(dateFormat.parse("2016-11-15"));
//				dao.insert(orderRoomInfoBean);
			
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		OrderRoomInfoDAO dao=(OrderRoomInfoDAO)context.getBean("orderRoomInfoDAO");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			List<OrderRoomInfoBean> result=dao.selectByOrderID(1);
			for(OrderRoomInfoBean bean:result){
				System.out.println(bean);
			}


			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
		
	}

}

