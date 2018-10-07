
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
        //List 
        public static final  String[] vDetailStockFinanceRatio= new String[]{ "Dữ liệu tài chính"//0
        		,"Doanh thu thuần"//1
        		,"Lợi nhuận gộp"//2
        		,"LNST"//3
        		,"KL Cổ phiếu"//4
        		,"EPS"//5
        		,"Giá trị sổ sách"//6
        		,"Giá thị trường"//7
        		,"Chi phí đầu tư (Capex)"//9
        		,"Dòng tiền tự do"//10
        		,"EBIT"//11
        		,"EBITDA"//12
        		,"N-NWC"//13
        		,"Vốn lưu động"//14
        		,"Giá trị doanh nghiệp (EV)"//15
        		,"Vốn hóa"//16
        		,"Tăng trưởng so với cùng kỳ"//17
        		,"TT Doanh thu "//18
        		,"TT LNST"//19
        		,"TT EPS"//20
        		,"TT EBITDA"//21
        		,"TT Nợ"//22
        		,"TT VCSH"//23
        		,"TT Vốn hóa"//24
        		,"TT Tài sản"//25
        		,"Tỷ số định giá"//26
        		,"P/E"//27
        		,"Peg"//28
        		,"P/B"//29
        		,"P/S"//30
        		,"EV/EBITDA"//31
        		,"EV/EBIT"//32
        		,"EV/FCF"//33
        		,"Doanh thu/ Dòng tiền tự do"//34
        		,"Vốn hóa/ Dòng tiền HĐKD"//35
        		,"Vốn hóa/ Vốn lưu động"//36
        		,"Hiệu quả đầu tư"//37
        		,"Capex/Rev"//38
        		,"ROIC"//39
        		,"ROCE"//40
        		,"ROE"//41
        		,"ROA"//42
        		,"Lợi nhuận biên"//43
        		,"Tỷ lệ lãi gộp"//44
        		,"LN hoạt động biên"//45
        		,"LN trước thuế biên"//46
        		,"Tỷ lệ lãi ròng"//47
        		,"Tỷ suất cổ tức"//48
        		,"Tỷ suất EBIT"//49
        		,"Tỷ suất EBITDA"//50
        		,"Hiệu quả hoạt động"//51
        		,"Vòng quay tài sản"//52
        		,"Vòng quay phải thu"//53
        		,"Vòng quay phải trả"//54
        		,"Vòng quay tồn kho"//55
        		,"Đòn bẩy tài chính"//56
        		,"Nợ/Tổng tài sản"//57
        		,"Nợ/Vốn chủ sở hữu"//58
        		,"Nợ dài hạn/Vốn hóa"//59
        		,"Khả năng trả lãi vay"//60
        		,"Hệ số thanh toán"//61
        		,"Hệ số thanh toán hiện hành"//62
        		,"Hệ số thanh toán nhanh"//63
        		,"Hệ số thanh toán tiền mặt"//64
        		,"Rủi ro"//65
        		,"Phải thu/ Doanh thu"//66
        		,"Phải thu/ LNST"//67
        		,"Dự phòng/ LNST"//68
        		,"Đánh giá BCTC"//69
        		,"F-Score (Chất lượng BCTC)"//70
        		,"C-score (sửa đổi BCTC)"//71
        		,"M-score (Làm giả lợi nhuận)"//72
        		,"Z-score (khả năng phá sản)"
        }; 
        public static final  String[] eDetailStockFinanceRatio= new String[]{ "Finance" //0
        		,"Revenue"//1
        		,"Gross profit"//2
        		,"Income"//3
        		,"Shares"//4
        		,"EPS"//5
        		,"BV"//6
        		,"Price"//7
        		,"Capex "//8
        		,"FCF"//9
        		,"EBIT"//10
        		,"EBITDA"//11
        		,"NNWC"//12
        		,"Net working capital"//13
        		,"EV"//14
        		,"Capitalization"//15
        		,"Finance Growth"//16
        		,"Revenue Growth"//17
        		,"Income Growth"//18
        		,"EPS Growth"//19
        		,"EBITDA Growth"//20
        		,"Debt Growth"//21
        		,"Equity Growth"//22
        		,"Capitalization Growth"//23
        		,"Assets Growth"//24
        		,"Price ratio"//25
        		,"P/E"//26
        		,"Peg"//27
        		,"P/B"//28
        		,"P/S"//29
        		,"EV/EBITDA"//30
        		,"EV/EBIT"//31
        		,"EV/FCF"//32
        		,"Rev/FCF"//33
        		,"MC/CFO"//34
        		,"MC/NWC"//35
        		,"Profitabilities ratio"//36
        		,"Capex/Rev"//37
        		,"ROIC"//38
        		,"ROCE"//39
        		,"ROE"//40
        		,"ROA"//41
        		,"Margin"//42
        		,"Gross Margin"//43
        		,"Operating Margin"//44
        		,"Pretax Margin"//45
        		,"Profit margin"//46
        		,"Div Yield"//47
        		,"EBIT/Rev"//48
        		,"EBITDA/Rev"//49
        		,"Activity turnover"//50
        		,"Asset Turnover"//51
        		,"Receivable turnover"//52
        		,"Payable turnover"//53
        		,"Inventory turnover"//54
        		,"Interpretation of Solvency ratios"//55
        		,"Debt to Assets "//56
        		,"Debt to Equity "//57
        		,"LT debt/Capitalazion"//58
        		,"Interest coverage"//59
        		,"Liquidity Ratio"//60
        		,"Current ratio"//61
        		,"Quick ratio"//62
        		,"Cash ratio"//63
        		,"Risk ratio"//64
        		,"AR/Rev"//65
        		,"AR/Income"//66
        		,"A&P/Income"//67
        		,"Score"//68
        		,"F-Score"//69
        		,"C-score"//70
        		,"M-score"//71
        		,"Z-score"//72
        };
        public static final  String[] detailStockFinanceRatioLevel= new String[]{ "level1"//0
        		,"level3"//1
        		,"level3"//2
        		,"level3"//3
        		,"level3"//4
        		,"level3"//5
        		,"level3"//6
        		,"level3"//7
        		,"level3"//8
        		,"level3"//9
        		,"level3"//10
        		,"level3"//11
        		,"level3"//12
        		,"level3"//13
        		,"level3"//14
        		,"level3"//15
        		,"level1"//16
        		,"level3"//17
        		,"level3"//18
        		,"level3"//19
        		,"level3"//20
        		,"level3"//21
        		,"level3"//22
        		,"level3"//23
        		,"level3"//24
        		,"level1"//25
        		,"level3"//26
        		,"level3"//27
        		,"level3"//28
        		,"level3"//29
        		,"level3"//30
        		,"level3"//31
        		,"level3"//32
        		,"level3"//33
        		,"level3"//34
        		,"level3"//35
        		,"level1"//36
        		,"level3"//37
        		,"level3"//38
        		,"level3"//39
        		,"level3"//40
        		,"level3"//41
        		,"level1"//42
        		,"level3"//43
        		,"level3"//44
        		,"level3"//45
        		,"level3"//46
        		,"level3"//47
        		,"level3"//48
        		,"level3"//49
        		,"level1"//50
        		,"level3"//51
        		,"level3"//52
        		,"level3"//53
        		,"level3"//54
        		,"level1"//55
        		,"level3"//56
        		,"level3"//57
        		,"level3"//58
        		,"level3"//59
        		,"level1"//60
        		,"level3"//61
        		,"level3"//62
        		,"level3"//63
        		,"level1"//64
        		,"level3"//65
        		,"level3"//66
        		,"level3"//67
        		,"level1"//68
        		,"level3"//69
        		,"level3"//70
        		,"level3"//71
        		,"level3"//72
        };
}