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
@Table(name = "bank_report_q", uniqueConstraints = {@UniqueConstraint(columnNames = "CODE") })
public class BankReportQ implements java.io.Serializable {
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
	@Column(name = "STOCK_CODE", nullable = false, length = 255)
	private String stockCode;

	@Basic
	@Column(name = "STOCK_ID", nullable = false)
	private Integer stockId;
	
	@Basic
	@Column(name = "ASSETS", precision = 20, scale = 5)
	private Double assets;
	@Basic
	@Column(name = "CASH_ON_HAND_GOLD_SILVER_AND_GEMSTONES", precision = 20, scale = 5)
	private Double cashOnHandGoldSilverAndGemstones;
	@Basic
	@Column(name = "BALANCES_WITH_THE_STATE_BANK_OF_VIETNAM", precision = 20, scale = 5)
	private Double balancesWithTheStateBankOfVietnam;
	@Basic
	@Column(name = "BALANCES_WITH_AND_LOANS_TO_OTHER_CREDIT_INSTITUTIONS", precision = 20, scale = 5)
	private Double balancesWithAndLoansToOtherCreditInstitutions;
	@Basic
	@Column(name = "TRADING_SECURITIES", precision = 20, scale = 5)
	private Double tradingSecurities;
	@Basic
	@Column(name = "DERIVATIVE_FINANCIAL_INSTRUMENTS_AND_N_OTHER_FINANCIAL_ASSETS", precision = 20, scale = 5)
	private Double derivativeFinancialInstrumentsAndNOtherFinancialAssets;
	@Basic
	@Column(name = "LOANS_TO_CUSTOMERS", precision = 20, scale = 5)
	private Double loansToCustomers;
	@Basic
	@Column(name = "INVESTMENT_SECURITIES", precision = 20, scale = 5)
	private Double investmentSecurities;
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
	@Column(name = "AMOUNTS_DUE_TO_THE_GOVERNMENT_AND_THE_SBV", precision = 20, scale = 5)
	private Double amountsDueToTheGovernmentAndTheSbv;
	@Basic
	@Column(name = "DEPOSITS_AND_BORROWINGS_FROM_OTHER_CREDIT_INSTITUTIONS", precision = 20, scale = 5)
	private Double depositsAndBorrowingsFromOtherCreditInstitutions;
	@Basic
	@Column(name = "DEPOSITS_FROM_OTHER_CREDIT_INSTITUTIONS", precision = 20, scale = 5)
	private Double depositsFromOtherCreditInstitutions;
	@Basic
	@Column(name = "BORROWINGS_FROM_OTHER_CREDIT_INSTITUTIONS", precision = 20, scale = 5)
	private Double borrowingsFromOtherCreditInstitutions;
	@Basic
	@Column(name = "DEPOSITS_FROM_CUSTOMERS", precision = 20, scale = 5)
	private Double depositsFromCustomers;
	@Basic
	@Column(name = "FUNDS_FOR_FINANCE_ENTRUSTED_INVESTMENTS_AND_ENTRUSTED_LOANS", precision = 20, scale = 5)
	private Double fundsForFinanceEntrustedInvestmentsAndEntrustedLoans;
	@Basic
	@Column(name = "VALUABLE_PAPERS_ISSUED", precision = 20, scale = 5)
	private Double valuablePapersIssued;
	@Basic
	@Column(name = "OTHER_LIABILITIES", precision = 20, scale = 5)
	private Double otherLiabilities;
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
	@Column(name = "TOTAL_RESOURCES", precision = 20, scale = 5)
	private Double totalResources;
	@Basic
	@Column(name = "NET_INTEREST_INCOME", precision = 20, scale = 5)
	private Double netInterestIncome;
	@Basic
	@Column(name = "FEE_AND_COMMISSION_INCOME", precision = 20, scale = 5)
	private Double feeAndCommissionIncome;
	@Basic
	@Column(name = "FEE_AND_COMMISSION_EXPENSES", precision = 20, scale = 5)
	private Double feeAndCommissionExpenses;
	@Basic
	@Column(name = "NET_FEE_AND_COMMISSION_INCOME", precision = 20, scale = 5)
	private Double netFeeAndCommissionIncome;
	@Basic
	@Column(name = "NET_GAIN_LOSS_FROM_TRADING_OF_FOREIGN_CURRENCIES", precision = 20, scale = 5)
	private Double netGainLossFromTradingOfForeignCurrencies;
	@Basic
	@Column(name = "NET_GAIN_LOSS_FROM_TRADING_SECURITIES", precision = 20, scale = 5)
	private Double netGainLossFromTradingSecurities;
	@Basic
	@Column(name = "NET_GAIN_LOSS_FROM_INVESTMENT_SECURITIES", precision = 20, scale = 5)
	private Double netGainLossFromInvestmentSecurities;
	@Basic
	@Column(name = "NET_OTHER_INCOME", precision = 20, scale = 5)
	private Double netOtherIncome;
	@Basic
	@Column(name = "INCOME_FROM_CAPITAL_CONTRIBUTION_AND_EQUITY_INVESTMENTS", precision = 20, scale = 5)
	private Double incomeFromCapitalContributionAndEquityInvestments;
	@Basic
	@Column(name = "OPERATING_INCOME", precision = 20, scale = 5)
	private Double operatingIncome;
	@Basic
	@Column(name = "OPERATING_EXPENSE", precision = 20, scale = 5)
	private Double operatingExpense;
	@Basic
	@Column(name = "NET_OPERATING_PROFIT_BEFORE_ALLOWANCE_FOR_CREDIT_LOSSES", precision = 20, scale = 5)
	private Double netOperatingProfitBeforeAllowanceForCreditLosses;
	@Basic
	@Column(name = "ALLOWANCE_FOR_CREDIT_LOSSES", precision = 20, scale = 5)
	private Double allowanceForCreditLosses;
	@Basic
	@Column(name = "PROFIT_BEFORE_TAX", precision = 20, scale = 5)
	private Double profitBeforeTax;
	@Basic
	@Column(name = "CORPORATE_INCOME_TAX_EXPENSES", precision = 20, scale = 5)
	private Double corporateIncomeTaxExpenses;
	@Basic
	@Column(name = "NET_PROFIT_AFTER_TAX", precision = 20, scale = 5)
	private Double netProfitAfterTax;
	@Basic
	@Column(name = "NON_CONTROLLING_INTEREST", precision = 20, scale = 5)
	private Double nonControllingInterest;
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

	public Double getAssets() {
		return assets;
	}

	public void setAssets(Double assets) {
		this.assets = assets;
	}

	public Double getCashOnHandGoldSilverAndGemstones() {
		return cashOnHandGoldSilverAndGemstones;
	}

	public void setCashOnHandGoldSilverAndGemstones(Double cashOnHandGoldSilverAndGemstones) {
		this.cashOnHandGoldSilverAndGemstones = cashOnHandGoldSilverAndGemstones;
	}

	public Double getBalancesWithTheStateBankOfVietnam() {
		return balancesWithTheStateBankOfVietnam;
	}

	public void setBalancesWithTheStateBankOfVietnam(Double balancesWithTheStateBankOfVietnam) {
		this.balancesWithTheStateBankOfVietnam = balancesWithTheStateBankOfVietnam;
	}

	public Double getBalancesWithAndLoansToOtherCreditInstitutions() {
		return balancesWithAndLoansToOtherCreditInstitutions;
	}

	public void setBalancesWithAndLoansToOtherCreditInstitutions(Double balancesWithAndLoansToOtherCreditInstitutions) {
		this.balancesWithAndLoansToOtherCreditInstitutions = balancesWithAndLoansToOtherCreditInstitutions;
	}

	public Double getTradingSecurities() {
		return tradingSecurities;
	}

	public void setTradingSecurities(Double tradingSecurities) {
		this.tradingSecurities = tradingSecurities;
	}

	public Double getDerivativeFinancialInstrumentsAndNOtherFinancialAssets() {
		return derivativeFinancialInstrumentsAndNOtherFinancialAssets;
	}

	public void setDerivativeFinancialInstrumentsAndNOtherFinancialAssets(
			Double derivativeFinancialInstrumentsAndNOtherFinancialAssets) {
		this.derivativeFinancialInstrumentsAndNOtherFinancialAssets = derivativeFinancialInstrumentsAndNOtherFinancialAssets;
	}

	public Double getLoansToCustomers() {
		return loansToCustomers;
	}

	public void setLoansToCustomers(Double loansToCustomers) {
		this.loansToCustomers = loansToCustomers;
	}

	public Double getInvestmentSecurities() {
		return investmentSecurities;
	}

	public void setInvestmentSecurities(Double investmentSecurities) {
		this.investmentSecurities = investmentSecurities;
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

	public Double getAmountsDueToTheGovernmentAndTheSbv() {
		return amountsDueToTheGovernmentAndTheSbv;
	}

	public void setAmountsDueToTheGovernmentAndTheSbv(Double amountsDueToTheGovernmentAndTheSbv) {
		this.amountsDueToTheGovernmentAndTheSbv = amountsDueToTheGovernmentAndTheSbv;
	}

	public Double getDepositsAndBorrowingsFromOtherCreditInstitutions() {
		return depositsAndBorrowingsFromOtherCreditInstitutions;
	}

	public void setDepositsAndBorrowingsFromOtherCreditInstitutions(
			Double depositsAndBorrowingsFromOtherCreditInstitutions) {
		this.depositsAndBorrowingsFromOtherCreditInstitutions = depositsAndBorrowingsFromOtherCreditInstitutions;
	}

	public Double getDepositsFromOtherCreditInstitutions() {
		return depositsFromOtherCreditInstitutions;
	}

	public void setDepositsFromOtherCreditInstitutions(Double depositsFromOtherCreditInstitutions) {
		this.depositsFromOtherCreditInstitutions = depositsFromOtherCreditInstitutions;
	}

	public Double getBorrowingsFromOtherCreditInstitutions() {
		return borrowingsFromOtherCreditInstitutions;
	}

	public void setBorrowingsFromOtherCreditInstitutions(Double borrowingsFromOtherCreditInstitutions) {
		this.borrowingsFromOtherCreditInstitutions = borrowingsFromOtherCreditInstitutions;
	}

	public Double getDepositsFromCustomers() {
		return depositsFromCustomers;
	}

	public void setDepositsFromCustomers(Double depositsFromCustomers) {
		this.depositsFromCustomers = depositsFromCustomers;
	}

	public Double getFundsForFinanceEntrustedInvestmentsAndEntrustedLoans() {
		return fundsForFinanceEntrustedInvestmentsAndEntrustedLoans;
	}

	public void setFundsForFinanceEntrustedInvestmentsAndEntrustedLoans(
			Double fundsForFinanceEntrustedInvestmentsAndEntrustedLoans) {
		this.fundsForFinanceEntrustedInvestmentsAndEntrustedLoans = fundsForFinanceEntrustedInvestmentsAndEntrustedLoans;
	}

	public Double getValuablePapersIssued() {
		return valuablePapersIssued;
	}

	public void setValuablePapersIssued(Double valuablePapersIssued) {
		this.valuablePapersIssued = valuablePapersIssued;
	}

	public Double getOtherLiabilities() {
		return otherLiabilities;
	}

	public void setOtherLiabilities(Double otherLiabilities) {
		this.otherLiabilities = otherLiabilities;
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

	public Double getTotalResources() {
		return totalResources;
	}

	public void setTotalResources(Double totalResources) {
		this.totalResources = totalResources;
	}

	public Double getNetInterestIncome() {
		return netInterestIncome;
	}

	public void setNetInterestIncome(Double netInterestIncome) {
		this.netInterestIncome = netInterestIncome;
	}

	public Double getFeeAndCommissionIncome() {
		return feeAndCommissionIncome;
	}

	public void setFeeAndCommissionIncome(Double feeAndCommissionIncome) {
		this.feeAndCommissionIncome = feeAndCommissionIncome;
	}

	public Double getFeeAndCommissionExpenses() {
		return feeAndCommissionExpenses;
	}

	public void setFeeAndCommissionExpenses(Double feeAndCommissionExpenses) {
		this.feeAndCommissionExpenses = feeAndCommissionExpenses;
	}

	public Double getNetFeeAndCommissionIncome() {
		return netFeeAndCommissionIncome;
	}

	public void setNetFeeAndCommissionIncome(Double netFeeAndCommissionIncome) {
		this.netFeeAndCommissionIncome = netFeeAndCommissionIncome;
	}

	public Double getNetGainLossFromTradingOfForeignCurrencies() {
		return netGainLossFromTradingOfForeignCurrencies;
	}

	public void setNetGainLossFromTradingOfForeignCurrencies(Double netGainLossFromTradingOfForeignCurrencies) {
		this.netGainLossFromTradingOfForeignCurrencies = netGainLossFromTradingOfForeignCurrencies;
	}

	public Double getNetGainLossFromTradingSecurities() {
		return netGainLossFromTradingSecurities;
	}

	public void setNetGainLossFromTradingSecurities(Double netGainLossFromTradingSecurities) {
		this.netGainLossFromTradingSecurities = netGainLossFromTradingSecurities;
	}

	public Double getNetGainLossFromInvestmentSecurities() {
		return netGainLossFromInvestmentSecurities;
	}

	public void setNetGainLossFromInvestmentSecurities(Double netGainLossFromInvestmentSecurities) {
		this.netGainLossFromInvestmentSecurities = netGainLossFromInvestmentSecurities;
	}

	public Double getNetOtherIncome() {
		return netOtherIncome;
	}

	public void setNetOtherIncome(Double netOtherIncome) {
		this.netOtherIncome = netOtherIncome;
	}

	public Double getIncomeFromCapitalContributionAndEquityInvestments() {
		return incomeFromCapitalContributionAndEquityInvestments;
	}

	public void setIncomeFromCapitalContributionAndEquityInvestments(
			Double incomeFromCapitalContributionAndEquityInvestments) {
		this.incomeFromCapitalContributionAndEquityInvestments = incomeFromCapitalContributionAndEquityInvestments;
	}

	public Double getOperatingIncome() {
		return operatingIncome;
	}

	public void setOperatingIncome(Double operatingIncome) {
		this.operatingIncome = operatingIncome;
	}

	public Double getOperatingExpense() {
		return operatingExpense;
	}

	public void setOperatingExpense(Double operatingExpense) {
		this.operatingExpense = operatingExpense;
	}

	public Double getNetOperatingProfitBeforeAllowanceForCreditLosses() {
		return netOperatingProfitBeforeAllowanceForCreditLosses;
	}

	public void setNetOperatingProfitBeforeAllowanceForCreditLosses(
			Double netOperatingProfitBeforeAllowanceForCreditLosses) {
		this.netOperatingProfitBeforeAllowanceForCreditLosses = netOperatingProfitBeforeAllowanceForCreditLosses;
	}

	public Double getAllowanceForCreditLosses() {
		return allowanceForCreditLosses;
	}

	public void setAllowanceForCreditLosses(Double allowanceForCreditLosses) {
		this.allowanceForCreditLosses = allowanceForCreditLosses;
	}

	public Double getProfitBeforeTax() {
		return profitBeforeTax;
	}

	public void setProfitBeforeTax(Double profitBeforeTax) {
		this.profitBeforeTax = profitBeforeTax;
	}

	public Double getCorporateIncomeTaxExpenses() {
		return corporateIncomeTaxExpenses;
	}

	public void setCorporateIncomeTaxExpenses(Double corporateIncomeTaxExpenses) {
		this.corporateIncomeTaxExpenses = corporateIncomeTaxExpenses;
	}

	public Double getNetProfitAfterTax() {
		return netProfitAfterTax;
	}

	public void setNetProfitAfterTax(Double netProfitAfterTax) {
		this.netProfitAfterTax = netProfitAfterTax;
	}

	public Double getNonControllingInterest() {
		return nonControllingInterest;
	}

	public void setNonControllingInterest(Double nonControllingInterest) {
		this.nonControllingInterest = nonControllingInterest;
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
	
}