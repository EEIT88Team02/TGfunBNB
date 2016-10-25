package model.OrderRoomInfo;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/*待測*/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.MemberOrder.MemberOrderBean;
import model.RoomInfo.RoomInfoBean;

@Entity
@Table(	name = "ORDERROOMINFO" ,
		uniqueConstraints = { @UniqueConstraint(columnNames = { "ORDERID", "ROOMCODE" }) })
@Component
public class OrderRoomInfoBean  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderRoomInfoID;
	private int orderID;
	private int roomCode;// 房間代號
	private Date inDate;    // 入住日期
	private Date outDate;   // 退房日期
	private double roomSum;// 房間金額

	@OneToOne
	@JoinColumn(name = "orderID" ,
	referencedColumnName = "orderID" ,
	insertable = false ,
	updatable = false)
	private MemberOrderBean memberOrders;

	public MemberOrderBean getMemberOrders() {
		return memberOrders;
	}

	public void setMemberOrders(MemberOrderBean memberOrders) {
		this.memberOrders = memberOrders;
	}

	@OneToOne
	@JoinColumn(name = "roomCode" ,
	referencedColumnName = "roomCode" ,
	insertable = false ,
	updatable = false)
	private RoomInfoBean roomInfos;

	public RoomInfoBean getRoomInfos() {
		return roomInfos;
	}

	public void setRoomInfos(RoomInfoBean roomInfos) {
		this.roomInfos = roomInfos;
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
	
	

	public static void main(String[] args) throws ParseException {

//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//			 OrderRoomInfoBean select = (OrderRoomInfoBean) session.get(OrderRoomInfoBean.class,1);
//			 System.out.println(select);
			
//			 OrderRoomInfoBean select = (OrderRoomInfoBean) session.get(OrderRoomInfoBean.class,1);
//			 System.out.println(select.getRoomInfos());

			/* 查詢全部 */
//			 Query query = session.createQuery("from OrderRoomInfoBean");
//			 List<OrderRoomInfoBean> beans = query.list();
//			 System.out.println(beans);

			/* 新增 */
//			 OrderRoomInfoBean insert=new OrderRoomInfoBean();
//			 insert.setOrderID(2);
//			 insert.setRoomCode(102);
//			 insert.setRoomSum(25000);
//			 SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");			 
//			 insert.setInDate(dateFormat.parse("2016-10-14"));
//			 insert.setOutDate(dateFormat.parse("2016-11-15"));
//			 session.save(insert);

//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			 OrderRoomInfoBean select = (OrderRoomInfoBean) session.get(OrderRoomInfoBean.class,1);
			 System.out.println(select);


			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

}
