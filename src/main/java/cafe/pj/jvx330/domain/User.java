package cafe.pj.jvx330.domain;

import java.util.Date;

public abstract class User {
	protected long id;
	protected char userType;
	protected Date regDate;
	
	public User() {
		
	}
	
	public User(char userType) {
		this.userType = userType; 
	}
	
	public User(long id, char userType, Date regDate) {
		this.id = id;
		this.userType = userType;
		this.regDate = regDate;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public char getUserType() {
		return userType;
	}
	public void setUserType(char userType) {
		this.userType = userType;
	}
	
}
