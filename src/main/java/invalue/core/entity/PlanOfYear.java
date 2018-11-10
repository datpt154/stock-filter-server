package invalue.core.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "plan_of_year", uniqueConstraints = {@UniqueConstraint(columnNames = "CODE") })
public class PlanOfYear implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_of_year_generator")
	@SequenceGenerator(name="plan_of_year_generator", sequenceName = "plan_of_year_seq", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Basic
	@Column(name = "CODE", nullable = false, unique = true, length = 100)
	private String code;
	
	@Basic
	@Column(name = "TYPE", nullable = false)
	private Integer type;
	
	@Basic
	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME", nullable = false, length = 19)
	private Date createdTime;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_TIME", length = 19, insertable = false, updatable = true)
	private Date updatedTime;
	
	@Basic
	@Column(name = "TIME_STRING", length = 255)
	private String timeString;

	@Basic
	@Column(name = "STOCK_CODE", nullable = false, length = 255)
	private String stockCode;

	@Basic
	@Column(name = "STOCK_ID", nullable = false)
	private Integer stockId;
	
	@Basic
	@Column(name = "REVENUE", precision = 20, scale = 5)
	private Double revenue;	
	
	@Basic
	@Column(name = "REVENUE_NOW", precision = 20, scale = 5)
	private Double revenueNow;	
	
	@Basic
	@Column(name = "REVENUE_PERCENT", precision = 20, scale = 5)
	private Double revenuePercent;	

	@Basic
	@Column(name = "PRETAX_PROFIT", precision = 20, scale = 5)
	private Double pretaxProfit;
	
	@Basic
	@Column(name = "PRETAX_PROFIT_NOW", precision = 20, scale = 5)
	private Double pretaxProfitNow;
	
	@Basic
	@Column(name = "PRETAX_PROFIT_PERCENT", precision = 20, scale = 5)
	private Double pretaxProfitPercent;
	
	@Basic
	@Column(name = "NET_PROFIT", precision = 20, scale = 5)
	private Double netProfit;
	
	@Basic
	@Column(name = "NET_PROFIT_NOW", precision = 20, scale = 5)
	private Double netProfitNow;
	
	@Basic
	@Column(name = "NET_PROFIT_PERCENT", precision = 20, scale = 5)
	private Double netProfitPercent;
	
	@Basic
	@Column(name = "PAYMENTS_OF_DIVIDENDS", precision = 20, scale = 5)
	private Double paymentsOfDividends;
	
	@Basic
	@Column(name = "RESULT", precision = 20, scale = 5)
	private Double result;
	
	@Basic
	@Column(name = "YOY", precision = 20, scale = 5)
	private Double yoy;
	
	@Basic
	@Column(name = "DBTTM", precision = 20, scale = 5)
	private Double dBTTM;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
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

	public Double getPaymentsOfDividends() {
		return paymentsOfDividends;
	}

	public void setPaymentsOfDividends(Double paymentsOfDividends) {
		this.paymentsOfDividends = paymentsOfDividends;
	}

	public Double getRevenueNow() {
		return revenueNow;
	}

	public void setRevenueNow(Double revenueNow) {
		this.revenueNow = revenueNow;
	}

	public Double getRevenuePercent() {
		return revenuePercent;
	}

	public void setRevenuePercent(Double revenuePercent) {
		this.revenuePercent = revenuePercent;
	}

	public Double getPretaxProfitNow() {
		return pretaxProfitNow;
	}

	public void setPretaxProfitNow(Double pretaxProfitNow) {
		this.pretaxProfitNow = pretaxProfitNow;
	}

	public Double getPretaxProfitPercent() {
		return pretaxProfitPercent;
	}

	public void setPretaxProfitPercent(Double pretaxProfitPercent) {
		this.pretaxProfitPercent = pretaxProfitPercent;
	}

	public Double getNetProfitNow() {
		return netProfitNow;
	}

	public void setNetProfitNow(Double netProfitNow) {
		this.netProfitNow = netProfitNow;
	}

	public Double getNetProfitPercent() {
		return netProfitPercent;
	}

	public void setNetProfitPercent(Double netProfitPercent) {
		this.netProfitPercent = netProfitPercent;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public Double getYoy() {
		return yoy;
	}

	public void setYoy(Double yoy) {
		this.yoy = yoy;
	}

	public Double getdBTTM() {
		return dBTTM;
	}

	public void setdBTTM(Double dBTTM) {
		this.dBTTM = dBTTM;
	}
}