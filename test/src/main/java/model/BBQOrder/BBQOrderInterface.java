package model.BBQOrder;

import java.util.List;

public interface BBQOrderInterface {

	public abstract List<BBQOrderBean> selectByOrderID(int orderID,boolean haveDelete);
	
	public abstract boolean insert(BBQOrderBean bbqOrderBean);	
	
	public abstract BBQOrderBean updateHaveDelete(BBQOrderBean bbqOrderBean);
	
}
