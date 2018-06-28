package invalue.core.vo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class  ReportVO implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 7656055076958852454L;
	private Integer id;
	
	private String code;
	private Integer type;
	
	private Integer status;

	private Date createdTime;

	private Date updatedTime;
	
	private String timeString;	
	
	private Double netRevenue;
	private Double grossProfit;
	private Double netIncome;
	private Double shareSOustanding;
	private Double eps;
	private Double bookValue;
	private Double marketPrice;
	private Double dayys;
	private Double capex;
	private Double fcf;
	private Double ebit;
	private Double ebitda;
	private Double nnwc;
	private Double netWorkingCapital;
	private Double ev;
	private Double marketCapital;
	private Double netRevenueYoy;
	private Double grossProfitYoy;
	private Double epsYoy;
	private Double ebitdaYoy;
	private Double debtYoy;
	private Double equityYoy;
	private Double marketCapitalYoy;
	private Double totalAssetsYoy;
	private Double pE;
	private Double peg;
	private Double pB;
	private Double pS;
	private Double evEbitda;
	private Double evEbit;
	private Double evFcf;
	private Double revFcf;
	private Double mcCfo;
	private Double mcNwc;
	private Double fcff;
	private Double fcfe;
	private Double capexRev;
	private Double roic;
	private Double roce;
	private Double roe;
	private Double roa;
	private Double grossProfitMargin;
	private Double operatingProfitMargin;
	private Double pretaxProfitMargin;
	private Double netProfitMargin;
	private Double divYield;
	private Double ebitRev;
	private Double ebitdaRev;
	private Double salesToTotalAssets;
	private Double receivableTurnover;
	private Double payableTurnover;
	private Double inventoryTurnover;
	private Double debtToAssetsRatio;
	private Double debtToEquityRatio;
	private Double longTimeDebtTotalCapitalazion;
	private Double interestCoverage;
	private Double currentRatio;
	private Double quickRatio;
	private Double cashRatio;
	private Double accountReceivableToRevenue;
	private Double accountReceivableToNetIncome;
	private Double allowancesAndProvisionsToNetIncome;
	private Double fScore;
	private Double cScore;
	private Double mScore;
	private Double zScore;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public void setLongTimeDebtTotalCapitalazion(
			Double longTimeDebtTotalCapitalazion) {
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
	public void setAllowancesAndProvisionsToNetIncome(
			Double allowancesAndProvisionsToNetIncome) {
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
}