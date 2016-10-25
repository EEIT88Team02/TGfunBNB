package model.DeviceInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class DeviceInfoDAO implements DeviceInfoInterface {

	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
//			
//			DeviceInfoDAO dao = new DeviceInfoDAO(session);
//			DeviceInfoBean result=dao.selectByDeviceID(1);
//			System.out.println(result);
//
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		DeviceInfoDAO dao = (DeviceInfoDAO) context.getBean("deviceInfoDAO");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			DeviceInfoBean result=dao.selectByDeviceID(1);
			System.out.println(result);
			 	 
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	private SessionFactory sessionFactory = null;

	public DeviceInfoDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public DeviceInfoBean selectByDeviceID(int deviceID) {
		String select_by_deviceID = "FROM DeviceInfoBean WHERE deviceID =:deviceID";
		DeviceInfoBean result = (DeviceInfoBean) this.getSession()
										.createQuery(select_by_deviceID)
										.setInteger("deviceID",deviceID)
										.uniqueResult();
		return result;
	}

}
