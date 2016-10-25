package model.ProdOrderInfo;

import java.util.List;

public interface ProdOrderInfoDAO {

public abstract ProdOrderInfoBean select(int prodorderinfoID);
	
	public abstract ProdOrderInfoBean update(int prodorderinfoID,int prodOrderID,int prodCode,int prodCount,int prodSum);

	public abstract ProdOrderInfoBean insert(ProdOrderInfoBean bean);

	public abstract boolean delete(int prodorderinfoID);

	public abstract List<ProdOrderInfoBean> selectall();
	
	
}
