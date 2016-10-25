package model.RoomDeviceInfo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RoomDeviceInfoService {

	private RoomDeviceInfoDAO roomDeviceInfoDAO;

	public RoomDeviceInfoService(RoomDeviceInfoDAO roomDeviceInfoDAO) {
		this.roomDeviceInfoDAO =roomDeviceInfoDAO;
	}
	
	public List<RoomDeviceInfoBean> selectByRoomCode(int roomCode) {
		return roomDeviceInfoDAO.selectByRoomCode(roomCode);
	}

	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//			RoomDeviceInfoService service= new RoomDeviceInfoService();
//			List<RoomDeviceInfoBean> result = service.selectByRoomCode(101);
//			System.out.println(result);

//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");		
		RoomDeviceInfoService service = (RoomDeviceInfoService) context.getBean("roomDeviceInfoService");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			List<RoomDeviceInfoBean> result = service.selectByRoomCode(101);
			System.out.println(result);

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

}
