package model.Member;

import java.util.List;

import model.Article.ArticleBean;
import model.BonusHistory.BonusHistoryBean;
import model.Message.MessageBean;

public interface MemberDAO {
	/*--------------user--------------*/


	public abstract MemberBean select(int MemberID);// 搜尋

	public abstract MemberBean update(byte[] pwd,String name,String sex, java.util.Date birthday, String ID, String email, String Celphone, String Telephone,
			String address,byte[] photo,int MemberID);// 修改個資

	public abstract MemberBean insert(MemberBean bean);// 新增個資

	public abstract List<MemberBean> selectall();

	public abstract MemberBean selectAD(String account);// 登入比對密碼
	
	public abstract boolean ADupdate(String account,boolean MemberStatus);// 改變驗證碼

	public abstract boolean changpwd(byte[] pwd, String account);// 改密碼
	

	public abstract List<ArticleBean> selectMessage(int MemberID);

	


}
