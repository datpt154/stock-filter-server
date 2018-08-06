package invalue.core.repository;



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
	public List<Object> searchReportData(String searchPattern) {
		StringBuilder sql = new StringBuilder();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select code,name,10 param1,9 param2,8 param3,7 param4 from stock where 1=1 LIMIT 7");
		
        query = entityManager.createNativeQuery(sql.toString());
        
        return query.getResultList();
	}
   
    
   
}