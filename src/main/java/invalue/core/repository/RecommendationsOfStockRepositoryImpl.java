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
public class RecommendationsOfStockRepositoryImpl implements RecommendationsOfStockRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Object> GetListRecommendationsOfStock(String stockCode){
		StringBuilder sql = new StringBuilder();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select COMPANY_RECOMMENDATIONS, RECOMMENDATIONS_ACTION, TARGET_PRICE, REVENUE, PRETAX_PROFIT, NET_PROFIT, TIME_RECOMMENDATIONS from recommendations_of_stock where STOCK_CODE = ? order by TIME_RECOMMENDATIONS desc LIMIT 5");

        query = entityManager.createNativeQuery(sql.toString());
        query.setParameter(1, stockCode);

        return query.getResultList();
	}

}