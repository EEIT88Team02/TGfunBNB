package model.Report;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Session;

import model.Article.ArticleBean;
import model.misc.HibernateUtil;
import tgfunBean.MemberBean;

@Entity
@Table(	name = "REPORT" ,
		uniqueConstraints = { @UniqueConstraint(columnNames = { "REPORTMEMBERID", "ArtCode" }) })
public class ReportBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reportID;
	private int ArtCode;
	private int reportMemberID;
	private String reportContent;
	private java.util.Date reportDate;

	
	

	


	@Override
	public String toString() {
		return "ReportBean [reportID=" + reportID + ", ArtCode=" + ArtCode + ", reportMemberID=" + reportMemberID + ", reportContent=" + reportContent + ", reportDate=" + reportDate + "]";
	}


	public int getReportID() {
		return reportID;
	}

	
	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	
	public int getArtCode() {
		return ArtCode;
	}

	
	public void setArtCode(int artCode) {
		ArtCode = artCode;
	}

	
	public int getReportMemberID() {
		return reportMemberID;
	}

	
	public void setReportMemberID(int reportMemberID) {
		this.reportMemberID = reportMemberID;
	}

	
	public String getReportContent() {
		return reportContent;
	}

	
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

	
	public java.util.Date getReportDate() {
		return reportDate;
	}

	
	public void setReportDate(java.util.Date reportDate) {
		this.reportDate = reportDate;
	}

	
	public ArticleBean getArticles() {
		return articles;
	}

	
	public void setArticles(ArticleBean articles) {
		this.articles = articles;
	}

	@ManyToOne
	@JoinColumn(name = "ArtCode" ,
				referencedColumnName = "ArtCode" ,
				insertable = false ,
				updatable = false)
	private ArticleBean articles;

	public ArticleBean getArticle() {
		return articles;
	}

	public void setArticle(ArticleBean articles) {
		this.articles = articles;
	}

	@ManyToOne
	@JoinColumn(name = "REPORTMEMBERID" ,
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

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			// ReportBean insert = new ReportBean();
			// insert.setMsgCode(1);
			// insert.setReportMemberID(1);
			// insert.setReportContent("142");
			// insert.setReportDate(new Date());
			// session.save(insert);

			 ReportBean select = (ReportBean) session.get(ReportBean.class,1);
		 System.out.println("select=" + select);

//			 ReportBean select = (ReportBean) session.get(ReportBean.class,1);
//			 System.out.println(select.getMembers());
//
//			 ReportBean select2 = (ReportBean) session.get(ReportBean.class,1);
//			 select2.setReportContent("aaa");

			// ReportBean select = (ReportBean) session.get(ReportBean.class,17);
			// session.delete(select);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}

	}

}
