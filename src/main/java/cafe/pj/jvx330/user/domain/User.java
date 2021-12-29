package cafe.pj.jvx330.user.domain;

import java.util.Date;

// abstract 는 생성자 자체를 new User로 만들 수 없음. 
public abstract class User {
	protected long id;
	protected Date regDate;
	
	public User() {
		
	}
	
	public User(long id, Date regDate) {
		this.id = id;
		this.regDate = regDate;
	}
	
	public User(long id) {
		this.id = id;
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
