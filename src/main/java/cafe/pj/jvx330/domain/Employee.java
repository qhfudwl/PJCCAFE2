package cafe.pj.jvx330.domain;

import java.util.Date;

public class Employee extends User { 
	private String eid;
	private String passwd;
	private String position;
	
	public Employee() {
		
	}
	
	public Employee(String eid, String passwd, String position) {

	}        
	public Employee(char userType, String eid, String passwd, String position) {
		this.eid = eid;
		this.passwd = passwd;
		this.position = position;
	}
	
	public Employee(long id, String eid, String passwd, String position, Date regDate) {
		super(id, regDate);
		this.eid = eid;
		this.passwd = passwd;
		this.position = position;
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
