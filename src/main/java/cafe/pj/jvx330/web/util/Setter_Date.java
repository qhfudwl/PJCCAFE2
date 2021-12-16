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
			// 오늘 날짜
		} else {
			cal.add(Calendar.DAY_OF_MONTH, -1);
			//)ex 월별 뽑을 떄 12월 1일을 받으면 11월 30일로
			//)ex 주별 뽑을 때 12월 7일을 받으면 12월 6일로 뒷값 세팅
			s2 = format.format(cal.getTime());
			//s2 뒷 값에 마지막 일 지정
		}
		
		if(dateType == 'M') { // -1 month
			//그달 의 날을 1일로 세팅 초기화 하기
			cal.set(Calendar.DAY_OF_MONTH, 1);
			
		} else if(dateType == 'W') { //-7days ( -1 week)
			cal.add(Calendar.DAY_OF_MONTH, -6);
			//현재 날짜에서 6일을 빼기
			if(cal.get(Calendar.YEAR) != now.get(Calendar.YEAR)) { // 2021년을 벗어나면 2021-01-01로 세팅
				//6을일 뺀 값의 년도가 작년의 년도와 다를경우
				cal.set(Calendar.YEAR, now.get(Calendar.YEAR));
				//올해의 년도를 세팅 해준다.
				cal.set(Calendar.MONTH, 0);
				//그 달의 월을 1월
				cal.set(Calendar.DAY_OF_MONTH, 1);
				//그 달의 1일로 셋팅
			}
			//올해 년도와 cal.get값이 같으면 그냥 계속 진행
		} else { 
			// -1day
		}
		
		s1 = format.format(cal.getTime());
		
		sDateList.add(s1);		
		sDateList.add(s2);	
		
		return sDateList;
	}
}
