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
@Table(name = "security_report", uniqueConstraints = {@UniqueConstraint(columnNames = "CODE") })
public class SecurityReport implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "security_report_generator")
	@SequenceGenerator(name="security_report_generator", sequenceName = "security_report_seq", allocationSize=1)
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
	
	@Column(name = "Y_Q_R", length = 1)
	private String YQR;	
	
	@Basic
	@Column(name = "CURRENT_ASSETS", precision = 20, scale = 5)
	private Double currentAssets;
	@Basic
	@Column(name = "FINANCE_ASSETS", precision = 20, scale = 5)
	private Double financeAssets;
	@Basic
	@Column(name = "CASH_AND_CASH_EQUIVALENTS", precision = 20, scale = 5)
	private Double cashAndCashEquivalents;
	@Basic
	@Column(name = "FINANCE_ASSETS_AT_FAIR_VALUE_THOUGH_PROFIT_OR_LOSS", precision = 20, scale = 5)
	private Double financeAssetsAtFairValueThoughProfitOrLoss;
	@Basic
	@Column(name = "HELD_TO_MATURITY_INVESTMENT", precision = 20, scale = 5)
	private Double heldToMaturityInvestment;
	@Basic
	@Column(name = "LOANS", precision = 20, scale = 5)
	private Double loans;
	@Basic
	@Column(name = "AVAILABLE_FOR_SALE_FINANCE_ASSET", precision = 20, scale = 5)
	private Double availableForSaleFinanceAsset;
	@Basic
	@Column(name = "SHORT_TERM_ACCOUNT_RECEIVABLE", precision = 20, scale = 5)
	private Double shortTermAccountReceivable;
	@Basic
	@Column(name = "OTHER_CURRENT_ASSETS", precision = 20, scale = 5)
	private Double otherCurrentAssets;
	@Basic
	@Column(name = "NON_CURRENT_ASSETS", precision = 20, scale = 5)
	private Double nonCurrentAssets;
	@Basic
	@Column(name = "ACCOUNT_RECEIVABLE_LONG_TERM", precision = 20, scale = 5)
	private Double accountReceivableLongTerm;
	@Basic
	@Column(name = "FIXED_ASSETS", precision = 20, scale = 5)
	private Double fixedAssets;
	@Basic
	@Column(name = "TANGIBLE_FIXED_ASSETS", precision = 20, scale = 5)
	private Double tangibleFixedAssets;
	@Basic
	@Column(name = "FINANCE_TANGIBLE_FIXED_ASSETS", precision = 20, scale = 5)
	private Double financeTangibleFixedAssets;
	@Basic
	@Column(name = "INTANGIBLE_FIXED_ASSETS", precision = 20, scale = 5)
	private Double intangibleFixedAssets;
	@Basic
	@Column(name = "INVESTMENT_PROPERTY", precision = 20, scale = 5)
	private Double investmentProperty;
	@Basic
	@Column(name = "CONTRUCTION_IN_PROGRESS", precision = 20, scale = 5)
	private Double contructionInProgress;
	@Basic
	@Column(name = "LONG_TERM_INVESTMENT", precision = 20, scale = 5)
	private Double longTermInvestment;
	@Basic
	@Column(name = "GOOD_WILL", precision = 20, scale = 5)
	private Double goodWill;
	@Basic
	@Column(name = "OTHER_LONG_TERM_ASSETS", precision = 20, scale = 5)
	private Double otherLongTermAssets;
	@Basic
	@Column(name = "TOTAL_ASSETS", precision = 20, scale = 5)
	private Double totalAssets;
	@Basic
	@Column(name = "LIABILITIES", precision = 20, scale = 5)
	private Double liabilities;
	@Basic
	@Column(name = "CURRENT_LIABILITIES", precision = 20, scale = 5)
	private Double currentLiabilities;
	@Basic
	@Column(name = "SHORT_TERM_BORROWINGS_AND_LIABILITIES", precision = 20, scale = 5)
	private Double shortTermBorrowingsAndLiabilities;
	@Basic
	@Column(name = "SHORT_TERM_TRADE_PAYABLE", precision = 20, scale = 5)
	private Double shortTermTradePayable;
	@Basic
	@Column(name = "LONG_TERM_LIABILITIES", precision = 20, scale = 5)
	private Double longTermLiabilities;
	@Basic
	@Column(name = "NET_REVENUE", precision = 20, scale = 5)
	private Double netRevenue;
	@Basic
	@Column(name = "COST_OF_SALES", precision = 20, scale = 5)
	private Double costOfSales;
	@Basic
	@Column(name = "GROSS_PROFIT", precision = 20, scale = 5)
	private Double grossProfit;
	@Basic
	@Column(name = "FINANCIAL_INCOME", precision = 20, scale = 5)
	private Double financialIncome;
	@Basic
	@Column(name = "FINANCIAL_EXPENSES", precision = 20, scale = 5)
	private Double financialExpenses;
	@Basic
	@Column(name = "IN_WHICH_INTEREST_EXPENSE", precision = 20, scale = 5)
	private Double inWhichInterestExpense;
	@Basic
	@Column(name = "SHARE_OF_PROFIT_IN_ASSOCIATES", precision = 20, scale = 5)
	private Double shareOfProfitInAssociates;
	@Basic
	@Column(name = "SELLING_EXPENSES", precision = 20, scale = 5)
	private Double sellingExpenses;
	@Basic
	@Column(name = "GENERAL_AND_ADMINISTRATION_EXPENSES", precision = 20, scale = 5)
	private Double generalAndAdministrationExpenses;
	@Basic
	@Column(name = "NET_OPERATING_PROFIT", precision = 20, scale = 5)
	private Double netOperatingProfit;
	@Basic
	@Column(name = "OTHER_INCOME", precision = 20, scale = 5)
	private Double otherIncome;
	@Basic
	@Column(name = "PROFIT_BEFORE_TAX", precision = 20, scale = 5)
	private Double profitBeforeTax;
	@Basic
	@Column(name = "INCOME_TAX_EXPENSE", precision = 20, scale = 5)
	private Double incomeTaxExpense;
	@Basic
	@Column(name = "NET_PROFIT_AFTER_TAX", precision = 20, scale = 5)
	private Double netProfitAfterTax;
	@Basic
	@Column(name = "MINORITY_INTEREST", precision = 20, scale = 5)
	private Double minorityInterest;
	@Basic
	@Column(name = "NET_INCOME", precision = 20, scale = 5)
	private Double netIncome;
	@Basic
	@Column(name = "DEPRECIATION", precision = 20, scale = 5)
	private Double depreciation;
	@Basic
	@Column(name = "ALLOWANCES_AND_PROVISIONS", precision = 20, scale = 5)
	private Double allowancesAndProvisions;
	@Basic
	@Column(name = "NET_CASH_FLOWS_FROM_OPERATING_ACTIVITIES", precision = 20, scale = 5)
	private Double netCashFlowsFromOperatingActivities;
	@Basic
	@Column(name = "NET_CASH_FLOWS_FROM_INVESTING_ACTIVITIES", precision = 20, scale = 5)
	private Double netCashFlowsFromInvestingActivities;
	@Basic
	@Column(name = "NET_CASH_FLOWS_FROM_FINANCING_ACTIVITIES", precision = 20, scale = 5)
	private Double netCashFlowsFromFinancingActivities;
	@Basic
	@Column(name = "PAYMENTS_OF_DIVIDENDS", precision = 20, scale = 5)
	private Double paymentsOfDividends;
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
	public Double getCurrentAssets() {
		return currentAssets;
	}
	public void setCurrentAssets(Double currentAssets) {
		this.currentAssets = currentAssets;
	}
	public Double getFinanceAssets() {
		return financeAssets;
	}
	public void setFinanceAssets(Double financeAssets) {
		this.financeAssets = financeAssets;
	}
	public Double getCashAndCashEquivalents() {
		return cashAndCashEquivalents;
	}
	public void setCashAndCashEquivalents(Double cashAndCashEquivalents) {
		this.cashAndCashEquivalents = cashAndCashEquivalents;
	}
	public Double getFinanceAssetsAtFairValueThoughProfitOrLoss() {
		return financeAssetsAtFairValueThoughProfitOrLoss;
	}
	public void setFinanceAssetsAtFairValueThoughProfitOrLoss(Double financeAssetsAtFairValueThoughProfitOrLoss) {
		this.financeAssetsAtFairValueThoughProfitOrLoss = financeAssetsAtFairValueThoughProfitOrLoss;
	}
	public Double getHeldToMaturityInvestment() {
		return heldToMaturityInvestment;
	}
	public void setHeldToMaturityInvestment(Double heldToMaturityInvestment) {
		this.heldToMaturityInvestment = heldToMaturityInvestment;
	}
	public Double getLoans() {
		return loans;
	}
	public void setLoans(Double loans) {
		this.loans = loans;
	}
	public Double getAvailableForSaleFinanceAsset() {
		return availableForSaleFinanceAsset;
	}
	public void setAvailableForSaleFinanceAsset(Double availableForSaleFinanceAsset) {
		this.availableForSaleFinanceAsset = availableForSaleFinanceAsset;
	}
	public Double getShortTermAccountReceivable() {
		return shortTermAccountReceivable;
	}
	public void setShortTermAccountReceivable(Double shortTermAccountReceivable) {
		this.shortTermAccountReceivable = shortTermAccountReceivable;
	}
	public Double getOtherCurrentAssets() {
		return otherCurrentAssets;
	}
	public void setOtherCurrentAssets(Double otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}
	public Double getNonCurrentAssets() {
		return nonCurrentAssets;
	}
	public void setNonCurrentAssets(Double nonCurrentAssets) {
		this.nonCurrentAssets = nonCurrentAssets;
	}
	public Double getAccountReceivableLongTerm() {
		return accountReceivableLongTerm;
	}
	public void setAccountReceivableLongTerm(Double accountReceivableLongTerm) {
		this.accountReceivableLongTerm = accountReceivableLongTerm;
	}
	public Double getFixedAssets() {
		return fixedAssets;
	}
	public void setFixedAssets(Double fixedAssets) {
		this.fixedAssets = fixedAssets;
	}
	public Double getTangibleFixedAssets() {
		return tangibleFixedAssets;
	}
	public void setTangibleFixedAssets(Double tangibleFixedAssets) {
		this.tangibleFixedAssets = tangibleFixedAssets;
	}
	public Double getFinanceTangibleFixedAssets() {
		return financeTangibleFixedAssets;
	}
	public void setFinanceTangibleFixedAssets(Double financeTangibleFixedAssets) {
		this.financeTangibleFixedAssets = financeTangibleFixedAssets;
	}
	public Double getIntangibleFixedAssets() {
		return intangibleFixedAssets;
	}
	public void setIntangibleFixedAssets(Double intangibleFixedAssets) {
		this.intangibleFixedAssets = intangibleFixedAssets;
	}
	public Double getInvestmentProperty() {
		return investmentProperty;
	}
	public void setInvestmentProperty(Double investmentProperty) {
		this.investmentProperty = investmentProperty;
	}
	public Double getContructionInProgress() {
		return contructionInProgress;
	}
	public void setContructionInProgress(Double contructionInProgress) {
		this.contructionInProgress = contructionInProgress;
	}
	public Double getLongTermInvestment() {
		return longTermInvestment;
	}
	public void setLongTermInvestment(Double longTermInvestment) {
		this.longTermInvestment = longTermInvestment;
	}
	public Double getGoodWill() {
		return goodWill;
	}
	public void setGoodWill(Double goodWill) {
		this.goodWill = goodWill;
	}
	public Double getOtherLongTermAssets() {
		return otherLongTermAssets;
	}
	public void setOtherLongTermAssets(Double otherLongTermAssets) {
		this.otherLongTermAssets = otherLongTermAssets;
	}
	public Double getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}
	public Double getLiabilities() {
		return liabilities;
	}
	public void setLiabilities(Double liabilities) {
		this.liabilities = liabilities;
	}
	public Double getCurrentLiabilities() {
		return currentLiabilities;
	}
	public void setCurrentLiabilities(Double currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}
	public Double getShortTermBorrowingsAndLiabilities() {
		return shortTermBorrowingsAndLiabilities;
	}
	public void setShortTermBorrowingsAndLiabilities(Double shortTermBorrowingsAndLiabilities) {
		this.shortTermBorrowingsAndLiabilities = shortTermBorrowingsAndLiabilities;
	}
	public Double getShortTermTradePayable() {
		return shortTermTradePayable;
	}
	public void setShortTermTradePayable(Double shortTermTradePayable) {
		this.shortTermTradePayable = shortTermTradePayable;
	}
	public Double getLongTermLiabilities() {
		return longTermLiabilities;
	}
	public void setLongTermLiabilities(Double longTermLiabilities) {
		this.longTermLiabilities = longTermLiabilities;
	}
	public Double getNetRevenue() {
		return netRevenue;
	}
	public void setNetRevenue(Double netRevenue) {
		this.netRevenue = netRevenue;
	}
	public Double getCostOfSales() {
		return costOfSales;
	}
	public void setCostOfSales(Double costOfSales) {
		this.costOfSales = costOfSales;
	}
	public Double getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}
	public Double getFinancialIncome() {
		return financialIncome;
	}
	public void setFinancialIncome(Double financialIncome) {
		this.financialIncome = financialIncome;
	}
	public Double getFinancialExpenses() {
		return financialExpenses;
	}
	public void setFinancialExpenses(Double financialExpenses) {
		this.financialExpenses = financialExpenses;
	}
	public Double getInWhichInterestExpense() {
		return inWhichInterestExpense;
	}
	public void setInWhichInterestExpense(Double inWhichInterestExpense) {
		this.inWhichInterestExpense = inWhichInterestExpense;
	}
	public Double getShareOfProfitInAssociates() {
		return shareOfProfitInAssociates;
	}
	public void setShareOfProfitInAssociates(Double shareOfProfitInAssociates) {
		this.shareOfProfitInAssociates = shareOfProfitInAssociates;
	}
	public Double getSellingExpenses() {
		return sellingExpenses;
	}
	public void setSellingExpenses(Double sellingExpenses) {
		this.sellingExpenses = sellingExpenses;
	}
	public Double getGeneralAndAdministrationExpenses() {
		return generalAndAdministrationExpenses;
	}
	public void setGeneralAndAdministrationExpenses(Double generalAndAdministrationExpenses) {
		this.generalAndAdministrationExpenses = generalAndAdministrationExpenses;
	}
	public Double getNetOperatingProfit() {
		return netOperatingProfit;
	}
	public void setNetOperatingProfit(Double netOperatingProfit) {
		this.netOperatingProfit = netOperatingProfit;
	}
	public Double getOtherIncome() {
		return otherIncome;
	}
	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}
	public Double getProfitBeforeTax() {
		return profitBeforeTax;
	}
	public void setProfitBeforeTax(Double profitBeforeTax) {
		this.profitBeforeTax = profitBeforeTax;
	}
	public Double getIncomeTaxExpense() {
		return incomeTaxExpense;
	}
	public void setIncomeTaxExpense(Double incomeTaxExpense) {
		this.incomeTaxExpense = incomeTaxExpense;
	}
	public Double getNetProfitAfterTax() {
		return netProfitAfterTax;
	}
	public void setNetProfitAfterTax(Double netProfitAfterTax) {
		this.netProfitAfterTax = netProfitAfterTax;
	}
	public Double getMinorityInterest() {
		return minorityInterest;
	}
	public void setMinorityInterest(Double minorityInterest) {
		this.minorityInterest = minorityInterest;
	}
	public Double getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}
	public Double getDepreciation() {
		return depreciation;
	}
	public void setDepreciation(Double depreciation) {
		this.depreciation = depreciation;
	}
	public Double getAllowancesAndProvisions() {
		return allowancesAndProvisions;
	}
	public void setAllowancesAndProvisions(Double allowancesAndProvisions) {
		this.allowancesAndProvisions = allowancesAndProvisions;
	}
	public Double getNetCashFlowsFromOperatingActivities() {
		return netCashFlowsFromOperatingActivities;
	}
	public void setNetCashFlowsFromOperatingActivities(Double netCashFlowsFromOperatingActivities) {
		this.netCashFlowsFromOperatingActivities = netCashFlowsFromOperatingActivities;
	}
	public Double getNetCashFlowsFromInvestingActivities() {
		return netCashFlowsFromInvestingActivities;
	}
	public void setNetCashFlowsFromInvestingActivities(Double netCashFlowsFromInvestingActivities) {
		this.netCashFlowsFromInvestingActivities = netCashFlowsFromInvestingActivities;
	}
	public Double getNetCashFlowsFromFinancingActivities() {
		return netCashFlowsFromFinancingActivities;
	}
	public void setNetCashFlowsFromFinancingActivities(Double netCashFlowsFromFinancingActivities) {
		this.netCashFlowsFromFinancingActivities = netCashFlowsFromFinancingActivities;
	}
	public Double getPaymentsOfDividends() {
		return paymentsOfDividends;
	}
	public void setPaymentsOfDividends(Double paymentsOfDividends) {
		this.paymentsOfDividends = paymentsOfDividends;
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
	
	public String getYQR() {
		return YQR;
	}
	
	public void setYQR(String yQR) {
		YQR = yQR;
	}
}