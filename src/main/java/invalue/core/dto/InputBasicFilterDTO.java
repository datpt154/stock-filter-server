package invalue.core.dto;

import java.util.List;

import invalue.core.vo.ReportFilterInfo;

//@Getter @Setter
public class InputBasicFilterDTO {
	private List<ReportFilterInfo> searchDataitems;
	private String time;
	private String[] stockExchanges;
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
	public String[] getStockExchanges() {
		return stockExchanges;
	}
	public void setStockExchanges(String[] stockExchanges) {
		this.stockExchanges = stockExchanges;
	}
}

