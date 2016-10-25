package model.Message;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class MessageService {
	private MessageDAO messageDAO ;
	
	ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
	 SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
	 public MessageService() {
		 messageDAO=new MessageDAO(sessionFactory);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			// Session session=HibernateUtil.getSessionFactory().getCurrentSession();
           // MessageService dao=new  MessageService(session);
             //System.out.println(dao.Show_ALL_Messages()); 
			// HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally
		 {
			// TODO Auto-generated catch block
			//HibernateUtil.closeSessionFactory();
		}
	}

public  List<MessageBean>  Show_ALL_Messages()	
{
		return messageDAO.selectAll();
}
	
public List<MessageBean> Articles_Message_History_search(int artCode )
{

	return messageDAO.selectByartCode(artCode);
	
}
	
public boolean Message_insert(Object[] bean){
	MessageBean x=new MessageBean();
    x.setMemberID(Integer.valueOf(String.valueOf(bean[0])));
	x.setArtCode(Integer.valueOf(String.valueOf(bean[1])));
	x.setMsgContent(String.valueOf(bean[2]));
    x.setMsgDate((Date)bean[3]);
    x.setGood(Integer.valueOf(String.valueOf(bean[4])));
    x.setBad(Integer.valueOf(String.valueOf(bean[5])));
    return 	messageDAO.insert(x);
}
/*必須傳入完整bean(含PK鍵)*/
public boolean Message_update(Object[] bean){
	MessageBean x=new MessageBean();
	x.setMsgCode(Integer.valueOf(String.valueOf(bean[0])));
	x.setMemberID(Integer.valueOf(String.valueOf(bean[1])));
	x.setArtCode(Integer.valueOf(String.valueOf(bean[2])));
	x.setMsgContent(String.valueOf(bean[3]));
    x.setMsgDate((Date)bean[4]);
    x.setGood(Integer.valueOf(String.valueOf(bean[5])));
    x.setBad(Integer.valueOf(String.valueOf(bean[6])));
	return 	messageDAO.update(x);
}


	
}
