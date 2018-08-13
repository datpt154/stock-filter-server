package invalue.core.repository;

import java.util.List;

public interface NormalReportYCustom {
	public List<Object> searchReportData(String searchPattern);
	public List<Object> searchInfoCtyAnCurrenData(String searchPattern, String timeString);
	public List<Object> searchHeaderReportData(String searchPattern);
}
