package invalue.core.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name","email"}) })
public class User implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "user_seq", allocationSize=1)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Basic
	@Column(name = "user_name")
	private String userName;
	
	@Basic
	@Column(name = "email")
	private String email;
	
	@Basic
	@Column(name = "full_name")
	private String fullName;
	
	@Basic
	@Column(name = "phone")
	private String phone;
	
	@Basic
	@Column(name = "address")
	private String address;
	
	@Basic
	@Column(name = "sex")
	private String sex;
	
	@Basic
	@Column(name = "TYPE", nullable = false)
	private Integer type;
	
	@Basic
	@Column(name = "STATUS", nullable = false)
	private Integer status;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME", nullable = false, length = 19)
	private Date createdTime;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_TIME", length = 19, insertable = false, updatable = true)
	private Date updatedTime;
	
	@Basic
	@Column(name = "ROLE_ID", nullable = false)
	private Integer roleId;
	
	@Basic
	@Column(name = "salt")
	private String salt;
	
	@Basic
	@Column(name = "password")
	private String password;
	
	@Basic
	@Column(name = "token")
	private String token;
	
	@Basic
	@Column(name = "id_provider")
	private String idProvider;
	
	@Basic
	@Column(name = "provider")
	private String provider;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(String idProvider) {
		this.idProvider = idProvider;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
}