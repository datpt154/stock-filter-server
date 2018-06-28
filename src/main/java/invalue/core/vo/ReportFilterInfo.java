package invalue.core.vo;

import java.io.Serializable;

public class ReportFilterInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1343859963517305230L;
	
	private String code;
	
	private Double max;
	
	private Double min;

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
	
	
}
