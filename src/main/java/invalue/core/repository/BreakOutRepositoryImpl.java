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
public class BreakOutRepositoryImpl implements BreakOutRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Object> getBreakOutScreenByCode(String screnCode) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select b.stock_code, s.name,s.stock_exchange_code,b.close_price,b.resistance,b.support,b.adx14,b.rsi14,b.macd,b.volume,DATE_FORMAT(b.break_time,'%d/%m/%Y') AS your_date  ");
    	sql.append(" from break_out b, stock s  ");
    	sql.append(" where 1 = 1 and b.STOCK_CODE = s.code and screen=? ");
    	sql.append(" order by b.break_time desc,b.stock_code ");
    	params.add(screnCode);
        query = entityManager.createNativeQuery(sql.toString());
        
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	} 
}