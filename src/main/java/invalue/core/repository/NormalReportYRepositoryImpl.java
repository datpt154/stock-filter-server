package invalue.core.repository;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class NormalReportYRepositoryImpl implements NormalReportYCustom {
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Object> searchReportData(String code) {
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
    	sql.append(" from normal_report_y n   ");
    	sql.append(" left join finance_ratio_y f  on n.code = f.code ");
    	sql.append(" where n.stock_code = ? order by n.code limit 5  ");
    	params.add(code);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
		
	}
	@Override
	public List<Object> searchInfoCtyAnCurrenData(String code, String timeString) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select s.code, s.name ");
    	sql.append(" ,999 plan_revenue, 999 LNTT, 999 LNST, 999 CoTuc ");
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


    	sql.append(" from stock s  ");
    	sql.append(" left join finance_ratio_y f  on s.code = f.stock_code ");
    	sql.append(" where s.code=? and f.time_string=? ");
    	params.add(code);
    	params.add(timeString);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
		
	}
	@Override
	public List<Object> searchHeaderReportData(String code) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select n.TIME_STRING ");
    	sql.append(" from normal_report_y n   ");
    	sql.append(" where n.stock_code = ? order by n.code limit 5  ");
    	params.add(code);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}
   
    
   
}