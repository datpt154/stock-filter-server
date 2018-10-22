package invalue.core.repository;

import java.util.List;

import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;
/**
 * Created by HUYNP4 on 15/09/2018.
 */
public interface FinanceRatioRepositoryCustom {
	public List<Object> getFinanceRatioFillter(InputBasicFilterDTO inputBasicFilterDTO);
	public List<Object> getCompareFillter(InputCompareFilterDTO inputCompareFilterDTO);
	public void updateOldFinanceRatioFillter(List<String> listCode, String timeString, String time);
	public Long getFinanceRatioByCode(String code);
	public Double getNewPriceByStockCode(String code);
	public List<Object> searchReportDataDetail(String code, String time);
	public List<Object> searchHeaderReportData(String searchPattern, String time);
	public List<Object> highestRevenue(String time);
	public List<Object> highestRevenueGrowth(String time);
	public List<Object> highestProfit(String time);
	public List<Object> highestProfitGrowth(String time);
	public List<Object> highestEPS(String time);
	public List<Object> highestEPSGrowth(String time);
	public List<Object> lowestPE(String time);
	public List<Object> lowestPB(String time);
	public List<Object> lowestMCNWC(String time);
	public List<Object> lowestEVEBITDA(String time);
}
