package model.Article;

import java.text.ParseException;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Message.MessageBean;
import model.Report.ReportBean;
import model.Member.MemberBean;

@Entity
@Table(name = "Article")

public class ArticleBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ArtCode;
	

	private int memberID;
	private int ReportCount;
	private String ArtTopic;
	private String ArtContent;
	private java.util.Date ArtDate;

	private java.util.Date appealDate;
	private boolean HaveAppeal;
	private String AppealContent;
	private boolean HaveProcess;
	private java.util.Date ProcessDate;
	private String ReportReply;
	private boolean haveDelete;

	


	public int getArtCode() {
		return ArtCode;
	}

	public void setArtCode(int artCode) {
		ArtCode = artCode;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public int getReportCount() {
		return ReportCount;
	}

	public void setReportCount(int reportCount) {
		ReportCount = reportCount;
	}

	public String getArtTopic() {
		return ArtTopic;
	}

	public void setArtTopic(String artTopic) {
		ArtTopic = artTopic;
	}

	public String getArtContent() {
		return ArtContent;
	}

	public void setArtContent(String artContent) {
		ArtContent = artContent;
	}

	public java.util.Date getArtDate() {
		return ArtDate;
	}

	public void setArtDate(java.util.Date artDate) {
		ArtDate = artDate;
	}

	public java.util.Date getAppealDate() {
		return appealDate;
	}

	public void setAppealDate(java.util.Date appealDate) {
		this.appealDate = appealDate;
	}

	public boolean isHaveAppeal() {
		return HaveAppeal;
	}

	public void setHaveAppeal(boolean haveAppeal) {
		HaveAppeal = haveAppeal;
	}

	public String getAppealContent() {
		return AppealContent;
	}

	public void setAppealContent(String appealContent) {
		AppealContent = appealContent;
	}

	public boolean isHaveProcess() {
		return HaveProcess;
	}

	public void setHaveProcess(boolean haveProcess) {
		HaveProcess = haveProcess;
	}

	public java.util.Date getProcessDate() {
		return ProcessDate;
	}

	public void setProcessDate(java.util.Date processDate) {
		ProcessDate = processDate;
	}

	public String getReportReply() {
		return ReportReply;
	}

	public void setReportReply(String reportReply) {
		ReportReply = reportReply;
	}

	public boolean isHaveDelete() {
		return haveDelete;
	}

	public void setHaveDelete(boolean haveDelete) {
		this.haveDelete = haveDelete;
	}
	@OneToMany(	mappedBy = "articles" ,
				cascade = { CascadeType.REMOVE })
	private Set<ReportBean> reports;

	public Set<ReportBean> getReports() {
		return reports;
	}

	public void setReports(Set<ReportBean> reports) {
		this.reports = reports;
	}
  @OneToMany( cascade={CascadeType.ALL})   
   private Set<MessageBean> messages;	
	
public Set<MessageBean> getMessages() {
	return messages;
}
public void setMessages(Set<MessageBean> messages) {
	this.messages = messages;
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

	public static void main(String[] args) throws ParseException {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		
		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			//	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		//	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			// ArticleBean insert = new ArticleBean();
			  
			 // insert.setMemberID(1);
			// insert.setReportCount(1);
			// insert.setArtContent("aa");
			// insert.setArtTopic("bb");
			// insert.setArtDate(new java.util.Date());
			// insert.setAppealDate(new java.util.Date());
			// insert.setHaveAppeal(true);
			// insert.setAppealContent("f");
			// insert.setHaveProcess(false);
			// insert.setProcessDate(new java.util.Date());
			// insert.setReportReply("h");
			// insert.sethaveDelete(false);
			//
			//
			// session.save(insert);

			 ArticleBean select = (ArticleBean) session.get(ArticleBean.class,2);
			 System.out.println("select="+select );

			// ArticleBean select = (ArticleBean) session.get(ArticleBean.class,1);
			// System.out.println(select.getReports());

			// ArticleBean select = (ArticleBean) session.get(ArticleBean.class,1);
			// System.out.println(select.getMembers());

			// ArticleBean select = (ArticleBean) session.get(ArticleBean.class, 2);
			// select.setMessageTopic("ddd");
			// select.setMessageContent("eee");
			// select.setAskDate(new java.util.Date());

			// ArticleBean select = (ArticleBean) session.get(ArticleBean.class,3);
			// session.delete(select);

		//	HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			 sessionFactory.getCurrentSession().getTransaction().commit();
		}
		finally {
		//	HibernateUtil.closeSessionFactory();
		}

	}

}
