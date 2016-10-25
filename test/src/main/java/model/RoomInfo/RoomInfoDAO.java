package model.RoomInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RoomInfoDAO implements RoomInfoInterface {

	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
//			
//			RoomInfoDAO dao = new RoomInfoDAO(session);
//			RoomInfoBean result=dao.selectByRoomCode(101);
//			System.out.println(result);
//
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		RoomInfoDAO dao = (RoomInfoDAO) context.getBean("roomInfoDAO");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			RoomInfoBean result=dao.selectByRoomCode(101);
			System.out.println(result);


			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
		
	}

	private SessionFactory sessionFactory = null;

	public RoomInfoDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public RoomInfoBean selectByRoomCode(int roomCode) {
		String select_by_roomCode = 
				"FROM RoomInfoBean WHERE roomCode =:roomCode";
		RoomInfoBean result =(RoomInfoBean) this.getSession()
											.createQuery(select_by_roomCode)
											.setInteger("roomCode",roomCode)
											.uniqueResult();
		return result;
	}

}
