package model.OrderRoomInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
/*待測*/
import org.hibernate.Session;

import model.MemberOrder.MemberOrderBean;
import model.RoomInfo.RoomInfoBean;
import model.misc.HibernateUtil;

@Entity
@Table(	name = "ORDERROOMINFO" ,
		uniqueConstraints = { @UniqueConstraint(columnNames = { "ORDERID", "ROOMCODE" }) })
public class OrderRoomInfoBean  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderRoomInfoID;
	private int orderID;
	private int roomCode;// 房間代號
	private Date inDate;    // 入住日期
	private Date outDate;   // 退房日期
	private double roomSum;// 房間金額

	@ManyToOne
	@JoinColumn(name = "ORDERID" ,
				referencedColumnName = "ORDERID" ,
				insertable = false ,
				updatable = false)
	private MemberOrderBean memberOrders;

	public MemberOrderBean getMemberOrders() {
		return memberOrders;
	}

	public void setMemberOrders(MemberOrderBean memberOrders) {
		this.memberOrders = memberOrders;
	}

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

	public static void main(String[] args) {

		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//			 OrderRoomInfoBean select = (OrderRoomInfoBean) session.get(OrderRoomInfoBean.class,1);
//			 System.out.println(select);
			
//			 OrderRoomInfoBean select = (OrderRoomInfoBean) session.get(OrderRoomInfoBean.class,1);
//			 System.out.println(select.getRoomInfos());

			/* 查詢全部 */
			// Query query = session.createQuery("from RoomOrderInfoBean");
			// List<RoomOrderInfoBean> beans = query.list();
			// System.out.println(beans);

			/* 新增 */
			// RoomOrderInfoBean insert=new RoomOrderInfoBean();
			// insert.setRoomID(1);
			// insert.setRoomCode(101);
			// insert.setRoomSum(25000);
			// session.save(insert);

			/* 修改 */
			// RoomOrderInfoBean bean=(RoomOrderInfoBean)session.get(RoomOrderInfoBean.class,1);
			// bean.setRoomCode(102);
			// bean.setRoomSum(22000);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {

			HibernateUtil.closeSessionFactory();
		}

	}

	@Override
	public String toString() {
		return "OrderRoomInfoBean [OrderRoomInfoID=" + OrderRoomInfoID + ", orderID=" + orderID + ", roomCode=" + roomCode + ", inDate=" + inDate + ", outDate=" + outDate + ", roomSum=" + roomSum
				+ "]";
	}

	public int getOrderRoomInfoID() {
		return OrderRoomInfoID;
	}

	public void setOrderRoomInfoID(int orderRoomInfoID) {
		OrderRoomInfoID = orderRoomInfoID;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(int roomCode) {
		this.roomCode = roomCode;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public double getRoomSum() {
		return roomSum;
	}

	public void setRoomSum(double roomSum) {
		this.roomSum = roomSum;
	}

}
