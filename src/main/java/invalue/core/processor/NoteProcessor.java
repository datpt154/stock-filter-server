package invalue.core.processor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import invalue.core.dto.ApiDTOBuilder;
import invalue.core.dto.BasicFilterDTO;
import invalue.core.entity.FinanceRatioQ;
import invalue.core.repository.FinanceRatioQRepository;
import invalue.core.util.NumberFormatUtil;
import invalue.core.vo.ReportFilterInfo;

@Component
public class NoteProcessor {
	
    @Autowired
    FinanceRatioQRepository financeRatioQRepository;
	
    public Collection<BasicFilterDTO> getFiltered(List<ReportFilterInfo> listIn) {    
    	List<Object> result = financeRatioQRepository.getFinanceRatioFillter(listIn);
    	Collection<BasicFilterDTO> basicFilterDTOs = new ArrayList<>();
    	for (Object object : result) {
    		BasicFilterDTO basicFilterDTO = ApiDTOBuilder.convertToDto(object,listIn);
    		basicFilterDTOs.add(basicFilterDTO);
		}
        return basicFilterDTOs;
    }
    
	public String importFile(MultipartFile multipartFile,String timeString) {
		try {
	    	if(multipartFile!=null) {
	    		InputStream in = multipartFile.getInputStream();
			    File currDir = new File(".");
			    String path = currDir.getAbsolutePath();
	//		    String fileLocation = file.substring(0, path.length() - 1) + file.getOriginalFilename();
			    File file= convert(multipartFile);
			    if(null==file) {
			    	return "false";
			    }
			    FileInputStream input = new FileInputStream(file.getPath());
			    Workbook workbook = new XSSFWorkbook(input);
			    Sheet sheet = workbook.getSheetAt(0);
			    
			    for (Row row : sheet) {
			    	
			    	FinanceRatioQ financeRatioQ = new FinanceRatioQ();
			    	financeRatioQ.setStockCode(row.getCell(0).getStringCellValue());
			    	System.out.println("row:"+financeRatioQ.getStockCode()); 
			    	financeRatioQ.setCode(financeRatioQ.getStockCode()+timeString);
			    	financeRatioQ.setType(1);
			    	financeRatioQ.setStatus(0);
			    	financeRatioQ.setCreatedTime(new Date());
			    	financeRatioQ.setTimeString(timeString);
			    	financeRatioQ.setStockId(1);
			    	financeRatioQ.setNetRevenue(NumberFormatUtil.formatDouble(row.getCell(1).getNumericCellValue(),1));
			    	financeRatioQ.setGrossProfit(NumberFormatUtil.formatDouble(row.getCell(2).getNumericCellValue(),1));
			    	financeRatioQ.setNetIncome(NumberFormatUtil.formatDouble(row.getCell(3).getNumericCellValue(),1));
			    	financeRatioQ.setShareSOustanding(NumberFormatUtil.formatDouble(row.getCell(4).getNumericCellValue(),1));
			    	financeRatioQ.setEps(NumberFormatUtil.formatDouble(row.getCell(5).getNumericCellValue(),0));
			    	financeRatioQ.setBookValue(NumberFormatUtil.formatDouble(row.getCell(6).getNumericCellValue(),0));
			    	financeRatioQ.setMarketPrice(NumberFormatUtil.formatDouble(row.getCell(7).getNumericCellValue(),0));
	//		    	financeRatioQ.setDayys(row.getCell(8).getNumericCellValue());
			    	financeRatioQ.setCapex(row.getCell(9).getNumericCellValue());//N?A
			    	financeRatioQ.setFcf(NumberFormatUtil.formatDouble(row.getCell(10).getNumericCellValue(),1));
			    	financeRatioQ.setEbit(NumberFormatUtil.formatDouble(row.getCell(11).getNumericCellValue(),1));
			    	financeRatioQ.setEbitda(NumberFormatUtil.formatDouble(row.getCell(12).getNumericCellValue(),1));
			    	financeRatioQ.setNnwc(NumberFormatUtil.formatDouble(row.getCell(13).getNumericCellValue(),1));
			    	financeRatioQ.setNetWorkingCapital(NumberFormatUtil.formatDouble(row.getCell(14).getNumericCellValue(),1));
			    	financeRatioQ.setEv(NumberFormatUtil.formatDouble(row.getCell(15).getNumericCellValue(),1));
			    	financeRatioQ.setMarketCapital(NumberFormatUtil.formatDouble(row.getCell(16).getNumericCellValue(),1));
			    	financeRatioQ.setNetRevenueYoy(NumberFormatUtil.formatDouble(row.getCell(17).getNumericCellValue()*100,2));
			    	financeRatioQ.setGrossProfitYoy(NumberFormatUtil.formatDouble(row.getCell(18).getNumericCellValue()*100,2));
			    	financeRatioQ.setEpsYoy(NumberFormatUtil.formatDouble(row.getCell(19).getNumericCellValue()*100,2));
			    	financeRatioQ.setEbitdaYoy(NumberFormatUtil.formatDouble(row.getCell(20).getNumericCellValue()*100,2));
			    	financeRatioQ.setDebtYoy(NumberFormatUtil.formatDouble(row.getCell(21).getNumericCellValue()*100,2));
			    	financeRatioQ.setEquityYoy(NumberFormatUtil.formatDouble(row.getCell(22).getNumericCellValue()*100,2));
			    	financeRatioQ.setMarketCapitalYoy(NumberFormatUtil.formatDouble(row.getCell(23).getNumericCellValue()*100,2));
			    	financeRatioQ.setTotalAssetsYoy(NumberFormatUtil.formatDouble(row.getCell(24).getNumericCellValue()*100,2));
			    	financeRatioQ.setPE(NumberFormatUtil.formatDouble(row.getCell(25).getNumericCellValue(),1));
			    	financeRatioQ.setPeg(NumberFormatUtil.formatDouble(row.getCell(26).getNumericCellValue(),1));
			    	financeRatioQ.setPB(NumberFormatUtil.formatDouble(row.getCell(27).getNumericCellValue(),1));
			    	financeRatioQ.setPS(NumberFormatUtil.formatDouble(row.getCell(28).getNumericCellValue(),1));
			    	financeRatioQ.setEvEbitda(NumberFormatUtil.formatDouble(row.getCell(29).getNumericCellValue(),1));
			    	financeRatioQ.setEvEbit(NumberFormatUtil.formatDouble(row.getCell(30).getNumericCellValue(),1));
			    	financeRatioQ.setEvFcf(NumberFormatUtil.formatDouble(row.getCell(31).getNumericCellValue(),1));
			    	financeRatioQ.setRevFcf(NumberFormatUtil.formatDouble(row.getCell(32).getNumericCellValue(),1));
			    	financeRatioQ.setMcCfo(NumberFormatUtil.formatDouble(row.getCell(33).getNumericCellValue(),1));
			    	financeRatioQ.setMcNwc(NumberFormatUtil.formatDouble(row.getCell(34).getNumericCellValue(),1));
			    	financeRatioQ.setFcff(NumberFormatUtil.formatDouble(row.getCell(35).getNumericCellValue(),1));
			    	financeRatioQ.setFcfe(NumberFormatUtil.formatDouble(row.getCell(36).getNumericCellValue(),1));
			    	financeRatioQ.setCapexRev(NumberFormatUtil.formatDouble(row.getCell(37).getNumericCellValue(),1));
			    	financeRatioQ.setRoic(NumberFormatUtil.formatDouble(row.getCell(38).getNumericCellValue(),2));
			    	financeRatioQ.setRoce(NumberFormatUtil.formatDouble(row.getCell(39).getNumericCellValue(),2));
			    	financeRatioQ.setRoe(NumberFormatUtil.formatDouble(row.getCell(40).getNumericCellValue(),2));
			    	financeRatioQ.setRoa(NumberFormatUtil.formatDouble(row.getCell(41).getNumericCellValue(),2));
			    	financeRatioQ.setGrossProfitMargin(NumberFormatUtil.formatDouble(row.getCell(42).getNumericCellValue(),2));
			    	financeRatioQ.setOperatingProfitMargin(NumberFormatUtil.formatDouble(row.getCell(43).getNumericCellValue(),2));
			    	financeRatioQ.setPretaxProfitMargin(NumberFormatUtil.formatDouble(row.getCell(44).getNumericCellValue(),2));
			    	financeRatioQ.setNetProfitMargin(NumberFormatUtil.formatDouble(row.getCell(45).getNumericCellValue(),2));
			    	financeRatioQ.setDivYield(NumberFormatUtil.formatDouble(row.getCell(46).getNumericCellValue(),2));
			    	financeRatioQ.setEbitRev(NumberFormatUtil.formatDouble(row.getCell(47).getNumericCellValue(),2));
			    	financeRatioQ.setEbitdaRev(NumberFormatUtil.formatDouble(row.getCell(48).getNumericCellValue(),2));
			    	financeRatioQ.setSalesToTotalAssets(NumberFormatUtil.formatDouble(row.getCell(49).getNumericCellValue(),1));
			    	financeRatioQ.setReceivableTurnover(NumberFormatUtil.formatDouble(row.getCell(50).getNumericCellValue(),1));
			    	financeRatioQ.setPayableTurnover(NumberFormatUtil.formatDouble(row.getCell(51).getNumericCellValue(),1));
			    	financeRatioQ.setInventoryTurnover(NumberFormatUtil.formatDouble(row.getCell(52).getNumericCellValue(),1));
			    	financeRatioQ.setDebtToAssetsRatio(NumberFormatUtil.formatDouble(row.getCell(53).getNumericCellValue(),1));
			    	financeRatioQ.setDebtToEquityRatio(NumberFormatUtil.formatDouble(row.getCell(54).getNumericCellValue(),1));
			    	financeRatioQ.setLongTimeDebtTotalCapitalazion(NumberFormatUtil.formatDouble(row.getCell(55).getNumericCellValue(),1));
			    	financeRatioQ.setInterestCoverage(NumberFormatUtil.formatDouble(row.getCell(56).getNumericCellValue(),1));
			    	financeRatioQ.setCurrentRatio(NumberFormatUtil.formatDouble(row.getCell(57).getNumericCellValue(),2));
			    	financeRatioQ.setQuickRatio(NumberFormatUtil.formatDouble(row.getCell(58).getNumericCellValue(),2));
			    	financeRatioQ.setCashRatio(NumberFormatUtil.formatDouble(row.getCell(59).getNumericCellValue(),2));
			    	financeRatioQ.setAccountReceivableToRevenue(NumberFormatUtil.formatDouble(row.getCell(60).getNumericCellValue(),2));
			    	financeRatioQ.setAccountReceivableToNetIncome(NumberFormatUtil.formatDouble(row.getCell(61).getNumericCellValue(),2));
			    	financeRatioQ.setAllowancesAndProvisionsToNetIncome(NumberFormatUtil.formatDouble(row.getCell(62).getNumericCellValue(),2));
			    	financeRatioQ.setFScore(NumberFormatUtil.formatDouble(row.getCell(63).getNumericCellValue(),0));
			    	financeRatioQ.setCScore(NumberFormatUtil.formatDouble(row.getCell(64).getNumericCellValue(),0));
			    	financeRatioQ.setMScore(NumberFormatUtil.formatDouble(row.getCell(65).getNumericCellValue(),2));
			    	financeRatioQ.setZScore(NumberFormatUtil.formatDouble(row.getCell(66).getNumericCellValue(),2));
//			    	financeRatioQ.setId(1L);
			    	financeRatioQRepository.save(financeRatioQ);
			    }
			    workbook.close();
	    	}
    	}catch(Exception ex) {
			System.out.println(ex);
		}
		return "abc";
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
