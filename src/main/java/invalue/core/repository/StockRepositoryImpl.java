package invalue.core.repository;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class StockRepositoryImpl implements StockRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Object> autoCompleteStock(String searchPattern) {
		StringBuilder sql = new StringBuilder();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select id,code,name from stock where code like ? or Name_Text like ? ");
    	
		
        query = entityManager.createNativeQuery(sql.toString());
        searchPattern="%"+searchPattern+"%";
        query.setParameter(1, searchPattern);
        query.setParameter(2, searchPattern);

        return query.getResultList();
	}
   
    
   
}