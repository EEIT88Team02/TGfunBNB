package Controller.OrderRoomFunction;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.MemberOrder.MemberOrderBean;
import model.MemberOrder.MemberOrderService;
import model.OrderRoomInfo.OrderRoomInfoBean;

@WebServlet(
		urlPatterns={"/Order/OrderRoom.Controller"}
)
public class OrderRoomServlet extends HttpServlet {
	
	
	private MemberOrderService memberOrderService;
	private MemberOrderBean memberOrderBean;
	private OrderRoomInfoBean orderRoomInfoBean;
	
	@Override
	public void init() throws ServletException {
		
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		memberOrderService=(MemberOrderService) context.getBean("memberOrderService");
		memberOrderBean=(MemberOrderBean)context.getBean("memberOrderBean");
		orderRoomInfoBean=(OrderRoomInfoBean)context.getBean("orderRoomInfoBean");
	}

	private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// 接收資料
		String memberID_str = request.getParameter("memberID");
		String inDate_str = request.getParameter("inDate");
		String outDate_str = request.getParameter("outDate");
		String roomCode_str = request.getParameter("roomCode");
		String order_button = request.getParameter("submit");
			
		//轉換資料
		Map<String,String> errors=new HashMap<String,String>();//存放錯誤訊息
		request.setAttribute("error",errors);//設定錯誤訊息的被el取值名稱
		int memberID=0;
		memberID = Integer.parseInt(memberID_str);
		int roomCode=0;
		roomCode = Integer.parseInt(roomCode_str);
		Date inDate = null;
		Date outDate = null;
		try {
			inDate=dateFormat.parse(inDate_str+" 16:00:00");
		}
		catch (ParseException e) {
			errors.put("inDate","日期轉型錯誤");
			e.printStackTrace();
			request.getRequestDispatcher("/Order/OrderRoom.jsp").forward(request,response);			
		}
		try {
			outDate=dateFormat.parse(outDate_str+" 10:00:00");
		}
		catch (ParseException e) {
			errors.put("outDate","日期轉型錯誤");
			e.printStackTrace();
			request.getRequestDispatcher("/Order/OrderRoom.jsp").forward(request,response);
		}						
		//驗證資料	
			
			
			
			
		//呼叫Model, 根據Model執行結果顯示View
		
		//設insert進資料庫的MemberOrderBean
		memberOrderBean.setMemberID(memberID);
		System.out.println("-----------------------------------------");
		System.out.println(memberOrderBean);
		System.out.println("-----------------------------------------");
		
		orderRoomInfoBean.setRoomCode(roomCode);
		orderRoomInfoBean.setInDate(inDate);
		orderRoomInfoBean.setOutDate(outDate);
		System.out.println("-----------------------------------------");
		System.out.println(orderRoomInfoBean);
		System.out.println("-----------------------------------------");
				
		if("訂房".equals(order_button)) {
			
			boolean result=memberOrderService.order(memberOrderBean,orderRoomInfoBean);
			System.out.println(result);			

			if(result==true) {
				System.out.println("-----------------------------------------");
				System.out.println("訂房成功");
				System.out.println("-----------------------------------------");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}else {
				errors.put("insertResult", "訂房失敗");
				System.out.println("-----------------------------------------");
				System.out.println("2");
				System.out.println("-----------------------------------------");
				request.getRequestDispatcher("/Order/OrderRoom.jsp").forward(request, response);
			}
			
		} 
			
			
			
				

	}

}
