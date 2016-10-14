package model.Member;

import java.util.ArrayList;
//1.比對帳密2.改密碼 3.隱藏帳號4.新增會員5.修改資料7.紅利查詢、歷史清單8.我的留言9.我的評論10.訂單查詢6.上傳照片
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import model.Article.ArticleBean;
import model.BonusHistory.BonusHistoryBean;
import model.misc.HibernateUtil;

public class MemberService {

	private MemberDAO memberDao;

	public MemberService() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		memberDao = new MemberDAOHibernate(session);

	}

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

			MemberService memberService = new MemberService();
			List<BonusHistoryBean> bean = memberService.Bonus("EEE");
			System.out.println(bean);

			// MemberService MemberService = new MemberService();
			// boolean bean = MemberService.status("DDD");
			// System.out.println(bean);

			// MemberService MemberService = new MemberService();
			// MemberBean bean=new MemberBean();
			// bean.setAccount("EEE");
			// bean.setPwd("!!!@456".getBytes());
			// bean.setName("王大明");
			// bean.setID("Q789456123");
			// bean.setEmail("QQQ@WWW");
			// bean.setCelphone("0912345678");
			// bean.setTelephone("02-13456789");
			// bean.setAddress("台北市天龍國添隆路天龍巷");
			// bean.setSex("F");
			// bean.setBirthday(new Date());
			// MemberService.insert(bean);
			// System.out.println("bean="+bean);

			// MemberService MemberService = new MemberService();
			// MemberBean bean=new MemberBean();
			// bean.setName("王苗名");
			// bean.setID("Q789456123");
			// bean.setEmail("QQQ@WWW");
			// bean.setCelphone("0912345678");
			// bean.setTelephone("02-13456789");
			// bean.setAddress("台北市天龍國添隆路天龍巷");
			// bean.setSex("F");
			// bean.setBirthday(new Date());
			// bean.setMemberID(2);
			// MemberService.update(bean);
			// System.out.println("bean="+bean);
			// memberService.changePwd("AAA","@@@*456","!!!*789");

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	// 1.比對帳密
	public MemberBean login(String account, String pwd) {
		MemberBean bean = memberDao.selectAD(account);
		if (bean != null) {
			if (pwd != null && pwd.length() != 0) {
				byte[] pass = pwd.getBytes();
				byte[] temp = bean.getPwd();
				if (Arrays.equals(pass,temp)) {
					return bean;

				}
			}
		}
		return null;
	}

	// 2.改密碼
	public boolean changePwd(String account, String oldPwd, String newPwd) {

		MemberBean bean = this.login(account,oldPwd);
		if (bean != null) {
			if (newPwd != null && newPwd.length() != 0) {
				byte[] pass = newPwd.getBytes();
				return memberDao.changpwd(pass,bean.getEmail(),bean.getBirthday(),account);
			}
		}
		return false;

	}

	// 3.隱藏帳號(對應檢舉次數*)
	// 文章檢舉次數5次!!!!!!!!!才變成停權
	public boolean status(String account) {
		MemberBean bean = memberDao.selectAD(account);// 先取出所有資料
		if (bean != null) {// 有沒有資料
			Set<ArticleBean> Article = bean.getArticleBean();// 取出ArticleBean全部資料
			if (Article != null) {// 判斷有沒有
				for (Iterator<ArticleBean> it = Article.iterator(); it.hasNext();) {// 取出次數
					ArticleBean bean1 = (ArticleBean) it.next();
					int count = bean1.getReportCount();
					if (count >= 5) {
						boolean oldstatus = bean.getMemberStatus();
						boolean newstatus = true;
						oldstatus = newstatus;
						return memberDao.ADupdate(account,oldstatus);
					}
				}
			}
		}
		return false;
	}

	// 4.新增會員
	public MemberBean insert(MemberBean bean) {
		MemberBean result = null;
		if (bean != null) {
			result = memberDao.insert(bean);
		}
		return result;
	}

	// 5.修改會員資料
	public MemberBean update(MemberBean bean) {
		MemberBean result = null;
		if (bean != null) {
			result = memberDao.update(bean.getName(),bean.getSex(),bean.getBirthday(),bean.getID(),bean.getEmail(),bean.getCelphone(),bean.getTelephone(),bean.getAddress(),bean.getPhoto(),
					bean.getMemberID());
		}
		return result;
	}

	// 7.查詢紅利*
	public List<BonusHistoryBean> Bonus(String account) {
		List<BonusHistoryBean> result = null;
		MemberBean bean = memberDao.selectAD(account);
		if (bean != null) {
			Set<BonusHistoryBean> bouns = bean.getBonusHistoryBean();
			if (bouns != null) {
				result = new ArrayList<BonusHistoryBean>();
				result.addAll(bouns);
			}
		}
		return result;
	}
	
}
