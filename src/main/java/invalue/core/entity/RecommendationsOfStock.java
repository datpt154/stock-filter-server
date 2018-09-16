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
@Table(name = "recommendations_of_stock")
public class RecommendationsOfStock implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recommendations_of_stock_generator")
	@SequenceGenerator(name="recommendations_of_stock_generator", sequenceName = "recommendations_of_stock_seq", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TIME_RECOMMENDATIONS", nullable = false, length = 19)
	private Date timeRecommendations;

	@Basic
	@Column(name = "STOCK_CODE", nullable = false, length = 255)
	private String stockCode;

	@Basic
	@Column(name = "STOCK_ID", nullable = false)
	private Integer stockId;

	@Basic
	@Column(name = "COMPANY_RECOMMENDATIONS", nullable = false)
	private String companyRecommendations;
	
	@Basic
	@Column(name = "RECOMMENDATIONS_ACTION", nullable = false)
	private String companyRecommendationsAction;
	
	@Basic
	@Column(name = "TARGET_PRICE", precision = 20, scale = 5)
	private Double targetPrice;
	
	@Basic
	@Column(name = "REVENUE", precision = 20, scale = 5)
	private Double revenue;	

	@Basic
	@Column(name = "PRETAX_PROFIT", precision = 20, scale = 5)
	private Double pretaxProfit;
	
	@Basic
	@Column(name = "NET_PROFIT", precision = 20, scale = 5)
	private Double netProfit;
	
	@Basic
	@Column(name = "PATH_FILE")
	private String pathFile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getTimeRecommendations() {
		return timeRecommendations;
	}

	public void setTimeRecommendations(Date timeRecommendations) {
		this.timeRecommendations = timeRecommendations;
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

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}
	
	
	
}