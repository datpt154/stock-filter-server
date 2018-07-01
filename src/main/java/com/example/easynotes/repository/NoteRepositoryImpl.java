package com.example.easynotes.repository;



import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.easynotes.model.Note;
import com.example.easynotes.model.vo.ReportFilterInfo;
import com.example.easynotes.model.FinanceRatioQ;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by gkatzioura on 6/3/16.
 */
@Repository
@Transactional(readOnly = true)
public class NoteRepositoryImpl implements NoteRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Note> getFirstNamesLike(String title) {
        Query query = entityManager.createNativeQuery("SELECT em.* FROM notes as em " +
                "WHERE em.title LIKE ?", Note.class);
        query.setParameter(1, title + "%");
        return query.getResultList();
    }
    @Override
    public List<FinanceRatioQ> getFinanceRatioFillter(List<ReportFilterInfo> listIn) {
    	StringBuilder sql = new StringBuilder();
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("", FinanceRatioQ.class);
		sql.append(" SELECT * ");
		sql.append(" from finance_ratio_q where 1=1 ");
		for(int i=0;i<listIn.size();i++){
			sql.append(" and "+listIn.get(i).getCode()+">= ?" );
			params.add(listIn.get(i).getMin());
			sql.append(" and "+listIn.get(i).getCode()+"<= ?" );
			params.add(listIn.get(i).getMax());
		}
    	
        query = entityManager.createNativeQuery(sql.toString(), FinanceRatioQ.class);
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
    }
}