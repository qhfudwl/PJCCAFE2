package cafe.pj.jvx330.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 변환기
 * @author qhfud
 *
 */
@Component("convertor")
public class Convertor {
	// 날짜 형식 지정
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 문자열을 형식에 맞춘 Date 로 변환
	 * @param strDate
	 * @return
	 */
	public Date convertStringToFormattedDate(String strDate) {
		
		Date date = null;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	/**
	 * Date 를 형식에 맞춘 문자열로 반환
	 * @param date
	 * @return
	 */
	public String convertDateToFormattedString(Date date) {
		
		String strDate = format.format(date);
		
		return strDate;
	}
}
