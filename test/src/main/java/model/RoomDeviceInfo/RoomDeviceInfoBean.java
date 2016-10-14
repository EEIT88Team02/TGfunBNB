package model.RoomDeviceInfo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Session;

import model.RoomInfo.RoomInfoBean;
import model.DeviceInfo.DeviceInfoBean;
import model.misc.HibernateUtil;

@Entity
@Table(	name = "ROOMDEVICEINFO" ,
		uniqueConstraints = { @UniqueConstraint(columnNames = { "ROOMCODE", "DEVICEID" }) })
public class RoomDeviceInfoBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomDeviceinfoID;
	private int roomCode;
	private int deviceID;

	@OneToMany(mappedBy = "roomDeviceInfos")
	private Set<RoomInfoBean> roomInfos;

	public Set<RoomInfoBean> getRoomInfos() {
		return roomInfos;
	}

	public void setRoomInfos(Set<RoomInfoBean> roomInfos) {
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

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			/* 查詢單筆ok */
			// RoomDeviceInfoBean select=(RoomDeviceInfoBean)session.get(RoomDeviceInfoBean.class, 1);
			// System.out.println(select);

			// RoomDeviceInfoBean select=(RoomDeviceInfoBean)session.get(RoomDeviceInfoBean.class, 1);
			// System.out.println(select.getRoomInfos());

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

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();

		}

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

}
