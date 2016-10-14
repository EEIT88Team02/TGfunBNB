package model.BBQInfo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.hibernate.Session;

import model.BBQOrder.BBQOrderBean;
import model.misc.HibernateUtil;

@Entity
@Table(name = "BBQINFO")
public class BBQInfoBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bbqID; // 烤肉區編號
	private String bbqCode;// 烤肉區代號

	@ManyToOne
	@JoinColumn(name = "BBQID" ,
				referencedColumnName = "BBQID" ,
				insertable = false ,
				updatable = false)
	private BBQOrderBean bbqOrders;

	public BBQOrderBean getBbqOrders() {
		return bbqOrders;
	}

	public void setBbqOrders(BBQOrderBean bbqOrders) {
		this.bbqOrders = bbqOrders;
	}

	public static void main(String[] args) {

		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			/* 查詢單筆OK */
			 BBQInfoBean select=(BBQInfoBean)session.get(BBQInfoBean.class, 1);
			 System.out.println(select);

//			 BBQInfoBean select=(BBQInfoBean)session.get(BBQInfoBean.class, 1);
//			 System.out.println(select.getBbqOrders());

			/* 查詢全部 OK */
			// SQLQuery query=session.createSQLQuery("select* from BBQInfo");
			// query.addEntity(BBQInfoBean.class);
			// List<BBQInfoBean> beans=query.list();
			// System.out.println(beans);

			/* 新增 OK */
			// BBQInfoBean insert = new BBQInfoBean();
			// insert.setBbqCode("D");
			// session.save(insert);

			/* 修改 OK */
			// BBQInfoBean bean = (BBQInfoBean) session.get(BBQInfoBean.class,2);
			// bean.setBbqCode("N");

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	@Override
	public String toString() {
		return "BBQInfoBean [bbqID=" + bbqID + ", bbqCode=" + bbqCode + "]";
	}

	public int getBbqID() {
		return bbqID;
	}

	public void setBbqID(int bbqID) {
		this.bbqID = bbqID;
	}

	public String getBbqCode() {
		return bbqCode;
	}

	public void setBbqCode(String bbqCode) {
		this.bbqCode = bbqCode;
	}

}
