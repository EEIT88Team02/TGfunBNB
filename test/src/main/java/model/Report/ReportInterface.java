package model.Report;

import java.util.List;

public interface ReportInterface {

	public abstract List<ReportBean> selectAll();
	
	public abstract boolean insert(ReportBean reportBean);
	
}




