package invalue.core.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListReportFilterInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5271022628926318417L;
	private List<ReportFilterInfo> lstSearch = new ArrayList<>();
	public List<ReportFilterInfo> getLstSearch() {
		return lstSearch;
	}
	public void setLstSearch(List<ReportFilterInfo> lstSearch) {
		this.lstSearch = lstSearch;
	}
	
}
