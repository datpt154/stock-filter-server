
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
	public static final String average="Trung bình";
	public static final String endYear="Năm tài chính";
	public static final String report="Báo cáo";
	public static final String reportFinanceRatio="Chỉ số tài chính";
	public static final String unit="ĐV";
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
        public static final  String[] detailStockFinanceRatioUnit= new String[]{ ""//0
        		,"$b"//1
        		,"$b"//2
        		,"$b"//3
        		,"mil"//4
        		,"$k"//5
        		,"$k"//6
        		,"$k"//7
        		,"$b"//8
        		,"$b"//9
        		,"$b"//10
        		,"$b"//11
        		,"$b"//12
        		,"$b"//13
        		,"$b"//14
        		,"$b"//15
        		,"$b"//16
        		,""//17
        		,"%"//18
        		,"%"//19
        		,"%"//20
        		,"%"//21
        		,"%"//22
        		,"%"//23
        		,"%"//24
        		,"%"//25
        		,""//26
        		,"x"//27
        		,"x"//28
        		,"x"//29
        		,"x"//30
        		,"x"//31
        		,"x"//32
        		,"x"//33
        		,"x"//34
        		,"x"//35
        		,"x"//36
        		,""//37
        		,"%"//38
        		,"%"//39
        		,"%"//40
        		,"%"//41
        		,"%"//42
        		,""//43
        		,"%"//44
        		,"%"//45
        		,"%"//46
        		,"%"//47
        		,"%"//48
        		,"%"//49
        		,"%"//50
        		,""//51
        		,"x"//52
        		,"x"//53
        		,"x"//54
        		,"x"//55
        		,""//56
        		,"x"//57
        		,"x"//58
        		,"x"//59
        		,"x"//60
        		,""//61
        		,"x"//62
        		,"x"//63
        		,"x"//64
        		,""//65
        		,"x"//66
        		,"x"//67
        		,"x"//68
        		,""//69
        		,"p"//70
        		,"p"//71
        		,"p"//72
        };
        public static final  String[] vDetailStockNomalReport= new String[]{ "Bảng Cân Đối Kế Toán"//0
        		,"Tài sản Ngắn hạn"//1
        		,"Tiền và tương đương tiền"//2
        		,"ĐTTC ngắn hạn"//3
        		,"Phải thu ngắn hạn"//4
        		,"Hàng tồn kho"//5
        		,"TS ngắn hạn khác"//6
        		,"Tài sản dài hạn"//7
        		,"Phải thu dài hạn"//8
        		,"Tài sản cố định ròng"//9
        		,"Tài sản cố định hữu hình"//10
        		,"Tài sản cố định thuê tài chính"//11
        		,"Tài sản cố định vô hình"//12
        		,"Bất động sản đầu tư"//13
        		,"Tài sản dở dang"//14
        		,"ĐTTC dài hạn"//15
        		,"Lợi thế thương mại"//16
        		,"Tài sản dài hạn khác"//17
        		,"TỔNG TÀI SẢN"//18
        		,"Nợ ngắn hạn"//19
        		,"Phải trả người bán ngắn"//20
        		,"Người mua trả trước ngắn hạn"//21
        		,"Doanh thu chưa thực hiện"//22
        		,"Vay và nợ thuê tài chính ngắn hạn"//23
        		,"Nợ ngắn hạn khác"//24
        		,"Nợ dài hạn"//25
        		,"Phải trả nhà cung cấp dài hạn"//26
        		,"Người mua trả tiền trước dài hạn"//27
        		,"Doanh thu chưa thực hiện"//28
        		,"Vay và nợ thuê tài chính dài hạn"//29
        		,"Nợ dài hạn khác"//30
        		,"Vốn Chủ Sở hữu"//31
        		,"Vốn điều lệ"//32
        		,"Thặng dư vốn cổ phần"//33
        		,"Lợi nhuận chưa phân phối"//34
        		,"Vốn khác"//35
        		,"Lợi ích cổ đông thiểu số"//36
        		,"TỔNG NGUỒN VỐN"//37
        		,"Báo cáo kết quả kinh doanh"//38
        		,"Doanh thu thuần"//39
        		,"Giá vốn hàng bán"//40
        		,"Lãi gộp"//41
        		,"Thu nhập tài chính"//42
        		,"Chi phí tài chính"//43
        		,"trong đó: Chi phí lãi vay"//44
        		,"Lãi/lỗ liên doanh liên kết"//45
        		,"Chi phí bán hàng"//46
        		,"Chi phí quản lý Doanh nghiệp"//47
        		,"Lãi/lỗ từ HĐ Kinh doanh"//48
        		,"Thu nhập/(chi phí) khác"//49
        		,"Lãi/(lỗ) trước thuế"//50
        		,"Thuế TNDN"//51
        		,"Lãi/(lỗ) ròng"//52
        		,"Lợi ích CĐTS"//53
        		,"Lãi/(lỗ) thuần của CĐCT mẹ"//54
        		,"Báo cáo lưu chuyển tiền"//55
        		,"LCTT từ hoạt động kinh doanh"//56
        		,"LCTT từ hoạt động đầu tư"//57
        		,"LCTT từ hoạt động tài chính"//58
        		,"Lưu chuyển tiền tệ ròng"//59
        };
        public static final  String[] eDetailStockNomalReport= new String[]{ "Balance Sheet"//0
        		,"Current Asset"//1
        		,"Cash and Cash equivalents"//2
        		,"short-term Investments"//3
        		,"Accounts receivable - short-term"//4
        		,"Inventories"//5
        		,"other current assets"//6
        		,"Long-term assets"//7
        		,"Account receivable - Long-term"//8
        		,"Fixed assets"//9
        		,"Tangible fixed assets"//10
        		,"Finance tangible fixed assets"//11
        		,"intangible fixed assets"//12
        		,"Investment property"//13
        		,"Contruction in progress"//14
        		,"Long-term investment"//15
        		,"Good will"//16
        		,"Other long-term assets"//17
        		,"TOTAL ASSETS"//18
        		,"Current liabilities"//19
        		,"Account payable to suppliers"//20
        		,"Advances from customers"//21
        		,"Short-term Unearned revenue"//22
        		,"Short-term borrowings and liabilities "//23
        		,"Other short-term liabilities"//24
        		,"Long-term liabilities "//25
        		,"Long-term accounts payable  "//26
        		,"Advances from customers"//27
        		,"Long-term Unearned revenue"//28
        		,"Long-term borrowings and liabilities"//29
        		,"Other long-term liabilities"//30
        		,"EQUITY "//31
        		,"Share capital"//32
        		,"Share premium "//33
        		,"Retained profits "//34
        		,"other capitals"//35
        		,"Non-controlling interest"//36
        		,"TOTAL RESOURCES"//37
        		,"Incom statement"//38
        		,"Net revenue"//39
        		,"Cost of sales"//40
        		,"Gross profit"//41
        		,"Financial income "//42
        		,"Financial expenses"//43
        		,"In which: Interest expense "//44
        		,"Share of profit in associates"//45
        		,"Selling expenses"//46
        		,"General and administration expenses "//47
        		,"Net operating profit "//48
        		,"Other income "//49
        		,"Profit before tax"//50
        		,"Income tax expense "//51
        		,"Net profit after tax "//52
        		,"Minority interest"//53
        		,"Net Income"//54
        		,"Cash flows statement"//55
        		,"Net cash flows from operating activities "//56
        		,"Net cash flows from investing activities "//57
        		,"Net cash flows from financing activities "//58
        		,"Net cash flows"//59
        };
        public static final  String[] detailStockNomalReportLevel= new String[]{ "level1"//0
        		,"level2"//1
        		,"level3"//2
        		,"level3"//3
        		,"level3"//4
        		,"level3"//5
        		,"level3"//6
        		,"level2"//7
        		,"level3"//8
        		,"level3"//9
        		,"level4"//10
        		,"level4"//11
        		,"level4"//12
        		,"level3"//13
        		,"level3"//14
        		,"level3"//15
        		,"level3"//16
        		,"level3"//17
        		,"level2"//18
        		,"level2"//19
        		,"level3"//20
        		,"level3"//21
        		,"level3"//22
        		,"level3"//23
        		,"level3"//24
        		,"level2"//25
        		,"level3"//26
        		,"level3"//27
        		,"level3"//28
        		,"level3"//29
        		,"level3"//30
        		,"level2"//31
        		,"level3"//32
        		,"level3"//33
        		,"level3"//34
        		,"level3"//35
        		,"level3"//36
        		,"level2"//37
        		,"level1"//38
        		,"level3"//39
        		,"level3"//40
        		,"level3"//41
        		,"level3"//42
        		,"level3"//43
        		,"level4"//44
        		,"level3"//45
        		,"level3"//46
        		,"level3"//47
        		,"level3"//48
        		,"level3"//49
        		,"level3"//50
        		,"level3"//51
        		,"level3"//52
        		,"level3"//53
        		,"level3"//54
        		,"level1"//55
        		,"level3"//56
        		,"level3"//57
        		,"level3"//58
        		,"level3"//59

        };
        public static final  String[] detailStockNomalReportUnit= new String[]{ ""//0
        		,"$b"//1
        		,"$b"//2
        		,"$b"//3
        		,"$b"//4
        		,"$b"//5
        		,"$b"//6
        		,"$b"//7
        		,"$b"//8
        		,"$b"//9
        		,"$b"//10
        		,"$b"//11
        		,"$b"//12
        		,"$b"//13
        		,"$b"//14
        		,"$b"//15
        		,"$b"//16
        		,"$b"//17
        		,"$b"//18
        		,"$b"//19
        		,"$b"//20
        		,"$b"//21
        		,"$b"//22
        		,"$b"//23
        		,"$b"//24
        		,"$b"//25
        		,"$b"//26
        		,"$b"//27
        		,"$b"//28
        		,"$b"//29
        		,"$b"//30
        		,"$b"//31
        		,"$b"//32
        		,"$b"//33
        		,"$b"//34
        		,"$b"//35
        		,"$b"//36
        		,"$b"//37
        		,""//38
        		,"$b"//39
        		,"$b"//40
        		,"$b"//41
        		,"$b"//42
        		,"$b"//43
        		,"$b"//44
        		,"$b"//45
        		,"$b"//46
        		,"$b"//47
        		,"$b"//48
        		,"$b"//49
        		,"$b"//50
        		,"$b"//51
        		,"$b"//52
        		,"$b"//53
        		,"$b"//54
        		,"$b"//55
        		,"$b"//56
        		,"$b"//57
        		,"$b"//58
        		,"$b"//59
        };
}