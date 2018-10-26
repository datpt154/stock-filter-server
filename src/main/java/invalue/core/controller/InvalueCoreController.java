package invalue.core.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import invalue.core.dto.BasicFilterDTO;
import invalue.core.dto.CompareFilterDTO;
import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;
import invalue.core.dto.InputSearchStockDTO;
import invalue.core.dto.ObjectOutPutDTO;
import invalue.core.dto.ObjectOutPutDetailStockDTO;
import invalue.core.dto.ObjectOutPutDetailStockMoreDTO;
import invalue.core.dto.OutPutScreenBenjamin;
import invalue.core.dto.OutPutScreenBreakOut;
import invalue.core.dto.OutPutScreenCANSLIM;
import invalue.core.dto.OutPutScreenGrahamChecklist;
import invalue.core.dto.OutPutScreenJohnNeffValue;
import invalue.core.dto.OutPutScreenPeterLynchGrowth;
import invalue.core.dto.OutPutScreenPhilipFisherGrowth;
import invalue.core.dto.ScreenPageDTO;
import invalue.core.processor.InvalueCoreProcessor;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InvalueCoreController {

//    @Autowired
//    NoteRepository noteRepository;
    @Autowired
    InvalueCoreProcessor invalueCoreProcessor;
    
    @GetMapping("/notes")
    public List<Object> getAllNotes() {
    	ArrayList<Object> result = new ArrayList<>();
    	result.add("dat");
        return result;
    }
    
    @GetMapping("/checkconnection")
    public Integer checkconnection() {
        return 1;
    }
    
    @PostMapping("/filter")
    public Collection<BasicFilterDTO> getFiltered(@Valid @RequestBody InputBasicFilterDTO inputBasicFilterDTO) {
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
    	return invalueCoreProcessor.getFiltered(inputBasicFilterDTO);
    }
    
    @PostMapping("/importfinanceratio")
    public String importFinanceratio(@Valid @RequestBody MultipartFile multipartFile, String timeString) {
    	return invalueCoreProcessor.importFinanceratio(multipartFile,timeString);
    }
    
    @PostMapping("/importcty")
    public String importCty(@Valid @RequestBody MultipartFile multipartFile) {
    	return invalueCoreProcessor.importCty(multipartFile);
    }
    
    @PostMapping("/importreportcty")
    public String importReportCty(@Valid @RequestBody MultipartFile multipartFile, String timeString) {
    	return invalueCoreProcessor.importReportCty(multipartFile,timeString);
    }
    @PostMapping("/importrecommandations")
    public String importRecommandations(@Valid @RequestBody MultipartFile multipartFile) {
    	return invalueCoreProcessor.importRecommandations(multipartFile);
    }
    @PostMapping("/importplanofyear")
    public String importPlanOfYear(@Valid @RequestBody MultipartFile multipartFile, String timeString) {
    	return invalueCoreProcessor.importPlanOfYear(multipartFile, timeString);
    }
    @PostMapping("/importBreakOut")
    public String importBreakOut(@Valid @RequestBody MultipartFile multipartFile) {
    	return invalueCoreProcessor.importBreakOut(multipartFile);
    }
    
    @GetMapping("/autocompletestock/{searchPattern}")
    public List<ObjectOutPutDTO> autoCompleteStock(@PathVariable(value = "searchPattern") String searchPattern) {
    	return invalueCoreProcessor.autoCompleteStock(searchPattern);
    }
    
    @PostMapping("/detailstock")
    public ObjectOutPutDetailStockDTO detailStock(@Valid @RequestBody InputSearchStockDTO inputSearchStockDTO) {
    	return invalueCoreProcessor.detailStock(inputSearchStockDTO);
    }
    
    @PostMapping("/detailstockmore")
    public ObjectOutPutDetailStockMoreDTO detailStockMore(@Valid @RequestBody InputSearchStockDTO inputSearchStockDTO) {
    	return invalueCoreProcessor.detailStockMore(inputSearchStockDTO);
    }
    
    @PostMapping("/detailstockfinanceratio")
    public ObjectOutPutDetailStockMoreDTO detailStockFinanceRatio(@Valid @RequestBody InputSearchStockDTO inputSearchStockDTO) {
    	return invalueCoreProcessor.detailStockFinanceRatio(inputSearchStockDTO);
    }
    
    @PostMapping("/Compare")
    public Collection<CompareFilterDTO> getCompareFiltered(@Valid @RequestBody InputCompareFilterDTO inputCompareFilterDTO) {
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
    	return invalueCoreProcessor.getCompareFiltered(inputCompareFilterDTO);
    }
    @GetMapping("/screenRevenue")
    public ScreenPageDTO screenRevenue() {
    	return invalueCoreProcessor.screenRevenue();
    }
    @GetMapping("/screenProfit")
    public ScreenPageDTO screenProfit() {
    	return invalueCoreProcessor.screenProfit();
    }
    @GetMapping("/screenEPS")
    public ScreenPageDTO screenEPS() {
    	return invalueCoreProcessor.screenEPS();
    }
    @GetMapping("/screenPE_PB")
    public ScreenPageDTO screenPE_PB() {
    	return invalueCoreProcessor.screenPE_PB();
    }
    @GetMapping("/screenMCNWC_EVEBITDA")
    public ScreenPageDTO screenMCNWC_EVEBITDA() {
    	return invalueCoreProcessor.screenMCNWC_EVEBITDA();
    }
    @GetMapping("/screenNetNet")
    public List<OutPutScreenBenjamin> screenNetNet() {
    	return invalueCoreProcessor.screenNetNet();
    }
    @GetMapping("/screnNCAV")
    public List<OutPutScreenBenjamin> screnNCAV() {
    	return invalueCoreProcessor.screnNCAV();
    }
    @GetMapping("/screnCANSLIM")
    public List<OutPutScreenCANSLIM> screnCANSLIM() {
    	return invalueCoreProcessor.screnCANSLIM();
    }
    @GetMapping("/screnPhilipFisherGrowth")
    public List<OutPutScreenPhilipFisherGrowth> screnPhilipFisherGrowth() {
    	return invalueCoreProcessor.screnPhilipFisherGrowth();
    }
    @GetMapping("/screnJohnNeffValue")
    public List<OutPutScreenJohnNeffValue> screnJohnNeffValue() {
    	return invalueCoreProcessor.screnJohnNeffValue();
    }
    @GetMapping("/screnPeterLynchGrowth")
    public List<OutPutScreenPeterLynchGrowth> screnPeterLynchGrowth() {
    	return invalueCoreProcessor.screnPeterLynchGrowth();
    }
    @GetMapping("/screnGrahamChecklist")
    public List<OutPutScreenGrahamChecklist> screnGrahamChecklist() {
    	return invalueCoreProcessor.screnGrahamChecklist();
    }
    @GetMapping("/screnBreakResistance")
    public List<OutPutScreenBreakOut> screnBreakResistance() {
    	return invalueCoreProcessor.screnBreakResistance();
    }
    @GetMapping("/screnBreakSupport")
    public List<OutPutScreenBreakOut> screnBreakSupport() {
    	return invalueCoreProcessor.screnBreakSupport();
    }
    @GetMapping("/screnTrendTrader")
    public List<OutPutScreenBreakOut> screnTrendTrader() {
    	return invalueCoreProcessor.screnTrendTrader();
    }
}
