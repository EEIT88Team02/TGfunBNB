package model.Bonus;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import model.Member.MemberBean;
import model.misc.HibernateUtil;

@Entity
@Table(name = "Bonus")
public class BonusBean {

	@Id
	@Column(name = "vip")
	private String vip;
	@Column(name = "bonusExchange")
	private Integer bonusExchange;

	// ---------------MemberBean---------------------
	@OneToMany(	mappedBy = "BonusBean" ,
				cascade = { CascadeType.REMOVE }

	)

	private Set<MemberBean> MemberBean;

	public Set<MemberBean> getMemberBean() {
		return MemberBean;
	}

	public void setMemberBean(Set<MemberBean> memberBean) {
		MemberBean = memberBean;
	}
//-----------------------------------------------------
	public static void main(String[] args) {

		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			BonusBean select = (BonusBean) session.get(BonusBean.class,"0");
			System.out.println(select.getMemberBean());

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}

	}

	@Override
	public String toString() {
		return "BonusBean [vip=" + vip + ", bonusExchange=" + bonusExchange + "]";
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public Integer getBonusExchange() {
		return bonusExchange;
	}

	public void setBonusExchange(Integer bonusExchange) {
		this.bonusExchange = bonusExchange;
	}

}
