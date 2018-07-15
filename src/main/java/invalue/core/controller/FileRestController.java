package invalue.core.controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import invalue.core.constant.ConstantManager;
import invalue.core.dto.ApiDTOBuilder;
import invalue.core.dto.BasicFilterDTO;
import invalue.core.entity.FinanceRatioQ;
import invalue.core.repository.FinanceRatioQRepository;
import invalue.core.vo.ObjectOutput;
import invalue.core.vo.ReportFilterInfo;

import javax.validation.Valid;

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

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FileRestController {

//    @Autowired
//    NoteRepository noteRepository;
    @Autowired
    FinanceRatioQRepository financeRatioQRepository;
//    @Autowired
//    NoteProcessor noteProcessor;
    
    @GetMapping("/notes")
    public List<Object> getAllNotes() {
    	ArrayList<Object> result = new ArrayList<>();
    	result.add("dat");
        return result;
    }
    
    @PostMapping("/filter")
    public Collection<BasicFilterDTO> getFiltered(@Valid @RequestBody List<ReportFilterInfo> listIn) {
//    	ObjectOutput out= new ObjectOutput();
//    	if(null==listIn || listIn.isEmpty()) {
//    		out.setCode(200);
//    		out.setMessage("Khong nhan duoc gia tri dau vao");
//    		out.setStatus(-1);
//    		return out;
//    	}
//    	for(int i=0; i<listIn.size(); i++) {
//    		if(!ConstantManager.mapingColumFinanceRatio.containsKey(listIn.get(i).getCode())) {
//    			out.setCode(200);
//        		out.setMessage("Gia tri dau vao khong hop le: "+listIn.get(i).getCode());
//        		out.setStatus(-1);
//        		return out;
//    		}
//    	}
    	List<Object> result = financeRatioQRepository.getFinanceRatioFillter(listIn);
    	Collection<BasicFilterDTO> basicFilterDTOs = new ArrayList<>();
    	for (Object object : result) {
    		BasicFilterDTO basicFilterDTO = ApiDTOBuilder.convertToDto(object,listIn);
    		basicFilterDTOs.add(basicFilterDTO);
		}
        return basicFilterDTOs;
    }
    
    @PostMapping("/importfile")
    public String importFile(@Valid @RequestBody MultipartFile multipartFile) {
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
			    	financeRatioQ.setCode(financeRatioQ.getStockCode()+"2018Q3");
			    	financeRatioQ.setType(1);
			    	financeRatioQ.setStatus(0);
			    	financeRatioQ.setCreatedTime(new Date());
			    	financeRatioQ.setTimeString("201807");
			    	financeRatioQ.setStockId(1);
			    	financeRatioQ.setNetRevenue(row.getCell(1).getNumericCellValue());
			    	financeRatioQ.setGrossProfit(row.getCell(2).getNumericCellValue());
			    	financeRatioQ.setNetIncome(row.getCell(3).getNumericCellValue());
			    	financeRatioQ.setShareSOustanding(row.getCell(4).getNumericCellValue());
			    	financeRatioQ.setEps(row.getCell(5).getNumericCellValue());
			    	financeRatioQ.setBookValue(row.getCell(6).getNumericCellValue());
			    	financeRatioQ.setMarketPrice(row.getCell(7).getNumericCellValue());
	//		    	financeRatioQ.setDayys(row.getCell(8).getNumericCellValue());
			    	financeRatioQ.setCapex(row.getCell(9).getNumericCellValue());
			    	financeRatioQ.setFcf(row.getCell(10).getNumericCellValue());
			    	financeRatioQ.setEbit(row.getCell(11).getNumericCellValue());
			    	financeRatioQ.setEbitda(row.getCell(12).getNumericCellValue());
			    	financeRatioQ.setNnwc(row.getCell(13).getNumericCellValue());
			    	financeRatioQ.setNetWorkingCapital(row.getCell(14).getNumericCellValue());
			    	financeRatioQ.setEv(row.getCell(15).getNumericCellValue());
			    	financeRatioQ.setMarketCapital(row.getCell(16).getNumericCellValue());
			    	financeRatioQ.setNetRevenueYoy(row.getCell(17).getNumericCellValue()*100);
			    	financeRatioQ.setGrossProfitYoy(row.getCell(18).getNumericCellValue()*100);
			    	financeRatioQ.setEpsYoy(row.getCell(19).getNumericCellValue()*100);
			    	financeRatioQ.setEbitdaYoy(row.getCell(20).getNumericCellValue()*100);
			    	financeRatioQ.setDebtYoy(row.getCell(21).getNumericCellValue()*100);
			    	financeRatioQ.setEquityYoy(row.getCell(22).getNumericCellValue()*100);
			    	financeRatioQ.setMarketCapitalYoy(row.getCell(23).getNumericCellValue()*100);
			    	financeRatioQ.setTotalAssetsYoy(row.getCell(24).getNumericCellValue()*100);
			    	financeRatioQ.setPE(row.getCell(25).getNumericCellValue());
			    	financeRatioQ.setPeg(row.getCell(26).getNumericCellValue());
			    	financeRatioQ.setPB(row.getCell(27).getNumericCellValue());
			    	financeRatioQ.setPS(row.getCell(28).getNumericCellValue());
			    	financeRatioQ.setEvEbitda(row.getCell(29).getNumericCellValue());
			    	financeRatioQ.setEvEbit(row.getCell(30).getNumericCellValue());
			    	financeRatioQ.setEvFcf(row.getCell(31).getNumericCellValue());
			    	financeRatioQ.setRevFcf(row.getCell(32).getNumericCellValue());
			    	financeRatioQ.setMcCfo(row.getCell(33).getNumericCellValue());
			    	financeRatioQ.setMcNwc(row.getCell(34).getNumericCellValue());
			    	financeRatioQ.setFcff(row.getCell(35).getNumericCellValue());
			    	financeRatioQ.setFcfe(row.getCell(36).getNumericCellValue());
			    	financeRatioQ.setCapexRev(row.getCell(37).getNumericCellValue());
			    	financeRatioQ.setRoic(row.getCell(38).getNumericCellValue());
			    	financeRatioQ.setRoce(row.getCell(39).getNumericCellValue());
			    	financeRatioQ.setRoe(row.getCell(40).getNumericCellValue());
			    	financeRatioQ.setRoa(row.getCell(41).getNumericCellValue());
			    	financeRatioQ.setGrossProfitMargin(row.getCell(42).getNumericCellValue());
			    	financeRatioQ.setOperatingProfitMargin(row.getCell(43).getNumericCellValue());
			    	financeRatioQ.setPretaxProfitMargin(row.getCell(44).getNumericCellValue());
			    	financeRatioQ.setNetProfitMargin(row.getCell(45).getNumericCellValue());
			    	financeRatioQ.setDivYield(row.getCell(46).getNumericCellValue());
			    	financeRatioQ.setEbitRev(row.getCell(47).getNumericCellValue());
			    	financeRatioQ.setEbitdaRev(row.getCell(48).getNumericCellValue());
			    	financeRatioQ.setSalesToTotalAssets(row.getCell(49).getNumericCellValue());
			    	financeRatioQ.setReceivableTurnover(row.getCell(50).getNumericCellValue());
			    	financeRatioQ.setPayableTurnover(row.getCell(51).getNumericCellValue());
			    	financeRatioQ.setInventoryTurnover(row.getCell(52).getNumericCellValue());
			    	financeRatioQ.setDebtToAssetsRatio(row.getCell(53).getNumericCellValue());
			    	financeRatioQ.setDebtToEquityRatio(row.getCell(54).getNumericCellValue());
			    	financeRatioQ.setLongTimeDebtTotalCapitalazion(row.getCell(55).getNumericCellValue());
			    	financeRatioQ.setInterestCoverage(row.getCell(56).getNumericCellValue());
			    	financeRatioQ.setCurrentRatio(row.getCell(57).getNumericCellValue());
			    	financeRatioQ.setQuickRatio(row.getCell(58).getNumericCellValue());
			    	financeRatioQ.setCashRatio(row.getCell(59).getNumericCellValue());
			    	financeRatioQ.setAccountReceivableToRevenue(row.getCell(60).getNumericCellValue());
			    	financeRatioQ.setAccountReceivableToNetIncome(row.getCell(61).getNumericCellValue());
			    	financeRatioQ.setAllowancesAndProvisionsToNetIncome(row.getCell(62).getNumericCellValue());
			    	financeRatioQ.setFScore(row.getCell(63).getNumericCellValue());
			    	financeRatioQ.setCScore(row.getCell(64).getNumericCellValue());
			    	financeRatioQ.setMScore(row.getCell(65).getNumericCellValue());
			    	financeRatioQ.setZScore(row.getCell(66).getNumericCellValue());
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
