package invalue.core.entity;

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
@Table(name = "break_out")
public class BreakOut implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "break_out_generator")
	@SequenceGenerator(name="break_out_generator", sequenceName = "break_out_seq", allocationSize=1)
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
	@Column(name = "STOCK_CODE", nullable = false)
	private String stockCode;
	
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BREAK_TIME", nullable = false, length = 19)
	private Date breakTime;
	
	@Basic
	@Column(name = "CLOSE_PRICE", precision = 20, scale = 5)
	private Double closePrice;
	@Basic
	@Column(name = "RESISTANCE", precision = 20, scale = 5)
	private Double Resistance;
	@Basic
	@Column(name = "SUPPORT", precision = 20, scale = 5)
	private Double support;
	@Basic
	@Column(name = "ADX14", precision = 20, scale = 5)
	private Double adx14;
	@Basic
	@Column(name = "RSI14", precision = 20, scale = 5)
	private Double rsi14;
	@Basic
	@Column(name = "MACD", precision = 20, scale = 5)
	private Double macd;
	@Basic
	@Column(name = "VOLUME", precision = 20, scale = 5)
	private Double volume;
	@Basic
	@Column(name = "SCREEN", nullable = false)
	private String screen;
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
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public Date getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(Date breakTime) {
		this.breakTime = breakTime;
	}
	public Double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}
	public Double getResistance() {
		return Resistance;
	}
	public void setResistance(Double resistance) {
		Resistance = resistance;
	}
	public Double getSupport() {
		return support;
	}
	public void setSupport(Double support) {
		this.support = support;
	}
	public Double getAdx14() {
		return adx14;
	}
	public void setAdx14(Double adx14) {
		this.adx14 = adx14;
	}
	public Double getRsi14() {
		return rsi14;
	}
	public void setRsi14(Double rsi14) {
		this.rsi14 = rsi14;
	}
	public Double getMacd() {
		return macd;
	}
	public void setMacd(Double macd) {
		this.macd = macd;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}

	
}