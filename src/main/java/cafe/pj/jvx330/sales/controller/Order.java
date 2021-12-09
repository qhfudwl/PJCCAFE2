package cafe.pj.jvx330.sales.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Order {
	
		//처음예제
		/*
		@RequestMapping(value="/sayHello",method=RequestMethod.GET)
		public ModelAndView sayHello() {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("order/order");
			return mav;
		}
		
		
		@RequestMapping(value="/sayHello",method=RequestMethod.POST)
		@ResponseBody
		public HashMap<String,Object> requestBody(@RequestBody HashMap<String,Object> map){
			HashMap<String,Object> map2 = new HashMap<String, Object>();
			System.out.println("hi");
			System.out.println(map.get("id"));
			//jsp에 값 전달하기
			map2.put("name", "yoon");
			return map2;
		}
		*/
	
	
		/*
		@RequestMapping(value="/sayHello",method=RequestMethod.POST)
		public String requestBody(@RequestParam("test") String test){
			
			System.out.println(test);
			return "order/orderList";
		}
		*/
		
		
	
	
	
		
		
		/**
		 * Get - 뿌려주는 화면
		 *		- 직원 직책
		 *		- 오늘 날짜
		 *		- 모든 메뉴
		 *		
		 * 
		 */
		
		
		
		
		
		
		
		
		
		/**
		 * 메뉴 사진 누르면
		 * 		1 - 좌측에 상품명 / 수량 / 단가 / 금액		업데이트
		 * 		2 - 좌측에 총 수량 / 총 금액 				업데이트
		 * 		3 - 좌측에 실 판매금액 / 받은 금액 			업데이트
		 * 
		 *
		 */
		
		
		/**
		 * 고객선택 누르면
		 * 		1 - 폰번호입력창
		 * 			1-1 회원가입하기
		 * 			1-2 포인트 적립하기
		 * 		2 - 고객이면
		 * 			2-1 좌측에 고객명 / 전화번호 / 포인트 / 생년월일 	업데이트
		 * 		
		 * 
		 */
		
		/**
		 * 포인트 누르면
		 * 		1 - 주문최소금액 5,000원 이상이면 포인트 사용 팝업창
		 * 			1-1 사용가능 포인트 / 사용할 포인트 입력창 ( 안내사항 포인트 최소 5,000원 부터, 100원 단위 1000원 이상 사용가능 )
		 * 			1-2 포인트 사용하면
		 * 				1-2-1 포인트 금액 / 받을 금액 			업데이트 
		 *			1-3 포인트 사용취소
		 * 		2 - 주문최스금액 5,000원 이하면 경고창
		 */
		
		/**
		 * 전체취소 누르면
		 *		1 - 좌측 화면에 모든 값 초기화
		 */
		
		/**
		 * 주문하기 누르면
		 * 		1 - 현금 결제 시 ( 포인트 10% )
		 *			1-1 현금 입력창 
		 * 		2 - 카드 결제 시 ( 포인트 5% ) 
		 * 			2-1 카드 결제 금액 자동 입력
		 */
		
		/**
		 * 메뉴 누를 때
		 *		1 - 이미 받아온 결과 값을 보여준다.
		 * 		
		 */
		
		
		/**
		 * 취소 누를 때
		 * 		1 - 좌측창에서 미리 메뉴를 눌렀을 경우
		 * 			1-1 선택한 메뉴 취소
		 * 		2 - 좌측창에서 미리 메뉴를 안눌렀을 경우
		 * 			2-1 아무 반응 없음
		 */
		
		
		/**
		 * 인기메뉴/신메뉴/모든메뉴 누를 때
		 * 		1 - 커피, 핫아이스 창은 선택된거 취소하게 만듬
		 * 		2 - 비동기방식으로 인기메뉴/신메뉴를 불러옴
		 * 
		 */
		
		
		
		
		
		
		
		
		
		
}
