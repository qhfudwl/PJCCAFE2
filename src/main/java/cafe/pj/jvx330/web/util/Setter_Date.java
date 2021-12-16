package cafe.pj.jvx330.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("setterDate")
public class Setter_Date {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	public List<String> chooseDate(Calendar cal, int cnt, char dateType){
		List<String> sDateList = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		
		String s1;
		String s2 = null;
		if(cnt == 0) {
			s2 = format.format(cal.getTime());
		} else {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			s2 = format.format(cal.getTime());
		}
		
		if(dateType == 'M') { // -1 month
			cal.set(Calendar.DAY_OF_MONTH, 1);
			
		} else if(dateType == 'W') { //-7days ( -1 week)
			cal.add(Calendar.DAY_OF_MONTH, -6);
			if(cal.get(Calendar.YEAR) != now.get(Calendar.YEAR)) { // 2021년을 벗어나면 2021-01-01로 세팅
				cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
				cal.set(Calendar.MONTH, 0);
				cal.set(Calendar.DAY_OF_MONTH, 1);
			}
		} else { 
			// -1day
		}
		
		s1 = format.format(cal.getTime());
		
		sDateList.add(s1);		
		sDateList.add(s2);	
		
		return sDateList;
	}
}
