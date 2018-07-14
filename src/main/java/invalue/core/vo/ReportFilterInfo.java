package invalue.core.vo;

public class ReportFilterInfo {


	private String code;

	private Double max;

	private Double min;

	private String title;
	
	private Double selectedValues[];
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double[] getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(Double[] selectedValues) {
		this.selectedValues = selectedValues;
	}

	
}
