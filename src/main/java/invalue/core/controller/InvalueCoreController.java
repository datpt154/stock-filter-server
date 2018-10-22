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
import invalue.core.dto.ScreenPageDTO;
import invalue.core.processor.InvalueCoreProcessor;
import invalue.core.repository.FinanceRatioQRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InvalueCoreController {

//    @Autowired
//    NoteRepository noteRepository;
    @Autowired
    FinanceRatioQRepository financeRatioQRepository;
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
    
//    @GetMapping("/autocompletestock")
//    public List<ObjectOutPutDTO> autoCompleteStock(@Valid @RequestBody String searchPattern) {
    @GetMapping("/autocompletestock/{searchPattern}")
    public List<ObjectOutPutDTO> autoCompleteStock(@PathVariable(value = "searchPattern") String searchPattern) {
		
//		Gson gson = new Gson();
//		Map<String,Object> map = new HashMap<String,Object>();
//		map = (Map<String,Object>) gson.fromJson(searchPattern, map.getClass());
//		String searchCode=(String) map.get("searchPattern");
    	
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
}
