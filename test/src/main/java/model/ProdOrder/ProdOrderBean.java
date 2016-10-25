package model.ProdOrder;

import java.sql.Timestamp;
//商品訂單
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
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


import model.MemberOrder.MemberOrderBean;
import model.ProdOrderInfo.ProdOrderInfoBean;

@Entity
@Table(name = "ProdOrder")
@Component
public class ProdOrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProdOrderID;// 商品訂單編號
	private int OrderID;// 訂單編號
	private java.util.Date prodDate;// 商品日期
	private float totalSum;// 總金額
	private boolean haveDelete;// 是否刪除

	// -------------ProdOrderInfoBean-------------------------------------------
	@OneToMany(

				mappedBy = "ProdOrderBean" ,
				cascade = { CascadeType.REMOVE }

	)
	private Set<ProdOrderInfoBean> ProdOrderInfo;

	public Set<ProdOrderInfoBean> getProdOrderInfo() {
		return ProdOrderInfo;
	}

	public void setProdOrderInfo(Set<ProdOrderInfoBean> prodOrderInfo) {
		ProdOrderInfo = prodOrderInfo;
	}

	// --------------MemberOrderBean-------------------------------------
	@ManyToOne
	@JoinColumn(

				name = "OrderID" ,
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

	// --------------------------------------------------------------------
	public static void main(String[] args) {
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();

			// 新增
//			ProdOrderBean insert = new ProdOrderBean();
//			insert.setOrderID(1);
//			insert.setProdDate(Timestamp.valueOf("2012-12-12 12:12:12"));
//			insert.setTotalSum(200);
//			insert.setHaveDelete(true);
//			session.save(insert);
//			System.out.println("insert=" + insert);
			// 查詢
			 ProdOrderBean select = (ProdOrderBean) session.get(ProdOrderBean.class,1);
			 System.out.println("select="+select.getProdOrderInfo());
			// 修改
			// ProdOrderBean update =(ProdOrderBean) session.get(ProdOrderBean.class,1);
			// update.setProdDate(Timestamp.valueOf("2018-10-14 12:12:12"));
			// update.setTotalSum(987465);
			// session.update(update);
			// System.out.println("update="+update);
			//// 刪除
			// ProdOrderBean select = (ProdOrderBean) session.get(ProdOrderBean.class,2);
			// session.delete(select);

				sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}


	}

	
	@Override
	public String toString() {
		return "ProdOrderBean [ProdOrderID=" + ProdOrderID + ", OrderID=" + OrderID + ", prodDate=" + prodDate + ", totalSum=" + totalSum + ", haveDelete=" + haveDelete + 
			", MemberOrderBean=" + MemberOrderBean + "]";
	}

	public int getProdOrderID() {
		return ProdOrderID;
	}

	
	public void setProdOrderID(int prodOrderID) {
		ProdOrderID = prodOrderID;
	}

	
	public int getOrderID() {
		return OrderID;
	}

	
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	
	public java.util.Date getProdDate() {
		return prodDate;
	}

	
	public void setProdDate(java.util.Date prodDate) {
		this.prodDate = prodDate;
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
