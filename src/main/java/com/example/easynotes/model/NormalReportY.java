package com.example.easynotes.model;

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
@Table(name = "normal_report_y", uniqueConstraints = {@UniqueConstraint(columnNames = "CODE") })
public class NormalReportY implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
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
	@Column(name = "CURRENT_ASSET", precision = 20, scale = 5)
	private Double currentAsset;
	@Basic
	@Column(name = "CASH_AND_CASH_EQUIVALENTS", precision = 20, scale = 5)
	private Double cashAndCashEquivalents;
	@Basic
	@Column(name = "SHORT_TERM_INVESTMENTS", precision = 20, scale = 5)
	private Double shortTermInvestments;
	@Basic
	@Column(name = "ACCOUNTS_RECEIVABLE_SHORT_TERM", precision = 20, scale = 5)
	private Double accountsReceivableShortTerm;
	@Basic
	@Column(name = "INVENTORIES", precision = 20, scale = 5)
	private Double inventories;
	@Basic
	@Column(name = "OTHER_CURRENT_ASSETS", precision = 20, scale = 5)
	private Double otherCurrentAssets;
	@Basic
	@Column(name = "LONG_TERM_ASSETS", precision = 20, scale = 5)
	private Double longTermAssets;
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
	@Column(name = "CURRENT_LIABILITIES", precision = 20, scale = 5)
	private Double currentLiabilities;
	@Basic
	@Column(name = "ACCOUNT_PAYABLE_TO_SUPPLIERS", precision = 20, scale = 5)
	private Double accountPayableToSuppliers;
	@Basic
	@Column(name = "SHORT_TERM_ADVANCES_FROM_CUSTOMERS", precision = 20, scale = 5)
	private Double shortTermAdvancesFromCustomers;
	@Basic
	@Column(name = "SHORT_TERM_UNEARNED_REVENUE", precision = 20, scale = 5)
	private Double shortTermUnearnedRevenue;
	@Basic
	@Column(name = "SHORT_TERM_BORROWINGS_AND_LIABILITIES", precision = 20, scale = 5)
	private Double shortTermBorrowingsAndLiabilities;
	@Basic
	@Column(name = "OTHER_SHORT_TERM_LIABILITIES", precision = 20, scale = 5)
	private Double otherShortTermLiabilities;
	@Basic
	@Column(name = "LONG_TERM_LIABILITIES", precision = 20, scale = 5)
	private Double longTermLiabilities;
	@Basic
	@Column(name = "LONG_TERM_ACCOUNTS_PAYABLE", precision = 20, scale = 5)
	private Double longTermAccountsPayable;
	@Basic
	@Column(name = "LONG_TERMADVANCES_FROM_CUSTOMERS", precision = 20, scale = 5)
	private Double longTermadvancesFromCustomers;
	@Basic
	@Column(name = "LONG_TERM_UNEARNED_REVENUE", precision = 20, scale = 5)
	private Double longTermUnearnedRevenue;
	@Basic
	@Column(name = "LONG_TERM_BORROWINGS_AND_LIABILITIES", precision = 20, scale = 5)
	private Double longTermBorrowingsAndLiabilities;
	@Basic
	@Column(name = "OTHER_LONG_TERM_LIABILITIES", precision = 20, scale = 5)
	private Double otherLongTermLiabilities;
	@Basic
	@Column(name = "EQUITY", precision = 20, scale = 5)
	private Double equity;
	@Basic
	@Column(name = "SHARE_CAPITAL", precision = 20, scale = 5)
	private Double shareCapital;
	@Basic
	@Column(name = "SHARE_PREMIUM", precision = 20, scale = 5)
	private Double sharePremium;
	@Basic
	@Column(name = "RETAINED_PROFITS", precision = 20, scale = 5)
	private Double retainedProfits;
	@Basic
	@Column(name = "OTHER_CAPITALS", precision = 20, scale = 5)
	private Double otherCapitals;
	@Basic
	@Column(name = "NON_CONTROLLING_INTEREST", precision = 20, scale = 5)
	private Double nonControllingInterest;
	@Basic
	@Column(name = "TOTAL_RESOURCES", precision = 20, scale = 5)
	private Double totalResources;
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
	public Double getCurrentAsset() {
		return currentAsset;
	}
	public void setCurrentAsset(Double currentAsset) {
		this.currentAsset = currentAsset;
	}
	public Double getCashAndCashEquivalents() {
		return cashAndCashEquivalents;
	}
	public void setCashAndCashEquivalents(Double cashAndCashEquivalents) {
		this.cashAndCashEquivalents = cashAndCashEquivalents;
	}
	public Double getShortTermInvestments() {
		return shortTermInvestments;
	}
	public void setShortTermInvestments(Double shortTermInvestments) {
		this.shortTermInvestments = shortTermInvestments;
	}
	public Double getAccountsReceivableShortTerm() {
		return accountsReceivableShortTerm;
	}
	public void setAccountsReceivableShortTerm(Double accountsReceivableShortTerm) {
		this.accountsReceivableShortTerm = accountsReceivableShortTerm;
	}
	public Double getInventories() {
		return inventories;
	}
	public void setInventories(Double inventories) {
		this.inventories = inventories;
	}
	public Double getOtherCurrentAssets() {
		return otherCurrentAssets;
	}
	public void setOtherCurrentAssets(Double otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}
	public Double getLongTermAssets() {
		return longTermAssets;
	}
	public void setLongTermAssets(Double longTermAssets) {
		this.longTermAssets = longTermAssets;
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
	public Double getCurrentLiabilities() {
		return currentLiabilities;
	}
	public void setCurrentLiabilities(Double currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}
	public Double getAccountPayableToSuppliers() {
		return accountPayableToSuppliers;
	}
	public void setAccountPayableToSuppliers(Double accountPayableToSuppliers) {
		this.accountPayableToSuppliers = accountPayableToSuppliers;
	}
	public Double getShortTermAdvancesFromCustomers() {
		return shortTermAdvancesFromCustomers;
	}
	public void setShortTermAdvancesFromCustomers(Double shortTermAdvancesFromCustomers) {
		this.shortTermAdvancesFromCustomers = shortTermAdvancesFromCustomers;
	}
	public Double getShortTermUnearnedRevenue() {
		return shortTermUnearnedRevenue;
	}
	public void setShortTermUnearnedRevenue(Double shortTermUnearnedRevenue) {
		this.shortTermUnearnedRevenue = shortTermUnearnedRevenue;
	}
	public Double getShortTermBorrowingsAndLiabilities() {
		return shortTermBorrowingsAndLiabilities;
	}
	public void setShortTermBorrowingsAndLiabilities(Double shortTermBorrowingsAndLiabilities) {
		this.shortTermBorrowingsAndLiabilities = shortTermBorrowingsAndLiabilities;
	}
	public Double getOtherShortTermLiabilities() {
		return otherShortTermLiabilities;
	}
	public void setOtherShortTermLiabilities(Double otherShortTermLiabilities) {
		this.otherShortTermLiabilities = otherShortTermLiabilities;
	}
	public Double getLongTermLiabilities() {
		return longTermLiabilities;
	}
	public void setLongTermLiabilities(Double longTermLiabilities) {
		this.longTermLiabilities = longTermLiabilities;
	}
	public Double getLongTermAccountsPayable() {
		return longTermAccountsPayable;
	}
	public void setLongTermAccountsPayable(Double longTermAccountsPayable) {
		this.longTermAccountsPayable = longTermAccountsPayable;
	}
	public Double getLongTermadvancesFromCustomers() {
		return longTermadvancesFromCustomers;
	}
	public void setLongTermadvancesFromCustomers(Double longTermadvancesFromCustomers) {
		this.longTermadvancesFromCustomers = longTermadvancesFromCustomers;
	}
	public Double getLongTermUnearnedRevenue() {
		return longTermUnearnedRevenue;
	}
	public void setLongTermUnearnedRevenue(Double longTermUnearnedRevenue) {
		this.longTermUnearnedRevenue = longTermUnearnedRevenue;
	}
	public Double getLongTermBorrowingsAndLiabilities() {
		return longTermBorrowingsAndLiabilities;
	}
	public void setLongTermBorrowingsAndLiabilities(Double longTermBorrowingsAndLiabilities) {
		this.longTermBorrowingsAndLiabilities = longTermBorrowingsAndLiabilities;
	}
	public Double getOtherLongTermLiabilities() {
		return otherLongTermLiabilities;
	}
	public void setOtherLongTermLiabilities(Double otherLongTermLiabilities) {
		this.otherLongTermLiabilities = otherLongTermLiabilities;
	}
	public Double getEquity() {
		return equity;
	}
	public void setEquity(Double equity) {
		this.equity = equity;
	}
	public Double getShareCapital() {
		return shareCapital;
	}
	public void setShareCapital(Double shareCapital) {
		this.shareCapital = shareCapital;
	}
	public Double getSharePremium() {
		return sharePremium;
	}
	public void setSharePremium(Double sharePremium) {
		this.sharePremium = sharePremium;
	}
	public Double getRetainedProfits() {
		return retainedProfits;
	}
	public void setRetainedProfits(Double retainedProfits) {
		this.retainedProfits = retainedProfits;
	}
	public Double getOtherCapitals() {
		return otherCapitals;
	}
	public void setOtherCapitals(Double otherCapitals) {
		this.otherCapitals = otherCapitals;
	}
	public Double getNonControllingInterest() {
		return nonControllingInterest;
	}
	public void setNonControllingInterest(Double nonControllingInterest) {
		this.nonControllingInterest = nonControllingInterest;
	}
	public Double getTotalResources() {
		return totalResources;
	}
	public void setTotalResources(Double totalResources) {
		this.totalResources = totalResources;
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
	
}