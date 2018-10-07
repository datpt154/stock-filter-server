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
public class StockRepositoryImpl implements StockRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Object> autoCompleteStock(String searchPattern) {
		StringBuilder sql = new StringBuilder();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select id,code,name from stock where code like ? or Name_Text like ? order by code LIMIT 5 ");
    	
		
        query = entityManager.createNativeQuery(sql.toString());
        searchPattern="%"+searchPattern+"%";
        query.setParameter(1, searchPattern);
        query.setParameter(2, searchPattern);

        return query.getResultList();
	}

	@Override
	public Long getStockByCode(String code) {
		StringBuilder sql = new StringBuilder();
    	StringBuilder select = new StringBuilder();
    	StringBuilder where = new StringBuilder();
    	StringBuilder from = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	select.append(" SELECT s.id");
    	from.append(" from stock s ");
    	where.append(" where 1 = 1 and s.CODE = ? and s.status<> -1 ");
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