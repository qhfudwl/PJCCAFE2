package cafe.pj.jvx330.user.domain;

import java.util.Date;


public class Customer extends User {
	private String customerName;
	private String phone;
	private String birth;
	private double point;
	
	public Customer() {
		super();
	}
	
	/**
	 * 유저 정보 변경
	 * @param customerName
	 * @param phone
	 * @param birth
	 * @param point
	 */
	public Customer(long id ,String customerName, String phone, String birth, double point) {
		super(id);
		this.customerName = customerName;
		this.phone = phone;
		this.birth = birth;
		this.point = point;
}
	
	
	public Customer(String customerName, String phone, String birth, double point) {
		this.customerName = customerName;
		this.phone = phone;
		this.birth = birth;
		this.point = point;
	}
	
	/**
	 * 유저 정보 가져오기
	 * @param id
	 * @param customerName
	 * @param phone
	 * @param birth
	 * @param point
	 * @param regDate
	 */
	public Customer(long id,  String customerName, String phone, String birth, double point, Date regDate) {
		super(id, regDate);
		this.customerName = customerName;
		this.phone = phone;
		this.birth = birth;
		this.point = point;
	}
	
	/**
	 * 유저 회원 가입
	 * @param customerName
	 * @param phone
	 * @param birth
	 */
	public Customer(String customerName, String phone, String birth) {
		this.customerName = customerName;
		this.phone = phone;
		this.birth = birth;
	}
	
	/**
	 * 추가
	 * @param point
	 * @return
	 */
	public double usePoint(double point) {
		
		return 0;
	}
	
	/**
	 * 추가
	 * @param point
	 * @return
	 */
	public double savePoint(double point) {
		
		return 0;
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

	@Override
	public String toString() {
		return "id=" + super.id + ",customerName=" + customerName + ",phone=" + phone + ",birth=" + birth + ",point=" + point
				+ ",regDate=" + super.regDate;
	} 
	
	
}
