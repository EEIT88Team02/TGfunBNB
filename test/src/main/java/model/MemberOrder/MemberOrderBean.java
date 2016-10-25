package model.MemberOrder;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.BBQOrder.BBQOrderBean;
import model.Member.MemberBean;
import model.OrderRoomInfo.OrderRoomInfoBean;

@Entity
@Table(name = "MEMBERORDER")
@Component
public class MemberOrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderID; // 訂單編號
	private int memberID; // 會員編號
	private java.util.Date memberDate;// 會員訂單日期
	private double roomTotalSum;
	private double memberSum;// 房間金額
	private boolean haveDelete;

	@ManyToOne
	@JoinColumn(name = "MEMBERID" ,
				referencedColumnName = "MEMBERID" ,
				insertable = false ,
				updatable = false)
	private MemberBean members;

	public MemberBean getMembers() {
		return members;
	}

	public void setMembers(MemberBean members) {
		this.members = members;
	}

	@OneToOne(mappedBy = "memberOrders")

	private OrderRoomInfoBean OrderRoomInfos;
	
	public OrderRoomInfoBean getOrderRoomInfos() {
		return OrderRoomInfos;
	}
	
	public void setOrderRoomInfos(OrderRoomInfoBean orderRoomInfos) {
		OrderRoomInfos = orderRoomInfos;
	}

	@OneToOne(mappedBy = "memberOrders")
	private BBQOrderBean bbqOrders;
	
	public BBQOrderBean getBbqOrders() {
		return bbqOrders;
	}
	
	public void setBbqOrders(BBQOrderBean bbqOrders) {
		this.bbqOrders = bbqOrders;
	}


	public static void main(String[] args) {

		// try {
		// HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		// Session session =
		// HibernateUtil.getSessionFactory().getCurrentSession();

		// MemberOrderBean select = (MemberOrderBean)
		// session.get(MemberOrderBean.class,1);
		// System.out.println(select);

		// MemberOrderBean select = (MemberOrderBean)
		// session.get(MemberOrderBean.class,1);
		// System.out.println(select.getMembers());

		// MemberOrderBean select = (MemberOrderBean)
		// session.get(MemberOrderBean.class,2);
		// System.out.println(select.getOrderRoomInfos());

		// MemberOrderBean select = (MemberOrderBean)
		// session.get(MemberOrderBean.class,1);
		// System.out.println(select.getBbqOreders());

		// List result = session.createSQLQuery("select memberID,MemberDate from
		// MemberOrder")
		// .addScalar("MemberID",StandardBasicTypes.INTEGER)
		// .addScalar("MemberDate",StandardBasicTypes.DATE)
		// // .addEntity("MemberOrder",MemberOrderBean.class)
		// .list();
		//
		// Iterator<Object[]> iterator = result.iterator();
		// while (iterator.hasNext()) {
		// Object[] obj = iterator.next();
		//
		// for (int i=0; i < obj.length; i++) {
		// System.out.println(obj[i]);
		// }
		// }

		// MemberOrderBean insert = new MemberOrderBean();
		// insert.setMemberID(1);
		// insert.setMemberDate(new Date());
		// insert.setMemberSum(150000);
		// session.save(insert);

		/* 修改 */
		// MemberOrderBean bean = (MemberOrderBean)
		// session.get(MemberOrderBean.class,1);
		// bean.setMemberID(2);
		// bean.setMemberDate(new java.util.Date());
		// bean.setMemberSum(20000);

		// HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		// }
		// finally {
		// HibernateUtil.closeSessionFactory();
		// }
		
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			 MemberOrderBean select = (MemberOrderBean)
			 session.get(MemberOrderBean.class,1);
			 System.out.println(select);


			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
		
	}

	@Override
	public String toString() {
		return "MemberOrderBean [orderID=" + orderID + ", memberID=" + memberID + ", memberDate=" + memberDate
				+ ", memberSum=" + memberSum + ", roomTotalSum=" + roomTotalSum + ", haveDelete=" + haveDelete + "]";
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public java.util.Date getMemberDate() {
		return memberDate;
	}

	public void setMemberDate(java.util.Date memberDate) {
		this.memberDate = memberDate;
	}

	public double getRoomTotalSum() {
		return roomTotalSum;
	}

	public void setRoomTotalSum(double roomTotalSum) {
		this.roomTotalSum = roomTotalSum;
	}

	public double getMemberSum() {
		return memberSum;
	}

	public void setMemberSum(double memberSum) {
		this.memberSum = memberSum;
	}

	public boolean isHaveDelete() {
		return haveDelete;
	}

	public void setHaveDelete(boolean haveDelete) {
		this.haveDelete = haveDelete;
	}

}
