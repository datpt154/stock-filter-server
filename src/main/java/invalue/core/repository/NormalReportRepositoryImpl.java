package invalue.core.repository;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by HUYNP4 on 15/09/2018.
 */

@Repository
@Transactional(readOnly = true)
public class NormalReportRepositoryImpl implements NormalReportCustom {
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Object> searchReportData(String code, String time) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select IFNULL(n.NET_REVENUE, 0) ");
    	sql.append(" ,IFNULL(n.NET_OPERATING_PROFIT, 0) ");
    	sql.append(" ,IFNULL(n.NET_PROFIT_AFTER_TAX, 0) ");
    	sql.append(" ,'null' SPACE_1 ");
    	sql.append(" ,IFNULL(n.TOTAL_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.CURRENT_ASSET, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERM_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.CURRENT_LIABILITIES, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERM_LIABILITIES, 0) ");
    	sql.append(" ,IFNULL(n.EQUITY, 0) ");
    	sql.append(" ,'null' SPACE_2 ");
    	sql.append(" ,IFNULL(n.NET_CASH_FLOWS_FROM_OPERATING_ACTIVITIES,0) ");
    	sql.append(" ,IFNULL(n.NET_CASH_FLOWS_FROM_INVESTING_ACTIVITIES,0) ");
    	sql.append(" ,IFNULL(n.NET_CASH_FLOWS_FROM_FINANCING_ACTIVITIES,0) ");
		sql.append(" ,IFNULL(n.NET_CASH_FLOWS,0) ");
    	sql.append(" ,'null' SPACE_3 ");
    	sql.append(" ,IFNULL(f.EPS, 0)  ");
    	sql.append(" ,IFNULL(f.BOOK_VALUE, 0) ");
    	sql.append(" ,IFNULL(f.EBITDA, 0) ");
		sql.append(" ,IFNULL(f.MARKET_CAPITAL,0) ");
    	sql.append(" ,'null' SPACE_4 ");
		sql.append(" ,IFNULL(f.NET_REVENUE_YOY,0) ");
    	sql.append(" ,IFNULL(f.GROSS_PROFIT_YOY, 0) ");
		sql.append(" ,IFNULL(f.EBITDA_YOY, 0) ");
		sql.append(" ,IFNULL(f.TOTAL_ASSETS_YOY, 0) ");
		sql.append(" ,IFNULL(f.MARKET_CAPITAL_YOY, 0) ");
    	sql.append(" ,'null' SPACE_5 ");
    	sql.append(" ,IFNULL(f.GROSS_PROFIT_MARGIN, 0) ");
    	sql.append(" ,IFNULL(f.OPERATING_PROFIT_MARGIN, 0) ");
    	sql.append(" ,IFNULL(f.NET_PROFIT_MARGIN, 0) ");
    	sql.append(" ,IFNULL(f.EBITDA_REV, 0) ");
    	sql.append(" ,'null' SPACE_6 ");
    	sql.append(" ,IFNULL(f.ROE, 0) ");
    	sql.append(" ,IFNULL(f.ROA, 0) ");
    	sql.append(" ,IFNULL(f.ROIC, 0) ");
    	sql.append(" ,IFNULL(f.ROCE, 0) ");
    	sql.append(" from normal_report n   ");
    	sql.append(" left join finance_ratio f  on n.code = f.code ");
    	sql.append(" where n.stock_code = ?  and n.Y_Q_R=?  order by n.code desc limit 5");
    	params.add(code);
    	params.add(time);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
		
	}
	
	@Override
	public List<Object> searchReportDataDetail(String code, String time) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select IFNULL(n.CURRENT_ASSET, 0) ");
    	sql.append(" ,IFNULL(n.CASH_AND_CASH_EQUIVALENTS, 0) ");
    	sql.append(" ,IFNULL(n.SHORT_TERM_INVESTMENTS, 0) ");
    	sql.append(" ,IFNULL(n.ACCOUNTS_RECEIVABLE_SHORT_TERM, 0) ");
    	sql.append(" ,IFNULL(n.INVENTORIES, 0) ");
    	sql.append(" ,IFNULL(n.OTHER_CURRENT_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERM_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.ACCOUNT_RECEIVABLE_LONG_TERM, 0) ");
    	sql.append(" ,IFNULL(n.FIXED_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.TANGIBLE_FIXED_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.FINANCE_TANGIBLE_FIXED_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.INTANGIBLE_FIXED_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.INVESTMENT_PROPERTY, 0) ");
    	sql.append(" ,IFNULL(n.CONTRUCTION_IN_PROGRESS, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERM_INVESTMENT, 0) ");
    	sql.append(" ,IFNULL(n.GOOD_WILL, 0) ");
    	sql.append(" ,IFNULL(n.OTHER_LONG_TERM_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.TOTAL_ASSETS, 0) ");
    	sql.append(" ,IFNULL(n.CURRENT_LIABILITIES, 0) ");
    	sql.append(" ,IFNULL(n.ACCOUNT_PAYABLE_TO_SUPPLIERS, 0) ");
    	sql.append(" ,IFNULL(n.SHORT_TERM_ADVANCES_FROM_CUSTOMERS, 0) ");
    	sql.append(" ,IFNULL(n.SHORT_TERM_UNEARNED_REVENUE, 0) ");
    	sql.append(" ,IFNULL(n.SHORT_TERM_BORROWINGS_AND_LIABILITIES, 0) ");
    	sql.append(" ,IFNULL(n.OTHER_SHORT_TERM_LIABILITIES, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERM_LIABILITIES, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERM_ACCOUNTS_PAYABLE, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERMADVANCES_FROM_CUSTOMERS, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERM_UNEARNED_REVENUE, 0) ");
    	sql.append(" ,IFNULL(n.LONG_TERM_BORROWINGS_AND_LIABILITIES, 0) ");
    	sql.append(" ,IFNULL(n.OTHER_LONG_TERM_LIABILITIES, 0) ");
    	sql.append(" ,IFNULL(n.EQUITY, 0) ");
    	sql.append(" ,IFNULL(n.SHARE_CAPITAL, 0) ");
    	sql.append(" ,IFNULL(n.SHARE_PREMIUM, 0) ");
    	sql.append(" ,IFNULL(n.RETAINED_PROFITS, 0) ");
    	sql.append(" ,IFNULL(n.OTHER_CAPITALS, 0) ");
    	sql.append(" ,IFNULL(n.NON_CONTROLLING_INTEREST, 0) ");
    	sql.append(" ,IFNULL(n.TOTAL_RESOURCES, 0) ");
    	sql.append(" ,'null' SPACE_1 ");
    	sql.append(" ,IFNULL(n.NET_REVENUE, 0) ");
    	sql.append(" ,IFNULL(n.COST_OF_SALES, 0) ");
    	sql.append(" ,IFNULL(n.GROSS_PROFIT, 0) ");
    	sql.append(" ,IFNULL(n.FINANCIAL_INCOME, 0) ");
    	sql.append(" ,IFNULL(n.FINANCIAL_EXPENSES, 0) ");
    	sql.append(" ,IFNULL(n.IN_WHICH_INTEREST_EXPENSE, 0) ");
    	sql.append(" ,IFNULL(n.SHARE_OF_PROFIT_IN_ASSOCIATES, 0) ");
    	sql.append(" ,IFNULL(n.SELLING_EXPENSES, 0) ");
    	sql.append(" ,IFNULL(n.GENERAL_AND_ADMINISTRATION_EXPENSES, 0) ");
    	sql.append(" ,IFNULL(n.NET_OPERATING_PROFIT, 0) ");
    	sql.append(" ,IFNULL(n.OTHER_INCOME, 0) ");
    	sql.append(" ,IFNULL(n.PROFIT_BEFORE_TAX, 0) ");
    	sql.append(" ,IFNULL(n.INCOME_TAX_EXPENSE, 0) ");
    	sql.append(" ,IFNULL(n.NET_PROFIT_AFTER_TAX, 0) ");
    	sql.append(" ,IFNULL(n.MINORITY_INTEREST, 0) ");
    	sql.append(" ,IFNULL(n.NET_INCOME, 0) ");
    	sql.append(" ,'null' SPACE_2 ");
    	sql.append(" ,IFNULL(n.DEPRECIATION, 0) ");
    	sql.append(" ,IFNULL(n.ALLOWANCES_AND_PROVISIONS, 0) ");
    	sql.append(" ,IFNULL(n.NET_CASH_FLOWS_FROM_OPERATING_ACTIVITIES, 0) ");
    	sql.append(" ,IFNULL(n.NET_CASH_FLOWS_FROM_INVESTING_ACTIVITIES, 0) ");
    	sql.append(" ,IFNULL(n.NET_CASH_FLOWS_FROM_FINANCING_ACTIVITIES, 0) ");
    	sql.append(" ,IFNULL(n.PAYMENTS_OF_DIVIDENDS, 0) ");

    	sql.append(" from normal_report n   ");
    	sql.append(" where n.stock_code = ?  and n.Y_Q_R=?  order by n.code desc limit 5");
    	params.add(code);
    	params.add(time);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
		
	}
	
	@Override
	public List<Object> searchInfoCtyAnCurrenData(String code, String timeString, String time) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select s.code, s.name ");
    	sql.append(" ,p.REVENUE , p.PRETAX_PROFIT , p.NET_PROFIT LNST, p.PAYMENTS_OF_DIVIDENDS CoTuc ");
    	sql.append(" ,f.P_E ");
    	sql.append(" ,f.PEG ");
    	sql.append(" ,f.P_B ");
    	sql.append(" ,f.P_S ");
    	sql.append(" ,f.DIV_YIELD ");
    	sql.append(" ,f.EV_EBITDA ");
    	sql.append(" ,f.F_SCORE ");
    	sql.append(" ,f.C_SCORE ");
    	sql.append(" ,f.M_SCORE ");
    	sql.append(" ,f.Z_SCORE ");
    	sql.append(" ,f.CURRENT_RATIO ");
    	sql.append(" ,f.QUICK_RATIO ");
    	sql.append(" ,f.CASH_RATIO ");
    	sql.append(" ,f.RECEIVABLE_TURNOVER ");
    	sql.append(" ,f.PAYABLE_TURNOVER ");
    	sql.append(" ,f.INVENTORY_TURNOVER ");
    	sql.append(" ,f.DEBT_TO_ASSETS_RATIO ");
    	sql.append(" ,f.DEBT_TO_EQUITY_RATIO ");
    	sql.append(" ,f.LONG_TIME_DEBT_TOTAL_CAPITALAZION ");
    	sql.append(" ,f.INTEREST_COVERAGE ");
    	sql.append(" ,f.ACCOUNT_RECEIVABLE_TO_REVENUE ");
    	sql.append(" ,f.ACCOUNT_RECEIVABLE_TO_NET_INCOME ");
    	sql.append(" ,f.ALLOWANCES_AND_PROVISIONS_TO_NET_INCOME ");
    	sql.append(" ,s.YEAR_END ");

    	sql.append(" from stock s  ");
    	sql.append(" left join finance_ratio f  on s.code = f.stock_code ");
    	sql.append(" left join plan_of_year p  on s.code = p.stock_code ");
    	sql.append(" where s.code=? and f.time_string=? and (f.Y_Q_R=? or f.Y_Q_R='R') order by f.time_string desc limit 1 ");
    	params.add(code);
    	params.add(timeString);
    	params.add(time);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
		
	}
	@Override
	public List<Object> searchHeaderReportData(String code, String time) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select n.TIME_STRING ");
    	sql.append(" from normal_report n   ");
    	sql.append(" where n.stock_code = ? and n.Y_Q_R=? order by n.code desc limit 5  ");
    	params.add(code);
    	params.add(time);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}
	@Override
	public Long getNormalReportByCode(String code) {
		StringBuilder sql = new StringBuilder();
    	StringBuilder select = new StringBuilder();
    	StringBuilder where = new StringBuilder();
    	StringBuilder from = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	select.append(" SELECT n.id");
    	from.append(" from normal_report n ");
    	where.append(" where 1 = 1 and n.CODE = ? and status<> -1 ");
    	params.add(code);
    	
		sql.append(select).append(from).append(where);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }

        List<Object> rs = query.getResultList();
        if(!rs.isEmpty()) {
        	return Long.parseLong(rs.get(0).toString());
        }
        return null ;
	}
   
    
   
}