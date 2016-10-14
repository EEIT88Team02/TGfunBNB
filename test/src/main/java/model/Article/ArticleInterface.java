package model.Article;


import java.util.Date;
import java.util.List;

public interface ArticleInterface {

	/*查詢message要另外selectByartCode*/
	public abstract List<ArticleBean> selectAll();
	/*尋找發問日期*/
	public abstract List<ArticleBean> selectByartDate(Date date, Date date1);
	/*主題搜尋*/
	public abstract List<ArticleBean> selectByArtTopic(String ArtTopic);
	/* 帳號文章搜尋*/
	public abstract List<ArticleBean> selectByaccount(String name);
	
	/* 是否有申訴文章(帳號)*/
	public abstract boolean selectByHaveAppeal(String name);
	
	/* 申訴文章板主是否已處理(帳號)*/
	public abstract boolean selectByhaveprocess(String name);
	
	public abstract boolean insert(ArticleBean articleBean);
	
	public abstract  ArticleBean  update(ArticleBean bean1);
	
}
