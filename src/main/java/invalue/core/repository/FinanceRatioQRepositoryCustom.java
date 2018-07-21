package invalue.core.repository;

import java.util.List;

import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.vo.ReportFilterInfo;

public interface FinanceRatioQRepositoryCustom {
	public List<Object> getFinanceRatioFillter(InputBasicFilterDTO inputBasicFilterDTO);
}
