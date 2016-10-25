package model.Report;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class ReportDAO implements ReportInterface {
	private SessionFactory sessionfactory=null;
	
	
	public ReportDAO(SessionFactory session) {
	this.sessionfactory = session;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
		//	HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		//	Session session=HibernateUtil.getSessionFactory().getCurrentSession(); 
//			ReportDAO dao=new ReportDAO(session); 
//			System.out.println(dao.selectAll());
//			       ReportBean insert1 = new ReportBean();
//						 insert1.setArtCode(1);;
//						 insert1.setReportMemberID(1);
//						 insert1.setReportContent("142");
//						 insert1.setReportDate(new Date());
//			    dao.insert(insert1);
			
		//	HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}finally
		{
			// TODO Auto-generated catch block
		//	HibernateUtil.closeSessionFactory();
		}
		
	}

	@Override
	public List<ReportBean> selectAll() {
		// TODO Auto-generated method stub
		Query query=sessionfactory.openSession().createQuery("from ReportBean");
		return (List<ReportBean>)query.list(); 
		
	}

	@Override
	public boolean insert(ReportBean reportBean) {
		// TODO Auto-generated method stub
		sessionfactory.openSession().save(reportBean);
		return true;
	}

}
