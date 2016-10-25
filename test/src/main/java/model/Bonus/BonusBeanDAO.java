package model.Bonus;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;


@Component
public class BonusBeanDAO {

	private SessionFactory sessionFactory = null;
	public BonusBeanDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public BonusBeanDAO select(int id) {
		return (BonusBeanDAO)
				this.getSession().get(BonusBeanDAO.class, id);
	}
	
	public List<BonusBean> select(){
		Query query =
				this.getSession().createQuery("from BonusBean");
		return (List<BonusBean>) query.list();
	}
	
	public BonusBean insert(BonusBean bean) {
		BonusBean result = (BonusBean)
				this.getSession().get(BonusBean.class, bean.getVip());
		if(result==null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}
	public BonusBean update(String vip,
			Integer bonusExchange) {
		BonusBean result = (BonusBean)
				this.getSession().get(BonusBean.class, vip);
		if(result!=null) {
			result.setVip(vip);
			result.setBonusExchange(bonusExchange);
		}
		return result;
	}
	
	
}
