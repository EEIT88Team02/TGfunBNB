package model.FoodOrder;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.misc.HibernateUtil;

public class FoodOrderDAOHibernate implements FoodOrderDAO {
	
	// 測試程式
		public static void main(String[] args) {
			try {
				HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				// 查詢
				 FoodOrderDAOHibernate dao = new FoodOrderDAOHibernate(session);
				 FoodOrderBean bean = dao.select(1);
				 System.out.println("bean="+bean);
				 //新增
//				 FoodOrderDAOHibernate insert =new FoodOrderDAOHibernate(session);
//				  FoodOrderBean bean=new  FoodOrderBean();
//					bean.setOrderID(1);
//					bean.setBbqOrderID(1);
//					String	dateTime="2016-12-12 12:12:12";
//					bean.setFoodDate(Timestamp.valueOf(dateTime));   
//					bean.setTotalSum(100);
//					insert.insert(bean);
//				 System.out.println("insert=" + insert);
				//
				// 修改
//				FoodOrderDAOHibernate update = new FoodOrderDAOHibernate(session);
//				update.update(3,1,1,Timestamp.valueOf("2018-12-12 12:12:12"),5,500);
//				 刪除
//				 FoodOrderDAOHibernate dao = new FoodOrderDAOHibernate(session);
//				 dao.delete(2);
				//查詢全部			
//				FoodOrderDAOHibernate selectall = new FoodOrderDAOHibernate(session);
//				List<FoodOrderBean> bean = selectall.selectall();
//				System.out.println(bean);
				
				
				
				HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			}
			finally {
				HibernateUtil.closeSessionFactory();
			}
		}
	

	// session-------------------------------

	private Session session = null;

	public FoodOrderDAOHibernate(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	// method----------------------------------------------

	public FoodOrderBean select(int foodOrderID) {

		return (FoodOrderBean) this.getSession().get(FoodOrderBean.class,foodOrderID);

	}

	public FoodOrderBean update(int foodOrderID, int OrderID, int bbqOrderID, Timestamp foodDate, int FoodCount, float totalSum) {
		FoodOrderBean result = (FoodOrderBean) this.getSession().get(FoodOrderBean.class,foodOrderID);
		if (result != null) {
			result.setFoodOrderID(foodOrderID);
			result.setOrderID(OrderID);
			result.setBbqOrderID(bbqOrderID);
			result.setFoodDate(foodDate);
			result.setTotalSum(totalSum);
		}
		return result;
	}

	public FoodOrderBean insert(FoodOrderBean bean) {
		FoodOrderBean temp = (FoodOrderBean) this.getSession().get(FoodOrderBean.class,bean.getFoodOrderID());
		if (temp == null) {
			this.getSession().save(bean);
		}
		return null;
	}

	public boolean delete(int foodOrderID) {
		FoodOrderBean bean = (FoodOrderBean) this.getSession().get(FoodOrderBean.class,foodOrderID);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
		}

	public List<FoodOrderBean> selectall() {
		Query query =
				this.getSession().createQuery("from FoodOrderBean");
		return (List<FoodOrderBean>) query.list();
	}

}
