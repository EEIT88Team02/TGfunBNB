package model.ProdOrder;

import java.util.List;

public interface ProdOrderDAO {
	
	public abstract ProdOrderBean select(int ProdOrderID);
	
	public abstract ProdOrderBean update(int ProdOrderID,int OrderID,java.sql.Timestamp prodDate,float totalSum);

	public abstract ProdOrderBean insert(ProdOrderBean bean);

	public abstract boolean delete(int ProdOrderID);

	public abstract List<ProdOrderBean> selectall();

}
