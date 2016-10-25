package tgfun.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import model.Article.ArticleBean;
import model.Article.ArticleService;
import model.Member.MemberService;
import model.Message.MessageBean;
import model.Message.MessageService;
import model.Report.ReportBean;
import model.Report.ReportService;
import tgfun.action.bv; 
@Controller
public class testaction {
	
	private ArticleService  Article;
	private ReportService  report;
	private MessageService message;
	@PostConstruct	
	public void init(){ 
		Article=new ArticleService();
	  report=new ReportService();
	  message=new MessageService();
	}
	@RequestMapping(value="/inserts")
	public ModelAndView message_board_inserts(HttpServletRequest request){
		ModelAndView model=new ModelAndView("message_board");
		List<Object[]> m=Article.Show_ALL_articles();		 
		List<List<String>> ABean=new ArrayList<>();
       List<List<String>> MBean=new ArrayList<>();
if(m!=null)
	 {
	 for(int u=0;u<m.size();u++)	
	 {	
	 	Object[] x=m.get(u);
	 	int i=0; 
	 	for(Object vv :x)	 
	 	 {
	 		 if(i==0)
	 		 {
	 			 ArticleBean df=(ArticleBean)vv;	
	 			List<String> AB=new ArrayList<>();
	 			 AB.add(Integer.toString(df.getArtCode()));//文章編號[0]
	 			 AB.add(Integer.toString(df.getMemberID()));//會員編號[1]
	 			 AB.add(df.getArtTopic());//主題[2]
	 			 AB.add(df.getArtContent());//留言內容[3]
	 			 AB.add(df.getArtDate().toString());//發問日期[4]
	 			 AB.add(df.getAppealDate().toString());//申訴日期[5] 
	 			 AB.add(String.valueOf(df.isHaveAppeal()));//是否申訴[6]
	 			 AB.add(df.getAppealContent());//申訴內容[7]
	 			 AB.add(String.valueOf(df.isHaveProcess()));//是否處理[8]   
	 			 AB.add(df.getProcessDate().toString());//處理日期[9]
	 			 AB.add(df.getReportReply());//管理者回復[10]
	 			 AB.add(String.valueOf(df.isHaveDelete()));//是否刪除[11]        
	 			 AB.add(df.getMembers().getName());//會員姓名[12]
	 			 ABean.add(AB);
	 			
	 		 }else{
	 		  MessageBean dj= ( MessageBean)vv;
	 		   
	 		  List<String>MB=new ArrayList<>();
	 		 MB.add(String.valueOf(dj.getMsgCode()));//留言編號[0]
	 		 MB.add(String.valueOf(dj.getMemberID()));//會員編號[1]
	 		 MB.add(String.valueOf(dj.getArtCode()));//文章編號[2]
	 		 MB.add(dj.getMsgDate().toString());//留言日期[3]
	 		 MB.add(dj.getMsgContent().toString());//留言內容[4]
	 		 MB.add(String.valueOf(dj.getGood()));//讚[5]
	 		 MB.add(String.valueOf(dj.getGood()));//噓[6]
	 		 MB.add(dj.getMembers().getName());//留言姓名[7]
	 		 MBean.add(MB);
	 		// System.out.println(dj);
	 		 }
	 	   ++i;
	 	  }
	 }	
 }

//System.out.println(ABean.get(1));
request.setAttribute("ArticleBean",ABean);
request.setAttribute("MessageBean",MBean);
		return model;
	}
	
	
	@RequestMapping(value="/insert")
	public ModelAndView message_board_insert(@ModelAttribute(value="message_board_insert")@Valid article_verification arc,Errors errors,HttpServletRequest request,HttpSession ssion)
	{
		ModelAndView model=new ModelAndView("message_board");
		
		System.out.println("test2");
		 if(errors.hasErrors()){
		   String x=arc.getTheme();
			String xx=arc.getContext(); 
			int y=x.length();
			int yy=xx.length();
			if(x.isEmpty())
			{
				request.setAttribute("errortheme","主題不能為空");	
			}else if(y>50){
				request.setAttribute("errortheme","字數介於1-50之間");
			}
			if(xx.isEmpty()){
				request.setAttribute("errorcontext","內容不能為空");
			}else if(yy>1600){
				request.setAttribute("errorcontext","字數介於1-1600之間");
				
			}
			 	 
			
		 }
	    HttpSession ssio=request.getSession();
		ssio.setAttribute("vv", "xx"); 
		ssio.setAttribute("rr", "ww"); 
				Integer aa=Integer.valueOf(String.valueOf(ssio.getAttribute("mumber")));
  
		
		//			 Integer mm=Integer.valueOf(String.valueOf( ssio.getAttribute("mumber")));
//		     //  SessionFactory session=HibernateUtil.getSessionFactory();
//		       
//
//		       
//		       
            Object[] st=new Object[12];     
		     st[0]=aa;  
             st[1]=0;
             st[2]=arc.getTheme();
             st[3]=arc.getContext();
             st[4]=new Date();
			 st[5]=new Date();
             st[6]=false;
             st[7]="";
             st[8]=true;
             st[9]=new Date();
             st[10]="asdf";
             st[11]=false; 
            		 
             Article.Articleinsert(st);
             message_board_inserts(request);	 
			 return model;
	}	
	
	@RequestMapping(value="/violation")
	public ModelAndView violation(@ModelAttribute(value="violation")@Valid Violation_verification violation,Errors errors,HttpServletRequest request)	
	{
		ModelAndView model=new ModelAndView("Violation");
		ModelAndView mode2=new ModelAndView("Flagged");
		List<Object[]> dd=Article.selectByartCode(Integer.parseInt(violation.getArtCode()));//文章編號
		HttpSession ssio=request.getSession();
		System.out.println(ssio.getAttribute("mumber"));//檢舉人ID 
		System.out.println(violation.getMemberID());//被檢舉人ID
		boolean y=false;             
		if(dd!=null)   
		  {
			  Object w =dd.get(0);
			  
			  List<ReportBean>vn=report.Show_ALL_Report();
		         if(vn!=null) 
		    	{
		    	 for(int u=0;u<vn.size();++u)	
		    	  {
		    		 ReportBean x=vn.get(u);
		            int check1= x.getArtCode();
		    	    int check2=x.getReportMemberID();
		    	   /*之後要做已檢舉過頁面*/
		    	    if((Integer.valueOf(violation.getArtCode())==check1)&&(Integer.valueOf(String.valueOf(ssio.getAttribute("mumber")))==check2))
		    	   {
		    	    	System.out.println("test");
		    	    	y=true;
		    	    	
		    	   }
		    	    
		    	  }
		     	}
		      
               if(y==false)
               {
            	   /*紀錄檢舉*/
			    Object [] fh=new Object[4];
			    fh[0]=Integer.parseInt(violation.getArtCode()); 
			    fh[1]= ssio.getAttribute("mumber");
			    fh[2]="爛文章";
			    fh[3]=new Date();
			    report.insert(fh);

			  
			  /*更新檢舉次數*/      
			  ArticleBean cm=(ArticleBean)w; 
			    int vv=cm.getReportCount()+1;
			    
			    Object[] st=new Object[13];     
			   
			      
			   System.out.println(cm.getArtCode());
		    
			      st[0]= cm.getArtCode();  
	             st[1]=cm.getMemberID();
	             st[2]=5;
	             st[3]=cm.getArtTopic();
	             st[4]=cm.getArtContent();
				 st[5]=cm.getArtDate();
	             st[6]=cm.getAppealDate();
	             st[7]=cm.isHaveAppeal();
	             st[8]=cm.getAppealContent();
	             st[9]=cm.isHaveProcess();
	             st[10]=cm.getProcessDate();
	             st[11]=cm.getReportReply(); 
			     st[12]=cm.isHaveDelete();
			    Article.Articleupdate(st);
		   
		 
	    request.setAttribute("artCode", violation.getArtCode());
		request.setAttribute("ProsecutorID",ssio.getAttribute("mumber") );
		request.setAttribute("By_prosecutorID",violation.getMemberID() );		
		   return model;
                }else
                {
                	 return mode2;	
                }
               }
              
		 return mode2;	
		
	 
	}
     	
    @RequestMapping(value="/violationcheck")	
	public ModelAndView violationcheck(@ModelAttribute(value="violationcheck")Violation2_verification viol,HttpServletRequest request)
	{
    	ModelAndView model=new ModelAndView("message_board");
        
    	//System.out.println(viol.getTextvalue());
    	
       
//    	/*新增檢舉*/	
//    	Object[] bean=new Object[4];
//    	bean[0]=viol.getArtCode();
//    	bean[1]=viol.getProsecutorID();
//    	bean[2]=viol.getTextvalue();
//    	bean[3]=new Date();
//    	report.insert(bean);
//    	/*檢舉寄信*/
		String email="mark7771002@yahoo.com.tw"; 
		
		 String host = "smtp.gmail.com";
		  int port = 587;
		  Properties props = new Properties();
		  final String username = "eeit.team07@gmail.com";
		  final String password = "eeitteam07";//your password
		  
		  props.put("mail.smtp.starttls.enable", "true");
		  props.put("mail.transport.protocl","smtp");
		  props.put("mail.smtp.host","smtp.gmail.com");
		  props.put("mail.smtp.port","465");
		  props.put("mail.smtp.auth","true");
		  props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		  props.setProperty("mail.smtp.socketFactory.fallback","false");
		  props.setProperty("mail.smtp.socketFactory.port","465");
		  javax.mail.Session session = javax.mail.Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
			    return new PasswordAuthentication(username, password);
			   }
			  });

			String body="http://localhost:8080/test/index.jsp"; 
		  try {
					System.out.println("mail-4");
					 MimeBodyPart mbp = new MimeBodyPart(); 
					   mbp.setText(body,"text/html");
					   Multipart multipart = new MimeMultipart();
					   multipart.addBodyPart(mbp);
					   
					
					Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress("eeit.team07@gmail.com"));
				message.setContent(multipart);
			   System.out.println("mail-5");
			   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				System.out.println("mail-6");
			   message.setSubject("同樂會民宿文章檢舉(您有被檢舉的文章)");
			   message.setText(body);
			   System.out.println("mail-7");
			   Transport transport = session.getTransport("smtp");
			   System.out.println("mail-8");
			   transport.connect(host, port, username, password);
			   System.out.println("mail-9");
			   Transport.send(message);
			   System.out.println(message);
			   System.out.println("mail-10");
			   System.out.println("寄送email結束.");

			  } catch (MessagingException e) {
			   throw new RuntimeException(e);
			  }
    	message_board_inserts(request);		
    	return model;	
	}

    @RequestMapping(value="/remessage")	
   	public ModelAndView remessage(@ModelAttribute(value="remessage")@Valid message_verification mes,Errors errors,HttpServletRequest request)
   	{
    	ModelAndView model=new ModelAndView("message_board");   
    	 String x=mes.getArticl();	
         String xx=mes.getLeave(); 
    	if(errors.hasErrors())
        {
          if(x.isEmpty())	
          request.setAttribute("errorarticl","編號不可為空"); 	
          if(xx.isEmpty())
          request.setAttribute("errorleave", "留言不可為空"); 
       
        }else{
        	Object[] sw=new Object[6];
        	HttpSession ssio=request.getSession();
    		System.out.println(ssio.getAttribute("mumber"));//檢舉人ID 
    		Integer aa=Integer.valueOf(String.valueOf(ssio.getAttribute("mumber")));
    		sw[0]=aa;
        	sw[1]=x;
        	sw[2]=xx;
        	sw[3]=new Date();
        	sw[4]=0;
        	sw[5]=0;
        	message.Message_insert(sw);		  	
        }
    	message_board_inserts(request);	
    	return model;	
            }

    @RequestMapping(value="/Like_taking")
    public @ResponseBody messageJSON Like_taking( String gg, String msgCode,HttpServletRequest request )
    {
        
       Integer xc=Integer.valueOf(msgCode);
       System.out.println("action::"+gg);
       List<MessageBean> mn=message.Show_ALL_Messages();
    	 Iterator iter=mn.iterator();
    	 MessageBean cv=new MessageBean();
    	 while(iter.hasNext()){
        	  MessageBean bn=(MessageBean)iter.next();	  
              if(Integer.valueOf(msgCode)==bn.getMsgCode())
              {
               cv=bn;	  
              }
          }  	
      Object[] sw=new Object[7];
  	
		sw[0]=cv.getMsgCode();
  	    sw[1]=cv.getMemberID();
  	    sw[2]=cv.getArtCode();
  	    sw[3]=cv.getMsgContent();
  	    sw[4]=cv.getMsgDate();
  	    sw[5]=cv.getGood()+1;
        sw[6]=0;
        message.Message_update(sw);
       messageJSON jh=new messageJSON();
        jh.setGg(gg);
        jh.setGood(String.valueOf(sw[5])); 
        return jh;
    
    }




}
