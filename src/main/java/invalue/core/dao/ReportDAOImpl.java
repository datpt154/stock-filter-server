package invalue.core.dao;

import invalue.core.vo.ReportFilterInfo;
import invalue.core.vo.ReportVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

//import vn.kunkun.cafe.core.entities.CF_AppConfigs;
//import vn.kunkun.cafe.core.entities.CF_Dishes;
//import vn.kunkun.cafe.core.service.SyncCF_AppConfigs;
//import vn.kunkun.cafe.core.service.SyncCF_Dishes;
//import vn.kunkun.core.dao.IRepository;
//import vn.kunkun.core.exceptions.DataAccessException;
//import vn.kunkun.core.utils.StringUtil;
//import vn.kunkun.core.vo.BranchFilter;
//import vn.kunkun.kara.core.service.SyncKA_AppConfigs;

public class ReportDAOImpl implements ReportDAO{
//	@Autowired
//	IRepository repo;

//	@Override
//	public List<ReportVO> getListStockSearch(List<ReportFilterInfo> listIn)
//			throws DataAccessException {
//		StringBuilder sql = new StringBuilder();
//		List<Object> params = new ArrayList<Object>();
//
//		sql.append(" SELECT ID id, ");
//		sql.append(" fcf ");
//		sql.append(" ebit , ");
//		sql.append(" ebitda , ");
//		sql.append(" eps , ");
//		sql.append(" dayys , ");
//		sql.append(" capex ");
//		sql.append(" from finance_ratio_q where 1=1 ");
//		for(int i=0;i<listIn.size();i++){
//			sql.append(" and "+listIn.get(i).getCode()+">= ?" );
//			params.add(listIn.get(i).getMin());
//			sql.append(" and "+listIn.get(i).getCode()+"<= ?" );
//			params.add(listIn.get(i).getMax());
//		}
//
//		String[] fieldNames = { "id",// 1
//				"fcf",// 2
//				"ebit",// 3
//				"ebitda",// 4
//				"eps",// 5
//				"dayys",// 6
//				"capex"
//		};
//		Type[] fieldTypes = { StandardBasicTypes.INTEGER,// 1
//				StandardBasicTypes.DOUBLE,// 2
//				StandardBasicTypes.DOUBLE, // 3
//				StandardBasicTypes.DOUBLE,// 4
//				StandardBasicTypes.DOUBLE, // 5
//				StandardBasicTypes.DOUBLE, // 6
//				StandardBasicTypes.DOUBLE
//		};
//		List<Class<?>> synchronizedClass = new ArrayList<Class<?>>();
//		synchronizedClass.add(CF_Dishes.class);
//		List<ReportVO> list = repo.getListByQueryAndScalar(ReportVO.class, fieldNames, fieldTypes, sql.toString(), params,synchronizedClass);
//		
//		return list;
//	}

	
}
