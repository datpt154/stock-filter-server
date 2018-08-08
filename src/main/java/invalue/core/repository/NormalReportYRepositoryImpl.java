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
    	sql.append(" ,999 CFO ");
    	sql.append(" ,999 CFI ");
    	sql.append(" ,999 CFF ");
    	sql.append(" ,'null' SPACE_3 ");
    	sql.append(" ,IFNULL(f.EPS, 0)  ");
    	sql.append(" ,IFNULL(f.BOOK_VALUE, 0) ");
    	sql.append(" ,IFNULL(f.EBITDA, 0) ");
    	sql.append(" ,999 Capitalization ");
    	sql.append(" ,'null' SPACE_4 ");
    	sql.append(" ,999 Revenue_Growth  ");
    	sql.append(" ,IFNULL(f.NET_INCOME, 0)  ");
    	sql.append(" ,999 EBITDA ");
    	sql.append(" ,999 ASSET ");
    	sql.append(" ,999 Capitalization_YOY ");
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