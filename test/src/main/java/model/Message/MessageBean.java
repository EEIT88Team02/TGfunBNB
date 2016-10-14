package model.Message;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import model.Article.ArticleBean;
import model.misc.HibernateUtil;
import tgfunBean.MemberBean;

@Entity
@Table(name = "Message")
public class MessageBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int msgCode;
	private int memberID;
	private int artCode;
	private String msgContent;
	private java.util.Date msgDate;
	private int good;
	private int bad;
     
	

	@Override
	public String toString() {
		return "MessageBean [msgCode=" + msgCode + ", memberID=" + memberID + ", artCode=" + artCode + ", msgContent=" + msgContent + ", msgDate=" + msgDate + ", good=" + good + ", bad=" + bad
				+ ", members=" + members + "]";
	}

	public int getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(int msgCode) {
		this.msgCode = msgCode;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public int getArtCode() {
		return artCode;
	}

	public void setArtCode(int artCode) {
		this.artCode = artCode;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public java.util.Date getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(java.util.Date msgDate) {
		this.msgDate = msgDate;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	@ManyToOne
	@JoinColumn(name = "MEMBERID" ,
				referencedColumnName = "MEMBERID" ,
				insertable = false ,
				updatable = false)
	private MemberBean members;

	public MemberBean getMembers() {
		return members;
	}

	public void setMembers(MemberBean members) {
		this.members = members;
	}

//	@ManyToOne
//	@JoinColumn(name = "MSGCODE" ,
//				referencedColumnName = "ArtCode" ,
//				insertable = false ,
//				updatable = false)
//	private ArticleBean articles;
//
//	public ArticleBean getMessages() {
//		return articles;
//	}
//
//	public void setMessages(ArticleBean messages) {
//		this.articles = messages;
//	}

	public static void main(String[] args) throws ParseException {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//			 MessageBean insert = new MessageBean();
//			 insert.setMemberID(1);
//			 insert.setArtCode(1);
//			 insert.setMsgContent("");
//			 insert.setMsgDate(new Date());
//			 insert.setGood(5);
//			 insert.setBad(1);
//			 session.save(insert);

			MessageBean select = (MessageBean) session.get(MessageBean.class,4);
			System.out.println("select=" + select);

//			 MessageBean select = (MessageBean) session.get(MessageBean.class, 1);
//			 System.out.println("select=" + select.getMembers());

//			 MessageBean select2= (MessageBean) session.get(MessageBean.class,1);
//			 System.out.println("select=" + select2.getMessages());
			// select.setReply("hi");
//
//			 MessageBean select = (MessageBean) session.get(MessageBean.class,1);
//			 session.delete(select);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}

	}

}
