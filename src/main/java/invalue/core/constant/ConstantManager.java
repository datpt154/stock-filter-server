
package invalue.core.constant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import invalue.core.vo.MapingColum;

/**
 * The Class ConstantManager.
 * 
 * @author Nguyen Phat Huy
 */
public class ConstantManager implements Serializable{
	/**
	 * 
	 */
	public static final Map<String, MapingColum> mapingColumFinanceRatio = new HashMap<String, MapingColum>(){
        {
            put("NET_REVENUE", new MapingColum(1,"NET_REVENUE","NET_REVENUE"));
            put("GROSS_PROFIT", new MapingColum(2,"GROSS_PROFIT","GROSS_PROFIT"));
            put("NET_INCOME", new MapingColum(3,"NET_INCOME","NET_INCOME"));
            put("SHARE_S_OUSTANDING", new MapingColum(4,"SHARE_S_OUSTANDING","SHARE_S_OUSTANDING"));
            put("EPS", new MapingColum(5,"EPS","EPS"));
            put("BOOK_VALUE", new MapingColum(6,"BOOK_VALUE","BOOK_VALUE"));
            put("MARKET_PRICE", new MapingColum(7,"MARKET_PRICE","MARKET_PRICE"));
            put("DAYYS", new MapingColum(8,"DAYYS","DAYYS"));
            put("CAPEX", new MapingColum(9,"CAPEX","CAPEX"));
            put("FCF", new MapingColum(10,"FCF","FCF"));
            put("EBIT", new MapingColum(11,"EBIT","EBIT"));
            put("EBITDA", new MapingColum(12,"EBITDA","EBITDA"));
            put("NNWC", new MapingColum(13,"NNWC","NNWC"));
            put("NET_WORKING_CAPITAL", new MapingColum(14,"NET_WORKING_CAPITAL","NET_WORKING_CAPITAL"));
            put("EV", new MapingColum(15,"EV","EV"));
            put("MARKET_CAPITAL", new MapingColum(16,"MARKET_CAPITAL","MARKET_CAPITAL"));
            put("NET_REVENUE_YOY", new MapingColum(17,"NET_REVENUE_YOY","NET_REVENUE_YOY"));
            put("GROSS_PROFIT_YOY", new MapingColum(18,"GROSS_PROFIT_YOY","GROSS_PROFIT_YOY"));
            put("EPS_YOY", new MapingColum(19,"EPS_YOY","EPS_YOY"));
            put("EBITDA_YOY", new MapingColum(20,"EBITDA_YOY","EBITDA_YOY"));
            put("DEBT_YOY", new MapingColum(21,"DEBT_YOY","DEBT_YOY"));
            put("EQUITY_YOY", new MapingColum(22,"EQUITY_YOY","EQUITY_YOY"));
            put("MARKET_CAPITAL_YOY", new MapingColum(23,"MARKET_CAPITAL_YOY","MARKET_CAPITAL_YOY"));
            put("TOTAL_ASSETS_YOY", new MapingColum(24,"TOTAL_ASSETS_YOY","TOTAL_ASSETS_YOY"));
            put("P_E", new MapingColum(25,"P_E","P_E"));
            put("PEG", new MapingColum(26,"PEG","PEG"));
            put("P_B", new MapingColum(27,"P_B","P_B"));
            put("P_S", new MapingColum(28,"P_S","P_S"));
            put("EV_EBITDA", new MapingColum(29,"EV_EBITDA","EV_EBITDA"));
            put("EV_EBIT", new MapingColum(30,"EV_EBIT","EV_EBIT"));
            put("EV_FCF", new MapingColum(31,"EV_FCF","EV_FCF"));
            put("REV_FCF", new MapingColum(32,"REV_FCF","REV_FCF"));
            put("MC_CFO", new MapingColum(33,"MC_CFO","MC_CFO"));
            put("MC_NWC", new MapingColum(34,"MC_NWC","MC_NWC"));
            put("FCFF", new MapingColum(35,"FCFF","FCFF"));
            put("FCFE", new MapingColum(36,"FCFE","FCFE"));
            put("CAPEX_REV", new MapingColum(37,"CAPEX_REV","CAPEX_REV"));
            put("ROIC", new MapingColum(38,"ROIC","ROIC"));
            put("ROCE", new MapingColum(39,"ROCE","ROCE"));
            put("ROE", new MapingColum(40,"ROE","ROE"));
            put("ROA", new MapingColum(41,"ROA","ROA"));
            put("GROSS_PROFIT_MARGIN", new MapingColum(42,"GROSS_PROFIT_MARGIN","GROSS_PROFIT_MARGIN"));
            put("OPERATING_PROFIT_MARGIN", new MapingColum(43,"OPERATING_PROFIT_MARGIN","OPERATING_PROFIT_MARGIN"));
            put("PRETAX_PROFIT_MARGIN", new MapingColum(44,"PRETAX_PROFIT_MARGIN","PRETAX_PROFIT_MARGIN"));
            put("NET_PROFIT_MARGIN", new MapingColum(45,"NET_PROFIT_MARGIN","NET_PROFIT_MARGIN"));
            put("DIV_YIELD", new MapingColum(46,"DIV_YIELD","DIV_YIELD"));
            put("EBIT_REV", new MapingColum(47,"EBIT_REV","EBIT_REV"));
            put("EBITDA_REV", new MapingColum(48,"EBITDA_REV","EBITDA_REV"));
            put("SALES_TO_TOTAL_ASSETS", new MapingColum(49,"SALES_TO_TOTAL_ASSETS","SALES_TO_TOTAL_ASSETS"));
            put("RECEIVABLE_TURNOVER", new MapingColum(50,"RECEIVABLE_TURNOVER","RECEIVABLE_TURNOVER"));
            put("PAYABLE_TURNOVER", new MapingColum(51,"PAYABLE_TURNOVER","PAYABLE_TURNOVER"));
            put("INVENTORY_TURNOVER", new MapingColum(52,"INVENTORY_TURNOVER","INVENTORY_TURNOVER"));
            put("DEBT_TO_ASSETS_RATIO", new MapingColum(53,"DEBT_TO_ASSETS_RATIO","DEBT_TO_ASSETS_RATIO"));
            put("DEBT_TO_EQUITY_RATIO", new MapingColum(54,"DEBT_TO_EQUITY_RATIO","DEBT_TO_EQUITY_RATIO"));
            put("LONG_TIME_DEBT_TOTAL_CAPITALAZION", new MapingColum(55,"LONG_TIME_DEBT_TOTAL_CAPITALAZION","LONG_TIME_DEBT_TOTAL_CAPITALAZION"));
            put("INTEREST_COVERAGE", new MapingColum(56,"INTEREST_COVERAGE","INTEREST_COVERAGE"));
            put("CURRENT_RATIO", new MapingColum(57,"CURRENT_RATIO","CURRENT_RATIO"));
            put("QUICK_RATIO", new MapingColum(58,"QUICK_RATIO","QUICK_RATIO"));
            put("CASH_RATIO", new MapingColum(59,"CASH_RATIO","CASH_RATIO"));
            put("ACCOUNT_RECEIVABLE_TO_REVENUE", new MapingColum(60,"ACCOUNT_RECEIVABLE_TO_REVENUE","ACCOUNT_RECEIVABLE_TO_REVENUE"));
            put("ACCOUNT_RECEIVABLE_TO_NET_INCOME", new MapingColum(61,"ACCOUNT_RECEIVABLE_TO_NET_INCOME","ACCOUNT_RECEIVABLE_TO_NET_INCOME"));
            put("ALLOWANCES_AND_PROVISIONS_TO_NET_INCOME", new MapingColum(62,"ALLOWANCES_AND_PROVISIONS_TO_NET_INCOME","ALLOWANCES_AND_PROVISIONS_TO_NET_INCOME"));
            put("F_SCORE", new MapingColum(63,"F_SCORE","F_SCORE"));
            put("C_SCORE", new MapingColum(64,"C_SCORE","C_SCORE"));
            put("M_SCORE", new MapingColum(65,"M_SCORE","M_SCORE"));
            put("Z_SCORE", new MapingColum(66,"Z_SCORE","Z_SCORE"));


        }
    };
	
}