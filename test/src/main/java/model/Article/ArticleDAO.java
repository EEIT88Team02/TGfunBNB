package model.Article;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import model.Message.MessageDAO;
import model.misc.HibernateUtil;
import tgfunBean.MemberBean;

public class ArticleDAO implements ArticleInterface{

	private Session session=null;
	
	public ArticleDAO(Session session) {
		this.session = session;
	}

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session=HibernateUtil.getSessionFactory().getCurrentSession();
			ArticleDAO dao=new ArticleDAO(session);
			 // System.out.println(dao.selectAll());
//			  List<ArticleBean> s=dao.selectAll();
//			 for(ArticleBean ss:s)
//			 {
//			  int  x=ss.getArtCode();
//			  MessageDAO dao1=new MessageDAO(session);	 
//			     System.out.println(dao1.selectByartCode(x)); 
//			 }
			  //			System.out.println(dao.selectByHaveAppeal("王荐進"));		  
			  //			System.out.println(dao.selectByhaveprocess("王荐進"));
//						Date dates=new Date();
//			            Date date4=new Date(); 
//						DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String e="2016-10-14"+" 00:00:00";
//			String ee="2016-10-15"+" 00:00:00";
//			dates=sdf.parse(e);
//			date4=sdf.parse(ee);
//	
//			System.out.println(dao.selectByartDate(dates,date4).size());
			//System.out.println(s.selectByaccount("aa"));
//			ArticleBean insert1 = new ArticleBean();
//		     insert1.setMemberID(1); 
//		    insert1.setReportCount(1);
//		    insert1.setArtContent("aa");
//		    insert1.setArtTopic("bb");       
//		    insert1.setArtDate(new java.util.Date());
//		    insert1.setAppealDate(new java.util.Date());
//		     insert1.setHaveAppeal(true);
//		     insert1.setAppealContent("f");
//		     insert1.setHaveProcess(false);
//		     insert1.setProcessDate(new java.util.Date());
//		     insert1.setReportReply("h");
//		     insert1.sethaveDelete(false);
//		     System.out.println(dao.insert(insert1));
//		     ArticleBean insert2 = new ArticleBean();
//		     insert2.setArtCode(1);
//		     insert2.setMemberID(1); 
//		    insert2.setReportCount(1);
//		    insert2.setArtContent("aa");
//		    insert2.setArtTopic("bb");       
//		    insert2.setArtDate(new java.util.Date());
//		    insert2.setAppealDate(new java.util.Date());
//		     insert2.setHaveAppeal(true);
//		     insert2.setAppealContent("f");
//		     insert2.setHaveProcess(false);
//		     insert2.setProcessDate(new java.util.Date());
//		     insert2.setReportReply("h");
//		     insert2.sethaveDelete(true);
//		     
//		     System.out.println(dao.update(insert2));			 
		    
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}finally
		 {
			// TODO Auto-generated catch block
			HibernateUtil.closeSessionFactory();
		}	
	}

	@Override
	public List<ArticleBean> selectAll() {
		// TODO Auto-generated method stub
		Query query=session.createQuery("from ArticleBean");
	   return 	(List<ArticleBean>)query.list();	
	}

	@Override
	public List<ArticleBean> selectByartDate(Date date,Date date1) {
		// TODO Auto-generated method stub
		Query query=session.createQuery("from ArticleBean c where c.ArtDate BETWEEN :stDate AND :edDate ");
		query.setParameter("stDate",date);
		query.setParameter("edDate",date1);
		return (List<ArticleBean>)query.list();
		
	}
	@Override
	public List<ArticleBean> selectByArtTopic(String ArtTopic) {
		// TODO Auto-generated method stub
		Query query=session.createQuery("from ArticleBean where ArtTopic=?");
		query.setString(0,ArtTopic);
		return (List<ArticleBean>)query.list();
		
	}

	@Override
	public List<ArticleBean> selectByaccount(String name) {
		// TODO Auto-generated method stub
		Query query=session.createQuery("from ArticleBean where memberID in(select memberID from MemberBean where Name=?)");
		query.setString(0,name);
		return (List<ArticleBean>)query.list();
		
	}

	@Override
	public boolean insert(ArticleBean articleBean) {
		// TODO Auto-generated method stub
		session.saveOrUpdate(articleBean);
		return true;		
	}

	@Override
	public boolean selectByHaveAppeal(String name) {
		// TODO Auto-generated method stub
		
		Query query=session.createQuery("from ArticleBean where memberID in(select memberID from MemberBean where Name=?) and HaveAppeal=1");
		query.setString(0,name);
		List<ArticleBean> st=(List<ArticleBean>)query.list();
		if(st.isEmpty())
		  return false; 
		return true;
	}

	@Override
	public boolean selectByhaveprocess(String name) {
		// TODO Auto-generated method stub
		Query query=session.createQuery("from ArticleBean where memberID in(select memberID from MemberBean where Name=?) and haveprocess=1");
		query.setString(0,name);
		List<ArticleBean> st=(List<ArticleBean>)query.list();
		if(st.isEmpty())
		  return false; 
		return true;
	}

	@Override
	public ArticleBean  update(ArticleBean bean) {
		// TODO Auto-generated method stub
		session.saveOrUpdate(bean);	
		return bean;
	}

	

}
