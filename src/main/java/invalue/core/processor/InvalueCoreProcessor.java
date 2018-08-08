package invalue.core.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
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
import invalue.core.dto.ObjectOutPutDTO;
import invalue.core.dto.ObjectOutPutDetailStockDTO;
import invalue.core.dto.SearchItemDTO;
import invalue.core.entity.FinanceRatioQ;
import invalue.core.entity.FinanceRatioY;
import invalue.core.entity.NormalReportY;
import invalue.core.entity.Stock;
import invalue.core.repository.FinanceRatioQRepository;
import invalue.core.repository.FinanceRatioYRepository;
import invalue.core.repository.NormalReportYRepository;
import invalue.core.repository.StockRepository;
import invalue.core.util.NumberFormatUtil;
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
    
	
    public Collection<BasicFilterDTO> getFiltered(InputBasicFilterDTO inputBasicFilterDTO) {
    	List<Object> result;
    	if("quarter".toUpperCase().equals(inputBasicFilterDTO.getTime().toUpperCase())) {
    		result = financeRatioQRepository.getFinanceRatioFillter(inputBasicFilterDTO);
    	}else {
    		result = financeRatioYRepository.getFinanceRatioFillter(inputBasicFilterDTO);
    	}
    	
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
    	if("quarter".toUpperCase().equals(inputCompareFilterDTO.getTime().toUpperCase())) {
    		result = financeRatioQRepository.getCompareFillter(inputCompareFilterDTO);
    	}
    	else {
    		result = financeRatioYRepository.getCompareFillter(inputCompareFilterDTO);
    	}
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
	public String importFinanceratio(MultipartFile multipartFile) {
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
			    String timeString=file.getName();
			    timeString=timeString.split("\\.")[0];
			    if(timeString.contains("Q")) {
			    	for (Row row : sheet) {
				    	rowIndex++;
				    	if(rowIndex<3) {
				    		continue;
				    	}
				    	if(null==row.getCell(0)) {
				    		break;
				    	}
				    	FinanceRatioQ financeRatioQ = new FinanceRatioQ();
				    	financeRatioQ.setStockCode(row.getCell(0).getStringCellValue());
				    	System.out.println("row:"+financeRatioQ.getStockCode()); 
				    	financeRatioQ.setCode(financeRatioQ.getStockCode()+timeString);
				    	financeRatioQ.setType(1);
				    	
				    	if(null != row.getCell(1) && "NEW".equals(row.getCell(1).getStringCellValue())) {
				    		listUpdateOld.add(financeRatioQ.getStockCode());
				    		financeRatioQ.setStatus(0);
				    	}else {
				    		financeRatioQ.setStatus(1);
				    	}
				    	financeRatioQ.setCreatedTime(new Date());
				    	financeRatioQ.setTimeString(timeString);
				    	financeRatioQ.setStockId(1);
				    	financeRatioQ.setNetRevenue(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),1));
				    	financeRatioQ.setGrossProfit(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),1));
				    	financeRatioQ.setNetIncome(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),1));
				    	financeRatioQ.setShareSOustanding(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),1));
				    	financeRatioQ.setEps(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),2));
				    	financeRatioQ.setBookValue(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),2));
				    	financeRatioQ.setMarketPrice(NumberFormatUtil.formatDouble(row.getCell(8).getNumericCellValue(),2));
		//		    	financeRatioQ.setDayys(row.getCell(9).getNumericCellValue());
				    	financeRatioQ.setCapex(row.getCell(10).getNumericCellValue());//N?A
				    	financeRatioQ.setFcf(NumberFormatUtil.formatDouble(row.getCell(11).getNumericCellValue(),1));
				    	financeRatioQ.setEbit(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),1));
				    	financeRatioQ.setEbitda(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),1));
				    	financeRatioQ.setNnwc(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),1));
				    	financeRatioQ.setNetWorkingCapital(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),1));
				    	financeRatioQ.setEv(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),1));
				    	financeRatioQ.setMarketCapital(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),1));
				    	financeRatioQ.setNetRevenueYoy(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue()*100,1));
				    	financeRatioQ.setGrossProfitYoy(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue()*100,1));
				    	financeRatioQ.setEpsYoy(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue()*100,1));
				    	financeRatioQ.setEbitdaYoy(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue()*100,1));
				    	financeRatioQ.setDebtYoy(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue()*100,1));
				    	financeRatioQ.setEquityYoy(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue()*100,1));
				    	financeRatioQ.setMarketCapitalYoy(NumberFormatUtil.formatDouble(row.getCell(24).getNumericCellValue()*100,1));
				    	financeRatioQ.setTotalAssetsYoy(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue()*100,1));
				    	financeRatioQ.setPE(NumberFormatUtil.formatDouble(row.getCell(26).getNumericCellValue(),1));
				    	financeRatioQ.setPeg(NumberFormatUtil.formatDouble(row.getCell(27).getNumericCellValue(),1));
				    	financeRatioQ.setPB(NumberFormatUtil.formatDouble(row.getCell(28).getNumericCellValue(),1));
				    	financeRatioQ.setPS(NumberFormatUtil.formatDouble(row.getCell(29).getNumericCellValue(),1));
				    	financeRatioQ.setEvEbitda(NumberFormatUtil.formatDouble(row.getCell(30).getNumericCellValue(),1));
				    	financeRatioQ.setEvEbit(NumberFormatUtil.formatDouble(row.getCell(31).getNumericCellValue(),1));
				    	financeRatioQ.setEvFcf(NumberFormatUtil.formatDouble(row.getCell(32).getNumericCellValue(),1));
				    	financeRatioQ.setRevFcf(NumberFormatUtil.formatDouble(row.getCell(33).getNumericCellValue(),1));
				    	financeRatioQ.setMcCfo(NumberFormatUtil.formatDouble(row.getCell(34).getNumericCellValue(),1));
				    	financeRatioQ.setMcNwc(NumberFormatUtil.formatDouble(row.getCell(35).getNumericCellValue(),1));
				    	financeRatioQ.setFcff(NumberFormatUtil.formatDouble(row.getCell(36).getNumericCellValue(),1));
				    	financeRatioQ.setFcfe(NumberFormatUtil.formatDouble(row.getCell(37).getNumericCellValue(),1));
				    	financeRatioQ.setCapexRev(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue()*100,1));
				    	financeRatioQ.setRoic(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue()*100,1));
				    	financeRatioQ.setRoce(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue()*100,1));
				    	financeRatioQ.setRoe(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue()*100,1));
				    	financeRatioQ.setRoa(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue()*100,1));
				    	financeRatioQ.setGrossProfitMargin(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue()*100,1));
				    	financeRatioQ.setOperatingProfitMargin(NumberFormatUtil.formatDouble(row.getCell(44).getNumericCellValue()*100,1));
				    	financeRatioQ.setPretaxProfitMargin(NumberFormatUtil.formatDouble(row.getCell(45).getNumericCellValue()*100,1));
				    	financeRatioQ.setNetProfitMargin(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue()*100,1));
				    	financeRatioQ.setDivYield(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue()*100,1));
				    	financeRatioQ.setEbitRev(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue()*100,1));
				    	financeRatioQ.setEbitdaRev(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue()*100,1));
				    	financeRatioQ.setSalesToTotalAssets(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue()*100,1));
				    	financeRatioQ.setReceivableTurnover(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue()*100,1));
				    	financeRatioQ.setPayableTurnover(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue()*100,1));
				    	financeRatioQ.setInventoryTurnover(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue()*100,1));
				    	financeRatioQ.setDebtToAssetsRatio(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue()*100,1));
				    	financeRatioQ.setDebtToEquityRatio(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue()*100,1));
				    	financeRatioQ.setLongTimeDebtTotalCapitalazion(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue()*100,1));
				    	financeRatioQ.setInterestCoverage(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue()*100,1));
				    	financeRatioQ.setCurrentRatio(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue()*100,2));
				    	financeRatioQ.setQuickRatio(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue()*100,2));
				    	financeRatioQ.setCashRatio(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue()*100,2));
				    	financeRatioQ.setAccountReceivableToRevenue(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue()*100,2));
				    	financeRatioQ.setAccountReceivableToNetIncome(NumberFormatUtil.formatDouble(row.getCell(62).getNumericCellValue()*100,2));
				    	financeRatioQ.setAllowancesAndProvisionsToNetIncome(NumberFormatUtil.formatDouble(row.getCell(63).getNumericCellValue()*100,2));
				    	financeRatioQ.setFScore(NumberFormatUtil.formatDouble(row.getCell(64).getNumericCellValue(),0));
				    	financeRatioQ.setCScore(NumberFormatUtil.formatDouble(row.getCell(65).getNumericCellValue(),0));
				    	financeRatioQ.setMScore(NumberFormatUtil.formatDouble(row.getCell(66).getNumericCellValue(),2));
				    	financeRatioQ.setZScore(NumberFormatUtil.formatDouble(row.getCell(67).getNumericCellValue(),2));
//				    	financeRatioQ.setId(1L);
				    	financeRatioQRepository.save(financeRatioQ);
				    }
				    if(!listUpdateOld.isEmpty()) {
				    	financeRatioQRepository.updateOldFinanceRatioFillter(listUpdateOld, timeString);
				    }
			    }else if(timeString.contains("Y")) {
			    	
			    	for (Row row : sheet) {
				    	rowIndex++;
				    	if(rowIndex<3) {
				    		continue;
				    	}
				    	if(null==row.getCell(0)) {
				    		break;
				    	}
				    	FinanceRatioY financeRatioY = new FinanceRatioY();
				    	financeRatioY.setStockCode(row.getCell(0).getStringCellValue());
				    	System.out.println("row:"+financeRatioY.getStockCode()); 
				    	financeRatioY.setCode(financeRatioY.getStockCode()+timeString);
				    	financeRatioY.setType(1);
				    	
				    	if(null != row.getCell(1) && "NEW".equals(row.getCell(1).getStringCellValue())) {
				    		listUpdateOld.add(financeRatioY.getStockCode());
				    		financeRatioY.setStatus(0);
				    	}else {
				    		financeRatioY.setStatus(1);
				    	}
				    	financeRatioY.setCreatedTime(new Date());
				    	financeRatioY.setTimeString(timeString);
				    	financeRatioY.setStockId(1);
				    	financeRatioY.setNetRevenue(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),1));
				    	financeRatioY.setGrossProfit(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),1));
				    	financeRatioY.setNetIncome(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),1));
				    	financeRatioY.setShareSOustanding(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),1));
				    	financeRatioY.setEps(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),2));
				    	financeRatioY.setBookValue(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),2));
				    	financeRatioY.setMarketPrice(NumberFormatUtil.formatDouble(row.getCell(8).getNumericCellValue(),2));
		//		    	financeRatioY.setDayys(row.getCell(9).getNumericCellValue());
				    	financeRatioY.setCapex(row.getCell(10).getNumericCellValue());//N?A
				    	financeRatioY.setFcf(NumberFormatUtil.formatDouble(row.getCell(11).getNumericCellValue(),1));
				    	financeRatioY.setEbit(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),1));
				    	financeRatioY.setEbitda(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),1));
				    	financeRatioY.setNnwc(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),1));
				    	financeRatioY.setNetWorkingCapital(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),1));
				    	financeRatioY.setEv(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),1));
				    	financeRatioY.setMarketCapital(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),1));
				    	financeRatioY.setNetRevenueYoy(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue()*100,1));
				    	financeRatioY.setGrossProfitYoy(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue()*100,1));
				    	financeRatioY.setEpsYoy(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue()*100,1));
				    	financeRatioY.setEbitdaYoy(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue()*100,1));
				    	financeRatioY.setDebtYoy(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue()*100,1));
				    	financeRatioY.setEquityYoy(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue()*100,1));
				    	financeRatioY.setMarketCapitalYoy(NumberFormatUtil.formatDouble(row.getCell(24).getNumericCellValue()*100,1));
				    	financeRatioY.setTotalAssetsYoy(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue()*100,1));
				    	financeRatioY.setPE(NumberFormatUtil.formatDouble(row.getCell(26).getNumericCellValue(),1));
				    	financeRatioY.setPeg(NumberFormatUtil.formatDouble(row.getCell(27).getNumericCellValue(),1));
				    	financeRatioY.setPB(NumberFormatUtil.formatDouble(row.getCell(28).getNumericCellValue(),1));
				    	financeRatioY.setPS(NumberFormatUtil.formatDouble(row.getCell(29).getNumericCellValue(),1));
				    	financeRatioY.setEvEbitda(NumberFormatUtil.formatDouble(row.getCell(30).getNumericCellValue(),1));
				    	financeRatioY.setEvEbit(NumberFormatUtil.formatDouble(row.getCell(31).getNumericCellValue(),1));
				    	financeRatioY.setEvFcf(NumberFormatUtil.formatDouble(row.getCell(32).getNumericCellValue(),1));
				    	financeRatioY.setRevFcf(NumberFormatUtil.formatDouble(row.getCell(33).getNumericCellValue(),1));
				    	financeRatioY.setMcCfo(NumberFormatUtil.formatDouble(row.getCell(34).getNumericCellValue(),1));
				    	financeRatioY.setMcNwc(NumberFormatUtil.formatDouble(row.getCell(35).getNumericCellValue(),1));
				    	financeRatioY.setFcff(NumberFormatUtil.formatDouble(row.getCell(36).getNumericCellValue(),1));
				    	financeRatioY.setFcfe(NumberFormatUtil.formatDouble(row.getCell(37).getNumericCellValue(),1));
				    	financeRatioY.setCapexRev(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue()*100,1));
				    	financeRatioY.setRoic(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue()*100,1));
				    	financeRatioY.setRoce(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue()*100,1));
				    	financeRatioY.setRoe(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue()*100,1));
				    	financeRatioY.setRoa(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue()*100,1));
				    	financeRatioY.setGrossProfitMargin(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue()*100,1));
				    	financeRatioY.setOperatingProfitMargin(NumberFormatUtil.formatDouble(row.getCell(44).getNumericCellValue()*100,1));
				    	financeRatioY.setPretaxProfitMargin(NumberFormatUtil.formatDouble(row.getCell(45).getNumericCellValue()*100,1));
				    	financeRatioY.setNetProfitMargin(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue()*100,1));
				    	financeRatioY.setDivYield(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue()*100,1));
				    	financeRatioY.setEbitRev(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue()*100,1));
				    	financeRatioY.setEbitdaRev(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue()*100,1));
				    	financeRatioY.setSalesToTotalAssets(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue()*100,1));
				    	financeRatioY.setReceivableTurnover(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue()*100,1));
				    	financeRatioY.setPayableTurnover(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue()*100,1));
				    	financeRatioY.setInventoryTurnover(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue()*100,1));
				    	financeRatioY.setDebtToAssetsRatio(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue()*100,1));
				    	financeRatioY.setDebtToEquityRatio(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue()*100,1));
				    	financeRatioY.setLongTimeDebtTotalCapitalazion(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue()*100,1));
				    	financeRatioY.setInterestCoverage(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue()*100,1));
				    	financeRatioY.setCurrentRatio(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue()*100,2));
				    	financeRatioY.setQuickRatio(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue()*100,2));
				    	financeRatioY.setCashRatio(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue()*100,2));
				    	financeRatioY.setAccountReceivableToRevenue(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue()*100,2));
				    	financeRatioY.setAccountReceivableToNetIncome(NumberFormatUtil.formatDouble(row.getCell(62).getNumericCellValue()*100,2));
				    	financeRatioY.setAllowancesAndProvisionsToNetIncome(NumberFormatUtil.formatDouble(row.getCell(63).getNumericCellValue()*100,2));
				    	financeRatioY.setFScore(NumberFormatUtil.formatDouble(row.getCell(64).getNumericCellValue(),0));
				    	financeRatioY.setCScore(NumberFormatUtil.formatDouble(row.getCell(65).getNumericCellValue(),0));
				    	financeRatioY.setMScore(NumberFormatUtil.formatDouble(row.getCell(66).getNumericCellValue(),2));
				    	financeRatioY.setZScore(NumberFormatUtil.formatDouble(row.getCell(67).getNumericCellValue(),2));
//				    	financeRatioY.setId(1L);
				    	financeRatioYRepository.save(financeRatioY);
				    }
				    if(!listUpdateOld.isEmpty()) {
				    	financeRatioYRepository.updateOldFinanceRatioFillter(listUpdateOld, timeString);
				    }
			    }
			    
			    workbook.close();
			    
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
		}
		return "import file thanh cong";
	}
	
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
	public String importReportCty(MultipartFile multipartFile) {
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
			    String timeString=file.getName();
			    timeString=timeString.split("\\.")[0];
			    if(timeString.contains("Q")) {
			    	
			    }else if(timeString.contains("Y")) {
			    	
			    	for (Row row : sheet) {
				    	rowIndex++;
				    	if(rowIndex<3) {
				    		continue;
				    	}
				    	if(null==row.getCell(0)) {
				    		break;
				    	}
				    	NormalReportY normalReportY = new NormalReportY();
				    	normalReportY.setStockCode(row.getCell(0).getStringCellValue());
				    	System.out.println("row:"+normalReportY.getStockCode()); 
				    	normalReportY.setCode(normalReportY.getStockCode()+timeString);
				    	normalReportY.setType(1);
				    	
//				    	if(null != row.getCell(1) && "NEW".equals(row.getCell(1).getStringCellValue())) {
//				    		listUpdateOld.add(financeRatioY.getStockCode());
//				    		financeRatioY.setStatus(0);
//				    	}else {
				    		normalReportY.setStatus(1);
//				    	}
				    	normalReportY.setCreatedTime(new Date());
				    	normalReportY.setTimeString(timeString);
				    	normalReportY.setStockId(1);
				    	
				    	normalReportY.setCurrentAsset(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),1));
				    	normalReportY.setCashAndCashEquivalents(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),1));
				    	normalReportY.setShortTermInvestments(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),1));
				    	normalReportY.setAccountsReceivableShortTerm(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),1));
				    	normalReportY.setInventories(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),1));
				    	normalReportY.setOtherCurrentAssets(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),1));
				    	normalReportY.setLongTermAssets(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),1));
				    	normalReportY.setAccountReceivableLongTerm(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),1));
				    	normalReportY.setFixedAssets(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),1));
				    	normalReportY.setTangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),1));
				    	normalReportY.setFinanceTangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),1));
				    	normalReportY.setIntangibleFixedAssets(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),1));
				    	normalReportY.setInvestmentProperty(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue(),1));
				    	normalReportY.setContructionInProgress(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue(),1));
				    	normalReportY.setLongTermInvestment(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue(),1));
				    	normalReportY.setGoodWill(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue(),1));
				    	normalReportY.setOtherLongTermAssets(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue(),1));
				    	normalReportY.setTotalAssets(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue(),1));
				    	normalReportY.setCurrentLiabilities(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue(),1));
				    	normalReportY.setAccountPayableToSuppliers(NumberFormatUtil.formatDouble(row.getCell(26).getNumericCellValue(),1));
				    	normalReportY.setShortTermAdvancesFromCustomers(NumberFormatUtil.formatDouble(row.getCell(27).getNumericCellValue(),1));
				    	normalReportY.setShortTermUnearnedRevenue(NumberFormatUtil.formatDouble(row.getCell(28).getNumericCellValue(),1));
				    	normalReportY.setShortTermBorrowingsAndLiabilities(NumberFormatUtil.formatDouble(row.getCell(29).getNumericCellValue(),1));
				    	normalReportY.setOtherShortTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(30).getNumericCellValue(),1));
				    	normalReportY.setLongTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(31).getNumericCellValue(),1));
				    	normalReportY.setLongTermAccountsPayable(NumberFormatUtil.formatDouble(row.getCell(32).getNumericCellValue(),1));
				    	normalReportY.setLongTermadvancesFromCustomers(NumberFormatUtil.formatDouble(row.getCell(33).getNumericCellValue(),1));
				    	normalReportY.setLongTermUnearnedRevenue(NumberFormatUtil.formatDouble(row.getCell(34).getNumericCellValue(),1));
				    	normalReportY.setLongTermBorrowingsAndLiabilities(NumberFormatUtil.formatDouble(row.getCell(35).getNumericCellValue(),1));
				    	normalReportY.setOtherLongTermLiabilities(NumberFormatUtil.formatDouble(row.getCell(36).getNumericCellValue(),1));
				    	normalReportY.setEquity(NumberFormatUtil.formatDouble(row.getCell(37).getNumericCellValue(),1));
				    	normalReportY.setShareCapital(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue(),1));
				    	normalReportY.setSharePremium(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue(),1));
				    	normalReportY.setRetainedProfits(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue(),1));
				    	normalReportY.setOtherCapitals(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue(),1));
				    	normalReportY.setNonControllingInterest(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue(),1));
				    	normalReportY.setTotalResources(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue(),1));
				    	normalReportY.setNetRevenue(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue(),1));
				    	normalReportY.setCostOfSales(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue(),1));
				    	normalReportY.setGrossProfit(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue(),1));
				    	normalReportY.setFinancialIncome(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue(),1));
				    	normalReportY.setFinancialExpenses(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue(),1));
				    	normalReportY.setInWhichInterestExpense(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue(),1));
				    	normalReportY.setShareOfProfitInAssociates(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue(),1));
				    	normalReportY.setSellingExpenses(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue(),1));
				    	normalReportY.setGeneralAndAdministrationExpenses(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue(),1));
				    	normalReportY.setNetOperatingProfit(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue(),1));
				    	normalReportY.setOtherIncome(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue(),1));
				    	normalReportY.setProfitBeforeTax(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue(),1));
				    	normalReportY.setIncomeTaxExpense(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue(),1));
				    	normalReportY.setNetProfitAfterTax(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue(),1));
				    	normalReportY.setMinorityInterest(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue(),1));
				    	normalReportY.setNetIncome(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue(),1));
				    	normalReportY.setDepreciation(NumberFormatUtil.formatDouble(row.getCell(65).getNumericCellValue(),1));
				    	normalReportY.setAllowancesAndProvisions(NumberFormatUtil.formatDouble(row.getCell(66).getNumericCellValue(),1));
				    	normalReportY.setNetCashFlowsFromOperatingActivities(NumberFormatUtil.formatDouble(row.getCell(67).getNumericCellValue(),1));
				    	normalReportY.setNetCashFlowsFromInvestingActivities(NumberFormatUtil.formatDouble(row.getCell(68).getNumericCellValue(),1));
				    	normalReportY.setNetCashFlowsFromFinancingActivities(NumberFormatUtil.formatDouble(row.getCell(69).getNumericCellValue(),1));
				    	normalReportY.setNetCashFlows(NumberFormatUtil.formatDouble(row.getCell(70).getNumericCellValue(),1));
				    	normalReportY.setPaymentsOfDividends(NumberFormatUtil.formatDouble(row.getCell(71).getNumericCellValue(),1));


				    	normalReportYRepository.save(normalReportY);
				    }
				    
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
	
	public ObjectOutPutDetailStockDTO detailStock(String code) {
		// List<Object> result = stockRepository.autoCompleteStock(searchPattern.toUpperCase());
		ObjectOutPutDetailStockDTO out = new ObjectOutPutDetailStockDTO();
		List<String> header= new ArrayList<>();
		List<Object> resultHeader =normalReportYRepository.searchHeaderReportData(code);
		
		header.add("Year End 30th Sep");
		if(!resultHeader.isEmpty()) {
			for (Object object : resultHeader) {
				header.add(object.toString());
			}
		}
//		header.add("2012");
//		header.add("2013");
//		header.add("2014");
//		header.add("2015");
		out.setHeaders(header);
		
		List<Object> result =normalReportYRepository.searchReportData(code);
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
//	    		data.add(object);
	    		Object[] arrayObject = (Object[]) object;
	    		for(int i=0; i<arrayObject.length;i++) {
	    			if(!"null".equals(arrayObject[i])) {
	    				data.get(i).add(arrayObject[i]);
	    			}
	    		}
//	    		ObjectOutPutDTO basicFilterDTO = ApiDTOBuilder.convertToObjectOutPutDTO(object);
//	    		objectOutPutDTOs.add(basicFilterDTO);
			}
    	}
    	out.setRows(data);
		
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
