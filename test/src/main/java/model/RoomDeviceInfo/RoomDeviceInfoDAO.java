package model.RoomDeviceInfo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RoomDeviceInfoDAO implements RoomDeviceInfoInterface {

	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
//			
//			RoomDeviceInfoDAO dao = new RoomDeviceInfoDAO(session);
//			List<RoomDeviceInfoBean> result=dao.selectByRoomCode(101);
//			System.out.println(result);
//
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");		
		RoomDeviceInfoDAO dao = (RoomDeviceInfoDAO) context.getBean("roomDeviceInfoDAO");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			List<RoomDeviceInfoBean> result=dao.selectByRoomCode(101);
			System.out.println(result);


			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

	private SessionFactory sessionFactory = null;

	public RoomDeviceInfoDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoomDeviceInfoBean> selectByRoomCode(int roomCode) {
		String select_by_roomCode = "FROM RoomDeviceInfoBean WHERE roomCode =:roomCode";
		List<RoomDeviceInfoBean> result = this.getSession()
										.createQuery(select_by_roomCode)
										.setInteger("roomCode",roomCode)
										.list();
		return result;
	}
}
