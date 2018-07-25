package invalue.core.dto;

import java.util.ArrayList;

//@Getter @Setter
public class BasicFilterDTO {
	private String companyCode;
	private String companyName;
	private String stockExchange;
	private Double price;
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

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}

