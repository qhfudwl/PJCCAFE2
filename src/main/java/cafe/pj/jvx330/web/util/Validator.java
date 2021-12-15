package cafe.pj.jvx330.web.util;

import org.springframework.stereotype.Component;

@Component("validator")
public class Validator {
	
	public boolean isEmpty(Object obj) {
		if(obj == null || obj.equals("")) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(double d) {
		if(d == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(long l) {
		if(l == 0) {
			return true;
		}
		return false;
	}
	
}
