package invalue.core.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "finance_ratio_y", uniqueConstraints = {@UniqueConstraint(columnNames = "CODE") })
public class FinanceRatioY implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
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
	@Column(name = "NET_REVENUE", precision = 20, scale = 5)
	private Double netRevenue;
	@Basic
	@Column(name = "GROSS_PROFIT", precision = 20, scale = 5)
	private Double grossProfit;
	@Basic
	@Column(name = "NET_INCOME", precision = 20, scale = 5)
	private Double netIncome;
	@Basic
	@Column(name = "SHARE_S_OUSTANDING", precision = 20, scale = 5)
	private Double shareSOustanding;
	@Basic
	@Column(name = "EPS", precision = 20, scale = 5)
	private Double eps;
	@Basic
	@Column(name = "BOOK_VALUE", precision = 20, scale = 5)
	private Double bookValue;
	@Basic
	@Column(name = "MARKET_PRICE", precision = 20, scale = 5)
	private Double marketPrice;
	@Basic
	@Column(name = "DAYYS", precision = 20, scale = 5)
	private Double dayys;
	@Basic
	@Column(name = "CAPEX", precision = 20, scale = 5)
	private Double capex;
	@Basic
	@Column(name = "FCF", precision = 20, scale = 5)
	private Double fcf;
	@Basic
	@Column(name = "EBIT", precision = 20, scale = 5)
	private Double ebit;
	@Basic
	@Column(name = "EBITDA", precision = 20, scale = 5)
	private Double ebitda;
	@Basic
	@Column(name = "NNWC", precision = 20, scale = 5)
	private Double nnwc;
	@Basic
	@Column(name = "NET_WORKING_CAPITAL", precision = 20, scale = 5)
	private Double netWorkingCapital;
	@Basic
	@Column(name = "EV", precision = 20, scale = 5)
	private Double ev;
	@Basic
	@Column(name = "MARKET_CAPITAL", precision = 20, scale = 5)
	private Double marketCapital;
	@Basic
	@Column(name = "NET_REVENUE_YOY", precision = 20, scale = 5)
	private Double netRevenueYoy;
	@Basic
	@Column(name = "GROSS_PROFIT_YOY", precision = 20, scale = 5)
	private Double grossProfitYoy;
	@Basic
	@Column(name = "EPS_YOY", precision = 20, scale = 5)
	private Double epsYoy;
	@Basic
	@Column(name = "EBITDA_YOY", precision = 20, scale = 5)
	private Double ebitdaYoy;
	@Basic
	@Column(name = "DEBT_YOY", precision = 20, scale = 5)
	private Double debtYoy;
	@Basic
	@Column(name = "EQUITY_YOY", precision = 20, scale = 5)
	private Double equityYoy;
	@Basic
	@Column(name = "MARKET_CAPITAL_YOY", precision = 20, scale = 5)
	private Double marketCapitalYoy;
	@Basic
	@Column(name = "TOTAL_ASSETS_YOY", precision = 20, scale = 5)
	private Double totalAssetsYoy;
	@Basic
	@Column(name = "P_E", precision = 20, scale = 5)
	private Double pE;
	@Basic
	@Column(name = "PEG", precision = 20, scale = 5)
	private Double peg;
	@Basic
	@Column(name = "P_B", precision = 20, scale = 5)
	private Double pB;
	@Basic
	@Column(name = "P_S", precision = 20, scale = 5)
	private Double pS;
	@Basic
	@Column(name = "EV_EBITDA", precision = 20, scale = 5)
	private Double evEbitda;
	@Basic
	@Column(name = "EV_EBIT", precision = 20, scale = 5)
	private Double evEbit;
	@Basic
	@Column(name = "EV_FCF", precision = 20, scale = 5)
	private Double evFcf;
	@Basic
	@Column(name = "REV_FCF", precision = 20, scale = 5)
	private Double revFcf;
	@Basic
	@Column(name = "MC_CFO", precision = 20, scale = 5)
	private Double mcCfo;
	@Basic
	@Column(name = "MC_NWC", precision = 20, scale = 5)
	private Double mcNwc;
	@Basic
	@Column(name = "FCFF", precision = 20, scale = 5)
	private Double fcff;
	@Basic
	@Column(name = "FCFE", precision = 20, scale = 5)
	private Double fcfe;
	@Basic
	@Column(name = "CAPEX_REV", precision = 20, scale = 5)
	private Double capexRev;
	@Basic
	@Column(name = "ROIC", precision = 20, scale = 5)
	private Double roic;
	@Basic
	@Column(name = "ROCE", precision = 20, scale = 5)
	private Double roce;
	@Basic
	@Column(name = "ROE", precision = 20, scale = 5)
	private Double roe;
	@Basic
	@Column(name = "ROA", precision = 20, scale = 5)
	private Double roa;
	@Basic
	@Column(name = "GROSS_PROFIT_MARGIN", precision = 20, scale = 5)
	private Double grossProfitMargin;
	@Basic
	@Column(name = "OPERATING_PROFIT_MARGIN", precision = 20, scale = 5)
	private Double operatingProfitMargin;
	@Basic
	@Column(name = "PRETAX_PROFIT_MARGIN", precision = 20, scale = 5)
	private Double pretaxProfitMargin;
	@Basic
	@Column(name = "NET_PROFIT_MARGIN", precision = 20, scale = 5)
	private Double netProfitMargin;
	@Basic
	@Column(name = "DIV_YIELD", precision = 20, scale = 5)
	private Double divYield;
	@Basic
	@Column(name = "EBIT_REV", precision = 20, scale = 5)
	private Double ebitRev;
	@Basic
	@Column(name = "EBITDA_REV", precision = 20, scale = 5)
	private Double ebitdaRev;
	@Basic
	@Column(name = "SALES_TO_TOTAL_ASSETS", precision = 20, scale = 5)
	private Double salesToTotalAssets;
	@Basic
	@Column(name = "RECEIVABLE_TURNOVER", precision = 20, scale = 5)
	private Double receivableTurnover;
	@Basic
	@Column(name = "PAYABLE_TURNOVER", precision = 20, scale = 5)
	private Double payableTurnover;
	@Basic
	@Column(name = "INVENTORY_TURNOVER", precision = 20, scale = 5)
	private Double inventoryTurnover;
	@Basic
	@Column(name = "DEBT_TO_ASSETS_RATIO", precision = 20, scale = 5)
	private Double debtToAssetsRatio;
	@Basic
	@Column(name = "DEBT_TO_EQUITY_RATIO", precision = 20, scale = 5)
	private Double debtToEquityRatio;
	@Basic
	@Column(name = "LONG_TIME_DEBT_TOTAL_CAPITALAZION", precision = 20, scale = 5)
	private Double longTimeDebtTotalCapitalazion;
	@Basic
	@Column(name = "INTEREST_COVERAGE", precision = 20, scale = 5)
	private Double interestCoverage;
	@Basic
	@Column(name = "CURRENT_RATIO", precision = 20, scale = 5)
	private Double currentRatio;
	@Basic
	@Column(name = "QUICK_RATIO", precision = 20, scale = 5)
	private Double quickRatio;
	@Basic
	@Column(name = "CASH_RATIO", precision = 20, scale = 5)
	private Double cashRatio;
	@Basic
	@Column(name = "ACCOUNT_RECEIVABLE_TO_REVENUE", precision = 20, scale = 5)
	private Double accountReceivableToRevenue;
	@Basic
	@Column(name = "ACCOUNT_RECEIVABLE_TO_NET_INCOME", precision = 20, scale = 5)
	private Double accountReceivableToNetIncome;
	@Basic
	@Column(name = "ALLOWANCES_AND_PROVISIONS_TO_NET_INCOME", precision = 20, scale = 5)
	private Double allowancesAndProvisionsToNetIncome;
	@Basic
	@Column(name = "F_SCORE", precision = 20, scale = 5)
	private Double fScore;
	@Basic
	@Column(name = "C_SCORE", precision = 20, scale = 5)
	private Double cScore;
	@Basic
	@Column(name = "M_SCORE", precision = 20, scale = 5)
	private Double mScore;
	@Basic
	@Column(name = "Z_SCORE", precision = 20, scale = 5)
	private Double zScore;
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
	public Double getNetRevenue() {
		return netRevenue;
	}
	public void setNetRevenue(Double netRevenue) {
		this.netRevenue = netRevenue;
	}
	public Double getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}
	public Double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}
	public Double getShareSOustanding() {
		return shareSOustanding;
	}
	public void setShareSOustanding(Double shareSOustanding) {
		this.shareSOustanding = shareSOustanding;
	}
	public Double getEps() {
		return eps;
	}
	public void setEps(Double eps) {
		this.eps = eps;
	}
	public Double getBookValue() {
		return bookValue;
	}
	public void setBookValue(Double bookValue) {
		this.bookValue = bookValue;
	}
	public Double getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Double getDayys() {
		return dayys;
	}
	public void setDayys(Double dayys) {
		this.dayys = dayys;
	}
	public Double getCapex() {
		return capex;
	}
	public void setCapex(Double capex) {
		this.capex = capex;
	}
	public Double getFcf() {
		return fcf;
	}
	public void setFcf(Double fcf) {
		this.fcf = fcf;
	}
	public Double getEbit() {
		return ebit;
	}
	public void setEbit(Double ebit) {
		this.ebit = ebit;
	}
	public Double getEbitda() {
		return ebitda;
	}
	public void setEbitda(Double ebitda) {
		this.ebitda = ebitda;
	}
	public Double getNnwc() {
		return nnwc;
	}
	public void setNnwc(Double nnwc) {
		this.nnwc = nnwc;
	}
	public Double getNetWorkingCapital() {
		return netWorkingCapital;
	}
	public void setNetWorkingCapital(Double netWorkingCapital) {
		this.netWorkingCapital = netWorkingCapital;
	}
	public Double getEv() {
		return ev;
	}
	public void setEv(Double ev) {
		this.ev = ev;
	}
	public Double getMarketCapital() {
		return marketCapital;
	}
	public void setMarketCapital(Double marketCapital) {
		this.marketCapital = marketCapital;
	}
	public Double getNetRevenueYoy() {
		return netRevenueYoy;
	}
	public void setNetRevenueYoy(Double netRevenueYoy) {
		this.netRevenueYoy = netRevenueYoy;
	}
	public Double getGrossProfitYoy() {
		return grossProfitYoy;
	}
	public void setGrossProfitYoy(Double grossProfitYoy) {
		this.grossProfitYoy = grossProfitYoy;
	}
	public Double getEpsYoy() {
		return epsYoy;
	}
	public void setEpsYoy(Double epsYoy) {
		this.epsYoy = epsYoy;
	}
	public Double getEbitdaYoy() {
		return ebitdaYoy;
	}
	public void setEbitdaYoy(Double ebitdaYoy) {
		this.ebitdaYoy = ebitdaYoy;
	}
	public Double getDebtYoy() {
		return debtYoy;
	}
	public void setDebtYoy(Double debtYoy) {
		this.debtYoy = debtYoy;
	}
	public Double getEquityYoy() {
		return equityYoy;
	}
	public void setEquityYoy(Double equityYoy) {
		this.equityYoy = equityYoy;
	}
	public Double getMarketCapitalYoy() {
		return marketCapitalYoy;
	}
	public void setMarketCapitalYoy(Double marketCapitalYoy) {
		this.marketCapitalYoy = marketCapitalYoy;
	}
	public Double getTotalAssetsYoy() {
		return totalAssetsYoy;
	}
	public void setTotalAssetsYoy(Double totalAssetsYoy) {
		this.totalAssetsYoy = totalAssetsYoy;
	}
	public Double getpE() {
		return pE;
	}
	public void setpE(Double pE) {
		this.pE = pE;
	}
	public Double getPeg() {
		return peg;
	}
	public void setPeg(Double peg) {
		this.peg = peg;
	}
	public Double getpB() {
		return pB;
	}
	public void setpB(Double pB) {
		this.pB = pB;
	}
	public Double getpS() {
		return pS;
	}
	public void setpS(Double pS) {
		this.pS = pS;
	}
	public Double getEvEbitda() {
		return evEbitda;
	}
	public void setEvEbitda(Double evEbitda) {
		this.evEbitda = evEbitda;
	}
	public Double getEvEbit() {
		return evEbit;
	}
	public void setEvEbit(Double evEbit) {
		this.evEbit = evEbit;
	}
	public Double getEvFcf() {
		return evFcf;
	}
	public void setEvFcf(Double evFcf) {
		this.evFcf = evFcf;
	}
	public Double getRevFcf() {
		return revFcf;
	}
	public void setRevFcf(Double revFcf) {
		this.revFcf = revFcf;
	}
	public Double getMcCfo() {
		return mcCfo;
	}
	public void setMcCfo(Double mcCfo) {
		this.mcCfo = mcCfo;
	}
	public Double getMcNwc() {
		return mcNwc;
	}
	public void setMcNwc(Double mcNwc) {
		this.mcNwc = mcNwc;
	}
	public Double getFcff() {
		return fcff;
	}
	public void setFcff(Double fcff) {
		this.fcff = fcff;
	}
	public Double getFcfe() {
		return fcfe;
	}
	public void setFcfe(Double fcfe) {
		this.fcfe = fcfe;
	}
	public Double getCapexRev() {
		return capexRev;
	}
	public void setCapexRev(Double capexRev) {
		this.capexRev = capexRev;
	}
	public Double getRoic() {
		return roic;
	}
	public void setRoic(Double roic) {
		this.roic = roic;
	}
	public Double getRoce() {
		return roce;
	}
	public void setRoce(Double roce) {
		this.roce = roce;
	}
	public Double getRoe() {
		return roe;
	}
	public void setRoe(Double roe) {
		this.roe = roe;
	}
	public Double getRoa() {
		return roa;
	}
	public void setRoa(Double roa) {
		this.roa = roa;
	}
	public Double getGrossProfitMargin() {
		return grossProfitMargin;
	}
	public void setGrossProfitMargin(Double grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}
	public Double getOperatingProfitMargin() {
		return operatingProfitMargin;
	}
	public void setOperatingProfitMargin(Double operatingProfitMargin) {
		this.operatingProfitMargin = operatingProfitMargin;
	}
	public Double getPretaxProfitMargin() {
		return pretaxProfitMargin;
	}
	public void setPretaxProfitMargin(Double pretaxProfitMargin) {
		this.pretaxProfitMargin = pretaxProfitMargin;
	}
	public Double getNetProfitMargin() {
		return netProfitMargin;
	}
	public void setNetProfitMargin(Double netProfitMargin) {
		this.netProfitMargin = netProfitMargin;
	}
	public Double getDivYield() {
		return divYield;
	}
	public void setDivYield(Double divYield) {
		this.divYield = divYield;
	}
	public Double getEbitRev() {
		return ebitRev;
	}
	public void setEbitRev(Double ebitRev) {
		this.ebitRev = ebitRev;
	}
	public Double getEbitdaRev() {
		return ebitdaRev;
	}
	public void setEbitdaRev(Double ebitdaRev) {
		this.ebitdaRev = ebitdaRev;
	}
	public Double getSalesToTotalAssets() {
		return salesToTotalAssets;
	}
	public void setSalesToTotalAssets(Double salesToTotalAssets) {
		this.salesToTotalAssets = salesToTotalAssets;
	}
	public Double getReceivableTurnover() {
		return receivableTurnover;
	}
	public void setReceivableTurnover(Double receivableTurnover) {
		this.receivableTurnover = receivableTurnover;
	}
	public Double getPayableTurnover() {
		return payableTurnover;
	}
	public void setPayableTurnover(Double payableTurnover) {
		this.payableTurnover = payableTurnover;
	}
	public Double getInventoryTurnover() {
		return inventoryTurnover;
	}
	public void setInventoryTurnover(Double inventoryTurnover) {
		this.inventoryTurnover = inventoryTurnover;
	}
	public Double getDebtToAssetsRatio() {
		return debtToAssetsRatio;
	}
	public void setDebtToAssetsRatio(Double debtToAssetsRatio) {
		this.debtToAssetsRatio = debtToAssetsRatio;
	}
	public Double getDebtToEquityRatio() {
		return debtToEquityRatio;
	}
	public void setDebtToEquityRatio(Double debtToEquityRatio) {
		this.debtToEquityRatio = debtToEquityRatio;
	}
	public Double getLongTimeDebtTotalCapitalazion() {
		return longTimeDebtTotalCapitalazion;
	}
	public void setLongTimeDebtTotalCapitalazion(Double longTimeDebtTotalCapitalazion) {
		this.longTimeDebtTotalCapitalazion = longTimeDebtTotalCapitalazion;
	}
	public Double getInterestCoverage() {
		return interestCoverage;
	}
	public void setInterestCoverage(Double interestCoverage) {
		this.interestCoverage = interestCoverage;
	}
	public Double getCurrentRatio() {
		return currentRatio;
	}
	public void setCurrentRatio(Double currentRatio) {
		this.currentRatio = currentRatio;
	}
	public Double getQuickRatio() {
		return quickRatio;
	}
	public void setQuickRatio(Double quickRatio) {
		this.quickRatio = quickRatio;
	}
	public Double getCashRatio() {
		return cashRatio;
	}
	public void setCashRatio(Double cashRatio) {
		this.cashRatio = cashRatio;
	}
	public Double getAccountReceivableToRevenue() {
		return accountReceivableToRevenue;
	}
	public void setAccountReceivableToRevenue(Double accountReceivableToRevenue) {
		this.accountReceivableToRevenue = accountReceivableToRevenue;
	}
	public Double getAccountReceivableToNetIncome() {
		return accountReceivableToNetIncome;
	}
	public void setAccountReceivableToNetIncome(Double accountReceivableToNetIncome) {
		this.accountReceivableToNetIncome = accountReceivableToNetIncome;
	}
	public Double getAllowancesAndProvisionsToNetIncome() {
		return allowancesAndProvisionsToNetIncome;
	}
	public void setAllowancesAndProvisionsToNetIncome(Double allowancesAndProvisionsToNetIncome) {
		this.allowancesAndProvisionsToNetIncome = allowancesAndProvisionsToNetIncome;
	}
	public Double getfScore() {
		return fScore;
	}
	public void setfScore(Double fScore) {
		this.fScore = fScore;
	}
	public Double getcScore() {
		return cScore;
	}
	public void setcScore(Double cScore) {
		this.cScore = cScore;
	}
	public Double getmScore() {
		return mScore;
	}
	public void setmScore(Double mScore) {
		this.mScore = mScore;
	}
	public Double getzScore() {
		return zScore;
	}
	public void setzScore(Double zScore) {
		this.zScore = zScore;
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
}