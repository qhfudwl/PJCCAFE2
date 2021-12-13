package cafe.pj.jvx330.web.command;

import java.util.Date;

public class CustomerCommand {
	private long id;
	private String customerName;
	private String phone;
	private String birth;
	private double point;
	private Date regDate;
	
	//파람,, 리퀘스트파라메터 
	//컨트롤러 커맨드 배치하기,
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	} 
}
