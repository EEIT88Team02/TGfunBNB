package Controller.MemberFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.Member.MemberBean;
import model.Member.MemberService;
import model.MemberOrder.MemberOrderBean;
import model.MemberOrder.MemberOrderService;

@WebServlet(urlPatterns = { "/OrderList.controller" })
public class OrderListServlet extends HttpServlet {

	private MemberService service;
	private MemberOrderService OrderService;

	public void init() throws ServletException {
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		service=(MemberService) context.getBean("memberService");
	;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderListServlet");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		
		String prodaction = request.getParameter("prodaction");
		if (session == null) {// 沒有Session重新登入
			response.sendRedirect(response.encodeRedirectURL("/Login/Login.jsp"));
			return;
		}
		MemberBean memberBean= new MemberBean();
		memberBean.setMemberID(1);
		session.setAttribute("user",memberBean);

		MemberBean mb = (MemberBean) session.getAttribute("user");// 重session拿到memberID
		System.out.println("mb=" + mb);
		int memberID = mb.getMemberID();
		System.out.println("STEP2=" + memberID);
		
		Set<MemberOrderBean> result=service.select(memberID);
//		Map<String,Object> aa=new HashMap<String,Object>();
//		aa.put("MemberOrderBean",result);
		
//        List<String> m=new ArrayList<>();
//		
//		Iterator iter=result.iterator();
//		  while(iter.hasNext()) 
//		  {
//			  MemberOrderBean x=(MemberOrderBean)iter.next(); 	  
//		      System.out.println(x.getMemberID());
//		  }
//		
		
		if("List".equals(prodaction)){
			System.out.println("STEP3");
			if(result==null){
				System.out.println("STEP4");
				request.getRequestDispatcher("/Login/OrderList.jsp").forward(request,response);
				
			}else{
				java.util.List<MemberOrderBean>	list=new ArrayList<MemberOrderBean>();
				for(MemberOrderBean memberOrderBean:result){
				list.add(memberOrderBean);				
				}				
				request.setAttribute("list",list);
				System.out.println("STEP5"+list);
				request.getRequestDispatcher("/Login/OrderList.jsp").forward(request,response);
			}
			
		}
		
		

		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request,response);
	}

}
