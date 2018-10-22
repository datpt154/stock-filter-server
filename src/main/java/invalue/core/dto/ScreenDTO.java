package invalue.core.dto;

import java.util.List;

public class ScreenDTO {
	private List<SearchItemDTO> dataYear;
	private List<SearchItemDTO> dataQuarter;
	private List<SearchItemDTO> dataTTM;
	public List<SearchItemDTO> getDataYear() {
		return dataYear;
	}
	public void setDataYear(List<SearchItemDTO> dataYear) {
		this.dataYear = dataYear;
	}
	public List<SearchItemDTO> getDataQuarter() {
		return dataQuarter;
	}
	public void setDataQuarter(List<SearchItemDTO> dataQuarter) {
		this.dataQuarter = dataQuarter;
	}
	public List<SearchItemDTO> getDataTTM() {
		return dataTTM;
	}
	public void setDataTTM(List<SearchItemDTO> dataTTM) {
		this.dataTTM = dataTTM;
	}
	
	
}
