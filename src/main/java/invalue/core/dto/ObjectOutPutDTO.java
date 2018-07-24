package invalue.core.dto;

import java.math.BigInteger;

public class ObjectOutPutDTO {
	private BigInteger id;
	private String code;
	private String name;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

