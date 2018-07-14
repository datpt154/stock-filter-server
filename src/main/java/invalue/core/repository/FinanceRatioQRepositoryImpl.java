package invalue.core.repository;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import invalue.core.entity.FinanceRatioQ;
import invalue.core.vo.ReportFilterInfo;
/**
 * Created by gkatzioura on 6/3/16.
 */
@Repository
@Transactional(readOnly = true)
public class FinanceRatioQRepositoryImpl implements FinanceRatioQRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;
   
    @Override
    public List<Object> getFinanceRatioFillter(List<ReportFilterInfo> listIn) {
    	StringBuilder sql = new StringBuilder();
    	StringBuilder select = new StringBuilder();
    	StringBuilder where = new StringBuilder();
    	StringBuilder from = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("", FinanceRatioQ.class);
    	select.append(" SELECT stock_code");
    	from.append(" from finance_ratio_q ");
    	where.append(" where 1=1 ");
		for(int i=0;i<listIn.size();i++){
			//select clause
			select.append(",").append(listIn.get(i).getCode());
			//where clause
			where.append(" and "+listIn.get(i).getCode()+">= ?" );
			params.add(listIn.get(i).getSelectedValues()[0]);
			where.append(" and "+listIn.get(i).getCode()+"<= ?" );
			params.add(listIn.get(i).getSelectedValues()[1]);
		}
		sql.append(select).append(from).append(where);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }

        return query.getResultList();
    }
   
}