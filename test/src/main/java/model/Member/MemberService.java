package model.Member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
//1*.比對帳密2*.改密碼 3.隱藏帳號4*.新增會員5*.修改資料7.紅利查詢、歷史清單8.我的留言10.訂單查詢6.上傳照片
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.Article.ArticleBean;
import model.BonusHistory.BonusHistoryBean;
import model.MemberOrder.MemberOrderBean;
import model.Message.MessageBean;

@Component
public class MemberService {
	@Autowired
	private MemberDAO memberDao;

	public MemberService(MemberDAO memberDao) {
		memberDao = memberDao;

	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		MemberService memberService = (MemberService) context.getBean("memberService");		
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();

//			 MemberService memberService = new MemberService();
//			 MemberBean bean = memberService.select(78);
//			 System.out.println(bean);

			// MemberService MemberService = new MemberService();
			// boolean bean = MemberService.status("DDD");
			// System.out.println(bean);

//			MemberService MemberService = new MemberService();
//			MemberBean bean = new MemberBean();
//			bean.setAccount("FFF");
//			bean.setPwd("F".getBytes());
//			bean.setName("王小明");
//			bean.setID("Q789456123");
//			bean.setEmail("QQQ@WWW");
//			bean.setCelphone("0912345678");
//			bean.setTelephone("02-13456789");
//			bean.setAddress("台北市天龍國添隆路天龍巷");
//			bean.setSex("F");
//			bean.setBirthday(new Date());
//			MemberService.insert(bean);
//			System.out.println("bean=" + bean);

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

			sessionFactory.getCurrentSession().getTransaction().commit();
			} finally {
				((ConfigurableApplicationContext) context).close();
			}
	}
	

	// 總訂單
	public Set<MemberOrderBean> select(int MemberID) {
		System.out.println("service1");
		MemberBean result = memberDao.select(MemberID);
		System.out.println("service2");
		Set<MemberOrderBean> mo = result.getMemberOrders();
		System.out.println("service3="+mo);		
		return mo;

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
	public boolean changePwd(String account, String oldPwd, byte[] newpwd) {
		MemberBean bean = this.login(account,oldPwd);
		if (bean != null) {
			if (newpwd != null && newpwd.length != 0) {

				return memberDao.changpwd(newpwd,account);
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
			bean.setSsl(true);
			bean.setMemberStatus(true);
			result = memberDao.insert(bean);

		}
		return result;
	}

	// 5.修改會員資料
	public MemberBean update(MemberBean bean) {
		System.out.println("service1");
		MemberBean result = null;
		System.out.println("service2=" + result);
		if (bean != null) {
			System.out.println("service3=" + bean);
			result = memberDao.update(bean.getPwd(),bean.getName(),bean.getSex(),bean.getBirthday(),bean.getID(),bean.getEmail(),bean.getCelphone(),bean.getTelephone(),bean.getAddress(),
					bean.getPhoto(),bean.getMemberID());
			System.out.println("service4=" + result);
		}
		return result;
	}

	// 8.查詢留言
	public ArticleBean Article(int MemberID) {
		System.out.println("Service");
		MemberBean result = memberDao.select(MemberID);
		System.out.println("ServiceSTEP0=" + result);
		if (result != null) {
			System.out.println("ServiceSTEP0-1=" + result);
			Set<ArticleBean> Article = result.getArticleBean();
			System.out.println("ServiceSTEP1=" + Article);
			if (Article != null) {
				for (Iterator<ArticleBean> it = Article.iterator(); it.hasNext();) {
					ArticleBean bean = (ArticleBean) it.next();
					return bean;
				}

			}

		}
		return null;
	}

	public MemberBean email(String email) {
		System.out.println("mail-1");
		String body="測試測試   http://localhost:8080/test/index.jsp";
		String host = "smtp.gmail.com";
		int port = 587;

		final String username = "eeit.team07@gmail.com";
		final String password = "eeitteam07";// your password
		System.out.println("mail-2");
		Properties props = new Properties();

		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.transport.protocl","smtp");
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","465");
		props.put("mail.smtp.auth","true");
		props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback","false");
		props.setProperty("mail.smtp.socketFactory.port","465");
		System.out.println("mail-3");
		javax.mail.Session session = javax.mail.Session.getInstance(props,new Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username , password);
			}
		});

		try {
			System.out.println("mail-4");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("eeit.team07@gmail.com"));
			System.out.println("mail-5");
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			System.out.println("mail-6");
			message.isMimeType("text/plain;charset=UTF-8");
			message.setSubject("測試寄信.");
			message.setText(body);
			message.setContent(body,"text/html;charset = UTF-8");
			System.out.println("mail-7");
			Transport transport = session.getTransport("smtp");
			System.out.println("mail-8");
			transport.connect(host,port,username,password);
			System.out.println("mail-9");
			Transport.send(message);
			System.out.println(message);
			System.out.println("mail-10");
			System.out.println("寄送email結束.");
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return null;

	}

}
