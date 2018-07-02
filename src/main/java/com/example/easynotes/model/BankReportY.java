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
@Table(name = "bank_report_y", uniqueConstraints = {@UniqueConstraint(columnNames = "CODE") })
public class BankReportY implements java.io.Serializable {
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