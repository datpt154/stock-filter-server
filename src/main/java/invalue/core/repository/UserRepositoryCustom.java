package invalue.core.repository;

import java.util.List;

import invalue.core.dto.OutPutLoginDTO;
import invalue.core.entity.User;

/**
 * Created by HUYNP4 on 28/11/2018.
 */
public interface UserRepositoryCustom {
//	public List<Object> autoCompleteStock(String searchPattern);
//	public OutPutLoginDTO login(String userName, String password);
	public List<Object> getUserByEmail(String email);
}
