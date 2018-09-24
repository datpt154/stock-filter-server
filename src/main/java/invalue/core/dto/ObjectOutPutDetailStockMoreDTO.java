package invalue.core.dto;

import java.util.List;

public class ObjectOutPutDetailStockMoreDTO {
	private List<String> headers;
	private List<List<Object>> rows;
	private List<String> headerFinance;
	private List<List<Object>> rowsFinance;
	public List<String> getHeaders() {
		return headers;
	}
	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}
	public List<List<Object>> getRows() {
		return rows;
	}
	public void setRows(List<List<Object>> rows) {
		this.rows = rows;
	}
	public List<String> getHeaderFinance() {
		return headerFinance;
	}
	public void setHeaderFinance(List<String> headerFinance) {
		this.headerFinance = headerFinance;
	}
	public List<List<Object>> getRowsFinance() {
		return rowsFinance;
	}
	public void setRowsFinance(List<List<Object>> rowsFinance) {
		this.rowsFinance = rowsFinance;
	}
}

