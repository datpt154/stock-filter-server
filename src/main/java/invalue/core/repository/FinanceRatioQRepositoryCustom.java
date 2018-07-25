package invalue.core.repository;

import java.util.List;

import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;

public interface FinanceRatioQRepositoryCustom {
	public List<Object> getFinanceRatioFillter(InputBasicFilterDTO inputBasicFilterDTO);
	public List<Object> getCompareFillter(InputCompareFilterDTO inputCompareFilterDTO);
	public void updateOldFinanceRatioFillter(List<String> listCode, String timeString );
}
