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

@WebServlet(urlPatterns = { "/Login.controller" })
public class LoginServlet extends HttpServlet {

	private MemberService service;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = 
				WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		
		service=(MemberService) context.getBean("memberService");
	;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginServlet");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		// 接收資料
		String account = request.getParameter("account");
		String pwd = request.getParameter("pwd");
		String c = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String parm = (String) request.getParameter("kaptchafield");
		String prodaction = request.getParameter("prodaction");
		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("error",errors);

		if (c != null && parm != null) {
			if (c.equals(parm)) {
			}
			else if("更換圖片".equals(prodaction)){
				errors.put("parm","");
			}else{
				errors.put("parm","驗證錯誤");
			}
		}
		if (errors != null && !errors.isEmpty()) {

			request.getRequestDispatcher("/Login.jsp").forward(request,response);

			return;
		}

		// 呼叫model
		MemberBean bean = service.login(account,pwd);

		// 根據model執行結果，導向view
		// if (identity == randomString) {
		if (bean == null) {
			errors.put("pwd","登入失敗!請檢查帳號密碼");
			request.getRequestDispatcher("/Login.jsp").forward(request,response);

		}
		else {
			// 把帳密寫到session
			session.setAttribute("user",bean);

			String path = request.getContextPath();
			response.sendRedirect(path + "/index.jsp");

		}
		// }

	}


	@Override
	protected void doPost(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(reqest,response);
	}
}