package model.RoomDeviceInfo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.DeviceInfo.DeviceInfoBean;
import model.RoomInfo.RoomInfoBean;

@Entity
@Table(	name = "ROOMDEVICEINFO" ,
		uniqueConstraints = { @UniqueConstraint(columnNames = { "ROOMCODE", "DEVICEID" }) })
@Component
public class RoomDeviceInfoBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomDeviceinfoID;
	private int roomCode;
	private int deviceID;

	@ManyToOne
	@JoinColumn(name = "ROOMCODE" ,
				referencedColumnName = "ROOMCODE" ,
				insertable = false ,
				updatable = false)
	private RoomInfoBean roomInfos;

	public RoomInfoBean getRoomInfos() {
		return roomInfos;
	}

	public void setRoomInfos(RoomInfoBean roomInfos) {
		this.roomInfos = roomInfos;
	}

	@ManyToOne
	@JoinColumn(name = "DEVICEID" ,
				referencedColumnName = "DEVICEID" ,
				insertable = false ,
				updatable = false)
	private DeviceInfoBean DeviceInfos;

	public DeviceInfoBean getDeviceInfos() {
		return DeviceInfos;
	}

	public void setDeviceInfos(DeviceInfoBean deviceInfos) {
		DeviceInfos = deviceInfos;
	}


	@Override
	public String toString() {
		return "RoomDeviceInfoBean [roomDeviceinfoID=" + roomDeviceinfoID + ", roomCode=" + roomCode + ", deviceID=" + deviceID + "]";
	}

	public int getRoomDeviceinfoID() {
		return roomDeviceinfoID;
	}

	public void setRoomDeviceinfoID(int roomDeviceinfoID) {
		this.roomDeviceinfoID = roomDeviceinfoID;
	}

	public int getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}
	
	

	public static void main(String[] args) {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			/* 查詢單筆ok */
			// RoomDeviceInfoBean select=(RoomDeviceInfoBean)session.get(RoomDeviceInfoBean.class, 1);
			// System.out.println(select);

//			RoomDeviceInfoBean select = (RoomDeviceInfoBean) session.get(RoomDeviceInfoBean.class,1);
//			System.out.println(select.getRoomInfos());

//			 RoomDeviceInfoBean select=(RoomDeviceInfoBean)session.get(RoomDeviceInfoBean.class, 1);
//			 System.out.println(select.getDeviceInfos());

			/* 新增資料ok */
			// RoomDeviceInfoBean insert = new RoomDeviceInfoBean();
			// insert.setDeviceID(1);
			// insert.setRoomCode(102);
			// session.save(insert);

			/* 刪除ok */
			// RoomDeviceInfoBean bean=(RoomDeviceInfoBean)session.get(RoomDeviceInfoBean.class, 2);
			// session.delete(bean);

			/* 修改ok */
			// RoomDeviceInfoBean bean=(RoomDeviceInfoBean)session.get(RoomDeviceInfoBean.class, 2);
			// bean.setDeviceID(2);

//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			 RoomDeviceInfoBean select=(RoomDeviceInfoBean)session.get(RoomDeviceInfoBean.class, 1);
			 System.out.println(select);


			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

}
