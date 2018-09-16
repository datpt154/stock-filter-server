package invalue.core.repository;

import java.util.List;

import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;
/**
 * Created by HUYNP4 on 15/09/2018.
 */
public interface FinanceRatioYRepositoryCustom {
	public List<Object> getFinanceRatioFillter(InputBasicFilterDTO inputBasicFilterDTO);
	public List<Object> getCompareFillter(InputCompareFilterDTO inputCompareFilterDTO);
	public void updateOldFinanceRatioFillter(List<String> listCode, String timeString );
}
