package invalue.core.dto;

public class OutPutScreenBreakOut {
	private String name;
	private String code;
	private String exchange;
	private Double price;
	private Double breakPrice ;
	private Double adx14;
	private Double rsi14;
	private Double macd;
	private Double volume;
	private String breakTime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getBreakPrice() {
		return breakPrice;
	}
	public void setBreakPrice(Double breakPrice) {
		this.breakPrice = breakPrice;
	}
	public Double getAdx14() {
		return adx14;
	}
	public void setAdx14(Double adx14) {
		this.adx14 = adx14;
	}
	public Double getRsi14() {
		return rsi14;
	}
	public void setRsi14(Double rsi14) {
		this.rsi14 = rsi14;
	}
	public Double getMacd() {
		return macd;
	}
	public void setMacd(Double macd) {
		this.macd = macd;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public String getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}
	
}

