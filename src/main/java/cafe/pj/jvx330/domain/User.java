package cafe.pj.jvx330.domain;

import java.util.Date;

public abstract class User {
	protected long id;
	protected Date regDate;
	
	public User() {
		
	}
	
	
	public User(long id, Date regDate) {
		this.id = id;
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
	
}
