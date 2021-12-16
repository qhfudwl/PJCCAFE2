package cafe.pj.jvx330.web.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.web.util.Validator;

public abstract class CafeController {
	
	public static String order_number = "";
	
	@Resource(name="validator")
	protected Validator validator;
	
	/**
	 * 주문 번호 반환
	 * @return
	 * @author 김보령
	 */
	private String returnOrderNumber() {
		
		// 만일 서버 내 첫 주문이라면 a-001 반환
		if (validator.isEmpty(order_number)) {
			order_number = "A-001";
			return order_number;
		}

		// 모든 문자열을 분리해서 배열에 저장
		String[] arr = order_number.trim().split("");
		
		// 첫번째 글자는 알파벳 (ascii > 65 - 90)
		char alphabet = arr[0].charAt(0);
		int hundred = Integer.parseInt(arr[2]);
		int ten = Integer.parseInt(arr[3]);
		int one = Integer.parseInt(arr[4]);
		
		if (one < 9) {
			one++;
		} else {
			if (hundred == 9 && ten == 9) { // 현재 999면 다음 숫자는 001이다.
				one = 1;
			} else {
				one = 0;
			}
			if (ten < 9) {
				ten++;
			} else {
				ten = 0;
				if (hundred < 9) {
					hundred++;
				} else {
					hundred = 0;
					if (alphabet < 90) {
						alphabet++;
					} else {
						alphabet = 65;
					}
				}
			}
		}
		alphabet = (char)alphabet;
		String strA = String.valueOf(alphabet);
		String strH = String.valueOf(hundred);
		String strT = String.valueOf(ten);
		String strO = String.valueOf(one);
		
		order_number = strA + "-" + strH + strT + strO;
		
		return order_number;
	}
	
	public Date getNowDate() {
		return java.sql.Date.valueOf(LocalDate.now());
	}
	
}
