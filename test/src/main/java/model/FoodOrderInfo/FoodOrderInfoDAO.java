package model.FoodOrderInfo;

import java.util.List;

public interface FoodOrderInfoDAO {

	
	public abstract FoodOrderInfoBean select(int FoodorderInfoID);
	
	public abstract FoodOrderInfoBean update(int FoodorderInfoID,int ProdCode,int FoodOrderID,int FoodCount,float FoodSum);
	
	public abstract FoodOrderInfoBean insert(FoodOrderInfoBean bean);

	public abstract boolean delete(int FoodorderInfoID);

	public abstract List<FoodOrderInfoBean> selectall();

	
}
