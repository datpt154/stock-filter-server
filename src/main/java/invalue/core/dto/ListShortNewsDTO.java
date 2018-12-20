package invalue.core.dto;

import java.util.List;

public class ListShortNewsDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer totalRows;

	private List<ShortNewsDTO> listData;

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public List<ShortNewsDTO> getListData() {
		return listData;
	}

	public void setListData(List<ShortNewsDTO> listData) {
		this.listData = listData;
	}
}