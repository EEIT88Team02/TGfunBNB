package model.FoodOrderInfo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import model.Member.MemberDAOHibernate;

public class FoodOrderInfoDAOHibernate implements FoodOrderInfoDAO {

	// 測試程式
	public static void main(String[] args) {
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		FoodOrderInfoDAOHibernate dao = (FoodOrderInfoDAOHibernate) context.getBean("foodOrderInfoDAOHibernate");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();		// 查詢
			// 查詢
			// FoodOrderInfoDAOHibernate dao = new FoodOrderInfoDAOHibernate(session);
			// FoodOrderInfoBean bean = dao.select(1);
			// System.out.println("bean="+bean);
			// 新增
//			FoodOrderInfoDAOHibernate insert = new FoodOrderInfoDAOHibernate(session);
//			FoodOrderInfoBean bean = new FoodOrderInfoBean();
//			bean.setProdCode(1);
//			bean.setFoodOrderID(6);
//			bean.setFoodCount(5);
//			bean.setFoodSum(57879);
//			insert.insert(bean);
//			System.out.println("insert=" + insert);
			//
			// 修改
//			 FoodOrderInfoDAOHibernate update = new FoodOrderInfoDAOHibernate(session);
//			 update.update(13,1,6,5,100);
			// 刪除
//			 FoodOrderInfoDAOHibernate dao = new FoodOrderInfoDAOHibernate(session);
//			 dao.delete(13);
			// 查詢全部
			 FoodOrderInfoDAOHibernate selectall = new FoodOrderInfoDAOHibernate(sessionFactory);
			 List<FoodOrderInfoBean> bean = selectall.selectall();
			 System.out.println(bean);


				sessionFactory.getCurrentSession().getTransaction().commit();
			} finally {
				((ConfigurableApplicationContext) context).close();
			}
		}
	

	// session-------------------------------

	private SessionFactory sessionFactory = null;
	public  FoodOrderInfoDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// method----------------------------------------------

	public FoodOrderInfoBean select(int FoodorderInfoID) {

		return (FoodOrderInfoBean) this.getSession().get(FoodOrderInfoBean.class,FoodorderInfoID);
	}

	public FoodOrderInfoBean update(int FoodorderInfoID, int ProdCode, int FoodOrderID, int FoodCount, float FoodSum) {
		FoodOrderInfoBean result = (FoodOrderInfoBean) this.getSession().get(FoodOrderInfoBean.class,FoodorderInfoID);
		if (result != null) {
			result.setProdCode(ProdCode);
			result.setFoodOrderID(FoodOrderID);
			result.setFoodCount(FoodCount);
			result.setFoodSum(FoodSum);
		}
		return result;
	}

	public FoodOrderInfoBean insert(FoodOrderInfoBean bean) {
		FoodOrderInfoBean temp = (FoodOrderInfoBean) this.getSession().get(FoodOrderInfoBean.class,bean.getFoodorderInfoID());
		if (temp == null) {
			this.getSession().save(bean);
		}

		return null;
	}

	public boolean delete(int FoodorderInfoID) {
		FoodOrderInfoBean bean = (FoodOrderInfoBean) this.getSession().get(FoodOrderInfoBean.class,FoodorderInfoID);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}

	public List<FoodOrderInfoBean> selectall() {
		Query query = this.getSession().createQuery("from FoodOrderInfoBean");
		return (List<FoodOrderInfoBean>) query.list();
	}

}
