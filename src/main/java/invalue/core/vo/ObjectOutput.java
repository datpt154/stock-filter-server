package invalue.core.vo;

public class ObjectOutput {


	private Integer code=200;//200:thanh cong, 500: loi he thong
	
	private Integer status=1;//1 thanh cong, -1: loi input

	private String message;// thong bao tra ve khi co loi

	private Object data;// gia tri tra ve khi thanh cong

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
