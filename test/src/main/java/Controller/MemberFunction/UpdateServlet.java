package Controller.MemberFunction;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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

@WebServlet(urlPatterns = { "/Login/Update.controller" })
public class UpdateServlet extends HttpServlet {

	private MemberService service;
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void init() throws ServletException {
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		service=(MemberService) context.getBean("memberService");
	;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UpdateServlet");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("STEP0");
		// 接收資料
		HttpSession session = request.getSession();
		System.out.println("STEP1=" + session);

		// 驗證資料
		if (session == null) {// 沒有Session重新登入
			response.sendRedirect(response.encodeRedirectURL("/Login.jsp"));
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("user");// 重session拿到memberID
		System.out.println("mb=" + mb);
		int memberID = mb.getMemberID();
		System.out.println("STEP2=" + memberID);


		// 接收資料
		System.out.println("STEP3-1-1");

		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String ID = request.getParameter("ID");
		String Email = request.getParameter("Email");
		String Celphone = request.getParameter("Celphone");
		String Telephone = request.getParameter("Telephone");
		String Address = request.getParameter("Address");
		String temp2 = request.getParameter("Birthday");
		String Sex = request.getParameter("Sex");
		String prodaction = request.getParameter("prodaction");

		// 轉換資料

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error",errors);

		java.util.Date Birthday = null;
		if (temp2 != null && temp2.trim().length() != 0) {
			try {
				Birthday = sFormat.parse(temp2);
				System.out.println(Birthday);

			}
			catch (ParseException e) {
				errors.put("Birthday","日期格式:yyyy-MM-dd");
				e.printStackTrace();
			}
		}

		// 驗證資料

		if (account == null || account.length() == 0) {
			errors.put("account","請輸入帳號");
		}
		if (name == null || name.length() == 0) {
			errors.put("name","請輸入姓名");
		}

		if (ID == null || ID.length() == 0) {
			errors.put("ID","請輸入身分證字號");
		}
		if (Email == null || Email.length() == 0) {
			errors.put("Email","請輸入信箱");
		}
		if (Celphone == null || Celphone.length() == 0) {
			errors.put("Celphone","請輸入手機");
		}
		if (Telephone == null || Telephone.length() == 0) {
			errors.put("Telephone","請輸入電話");
		}
		if (Address == null || Address.length() == 0) {
			errors.put("Address","請輸入地址");
		}

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/Insert.jsp").forward(request,response);

			return;
		}
		// 呼叫model根據model執行結果，導向view

		MemberBean bean1 = new MemberBean();
		bean1.setAccount(account);
		bean1.setMemberID(memberID);

		bean1.setName(name);
		bean1.setID(ID);
		bean1.setEmail(Email);
		bean1.setCelphone(Celphone);
		bean1.setTelephone(Telephone);
		bean1.setAddress(Address);
		bean1.setBirthday(Birthday);
		bean1.setSex(Sex);

		System.out.println("bean1=" + bean1);

		// -------------------------------------------------------

		// 轉畫面
		if ("Update".equals(prodaction)) {

			System.out.println("bean2=" + bean1);
			MemberBean result = service.update(bean1);

			System.out.println("STEP3-0=" + bean1);
			System.out.println("STEP3-1=" + result);
			if (result == null) {
				System.out.println("STEP9=" + result);
				request.getRequestDispatcher("/Login.jsp").forward(request,response);

			}
			else {
				System.out.println("STEP10=" + bean1);
				request.setAttribute("user",bean1);
				request.getRequestDispatcher("/Login/Update.jsp").forward(request,response);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request,response);
	}

}
