package invalue.core.repository;

import java.util.List;

import invalue.core.dto.FilterNewsDTO;
/**
 * Created by HUYNP4 on 28/11/2018.
 */
public interface NewsRepositoryCustom {
//	public List<Object> autoCompleteStock(String searchPattern);
	public List<Object> getNewsById(Long id);
	public List<Object> getListNewsPaging(FilterNewsDTO inputData);
}
