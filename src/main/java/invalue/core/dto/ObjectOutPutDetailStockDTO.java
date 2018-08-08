package invalue.core.dto;

import java.util.List;

public class ObjectOutPutDetailStockDTO {
	private List<String> headers;
	private List<List<Object>> rows;
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
	
	
}

