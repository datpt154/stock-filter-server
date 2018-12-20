package invalue.core.repository;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import invalue.core.dto.FilterNewsDTO;
import invalue.core.util.StringUtil;
/**
 * Created by HUYNP4 on 28/11/2018.
 */
@Repository
@Transactional(readOnly = true)
public class NewsRepositoryImpl implements NewsRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Object> getNewsById(Long id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select id,category_id,content,DATE_FORMAT(created_time, '%d/%m/%Y'),tag,thumbnail_url,title from news where id=?");
    	params.add(id);
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        return query.getResultList();
	}

	@Override
	public List<Object> getListNewsPaging(FilterNewsDTO inputData) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" select SQL_CALC_FOUND_ROWS id,category_id,DATE_FORMAT(created_time, '%d/%m/%Y'),thumbnail_url,title from news where 1=1 ");
    	if(null!=inputData.getCategoryId() && inputData.getCategoryId()>0) {
    		sql.append(" and category_id=? ");
    		params.add(inputData.getCategoryId());
    	}
    	if(null!=inputData.getTitle() && !StringUtil.isNullOrEmpty(inputData.getTitle())) {
    		sql.append(" and title like ? ");
    		params.add("%"+inputData.getTitle()+"%");
    	}
    	if(null!=inputData.getCreatedTime() && !StringUtil.isNullOrEmpty(inputData.getCreatedTime())) {
    		sql.append(" and DATE(created_time)>=STR_TO_DATE(?,'%d/%m/%Y') ");
    		params.add(inputData.getCreatedTime());
    	}
    	if(null!=inputData.getListTag() && !inputData.getListTag().isEmpty()) {
    		sql.append(" and ( 1=2 ");
    		for(String t:inputData.getListTag()) {
    			sql.append(" or( tag like ? ) ");
    			params.add("%#"+t+"%");
    		}
    		sql.append(" ) ");
    	}
    	sql.append(" order by created_time, id desc limit ?,?");
    	params.add(inputData.getStart());
    	params.add(inputData.getNumRow());
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }
        List<Object>lObj= query.getResultList();
        query = entityManager.createNativeQuery("Select FOUND_ROWS()");
        lObj.add(query.getSingleResult());
        return lObj;
	}

}