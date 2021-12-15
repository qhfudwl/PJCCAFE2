package cafe.pj.jvx330.web.command;

public class EmployeeCommand { 
	private String eid;
	private String passwd;
	private String position;
	
	public EmployeeCommand() {
		
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
