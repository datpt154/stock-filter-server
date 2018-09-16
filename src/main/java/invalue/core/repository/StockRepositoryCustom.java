package invalue.core.repository;

import java.util.List;
/**
 * Created by HUYNP4 on 15/09/2018.
 */
public interface StockRepositoryCustom {
	public List<Object> autoCompleteStock(String searchPattern);
	public Long getStockByCode(String code );
}
