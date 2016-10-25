package Controller.MemberFunction;

import java.io.IOException;
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
@WebServlet(urlPatterns = { "/ChangePwd.controller" })
public class ChangePwdServlet extends HttpServlet {
	
	private MemberService service;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	
		service=(MemberService) context.getBean("memberService");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ChangePwdServlet");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 接收資料
		String c = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String parm = (String) request.getParameter("kaptchafield");		
		String account = request.getParameter("account");
		String oldpwd = request.getParameter("oldpwd");
		String temp1 = request.getParameter("newpwd");
		String prodaction = request.getParameter("prodaction");

		// 轉換資料.驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error",errors);

		if (c != null && parm != null) {
			if (c.equals(parm)) {
			}
			else {
				errors.put("parm","請輸入驗證碼");
			}
		}
		byte[] newpwd = null;
		if (temp1 != null && temp1.trim().length() != 0) {
			newpwd = temp1.getBytes();
		}	
		if (account == null || account.length() == 0) {
			errors.put("account","請輸入帳號");
		}
		if (oldpwd == null || oldpwd.length() == 0) {
			errors.put("oldpwd","請輸入就密碼");
		}
		if (newpwd == null || newpwd.length == 0) {
			errors.put("newpwd","請輸入新密碼");
		}
		if (errors != null && !errors.isEmpty()) {		
			request.getRequestDispatcher("/ChangePwd.jsp").forward(request,response);
			return;
		}
		// 根據model執行結果，導向View	
		MemberBean bean = new MemberBean();
		bean.setPwd(newpwd);	
		if ("ChangePwd".equals(prodaction)) {			
			boolean bean1 = service.changePwd(account,oldpwd,newpwd);		
			if (bean1 == false) {			
				request.getRequestDispatcher("/ChangePwd.jsp").forward(request,response);
				errors.put("action","更改失敗");
			}
			request.getRequestDispatcher("/index.jsp").forward(request,response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request,response);
	}

}
