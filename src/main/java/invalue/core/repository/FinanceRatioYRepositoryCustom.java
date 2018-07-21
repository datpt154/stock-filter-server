package invalue.core.repository;

import java.util.List;

import invalue.core.dto.InputBasicFilterDTO;

public interface FinanceRatioYRepositoryCustom {
	public List<Object> getFinanceRatioFillter(InputBasicFilterDTO inputBasicFilterDTO);
	public void updateOldFinanceRatioFillter(List<String> listCode, String timeString );
}
