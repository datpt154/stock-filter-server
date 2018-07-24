package invalue.core.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import invalue.core.dto.BasicFilterDTO;
import invalue.core.dto.CompareFilterDTO;
import invalue.core.dto.InputBasicFilterDTO;
import invalue.core.dto.InputCompareFilterDTO;
import invalue.core.dto.ObjectOutPutDTO;
import invalue.core.processor.InvalueCoreProcessor;
import invalue.core.repository.FinanceRatioQRepository;
import invalue.core.vo.ReportFilterInfo;

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
    public String importFinanceratio(@Valid @RequestBody MultipartFile multipartFile) {
    	return invalueCoreProcessor.importFinanceratio(multipartFile);
    }
    
    @PostMapping("/importcty")
    public String importCty(@Valid @RequestBody MultipartFile multipartFile) {
    	return invalueCoreProcessor.importCty(multipartFile);
    }
    
    @PostMapping("/autocompletestock")
    public List<ObjectOutPutDTO> autoCompleteStock(@Valid @RequestBody String searchPattern) {
    	
		
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		map = (Map<String,Object>) gson.fromJson(searchPattern, map.getClass());
		String searchCode=(String) map.get("searchPattern");
    	
    	return invalueCoreProcessor.autoCompleteStock(searchCode);
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
}
