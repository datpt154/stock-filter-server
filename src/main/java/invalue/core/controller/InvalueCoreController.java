package invalue.core.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import invalue.core.dto.BasicFilterDTO;
import invalue.core.dto.InputBasicFilterDTO;
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
    
    @PostMapping("/importfile")
    public String importFile(@Valid @RequestBody MultipartFile multipartFile, String timeString) {
    	return invalueCoreProcessor.importFile(multipartFile,timeString);
    }
    

}
