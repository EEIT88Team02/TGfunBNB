package model.FoodOrderInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


import model.FoodOrder.FoodOrderBean;
import model.ProductInfo.ProductInfoBean;

@Entity
@Table(name = "FoodOrderInfo")
@Component
public class FoodOrderInfoBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int FoodorderInfoID;//食材訂單明細編號
	private int ProdCode;// 商品代號
	private int FoodOrderID;// 食材訂單編號
	private int FoodCount;// 數量
	private float FoodSum;// 食材小計


	// --------------FoodOrderBean-----------------------------------
	@ManyToOne
	@JoinColumn(

				name = "foodOrderID" ,
				referencedColumnName = "foodOrderID" ,
				insertable = false ,
				updatable = false)

	private FoodOrderBean FoodOrder;

	public FoodOrderBean getFoodOrder() {
		return FoodOrder;
	}

	public void setFoodOrder(FoodOrderBean foodOrder) {
		FoodOrder = foodOrder;
	}

	// --------------ProductInfoBean-----------------------------------------
	@ManyToOne
	@JoinColumn(

				name = "prodCode" ,
				referencedColumnName = "prodCode" ,
				insertable = false ,
				updatable = false)

	private ProductInfoBean ProductInfo;

	public ProductInfoBean getProductInfo() {
		return ProductInfo;
	}

	public void setProductInfo(ProductInfoBean productInfo) {
		ProductInfo = productInfo;
	}

	// ---------------------------------------------------
	public static void main(String[] args) {
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();

			// 新增
//			FoodOrderInfoBean insert = new FoodOrderInfoBean();
//			 insert.setProdCode(1);
//			 insert.setFoodOrderID(5);
//			 insert.setFoodCount(5);
//			 insert.setFoodSum(57879);
//			 session.save(insert);
//			 System.out.println("insert=" + insert);
			// 查詢
//			FoodOrderInfoBean select = (FoodOrderInfoBean) session.get(FoodOrderInfoBean.class,9);
//			 System.out.println("select="+select);
			//// 刪除*
			 FoodOrderInfoBean select = (FoodOrderInfoBean) session.get(FoodOrderInfoBean.class,9);
			 session.delete(select);
//			// 修改*
//			FoodOrderInfoBean update = (FoodOrderInfoBean) session.get(FoodOrderInfoBean.class,12);
//			update.setFoodCount(12);;
//			update.setFoodSum(8596);
//			session.update(update);
//			System.out.println("update=" + update);
				sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}

	}

	@Override
	public String toString() {
		return "FoodOrderInfoBean [FoodorderInfoID=" + FoodorderInfoID + ", ProdCode=" + ProdCode + ", FoodOrderID=" + FoodOrderID + ", FoodCount=" + FoodCount + ", FoodSum=" + FoodSum
				+ ", FoodOrder=" + FoodOrder + ", ProductInfo=" + ProductInfo + "]";
	}

	
	public int getFoodorderInfoID() {
		return FoodorderInfoID;
	}

	
	public void setFoodorderInfoID(int foodorderInfoID) {
		FoodorderInfoID = foodorderInfoID;
	}

	
	public int getProdCode() {
		return ProdCode;
	}

	
	public void setProdCode(int prodCode) {
		ProdCode = prodCode;
	}

	
	public int getFoodOrderID() {
		return FoodOrderID;
	}

	
	public void setFoodOrderID(int foodOrderID) {
		FoodOrderID = foodOrderID;
	}

	
	public int getFoodCount() {
		return FoodCount;
	}

	
	public void setFoodCount(int foodCount) {
		FoodCount = foodCount;
	}

	
	public float getFoodSum() {
		return FoodSum;
	}

	
	public void setFoodSum(float foodSum) {
		FoodSum = foodSum;
	}

	

}
