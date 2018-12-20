package invalue.core.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class LoginOutputsDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String fullName;
	private String phone;
	private Integer type;
	private Integer roleId;
	private String token;
	private Integer numPTKT;
	private Integer numFilter;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getNumPTKT() {
		return numPTKT;
	}
	public void setNumPTKT(Integer numPTKT) {
		this.numPTKT = numPTKT;
	}
	public Integer getNumFilter() {
		return numFilter;
	}
	public void setNumFilter(Integer numFilter) {
		this.numFilter = numFilter;
	}
	
}