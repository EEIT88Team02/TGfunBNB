package model.FoodOrder;

import java.util.List;

public  interface FoodOrderDAO {
	
	public abstract FoodOrderBean select(int foodOrderID);
	
	public abstract FoodOrderBean update(int foodOrderID,int OrderID,
			int bbqOrderID,java.sql.Timestamp foodDate,int FoodCount,
			float totalSum);
	
	public abstract FoodOrderBean insert(FoodOrderBean bean);

	public abstract boolean delete(int foodOrderID);

	public abstract List<FoodOrderBean> selectall();

}
