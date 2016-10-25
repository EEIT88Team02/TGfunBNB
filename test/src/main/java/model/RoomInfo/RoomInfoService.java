package model.RoomInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class RoomInfoService {
	
	@Autowired
	private RoomInfoDAO roomInfoDAO;

	public RoomInfoService(RoomInfoDAO roomInfoDAO) {
		this.roomInfoDAO =roomInfoDAO;
	}
	
	public RoomInfoBean selectByRoomCode(int roomCode) {
		return roomInfoDAO.selectByRoomCode(roomCode);
	}

	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//			RoomInfoService service = new RoomInfoService();
//			RoomInfoBean result = service.selectByRoomCode(101);
//			System.out.println(result);
//
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		RoomInfoService service = (RoomInfoService) context.getBean("roomInfoService");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
				
			RoomInfoBean result = service.selectByRoomCode(101);
			System.out.println(result);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

}
