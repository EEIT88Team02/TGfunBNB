package model.Message;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import model.misc.HibernateUtil;
import tgfunBean.MemberBean;

public class MessageDAO implements MessageInterface  {
     private Session session=null;
	
	
	public MessageDAO(Session session) {
		this.session = session;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      try {
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		 Session session=HibernateUtil.getSessionFactory().getCurrentSession();	 
		 MessageDAO dao=new MessageDAO(session);
//		 System.out.println(dao.selectAll()); 
	//	 System.out.println(dao.selectByartCode(1)); 
		 
//			MessageBean insert2 = new MessageBean();
//		 insert2.setMemberID(1);
//		 insert2.setArtCode(1);
//		 insert2.setMsgContent("");
//		 insert2.setMsgDate(new Date());
//		 insert2.setGood(5);
//		 insert2.setBad(1);
//		 dao.insert(insert2); 
//			MessageBean insert3 = new MessageBean();
//			 insert3.setMsgCode(2); 
//			insert3.setArtCode(1); 
//			insert3.setMemberID(1);
//			 insert3.setArtCode(1);
//			 insert3.setMsgContent("");
//			 insert3.setMsgDate(new Date());
//			 insert3.setGood(5);
//			 insert3.setBad(1);
//			 dao.update(insert3);
			 HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	}finally
	 {
		// TODO Auto-generated catch block
		HibernateUtil.closeSessionFactory();	
	  }	 
	  
	}

	
	@Override
	public List<MessageBean> selectAll() {
		// TODO Auto-generated method stub
		Query query=session.createQuery("from MessageBean");
		return (List<MessageBean>)query.list();
	}

	@Override
	public boolean insert(MessageBean messageBean) {
		// TODO Auto-generated method stub
		session.save(messageBean);
		return true;
	}

	@Override
	public MessageBean update(MessageBean bean) {
		// TODO Auto-generated method stub
		session.saveOrUpdate(bean);
		return bean;
	}

	@Override
	public List<MessageBean> selectByartCode(Integer artCode ) {
		// TODO Auto-generated method stub
		Query query=session.createQuery("from MessageBean where artCode=?");
		 query.setInteger(0,artCode) ;
		return (List<MessageBean>)query.list();
	}

}
