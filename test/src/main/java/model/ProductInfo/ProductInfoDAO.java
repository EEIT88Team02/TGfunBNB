package model.ProductInfo;

import java.util.List;

public interface ProductInfoDAO {

	public abstract ProductInfoBean select(int ProdCode);

	public abstract ProductInfoBean update(int ProdCode,String ProdClass,String ProdName,float ProdPrice,int Inventory,boolean FoodCheck,byte[] ProdImage);
	
	public abstract ProductInfoBean insert(ProductInfoBean bean);

	public abstract boolean delete(int ProdCode);

	public abstract List<ProductInfoBean> selectall();

}
