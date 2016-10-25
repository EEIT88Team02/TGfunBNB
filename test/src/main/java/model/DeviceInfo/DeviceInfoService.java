package model.DeviceInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DeviceInfoService {
	@Autowired
	private DeviceInfoDAO deviceInfoDAO;

	public DeviceInfoService(DeviceInfoDAO deviceInfoDAO) {
		this.deviceInfoDAO =deviceInfoDAO;
	}
	
	public DeviceInfoBean selectByDeviceID(int deviceID) {
		return deviceInfoDAO.selectByDeviceID(deviceID);
	}
	
	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
//			
//			DeviceInfoService service = new DeviceInfoService();
//			DeviceInfoBean result=service.selectByDeviceID(1);
//			System.out.println(result);
//
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
	
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		DeviceInfoService service = (DeviceInfoService) context.getBean("deviceInfoService");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			DeviceInfoBean result=service.selectByDeviceID(1);
			System.out.println(result);
			 	 
			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

}
