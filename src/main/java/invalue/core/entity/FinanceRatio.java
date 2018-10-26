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
@Table(name = "finance_ratio", uniqueConstraints = {@UniqueConstraint(columnNames = "CODE") })
public class FinanceRatio implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "finance_ratio_generator")
	@SequenceGenerator(name="finance_ratio_generator", sequenceName = "finance_ratio_seq", allocationSize=1)
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
	@Column(name = "Y_Q_R", length = 1)
	private String YQR;	
	
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
	@Basic
	@Column(name = "NIM", precision = 20, scale = 5)
	private Double nim;
	@Basic
	@Column(name = "LDR", precision = 20, scale = 5)
	private Double ldr;
	@Basic
	@Column(name = "TANG_TRUONG_TIN_DUNG", precision = 20, scale = 5)
	private Double tangTruongTinDung;
	@Basic
	@Column(name = "TANG_TRUONG_TIEN_GUI", precision = 20, scale = 5)
	private Double tangTruongTienGui;
	@Basic
	@Column(name = "BENJAMIN_GRAHAM_NET_NETS", precision = 20, scale = 5)
	private Double benjaminGrahamNetNets;
	@Basic
	@Column(name = "BENJAMIN_GRAHAM_NCAV_BARGAIN", precision = 20, scale = 5)
	private Double benjaminGrahamNcavBargain;
	@Basic
	@Column(name = "CANSLIM_NOT_UPCOM", precision = 20, scale = 5)
	private Double canslimNotUpcom;
	@Basic
	@Column(name = "PHILIP_FISHER_GROWTH_BUSINESS_INSIDER_Y", precision = 20, scale = 5)
	private Double philipFisherGrowthBusinessInsiderY;
	@Basic
	@Column(name = "JOHN_NEFF_VALUE", precision = 20, scale = 5)
	private Double johnNeffValue;
	@Basic
	@Column(name = "PETER_LYNCH_GROWTH", precision = 20, scale = 5)
	private Double peterLynchGrowth;
	@Basic
	@Column(name = "GRAHAM_CHECKLIST_Y", precision = 20, scale = 5)
	private Double grahamChecklistY;
	@Basic
	@Column(name = "SEARCH1", precision = 20, scale = 5)
	private Double search1;
	@Basic
	@Column(name = "SEARCH2", precision = 20, scale = 5)
	private Double search2;
	@Basic
	@Column(name = "SEARCH3", precision = 20, scale = 5)
	private Double search3;
	@Basic
	@Column(name = "SEARCH4", precision = 20, scale = 5)
	private Double search4;
	@Basic
	@Column(name = "SEARCH5", precision = 20, scale = 5)
	private Double search5;
	@Basic
	@Column(name = "SEARCH6", precision = 20, scale = 5)
	private Double search6;
	@Basic
	@Column(name = "SEARCH7", precision = 20, scale = 5)
	private Double search7;
	@Basic
	@Column(name = "SEARCH8", precision = 20, scale = 5)
	private Double search8;
	@Basic
	@Column(name = "SEARCH9", precision = 20, scale = 5)
	private Double search9;
	@Basic
	@Column(name = "SEARCH10", precision = 20, scale = 5)
	private Double search10;
	@Basic
	@Column(name = "FOR_COUTING", precision = 20, scale = 5)
	private Double forCouting;
	@Basic
	@Column(name = "CANSLIM_NI", precision = 20, scale = 5)
	private Double canslimNi;

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
	public Double getPE() {
		return pE;
	}
	public void setPE(Double pE) {
		this.pE = pE;
	}
	public Double getPeg() {
		return peg;
	}
	public void setPeg(Double peg) {
		this.peg = peg;
	}
	public Double getPB() {
		return pB;
	}
	public void setPB(Double pB) {
		this.pB = pB;
	}
	public Double getPS() {
		return pS;
	}
	public void setPS(Double pS) {
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
	public Double getFScore() {
		return fScore;
	}
	public void setFScore(Double fScore) {
		this.fScore = fScore;
	}
	public Double getCScore() {
		return cScore;
	}
	public void setCScore(Double cScore) {
		this.cScore = cScore;
	}
	public Double getMScore() {
		return mScore;
	}
	public void setMScore(Double mScore) {
		this.mScore = mScore;
	}
	public Double getZScore() {
		return zScore;
	}
	public void setZScore(Double zScore) {
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
	public Double getpE() {
		return pE;
	}
	public void setpE(Double pE) {
		this.pE = pE;
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
	public String getYQR() {
		return YQR;
	}
	public void setYQR(String yQR) {
		YQR = yQR;
	}
	public Double getNim() {
		return nim;
	}
	public void setNim(Double nim) {
		this.nim = nim;
	}
	public Double getLdr() {
		return ldr;
	}
	public void setLdr(Double ldr) {
		this.ldr = ldr;
	}
	public Double getTangTruongTinDung() {
		return tangTruongTinDung;
	}
	public void setTangTruongTinDung(Double tangTruongTinDung) {
		this.tangTruongTinDung = tangTruongTinDung;
	}
	public Double getTangTruongTienGui() {
		return tangTruongTienGui;
	}
	public void setTangTruongTienGui(Double tangTruongTienGui) {
		this.tangTruongTienGui = tangTruongTienGui;
	}
	public Double getBenjaminGrahamNetNets() {
		return benjaminGrahamNetNets;
	}
	public void setBenjaminGrahamNetNets(Double benjaminGrahamNetNets) {
		this.benjaminGrahamNetNets = benjaminGrahamNetNets;
	}
	public Double getBenjaminGrahamNcavBargain() {
		return benjaminGrahamNcavBargain;
	}
	public void setBenjaminGrahamNcavBargain(Double benjaminGrahamNcavBargain) {
		this.benjaminGrahamNcavBargain = benjaminGrahamNcavBargain;
	}
	public Double getCanslimNotUpcom() {
		return canslimNotUpcom;
	}
	public void setCanslimNotUpcom(Double canslimNotUpcom) {
		this.canslimNotUpcom = canslimNotUpcom;
	}
	public Double getPhilipFisherGrowthBusinessInsiderY() {
		return philipFisherGrowthBusinessInsiderY;
	}
	public void setPhilipFisherGrowthBusinessInsiderY(Double philipFisherGrowthBusinessInsiderY) {
		this.philipFisherGrowthBusinessInsiderY = philipFisherGrowthBusinessInsiderY;
	}
	public Double getJohnNeffValue() {
		return johnNeffValue;
	}
	public void setJohnNeffValue(Double johnNeffValue) {
		this.johnNeffValue = johnNeffValue;
	}
	public Double getPeterLynchGrowth() {
		return peterLynchGrowth;
	}
	public void setPeterLynchGrowth(Double peterLynchGrowth) {
		this.peterLynchGrowth = peterLynchGrowth;
	}
	public Double getGrahamChecklistY() {
		return grahamChecklistY;
	}
	public void setGrahamChecklistY(Double grahamChecklistY) {
		this.grahamChecklistY = grahamChecklistY;
	}
	public Double getSearch1() {
		return search1;
	}
	public void setSearch1(Double search1) {
		this.search1 = search1;
	}
	public Double getSearch2() {
		return search2;
	}
	public void setSearch2(Double search2) {
		this.search2 = search2;
	}
	public Double getSearch3() {
		return search3;
	}
	public void setSearch3(Double search3) {
		this.search3 = search3;
	}
	public Double getSearch4() {
		return search4;
	}
	public void setSearch4(Double search4) {
		this.search4 = search4;
	}
	public Double getSearch5() {
		return search5;
	}
	public void setSearch5(Double search5) {
		this.search5 = search5;
	}
	public Double getSearch6() {
		return search6;
	}
	public void setSearch6(Double search6) {
		this.search6 = search6;
	}
	public Double getSearch7() {
		return search7;
	}
	public void setSearch7(Double search7) {
		this.search7 = search7;
	}
	public Double getSearch8() {
		return search8;
	}
	public void setSearch8(Double search8) {
		this.search8 = search8;
	}
	public Double getSearch9() {
		return search9;
	}
	public void setSearch9(Double search9) {
		this.search9 = search9;
	}
	public Double getSearch10() {
		return search10;
	}
	public void setSearch10(Double search10) {
		this.search10 = search10;
	}
	public Double getForCouting() {
		return forCouting;
	}
	public void setForCouting(Double forCouting) {
		this.forCouting = forCouting;
	}
	public Double getCanslimNi() {
		return canslimNi;
	}
	public void setCanslimNi(Double canslimNi) {
		this.canslimNi = canslimNi;
	}
	
}