package invalue.core.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter @Setter
public class BasicFilterDTO {
	private String companyName;
	private String companyCode;
	private ArrayList<SearchItemDTO> searchItems;
 	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public ArrayList<SearchItemDTO> getSearchItems() {
		return searchItems;
	}

	public void setSearchItems(ArrayList<SearchItemDTO> searchItems) {
		this.searchItems = searchItems;
	}
}

