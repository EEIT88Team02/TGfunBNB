package model.Report;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class ReportService {
	private  ReportDAO  reportDAO;
	
	ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
	 SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");	  	 
	 public ReportService() {
		 reportDAO=new ReportDAO(sessionFactory); 
		 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		//	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			 Session session=HibernateUtil.getSessionFactory().getCurrentSession();
//			 ReportService dao=new ReportService(session);
			 
			 
			// HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}finally
		{
			// TODO Auto-generated catch block
		//	HibernateUtil.closeSessionFactory();
		}		
	}

public <T> List<T>  Show_ALL_Report()	
{
	return (List<T>)reportDAO.selectAll();
}
	
     public boolean insert(Object[] bean)
     {
	   ReportBean x=new ReportBean();
    	 x.setArtCode(Integer.valueOf(String.valueOf(bean[0])));
    	 x.setReportMemberID(Integer.valueOf(String.valueOf(bean[1])));
    	 x.setReportContent(String.valueOf(bean[2]));
    	 x.setReportDate((Date)bean[3]);
     return  reportDAO.insert(x);	 
    }
	
	
	
}
