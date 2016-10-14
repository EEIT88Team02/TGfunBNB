package model.Report;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import model.misc.HibernateUtil;

public class ReportDAO implements ReportInterface {
  private Session session=null;
	
	
	public ReportDAO(Session session) {
	this.session = session;
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session=HibernateUtil.getSessionFactory().getCurrentSession(); 
			ReportDAO dao=new ReportDAO(session); 
			System.out.println(dao.selectAll());
//			       ReportBean insert1 = new ReportBean();
//						 insert1.setArtCode(1);;
//						 insert1.setReportMemberID(1);
//						 insert1.setReportContent("142");
//						 insert1.setReportDate(new Date());
//			    dao.insert(insert1);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}finally
		{
			// TODO Auto-generated catch block
			HibernateUtil.closeSessionFactory();
		}
		
	}

	@Override
	public List<ReportBean> selectAll() {
		// TODO Auto-generated method stub
		Query query=session.createQuery("from ReportBean");
		return (List<ReportBean>)query.list(); 
		
	}

	@Override
	public boolean insert(ReportBean reportBean) {
		// TODO Auto-generated method stub
		session.save(reportBean);
		return true;
	}

}
