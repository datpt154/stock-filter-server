package invalue.core.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import invalue.core.dto.ApiDTOBuilder;
import invalue.core.dto.BasicFilterDTO;
import invalue.core.dto.CompareFilterDTO;
import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;
import invalue.core.dto.ObjectOutPutDTO;
import invalue.core.dto.SearchItemDTO;
import invalue.core.entity.FinanceRatioQ;
import invalue.core.entity.FinanceRatioY;
import invalue.core.entity.Stock;
import invalue.core.repository.FinanceRatioQRepository;
import invalue.core.repository.FinanceRatioYRepository;
import invalue.core.repository.StockRepository;
import invalue.core.util.NumberFormatUtil;

@Component
public class InvalueCoreProcessor {
	
    @Autowired
    FinanceRatioQRepository financeRatioQRepository;
    @Autowired
    FinanceRatioYRepository financeRatioYRepository;
    @Autowired
    StockRepository stockRepository;
	
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
    			}
	    		sumCompareFilterDTO.setPrice(sumCompareFilterDTO.getPrice()+compareFilterDTOs.get(i).getPrice());
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
				    	financeRatioQ.setEps(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),0));
				    	financeRatioQ.setBookValue(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),0));
				    	financeRatioQ.setMarketPrice(NumberFormatUtil.formatDouble(row.getCell(8).getNumericCellValue(),0));
		//		    	financeRatioQ.setDayys(row.getCell(9).getNumericCellValue());
				    	financeRatioQ.setCapex(row.getCell(10).getNumericCellValue());//N?A
				    	financeRatioQ.setFcf(NumberFormatUtil.formatDouble(row.getCell(11).getNumericCellValue(),1));
				    	financeRatioQ.setEbit(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),1));
				    	financeRatioQ.setEbitda(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),1));
				    	financeRatioQ.setNnwc(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),1));
				    	financeRatioQ.setNetWorkingCapital(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),1));
				    	financeRatioQ.setEv(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),1));
				    	financeRatioQ.setMarketCapital(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),1));
				    	financeRatioQ.setNetRevenueYoy(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue()*100,2));
				    	financeRatioQ.setGrossProfitYoy(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue()*100,2));
				    	financeRatioQ.setEpsYoy(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue()*100,2));
				    	financeRatioQ.setEbitdaYoy(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue()*100,2));
				    	financeRatioQ.setDebtYoy(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue()*100,2));
				    	financeRatioQ.setEquityYoy(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue()*100,2));
				    	financeRatioQ.setMarketCapitalYoy(NumberFormatUtil.formatDouble(row.getCell(24).getNumericCellValue()*100,2));
				    	financeRatioQ.setTotalAssetsYoy(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue()*100,2));
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
				    	financeRatioQ.setCapexRev(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue(),1));
				    	financeRatioQ.setRoic(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue(),2));
				    	financeRatioQ.setRoce(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue(),2));
				    	financeRatioQ.setRoe(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue(),2));
				    	financeRatioQ.setRoa(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue(),2));
				    	financeRatioQ.setGrossProfitMargin(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue(),2));
				    	financeRatioQ.setOperatingProfitMargin(NumberFormatUtil.formatDouble(row.getCell(44).getNumericCellValue(),2));
				    	financeRatioQ.setPretaxProfitMargin(NumberFormatUtil.formatDouble(row.getCell(45).getNumericCellValue(),2));
				    	financeRatioQ.setNetProfitMargin(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue(),2));
				    	financeRatioQ.setDivYield(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue(),2));
				    	financeRatioQ.setEbitRev(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue(),2));
				    	financeRatioQ.setEbitdaRev(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue(),2));
				    	financeRatioQ.setSalesToTotalAssets(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue(),1));
				    	financeRatioQ.setReceivableTurnover(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue(),1));
				    	financeRatioQ.setPayableTurnover(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue(),1));
				    	financeRatioQ.setInventoryTurnover(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue(),1));
				    	financeRatioQ.setDebtToAssetsRatio(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue(),1));
				    	financeRatioQ.setDebtToEquityRatio(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue(),1));
				    	financeRatioQ.setLongTimeDebtTotalCapitalazion(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue(),1));
				    	financeRatioQ.setInterestCoverage(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue(),1));
				    	financeRatioQ.setCurrentRatio(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue(),2));
				    	financeRatioQ.setQuickRatio(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue(),2));
				    	financeRatioQ.setCashRatio(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue(),2));
				    	financeRatioQ.setAccountReceivableToRevenue(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue(),2));
				    	financeRatioQ.setAccountReceivableToNetIncome(NumberFormatUtil.formatDouble(row.getCell(62).getNumericCellValue(),2));
				    	financeRatioQ.setAllowancesAndProvisionsToNetIncome(NumberFormatUtil.formatDouble(row.getCell(63).getNumericCellValue(),2));
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
				    	financeRatioY.setEps(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),0));
				    	financeRatioY.setBookValue(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),0));
				    	financeRatioY.setMarketPrice(NumberFormatUtil.formatDouble(row.getCell(8).getNumericCellValue(),0));
		//		    	financeRatioY.setDayys(row.getCell(9).getNumericCellValue());
				    	financeRatioY.setCapex(row.getCell(10).getNumericCellValue());//N?A
				    	financeRatioY.setFcf(NumberFormatUtil.formatDouble(row.getCell(11).getNumericCellValue(),1));
				    	financeRatioY.setEbit(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),1));
				    	financeRatioY.setEbitda(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),1));
				    	financeRatioY.setNnwc(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),1));
				    	financeRatioY.setNetWorkingCapital(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),1));
				    	financeRatioY.setEv(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),1));
				    	financeRatioY.setMarketCapital(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue(),1));
				    	financeRatioY.setNetRevenueYoy(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue()*100,2));
				    	financeRatioY.setGrossProfitYoy(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue()*100,2));
				    	financeRatioY.setEpsYoy(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue()*100,2));
				    	financeRatioY.setEbitdaYoy(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue()*100,2));
				    	financeRatioY.setDebtYoy(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue()*100,2));
				    	financeRatioY.setEquityYoy(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue()*100,2));
				    	financeRatioY.setMarketCapitalYoy(NumberFormatUtil.formatDouble(row.getCell(24).getNumericCellValue()*100,2));
				    	financeRatioY.setTotalAssetsYoy(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue()*100,2));
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
				    	financeRatioY.setCapexRev(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue(),1));
				    	financeRatioY.setRoic(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue(),2));
				    	financeRatioY.setRoce(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue(),2));
				    	financeRatioY.setRoe(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue(),2));
				    	financeRatioY.setRoa(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue(),2));
				    	financeRatioY.setGrossProfitMargin(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue(),2));
				    	financeRatioY.setOperatingProfitMargin(NumberFormatUtil.formatDouble(row.getCell(44).getNumericCellValue(),2));
				    	financeRatioY.setPretaxProfitMargin(NumberFormatUtil.formatDouble(row.getCell(45).getNumericCellValue(),2));
				    	financeRatioY.setNetProfitMargin(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue(),2));
				    	financeRatioY.setDivYield(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue(),2));
				    	financeRatioY.setEbitRev(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue(),2));
				    	financeRatioY.setEbitdaRev(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue(),2));
				    	financeRatioY.setSalesToTotalAssets(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue(),1));
				    	financeRatioY.setReceivableTurnover(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue(),1));
				    	financeRatioY.setPayableTurnover(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue(),1));
				    	financeRatioY.setInventoryTurnover(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue(),1));
				    	financeRatioY.setDebtToAssetsRatio(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue(),1));
				    	financeRatioY.setDebtToEquityRatio(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue(),1));
				    	financeRatioY.setLongTimeDebtTotalCapitalazion(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue(),1));
				    	financeRatioY.setInterestCoverage(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue(),1));
				    	financeRatioY.setCurrentRatio(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue(),2));
				    	financeRatioY.setQuickRatio(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue(),2));
				    	financeRatioY.setCashRatio(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue(),2));
				    	financeRatioY.setAccountReceivableToRevenue(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue(),2));
				    	financeRatioY.setAccountReceivableToNetIncome(NumberFormatUtil.formatDouble(row.getCell(62).getNumericCellValue(),2));
				    	financeRatioY.setAllowancesAndProvisionsToNetIncome(NumberFormatUtil.formatDouble(row.getCell(63).getNumericCellValue(),2));
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
	
	public List<ObjectOutPutDTO> autoCompleteStock(String searchPattern) {
		List<Object> result = stockRepository.autoCompleteStock(searchPattern);
    	
		List<ObjectOutPutDTO> objectOutPutDTOs = new ArrayList<>();
    	if(!result.isEmpty()) {
	    	for (Object object : result) {
	    		ObjectOutPutDTO basicFilterDTO = ApiDTOBuilder.convertToObjectOutPutDTO(object);
	    		objectOutPutDTOs.add(basicFilterDTO);
			}
    	}
        return objectOutPutDTOs;
		
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
