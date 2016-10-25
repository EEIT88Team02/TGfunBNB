package model.BonusHistory;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
@Component
public class BonusHistoryBeanDAO {
	
	private SessionFactory sessionFactory = null;
	public BonusHistoryBeanDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public BonusHistoryBean select(int id) {
		return (BonusHistoryBean)
				this.getSession().get(BonusHistoryBean.class, id);
	}
	public List<BonusHistoryBean> select() {
		Query query =
				this.getSession().createQuery("from BonusHistoryBean");
		return (List<BonusHistoryBean>) query.list();
	}
	public BonusHistoryBean insert(BonusHistoryBean bean) {
		BonusHistoryBean result = (BonusHistoryBean)
				this.getSession().get(BonusHistoryBean.class, bean.getBonusHistoryId());
		if(result==null) {
			this.getSession().save(bean);
			return bean;
		}
		return null;
	}
	public BonusHistoryBean update(
			Integer bonusHistoryId,Integer memberID,Integer costBonus,Integer leftBonus,java.util.Date useDate) {
		BonusHistoryBean result = (BonusHistoryBean)
				this.getSession().get(BonusHistoryBean.class, bonusHistoryId);
		if(result!=null) {
			result.setBonusHistoryId(bonusHistoryId);
			result.setMemberID(memberID);
			result.setCostBonus(costBonus);
			result.setLeftBonus(leftBonus);
			result.setUseDate(useDate);
		}
		return result;
	}
	public boolean delete(int id) {
		BonusHistoryBean bean = (BonusHistoryBean) this.getSession().get(BonusHistoryBean.class, id);
		if(bean!=null) {
			this.getSession().delete(bean);
			return true;
		}
		return false;
	}
}
