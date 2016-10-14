package model.ProdOrderInfo;

//商品訂單明細
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import model.ProdOrder.ProdOrderBean;
import model.ProductInfo.ProductInfoBean;
import model.misc.HibernateUtil;

@Entity
@Table(name = "ProdOrderInfo")
public class ProdOrderInfoBean {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int prodorderinfoID;// 商品訂單明細編號
	private int prodOrderID;// 商品訂單編號
	private int prodCode;// 商品代號
	private int prodCount;// 數量
	private int prodSum;// 商品小計

	// ---------ProdOrderBean-------------------------
	@ManyToOne
	@JoinColumn(

				name = "prodOrderID" ,
				referencedColumnName = "prodOrderID" ,
				insertable = false ,
				updatable = false)

	private ProdOrderBean ProdOrderBean;

	public ProdOrderBean getProdOrderBean() {
		return ProdOrderBean;
	}

	public void setProdOrderBean(ProdOrderBean prodOrderBean) {
		ProdOrderBean = prodOrderBean;
	}

	// --------------ProductInfo--------------------------------------
	@ManyToOne
	@JoinColumn(name = "prodCode" ,
				referencedColumnName = "prodCode" ,
				insertable = false ,
				updatable = false

	)
	private ProductInfoBean ProductInfo;

	public ProductInfoBean getProductInfo() {
		return ProductInfo;
	}

	public void setProductInfo(ProductInfoBean productInfo) {
		ProductInfo = productInfo;
	}

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			// 新增
//			ProdOrderInfoBean insert = new ProdOrderInfoBean();		
//			insert.setProdOrderID(1);
//			insert.setProdCode(2);
//			insert.setProdCount(5);
//			insert.setProdSum(5000);
//			session.save(insert);
//			System.out.println("insert=" + insert);
			// 查詢
			 ProdOrderInfoBean select = (ProdOrderInfoBean) session.get(ProdOrderInfoBean.class,1);
			 System.out.println("select="+select.getProdOrderBean());
			//// 刪除
			// session.delete(select);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}

	}

	@Override
	public String toString() {
		return "ProdOrderInfoBean [prodorderinfoID=" + prodorderinfoID + ", prodOrderID=" + prodOrderID + ", prodCode=" + prodCode + ", prodCount=" + prodCount + ", prodSum=" + prodSum
				+ ", ProdOrderBean=" + ProdOrderBean + ", ProductInfo=" + ProductInfo + "]";
	}

	public int getProdorderinfoID() {
		return prodorderinfoID;
	}

	public void setProdorderinfoID(int prodorderinfoID) {
		this.prodorderinfoID = prodorderinfoID;
	}

	public int getProdOrderID() {
		return prodOrderID;
	}

	public void setProdOrderID(int prodOrderID) {
		this.prodOrderID = prodOrderID;
	}

	public int getProdCode() {
		return prodCode;
	}

	public void setProdCode(int prodCode) {
		this.prodCode = prodCode;
	}

	public int getProdCount() {
		return prodCount;
	}

	public void setProdCount(int prodCount) {
		this.prodCount = prodCount;
	}

	public int getProdSum() {
		return prodSum;
	}

	public void setProdSum(int prodSum) {
		this.prodSum = prodSum;
	}
}
