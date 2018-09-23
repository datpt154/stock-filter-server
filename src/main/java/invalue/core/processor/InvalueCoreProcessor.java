package invalue.core.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import invalue.core.dto.ApiDTOBuilder;
import invalue.core.dto.BasicFilterDTO;
import invalue.core.dto.CompareFilterDTO;
import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;
import invalue.core.dto.InputSearchStockDTO;
import invalue.core.dto.ObjectOutPutDTO;
import invalue.core.dto.ObjectOutPutDetailStockDTO;
import invalue.core.dto.RecommendationsDTO;
import invalue.core.dto.SearchItemDTO;
import invalue.core.entity.FinanceRatio;
import invalue.core.entity.NormalReport;
import invalue.core.entity.PlanOfYear;
import invalue.core.entity.RecommendationsOfStock;
import invalue.core.entity.Stock;
import invalue.core.repository.FinanceRatioQRepository;
import invalue.core.repository.FinanceRatioRepository;
import invalue.core.repository.FinanceRatioYRepository;
import invalue.core.repository.NormalReportRepository;
import invalue.core.repository.NormalReportYRepository;
import invalue.core.repository.PlanOfYearRepository;
import invalue.core.repository.RecommendationsOfStockRepository;
import invalue.core.repository.StockRepository;
import invalue.core.util.NumberFormatUtil;
import invalue.core.util.StringUtil;
import invalue.core.util.Unicode2English;

@Component
public class InvalueCoreProcessor {
	
    @Autowired
    FinanceRatioQRepository financeRatioQRepository;
    @Autowired
    FinanceRatioYRepository financeRatioYRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    NormalReportYRepository normalReportYRepository;
    
    @Autowired
    FinanceRatioRepository financeRatioRepository;
    
    @Autowired
    NormalReportRepository normalReportRepository;
    
    @Autowired
    RecommendationsOfStockRepository recommendationsOfStockRepository;
    
    @Autowired
    PlanOfYearRepository planOfYearRepository;
    
    
	
    public Collection<BasicFilterDTO> getFiltered(InputBasicFilterDTO inputBasicFilterDTO) {
    	List<Object> result;
//    	if("quarter".toUpperCase().equals(inputBasicFilterDTO.getTime().toUpperCase())) {
//    		result = financeRatioQRepository.getFinanceRatioFillter(inputBasicFilterDTO);
//    	}else {
//    		result = financeRatioYRepository.getFinanceRatioFillter(inputBasicFilterDTO);
//    	}
    	result = financeRatioRepository.getFinanceRatioFillter(inputBasicFilterDTO);
    	
    	Collection<BasicFilterDTO> basicFilterDTOs = new ArrayList<>();
    	if(!result.isEmpty()) {
	    	for (Object object : result) {
	    		BasicFilterDTO basicFilterDTO = ApiDTOBuilder.convertToBasicFilterDTOToDto(object,inputBasicFilterDTO.getSearchDataitems());
	    		basicFilterDTOs.add(basicFilterDTO);
			}
    	}
        return basicFilterDTOs;
    }
    
    public List<CompareFilterDTO> getCompareFiltered(InputCompareFilterDTO inputCompareFilterDTO){
    	List<Object> result;
//    	if("quarter".toUpperCase().equals(inputCompareFilterDTO.getTime().toUpperCase())) {
//    		result = financeRatioQRepository.getCompareFillter(inputCompareFilterDTO);
//    	}
//    	else {
//    		result = financeRatioYRepository.getCompareFillter(inputCompareFilterDTO);
//    	}
    	
    	result = financeRatioRepository.getCompareFillter(inputCompareFilterDTO);
    	CompareFilterDTO sumCompareFilterDTO= new CompareFilterDTO();
    	ArrayList<SearchItemDTO> sumList = new ArrayList<>();
    	List<CompareFilterDTO> compareFilterDTOs = new ArrayList<>();
    	if(!result.isEmpty()) {
	    	for (Object object : result) {
	    		CompareFilterDTO compareFilterDTO = ApiDTOBuilder.convertToCompareFilterDTO(object,inputCompareFilterDTO.getSearchDataitems());
	    		compareFilterDTOs.add(compareFilterDTO);
			}
	    	for (int i=0;i<compareFilterDTOs.size();i++) {
	    		if(i==0) {
	    			sumCompareFilterDTO.setSearchItems(new ArrayList<>());
	    			sumCompareFilterDTO.setPrice(compareFilterDTOs.get(i).getPrice());
	    			for(int j=0;j<compareFilterDTOs.get(i).getSearchItems().size();j++) {
	    				SearchItemDTO searchItem = new SearchItemDTO();
	    				searchItem.setCode(compareFilterDTOs.get(i).getSearchItems().get(j).getCode());
	    				searchItem.setValue(compareFilterDTOs.get(i).getSearchItems().get(j).getValue());
//	    				sumCompareFilterDTO.getSearchItems().add(compareFilterDTOs.get(i).getSearchItems().get(j));
	    				sumCompareFilterDTO.getSearchItems().add(searchItem);
    	    		}
    			}else {
    				for(int j=0;j<compareFilterDTOs.get(i).getSearchItems().size();j++) {
    					sumCompareFilterDTO.getSearchItems().get(j).setValue(sumCompareFilterDTO.getSearchItems().get(j).getValue()+compareFilterDTOs.get(i).getSearchItems().get(j).getValue());
    	    		}
    				sumCompareFilterDTO.setPrice(sumCompareFilterDTO.getPrice()+compareFilterDTOs.get(i).getPrice());
    			}
	    		
	    	}
	    	sumCompareFilterDTO.setCompanyCode("Trung binh");
	    	int totalItem=compareFilterDTOs.size();
	    	sumCompareFilterDTO.setPrice(NumberFormatUtil.formatDouble(sumCompareFilterDTO.getPrice()/totalItem,2));
	    	for(int i=0;i<sumCompareFilterDTO.getSearchItems().size();i++) {
	    		sumCompareFilterDTO.getSearchItems().get(i).setValue(NumberFormatUtil.formatDouble(sumCompareFilterDTO.getSearchItems().get(i).getValue()/totalItem,2));
	    	}
	    	compareFilterDTOs.add(sumCompareFilterDTO);
    	}
        return compareFilterDTOs;
    }

    @Transactional
	public String importFinanceratio(MultipartFile multipartFile, String timeString) {
		try {
	    	if(multipartFile!=null) {
			    File file= convert(multipartFile);
			    
			    if(null==file) {
			    	return "false";
			    }
			    FileInputStream input = new FileInputStream(file.getPath());
			    Workbook workbook = new XSSFWorkbook(input);
			    Sheet sheet = workbook.getSheetAt(0);
			    List<String> listUpdateOld= new ArrayList<>();
			    int rowIndex=-1;
			    if (timeString.matches("\\d{4}")||timeString.matches("\\d{4}Q\\d{1}")||"R".equals(timeString)) {
			    	for (Row row : sheet) {
				    	rowIndex++;
				    	if(rowIndex<3) {
				    		continue;
				    	}
				    	if(null==row.getCell(0)) {
				    		break;
				    	}
				    	FinanceRatio financeRatio = new FinanceRatio();
				    	financeRatio.setStockCode(row.getCell(0).getStringCellValue());
				    	System.out.println("row:"+financeRatio.getStockCode()); 
				    	financeRatio.setCode(financeRatio.getStockCode()+timeString);
				    	financeRatio.setType(1);
				    	
				    	if(null != row.getCell(1) && "NEW".equals(row.getCell(1).getStringCellValue())) {
				    		listUpdateOld.add(financeRatio.getStockCode());
				    		financeRatio.setStatus(0);
				    	}else {
				    		financeRatio.setStatus(1);
				    	}
				    	financeRatio.setCreatedTime(new Date());
				    	financeRatio.setTimeString(timeString);
				    	financeRatio.setStockId(1);
				    	if (timeString.matches("\\d{4}")) {
				    		financeRatio.setYQR("Y");
				    	}else if (timeString.matches("\\d{4}Q\\d{1}")) {
				    		financeRatio.setYQR("Q");
				    	}else if ("R".equals(timeString)) {
				    		financeRatio.setYQR("R");
				    	}
				    	//check code
				    	Long id = financeRatioRepository.getFinanceRatioByCode(financeRatio.getCode());
				    	if(null!=id) {
				    		financeRatio.setId(id);
				    	}
				    	financeRatio.setNetRevenue(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),1));
				    	financeRatio.setGrossProfit(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),1));
				    	financeRatio.setNetIncome(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),1));
				    	financeRatio.setShareSOustanding(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),1));
				    	financeRatio.setEps(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),2));
				    	financeRatio.setBookValue(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),2));
				    	financeRatio.setMarketPrice(NumberFormatUtil.formatDouble(row.getCell(8).getNumericCellValue(),2));
		//		    	financeRatio.setDayys(row.getCell(9).getNumericCellValue());
				    	financeRatio.setCapex(row.getCell(10).getNumericCellValue());//N?A
				    	financeRatio.setFcf(NumberFormatUtil.formatDouble(row.getCell(11).getNumericCellValue(),1));
				    	financeRatio.setEbit(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),1));
				    	financeRatio.setEbitda(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),1));
				    	financeRatio.setNnwc(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),1));
				    	financeRatio.setNetWorkingCapital(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),1));
				    	financeRatio.setEv(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),1));
				    	financeRatio.setMarketCapital(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),1));
				    	financeRatio.setNetRevenueYoy(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue()*100,1));
				    	financeRatio.setGrossProfitYoy(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue()*100,1));
				    	financeRatio.setEpsYoy(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue()*100,1));
				    	financeRatio.setEbitdaYoy(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue()*100,1));
				    	financeRatio.setDebtYoy(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue()*100,1));
				    	financeRatio.setEquityYoy(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue()*100,1));
				    	financeRatio.setMarketCapitalYoy(NumberFormatUtil.formatDouble(row.getCell(24).getNumericCellValue()*100,1));
				    	financeRatio.setTotalAssetsYoy(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue()*100,1));
				    	financeRatio.setPE(NumberFormatUtil.formatDouble(row.getCell(26).getNumericCellValue(),1));
				    	financeRatio.setPeg(NumberFormatUtil.formatDouble(row.getCell(27).getNumericCellValue(),1));
				    	financeRatio.setPB(NumberFormatUtil.formatDouble(row.getCell(28).getNumericCellValue(),1));
				    	financeRatio.setPS(NumberFormatUtil.formatDouble(row.getCell(29).getNumericCellValue(),1));
				    	financeRatio.setEvEbitda(NumberFormatUtil.formatDouble(row.getCell(30).getNumericCellValue(),1));
				    	financeRatio.setEvEbit(NumberFormatUtil.formatDouble(row.getCell(31).getNumericCellValue(),1));
				    	financeRatio.setEvFcf(NumberFormatUtil.formatDouble(row.getCell(32).getNumericCellValue(),1));
				    	financeRatio.setRevFcf(NumberFormatUtil.formatDouble(row.getCell(33).getNumericCellValue(),1));
				    	financeRatio.setMcCfo(NumberFormatUtil.formatDouble(row.getCell(34).getNumericCellValue(),1));
				    	financeRatio.setMcNwc(NumberFormatUtil.formatDouble(row.getCell(35).getNumericCellValue(),1));
				    	financeRatio.setFcff(NumberFormatUtil.formatDouble(row.getCell(36).getNumericCellValue(),1));
				    	financeRatio.setFcfe(NumberFormatUtil.formatDouble(row.getCell(37).getNumericCellValue(),1));
				    	financeRatio.setCapexRev(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue()*100,1));
				    	financeRatio.setRoic(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue()*100,1));
				    	financeRatio.setRoce(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue()*100,1));
				    	financeRatio.setRoe(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue()*100,1));
				    	financeRatio.setRoa(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue()*100,1));
				    	financeRatio.setGrossProfitMargin(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue()*100,1));
				    	financeRatio.setOperatingProfitMargin(NumberFormatUtil.formatDouble(row.getCell(44).getNumericCellValue()*100,1));
				    	financeRatio.setPretaxProfitMargin(NumberFormatUtil.formatDouble(row.getCell(45).getNumericCellValue()*100,1));
				    	financeRatio.setNetProfitMargin(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue()*100,1));
				    	financeRatio.setDivYield(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue()*100,1));
				    	financeRatio.setEbitRev(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue()*100,1));
				    	financeRatio.setEbitdaRev(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue()*100,1));
				    	financeRatio.setSalesToTotalAssets(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue()*100,1));
				    	financeRatio.setReceivableTurnover(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue()*100,1));
				    	financeRatio.setPayableTurnover(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue()*100,1));
				    	financeRatio.setInventoryTurnover(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue()*100,1));
				    	financeRatio.setDebtToAssetsRatio(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue()*100,1));
				    	financeRatio.setDebtToEquityRatio(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue()*100,1));
				    	financeRatio.setLongTimeDebtTotalCapitalazion(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue()*100,1));
				    	financeRatio.setInterestCoverage(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue()*100,1));
				    	financeRatio.setCurrentRatio(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue()*100,2));
				    	financeRatio.setQuickRatio(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue()*100,2));
				    	financeRatio.setCashRatio(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue()*100,2));
				    	financeRatio.setAccountReceivableToRevenue(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue()*100,2));
				    	financeRatio.setAccountReceivableToNetIncome(NumberFormatUtil.formatDouble(row.getCell(62).getNumericCellValue()*100,2));
				    	financeRatio.setAllowancesAndProvisionsToNetIncome(NumberFormatUtil.formatDouble(row.getCell(63).getNumericCellValue()*100,2));
				    	financeRatio.setFScore(NumberFormatUtil.formatDouble(row.getCell(64).getNumericCellValue(),0));
				    	financeRatio.setCScore(NumberFormatUtil.formatDouble(row.getCell(65).getNumericCellValue(),0));
				    	financeRatio.setMScore(NumberFormatUtil.formatDouble(row.getCell(66).getNumericCellValue(),2));
				    	financeRatio.setZScore(NumberFormatUtil.formatDouble(row.getCell(67).getNumericCellValue(),2));
				    	financeRatio.setNim(NumberFormatUtil.formatDouble(row.getCell(68).getNumericCellValue(),2));
				    	financeRatio.setLdr(NumberFormatUtil.formatDouble(row.getCell(69).getNumericCellValue(),2));
				    	financeRatio.setTangTruongTinDung(NumberFormatUtil.formatDouble(row.getCell(70).getNumericCellValue(),2));
				    	financeRatio.setTangTruongTienGui(NumberFormatUtil.formatDouble(row.getCell(71).getNumericCellValue(),2));
				    	financeRatio.setBenjaminGrahamNetNets(NumberFormatUtil.formatDouble(row.getCell(72).getNumericCellValue(),2));
				    	financeRatio.setBenjaminGrahamNcavBargain(NumberFormatUtil.formatDouble(row.getCell(73).getNumericCellValue(),2));
				    	financeRatio.setCanslimNotUpcom(NumberFormatUtil.formatDouble(row.getCell(74).getNumericCellValue(),2));
				    	financeRatio.setPhilipFisherGrowthBusinessInsiderY(NumberFormatUtil.formatDouble(row.getCell(75).getNumericCellValue(),2));
				    	financeRatio.setJohnNeffValue(NumberFormatUtil.formatDouble(row.getCell(76).getNumericCellValue(),2));
				    	financeRatio.setPeterLynchGrowth(NumberFormatUtil.formatDouble(row.getCell(77).getNumericCellValue(),2));
				    	financeRatio.setGrahamChecklistY(NumberFormatUtil.formatDouble(row.getCell(78).getNumericCellValue(),2));
				    	financeRatio.setSearch1(NumberFormatUtil.formatDouble(row.getCell(79).getNumericCellValue(),2));
				    	financeRatio.setSearch2(NumberFormatUtil.formatDouble(row.getCell(80).getNumericCellValue(),2));
				    	financeRatio.setSearch3(NumberFormatUtil.formatDouble(row.getCell(81).getNumericCellValue(),2));
				    	financeRatio.setSearch4(NumberFormatUtil.formatDouble(row.getCell(82).getNumericCellValue(),2));
				    	financeRatio.setSearch5(NumberFormatUtil.formatDouble(row.getCell(83).getNumericCellValue(),2));
				    	financeRatio.setSearch6(NumberFormatUtil.formatDouble(row.getCell(84).getNumericCellValue(),2));
				    	financeRatio.setSearch7(NumberFormatUtil.formatDouble(row.getCell(85).getNumericCellValue(),2));
				    	financeRatio.setSearch8(NumberFormatUtil.formatDouble(row.getCell(86).getNumericCellValue(),2));
				    	financeRatio.setSearch9(NumberFormatUtil.formatDouble(row.getCell(87).getNumericCellValue(),2));
				    	financeRatio.setSearch10(NumberFormatUtil.formatDouble(row.getCell(88).getNumericCellValue(),2));
				    	financeRatio.setForCouting(NumberFormatUtil.formatDouble(row.getCell(89).getNumericCellValue(),2));
				    	financeRatio.setCanslimNi(NumberFormatUtil.formatDouble(row.getCell(90).getNumericCellValue(),2));

				    	financeRatioRepository.save(financeRatio);
				    }
				    if(!listUpdateOld.isEmpty()) {
				    	financeRatioRepository.updateOldFinanceRatioFillter(listUpdateOld, timeString);
				    }
			    }else {
			    	return "time String không đúng";
			    }
			    
			    workbook.close();
			    
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
		}
		return "import file thanh cong";
	}
    
    @Transactional
	public String importCty(MultipartFile multipartFile) {
		try {
	    	if(multipartFile!=null) {
			    File currDir = new File(".");
	//		    String fileLocation = file.substring(0, path.length() - 1) + file.getOriginalFilename();
			    File file= convert(multipartFile);
			    if(null==file) {
			    	return "false";
			    }
			    FileInputStream input = new FileInputStream(file.getPath());
			    Workbook workbook = new XSSFWorkbook(input);
			    Sheet sheet = workbook.getSheetAt(0);
			    
			    for (Row row : sheet) {
			    	if(null==row.getCell(0)) {
			    		break;
			    	}
			    	Stock stock = new Stock();
			    	stock.setCode(row.getCell(0).getStringCellValue());
			    	System.out.println("row:"+stock.getCode()); 
			    	//check code
			    	Long id = stockRepository.getStockByCode(stock.getCode());
			    	if(null!=id) {
			    		stock.setId(id);
			    	}
			    	stock.setType(1);//
			    	stock.setStatus(0);
			    	stock.setCreatedTime(new Date());
			    	stock.setName(row.getCell(1).getStringCellValue());
			    	stock.setNameText(Unicode2English.removeDau(stock.getName()));
			    	stock.setStockExchangeCode(row.getCell(2).getStringCellValue());
			    	
//			    	if("HOSE".equals(row.getCell(2).getStringCellValue())) {
//			    		stock.setStockExchangeId(1);
//			    	}
//			    	else if("HNX".equals(row.getCell(2).getStringCellValue())) {
//			    		stock.setStockExchangeId(3);
//			    	}else if("UPCOM".equals(row.getCell(2).getStringCellValue())) {
//			    		stock.setStockExchangeId(3);
//			    	}else {
//			    		//loi
//			    	}
			    	if(null==row.getCell(3)) {
			    		stock.setType(0);
			    	}else if("CTCK".equals(row.getCell(3).getStringCellValue())) {
			    		stock.setType(1);
			    	}
			    	else if("BANKS".equals(row.getCell(3).getStringCellValue())) {
			    		stock.setType(2);
			    	}else {
			    		stock.setType(0);
			    	}
			    	
			    	if(null != row.getCell(4)) {
			    		stock.setYearEnd(row.getCell(4).getStringCellValue());
			    	}
			    	
			    	stockRepository.save(stock);
			    }
			    workbook.close();
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
		}
		return "import file thanh cong";
	}
	
	@Transactional
	public String importReportCty(MultipartFile multipartFile, String timeString) {
		try {
	    	if(multipartFile!=null) {
			    File file= convert(multipartFile);
			    
			    if(null==file) {
			    	return "false";
			    }
			    FileInputStream input = new FileInputStream(file.getPath());
			    Workbook workbook = new XSSFWorkbook(input);
			    Sheet sheet = workbook.getSheetAt(0);
			    List<String> listUpdateOld= new ArrayList<>();
			    int rowIndex=-1;
			    if (timeString.matches("\\d{4}")||timeString.matches("\\d{4}Q\\d{1}")||"R".equals(timeString)) {
			    	
			    	for (Row row : sheet) {
				    	rowIndex++;
				    	if(rowIndex<1) {
				    		continue;
				    	}
				    	if(null==row.getCell(0)) {
				    		break;
				    	}
				    	NormalReport normalReport = new NormalReport();
				    	normalReport.setStockCode(row.getCell(0).getStringCellValue());
				    	System.out.println("row:"+normalReport.getStockCode()); 
				    	
				    	normalReport.setCode(normalReport.getStockCode()+timeString);
				    	Long id = normalReportRepository.getNormalReportByCode(normalReport.getCode());
				    	if(null!=id) {
				    		normalReport.setId(id);
				    	}
				    	normalReport.setType(1);
				    	
//				    	if(null != row.getCell(1) && "NEW".equals(row.getCell(1).getStringCellValue())) {
//				    		listUpdateOld.add(financeRatioY.getStockCode());
//				    		financeRatioY.setStatus(0);
//				    	}else {
				    		normalReport.setStatus(1);
//				    	}
				    	normalReport.setCreatedTime(new Date());
				    	normalReport.setTimeString(timeString);
				    	normalReport.setStockId(1);
				    	if (timeString.matches("\\d{4}")) {
				    		normalReport.setYQR("Y");
				    	}else if (timeString.matches("\\d{4}Q\\d{1}")) {
				    		normalReport.setYQR("Q");
				    	}else if ("R".equals(timeString)) {
				    		normalReport.setYQR("R");
				    	}
				    	
				    	normalReport.setCurrentAsset(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),1));
				    	normalReport.setCashAndCashEquivalents(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),1));
				    	normalReport.setShortTermInvestments(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),1));
				    	normalReport.setAccountsReceivableShortTerm(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),1));
				    	normalReport.setInventories(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),1));
				    	normalReport.setOtherCurrentAssets(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),1));
				    	normalReport.setLongTermAssets(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),1));
				    	normalReport.setAccountReceivableLongTerm(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),1));
				    	normalReport.setFixedAssets(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),1));
				    	normalReport.setTangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),1));
				    	normalReport.setFinanceTangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),1));
				    	normalReport.setIntangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),1));
				    	normalReport.setInvestmentProperty(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue(),1));
				    	normalReport.setContructionInProgress(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue(),1));
				    	normalReport.setLongTermInvestment(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue(),1));
				    	normalReport.setGoodWill(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue(),1));
				    	normalReport.setOtherLongTermAssets(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue(),1));
				    	normalReport.setTotalAssets(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue(),1));
				    	normalReport.setCurrentLiabilities(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue(),1));
				    	normalReport.setAccountPayableToSuppliers(NumberFormatUtil.formatDouble(row.getCell(26).getNumericCellValue(),1));
				    	normalReport.setShortTermAdvancesFromCustomers(NumberFormatUtil.formatDouble(row.getCell(27).getNumericCellValue(),1));
				    	normalReport.setShortTermUnearnedRevenue(NumberFormatUtil.formatDouble(row.getCell(28).getNumericCellValue(),1));
				    	normalReport.setShortTermBorrowingsAndLiabilities(NumberFormatUtil.formatDouble(row.getCell(29).getNumericCellValue(),1));
				    	normalReport.setOtherShortTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(30).getNumericCellValue(),1));
				    	normalReport.setLongTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(31).getNumericCellValue(),1));
				    	normalReport.setLongTermAccountsPayable(NumberFormatUtil.formatDouble(row.getCell(32).getNumericCellValue(),1));
				    	normalReport.setLongTermadvancesFromCustomers(NumberFormatUtil.formatDouble(row.getCell(33).getNumericCellValue(),1));
				    	normalReport.setLongTermUnearnedRevenue(NumberFormatUtil.formatDouble(row.getCell(34).getNumericCellValue(),1));
				    	normalReport.setLongTermBorrowingsAndLiabilities(NumberFormatUtil.formatDouble(row.getCell(35).getNumericCellValue(),1));
				    	normalReport.setOtherLongTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(36).getNumericCellValue(),1));
				    	normalReport.setEquity(NumberFormatUtil.formatDouble(row.getCell(37).getNumericCellValue(),1));
				    	normalReport.setShareCapital(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue(),1));
				    	normalReport.setSharePremium(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue(),1));
				    	normalReport.setRetainedProfits(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue(),1));
				    	normalReport.setOtherCapitals(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue(),1));
				    	normalReport.setNonControllingInterest(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue(),1));
				    	normalReport.setTotalResources(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue(),1));
				    	normalReport.setNetRevenue(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue(),1));
				    	normalReport.setCostOfSales(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue(),1));
				    	normalReport.setGrossProfit(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue(),1));
				    	normalReport.setFinancialIncome(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue(),1));
				    	normalReport.setFinancialExpenses(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue(),1));
				    	normalReport.setInWhichInterestExpense(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue(),1));
				    	normalReport.setShareOfProfitInAssociates(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue(),1));
				    	normalReport.setSellingExpenses(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue(),1));
				    	normalReport.setGeneralAndAdministrationExpenses(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue(),1));
				    	normalReport.setNetOperatingProfit(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue(),1));
				    	normalReport.setOtherIncome(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue(),1));
				    	normalReport.setProfitBeforeTax(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue(),1));
				    	normalReport.setIncomeTaxExpense(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue(),1));
				    	normalReport.setNetProfitAfterTax(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue(),1));
				    	normalReport.setMinorityInterest(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue(),1));
				    	normalReport.setNetIncome(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue(),1));
				    	normalReport.setDepreciation(NumberFormatUtil.formatDouble(row.getCell(65).getNumericCellValue(),1));
				    	normalReport.setAllowancesAndProvisions(NumberFormatUtil.formatDouble(row.getCell(66).getNumericCellValue(),1));
				    	normalReport.setNetCashFlowsFromOperatingActivities(NumberFormatUtil.formatDouble(row.getCell(67).getNumericCellValue(),1));
				    	normalReport.setNetCashFlowsFromInvestingActivities(NumberFormatUtil.formatDouble(row.getCell(68).getNumericCellValue(),1));
				    	normalReport.setNetCashFlowsFromFinancingActivities(NumberFormatUtil.formatDouble(row.getCell(69).getNumericCellValue(),1));
				    	normalReport.setNetCashFlows(NumberFormatUtil.formatDouble(row.getCell(70).getNumericCellValue(),1));
				    	if(null!=row.getCell(71)) {
				    		normalReport.setPaymentsOfDividends(NumberFormatUtil.formatDouble(row.getCell(71).getNumericCellValue(),1));
				    	}


				    	normalReportRepository.save(normalReport);
				    }
				    
			    }
			    
			    workbook.close();
			    
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
		}
		return "import file thanh cong";
	}
	
	@Transactional
	public String importRecommandations(MultipartFile multipartFile) {
		try {
	    	if(multipartFile!=null) {
			    File currDir = new File(".");
	//		    String fileLocation = file.substring(0, path.length() - 1) + file.getOriginalFilename();
			    File file= convert(multipartFile);
			    if(null==file) {
			    	return "false";
			    }
			    FileInputStream input = new FileInputStream(file.getPath());
			    Workbook workbook = new XSSFWorkbook(input);
			    Sheet sheet = workbook.getSheetAt(0);
			    
			    int rowIndex=-1;
			    for (Row row : sheet) {
			    	rowIndex++;
			    	if(rowIndex<1) {
			    		continue;
			    	}
			    	if(null==row.getCell(0)) {
			    		break;
			    	}
			    	RecommendationsOfStock recommendationsOfStock = new RecommendationsOfStock();
			    	recommendationsOfStock.setStockCode(row.getCell(0).getStringCellValue());
			    	System.out.println("row:"+recommendationsOfStock.getStockCode()); 
			    	
			    	recommendationsOfStock.setType(1);//
			    	recommendationsOfStock.setStatus(0);
			    	recommendationsOfStock.setCreatedTime(new Date());
			    	recommendationsOfStock.setStockId(1);
			    	if(null!=row.getCell(1)) {
			    		recommendationsOfStock.setCompanyRecommendations(row.getCell(1).getStringCellValue());
			    	}
			    	if(null!=row.getCell(2)) {
			    		recommendationsOfStock.setCompanyRecommendationsAction(row.getCell(2).getStringCellValue());
			    	}
			    	if(null!=row.getCell(3)) {
			    		recommendationsOfStock.setTargetPrice(row.getCell(3).getNumericCellValue());
			    	}
			    	if(null!=row.getCell(4)) {
			    		recommendationsOfStock.setRevenue(row.getCell(4).getNumericCellValue());
			    	}
			    	if(null!=row.getCell(5)) {
			    		recommendationsOfStock.setPretaxProfit(row.getCell(5).getNumericCellValue());
			    	}
			    	if(null!=row.getCell(6)) {
			    		recommendationsOfStock.setNetProfit(row.getCell(6).getNumericCellValue());
			    	}
			    	if(null!=row.getCell(7)) {
			    		recommendationsOfStock.setTimeRecommendations(row.getCell(7).getDateCellValue());
			    	}
			    	if(null!=row.getCell(8)) {
			    		recommendationsOfStock.setPathFile(row.getCell(8).getStringCellValue());
			    	}
			    	
			    	recommendationsOfStockRepository.save(recommendationsOfStock);
			    }
			    workbook.close();
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
		}
		return "import file thanh cong";
	}
	
	@Transactional
	public String importPlanOfYear(MultipartFile multipartFile, String timeString) {
		try {
	    	if(multipartFile!=null) {
			    File currDir = new File(".");
	//		    String fileLocation = file.substring(0, path.length() - 1) + file.getOriginalFilename();
			    File file= convert(multipartFile);
			    if(null==file) {
			    	return "false";
			    }
			    FileInputStream input = new FileInputStream(file.getPath());
			    Workbook workbook = new XSSFWorkbook(input);
			    Sheet sheet = workbook.getSheetAt(0);
			    
			    int rowIndex=-1;
			    for (Row row : sheet) {
			    	rowIndex++;
			    	if(rowIndex<1) {
			    		continue;
			    	}
			    	if(null==row.getCell(0)) {
			    		break;
			    	}
			    	PlanOfYear planOfYear = new PlanOfYear();
			    	planOfYear.setStockCode(row.getCell(0).getStringCellValue());
			    	System.out.println("row:"+planOfYear.getStockCode()); 
			    	
			    	planOfYear.setCode(planOfYear.getStockCode()+timeString);
			    	planOfYear.setTimeString(timeString);
			    	Long id = planOfYearRepository.getPlanOfYearByCode(planOfYear.getStockCode());
			    	if(null!=id) {
			    		planOfYear.setId(id);
			    	}
			    	planOfYear.setType(1);//
			    	planOfYear.setStatus(0);
			    	planOfYear.setCreatedTime(new Date());
			    	planOfYear.setStockId(1);
			    	
			    	if(null!=row.getCell(1)) {
			    		planOfYear.setRevenue(row.getCell(1).getNumericCellValue());
			    	}
			    	if(null!=row.getCell(2)) {
			    		planOfYear.setPretaxProfit(row.getCell(2).getNumericCellValue());
			    	}
			    	if(null!=row.getCell(3)) {
			    		planOfYear.setNetProfit(row.getCell(3).getNumericCellValue());
			    	}
			    	
			    	if(null!=row.getCell(4)) {
			    		planOfYear.setPaymentsOfDividends(row.getCell(4).getNumericCellValue());
			    	}
			    	
			    	planOfYearRepository.save(planOfYear);
			    }
			    workbook.close();
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
		}
		return "import file thanh cong";
	}
	
	public List<ObjectOutPutDTO> autoCompleteStock(String searchPattern) {
		List<Object> result = stockRepository.autoCompleteStock(searchPattern.toUpperCase());
    	
		List<ObjectOutPutDTO> objectOutPutDTOs = new ArrayList<>();
    	if(!result.isEmpty()) {
	    	for (Object object : result) {
	    		ObjectOutPutDTO basicFilterDTO = ApiDTOBuilder.convertToObjectOutPutDTO(object);
	    		objectOutPutDTOs.add(basicFilterDTO);
			}
    	}
        return objectOutPutDTOs;
		
	}
	
	public ObjectOutPutDetailStockDTO detailStock(InputSearchStockDTO inputSearchStockDTO) {
		ObjectOutPutDetailStockDTO out = new ObjectOutPutDetailStockDTO();
		List<String> header= new ArrayList<>();
		String time;
		if(!StringUtil.isNullOrEmpty(inputSearchStockDTO.getTime()) && "quarter".toUpperCase().equals(inputSearchStockDTO.getTime().toUpperCase())) {
			time="Q";
		}else {
			time="Y";
		}
		List<Object> resultHeader =normalReportRepository.searchHeaderReportData(inputSearchStockDTO.getCode().toUpperCase(),time);
		if(null!=resultHeader && !resultHeader.isEmpty()) {
			
			if(!resultHeader.isEmpty()) {
				for (Object object : resultHeader) {
					header.add(object.toString());
				}
			}
			header.add("Year End");
			Collections.reverse(header);
			out.setHeaders(header);
			
			List<Object> result =normalReportRepository.searchReportData(inputSearchStockDTO.getCode().toUpperCase(),time);
			Collections.reverse(result);
			List<List<Object>> data = new ArrayList<>();
	    	if(!result.isEmpty()) {
	    		List<Object> ls0= new ArrayList<>();
	    		ls0.add("Revenue");
	    		ls0.add("$b");
	    		data.add(ls0);
	    		List<Object> ls1= new ArrayList<>();
	    		ls1.add("Operating Profit");
	    		ls1.add("$b");
	    		data.add(ls1);
	    		List<Object> ls2= new ArrayList<>();
	    		ls2.add("Net Profit");
	    		ls2.add("$b");
	    		data.add(ls2);
	    		List<Object> ls3= new ArrayList<>();
	    		ls3.add("Balance Sheet");
	    		data.add(ls3);
	    		List<Object> ls4= new ArrayList<>();
	    		ls4.add("Total asset");
	    		ls4.add("$b");
	    		data.add(ls4);
	    		List<Object> ls5= new ArrayList<>();
	    		ls5.add("Current asset");
	    		ls5.add("$b");
	    		data.add(ls5);
	    		List<Object> ls6= new ArrayList<>();
	    		ls6.add("Long-term asset");
	    		ls6.add("$b");
	    		data.add(ls6);
	    		List<Object> ls7= new ArrayList<>();
	    		ls7.add("Current liabilities");
	    		ls7.add("$b");
	    		data.add(ls7);
	    		List<Object> ls8= new ArrayList<>();
	    		ls8.add("Long-term liabilities ");
	    		ls8.add("$b");
	    		data.add(ls8);
	    		List<Object> ls9= new ArrayList<>();
	    		ls9.add("Equity");
	    		ls9.add("$b");
	    		data.add(ls9);
	    		List<Object> ls10= new ArrayList<>();
	    		ls10.add("Cash Flow");
	    		data.add(ls10);
	    		List<Object> ls11= new ArrayList<>();
	    		ls11.add("CFO");
	    		ls11.add("$b");
	    		data.add(ls11);
	    		List<Object> ls12= new ArrayList<>();
	    		ls12.add("CFI");
	    		ls12.add("$b");
	    		data.add(ls12);
	    		List<Object> ls13= new ArrayList<>();
	    		ls13.add("CFF");
	    		ls13.add("$b");
	    		data.add(ls13);
				List<Object> ls14= new ArrayList<>();
	    		ls14.add("NET CASH");
	    		ls14.add("$b");
	    		data.add(ls14);
	    		List<Object> ls15= new ArrayList<>();
	    		ls15.add("Profit");
	    		data.add(ls15);
	    		List<Object> ls16= new ArrayList<>();
	    		ls16.add("EPS ");
	    		ls16.add("$k");
	    		data.add(ls16);
	    		List<Object> ls17= new ArrayList<>();
	    		ls17.add("BV");
	    		ls17.add("$k");
	    		data.add(ls17);
	    		List<Object> ls18= new ArrayList<>();
	    		ls18.add("EBITDA");
	    		ls18.add("$b");
	    		data.add(ls18);
	    		List<Object> ls19= new ArrayList<>();
	    		ls19.add("Capitalization");
	    		ls19.add("$b");
	    		data.add(ls19);
	    		List<Object> ls20= new ArrayList<>();
	    		ls20.add("Growth");
	    		data.add(ls20);
	    		List<Object> ls21= new ArrayList<>();
	    		ls21.add("Revenue Growth");
	    		ls21.add("%");
	    		data.add(ls21);
	    		List<Object> ls22= new ArrayList<>();
	    		ls22.add("Net Income");
	    		ls22.add("%");
	    		data.add(ls22);
	    		List<Object> ls23= new ArrayList<>();
	    		ls23.add("EBITDA");
	    		ls23.add("%");
	    		data.add(ls23);
	    		List<Object> ls24= new ArrayList<>();
	    		ls24.add("ASSET");
	    		ls24.add("%");
	    		data.add(ls24);
	    		List<Object> ls25= new ArrayList<>();
	    		ls25.add("Capitalization");
	    		ls25.add("%");
	    		data.add(ls25);
	    		List<Object> ls26= new ArrayList<>();
	    		ls26.add("Margin");
	    		data.add(ls26);
	    		List<Object> ls27= new ArrayList<>();
	    		ls27.add("Gross Margin");
	    		ls27.add("%");
	    		data.add(ls27);
	    		List<Object> ls28= new ArrayList<>();
	    		ls28.add("Operating Margin");
	    		ls28.add("%");
	    		data.add(ls28);
	    		List<Object> ls29= new ArrayList<>();
	    		ls29.add("Profit margin");
	    		ls29.add("%");
	    		data.add(ls29);
	    		List<Object> ls30= new ArrayList<>();
	    		ls30.add("EBITDA/Rev");
	    		ls30.add("%");
	    		data.add(ls30);
	    		List<Object> ls31= new ArrayList<>();
	    		ls31.add("Profitabilities ratio");
	    		data.add(ls31);
	    		List<Object> ls32= new ArrayList<>();
	    		ls32.add("ROE");
	    		ls32.add("%");
	    		data.add(ls32);
	    		List<Object> ls33= new ArrayList<>();
	    		ls33.add("ROA");
	    		ls33.add("%");
	    		data.add(ls33);
	    		List<Object> ls34= new ArrayList<>();
	    		ls34.add("ROIC");
	    		ls34.add("%");
	    		data.add(ls34);
	    		List<Object> ls35= new ArrayList<>();
	    		ls35.add("ROCE");
	    		ls35.add("%");
	    		data.add(ls35);

		    	for (Object object : result) {
		    		Object[] arrayObject = (Object[]) object;
		    		for(int i=0; i<arrayObject.length;i++) {
		    			if(!"null".equals(arrayObject[i])) {
		    				data.get(i).add(arrayObject[i]);
		    			}
		    		}
				}
	    	}
//	    	Collections.reverse(data);
	    	out.setRows(data);
	    	List<Object> result1 =normalReportRepository.searchInfoCtyAnCurrenData(inputSearchStockDTO.getCode().toUpperCase(),resultHeader.get(resultHeader.size()-1).toString(),time);
	    	
	    	if(!result1.isEmpty()) {
	    		List<List<Object>> plans = new ArrayList<>();
	    		Object[] arrayObject = (Object[])result1.get(0);
	    		out.setCode(arrayObject[0].toString());
	    		out.setName(arrayObject[1].toString());
	    		
	    		List<Object> ls1= new ArrayList<>();
	    		ls1.add("Doanh thu(bil)");
	    		ls1.add(arrayObject[2]);
	    		plans.add(ls1);
	    		List<Object> ls2= new ArrayList<>();
	    		ls2.add("LNTT(bil)");
	    		ls2.add(arrayObject[3]);
	    		plans.add(ls2);
	    		List<Object> ls3= new ArrayList<>();
	    		ls3.add("LNST(bil)");
	    		ls3.add(arrayObject[4]);
	    		plans.add(ls3);
	    		List<Object> ls4= new ArrayList<>();
	    		ls4.add("Cổ tức(%)");
	    		ls4.add(arrayObject[5]);
	    		plans.add(ls4);
	    		out.setPlans(plans);
	    		
	    		List<List<Object>> valuation = new ArrayList<>();
	    		List<Object> ls5= new ArrayList<>();
	    		ls5.add("P/E");
	    		ls5.add(arrayObject[6]);
	    		valuation.add(ls5);
	    		List<Object> ls6= new ArrayList<>();
	    		ls6.add("Peg");
	    		ls6.add(arrayObject[7]);
	    		valuation.add(ls6);
	    		List<Object> ls7= new ArrayList<>();
	    		ls7.add("PB");
	    		ls7.add(arrayObject[8]);
	    		valuation.add(ls7);
	    		List<Object> ls8= new ArrayList<>();
	    		ls8.add("P/S");
	    		ls8.add(arrayObject[9]);
	    		valuation.add(ls8);
	    		List<Object> ls9= new ArrayList<>();
	    		ls9.add("Div Yield");
	    		ls9.add(arrayObject[10]);
	    		valuation.add(ls9);
	    		List<Object> ls10= new ArrayList<>();
	    		ls10.add("EV/EBITDA");
	    		ls10.add(arrayObject[11]);
	    		valuation.add(ls10);
	    		out.setValuation(valuation);
	    		
	    		List<List<Object>> score = new ArrayList<>();
	    		List<Object> ls11= new ArrayList<>();
	    		ls11.add("F-Score");
	    		ls11.add(arrayObject[12]);
	    		score.add(ls11);
	    		List<Object> ls12= new ArrayList<>();
	    		ls12.add("C-score");
	    		ls12.add(arrayObject[13]);
	    		score.add(ls12);
	    		List<Object> ls13= new ArrayList<>();
	    		ls13.add("M-score");
	    		ls13.add(arrayObject[14]);
	    		score.add(ls13);
	    		List<Object> ls14= new ArrayList<>();
	    		ls14.add("Z-score");
	    		ls14.add(arrayObject[15]);
	    		score.add(ls14);
	    		out.setScore(score);
	    		
	    		List<List<Object>> liquidityRatio = new ArrayList<>();
	    		List<Object> ls15= new ArrayList<>();
	    		ls15.add("Current ratio");
	    		ls15.add(arrayObject[16]);
	    		liquidityRatio.add(ls15);
	    		List<Object> ls16= new ArrayList<>();
	    		ls16.add("Quick ratio");
	    		ls16.add(arrayObject[17]);
	    		liquidityRatio.add(ls16);
	    		List<Object> ls17= new ArrayList<>();
	    		ls17.add("Cash ratio");
	    		ls17.add(arrayObject[18]);
	    		liquidityRatio.add(ls17);
	    		out.setLiquidityRatio(liquidityRatio);
	    		
	    		List<List<Object>> activityTurnover = new ArrayList<>();
	    		List<Object> ls18= new ArrayList<>();
	    		ls18.add("Receivable turnover");
	    		ls18.add(arrayObject[19]);
	    		activityTurnover.add(ls18);
	    		List<Object> ls19= new ArrayList<>();
	    		ls19.add("Payable turnover");
	    		ls19.add(arrayObject[20]);
	    		activityTurnover.add(ls19);
	    		List<Object> ls20= new ArrayList<>();
	    		ls20.add("inventory turnover");
	    		ls20.add(arrayObject[21]);
	    		activityTurnover.add(ls20);
	    		out.setActivityTurnover(activityTurnover);
	    		
	    		List<List<Object>> interpretationOfSolvencyRatios = new ArrayList<>();
	    		List<Object> ls21= new ArrayList<>();
	    		ls21.add("Debt to Assets ");
	    		ls21.add(arrayObject[22]);
	    		interpretationOfSolvencyRatios.add(ls21);
	    		List<Object> ls22= new ArrayList<>();
	    		ls22.add("Debt to Equity ");
	    		ls22.add(arrayObject[23]);
	    		interpretationOfSolvencyRatios.add(ls22);
	    		List<Object> ls23= new ArrayList<>();
	    		ls23.add("LT debt/Capitalazion");
	    		ls23.add(arrayObject[24]);
	    		interpretationOfSolvencyRatios.add(ls23);
	    		List<Object> ls24= new ArrayList<>();
	    		ls24.add("Interest coverage");
	    		ls24.add(arrayObject[25]);
	    		interpretationOfSolvencyRatios.add(ls24);
	    		out.setInterpretationOfSolvencyRatios(interpretationOfSolvencyRatios);
	    		
	    		
	    		List<List<Object>> riskRatio = new ArrayList<>();
	    		List<Object> ls25= new ArrayList<>();
	    		ls25.add("AR/Rev");
	    		ls25.add(arrayObject[26]);
	    		riskRatio.add(ls25);
	    		List<Object> ls26= new ArrayList<>();
	    		ls26.add("AR/Income");
	    		ls26.add(arrayObject[27]);
	    		riskRatio.add(ls26);
	    		List<Object> ls27= new ArrayList<>();
	    		ls27.add("A&P/Income");
	    		ls27.add(arrayObject[28]);
	    		riskRatio.add(ls27);
	    		out.setRiskRatio(riskRatio);
	    		if(null!=arrayObject[29])
	    			out.getHeaders().set(0, "Year End "+arrayObject[29]);
	    	}
	    	List<Object> resultR =normalReportRepository.searchReportData(inputSearchStockDTO.getCode().toUpperCase(),"R");
	    	if(null!=resultR && !resultR.isEmpty()) {
	    		for (Object object : resultR) {
	        		Object[] arrayObject = (Object[]) object;
	        		for(int i=0; i<arrayObject.length;i++) {
	        			if(!"null".equals(arrayObject[i])) {
	        				data.get(i).add(arrayObject[i]);
	        			}
	        		}
	    		}
	    		out.getHeaders().add("Rolling");
	    	}
		}
		
    	
    	Double price=financeRatioRepository.getNewPriceByStockCode(inputSearchStockDTO.getCode().toUpperCase());
    	if(null!=price) {
    		out.setPrice(price);
    	}
    	List<Object> resultRecommendations =recommendationsOfStockRepository.GetListRecommendationsOfStock(inputSearchStockDTO.getCode().toUpperCase());
    	
    	if(!resultRecommendations.isEmpty()) {
    		List<RecommendationsDTO> listRe= new ArrayList<>();
    		for (Object object : resultRecommendations) {
    			RecommendationsDTO re = new RecommendationsDTO();

    			Object[] arrayObject = (Object[]) object;
    			re.setCompanyRecommendations((String) arrayObject[0]);
    			re.setCompanyRecommendationsAction((String) arrayObject[1]);
    			re.setTargetPrice((Double) arrayObject[2]);
    			re.setRevenue((Double) arrayObject[3]);
    			re.setPretaxProfit((Double) arrayObject[4]);
    			re.setNetProfit((Double) arrayObject[5]);
    			re.setTimeRecommendations((Date) arrayObject[6]);
    			listRe.add(re);
    		}
    		out.setRecommended(listRe);
    	}
    	
        return out;
		
	}
	
	public ObjectOutPutDetailStockDTO detailStockMore(InputSearchStockDTO inputSearchStockDTO) {
		ObjectOutPutDetailStockDTO out = new ObjectOutPutDetailStockDTO();
		List<String> headerNormal= new ArrayList<>();
		List<String> headerFinance= new ArrayList<>();
		String time;
		System.out.println("--------------"+inputSearchStockDTO.getTime());
		System.out.println("--------------"+inputSearchStockDTO.getCode());
		if(!StringUtil.isNullOrEmpty(inputSearchStockDTO.getTime()) && "quarter".toUpperCase().equals(inputSearchStockDTO.getTime().toUpperCase())) {
			time="Q";
		}else {
			time="Y";
		}
		List<Object> resultHeaderNormal =normalReportRepository.searchHeaderReportData(inputSearchStockDTO.getCode().toUpperCase(),time);
		List<Object> resultHeaderFinance =financeRatioRepository.searchHeaderReportData(inputSearchStockDTO.getCode().toUpperCase(),time);
		
		headerNormal.add("Balance Sheet");
		if(!resultHeaderNormal.isEmpty()) {
			for (Object object : resultHeaderNormal) {
				headerNormal.add(object.toString());
			}
		}
		Collections.reverse(headerNormal);
		out.setHeaders(headerNormal);
		
		headerFinance.add("Chỉ số Tài chính");
		if(!resultHeaderFinance.isEmpty()) {
			for (Object object : resultHeaderNormal) {
				headerFinance.add(object.toString());
			}
		}
		Collections.reverse(headerFinance);
		out.setHeaders(headerFinance);
		
		List<Object> resultNormal =normalReportRepository.searchReportDataDetail(inputSearchStockDTO.getCode().toUpperCase(),time);
		List<Object> resultFinance =financeRatioRepository.searchReportDataDetail(inputSearchStockDTO.getCode().toUpperCase(),time);
		List<List<Object>> dataNormal = new ArrayList<>();
		List<List<Object>> dataFinance = new ArrayList<>();
		
    	if(!resultNormal.isEmpty()) {
    		List<Object> ls0= new ArrayList<>();
    		ls0.add("Current Asset");
    		dataNormal.add(ls0);
    		List<Object> ls1= new ArrayList<>();
    		ls1.add("Cash and Cash equivalents");
    		dataNormal.add(ls1);
    		List<Object> ls2= new ArrayList<>();
    		ls2.add("short-term Investments");
    		dataNormal.add(ls2);
    		List<Object> ls3= new ArrayList<>();
    		ls3.add("Accounts receivable - short-term");
    		dataNormal.add(ls3);
    		List<Object> ls4= new ArrayList<>();
    		ls4.add("Inventories");
    		dataNormal.add(ls4);
    		List<Object> ls5= new ArrayList<>();
    		ls5.add("other current assets");
    		dataNormal.add(ls5);
    		List<Object> ls6= new ArrayList<>();
    		ls6.add("Long-term assets");
    		dataNormal.add(ls6);
    		List<Object> ls7= new ArrayList<>();
    		ls7.add("Account receivable - Long-term");
    		dataNormal.add(ls7);
    		List<Object> ls8= new ArrayList<>();
    		ls8.add("Fixed assets");
    		dataNormal.add(ls8);
    		List<Object> ls9= new ArrayList<>();
    		ls9.add("Tangible fixed assets");
    		dataNormal.add(ls9);
    		List<Object> ls10= new ArrayList<>();
    		ls10.add("Finance tangible fixed assets");
    		dataNormal.add(ls10);
    		List<Object> ls11= new ArrayList<>();
    		ls11.add("intangible fixed assets");
    		dataNormal.add(ls11);
    		List<Object> ls12= new ArrayList<>();
    		ls12.add("Investment property");
    		dataNormal.add(ls12);
    		List<Object> ls13= new ArrayList<>();
    		ls13.add("Contruction in progress");
    		dataNormal.add(ls13);
    		List<Object> ls14= new ArrayList<>();
    		ls14.add("Long-term investment");
    		dataNormal.add(ls14);
    		List<Object> ls15= new ArrayList<>();
    		ls15.add("Good will");
    		dataNormal.add(ls15);
    		List<Object> ls16= new ArrayList<>();
    		ls16.add("Other long-term assets");
    		dataNormal.add(ls16);
    		List<Object> ls17= new ArrayList<>();
    		ls17.add("TOTAL ASSETS");
    		dataNormal.add(ls17);
    		List<Object> ls18= new ArrayList<>();
    		ls18.add("Current liabilities");
    		dataNormal.add(ls18);
    		List<Object> ls19= new ArrayList<>();
    		ls19.add("Account payable to suppliers");
    		dataNormal.add(ls19);
    		List<Object> ls20= new ArrayList<>();
    		ls20.add(" Advances from customers");
    		dataNormal.add(ls20);
    		List<Object> ls21= new ArrayList<>();
    		ls21.add(" Short-term Unearned revenue");
    		dataNormal.add(ls21);
    		List<Object> ls22= new ArrayList<>();
    		ls22.add(" Short-term borrowings and liabilities ");
    		dataNormal.add(ls22);
    		List<Object> ls23= new ArrayList<>();
    		ls23.add("Other short-term liabilities");
    		dataNormal.add(ls23);
    		List<Object> ls24= new ArrayList<>();
    		ls24.add(" Long-term liabilities ");
    		dataNormal.add(ls24);
    		List<Object> ls25= new ArrayList<>();
    		ls25.add("Long-term accounts payable  ");
    		dataNormal.add(ls25);
    		List<Object> ls26= new ArrayList<>();
    		ls26.add(" Advances from customers");
    		dataNormal.add(ls26);
    		List<Object> ls27= new ArrayList<>();
    		ls27.add("Long-term Unearned revenue");
    		dataNormal.add(ls27);
    		List<Object> ls28= new ArrayList<>();
    		ls28.add(" Long-term borrowings and liabilities");
    		dataNormal.add(ls28);
    		List<Object> ls29= new ArrayList<>();
    		ls29.add("Other long-term liabilities");
    		dataNormal.add(ls29);
    		List<Object> ls30= new ArrayList<>();
    		ls30.add("EQUITY ");
    		dataNormal.add(ls30);
    		List<Object> ls31= new ArrayList<>();
    		ls31.add(" Share capital");
    		dataNormal.add(ls31);
    		List<Object> ls32= new ArrayList<>();
    		ls32.add("Share premium ");
    		dataNormal.add(ls32);
    		List<Object> ls33= new ArrayList<>();
    		ls33.add("Retained profits ");
    		dataNormal.add(ls33);
    		List<Object> ls34= new ArrayList<>();
    		ls34.add("other capitals");
    		dataNormal.add(ls34);
    		List<Object> ls35= new ArrayList<>();
    		ls35.add("Non-controlling interest");
    		dataNormal.add(ls35);
    		List<Object> ls36= new ArrayList<>();
    		ls36.add("TOTAL RESOURCES");
    		dataNormal.add(ls36);
    		List<Object> ls37= new ArrayList<>();
    		ls37.add("Incom statement");
    		dataNormal.add(ls37);
    		List<Object> ls38= new ArrayList<>();
    		ls38.add("Net revenue");
    		dataNormal.add(ls38);
    		List<Object> ls39= new ArrayList<>();
    		ls39.add("Cost of sales");
    		dataNormal.add(ls39);
    		List<Object> ls40= new ArrayList<>();
    		ls40.add("Gross profit");
    		dataNormal.add(ls40);
    		List<Object> ls41= new ArrayList<>();
    		ls41.add("Financial income ");
    		dataNormal.add(ls41);
    		List<Object> ls42= new ArrayList<>();
    		ls42.add("Financial expenses");
    		dataNormal.add(ls42);
    		List<Object> ls43= new ArrayList<>();
    		ls43.add("In which: Interest expense ");
    		dataNormal.add(ls43);
    		List<Object> ls44= new ArrayList<>();
    		ls44.add("Share of profit in associates");
    		dataNormal.add(ls44);
    		List<Object> ls45= new ArrayList<>();
    		ls45.add("Selling expenses");
    		dataNormal.add(ls45);
    		List<Object> ls46= new ArrayList<>();
    		ls46.add("General and administration expenses ");
    		dataNormal.add(ls46);
    		List<Object> ls47= new ArrayList<>();
    		ls47.add("Net operating profit ");
    		dataNormal.add(ls47);
    		List<Object> ls48= new ArrayList<>();
    		ls48.add("Other income ");
    		dataNormal.add(ls48);
    		List<Object> ls49= new ArrayList<>();
    		ls49.add("Profit before tax");
    		dataNormal.add(ls49);
    		List<Object> ls50= new ArrayList<>();
    		ls50.add("Income tax expense ");
    		dataNormal.add(ls50);
    		List<Object> ls51= new ArrayList<>();
    		ls51.add("Net profit after tax ");
    		dataNormal.add(ls51);
    		List<Object> ls52= new ArrayList<>();
    		ls52.add("Minority interest");
    		dataNormal.add(ls52);
    		List<Object> ls53= new ArrayList<>();
    		ls53.add("Net Income");
    		dataNormal.add(ls53);
    		List<Object> ls54= new ArrayList<>();
    		ls54.add("Cash flows statement");
    		dataNormal.add(ls54);
    		List<Object> ls55= new ArrayList<>();
    		ls55.add("Depreciation ");
    		dataNormal.add(ls55);
    		List<Object> ls56= new ArrayList<>();
    		ls56.add("Allowances and provisions");
    		dataNormal.add(ls56);
    		List<Object> ls57= new ArrayList<>();
    		ls57.add("Net cash flows from operating activities ");
    		dataNormal.add(ls57);
    		List<Object> ls58= new ArrayList<>();
    		ls58.add("Net cash flows from investing activities ");
    		dataNormal.add(ls58);
    		List<Object> ls59= new ArrayList<>();
    		ls59.add("Net cash flows from financing activities ");
    		dataNormal.add(ls59);
    		List<Object> ls60= new ArrayList<>();
    		ls60.add("Payments of dividends ");
    		dataNormal.add(ls60);


	    	for (Object object : resultNormal) {
	    		Object[] arrayObject = (Object[]) object;
	    		for(int i=0; i<arrayObject.length;i++) {
	    			if(!"null".equals(arrayObject[i])) {
	    				dataNormal.get(i).add(arrayObject[i]);
	    			}
	    		}
			}
    	}
    	Collections.reverse(dataNormal);
    	out.setRows(dataNormal);
    	
    	if(!resultFinance.isEmpty()) {
    		List<Object> ls0= new ArrayList<>();
    		ls0.add("Finance data");
    		dataFinance.add(ls0);
    		List<Object> ls1= new ArrayList<>();
    		ls1.add("Net revenue");
    		dataFinance.add(ls1);
    		List<Object> ls2= new ArrayList<>();
    		ls2.add("Gross profit");
    		dataFinance.add(ls2);
    		List<Object> ls3= new ArrayList<>();
    		ls3.add("Net income");
    		dataFinance.add(ls3);
    		List<Object> ls4= new ArrayList<>();
    		ls4.add("Share's oustanding");
    		dataFinance.add(ls4);
    		List<Object> ls5= new ArrayList<>();
    		ls5.add("EPS");
    		dataFinance.add(ls5);
    		List<Object> ls6= new ArrayList<>();
    		ls6.add("Book value");
    		dataFinance.add(ls6);
    		List<Object> ls7= new ArrayList<>();
    		ls7.add("Market price");
    		dataFinance.add(ls7);
    		List<Object> ls8= new ArrayList<>();
    		ls8.add("Capex ");
    		dataFinance.add(ls8);
    		List<Object> ls9= new ArrayList<>();
    		ls9.add("FCF");
    		dataFinance.add(ls9);
    		List<Object> ls10= new ArrayList<>();
    		ls10.add("EBIT");
    		dataFinance.add(ls10);
    		List<Object> ls11= new ArrayList<>();
    		ls11.add("EBITDA");
    		dataFinance.add(ls11);
    		List<Object> ls12= new ArrayList<>();
    		ls12.add("NNWC");
    		dataFinance.add(ls12);
    		List<Object> ls13= new ArrayList<>();
    		ls13.add("Net working capital");
    		dataFinance.add(ls13);
    		List<Object> ls14= new ArrayList<>();
    		ls14.add("EV");
    		dataFinance.add(ls14);
    		List<Object> ls15= new ArrayList<>();
    		ls15.add("Market capital");
    		dataFinance.add(ls15);
    		List<Object> ls16= new ArrayList<>();
    		ls16.add("%YOY increase");
    		dataFinance.add(ls16);
    		List<Object> ls17= new ArrayList<>();
    		ls17.add("Net revenue (%YOY)");
    		dataFinance.add(ls17);
    		List<Object> ls18= new ArrayList<>();
    		ls18.add("Gross profit (%YOY)");
    		dataFinance.add(ls18);
    		List<Object> ls19= new ArrayList<>();
    		ls19.add("EPS (%YOY)");
    		dataFinance.add(ls19);
    		List<Object> ls20= new ArrayList<>();
    		ls20.add("EBITDA (%YOY)");
    		dataFinance.add(ls20);
    		List<Object> ls21= new ArrayList<>();
    		ls21.add("Debt (%YOY)");
    		dataFinance.add(ls21);
    		List<Object> ls22= new ArrayList<>();
    		ls22.add("Equity (%YOY)");
    		dataFinance.add(ls22);
    		List<Object> ls23= new ArrayList<>();
    		ls23.add("Market capital (%YOY)");
    		dataFinance.add(ls23);
    		List<Object> ls24= new ArrayList<>();
    		ls24.add("Total assets (%YOY)");
    		dataFinance.add(ls24);
    		List<Object> ls25= new ArrayList<>();
    		ls25.add("Price ratio");
    		dataFinance.add(ls25);
    		List<Object> ls26= new ArrayList<>();
    		ls26.add("P/E");
    		dataFinance.add(ls26);
    		List<Object> ls27= new ArrayList<>();
    		ls27.add("Peg");
    		dataFinance.add(ls27);
    		List<Object> ls28= new ArrayList<>();
    		ls28.add("P/B");
    		dataFinance.add(ls28);
    		List<Object> ls29= new ArrayList<>();
    		ls29.add("P/S");
    		dataFinance.add(ls29);
    		List<Object> ls30= new ArrayList<>();
    		ls30.add("EV/EBITDA");
    		dataFinance.add(ls30);
    		List<Object> ls31= new ArrayList<>();
    		ls31.add("EV/EBIT");
    		dataFinance.add(ls31);
    		List<Object> ls32= new ArrayList<>();
    		ls32.add("EV/FCF");
    		dataFinance.add(ls32);
    		List<Object> ls33= new ArrayList<>();
    		ls33.add("Rev/FCF");
    		dataFinance.add(ls33);
    		List<Object> ls34= new ArrayList<>();
    		ls34.add("MC/CFO");
    		dataFinance.add(ls34);
    		List<Object> ls35= new ArrayList<>();
    		ls35.add("MC/NWC");
    		dataFinance.add(ls35);
    		List<Object> ls36= new ArrayList<>();
    		ls36.add("Profitabilities ratio");
    		dataFinance.add(ls36);
    		List<Object> ls37= new ArrayList<>();
    		ls37.add("Capex/Rev");
    		dataFinance.add(ls37);
    		List<Object> ls38= new ArrayList<>();
    		ls38.add("ROIC");
    		dataFinance.add(ls38);
    		List<Object> ls39= new ArrayList<>();
    		ls39.add("ROCE");
    		dataFinance.add(ls39);
    		List<Object> ls40= new ArrayList<>();
    		ls40.add("ROE");
    		dataFinance.add(ls40);
    		List<Object> ls41= new ArrayList<>();
    		ls41.add("ROA");
    		dataFinance.add(ls41);
    		List<Object> ls42= new ArrayList<>();
    		ls42.add("Margin");
    		dataFinance.add(ls42);
    		List<Object> ls43= new ArrayList<>();
    		ls43.add("Gross profit margin");
    		dataFinance.add(ls43);
    		List<Object> ls44= new ArrayList<>();
    		ls44.add("Operating profit Margin");
    		dataFinance.add(ls44);
    		List<Object> ls45= new ArrayList<>();
    		ls45.add("Pretax profit Margin");
    		dataFinance.add(ls45);
    		List<Object> ls46= new ArrayList<>();
    		ls46.add("Net profit margin");
    		dataFinance.add(ls46);
    		List<Object> ls47= new ArrayList<>();
    		ls47.add("Div Yield");
    		dataFinance.add(ls47);
    		List<Object> ls48= new ArrayList<>();
    		ls48.add("EBIT/REV");
    		dataFinance.add(ls48);
    		List<Object> ls49= new ArrayList<>();
    		ls49.add("EBITDA/REV");
    		dataFinance.add(ls49);
    		List<Object> ls50= new ArrayList<>();
    		ls50.add("Activity turnover");
    		dataFinance.add(ls50);
    		List<Object> ls51= new ArrayList<>();
    		ls51.add("sales-to-total assets");
    		dataFinance.add(ls51);
    		List<Object> ls52= new ArrayList<>();
    		ls52.add("Receivable turnover");
    		dataFinance.add(ls52);
    		List<Object> ls53= new ArrayList<>();
    		ls53.add("Payable turnover");
    		dataFinance.add(ls53);
    		List<Object> ls54= new ArrayList<>();
    		ls54.add("inventory turnover");
    		dataFinance.add(ls54);
    		List<Object> ls55= new ArrayList<>();
    		ls55.add("Interpretation of Solvency ratios");
    		dataFinance.add(ls55);
    		List<Object> ls56= new ArrayList<>();
    		ls56.add("debt-to-assets ratio");
    		dataFinance.add(ls56);
    		List<Object> ls57= new ArrayList<>();
    		ls57.add("Debt-to-equity ratio");
    		dataFinance.add(ls57);
    		List<Object> ls58= new ArrayList<>();
    		ls58.add("Long-time debt/total capitalazion");
    		dataFinance.add(ls58);
    		List<Object> ls59= new ArrayList<>();
    		ls59.add("interest coverage");
    		dataFinance.add(ls59);
    		List<Object> ls60= new ArrayList<>();
    		ls60.add("Liquidity Ratio");
    		dataFinance.add(ls60);
    		List<Object> ls61= new ArrayList<>();
    		ls61.add("Current ratio");
    		dataFinance.add(ls61);
    		List<Object> ls62= new ArrayList<>();
    		ls62.add("Quick ratio");
    		dataFinance.add(ls62);
    		List<Object> ls63= new ArrayList<>();
    		ls63.add("Cash ratio");
    		dataFinance.add(ls63);
    		List<Object> ls64= new ArrayList<>();
    		ls64.add("Risk ratio");
    		dataFinance.add(ls64);
    		List<Object> ls65= new ArrayList<>();
    		ls65.add("Account receivable-to-revenue");
    		dataFinance.add(ls65);
    		List<Object> ls66= new ArrayList<>();
    		ls66.add("Account receivable-to-net income");
    		dataFinance.add(ls66);
    		List<Object> ls67= new ArrayList<>();
    		ls67.add("Allowances and provisions-to-net income");
    		dataFinance.add(ls67);
    		List<Object> ls68= new ArrayList<>();
    		ls68.add("Score");
    		dataFinance.add(ls68);
    		List<Object> ls69= new ArrayList<>();
    		ls69.add("F-Score");
    		dataFinance.add(ls69);
    		List<Object> ls70= new ArrayList<>();
    		ls70.add("C-score");
    		dataFinance.add(ls70);
    		List<Object> ls71= new ArrayList<>();
    		ls71.add("M-score");
    		dataFinance.add(ls71);
    		List<Object> ls72= new ArrayList<>();
    		ls72.add("Z-score");
    		dataFinance.add(ls72);



	    	for (Object object : resultNormal) {
	    		Object[] arrayObject = (Object[]) object;
	    		for(int i=0; i<arrayObject.length;i++) {
	    			if(!"null".equals(arrayObject[i])) {
	    				dataNormal.get(i).add(arrayObject[i]);
	    			}
	    		}
			}
    	}
    	Collections.reverse(dataNormal);
    	out.setRows(dataNormal);
        return out;
		
	}
	
	public File convert(MultipartFile file)
	{    
		try {
		    File convFile = new File(file.getOriginalFilename());
		    convFile.createNewFile(); 
		    FileOutputStream fos = new FileOutputStream(convFile); 
		    fos.write(file.getBytes());
		    fos.close(); 
		    return convFile;
		}catch(Exception ex) {
			System.out.println(ex);
		}
		return null;
	}
}
