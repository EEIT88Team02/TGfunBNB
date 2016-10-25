package model.MemberOrder;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import model.OrderRoomInfo.OrderRoomInfoBean;
import model.OrderRoomInfo.OrderRoomInfoService;
import model.RoomInfo.RoomInfoBean;
import model.RoomInfo.RoomInfoService;

@Component
public class MemberOrderService {
	@Autowired
	private MemberOrderDAO memberOrderDAO;
	private RoomInfoService roomInfoService;
	private OrderRoomInfoService orderRoomInfoService;
	public MemberOrderService(MemberOrderDAO memberOrderDAO,RoomInfoService roomInfoService,OrderRoomInfoService orderRoomInfoService) {
		this.memberOrderDAO = memberOrderDAO;
		this.roomInfoService =roomInfoService;
		this.orderRoomInfoService=orderRoomInfoService;
	}

	public List<MemberOrderBean> selectByMemberID(int memberID,boolean haveDelete) {
		return memberOrderDAO.selectByMemberID(memberID,haveDelete);
	}
	
	public List<MemberOrderBean> selecTByDateRange(int memberID,Date firstDate,Date lastDate) {
		return memberOrderDAO.selecTByDateRange(memberID,firstDate,lastDate);
	}
	
	public int insert(MemberOrderBean memberOrderBean) {
		int result=memberOrderDAO.insert(memberOrderBean);
		if(result!=0)
			return result;
		else
			return 0;
	}
	
	public boolean update(MemberOrderBean memberOrderBean) {
		return memberOrderDAO.update(memberOrderBean);
	}
	
	public boolean order(MemberOrderBean memberOrderBean ,OrderRoomInfoBean orderRoomInfoBean) {
		try {

			//把MemberOrderBean新增進資料庫後取得orderID
			memberOrderBean.setMemberDate(new Date());
			memberOrderBean.setHaveDelete(false);
			int orderID= this.insert(memberOrderBean);
			System.out.println("-----------------------------------------");
			System.out.println(orderID);
			System.out.println("-----------------------------------------");
			int roomCode=orderRoomInfoBean.getRoomCode();

			//利用roomCode取得roomPrice
			RoomInfoBean roomInfoBean= roomInfoService.selectByRoomCode(roomCode);
			double roomPrice = roomInfoBean.getRoomPrice();
			System.out.println("-----------------------------------------");
			System.out.println(roomPrice);
			System.out.println("-----------------------------------------");
	
			//計算住了多少時間			
			 long inDate_long=orderRoomInfoBean.getInDate().getTime();
			 long outDate_long=orderRoomInfoBean.getOutDate().getTime();
			 int result_date=(int) (((outDate_long-inDate_long)/(3600*24*1000))+1);
			 System.out.println("-----------------------------------------");
			 System.out.println(result_date);
			 System.out.println("-----------------------------------------");
			 //在利用上面的roomPrice來算roomSum
			 double roomSum=roomPrice*result_date;
			 System.out.println("-----------------------------------------");
			 System.out.println(roomSum);
			 System.out.println("-----------------------------------------");
			
			//利用setRoomSum()把roomSum設進orderRoomInfoBean,接著直接把他新增進資料庫
			orderRoomInfoBean.setRoomSum(roomSum);
			orderRoomInfoBean.setOrderID(orderID);
			boolean orderRoomInfo_result=orderRoomInfoService.insert(orderRoomInfoBean);
			System.out.println("-----------------------------------------");
			System.out.println(orderRoomInfo_result);
			System.out.println("-----------------------------------------");
			
			//利用orderID搜尋roomSum後加總起來再塞給memberOrder
			List<OrderRoomInfoBean> orderRoomInfoBeans=orderRoomInfoService.selectByOrderID(orderID);
			double roomTotalSum=0;
			for(OrderRoomInfoBean infoBean:orderRoomInfoBeans){
				double roomsum=infoBean.getRoomSum();
				roomTotalSum=roomTotalSum+roomsum;
			}
			System.out.println("-----------------------------------------");
			System.out.println(roomTotalSum);
			System.out.println("-----------------------------------------");
			
			//更新memberOrder	
			memberOrderBean.setRoomTotalSum(roomTotalSum);
			memberOrderBean.setMemberSum(roomTotalSum);

			boolean memberOrder_update_result=this.update(memberOrderBean);
			System.out.println("-----------------------------------------");
			System.out.println(memberOrder_update_result);
			System.out.println("-----------------------------------------");
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;		
	}

	public static void main(String[] args) throws ParseException {
//		try {
//			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
//			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//			 MemberOrderService service= new MemberOrderService();
//			 List<MemberOrderBean> result=service.selectByMemberID(2,false);
//			 for(MemberOrderBean bean:result){
//			 System.out.println(bean);
//			 }
//
//			MemberOrderService service = new MemberOrderService();
//			List<MemberOrderBean> result=service.selecTByDateRange(1,new Date(),new Date());
//			for(MemberOrderBean bean:result){
//			System.out.println(bean);
//			}
//
//			MemberOrderService service = new MemberOrderService();
//			MemberOrderBean memberOrderBean=new MemberOrderBean();
//			memberOrderBean.setMemberID(1);
//			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//			Date date=format.parse("2016-10-20 23:59:59.997");
//			memberOrderBean.setMemberDate(date);
//			memberOrderBean.setMemberSum(150000);
//			int PK=service.insert(memberOrderBean);
//			System.out.println(PK);
//
//			MemberOrderService service = new MemberOrderService();
//			MemberOrderBean memberOrderBean=new MemberOrderBean();
//			memberOrderBean.setOrderID(2);
//			memberOrderBean.setMemberID(2);
//			memberOrderBean.setRoomTotalSum(10000);
//			memberOrderBean.setMemberSum(10000);
//			memberOrderBean.setMemberDate(new Date());
//			memberOrderBean.setHaveDelete(false);
//			service.update(memberOrderBean);		
//			
//			
//			
//			MemberOrderService memberOrderService = new MemberOrderService();
//			
//			MemberOrderBean memberOrderBean=new MemberOrderBean();
//			memberOrderBean.setMemberID(1);
//			
//			OrderRoomInfoBean orderRoomInfoBean=new OrderRoomInfoBean();
//			orderRoomInfoBean.setRoomCode(101);
//			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			 
//			orderRoomInfoBean.setInDate(dateFormat.parse("2016-10-14"+" 16:00:00"));
//			orderRoomInfoBean.setOutDate(dateFormat.parse("2016-10-16"+" 10:00:00"));
//			
//			boolean result=memberOrderService.order(memberOrderBean,orderRoomInfoBean);
//			System.out.println(result);
//	
//			
//			
//			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			 long inDate_long=format.parse("2016-10-14"+" 16:00:00").getTime();
//			 long outDate_long=format.parse("2016-10-16"+" 10:00:00").getTime();
//			 int result=(int) (((outDate_long-inDate_long)/(3600*24*1000))+1);
//			 System.out.println(result);
//			 
//			 double roomPrice=3800;
//			 double total=roomPrice*result;
//			 System.out.println(total);
//			
//			OrderRoomInfoService orderRoomInfoService=new OrderRoomInfoService();
//			List<OrderRoomInfoBean> orderRoomInfoBeans=orderRoomInfoService.selectByOrderID(2);
//			double roomTotalSum=0;
//			for(OrderRoomInfoBean infoBean:orderRoomInfoBeans){
//				double roomsum=infoBean.getRoomSum();
//				roomTotalSum=roomTotalSum+roomsum;
//			}
//			System.out.println(roomTotalSum);
//			 
//			 
//
//			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
//		}
//		finally {
//			HibernateUtil.closeSessionFactory();
//		}
		
		ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("beans.cfg.xml");
		MemberOrderService service=(MemberOrderService)context.getBean("memberOrderService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");		
		
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			Session session = sessionFactory.getCurrentSession();
			
			
			 List<MemberOrderBean> result=service.selectByMemberID(2,false);
			 for(MemberOrderBean bean:result){
			 System.out.println(bean);
			 }


			sessionFactory.getCurrentSession().getTransaction().commit();
		} finally {
			((ConfigurableApplicationContext) context).close();
		}
		
		
	}

}
