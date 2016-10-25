package model.ProdOrder;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.Member.MemberDAOHibernate;
@Component
public class ProdOrderDAOHibernate implements ProdOrderDAO {
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		ProdOrderDAOHibernate dao = (ProdOrderDAOHibernate) context.getBean("prodOrderDAOHibernate");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();		// 查詢
			// 查詢
//			 ProdOrderDAOHibernate dao = new ProdOrderDAOHibernate(session);
//			 ProdOrderBean bean = dao.select(1);
//			 System.out.println("bean="+bean);
			// 新增
//			 ProdOrderDAOHibernate insert =new ProdOrderDAOHibernate(session);
//			 ProdOrderBean bean=new ProdOrderBean();
//			 bean.setOrderID(1);
//			 bean.setProdDate(Timestamp.valueOf("2012-12-12 12:12:12"));
//			 bean.setTotalSum(2550);
//			 insert.insert(bean);
//			 System.out.println("insert=" + insert);
			//
			// 修改
//			ProdOrderDAOHibernate update = new ProdOrderDAOHibernate(session);
//			update.update(1,1,Timestamp.valueOf("2012-12-24 12:12:12"),13645654);
			// 刪除
//			 ProdOrderDAOHibernate dao = new ProdOrderDAOHibernate(session);
//			 dao.delete(3);
			//查詢全部			
//			ProdOrderDAOHibernate selectall = new ProdOrderDAOHibernate(session);
//			List<ProdOrderBean> bean = selectall.selectall();
//			System.out.println(bean);
			
				

			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
	}

	
	
	// session-----------------------------------------------

	private SessionFactory sessionFactory = null;
	public ProdOrderDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// method---------------------------------------------
	public ProdOrderBean select(int ProdOrderID) {

		return (ProdOrderBean) this.getSession().get(ProdOrderBean.class,ProdOrderID);
	}

	public ProdOrderBean update(int ProdOrderID, int OrderID, Timestamp prodDate, float totalSum) {
		ProdOrderBean result = (ProdOrderBean) this.getSession().get(ProdOrderBean.class,ProdOrderID);
		if (result != null) {
			result.setProdOrderID(ProdOrderID);
			result.setOrderID(OrderID);
			result.setProdDate(prodDate);
			result.setTotalSum(totalSum);

		}
		return result;
	}

	public ProdOrderBean insert(ProdOrderBean bean) {
		ProdOrderBean temp = (ProdOrderBean) this.getSession().get(ProdOrderBean.class,bean.getProdOrderID());
		if (temp == null) {
			this.getSession().save(bean);
		}
		return null;
	}

	public boolean delete(int ProdOrderID) {
		ProdOrderBean bean = (ProdOrderBean) this.getSession().get(ProdOrderBean.class,ProdOrderID);
		if (bean != null) {
			this.getSession().delete(bean);
			return true;
		}

		return false;
	}

	public List<ProdOrderBean> selectall() {
		Query query =
				this.getSession().createQuery("from ProdOrderBean");
		return (List<ProdOrderBean>) query.list();

	}

}
