package model.MemberOrder;

import java.util.Date;
import java.util.Iterator;
/*待測試*/
import java.util.List;
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

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import model.BBQOrder.BBQOrderBean;
import model.Member.MemberBean;
import model.OrderRoomInfo.OrderRoomInfoBean;
import model.misc.HibernateUtil;

@Entity
@Table(name = "MEMBERORDER")
public class MemberOrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderID;  // 訂單編號
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

	@OneToMany(mappedBy = "memberOrders")
	private Set<OrderRoomInfoBean> OrderRoomInfos;

	public Set<OrderRoomInfoBean> getOrderRoomInfos() {
		return OrderRoomInfos;
	}

	public void setOrderRoomInfos(Set<OrderRoomInfoBean> orderRoomInfos) {
		OrderRoomInfos = orderRoomInfos;
	}

	@OneToMany(mappedBy = "memberOrders")
	private Set<BBQOrderBean> bbqOreders;

	public Set<BBQOrderBean> getBbqOreders() {
		return bbqOreders;
	}

	public void setBbqOreders(Set<BBQOrderBean> bbqOreders) {
		this.bbqOreders = bbqOreders;
	}

	public static void main(String[] args) {

		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			// MemberOrderBean select = (MemberOrderBean) session.get(MemberOrderBean.class,1);
			// System.out.println(select);

			// MemberOrderBean select = (MemberOrderBean) session.get(MemberOrderBean.class,1);
			// System.out.println(select.getMembers());

//			 MemberOrderBean select = (MemberOrderBean) session.get(MemberOrderBean.class,1);
//			 System.out.println(select.getOrderRoomInfos());
			
//			 MemberOrderBean select = (MemberOrderBean) session.get(MemberOrderBean.class,1);
//			 System.out.println(select.getBbqOreders());

			// List result = session.createSQLQuery("select memberID,MemberDate from MemberOrder")
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

			 MemberOrderBean insert = new MemberOrderBean();
			 insert.setMemberID(1);
			 insert.setMemberDate(new Date());
			 insert.setMemberSum(150000);
			 session.save(insert);

			/* 修改 */
			// MemberOrderBean bean = (MemberOrderBean) session.get(MemberOrderBean.class,1);
			// bean.setMemberID(2);
			// bean.setMemberDate(new java.util.Date());
			// bean.setMemberSum(20000);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	@Override
	public String toString() {
		return "MemberOrderBean [orderID=" + orderID + ", memberID=" + memberID + ", memberDate=" + memberDate + ", memberSum=" + memberSum + ", roomTotalSum=" + roomTotalSum + ", haveDelete="
				+ haveDelete + "]";
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
