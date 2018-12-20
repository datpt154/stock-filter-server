package invalue.core.dto;

public class RegisterInputsDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;

	private String name;

	private String provider;
	
	private String password;
	
	private String idProvider;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIdProvider() {
		return idProvider;
	}

	public void setIdProvider(String idProvider) {
		this.idProvider = idProvider;
	}
	
}