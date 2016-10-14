package model.Member;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Article.ArticleBean;
import model.BonusHistory.BonusHistoryBean;
import model.misc.HibernateUtil;

public class MemberDAOHibernate implements MemberDAO {

	// 測試程式
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			// 查詢
			 MemberDAOHibernate dao = new MemberDAOHibernate(session);
			//dao.changpwd("@@@*456".getBytes(),"qwe@qwe",new java.util.Date(),"AAA"); 
//			 boolean bean =dao.ADupdate("QQQ",false);
//			 System.out.println(bean);
			// 新增
//			 MemberDAOHibernate insert =new MemberDAOHibernate(session);
//			 MemberBean bean=new MemberBean();
//			 bean.setAccount("AAA");
//			 bean.setPwd("!!!@123".getBytes());
//			 bean.setName("好奇怪");
//			 bean.setID("Q456456456");
//			 insert.insert(bean);
//			 System.out.println("insert=" + insert);
//			//
			// 修改
			MemberDAOHibernate update = new MemberDAOHibernate(session);
			update.update("張曉明","male",new Date(),"Q123546789","qwe@qwe","0913245678","021346789","OOXX0","".getBytes(),2);
	
			//查詢全部			
			MemberDAOHibernate selectall = new MemberDAOHibernate(session);
			List<BonusHistoryBean> bean = selectall.selectBonus();
			System.out.println("bean="+bean);
			
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	// session-----------------------------------------------

	private  Session session = null;

	public MemberDAOHibernate(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	// method---------------------------------------------

	public MemberBean select(int MemberID) {// 只能用PK鍵

		return (MemberBean) this.getSession().get(MemberBean.class,MemberID);

	}

	public MemberBean update(String name, String sex, Date birthday, String ID, String email, String Celphone, String Telephone,String address, byte[] photo ,int MemberID) {
		MemberBean result = (MemberBean) this.getSession().get(MemberBean.class,MemberID);
		if (result != null) {
			result.setName(name);
			result.setSex(sex);
			result.setBirthday(birthday);
			result.setID(ID);
			result.setEmail(email);
			result.setCelphone(Celphone);
			result.setTelephone(Telephone);
			result.setAddress(address);
			result.setPhoto(photo);
		}
		return result;
	}

	public MemberBean insert(MemberBean bean) {
		MemberBean temp = (MemberBean) this.getSession().get(MemberBean.class,bean.getMemberID());
		if (temp == null) {
			this.getSession().save(bean);
		}
		return null;
	}


	public List<MemberBean> selectall() {
		Query query =
				this.getSession().createQuery("from MemberBean");
		return (List<MemberBean>) query.list();
	}

	public MemberBean selectAD(String account) {
		Query query = this.getSession().createQuery("from MemberBean where account = ?");
		query.setString(0,account);
		MemberBean mb = (MemberBean)query.uniqueResult();		
			return mb;
	}

	public boolean changpwd(byte[] pwd, String email, Date birthday, String account) {
	
		Query query = this.getSession().createQuery("from MemberBean where account = ?");
		query.setString(0,account);
		MemberBean changpwd = (MemberBean)query.uniqueResult();	
		if (changpwd != null) {
			changpwd.setPwd(pwd);
		}			
		return true;
		
	}

	public boolean  ADupdate(String account,boolean memberStatus) {
		Query query = this.getSession().createQuery("from MemberBean where account = ?");
		query.setString(0,account);
		MemberBean status = (MemberBean)query.uniqueResult();	
		if (status != null) {
			status.setMemberStatus(memberStatus);
		}			
		return true;
	}

	public List<BonusHistoryBean> selectBonus() {
		Query query =
				this.getSession().createQuery("from BonusHistoryBean");
		return (List<BonusHistoryBean>) query.list();
		
	}


}
