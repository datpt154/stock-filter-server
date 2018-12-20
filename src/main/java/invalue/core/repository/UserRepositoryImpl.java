package invalue.core.repository;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import invalue.core.dto.OutPutLoginDTO;
import invalue.core.entity.User;
/**
 * Created by HUYNP4 on 28/11/2018.
 */
@Repository
@Transactional(readOnly = true)
public class UserRepositoryImpl implements UserRepositoryCustom {
	@PersistenceContext
    EntityManager entityManager;

	@Override
	public List<Object> getUserByEmail(String email) {
		StringBuilder sql = new StringBuilder();
    	
    	List<Object> params = new ArrayList<Object>();
    	Query query = entityManager.createNativeQuery("");
    	sql.append(" SELECT u.id,u.email,u.full_name,u.phone,u.role_id,u.status,u.type,u.user_name,u.password,u.salt,u.token,u.id_provider,u.provider");
    	sql.append(" from user u ");
    	sql.append(" where 1 = 1 and u.email = ? and u.status<> -1 ");
    	params.add(email);
    	
        query = entityManager.createNativeQuery(sql.toString());
        for(int i=0;i<params.size();i++){
        	query.setParameter(i+1, params.get(i));
        }

        return query.getResultList();
	}

}