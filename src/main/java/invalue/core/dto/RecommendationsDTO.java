package invalue.core.dto;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;

public class RecommendationsDTO {
	private Date timeRecommendations;
	private String companyRecommendations;
	private String companyRecommendationsAction;
	private Double targetPrice;
	private Double revenue;	
	private Double pretaxProfit;
	private Double netProfit;
	private String pathFile;
	
	public Date getTimeRecommendations() {
		return timeRecommendations;
	}
	public void setTimeRecommendations(Date timeRecommendations) {
		this.timeRecommendations = timeRecommendations;
	}
	
	public String getCompanyRecommendations() {
		return companyRecommendations;
	}
	public void setCompanyRecommendations(String companyRecommendations) {
		this.companyRecommendations = companyRecommendations;
	}
	public String getCompanyRecommendationsAction() {
		return companyRecommendationsAction;
	}
	public void setCompanyRecommendationsAction(String companyRecommendationsAction) {
		this.companyRecommendationsAction = companyRecommendationsAction;
	}
	public Double getTargetPrice() {
		return targetPrice;
	}
	public void setTargetPrice(Double targetPrice) {
		this.targetPrice = targetPrice;
	}
	public Double getRevenue() {
		return revenue;
	}
	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	public Double getPretaxProfit() {
		return pretaxProfit;
	}
	public void setPretaxProfit(Double pretaxProfit) {
		this.pretaxProfit = pretaxProfit;
	}
	public Double getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(Double netProfit) {
		this.netProfit = netProfit;
	}
	public String getPathFile() {
		return pathFile;
	}
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}
	
}

