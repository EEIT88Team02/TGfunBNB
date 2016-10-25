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

import model.Article.ArticleBean;
import model.Member.MemberBean;
import model.Member.MemberService;

@WebServlet(urlPatterns = { "/Login/CheckArticle.controller" })
public class CheckArticleServlet extends HttpServlet {

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
		System.out.println("Article");
		request.setCharacterEncoding("UTF-8");
		// 從Session拿資料出來
		HttpSession session = request.getSession();
		System.out.println("STEP1=" + session);

		String prodaction = request.getParameter("prodaction");

		if (session == null) {// 沒有session重新登入
			response.sendRedirect(response.encodeRedirectURL("/Login/Login.jsp"));
			return;
		}
		MemberBean mb = (MemberBean) session.getAttribute("user");
		int memberID = mb.getMemberID();
		System.out.println("STEP2=" + mb);

		if ("Article".equals(prodaction)) {

			ArticleBean result = (ArticleBean) service.Article(memberID);// 撈出Atricle

			System.out.println("STEP3=" + result);

			Map<String, Object> Art = new HashMap<String,Object>();
	
			Art.put("ArtCode",result.getArtCode());
			Art.put("MemberID",result.getMemberID());
			Art.put("ReportCount",result.getReportCount());
			Art.put("ArtTopic",result.getArtTopic());
			Art.put("ArtContent",result.getArtContent());
			Art.put("artDate",result.getArtDate());
			Art.put("HaveAppeal",result.isHaveAppeal());			
			Art.put("appealDate",result.getAppealDate());
			Art.put("AppealContent",result.getAppealContent());
			Art.put("haveprocess",result.isHaveProcess());
			Art.put("processDate",result.getProcessDate());
			Art.put("reportReply",result.getReportReply());
			Art.put("haveDelete",result.isHaveDelete());
			session.setAttribute("Art",Art);
			System.out.println("STEP3-1="+Art);
			
		
			
			
			System.out.println(session.getAttribute("Art"));

			System.out.println("STEP4=" + result);
			request.getRequestDispatcher("/Login/Article.jsp").forward(request,response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request,response);
	}

}
