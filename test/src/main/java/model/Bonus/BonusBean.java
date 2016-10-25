package model.Bonus;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.Member.MemberBean;

@Entity
@Table(name = "Bonus")
@Component
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
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();

			BonusBean select = (BonusBean) session.get(BonusBean.class,"0");
			System.out.println(select.getMemberBean());


			sessionFactory.getCurrentSession().getTransaction().commit();
			} finally {
				((ConfigurableApplicationContext) context).close();
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
