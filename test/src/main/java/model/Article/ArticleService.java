package model.Article;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Message.MessageBean;
import model.Message.MessageDAO;


public class ArticleService {
 private ArticleDAO articleDAO; 
 	
 ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
 SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
 public ArticleService( ) {
	 
	 articleDAO=new  ArticleDAO(sessionFactory);
}


public static void main(String[] args) {
		// TODO Auto-generated method stub
     try {
	//	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	//	 Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		// ArticleService dao=new ArticleService(session); 
		 
		// List<Object[]> m=dao.Show_ALL_articles();
	/*List<Object[]> 處理*/
//		 if(m!=null)
//		 {
//		 for(int u=0;u<m.size();u++)	
//		 {	
//		 	Object[] x=m.get(u);
//		 	int i=0; 
//		 	for(Object vv :x)	 
//		 	 {
//		 		 if(i==0)
//		 		 {
//		 			 ArticleBean df=(ArticleBean)vv;	
//		 			  System.out.println(df);
//		 		 }else{
//		 		  MessageBean dj= ( MessageBean)vv;
//		 	   System.out.println(dj);
//		 		 }
//		 	   ++i;
//		 	  }
//		 }	
//	 }
		
//	List<Object> c=dao.DateSort(true);		 
	
	 	 
		 
		 
	//	 HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	}finally
	 {
		// TODO Auto-generated catch block
	//	HibernateUtil.closeSessionFactory();
	}
	  
       
	}

public   List<Object[]>   Show_ALL_articles()
{
	List<ArticleBean> test1= articleDAO.selectAll();
	if(!test1.isEmpty()){
	List<Object[]> c=new ArrayList<>();
	
	MessageDAO messageDAO=new  MessageDAO(sessionFactory) ;	
	
	for(ArticleBean mm:test1)
	{
       int x=mm.getArtCode();
      List <MessageBean> y= messageDAO.selectByartCode(x);
       int len=y.size();  
      Object[] cc=new Object[len+1];
      cc[0]=mm;
      for(int i=0 ,j=1;i<len;++j,++i)
      {
    	  MessageBean yy=y.get(i);  
    	 cc[j]=yy;
      }
      c.add(cc);  
	} 
	 return c;
	}
	return null; 
}

public  <T>List<T> selectByartCode(Integer artcode){
return (List<T>)articleDAO.selectByartCode(artcode) ;
}

public  <T> List<T> accordance_with_gap_date(Date date1,Date date2){
	List<T> m=new ArrayList<>();
	List<ArticleBean> u=articleDAO.selectByartDate(date1, date2);
	
	
	return (List<T>) articleDAO.selectByartDate(date1, date2);
}

public <T> List<T> DateSort(boolean BOOL)
{
	return (List<T>) articleDAO.selectBySortDate(BOOL);
}

public <T> List<T> SearchByTopic(String ArtTopic)
{
	return  (List<T>) articleDAO.selectByArtTopic(ArtTopic);
}

public boolean   whetherHaveAppeal(String name)
{
	return articleDAO.selectByHaveAppeal(name);
}

public boolean whetherhaveprocess(String name)
{
	 return articleDAO.selectByhaveprocess(name);
}

public boolean Articleinsert(Object[] bean)
{
	ArticleBean x=new ArticleBean();
		
	x.setMemberID((int)bean[0]);
	  x.setReportCount(Integer.valueOf(String.valueOf(bean[1])));
	  x.setArtTopic(String.valueOf(bean[2]));
	  x.setArtContent(String.valueOf(bean[3]));
	  x.setArtDate((Date)bean[4]);
	  x.setAppealDate((Date)bean[5]);
	  x.setHaveAppeal((boolean)bean[6]);
	  x.setAppealContent(String.valueOf(bean[7]));
	  x.setHaveProcess((boolean)bean[8]);
	  x.setProcessDate((Date)bean[9]);
	  x.setReportReply(String.valueOf(bean[10]));
	  x.setHaveDelete((boolean)bean[11]);
	return  articleDAO.insert(x);
}

public  boolean  Articleupdate(Object[] bean)
{
	ArticleBean y=new ArticleBean();
    y.setArtCode((int)bean[0]);  
 	y.setMemberID((int)bean[1]);
 	y.setReportCount((int)bean[2]);
	  y.setArtTopic((String)bean[3]);
	  y.setArtContent((String)bean[4]);
	  y.setArtDate((Date)bean[5]);
	  y.setAppealDate((Date)bean[6]);
	  y.setHaveAppeal((boolean)bean[7]);
	  y.setAppealContent((String)bean[8]);
	  y.setHaveProcess((boolean)bean[9]);
	  y.setProcessDate((Date)bean[10]);
	  y.setReportReply((String)bean[11]);
	  y.setHaveDelete((boolean)bean[12]);
	articleDAO.update(y);
    return true;
}

}
