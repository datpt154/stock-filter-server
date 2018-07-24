package invalue.core.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import invalue.core.vo.ReportFilterInfo;

public class ApiDTOBuilder {
	@Autowired
	private static ModelMapper modelMapper;

	public static BasicFilterDTO convertToBasicFilterDTOToDto(Object object, List<ReportFilterInfo> listIn) {
		BasicFilterDTO basicFilterDTO = new BasicFilterDTO();

		Object[] arrayObject = (Object[]) object;
		basicFilterDTO.setCompanyCode((String) arrayObject[0]);
		basicFilterDTO.setCompanyName((String) arrayObject[1]);
		basicFilterDTO.setStockExchange((String) arrayObject[2]);
		basicFilterDTO.setPrice((Double) arrayObject[3]);
		basicFilterDTO.setSearchItems(new ArrayList<>());

		for (int i = 0; i < listIn.size(); i++) {
			SearchItemDTO searchItem = new SearchItemDTO();
			searchItem.setCode(listIn.get(i).getCode());
			searchItem.setValue((Double) arrayObject[i+4]);

			basicFilterDTO.getSearchItems().add(searchItem);
		}

		return basicFilterDTO;
	}
	public static ObjectOutPutDTO convertToObjectOutPutDTO(Object object) {
		ObjectOutPutDTO dto = new ObjectOutPutDTO();
		
		Object[] arrayObject = (Object[]) object;
		dto.setId((BigInteger) arrayObject[0]);
		dto.setCode((String) arrayObject[1]);
		dto.setName((String) arrayObject[2]);

		return dto;
	}
	public static CompareFilterDTO convertToCompareFilterDTO(Object object, List<ReportFilterInfo> listIn) {
		CompareFilterDTO compareFilterDTO = new CompareFilterDTO();

		Object[] arrayObject = (Object[]) object;
		compareFilterDTO.setCompanyCode((String) arrayObject[0]);
		compareFilterDTO.setPrice((Double) arrayObject[1]);
		compareFilterDTO.setSearchItems(new ArrayList<>());

		for (int i = 0; i < listIn.size(); i++) {
			SearchItemDTO searchItem = new SearchItemDTO();
			searchItem.setCode(listIn.get(i).getCode());
			searchItem.setValue((Double) arrayObject[i+2]);

			compareFilterDTO.getSearchItems().add(searchItem);
		}

		return compareFilterDTO;
	}
}
