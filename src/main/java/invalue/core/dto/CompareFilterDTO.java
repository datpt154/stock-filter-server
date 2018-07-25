package invalue.core.dto;

import java.util.ArrayList;

//@Getter @Setter
public class CompareFilterDTO {
	private String companyCode;
	private Double price;
	private ArrayList<SearchItemDTO> searchItems;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}

