package model.ProductInfo;

//商品資訊
import java.util.Set;

import javax.persistence.CascadeType;
//欄位對照有多加欄位
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.FoodOrderInfo.FoodOrderInfoBean;
import model.ProdOrderInfo.ProdOrderInfoBean;

@Entity
@Table(name = "ProductInfo")
@Component
public class ProductInfoBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ProdCode;
	private String ProdClass;
	private String ProdName;
	private float ProdPrice;
	private int Inventory;
	private Boolean FoodCheck;
	private byte[] ProdImage;
//----------------ProdOrderInfo----------------------------------
	@OneToMany(	mappedBy = "ProductInfo" ,
				cascade = { CascadeType.REMOVE })

	private Set<ProdOrderInfoBean> ProdOrderInfo;
	
	public Set<ProdOrderInfoBean> getProdOrderInfo() {
		return ProdOrderInfo;
	}
		
	public void setProdOrderInfo(Set<ProdOrderInfoBean> prodOrderInfo) {
		ProdOrderInfo = prodOrderInfo;
	}
//------------------FoodOrderInfo---------------------------------------
	@OneToMany(	mappedBy = "ProductInfo" ,
			cascade = { CascadeType.REMOVE })

private Set<FoodOrderInfoBean>FoodOrderInfo;

	public Set<FoodOrderInfoBean> getFoodOrderInfo() {
		return FoodOrderInfo;
	}
	
	public void setFoodOrderInfo(Set<FoodOrderInfoBean> foodOrderInfo) {
		FoodOrderInfo = foodOrderInfo;
	}
	//---------------------------------------------------------------------
	public static void main(String[] args) {
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();

			// 新增
//			ProductInfoBean insert = new ProductInfoBean();
//			insert.setProdClass("玩具");
//			insert.setProdName("籃球");
//			insert.setProdPrice(100);
//			insert.setInventory(2);
//			insert.setFoodCheck(0);
//			session.save(insert);
//			System.out.println("insert=" + insert);
			// 查詢
			 ProductInfoBean select = (ProductInfoBean) session.get(ProductInfoBean.class,1);
			 System.out.println("select="+select.getFoodOrderInfo());
			// 修改
			// ProductInfoBean update =(ProductInfoBean) session.get(ProductInfoBean.class,1);
			// update.setProdCount(50);
			// update.setProdSum(987465);
			// session.update(update);
			// System.out.println("update="+update);
			//// 刪除
			// session.delete(select);

				sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	@Override
	public String toString() {
		return "ProductInfoBean [ProdCode=" + ProdCode + ", ProdClass=" + ProdClass + ", ProdName=" + ProdName + ", ProdPrice=" + ProdPrice + ", Inventory=" + Inventory + ", FoodCheck=" + FoodCheck
				+ "]";
	}

	public int getProdCode() {
		return ProdCode;
	}

	public void setProdCode(int prodCode) {
		ProdCode = prodCode;
	}

	public String getProdClass() {
		return ProdClass;
	}

	public void setProdClass(String prodClass) {
		ProdClass = prodClass;
	}

	public String getProdName() {
		return ProdName;
	}

	public void setProdName(String prodName) {
		ProdName = prodName;
	}

	public float getProdPrice() {
		return ProdPrice;
	}

	public void setProdPrice(float prodPrice) {
		ProdPrice = prodPrice;
	}

	public int getInventory() {
		return Inventory;
	}

	public void setInventory(int inventory) {
		Inventory = inventory;
	}

	public Boolean getFoodCheck() {
		return FoodCheck;
	}

	public void setFoodCheck(Boolean foodCheck) {
		FoodCheck = foodCheck;
	}

	public byte[] getProdImage() {
		return ProdImage;
	}

	public void setProdImage(byte[] prodImage) {
		ProdImage = prodImage;
	}

}
