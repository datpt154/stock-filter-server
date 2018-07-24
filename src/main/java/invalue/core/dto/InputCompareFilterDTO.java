package invalue.core.dto;

import java.util.List;

import invalue.core.vo.ReportFilterInfo;

//@Getter @Setter
public class InputCompareFilterDTO {
	private List<ReportFilterInfo> searchDataitems;
	private String time;
	private List<String> stocks;
	public List<ReportFilterInfo> getSearchDataitems() {
		return searchDataitems;
	}
	public void setSearchDataitems(List<ReportFilterInfo> searchDataitems) {
		this.searchDataitems = searchDataitems;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<String> getStocks() {
		return stocks;
	}
	public void setStocks(List<String> stocks) {
		this.stocks = stocks;
	}
	
}

