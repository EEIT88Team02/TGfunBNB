package model.FoodOrder;

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

import org.hibernate.Session;

import model.BBQOrder.BBQOrderBean;
import model.FoodOrderInfo.FoodOrderInfoBean;
import model.MemberOrder.MemberOrderBean;
import model.misc.HibernateUtil;

@Entity
@Table(name = "FoodOrder")
public class FoodOrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int FoodOrderID;// 食材訂單編號
	private int OrderID;// 訂單編號
	private int bbqOrderID;// 烤肉區訂單編號
	private java.util.Date foodDate;// 食材訂單日期
	private float totalSum;// 金額
	private boolean haveDelete;// 是否刪除
	// -------------FoodOrderInfoBean--------------------------------------
	@OneToMany(

				mappedBy = "FoodOrder" ,
				cascade = { CascadeType.REMOVE }

	)
	private Set<FoodOrderInfoBean> FoodOrderInfo;

	public Set<FoodOrderInfoBean> getFoodOrderInfo() {
		return FoodOrderInfo;
	}

	public void setFoodOrderInfo(Set<FoodOrderInfoBean> foodOrderInfo) {
		FoodOrderInfo = foodOrderInfo;
	}

	// -------------------MemberOrderBean---------------------------------------

	@ManyToOne
	@JoinColumn(name = "OrderID" ,
				referencedColumnName = "OrderID" ,
				insertable = false ,
				updatable = false

	)
	private MemberOrderBean MemberOrderBean;

	public MemberOrderBean getMemberOrderBean() {
		return MemberOrderBean;
	}

	public void setMemberOrderBean(MemberOrderBean memberOrderBean) {
		MemberOrderBean = memberOrderBean;
	}

	// ------------------BBQOrderBean-------------------------------------
	@ManyToOne
	@JoinColumn(name = "bbqOrderID" ,
				referencedColumnName = "bbqOrderID" ,
				insertable = false ,
				updatable = false)

	private BBQOrderBean BBQOrderBean;

	public BBQOrderBean getBBQOrderBean() {
		return BBQOrderBean;
	}

	public void setBBQOrderBean(BBQOrderBean bBQOrderBean) {
		BBQOrderBean = bBQOrderBean;
	}

	// ---------------------------------------------------------------------
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			// 新增
//			 FoodOrderBean insert = new FoodOrderBean();
//			 insert.setOrderID(1);
//			 insert.setBbqOrderID(1);
//			 insert.setFoodDate(Timestamp.valueOf("2012-12-12 12:12:12"));
//			 insert.setTotalSum(100);
//			 session.save(insert);
//			 System.out.println("insert=" + insert);
			// 查詢
//			FoodOrderBean select = (FoodOrderBean) session.get(FoodOrderBean.class,1);
//			System.out.println("select=" + select);
			// 修改
			// FoodOrderBean update =(FoodOrderBean) session.get(FoodOrderBean.class,1);
			// update.setFoodCount(55);
			// update.setTotalSum(1000000);
			// session.update(update);
			// System.out.println("update="+update);
			//// 刪除
			// FoodOrderBean select = (FoodOrderBean) session.get(FoodOrderBean.class,9);
			// session.delete(select);
			FoodOrderBean select = (FoodOrderBean) session.get(FoodOrderBean.class,1);
			 System.out.println(select.getBBQOrderBean());

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}

	}

	
	@Override
	public String toString() {
		return "FoodOrderBean [FoodOrderID=" + FoodOrderID + ", OrderID=" + OrderID + ", bbqOrderID=" + bbqOrderID + ", foodDate=" + foodDate + ", totalSum=" + totalSum + ", haveDelete=" + haveDelete
				+ ", MemberOrderBean=" + MemberOrderBean + ", BBQOrderBean=" + BBQOrderBean + "]";
	}

	public int getFoodOrderID() {
		return FoodOrderID;
	}

	
	public void setFoodOrderID(int foodOrderID) {
		FoodOrderID = foodOrderID;
	}

	
	public int getOrderID() {
		return OrderID;
	}

	
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	
	public int getBbqOrderID() {
		return bbqOrderID;
	}

	
	public void setBbqOrderID(int bbqOrderID) {
		this.bbqOrderID = bbqOrderID;
	}

	
	public java.util.Date getFoodDate() {
		return foodDate;
	}

	
	public void setFoodDate(java.util.Date foodDate) {
		this.foodDate = foodDate;
	}

	
	public float getTotalSum() {
		return totalSum;
	}

	
	public void setTotalSum(float totalSum) {
		this.totalSum = totalSum;
	}

	
	public boolean isHaveDelete() {
		return haveDelete;
	}

	
	public void setHaveDelete(boolean haveDelete) {
		this.haveDelete = haveDelete;
	}

}