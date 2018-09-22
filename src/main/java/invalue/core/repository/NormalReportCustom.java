package invalue.core.repository;

import java.util.List;
/**
 * Created by HUYNP4 on 15/09/2018.
 */
public interface NormalReportCustom {
	public List<Object> searchReportData(String searchPattern, String time);
	public List<Object> searchReportDataDetail(String searchPattern, String time);
	public List<Object> searchInfoCtyAnCurrenData(String searchPattern, String timeString, String time);
	public List<Object> searchHeaderReportData(String searchPattern, String time);
	public Long getNormalReportByCode(String code );
}
