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

@WebServlet(urlPatterns = { "/Insert.controller" })

public class InsertServlet extends HttpServlet {

	private MemberService service;
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public void init() throws ServletException {
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	
		service=(MemberService) context.getBean("memberService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InsertServlet");
		
		HttpSession session = request.getSession();
		String c = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String parm = (String) request.getParameter("kaptchafield");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 接收資料
		System.out.println("STEP1");
		String account = request.getParameter("account");
		String temp1 = request.getParameter("pwd");
		String name = request.getParameter("name");
		String ID = request.getParameter("ID");
		String Email = request.getParameter("Email");
		String Celphone = request.getParameter("Celphone");
		String Telephone = request.getParameter("Telephone");
		String Address = request.getParameter("Address");
		String temp2 = request.getParameter("Birthday");
		String Sex = request.getParameter("Sex");
		String temp3 = request.getParameter("photo");
		String prodaction = request.getParameter("prodaction");
		System.out.println(temp3);
		System.out.println("STEP2");
		// 轉換資料
		
		
		

		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error",errors);
		System.out.println("STEP3");
		byte[] pwd = null;
		if (temp1 != null && temp1.trim().length() != 0) {
			pwd = temp1.getBytes();
		}

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

		System.out.println("STEP4");
		// 驗證資料
	
		if (c != null && parm != null) {
			if (c.equals(parm)) {
			}
			else {
				errors.put("parm","請輸入驗證碼");
			}
		}
		if (account == null || account.length() == 0) {
			errors.put("account","請輸入帳號");
		}
		if (name == null || name.length() == 0) {
			errors.put("name","請輸入姓名");
		}
		if (pwd == null || pwd.length == 0) {
			errors.put("pwd","請輸入密碼");
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
			try {
				request.getRequestDispatcher("/Insert.jsp").forward(request,response);
				return;
			}
			catch (Exception e) {
			
			}
			return;
		}
		
		// 呼叫model根據model執行結果，導向view
		System.out.println("STEP5");
		MemberBean bean = new MemberBean();
		bean.setAccount(account);
		bean.setPwd(pwd);
		bean.setName(name);
		bean.setID(ID);
		bean.setEmail(Email);
		bean.setCelphone(Celphone);
		bean.setTelephone(Telephone);
		bean.setAddress(Address);
		bean.setBirthday(Birthday);
		bean.setSex(Sex);
	
		System.out.println("STEP6");

		System.out.println("bean=" + bean);

		if ("Insert".equals(prodaction)) {
			System.out.println("STEP7");
			MemberBean result = service.insert(bean);
			System.out.println("STEP8");
			if (result == null) {

				request.getRequestDispatcher("/Insert.jsp").forward(request,response);
				errors.put("action","新增資料失敗");
			}
			else {
				System.out.println("STEP9");
				request.setAttribute("insert",result);
			}
			request.getRequestDispatcher("/Login.jsp").forward(request,response);
			MemberBean email = service.email(Email);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request,response);
	}

}
