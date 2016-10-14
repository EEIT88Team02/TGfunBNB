package model.MemberOrder;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.misc.HibernateUtil;

public class MemberOrderDAO implements MemberOrderInterface {

	public static void main(String[] args) throws ParseException {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();			
			
//			MemberOrderDAO dao = new MemberOrderDAO(session);
//			List<MemberOrderBean> result=dao.selectByHaveDelete(1,false);
//			System.out.println(result);
			
			
//			MemberOrderDAO dao = new MemberOrderDAO(session);
//			List<MemberOrderBean> result=dao.selecTByMemberDate(1,"2016-10-13");			
//			for(MemberOrderBean bean:result){
//				System.out.println(bean);
//			}
			
			MemberOrderDAO dao = new MemberOrderDAO(session);
			MemberOrderBean memberOrderBean=new MemberOrderBean();
			memberOrderBean.setMemberID(1);
			memberOrderBean.setMemberDate(new Date());
			memberOrderBean.setMemberSum(150000);
			dao.insert(memberOrderBean);
			
			

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		}
		finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	private Session session = null;

	public MemberOrderDAO(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberOrderBean> selectByHaveDelete(int memberID, boolean haveDelete) {
		String select_by_haveDelete = 
							"FROM MemberOrderBean WHERE memberID =:memberID AND haveDelete = :haveDelete ORDER BY memberDate DESC";
		List<MemberOrderBean> result =this.getSession()
										.createQuery(select_by_haveDelete)
										.setInteger("memberID",memberID)
										.setBoolean("haveDelete",haveDelete)
										.list();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberOrderBean> selecTByMemberDate(int memberID,String memberDate) {
		String select_by_memberDate = 
			"from MemberOrderBean m where m.memberID = :memberID AND m.memberDate between convert(datetime,(:memberDate +' 00:00:00')) AND CONVERT(datetime,(:memberDate+' 23:59:59.997'))";
		List<MemberOrderBean> result =this.getSession()
									.createQuery(select_by_memberDate)
									.setInteger("memberID",memberID)
									.setString("memberDate",memberDate)
									.list();	
		return result;
	}

	@Override
	public MemberOrderBean insert(MemberOrderBean memberOrderBean) {
		MemberOrderBean bean=(MemberOrderBean) this.getSession().save(memberOrderBean);
		return bean;
	}

	@Override
	public MemberOrderBean update(int orderID, int memberID, Date memberDate, 
									double roomTotalSum,double memberSum, boolean haveDelete) {
		MemberOrderBean bean=this.getSession().get(MemberOrderBean.class,orderID);
		bean.setMemberID(memberID);
		bean.setMemberDate(memberDate);
		bean.setRoomTotalSum(roomTotalSum);
		bean.setMemberSum(memberSum);
		bean.setHaveDelete(haveDelete);
		return bean;
	}

}
