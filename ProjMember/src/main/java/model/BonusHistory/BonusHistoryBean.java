package model.BonusHistory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import model.Member.MemberBean;
import model.misc.HibernateUtil;

@Entity
@Table(name = "bonusHistory")
public class BonusHistoryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bonusHistoryId")
	private Integer bonusHistoryId;
	@Column(name = "memberID")
	private Integer memberID;
	@Column(name = "costBonus")
	private Integer costBonus;
	@Column(name = "leftBonus")
	private Integer leftBonus;
	@Column(name = "useDate")
	private java.util.Date useDate;
	// ---------MemberBean--------------------
	@ManyToOne
	@JoinColumn(name = "memberID" ,
				referencedColumnName = "memberID" ,
				insertable = false ,
				updatable = false

	)
	private MemberBean MemberBean;

	public MemberBean getMemberBean() {
		return MemberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		MemberBean = memberBean;
	}
//----------------------------------------------
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			BonusHistoryBean select = (BonusHistoryBean) session.get(BonusHistoryBean.class,2);
			System.out.println(select.getMemberBean());
			
//			BonusHistoryBean insert = new BonusHistoryBean();
//			insert.setMemberID(6);
//			insert.setCostBonus(20);
//			insert.setLeftBonus(50);
//			insert.setUseDate(new java.util.Date());
//			 session.save(insert);
//			 System.out.println("insert=" + insert);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	@Override
	public String toString() {
		return "MESSAGEBean [bonusHistoryId=" + bonusHistoryId + ", memberID=" + memberID + ", costBonus=" + costBonus + ", leftBonus=" + leftBonus + ", useDate=" + useDate + "]";
	}

	public Integer getBonusHistoryId() {
		return bonusHistoryId;
	}

	public void setBonusHistoryId(Integer bonusHistoryId) {
		this.bonusHistoryId = bonusHistoryId;
	}

	public Integer getMemberID() {
		return memberID;
	}

	public void setMemberID(Integer memberID) {
		this.memberID = memberID;
	}

	public Integer getCostBonus() {
		return costBonus;
	}

	public void setCostBonus(Integer costBonus) {
		this.costBonus = costBonus;
	}

	public Integer getLeftBonus() {
		return leftBonus;
	}

	public void setLeftBonus(Integer leftBonus) {
		this.leftBonus = leftBonus;
	}

	public java.util.Date getUseDate() {
		return useDate;
	}

	public void setUseDate(java.util.Date useDate) {
		this.useDate = useDate;
	}

}
