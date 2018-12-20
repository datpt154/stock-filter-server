package invalue.core.dto;

import java.math.BigInteger;

public class OutPutLoginDTO {
	private Long id;
	private String userName;
	private Integer numFilterBCTC;
	private Integer numFilterPTKT;
	private Integer role;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getNumFilterBCTC() {
		return numFilterBCTC;
	}
	public void setNumFilterBCTC(Integer numFilterBCTC) {
		this.numFilterBCTC = numFilterBCTC;
	}
	public Integer getNumFilterPTKT() {
		return numFilterPTKT;
	}
	public void setNumFilterPTKT(Integer numFilterPTKT) {
		this.numFilterPTKT = numFilterPTKT;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	

	
}

