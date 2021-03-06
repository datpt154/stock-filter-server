package invalue.core.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import invalue.core.constant.ConstantManager;
import invalue.core.dto.ApiDTOBuilder;
import invalue.core.dto.BasicFilterDTO;
import invalue.core.dto.CompareFilterDTO;
import invalue.core.dto.FilterNewsDTO;
import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;
import invalue.core.dto.InputSearchStockDTO;
import invalue.core.dto.ListShortNewsDTO;
import invalue.core.dto.LoginOutputsDTO;
import invalue.core.dto.NewsDTO;
import invalue.core.dto.ObjectOutPutDTO;
import invalue.core.dto.ObjectOutPutDetailStockDTO;
import invalue.core.dto.ObjectOutPutDetailStockMoreDTO;
import invalue.core.dto.ObjectOutPutRegisterDTO;
import invalue.core.dto.OutPutLoginDTO;
import invalue.core.dto.OutPutScreenBenjamin;
import invalue.core.dto.OutPutScreenBreakOut;
import invalue.core.dto.OutPutScreenCANSLIM;
import invalue.core.dto.OutPutScreenDBTTM;
import invalue.core.dto.OutPutScreenGrahamChecklist;
import invalue.core.dto.OutPutScreenJohnNeffValue;
import invalue.core.dto.OutPutScreenPeterLynchGrowth;
import invalue.core.dto.OutPutScreenPhilipFisherGrowth;
import invalue.core.dto.RecommendationsDTO;
import invalue.core.dto.RegisterInputsDTO;
import invalue.core.dto.ScreenDTO;
import invalue.core.dto.ScreenPageDTO;
import invalue.core.dto.SearchItemDTO;
import invalue.core.dto.ShortNewsDTO;
import invalue.core.entity.BreakOut;
import invalue.core.entity.FinanceRatio;
import invalue.core.entity.News;
import invalue.core.entity.NormalReport;
import invalue.core.entity.PlanOfYear;
import invalue.core.entity.RecommendationsOfStock;
import invalue.core.entity.Stock;
import invalue.core.entity.User;
import invalue.core.repository.BreakOutRepository;
import invalue.core.repository.FinanceRatioRepository;
import invalue.core.repository.NewsRepository;
import invalue.core.repository.NormalReportRepository;
import invalue.core.repository.PlanOfYearRepository;
import invalue.core.repository.RecommendationsOfStockRepository;
import invalue.core.repository.StockRepository;
import invalue.core.repository.SystemConfigRepository;
import invalue.core.repository.UserRepository;
import invalue.core.util.DateUtil;
import invalue.core.util.NumberFormatUtil;
import invalue.core.util.StringUtil;
import invalue.core.util.Unicode2English;

@Component
public class InvalueCoreProcessor {

    @Autowired
    StockRepository stockRepository;
    
    @Autowired
    FinanceRatioRepository financeRatioRepository;
    
    @Autowired
    NormalReportRepository normalReportRepository;
    
    @Autowired
    RecommendationsOfStockRepository recommendationsOfStockRepository;
    
    @Autowired
    PlanOfYearRepository planOfYearRepository;
    
    @Autowired
    SystemConfigRepository systemConfigRepository;
    
    @Autowired
    BreakOutRepository breakOutRepository;
    
    @Autowired
    NewsRepository newsRepository;
    
    @Autowired
    UserRepository urerRepository;
    
	
    public Collection<BasicFilterDTO> getFiltered(InputBasicFilterDTO inputBasicFilterDTO) {
    	List<Object> result;
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
	    				sumCompareFilterDTO.getSearchItems().add(searchItem);
    	    		}
    			}else {
    				for(int j=0;j<compareFilterDTOs.get(i).getSearchItems().size();j++) {
    					sumCompareFilterDTO.getSearchItems().get(j).setValue(sumCompareFilterDTO.getSearchItems().get(j).getValue()+compareFilterDTOs.get(i).getSearchItems().get(j).getValue());
    	    		}
    				sumCompareFilterDTO.setPrice(sumCompareFilterDTO.getPrice()+compareFilterDTOs.get(i).getPrice());
    			}
	    		
	    	}
	    	sumCompareFilterDTO.setCompanyCode(ConstantManager.average);
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
    	String logRow="";
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
			    String time="";
			    if (timeString.matches("\\d{4}")||timeString.matches("\\d{4}Q\\d{1}")||"R".equals(timeString)) {
			    	if (timeString.matches("\\d{4}")) {
			    		time="Y";
			    	}else if (timeString.matches("\\d{4}Q\\d{1}")) {
			    		time="Q";
			    	}else if ("R".equals(timeString)) {
			    		time="R";
			    	}
			    	for (Row row : sheet) {
				    	rowIndex++;
				    	if(rowIndex<1) {
				    		continue;
				    	}
				    	if(null==row.getCell(0)||StringUtil.isNullOrEmpty(row.getCell(0).toString())) {
				    		break;
				    	}
				    	FinanceRatio financeRatio = new FinanceRatio();
				    	financeRatio.setStockCode(row.getCell(0).getStringCellValue());
				    	System.out.println("row:"+financeRatio.getStockCode()); 
				    	logRow=financeRatio.getStockCode();
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
				    	financeRatio.setYQR(time);
				    	//check code
				    	Long id = financeRatioRepository.getFinanceRatioByCode(financeRatio.getCode());
				    	if(null!=id) {
				    		financeRatio.setId(id);
				    	}
				    	financeRatio.setNetRevenue(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),2));
				    	financeRatio.setGrossProfit(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),2));
				    	financeRatio.setNetIncome(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),2));
				    	financeRatio.setShareSOustanding(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),2));
				    	financeRatio.setEps(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),2));
				    	financeRatio.setBookValue(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),2));
				    	financeRatio.setMarketPrice(NumberFormatUtil.formatDouble(row.getCell(8).getNumericCellValue(),2));
		//		    	financeRatio.setDayys(row.getCell(9).getNumericCellValue());
				    	financeRatio.setCapex(NumberFormatUtil.formatDouble(row.getCell(10).getNumericCellValue(),2));
				    	financeRatio.setFcf(NumberFormatUtil.formatDouble(row.getCell(11).getNumericCellValue(),2));
				    	financeRatio.setEbit(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),2));
				    	financeRatio.setEbitda(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),2));
				    	financeRatio.setNnwc(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),2));
				    	financeRatio.setNetWorkingCapital(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),2));
				    	financeRatio.setEv(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),2));
				    	financeRatio.setMarketCapital(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),2));
				    	financeRatio.setNetRevenueYoy(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue()*100,2));
				    	financeRatio.setGrossProfitYoy(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue()*100,2));
				    	financeRatio.setEpsYoy(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue()*100,2));
				    	financeRatio.setEbitdaYoy(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue()*100,2));
				    	financeRatio.setDebtYoy(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue()*100,2));
				    	financeRatio.setEquityYoy(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue()*100,2));
				    	financeRatio.setMarketCapitalYoy(NumberFormatUtil.formatDouble(row.getCell(24).getNumericCellValue()*100,2));
				    	financeRatio.setTotalAssetsYoy(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue()*100,2));
				    	financeRatio.setPE(NumberFormatUtil.formatDouble(row.getCell(26).getNumericCellValue(),2));
				    	financeRatio.setPeg(NumberFormatUtil.formatDouble(row.getCell(27).getNumericCellValue(),2));
				    	financeRatio.setPB(NumberFormatUtil.formatDouble(row.getCell(28).getNumericCellValue(),2));
				    	financeRatio.setPS(NumberFormatUtil.formatDouble(row.getCell(29).getNumericCellValue(),2));
				    	financeRatio.setEvEbitda(NumberFormatUtil.formatDouble(row.getCell(30).getNumericCellValue(),2));
				    	financeRatio.setEvEbit(NumberFormatUtil.formatDouble(row.getCell(31).getNumericCellValue(),2));
				    	financeRatio.setEvFcf(NumberFormatUtil.formatDouble(row.getCell(32).getNumericCellValue(),2));
				    	financeRatio.setRevFcf(NumberFormatUtil.formatDouble(row.getCell(33).getNumericCellValue(),2));
				    	financeRatio.setMcCfo(NumberFormatUtil.formatDouble(row.getCell(34).getNumericCellValue(),2));
				    	financeRatio.setMcNwc(NumberFormatUtil.formatDouble(row.getCell(35).getNumericCellValue(),2));
				    	financeRatio.setFcff(NumberFormatUtil.formatDouble(row.getCell(36).getNumericCellValue(),2));
				    	financeRatio.setFcfe(NumberFormatUtil.formatDouble(row.getCell(37).getNumericCellValue(),2));
				    	financeRatio.setCapexRev(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue()*100,2));
				    	financeRatio.setRoic(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue()*100,2));
				    	financeRatio.setRoce(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue()*100,2));
				    	financeRatio.setRoe(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue()*100,2));
				    	financeRatio.setRoa(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue()*100,2));
				    	financeRatio.setGrossProfitMargin(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue()*100,2));
				    	financeRatio.setOperatingProfitMargin(NumberFormatUtil.formatDouble(row.getCell(44).getNumericCellValue()*100,2));
				    	financeRatio.setPretaxProfitMargin(NumberFormatUtil.formatDouble(row.getCell(45).getNumericCellValue()*100,2));
				    	financeRatio.setNetProfitMargin(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue()*100,2));
				    	financeRatio.setDivYield(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue()*100,2));
				    	financeRatio.setEbitRev(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue()*100,2));
				    	financeRatio.setEbitdaRev(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue()*100,2));
				    	financeRatio.setSalesToTotalAssets(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue(),2));
				    	financeRatio.setReceivableTurnover(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue(),2));
				    	financeRatio.setPayableTurnover(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue(),2));
				    	financeRatio.setInventoryTurnover(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue(),2));
				    	financeRatio.setDebtToAssetsRatio(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue(),2));
				    	financeRatio.setDebtToEquityRatio(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue(),2));
				    	financeRatio.setLongTimeDebtTotalCapitalazion(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue(),2));
				    	financeRatio.setInterestCoverage(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue(),2));
				    	financeRatio.setCurrentRatio(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue(),2));
				    	financeRatio.setQuickRatio(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue(),2));
				    	financeRatio.setCashRatio(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue(),2));
				    	financeRatio.setAccountReceivableToRevenue(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue(),2));
				    	financeRatio.setAccountReceivableToNetIncome(NumberFormatUtil.formatDouble(row.getCell(62).getNumericCellValue(),2));
				    	financeRatio.setAllowancesAndProvisionsToNetIncome(NumberFormatUtil.formatDouble(row.getCell(63).getNumericCellValue(),2));
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
				    	financeRatioRepository.updateOldFinanceRatioFillter(listUpdateOld, timeString, time);
				    }
			    }else {
			    	return "time String không đúng";
			    }
			    
			    workbook.close();
			    
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
			return logRow+"=====Loi khi import file:"+ex.getMessage();
		}
		return "import file thanh cong";
	}
    
    @Transactional
	public String importCty(MultipartFile multipartFile) {
    	String logRow="";
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
			    int rowIndex=0;
			    for (Row row : sheet) {
			    	rowIndex++;
			    	if(rowIndex<1) {
			    		continue;
			    	}
			    	if(null==row.getCell(0)||StringUtil.isNullOrEmpty(row.getCell(0).toString())) {
			    		break;
			    	}
			    	Stock stock = new Stock();
			    	stock.setCode(row.getCell(0).getStringCellValue());
			    	System.out.println("row:"+stock.getCode()); 
			    	logRow=stock.getCode();
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
			return logRow+"=====Loi khi import file:"+ex.getMessage();
		}
		return "import file thanh cong";
	}
	
	@Transactional
	public String importReportCty(MultipartFile multipartFile, String timeString) {
		String logRow="";
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
			    String time="";
			    if (timeString.matches("\\d{4}")||timeString.matches("\\d{4}Q\\d{1}")||"R".equals(timeString)) {
			    	if (timeString.matches("\\d{4}")) {
			    		time="Y";
			    	}else if (timeString.matches("\\d{4}Q\\d{1}")) {
			    		time="Q";
			    	}else if ("R".equals(timeString)) {
			    		time="R";
			    	}
			    	for (Row row : sheet) {
				    	rowIndex++;
				    	if(rowIndex<1) {
				    		continue;
				    	}
				    	if(null==row.getCell(0)||StringUtil.isNullOrEmpty(row.getCell(0).toString())) {
				    		break;
				    	}
				    	NormalReport normalReport = new NormalReport();
				    	normalReport.setStockCode(row.getCell(0).getStringCellValue());
				    	System.out.println("row:"+normalReport.getStockCode()); 
				    	logRow=normalReport.getStockCode();
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
				    	normalReport.setYQR(time);
				    	
				    	normalReport.setCurrentAsset(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),2));
				    	normalReport.setCashAndCashEquivalents(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),2));
				    	normalReport.setShortTermInvestments(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),2));
				    	normalReport.setAccountsReceivableShortTerm(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),2));
				    	normalReport.setInventories(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),2));
				    	normalReport.setOtherCurrentAssets(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),2));
				    	normalReport.setLongTermAssets(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),2));
				    	normalReport.setAccountReceivableLongTerm(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),2));
				    	normalReport.setFixedAssets(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),2));
				    	normalReport.setTangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),2));
				    	normalReport.setFinanceTangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),2));
				    	normalReport.setIntangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),2));
				    	normalReport.setInvestmentProperty(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue(),2));
				    	normalReport.setContructionInProgress(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue(),2));
				    	normalReport.setLongTermInvestment(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue(),2));
				    	normalReport.setGoodWill(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue(),2));
				    	normalReport.setOtherLongTermAssets(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue(),2));
				    	normalReport.setTotalAssets(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue(),2));
				    	normalReport.setCurrentLiabilities(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue(),2));
				    	normalReport.setAccountPayableToSuppliers(NumberFormatUtil.formatDouble(row.getCell(26).getNumericCellValue(),2));
				    	normalReport.setShortTermAdvancesFromCustomers(NumberFormatUtil.formatDouble(row.getCell(27).getNumericCellValue(),2));
				    	normalReport.setShortTermUnearnedRevenue(NumberFormatUtil.formatDouble(row.getCell(28).getNumericCellValue(),2));
				    	normalReport.setShortTermBorrowingsAndLiabilities(NumberFormatUtil.formatDouble(row.getCell(29).getNumericCellValue(),2));
				    	normalReport.setOtherShortTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(30).getNumericCellValue(),2));
				    	normalReport.setLongTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(31).getNumericCellValue(),2));
				    	normalReport.setLongTermAccountsPayable(NumberFormatUtil.formatDouble(row.getCell(32).getNumericCellValue(),2));
				    	normalReport.setLongTermadvancesFromCustomers(NumberFormatUtil.formatDouble(row.getCell(33).getNumericCellValue(),2));
				    	normalReport.setLongTermUnearnedRevenue(NumberFormatUtil.formatDouble(row.getCell(34).getNumericCellValue(),2));
				    	normalReport.setLongTermBorrowingsAndLiabilities(NumberFormatUtil.formatDouble(row.getCell(35).getNumericCellValue(),2));
				    	normalReport.setOtherLongTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(36).getNumericCellValue(),2));
				    	normalReport.setEquity(NumberFormatUtil.formatDouble(row.getCell(37).getNumericCellValue(),2));
				    	normalReport.setShareCapital(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue(),2));
				    	normalReport.setSharePremium(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue(),2));
				    	normalReport.setRetainedProfits(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue(),2));
				    	normalReport.setOtherCapitals(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue(),2));
				    	normalReport.setNonControllingInterest(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue(),2));
				    	normalReport.setTotalResources(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue(),2));
				    	normalReport.setNetRevenue(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue(),2));
				    	normalReport.setCostOfSales(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue(),2));
				    	normalReport.setGrossProfit(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue(),2));
				    	normalReport.setFinancialIncome(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue(),2));
				    	normalReport.setFinancialExpenses(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue(),2));
				    	normalReport.setInWhichInterestExpense(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue(),2));
				    	normalReport.setShareOfProfitInAssociates(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue(),2));
				    	normalReport.setSellingExpenses(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue(),2));
				    	normalReport.setGeneralAndAdministrationExpenses(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue(),2));
				    	normalReport.setNetOperatingProfit(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue(),2));
				    	normalReport.setOtherIncome(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue(),2));
				    	normalReport.setProfitBeforeTax(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue(),2));
				    	normalReport.setIncomeTaxExpense(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue(),2));
				    	normalReport.setNetProfitAfterTax(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue(),2));
				    	normalReport.setMinorityInterest(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue(),2));
				    	normalReport.setNetIncome(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue(),2));
				    	normalReport.setDepreciation(NumberFormatUtil.formatDouble(row.getCell(65).getNumericCellValue(),2));
				    	normalReport.setAllowancesAndProvisions(NumberFormatUtil.formatDouble(row.getCell(66).getNumericCellValue(),2));
				    	normalReport.setNetCashFlowsFromOperatingActivities(NumberFormatUtil.formatDouble(row.getCell(67).getNumericCellValue(),2));
				    	normalReport.setNetCashFlowsFromInvestingActivities(NumberFormatUtil.formatDouble(row.getCell(68).getNumericCellValue(),2));
				    	normalReport.setNetCashFlowsFromFinancingActivities(NumberFormatUtil.formatDouble(row.getCell(69).getNumericCellValue(),2));
				    	normalReport.setNetCashFlows(NumberFormatUtil.formatDouble(row.getCell(70).getNumericCellValue(),2));
				    	if(null!=row.getCell(71)) {
				    		normalReport.setPaymentsOfDividends(NumberFormatUtil.formatDouble(row.getCell(71).getNumericCellValue(),2));
				    	}


				    	normalReportRepository.save(normalReport);
				    }
				    
			    }
			    
			    workbook.close();
			    
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
			return logRow+"=====Loi khi import file:"+ex.getMessage();
		}
		return "import file thanh cong";
	}
	
	@Transactional
	public String importRecommandations(MultipartFile multipartFile) {
		String logRow="";
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
			    	if(null==row.getCell(0)||StringUtil.isNullOrEmpty(row.getCell(0).toString())) {
			    		break;
			    	}
			    	RecommendationsOfStock recommendationsOfStock = new RecommendationsOfStock();
			    	recommendationsOfStock.setStockCode(row.getCell(0).getStringCellValue());
			    	System.out.println("row:"+recommendationsOfStock.getStockCode()); 
			    	logRow=recommendationsOfStock.getStockCode();
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
			return logRow+"====Loi khi import file:"+ex.getMessage();
		}
		return "import file thanh cong";
	}
	
	@Transactional
	public String importPlanOfYear(MultipartFile multipartFile, String timeString) {
		String logRow="";
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
			    	if(null==row.getCell(0)||StringUtil.isNullOrEmpty(row.getCell(0).toString())) {
			    		break;
			    	}
			    	PlanOfYear planOfYear = new PlanOfYear();
			    	planOfYear.setStockCode(row.getCell(0).getStringCellValue());
			    	System.out.println("row:"+planOfYear.getStockCode()); 
			    	logRow=planOfYear.getStockCode();
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
			    		planOfYear.setRevenue(NumberFormatUtil.formatDouble(row.getCell(1).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(2)) {
			    		planOfYear.setRevenueNow(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),2));
			    	}
			    	if(null==planOfYear.getRevenue()||null==planOfYear.getRevenueNow()||planOfYear.getRevenue()==0||planOfYear.getRevenueNow()==0) {
			    		planOfYear.setRevenuePercent(0d);
			    	}else {
			    		planOfYear.setRevenuePercent(NumberFormatUtil.formatDouble(planOfYear.getRevenueNow()*100/planOfYear.getRevenue(),2));
			    	}
			    	
			    	if(null!=row.getCell(3)) {
			    		planOfYear.setPretaxProfit(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(4)) {
			    		planOfYear.setPretaxProfitNow(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),2));
			    	}
			    	if(null==planOfYear.getPretaxProfit()||null==planOfYear.getPretaxProfitNow()||planOfYear.getPretaxProfit()==0||planOfYear.getPretaxProfitNow()==0) {
			    		planOfYear.setPretaxProfitPercent(0d);
			    	}else {
			    		planOfYear.setPretaxProfitPercent(NumberFormatUtil.formatDouble(planOfYear.getPretaxProfitNow()*100/planOfYear.getPretaxProfit(),2));
			    	}
			    	
			    	if(null!=row.getCell(5)) {
			    		planOfYear.setNetProfit(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(6)) {
			    		planOfYear.setNetProfitNow(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),2));
			    	}
			    	if(null==planOfYear.getNetProfit()||null==planOfYear.getNetProfitNow()||planOfYear.getNetProfit()==0||planOfYear.getNetProfitNow()==0) {
			    		planOfYear.setNetProfitPercent(0d);
			    	}else {
			    		planOfYear.setNetProfitPercent(NumberFormatUtil.formatDouble(planOfYear.getNetProfitNow()*100/planOfYear.getNetProfit(),2));
			    	}
			    	
			    	
			    	if(null!=row.getCell(7)) {
			    		planOfYear.setResult(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue()*100,2));
			    	}
			    	if(null!=row.getCell(8)) {
			    		planOfYear.setYoy(NumberFormatUtil.formatDouble(row.getCell(8).getNumericCellValue()*100,2));
			    	}
			    	if(null!=row.getCell(9)) {
			    		planOfYear.setPaymentsOfDividends(NumberFormatUtil.formatDouble(row.getCell(9).getNumericCellValue()*100,2));
			    	}
			    	if(null!=row.getCell(10)) {
			    		planOfYear.setdBTTM(NumberFormatUtil.formatDouble(row.getCell(10).getNumericCellValue(),2));
			    	}
			    	
			    	planOfYearRepository.save(planOfYear);
			    }
			    workbook.close();
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
			return logRow+"====Loi khi import file:"+ex.getMessage();
		}
		return "import file thanh cong";
	}
	
	@Transactional
	public String importBreakOut(MultipartFile multipartFile) {
		String logRow="";
		try {
	    	if(multipartFile!=null) {
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
			    	if(null==row.getCell(0)||StringUtil.isNullOrEmpty(row.getCell(0).toString())) {
			    		break;
			    	}
			    	BreakOut breakOut = new BreakOut();
			    	breakOut.setStockCode(row.getCell(0).getStringCellValue());
			    	if(null!=row.getCell(1)) {
			    		breakOut.setBreakTime(row.getCell(1).getDateCellValue());
			    	}			    	
			    	breakOut.setType(1);
			    	breakOut.setStatus(0);
			    	breakOut.setCreatedTime(new Date());
			    	
			    	if(null!=row.getCell(2)) {
			    		breakOut.setClosePrice(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(3)) {
			    		breakOut.setResistance(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(4)) {
			    		breakOut.setSupport(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(5)) {
			    		breakOut.setAdx14(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(6)) {
			    		breakOut.setRsi14(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(7)) {
			    		breakOut.setMacd(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),2));
			    	}
			    	if(null!=row.getCell(8)) {
			    		breakOut.setVolume(NumberFormatUtil.formatDouble(row.getCell(8).getNumericCellValue(),2));
			    	}
			    	breakOut.setScreen(row.getCell(9).getStringCellValue());
			    	
			    	breakOutRepository.save(breakOut);
			    }
			    workbook.close();
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
			return logRow+"====Loi khi import file:"+ex.getMessage();
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
					String h=object.toString();
					String[] arayTmp=h.split("Q");
					if(arayTmp.length==2) {
						h="Q"+arayTmp[1]+"/"+arayTmp[0];
					}
					header.add(h);
				}
			}
			header.add(ConstantManager.endYear);
			Collections.reverse(header);
			out.setHeaders(header);
			
			List<Object> result =normalReportRepository.searchReportData(inputSearchStockDTO.getCode().toUpperCase(),time);
			Collections.reverse(result);
			List<List<Object>> data = new ArrayList<>();
	    	if(!result.isEmpty()) {
	    		for(int i=0; i<ConstantManager.detailStockOverViewUnit.length;i++) {
		    		List<Object> ls= new ArrayList<>();
		    		ls.add(ConstantManager.vDetailStockOverView[i]);
		    		if(!"".equals(ConstantManager.detailStockOverViewUnit[i])) {
		    			ls.add(ConstantManager.detailStockOverViewUnit[i]);
		    		}
		    		data.add(ls);
	    		}
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
	    		ls1.add("Doanh thu");
	    		ls1.add(arrayObject[2]);
	    		ls1.add(arrayObject[3]);
	    		plans.add(ls1);
	    		List<Object> ls2= new ArrayList<>();
	    		ls2.add("LNTT");
	    		ls2.add(arrayObject[4]);
	    		ls2.add(arrayObject[5]);
	    		plans.add(ls2);
	    		List<Object> ls3= new ArrayList<>();
	    		ls3.add("LNST");
	    		ls3.add(arrayObject[6]);
	    		ls3.add(arrayObject[7]);
	    		plans.add(ls3);
	    		List<Object> ls4= new ArrayList<>();
	    		ls4.add("Cổ tức");
	    		ls4.add(arrayObject[8]);
	    		plans.add(ls4);
	    		out.setPlans(plans);
	    		
	    		List<List<Object>> valuation = new ArrayList<>();
	    		List<Object> ls5= new ArrayList<>();
	    		ls5.add("P/E");
	    		ls5.add("x");
	    		ls5.add(arrayObject[9]);
	    		valuation.add(ls5);
	    		List<Object> ls6= new ArrayList<>();
	    		ls6.add("Peg");
	    		ls6.add("x");
	    		ls6.add(arrayObject[10]);
	    		valuation.add(ls6);
	    		List<Object> ls7= new ArrayList<>();
	    		ls7.add("PB");
	    		ls7.add("x");
	    		ls7.add(arrayObject[11]);
	    		valuation.add(ls7);
	    		List<Object> ls8= new ArrayList<>();
	    		ls8.add("P/S");
	    		ls8.add("x");
	    		ls8.add(arrayObject[12]);
	    		valuation.add(ls8);
	    		List<Object> ls9= new ArrayList<>();
//	    		ls9.add("Div Yield");
	    		ls9.add("Tỷ suất cổ tức");
	    		ls9.add("(%)");
	    		ls9.add(arrayObject[13]);
	    		valuation.add(ls9);
	    		List<Object> ls10= new ArrayList<>();
	    		ls10.add("EV/EBITDA");
	    		ls10.add("x");
	    		ls10.add(arrayObject[14]);
	    		valuation.add(ls10);
	    		out.setValuation(valuation);
	    		
	    		List<List<Object>> score = new ArrayList<>();
	    		List<Object> ls11= new ArrayList<>();
//	    		ls11.add("F-Score");
	    		ls11.add("F-Score (Chất lượng BCTC)");
	    		ls11.add("p");
	    		ls11.add(arrayObject[15]);
	    		score.add(ls11);
	    		List<Object> ls12= new ArrayList<>();
//	    		ls12.add("C-score");
	    		ls12.add("C-score (sửa đổi BCTC)");
	    		ls12.add("p");
	    		ls12.add(arrayObject[16]);
	    		score.add(ls12);
	    		List<Object> ls13= new ArrayList<>();
//	    		ls13.add("M-score");
	    		ls13.add("M-score (Làm giả lợi nhuận)");
	    		ls13.add("p");
	    		ls13.add(arrayObject[17]);
	    		score.add(ls13);
	    		List<Object> ls14= new ArrayList<>();
//	    		ls14.add("Z-score");
	    		ls14.add("Z-score (khả năng phá sản)");
	    		ls14.add("p");
	    		ls14.add(arrayObject[18]);
	    		score.add(ls14);
	    		out.setScore(score);
	    		
	    		List<List<Object>> liquidityRatio = new ArrayList<>();
	    		List<Object> ls15= new ArrayList<>();
//	    		ls15.add("Current ratio");
	    		ls15.add("Hệ số thanh toán hiện hành");
	    		ls15.add("x");
	    		ls15.add(arrayObject[19]);
	    		liquidityRatio.add(ls15);
	    		List<Object> ls16= new ArrayList<>();
//	    		ls16.add("Quick ratio");
	    		ls16.add("Hệ số thanh toán nhanh");
	    		ls16.add("x");
	    		ls16.add(arrayObject[20]);
	    		liquidityRatio.add(ls16);
	    		List<Object> ls17= new ArrayList<>();
//	    		ls17.add("Cash ratio");
	    		ls17.add("Hệ số thanh toán tiền mặt");
	    		ls17.add("x");
	    		ls17.add(arrayObject[21]);
	    		liquidityRatio.add(ls17);
	    		out.setLiquidityRatio(liquidityRatio);
	    		
	    		List<List<Object>> activityTurnover = new ArrayList<>();
	    		List<Object> ls18= new ArrayList<>();
//	    		ls18.add("Receivable turnover");
	    		ls18.add("Vòng quay phải thu");
	    		ls18.add("x");
	    		ls18.add(arrayObject[22]);
	    		activityTurnover.add(ls18);
	    		List<Object> ls19= new ArrayList<>();
//	    		ls19.add("Payable turnover");
	    		ls19.add("Vòng quay phải trả");
	    		ls19.add("x");
	    		ls19.add(arrayObject[23]);
	    		activityTurnover.add(ls19);
	    		List<Object> ls20= new ArrayList<>();
//	    		ls20.add("inventory turnover");
	    		ls20.add("Vòng quay tồn kho");
	    		ls20.add("x");
	    		ls20.add(arrayObject[24]);
	    		activityTurnover.add(ls20);
	    		out.setActivityTurnover(activityTurnover);
	    		
	    		List<List<Object>> interpretationOfSolvencyRatios = new ArrayList<>();
	    		List<Object> ls21= new ArrayList<>();
//	    		ls21.add("Debt to Assets ");
	    		ls21.add("Nợ/Tổng tài sản");
	    		ls21.add("x");
	    		ls21.add(arrayObject[25]);
	    		interpretationOfSolvencyRatios.add(ls21);
	    		List<Object> ls22= new ArrayList<>();
//	    		ls22.add("Debt to Equity ");
	    		ls22.add("Nợ/Vốn chủ sở hữu");
	    		ls22.add("x");
	    		ls22.add(arrayObject[26]);
	    		interpretationOfSolvencyRatios.add(ls22);
	    		List<Object> ls23= new ArrayList<>();
//	    		ls23.add("LT debt/Capitalazion");
	    		ls23.add("Nợ dài hạn/Vốn hóa");
	    		ls23.add("x");
	    		ls23.add(arrayObject[27]);
	    		interpretationOfSolvencyRatios.add(ls23);
	    		List<Object> ls24= new ArrayList<>();
//	    		ls24.add("Interest coverage");
	    		ls24.add("Khả năng trả lãi vay");
	    		ls24.add("x");
	    		ls24.add(arrayObject[28]);
	    		interpretationOfSolvencyRatios.add(ls24);
	    		out.setInterpretationOfSolvencyRatios(interpretationOfSolvencyRatios);
	    		
	    		
	    		List<List<Object>> riskRatio = new ArrayList<>();
	    		List<Object> ls25= new ArrayList<>();
//	    		ls25.add("AR/Rev");
	    		ls25.add("Phải thu/ Doanh thu");
	    		ls25.add("x");
	    		ls25.add(arrayObject[29]);
	    		riskRatio.add(ls25);
	    		List<Object> ls26= new ArrayList<>();
//	    		ls26.add("AR/Income");
	    		ls26.add("Phải thu/ LNST");
	    		ls26.add("x");
	    		ls26.add(arrayObject[30]);
	    		riskRatio.add(ls26);
	    		List<Object> ls27= new ArrayList<>();
//	    		ls27.add("A&P/Income");
	    		ls27.add("Dự phòng/ LNST");
	    		ls27.add("x");
	    		ls27.add(arrayObject[31]);
	    		riskRatio.add(ls27);
	    		out.setRiskRatio(riskRatio);
	    		if(null!=arrayObject[32])
	    			out.getHeaders().set(0, ConstantManager.endYear+" - "+arrayObject[32]);
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
	    		out.getHeaders().add("TTM");
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
    	String yearOfPlan=systemConfigRepository.getSystemConfigByCode("YEAROFPLAN");
    	if(!StringUtil.isNullOrEmpty(yearOfPlan)) {
    		out.setYearOfPlan(yearOfPlan);
    	}
        return out;
		
	}
	
	public ObjectOutPutDetailStockMoreDTO detailStockMore(InputSearchStockDTO inputSearchStockDTO) {
		ObjectOutPutDetailStockMoreDTO out = new ObjectOutPutDetailStockMoreDTO();
		List<String> headerNormal= new ArrayList<>();
		List<String> headerFinance= new ArrayList<>();
		String time;
		if(!StringUtil.isNullOrEmpty(inputSearchStockDTO.getTime()) && "quarter".toUpperCase().equals(inputSearchStockDTO.getTime().toUpperCase())) {
			time="Q";
		}else {
			time="Y";
		}
		List<Object> resultHeaderNormal =normalReportRepository.searchHeaderReportData(inputSearchStockDTO.getCode().toUpperCase(),time);		
		
		if(!resultHeaderNormal.isEmpty()) {
			for (Object object : resultHeaderNormal) {
				String h=object.toString();
				String[] arayTmp=h.split("Q");
				if(arayTmp.length==2) {
					h="Q"+arayTmp[1]+"/"+arayTmp[0];
				}
				headerNormal.add(h);
			}
		}
		headerNormal.add(ConstantManager.unit);
		headerNormal.add(ConstantManager.report);
		Collections.reverse(headerNormal);
		out.setHeaders(headerNormal);
		
		List<Object> resultNormal =normalReportRepository.searchReportDataDetail(inputSearchStockDTO.getCode().toUpperCase(),time);
		Collections.reverse(resultNormal);
		List<List<Object>> dataNormal = new ArrayList<>();
		
    	if(!resultNormal.isEmpty()) {
    		for(int i=0; i<ConstantManager.detailStockNomalReportLevel.length;i++) {
    			List<Object> ls= new ArrayList<>();
        		ls.add(ConstantManager.detailStockNomalReportLevel[i]);
        		ls.add(ConstantManager.vDetailStockNomalReport[i]);
        		if(!"".equals(ConstantManager.detailStockNomalReportUnit[i])) {
        			ls.add(ConstantManager.detailStockNomalReportUnit[i]);
	    		}
        		
        		dataNormal.add(ls);
    		}
	    	for (Object object : resultNormal) {
	    		Object[] arrayObject = (Object[]) object;
	    		for(int i=0; i<arrayObject.length;i++) {
	    			if(!"null".equals(arrayObject[i])) {
	    				dataNormal.get(i).add(arrayObject[i]);
	    			}
	    		}
			}
	    	
    	}
    	out.setRows(dataNormal);

        return out;
	}
	
	public ObjectOutPutDetailStockMoreDTO detailStockFinanceRatio(InputSearchStockDTO inputSearchStockDTO) {
		ObjectOutPutDetailStockMoreDTO out = new ObjectOutPutDetailStockMoreDTO();
		List<String> header= new ArrayList<>();
		String time;
		if(!StringUtil.isNullOrEmpty(inputSearchStockDTO.getTime()) && "quarter".toUpperCase().equals(inputSearchStockDTO.getTime().toUpperCase())) {
			time="Q";
		}else {
			time="Y";
		}
		List<Object> resultHeaderFinance =financeRatioRepository.searchHeaderReportData(inputSearchStockDTO.getCode().toUpperCase(),time);
		
		
		if(!resultHeaderFinance.isEmpty()) {
			for (Object object : resultHeaderFinance) {
				String h=object.toString();
				String[] arayTmp=h.split("Q");
				if(arayTmp.length==2) {
					h="Q"+arayTmp[1]+"/"+arayTmp[0];
				}
				header.add(h);
			}
		}
		header.add(ConstantManager.unit);
		header.add(ConstantManager.reportFinanceRatio);
		Collections.reverse(header);
		out.setHeaders(header);
		
		List<Object> resultFinance =financeRatioRepository.searchReportDataDetail(inputSearchStockDTO.getCode().toUpperCase(),time);
		Collections.reverse(resultFinance);
		List<List<Object>> dataFinance = new ArrayList<>();
    	
    	if(!resultFinance.isEmpty()) {
    		for(int i=0; i<ConstantManager.detailStockFinanceRatioLevel.length;i++) {
    			List<Object> ls= new ArrayList<>();
        		ls.add(ConstantManager.detailStockFinanceRatioLevel[i]);
        		ls.add(ConstantManager.vDetailStockFinanceRatio[i]);
        		if(!"".equals(ConstantManager.detailStockFinanceRatioUnit[i])) {
        			ls.add(ConstantManager.detailStockFinanceRatioUnit[i]);
        		}
        		dataFinance.add(ls);
    		}
    		for (Object object : resultFinance) {
	    		Object[] arrayObject = (Object[]) object;
	    		for(int i=0; i<arrayObject.length;i++) {
	    			
	    			if(!"null".equals(arrayObject[i])) {
	    				dataFinance.get(i).add(arrayObject[i]);
	    			}
	    		}
			}
    		
    	}
    	out.setRows(dataFinance);
    	
        return out;
		
	}
	
	public ScreenPageDTO screenRevenue() {
		ScreenPageDTO page= new ScreenPageDTO();
		//left
		ScreenDTO left = new ScreenDTO();
		List<SearchItemDTO> data= new ArrayList<>();
		//Y
		List<Object> resultY =financeRatioRepository.highestRevenue("Y");
		if(!resultY.isEmpty()) {
			for (Object object : resultY) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataYear(data);
		}
		//Q
		List<Object> resultQ =financeRatioRepository.highestRevenue("Q");
		if(!resultQ.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQ) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataQuarter(data);
		}
		
		//R
		List<Object> resultR =financeRatioRepository.highestRevenue("R");
		if(!resultR.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultR) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataTTM(data);
		}
		
		
		page.setDataScreenLeft(left);
		
		//right
		ScreenDTO right = new ScreenDTO();
		data= new ArrayList<>();
		//Y
		List<Object> resultYr =financeRatioRepository.highestRevenueGrowth("Y");
		if(!resultYr.isEmpty()) {
			for (Object object : resultYr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataYear(data);
		}
		
		//Q
		List<Object> resultQr =financeRatioRepository.highestRevenueGrowth("Q");
		if(!resultQr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataQuarter(data);
		}
		
		//R
		List<Object> resultRr =financeRatioRepository.highestRevenueGrowth("R");
		if(!resultRr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultRr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataTTM(data);
		}
		
		
		page.setDataScreenRight(right);
		
		return page;
	}
	
	public ScreenPageDTO screenProfit() {
		ScreenPageDTO page= new ScreenPageDTO();
		//left
		ScreenDTO left = new ScreenDTO();
		List<SearchItemDTO> data= new ArrayList<>();
		//Y
		List<Object> resultY =financeRatioRepository.highestProfit("Y");
		if(!resultY.isEmpty()) {
			for (Object object : resultY) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataYear(data);
		}
		//Q
		List<Object> resultQ =financeRatioRepository.highestProfit("Q");
		if(!resultQ.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQ) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataQuarter(data);
		}
		
		//R
		List<Object> resultR =financeRatioRepository.highestProfit("R");
		if(!resultR.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultR) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataTTM(data);
		}
		
		
		page.setDataScreenLeft(left);
		
		//right
		ScreenDTO right = new ScreenDTO();
		data= new ArrayList<>();
		//Y
		List<Object> resultYr =financeRatioRepository.highestProfitGrowth("Y");
		if(!resultYr.isEmpty()) {
			for (Object object : resultYr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataYear(data);
		}
		
		//Q
		List<Object> resultQr =financeRatioRepository.highestProfitGrowth("Q");
		if(!resultQr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataQuarter(data);
		}
		
		//R
		List<Object> resultRr =financeRatioRepository.highestProfitGrowth("R");
		if(!resultRr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultRr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataTTM(data);
		}
		
		
		page.setDataScreenRight(right);
		
		return page;
	}
	
	public ScreenPageDTO screenEPS() {
		ScreenPageDTO page= new ScreenPageDTO();
		//left
		ScreenDTO left = new ScreenDTO();
		List<SearchItemDTO> data= new ArrayList<>();
		//Y
		List<Object> resultY =financeRatioRepository.highestEPS("Y");
		if(!resultY.isEmpty()) {
			for (Object object : resultY) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataYear(data);
		}
		//Q
		List<Object> resultQ =financeRatioRepository.highestEPS("Q");
		if(!resultQ.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQ) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataQuarter(data);
		}
		
		//R
		List<Object> resultR =financeRatioRepository.highestEPS("R");
		if(!resultR.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultR) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataTTM(data);
		}
		
		
		page.setDataScreenLeft(left);
		
		//right
		ScreenDTO right = new ScreenDTO();
		data= new ArrayList<>();
		//Y
		List<Object> resultYr =financeRatioRepository.highestEPSGrowth("Y");
		if(!resultYr.isEmpty()) {
			for (Object object : resultYr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataYear(data);
		}
		
		//Q
		List<Object> resultQr =financeRatioRepository.highestEPSGrowth("Q");
		if(!resultQr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataQuarter(data);
		}
		
		//R
		List<Object> resultRr =financeRatioRepository.highestEPSGrowth("R");
		if(!resultRr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultRr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataTTM(data);
		}
		
		
		page.setDataScreenRight(right);
		
		return page;
	}
	
	public ScreenPageDTO screenPE_PB() {
		ScreenPageDTO page= new ScreenPageDTO();
		//left
		ScreenDTO left = new ScreenDTO();
		List<SearchItemDTO> data= new ArrayList<>();
		//Y
		List<Object> resultY =financeRatioRepository.lowestPE("Y");
		if(!resultY.isEmpty()) {
			for (Object object : resultY) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataYear(data);
		}
		//Q
		List<Object> resultQ =financeRatioRepository.lowestPE("Q");
		if(!resultQ.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQ) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataQuarter(data);
		}
		
		//R
		List<Object> resultR =financeRatioRepository.lowestPE("R");
		if(!resultR.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultR) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataTTM(data);
		}
		
		
		page.setDataScreenLeft(left);
		
		//right
		ScreenDTO right = new ScreenDTO();
		data= new ArrayList<>();
		//Y
		List<Object> resultYr =financeRatioRepository.lowestPB("Y");
		if(!resultYr.isEmpty()) {
			for (Object object : resultYr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataYear(data);
		}
		
		//Q
		List<Object> resultQr =financeRatioRepository.lowestPB("Q");
		if(!resultQr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataQuarter(data);
		}
		
		//R
		List<Object> resultRr =financeRatioRepository.lowestPB("R");
		if(!resultRr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultRr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataTTM(data);
		}
		
		
		page.setDataScreenRight(right);
		
		return page;
	}
	
	public ScreenPageDTO screenMCNWC_EVEBITDA() {
		ScreenPageDTO page= new ScreenPageDTO();
		//left
		ScreenDTO left = new ScreenDTO();
		List<SearchItemDTO> data= new ArrayList<>();
		//Y
		List<Object> resultY =financeRatioRepository.lowestMCNWC("Y");
		if(!resultY.isEmpty()) {
			for (Object object : resultY) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataYear(data);
		}
		//Q
		List<Object> resultQ =financeRatioRepository.lowestMCNWC("Q");
		if(!resultQ.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQ) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataQuarter(data);
		}
		
		//R
		List<Object> resultR =financeRatioRepository.lowestMCNWC("R");
		if(!resultR.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultR) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			left.setDataTTM(data);
		}
		
		
		page.setDataScreenLeft(left);
		
		//right
		ScreenDTO right = new ScreenDTO();
		data= new ArrayList<>();
		//Y
		List<Object> resultYr =financeRatioRepository.lowestEVEBITDA("Y");
		if(!resultYr.isEmpty()) {
			for (Object object : resultYr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataYear(data);
		}
		
		//Q
		List<Object> resultQr =financeRatioRepository.lowestEVEBITDA("Q");
		if(!resultQr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultQr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataQuarter(data);
		}
		
		//R
		List<Object> resultRr =financeRatioRepository.lowestEVEBITDA("R");
		if(!resultRr.isEmpty()) {
			data= new ArrayList<>();
			for (Object object : resultRr) {
				SearchItemDTO tmp= new SearchItemDTO();
				Object[] arrayObject = (Object[]) object;
				tmp.setCode(arrayObject[0].toString());
				tmp.setValue((Double)arrayObject[1]);
				data.add(tmp);
			}
			right.setDataTTM(data);
		}
		
		
		page.setDataScreenRight(right);
		
		return page;
	}
	public List<OutPutScreenBenjamin> screenNetNet() {
		List<OutPutScreenBenjamin> out = new ArrayList<>();
		List<Object> result =financeRatioRepository.screnNetNet();
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenBenjamin tmp= new OutPutScreenBenjamin();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					tmp.setNwc((Double)arrayObject[4]);
					tmp.setMarketCap((Double)arrayObject[5]);
					tmp.setPe((Double)arrayObject[6]);
					tmp.setPb((Double)arrayObject[7]);
					tmp.setEps((Double)arrayObject[8]);
					tmp.setfScrore((Double)arrayObject[9]);
					tmp.setcScrore((Double)arrayObject[10]);
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenBenjamin> screnNCAV() {
		List<OutPutScreenBenjamin> out = new ArrayList<>();
		List<Object> result =financeRatioRepository.screnNCAV();
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenBenjamin tmp= new OutPutScreenBenjamin();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					tmp.setNwc((Double)arrayObject[4]);
					tmp.setMarketCap((Double)arrayObject[5]);
					tmp.setPe((Double)arrayObject[6]);
					tmp.setPb((Double)arrayObject[7]);
					tmp.setEps((Double)arrayObject[8]);
					tmp.setfScrore((Double)arrayObject[9]);
					tmp.setcScrore((Double)arrayObject[10]);
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenCANSLIM> screnCANSLIM() {
		List<OutPutScreenCANSLIM> out = new ArrayList<>();
		List<Object> result =financeRatioRepository.screnCANSLIM();
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenCANSLIM tmp= new OutPutScreenCANSLIM();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					tmp.setEpsYoy((Double)arrayObject[4]);
					tmp.setEps((Double)arrayObject[5]);
					tmp.setRoeYoy((Double)arrayObject[6]);
					tmp.setPe((Double)arrayObject[7]);
					tmp.setPb((Double)arrayObject[8]);
					tmp.setfScrore((Double)arrayObject[9]);
					tmp.setcScrore((Double)arrayObject[10]);
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenPhilipFisherGrowth> screnPhilipFisherGrowth() {
		List<OutPutScreenPhilipFisherGrowth> out = new ArrayList<>();
		List<Object> result =financeRatioRepository.screnPhilipFisherGrowth();
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenPhilipFisherGrowth tmp= new OutPutScreenPhilipFisherGrowth();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					tmp.setGrossProfitYoy((Double)arrayObject[4]);
					tmp.setPeg((Double)arrayObject[5]);
					tmp.setDebtToEquityRatio((Double)arrayObject[6]);
					tmp.setFcf((Double)arrayObject[7]);
					tmp.setPe((Double)arrayObject[8]);
					tmp.setfScrore((Double)arrayObject[9]);
					tmp.setcScrore((Double)arrayObject[10]);
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenJohnNeffValue> screnJohnNeffValue() {
		List<OutPutScreenJohnNeffValue> out = new ArrayList<>();
		List<Object> result =financeRatioRepository.screnJohnNeffValue();
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenJohnNeffValue tmp= new OutPutScreenJohnNeffValue();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					tmp.setNetRevenueYoy((Double)arrayObject[4]);
					tmp.setPeg((Double)arrayObject[5]);
					tmp.setEps((Double)arrayObject[6]);
					tmp.setFcf((Double)arrayObject[7]);
					tmp.setPe((Double)arrayObject[8]);
					tmp.setfScrore((Double)arrayObject[9]);
					tmp.setcScrore((Double)arrayObject[10]);
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenPeterLynchGrowth> screnPeterLynchGrowth() {
		List<OutPutScreenPeterLynchGrowth> out = new ArrayList<>();
		List<Object> result =financeRatioRepository.screnPeterLynchGrowth();
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenPeterLynchGrowth tmp= new OutPutScreenPeterLynchGrowth();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					tmp.setEpsYoy((Double)arrayObject[4]);
					tmp.setPeg((Double)arrayObject[5]);
					tmp.setDebtToAssetsRatio((Double)arrayObject[6]);
					tmp.setPe((Double)arrayObject[7]);
					tmp.setEps((Double)arrayObject[8]);
					tmp.setfScrore((Double)arrayObject[9]);
					tmp.setcScrore((Double)arrayObject[10]);
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenGrahamChecklist> screnGrahamChecklist() {
		List<OutPutScreenGrahamChecklist> out = new ArrayList<>();
		List<Object> result =financeRatioRepository.screnGrahamChecklist();
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenGrahamChecklist tmp= new OutPutScreenGrahamChecklist();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					tmp.setGrahamScore((Double)arrayObject[4]);
					tmp.setMarketCap((Double)arrayObject[5]);
					tmp.setPe((Double)arrayObject[6]);
					tmp.setPb((Double)arrayObject[7]);
					tmp.setEps((Double)arrayObject[8]);
					tmp.setfScrore((Double)arrayObject[9]);
					tmp.setcScrore((Double)arrayObject[10]);
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenBreakOut> screnBreakResistance() {
		List<OutPutScreenBreakOut> out = new ArrayList<>();
		List<Object> result =breakOutRepository.getBreakOutScreenByCode("BR");
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenBreakOut tmp= new OutPutScreenBreakOut();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					tmp.setBreakPrice((Double)arrayObject[4]);
					//5 khong lay
					tmp.setAdx14((Double)arrayObject[6]);
					tmp.setRsi14((Double)arrayObject[7]);
					tmp.setMacd((Double)arrayObject[8]);
					tmp.setVolume((Double)arrayObject[9]);
					tmp.setBreakTime(arrayObject[10].toString());
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenBreakOut> screnBreakSupport() {
		List<OutPutScreenBreakOut> out = new ArrayList<>();
		List<Object> result =breakOutRepository.getBreakOutScreenByCode("BS");
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenBreakOut tmp= new OutPutScreenBreakOut();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					//4 khong lay
					tmp.setBreakPrice((Double)arrayObject[5]);
					
					tmp.setAdx14((Double)arrayObject[6]);
					tmp.setRsi14((Double)arrayObject[7]);
					tmp.setMacd((Double)arrayObject[8]);
					tmp.setVolume((Double)arrayObject[9]);
					tmp.setBreakTime(arrayObject[10].toString());
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenBreakOut> screnTrendTrader() {
		List<OutPutScreenBreakOut> out = new ArrayList<>();
		List<Object> result =breakOutRepository.getBreakOutScreenByCode("TT");
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenBreakOut tmp= new OutPutScreenBreakOut();
				Object[] arrayObject = (Object[]) object;
				if(!lastCode.equals(arrayObject[0].toString())) {
					lastCode=arrayObject[0].toString();
					tmp.setCode(arrayObject[0].toString());
					tmp.setName(arrayObject[1].toString());
					tmp.setExchange(arrayObject[2].toString());
					tmp.setPrice((Double)arrayObject[3]);
					//4 khong lay
					tmp.setBreakPrice((Double)arrayObject[5]);
					
					tmp.setAdx14((Double)arrayObject[6]);
					tmp.setRsi14((Double)arrayObject[7]);
					tmp.setMacd((Double)arrayObject[8]);
					tmp.setVolume((Double)arrayObject[9]);
					tmp.setBreakTime(arrayObject[10].toString());
					out.add(tmp);
				}
			}
		}
		return out;
	}
	public List<OutPutScreenDBTTM> screnDBTTM() {
		List<OutPutScreenDBTTM> out = new ArrayList<>();
		List<Object> result =financeRatioRepository.screnDBTTM();
		if(!result.isEmpty()) {
			String lastCode="";
			for (Object object : result) {
				OutPutScreenDBTTM tmp= new OutPutScreenDBTTM();
				Object[] arrayObject = (Object[]) object;
				
				tmp.setCode(arrayObject[0].toString());
				tmp.setName(arrayObject[1].toString());
				tmp.setExchange(arrayObject[2].toString());
				tmp.setPrice((Double)arrayObject[3]);
				tmp.setResult((Double)arrayObject[4]);
				tmp.setYoy((Double)arrayObject[5]);
				tmp.setPe((Double)arrayObject[6]);
				tmp.setPb((Double)arrayObject[7]);
				tmp.setEps((Double)arrayObject[8]);
				tmp.setfScrore((Double)arrayObject[9]);
				tmp.setcScrore((Double)arrayObject[10]);
				out.add(tmp);
				
			}
		}
		return out;
	}
	public ListShortNewsDTO getAllNews(FilterNewsDTO inputData) {
		ListShortNewsDTO lout= new ListShortNewsDTO();
		List<ShortNewsDTO> out = new ArrayList<>();
		List<Object> result =newsRepository.getListNewsPaging(inputData);
		if(!result.isEmpty() && result.size()>1) {
			for (int i=0; i<result.size()-1;i++) {
				Object object=result.get(i);
				ShortNewsDTO dto= new ShortNewsDTO();
				Object[] arrayObject = (Object[]) object;
				dto.setId(Long.parseLong(arrayObject[0].toString()));
				if(null!=arrayObject[1]) {
					dto.setCategoryId((Integer)arrayObject[1]);
				}
				if(null!=arrayObject[2]) {
					dto.setCreatedTime(arrayObject[2].toString());
				}
				if(null!=arrayObject[3]) {
					dto.setThumbnailUrl(arrayObject[3].toString());
				}
				
				if(null!=arrayObject[4]) {
					dto.setTitle(arrayObject[4].toString());
				}
				
				out.add(dto);
			}
			lout.setListData(out);
			lout.setTotalRows(Integer.parseInt(result.get(result.size()-1).toString()));
		}else {
			lout.setListData(out);
			lout.setTotalRows(0);
		}
		return lout;
	}
	public News createNews(NewsDTO news) {
		News n;
		if(null!=news.getId() && news.getId()>0) {
			n=newsRepository.getOne(news.getId());
			n.setTitle(news.getTitle());
			n.setThumbnailUrl(news.getThumbnailUrl());
			if(null!=news.getListTag() && !news.getListTag().isEmpty()) {
				n.setTag("");
				for(String tag :news.getListTag()) {
					n.setTag(n.getTag()+"#"+tag);
				}
			}
			n.setContent(news.getContent());
			n.setCategoryId(news.getCategoryId());
			n.setUpdatedTime(new Date());
		}else {
			n = new News();
			n.setCreatedTime(new Date());
			n.setTitle(news.getTitle());
			n.setThumbnailUrl(news.getThumbnailUrl());
			if(null!=news.getListTag() && !news.getListTag().isEmpty()) {
				StringBuffer sb= new StringBuffer();
				for(String tag :news.getListTag()) {
					sb.append("#").append(tag);
				}
				n.setTag(sb.toString());
			}
			n.setContent(news.getContent());
			n.setCategoryId(news.getCategoryId());
			n.setStatus(1);
			n.setCreatedTime(new Date());
			n.setType(1);
		}
		
		return newsRepository.save(n);
	}
	public NewsDTO getNewsDetail(Long id) {
		List<Object> result =newsRepository.getNewsById(id);
		if(!result.isEmpty()) {
			Object[] arrayObject = (Object[]) result.get(0);
			NewsDTO dto= new NewsDTO();
			dto.setId(Long.parseLong(arrayObject[0].toString()));
			dto.setCategoryId((Integer)arrayObject[1]);
			dto.setContent(arrayObject[2].toString());
			dto.setCreatedTime(arrayObject[3].toString());
			if(null!=arrayObject[4]) {
				String tag= arrayObject[4].toString();
				String []arrayTag=tag.split("#");
				List<String> lst= new ArrayList<>();
				if(null!=arrayTag && arrayTag.length>0) {
					for (int i=1; i<arrayTag.length;i++) {
						lst.add(arrayTag[i]);
					}
				}
				dto.setListTag(lst);
			}
			
			dto.setThumbnailUrl(arrayObject[5].toString());
			dto.setTitle(arrayObject[6].toString());
			return dto;
		}
		return null;
	}
	public Boolean deleteNews(Long id) {
		newsRepository.deleteById(id);
		return true;
	}
	
	public List<ObjectOutPutDTO> getAllNewscategory() {
		List<ObjectOutPutDTO>lst= new ArrayList<>();
		ObjectOutPutDTO o= new ObjectOutPutDTO();
		o.setCode("PT");
		o.setId(1L);
		o.setName("Phân tích");
		lst.add(o);
		o= new ObjectOutPutDTO();
		o.setCode("KN");
		o.setId(2L);
		o.setName("Khuyến nghị");
		lst.add(o);
		o= new ObjectOutPutDTO();
		o.setCode("KS");
		o.setId(3L);
		o.setName("Case study");
		lst.add(o);
		o= new ObjectOutPutDTO();
		o.setCode("TT");
		o.setId(4L);
		o.setName("Tin tức");
		lst.add(o);
		return lst;
	}
	
	public ObjectOutPutRegisterDTO register(RegisterInputsDTO registerInput) throws NoSuchAlgorithmException {
		ObjectOutPutRegisterDTO out = new ObjectOutPutRegisterDTO();
		if(null==registerInput) {
			out.setCode("-1");
			out.setMessage("Khong nhan duoc thong tin khach hang");
			return out;
		}else if(StringUtil.isNullOrEmpty(registerInput.getEmail())) {
			out.setCode("-2");
			out.setMessage("Khong nhan duoc Email khach hang");
			return out;
		}else if(StringUtil.isNullOrEmpty(registerInput.getName())) {
			out.setCode("-3");
			out.setMessage("Khong nhan duoc Ten khach hang");
			return out;
		}else if(StringUtil.isNullOrEmpty(registerInput.getProvider())) {
			out.setCode("-4");
			out.setMessage("Khong nhan duoc Provider dang ky");
			return out;
		}else if("GOOGLE".equals(registerInput.getProvider()) && StringUtil.isNullOrEmpty(registerInput.getProvider())){
			out.setCode("-5");
			out.setMessage("Khong nhan duoc ID cua Provider Google");
			return out;
		}else if("INVALUE".equals(registerInput.getProvider()) && StringUtil.isNullOrEmpty(registerInput.getPassword())){
			out.setCode("-6");
			out.setMessage("Khong nhan duoc Password khach hang");
			return out;
		}else if(!validate(registerInput.getEmail())) {
			out.setCode("-8");
			out.setMessage("Email khong dung dinh dang");
			return out;
		}
		List<Object> result = urerRepository.getUserByEmail(registerInput.getEmail());
		if(!result.isEmpty()) {
			out.setCode("-7");
			out.setMessage("Email da dang ky roi");
			return out;
		}
		User u= new User();
		byte[] array = new byte[5]; // length is bounded by 7
	    new Random().nextBytes(array);
	    String salt = "123456";
		u.setEmail(registerInput.getEmail());
		u.setFullName(registerInput.getName());
		
		if("INVALUE".equals(registerInput.getProvider())) {
			u.setPassword(registerInput.getPassword());
			u.setSalt(salt);
		}else {
			u.setIdProvider(registerInput.getIdProvider());
		}
		u.setRoleId(1);
		u.setStatus(1);
		u.setToken("123456");
		u.setType(1);
		
		u.setCreatedTime(new Date());
		u = urerRepository.save(u);
		
		if(null!=u && u.getId()>0) {
			out.setCode("0");
			out.setMessage("Dang ky thanh cong");
			return out;
		}else {
			out.setCode("500");
			out.setMessage("Loi trong qua trinh xu ly");
			return out;
		}
    }
	
	public ObjectOutPutRegisterDTO login(RegisterInputsDTO registerInput) throws NoSuchAlgorithmException {
		ObjectOutPutRegisterDTO out = new ObjectOutPutRegisterDTO();
		if(null==registerInput) {
			out.setCode("-1");
			out.setMessage("Khong nhan duoc thong tin khach hang");
			return out;
		}else if(StringUtil.isNullOrEmpty(registerInput.getEmail())) {
			out.setCode("-2");
			out.setMessage("Khong nhan duoc Email khach hang");
			return out;
		}else if(StringUtil.isNullOrEmpty(registerInput.getProvider())) {
			out.setCode("-3");
			out.setMessage("Khong nhan duoc Provider dang nhap");
			return out;
		}else if("GOOGLE".equals(registerInput.getProvider()) && StringUtil.isNullOrEmpty(registerInput.getProvider())){
			out.setCode("-4");
			out.setMessage("Khong nhan duoc ID cua Provider Google");
			return out;
		}else if("INVALUE".equals(registerInput.getProvider()) && StringUtil.isNullOrEmpty(registerInput.getPassword())){
			out.setCode("-5");
			out.setMessage("Khong nhan duoc Password khach hang");
			return out;
		}
		List<Object> result = urerRepository.getUserByEmail(registerInput.getEmail());
		if(!result.isEmpty()) {
			Object[] arrayObject = (Object[]) result.get(0);
			LoginOutputsDTO u= new LoginOutputsDTO();
			u.setId(Long.parseLong(arrayObject[0].toString()));
			u.setEmail(arrayObject[2].toString());
			if(null!=arrayObject[3])
				u.setFullName(arrayObject[3].toString());
			if(null!=arrayObject[4])	
				u.setPhone(arrayObject[4].toString());
			u.setRoleId((Integer)arrayObject[5]);
			//6
			u.setType((Integer)arrayObject[7]);
			//7
			String uPass="";
			String uSalt="";
			String idProvider="";
			String provider="";
			if(null!=arrayObject[8])
				uPass=arrayObject[8].toString();
			if(null!=arrayObject[9])
				uSalt=arrayObject[9].toString();
			if(null!=arrayObject[10])
				u.setToken(arrayObject[10].toString());
			if(null!=arrayObject[11])
				idProvider = arrayObject[11].toString();
			if(null!=arrayObject[12])
				provider = arrayObject[12].toString();
			u.setNumFilter(5);
			u.setNumPTKT(5);
			if(!"INVALUE".equals(registerInput.getProvider()) && !"GOOGLE".equals(registerInput.getProvider())) {
				out.setCode("-4");
				out.setMessage("Tai khoan hoac mat khau khong hop le");
				return out;
			}
			if("INVALUE".equals(registerInput.getProvider())) {
				if(!registerInput.getPassword().equals(uPass)) {
					out.setCode("-4");
					out.setMessage("Tai khoan hoac mat khau khong hop le");
					return out;
				}
				else {
					out.setCode("0");
					out.setMessage("Dang nhap thanh cong");
					out.setValue(u);
					return out;
				}
			}else if("GOOGLE".equals(registerInput.getProvider())) {
				if(!registerInput.getIdProvider().equals(idProvider)) {
					out.setCode("-4");
					out.setMessage("Tai khoan hoac mat khau khong hop le");
					return out;
				}
				else {
					out.setCode("0");
					out.setMessage("Dang nhap thanh cong");
					out.setValue(u);
					return out;
				}
			}else {
				out.setCode("-4");
				out.setMessage("Tai khoan hoac mat khau khong hop le");
				return out;
			}
			
		}else {
			out.setCode("-4");
			out.setMessage("Tai khoan hoac mat khau khong hop le");
			return out;
		}
		
	}
	public final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public boolean validate(String emailStr) {
	        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	        return matcher.find();
	}
	public static String generateString() {
        String uuid = UUID.randomUUID().toString();
        return "uuid = " + uuid;
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
