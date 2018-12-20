package invalue.core.dto;

import java.util.List;

public class FilterNewsDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String createdTime;

	private String title;

	private Integer categoryId;
	
	private List<String> listTag;
	
	private Integer start;
	
	private Integer numRow;

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public List<String> getListTag() {
		return listTag;
	}

	public void setListTag(List<String> listTag) {
		this.listTag = listTag;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getNumRow() {
		return numRow;
	}

	public void setNumRow(Integer numRow) {
		this.numRow = numRow;
	}

	
}