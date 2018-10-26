package invalue.core.repository;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;
/**
 * Created by HUYNP4 on 15/09/2018.
 */
@Repository
@Transactional(readOnly = true)
public class FinanceRatioRepositoryImpl implements FinanceRatioRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;
   
    @Override
    public List<Object> getFinanceRatioFillter(InputBasicFilterDTO inputBasicFilterDTO) {
    	StringBuilder sql = new StringBuilder();
    	StringBuilder select = new StringBuilder();
    	StringBuilder where = new StringBuilder();
    	StringBuilder from = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	select.append(" SELECT f.stock_code, s.name, s.STOCK_EXCHANGE_CODE, f.MARKET_PRICE prices ");
    	from.append(" from finance_ratio f, stock s ");
    	where.append(" where 1 = 1 and f.STOCK_CODE = s.code and f.status = 0 ");
    	where.append(" and Y_Q_R = ? ");
    	if("quarter".toUpperCase().equals(inputBasicFilterDTO.getTime().toUpperCase())) {
    		params.add("Q");
		}else {
			params.add("Y");
		}
    	where.append(" and stock_exchange_code in (");
    	for(int i=0;i<inputBasicFilterDTO.getStockExchanges().length;i++){
    		if(i == 0) {
    			where.append(" ?");
			}else {
				where.append(", ?");
			}
			params.add(inputBasicFilterDTO.getStockExchanges()[i]);
		}
    	where.append(") ");
		for(int i=0;i<inputBasicFilterDTO.getSearchDataitems().size();i++){
			//select clause
			select.append(",").append(inputBasicFilterDTO.getSearchDataitems().get(i).getCode());
			//where clause
			where.append(" and "+inputBasicFilterDTO.getSearchDataitems().get(i).getCode()+">= ?" );
			params.add(inputBasicFilterDTO.getSearchDataitems().get(i).getSelectedValues()[0]);
			where.append(" and "+inputBasicFilterDTO.getSearchDataitems().get(i).getCode()+"<= ?" );
			params.add(inputBasicFilterDTO.getSearchDataitems().get(i).getSelectedValues()[1]);
		}
		sql.append(select).append(from).append(where);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }

        return query.getResultList();
    }

	@Override
	public void updateOldFinanceRatioFillter(List<String> listCode, String timeString, String time) {
		StringBuilder sql = new StringBuilder();
		Query query = entityManager.createNativeQuery("");
		List<Object> params = new ArrayList<Object>();
		sql.append("update finance_ratio set status = 1 where status = 0 and TIME_STRING< ? and Y_Q_R=? and stock_code in (");
		params.add(timeString);
		params.add(time);
		for(int i=0;i<listCode.size();i++){
			if(i == 0) {
				sql.append(" ?");
			}else {
				sql.append(", ?");
			}
			params.add(listCode.get(i));
        }
		sql.append(")");
		query = entityManager.createNativeQuery(sql.toString());
		for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
		query.executeUpdate();
	}
	
	@Override
	public List<Object> getCompareFillter(InputCompareFilterDTO inputCompareFilterDTO) {
    	StringBuilder sql = new StringBuilder();
    	StringBuilder select = new StringBuilder();
    	StringBuilder where = new StringBuilder();
    	StringBuilder from = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	select.append(" SELECT f.stock_code, f.MARKET_PRICE prices");
    	from.append(" from finance_ratio f, stock s ");
    	where.append(" where 1 = 1 and f.STOCK_CODE = s.code and f.status = 0 ");
    	where.append(" and Y_Q_R = ? ");
    	if("quarter".toUpperCase().equals(inputCompareFilterDTO.getTime().toUpperCase())) {
    		params.add("Q");
		}else {
			params.add("Y");
		}
		for(int i=0;i<inputCompareFilterDTO.getSearchDataitems().size();i++){
			//select clause
			select.append(",").append(inputCompareFilterDTO.getSearchDataitems().get(i).getCode());
			//where clause
//			where.append(" and "+inputCompareFilterDTO.getSearchDataitems().get(i).getCode()+">= ?" );
//			params.add(inputCompareFilterDTO.getSearchDataitems().get(i).getSelectedValues()[0]);
//			where.append(" and "+inputCompareFilterDTO.getSearchDataitems().get(i).getCode()+"<= ?" );
//			params.add(inputCompareFilterDTO.getSearchDataitems().get(i).getSelectedValues()[1]);
		}
		
		where.append(" and STOCK_CODE in( " );
		for(int i=0;i<inputCompareFilterDTO.getStocks().size();i++){
			
			if(i == 0) {
				where.append(" ?");
			}else {
				where.append(", ?");
			}
			params.add(inputCompareFilterDTO.getStocks().get(i));
		}
		where.append(" ) " );
		sql.append(select).append(from).append(where);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }

        return query.getResultList();
    }

	@Override
	public Long getFinanceRatioByCode(String code) {
		StringBuilder sql = new StringBuilder();
    	StringBuilder select = new StringBuilder();
    	StringBuilder where = new StringBuilder();
    	StringBuilder from = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	select.append(" SELECT f.id");
    	from.append(" from finance_ratio f ");
    	where.append(" where 1 = 1 and f.CODE = ? and status<> -1 ");
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
	
	@Override
	public Double getNewPriceByStockCode(String stockCode) {
		StringBuilder sql = new StringBuilder();
    	StringBuilder select = new StringBuilder();
    	StringBuilder where = new StringBuilder();
    	StringBuilder from = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	select.append(" select f.MARKET_PRICE ");
    	from.append(" from finance_ratio f ");
    	where.append(" where f.stock_code=? and f.y_q_r='Y' order by f.time_string desc limit 1 ");
    	params.add(stockCode);
    	
		sql.append(select).append(from).append(where);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }

        List<Object> rs = query.getResultList();
        if(!rs.isEmpty()) {
        	return Double.parseDouble(rs.get(0).toString());
        }
        return null ;
	}
   
	
	@Override
	public List<Object> searchReportDataDetail(String code, String time) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select 'null' SPACE_1 ");
    	sql.append(" ,IFNULL(f.NET_REVENUE, 0) ");
    	sql.append(" ,IFNULL(f.GROSS_PROFIT, 0) ");
    	sql.append(" ,IFNULL(f.NET_INCOME, 0) ");
    	sql.append(" ,IFNULL(f.SHARE_S_OUSTANDING, 0) ");
    	sql.append(" ,IFNULL(f.EPS, 0) ");
    	sql.append(" ,IFNULL(f.BOOK_VALUE, 0) ");
    	sql.append(" ,IFNULL(f.MARKET_PRICE, 0) ");
//    	sql.append(" ,IFNULL(f.DAYYS, 0) ");
    	sql.append(" ,IFNULL(f.CAPEX, 0) ");
    	sql.append(" ,IFNULL(f.FCF, 0) ");
    	sql.append(" ,IFNULL(f.EBIT, 0) ");
    	sql.append(" ,IFNULL(f.EBITDA, 0) ");
    	sql.append(" ,IFNULL(f.NNWC, 0) ");
    	sql.append(" ,IFNULL(f.NET_WORKING_CAPITAL, 0) ");
    	sql.append(" ,IFNULL(f.EV, 0) ");
    	sql.append(" ,IFNULL(f.MARKET_CAPITAL, 0) ");
    	sql.append(" ,'null' SPACE_2 ");
    	sql.append(" ,IFNULL(f.NET_REVENUE_YOY, 0) ");
    	sql.append(" ,IFNULL(f.GROSS_PROFIT_YOY, 0) ");
    	sql.append(" ,IFNULL(f.EPS_YOY, 0) ");
    	sql.append(" ,IFNULL(f.EBITDA_YOY, 0) ");
    	sql.append(" ,IFNULL(f.DEBT_YOY, 0) ");
    	sql.append(" ,IFNULL(f.EQUITY_YOY, 0) ");
    	sql.append(" ,IFNULL(f.MARKET_CAPITAL_YOY, 0) ");
    	sql.append(" ,IFNULL(f.TOTAL_ASSETS_YOY, 0) ");
    	sql.append(" ,'null' SPACE_3 ");
    	sql.append(" ,IFNULL(f.P_E, 0) ");
    	sql.append(" ,IFNULL(f.PEG, 0) ");
    	sql.append(" ,IFNULL(f.P_B, 0) ");
    	sql.append(" ,IFNULL(f.P_S, 0) ");
    	sql.append(" ,IFNULL(f.EV_EBITDA, 0) ");
    	sql.append(" ,IFNULL(f.EV_EBIT, 0) ");
    	sql.append(" ,IFNULL(f.EV_FCF, 0) ");
    	sql.append(" ,IFNULL(f.REV_FCF, 0) ");
    	sql.append(" ,IFNULL(f.MC_CFO, 0) ");
    	sql.append(" ,IFNULL(f.MC_NWC, 0) ");
//    	sql.append(" ,IFNULL(f.FCFF, 0) ");
//    	sql.append(" ,IFNULL(f.FCFE, 0) ");
    	sql.append(" ,'null' SPACE_4 ");
    	sql.append(" ,IFNULL(f.CAPEX_REV, 0) ");
    	sql.append(" ,IFNULL(f.ROIC, 0) ");
    	sql.append(" ,IFNULL(f.ROCE, 0) ");
    	sql.append(" ,IFNULL(f.ROE, 0) ");
    	sql.append(" ,IFNULL(f.ROA, 0) ");
    	sql.append(" ,'null' SPACE_5 ");
    	sql.append(" ,IFNULL(f.GROSS_PROFIT_MARGIN, 0) ");
    	sql.append(" ,IFNULL(f.OPERATING_PROFIT_MARGIN, 0) ");
    	sql.append(" ,IFNULL(f.PRETAX_PROFIT_MARGIN, 0) ");
    	sql.append(" ,IFNULL(f.NET_PROFIT_MARGIN, 0) ");
    	sql.append(" ,IFNULL(f.DIV_YIELD, 0) ");
    	sql.append(" ,IFNULL(f.EBIT_REV, 0) ");
    	sql.append(" ,IFNULL(f.EBITDA_REV, 0) ");
    	sql.append(" ,'null' SPACE_6 ");
    	sql.append(" ,IFNULL(f.SALES_TO_TOTAL_ASSETS, 0) ");
    	sql.append(" ,IFNULL(f.RECEIVABLE_TURNOVER, 0) ");
    	sql.append(" ,IFNULL(f.PAYABLE_TURNOVER, 0) ");
    	sql.append(" ,IFNULL(f.INVENTORY_TURNOVER, 0) ");
    	sql.append(" ,'null' SPACE_7 ");
    	sql.append(" ,IFNULL(f.DEBT_TO_ASSETS_RATIO, 0) ");
    	sql.append(" ,IFNULL(f.DEBT_TO_EQUITY_RATIO, 0) ");
    	sql.append(" ,IFNULL(f.LONG_TIME_DEBT_TOTAL_CAPITALAZION, 0) ");
    	sql.append(" ,IFNULL(f.INTEREST_COVERAGE, 0) ");
    	sql.append(" ,'null' SPACE_8 ");
    	sql.append(" ,IFNULL(f.CURRENT_RATIO, 0) ");
    	sql.append(" ,IFNULL(f.QUICK_RATIO, 0) ");
    	sql.append(" ,IFNULL(f.CASH_RATIO, 0) ");
    	sql.append(" ,'null' SPACE_9 ");
    	sql.append(" ,IFNULL(f.ACCOUNT_RECEIVABLE_TO_REVENUE, 0) ");
    	sql.append(" ,IFNULL(f.ACCOUNT_RECEIVABLE_TO_NET_INCOME, 0) ");
    	sql.append(" ,IFNULL(f.ALLOWANCES_AND_PROVISIONS_TO_NET_INCOME, 0) ");
    	sql.append(" ,'null' SPACE_10 ");
    	sql.append(" ,IFNULL(f.F_SCORE, 0) ");
    	sql.append(" ,IFNULL(f.C_SCORE, 0) ");
    	sql.append(" ,IFNULL(f.M_SCORE, 0) ");
    	sql.append(" ,IFNULL(f.Z_SCORE, 0) ");

    	sql.append(" from finance_ratio f   ");
    	sql.append(" where f.stock_code = ?  and f.Y_Q_R=?  order by f.code desc limit 5");
    	params.add(code);
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
    	sql.append(" select f.TIME_STRING ");
    	sql.append(" from finance_ratio f   ");
    	sql.append(" where f.stock_code = ? and f.Y_Q_R=? order by f.code desc limit 5  ");
    	params.add(code);
    	params.add(time);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> highestRevenue(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.NET_REVENUE revenue");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? ");
		params.add(time);
		sql.append(" order by f.NET_REVENUE desc,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}
	@Override
	public List<Object> highestRevenueGrowth(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.NET_REVENUE_YOY revenue");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? ");
		params.add(time);
		sql.append(" order by f.NET_REVENUE_YOY desc,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> highestProfit(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.NET_INCOME netIncome");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? ");
		params.add(time);
		sql.append(" order by f.NET_INCOME desc ,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> highestProfitGrowth(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.GROSS_PROFIT_YOY grossProfitYoy");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? ");
		params.add(time);
		sql.append(" order by f.GROSS_PROFIT_YOY desc,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> highestEPS(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.EPS eps");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? ");
		params.add(time);
		sql.append(" order by f.EPS desc,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> highestEPSGrowth(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.EPS_YOY epsYoy");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? ");
		params.add(time);
		sql.append(" order by f.EPS_YOY desc,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> lowestPE(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.P_E pe");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? and f.P_E>0 ");
		params.add(time);
		sql.append(" order by f.P_E ASC,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> lowestPB(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.P_B pb");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? and f.P_B>0 ");
		params.add(time);
		sql.append(" order by f.P_B ASC,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> lowestMCNWC(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.MC_NWC mcNwc");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? and f.MC_NWC>0 ");
		params.add(time);
		sql.append(" order by f.MC_NWC ASC,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> lowestEVEBITDA(String time) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT f.stock_code, f.EV_EBITDA evEbitda");
    	sql.append(" from finance_ratio f");
    	sql.append(" where 1 = 1 ");
    	if(!"R".equals(time)) {
    		sql.append(" and f.status = 0 ");
    	}
    	sql.append(" and Y_Q_R = ? and f.EV_EBITDA>0 ");
		params.add(time);
		sql.append(" order by f.EV_EBITDA ASC,f.stock_code limit 10");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> screnNetNet() {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select f.stock_code, s.name,s.stock_exchange_code");
    	sql.append(" ,f.MARKET_PRICE,f.NET_WORKING_CAPITAL");
    	sql.append(" ,f.MARKET_CAPITAL,f.P_E,f.P_B,f.EPS");
    	sql.append(" ,f.F_SCORE,f.C_SCORE ");
    	sql.append(" from finance_ratio f, stock s ");
    	sql.append(" where 1 = 1 and f.STOCK_CODE = s.code ");
    	sql.append(" and f.status = 0");
    	sql.append(" and f.Y_Q_R<>'R'");
    	sql.append(" and f.BENJAMIN_GRAHAM_NET_NETS =1");
    	sql.append(" order by f.stock_code,f.Y_Q_R");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> screnNCAV() {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select f.stock_code, s.name,s.stock_exchange_code,f.MARKET_PRICE");
    	sql.append(" ,f.NET_WORKING_CAPITAL");
    	sql.append(" ,f.MARKET_CAPITAL,f.P_E,f.P_B,f.EPS");
    	sql.append(" ,f.F_SCORE,f.C_SCORE ");
    	sql.append(" from finance_ratio f, stock s ");
    	sql.append(" where 1 = 1 and f.STOCK_CODE = s.code ");
    	sql.append(" and f.status = 0");
    	sql.append(" and f.Y_Q_R<>'R'");
    	sql.append(" and f.BENJAMIN_GRAHAM_NCAV_BARGAIN =1");
    	sql.append(" order by f.stock_code,f.Y_Q_R");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> screnCANSLIM() {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select f.stock_code, s.name,s.stock_exchange_code,f.MARKET_PRICE");
    	sql.append(" ,f.EPS_YOY,f.EPS");
    	sql.append(" ,f.ROE,f.P_E,f.P_B");
    	sql.append(" ,f.F_SCORE,f.C_SCORE ");
    	sql.append(" from finance_ratio f, stock s ");
    	sql.append(" where 1 = 1 and f.STOCK_CODE = s.code ");
    	sql.append(" and f.status = 0");
    	sql.append(" and f.Y_Q_R='Q'");
    	sql.append(" and f.CANSLIM_NOT_UPCOM =1");
    	sql.append(" order by f.stock_code");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> screnPhilipFisherGrowth() {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select f.stock_code, s.name,s.stock_exchange_code,f.MARKET_PRICE");
    	sql.append(" ,f.GROSS_PROFIT_YOY,f.PEG");
    	sql.append(" ,f.DEBT_TO_EQUITY_RATIO,f.FCF,f.P_E");
    	sql.append(" ,f.F_SCORE,f.C_SCORE ");
    	sql.append(" from finance_ratio f, stock s ");
    	sql.append(" where 1 = 1 and f.STOCK_CODE = s.code ");
    	sql.append(" and f.status = 0");
    	sql.append(" and f.Y_Q_R='Y'");
    	sql.append(" and f.PHILIP_FISHER_GROWTH_BUSINESS_INSIDER_Y =1");
    	sql.append(" order by f.stock_code,f.Y_Q_R");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> screnJohnNeffValue() {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select f.stock_code, s.name,s.stock_exchange_code,f.MARKET_PRICE ");
    	sql.append(" ,f.NET_REVENUE_YOY,f.PEG ");
    	sql.append(" ,f.EPS,f.FCF,f.P_E ");
    	sql.append(" ,f.F_SCORE,f.C_SCORE  ");
    	sql.append(" from finance_ratio f, stock s  ");
    	sql.append(" where 1 = 1 and f.STOCK_CODE = s.code  ");
    	sql.append(" and f.status = 0 ");
    	sql.append(" and f.Y_Q_R='Y' ");
    	sql.append(" and f.JOHN_NEFF_VALUE =1 ");
    	sql.append(" order by f.stock_code,f.Y_Q_R ");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> screnPeterLynchGrowth() {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select f.stock_code, s.name,s.stock_exchange_code,f.MARKET_PRICE ");
    	sql.append(" ,f.EPS_YOY,f.PEG ");
    	sql.append(" ,f.DEBT_TO_ASSETS_RATIO,f.P_E,f.EPS ");
    	sql.append(" ,f.F_SCORE,f.C_SCORE  ");
    	sql.append(" from finance_ratio f, stock s  ");
    	sql.append(" where 1 = 1 and f.STOCK_CODE = s.code  ");
    	sql.append(" and f.status = 0 ");
    	sql.append(" and f.Y_Q_R='Y' ");
    	sql.append(" and f.PETER_LYNCH_GROWTH =1 ");
    	sql.append(" order by f.stock_code,f.Y_Q_R ");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}
	@Override
	public List<Object> screnGrahamChecklist() {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select f.stock_code, s.name,s.stock_exchange_code,f.MARKET_PRICE ");
    	sql.append(" ,f.GRAHAM_CHECKLIST_Y,f.MARKET_CAPITAL ");
    	sql.append(" ,f.P_E,f.P_B,f.EPS ");
    	sql.append(" ,f.F_SCORE,f.C_SCORE  ");
    	sql.append(" from finance_ratio f, stock s  ");
    	sql.append(" where 1 = 1 and f.STOCK_CODE = s.code  ");
    	sql.append(" and f.status = 0 ");
    	sql.append(" and f.Y_Q_R='Y' ");
    	sql.append(" and f.GRAHAM_CHECKLIST_Y >7 ");
    	sql.append(" order by f.stock_code ");
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}
}