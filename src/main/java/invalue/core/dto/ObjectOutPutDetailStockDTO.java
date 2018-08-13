package invalue.core.dto;

import java.util.List;

public class ObjectOutPutDetailStockDTO {
	private String name;
	private String code;
	private Double price;
	private List<Object> recommended;
	private List<String> headers;
	private List<List<Object>> rows;
	private List<List<Object>> plans;
	private List<List<Object>> valuation;
	private List<List<Object>> score;
	private List<List<Object>> liquidityRatio;
	private List<List<Object>> activityTurnover;
	private List<List<Object>> interpretationOfSolvencyRatios;
	private List<List<Object>> riskRatio;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public List<Object> getRecommended() {
		return recommended;
	}
	public void setRecommended(List<Object> recommended) {
		this.recommended = recommended;
	}
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
	public List<List<Object>> getPlans() {
		return plans;
	}
	public void setPlans(List<List<Object>> plans) {
		this.plans = plans;
	}
	public List<List<Object>> getValuation() {
		return valuation;
	}
	public void setValuation(List<List<Object>> valuation) {
		this.valuation = valuation;
	}
	public List<List<Object>> getScore() {
		return score;
	}
	public void setScore(List<List<Object>> score) {
		this.score = score;
	}
	public List<List<Object>> getLiquidityRatio() {
		return liquidityRatio;
	}
	public void setLiquidityRatio(List<List<Object>> liquidityRatio) {
		this.liquidityRatio = liquidityRatio;
	}
	public List<List<Object>> getActivityTurnover() {
		return activityTurnover;
	}
	public void setActivityTurnover(List<List<Object>> activityTurnover) {
		this.activityTurnover = activityTurnover;
	}
	public List<List<Object>> getInterpretationOfSolvencyRatios() {
		return interpretationOfSolvencyRatios;
	}
	public void setInterpretationOfSolvencyRatios(List<List<Object>> interpretationOfSolvencyRatios) {
		this.interpretationOfSolvencyRatios = interpretationOfSolvencyRatios;
	}
	public List<List<Object>> getRiskRatio() {
		return riskRatio;
	}
	public void setRiskRatio(List<List<Object>> riskRatio) {
		this.riskRatio = riskRatio;
	}
	
	
}

