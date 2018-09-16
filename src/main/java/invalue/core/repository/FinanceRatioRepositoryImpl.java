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
    	select.append(" SELECT f.stock_code, s.name, s.STOCK_EXCHANGE_CODE , f.MARKET_PRICE");
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
	public void updateOldFinanceRatioFillter(List<String> listCode, String timeString) {
		StringBuilder sql = new StringBuilder();
		Query query = entityManager.createNativeQuery("");
		List<Object> params = new ArrayList<Object>();
		sql.append("update finance_ratio set status = 1 where status = 0 and TIME_STRING< ? and stock_code in (");
		params.add(timeString);
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
    	select.append(" SELECT f.stock_code, f.MARKET_PRICE");
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
   
}