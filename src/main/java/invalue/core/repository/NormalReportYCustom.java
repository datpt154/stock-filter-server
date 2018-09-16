package invalue.core.repository;

import java.util.List;
/**
 * Created by HUYNP4 on 15/09/2018.
 */
public interface NormalReportYCustom {
	public List<Object> searchReportData(String searchPattern);
	public List<Object> searchInfoCtyAnCurrenData(String searchPattern, String timeString);
	public List<Object> searchHeaderReportData(String searchPattern);
}
