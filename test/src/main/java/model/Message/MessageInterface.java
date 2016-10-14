package model.Message;

import java.util.List;

public interface MessageInterface {
    
	public abstract List<MessageBean> selectAll();
	/*文章記錄搜尋*/
	public abstract List<MessageBean> selectByartCode(Integer artCode );
	
	public abstract boolean insert(MessageBean messageBean);
	
	/*更新讚*/
	public abstract MessageBean update(MessageBean bean);

}
