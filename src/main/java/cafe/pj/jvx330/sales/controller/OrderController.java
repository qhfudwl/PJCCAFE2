package cafe.pj.jvx330.sales.controller;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.Employee;
import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.user.service.UserServiceImpl;
import cafe.pj.jvx330.web.command.OrderCommand;

@Controller
public class OrderController {
		@Resource(name="menuService")
		MenuService ms;
		
		
	
		//처음예제
		/*
		RequestBody
		Json 형태로 받은 HTTP Body 데이터를 MessageConverter를 통해 변환시킴
		값을 주입하지 않고 변환을 시키므로(엄밀히는 Reflection을 사용하여 할당), 변수들의 생성자나 Setter함수가 없어도 정상적으로 값이 할당됨

		*/
		
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
		
		
		List<Product> order = new ArrayList<>();
	
		
		
		
		/**
		 *  주문 화면 불러오기 
		 *		- 직원 직책
		 *		- 오늘 날짜
		 *		- 모든 메뉴
		 */
		
	
		@GetMapping("/order")
		public ModelAndView orderForm(HttpServletRequest request) {
			//직원 정보 불러오기
			//HttpSession session = request.getSession();
			//Employee employee= (Employee)session.getAttribute("manager");
			//String Mposition = employee.getPosition();
			HttpSession session = request.getSession();
			session.setAttribute("contentName", "주문목록");
			
			//직원 정보 임시
			String mPosition = "Manager";
	
			//오늘 날짜 불러오기
			LocalDate nowDate = LocalDate.now();
			
			//모든메뉴 불러오기
			List<Menu> BeverageMenus = ms.findAllMenusByMenuType('B');
			List<Menu> CoffeeMenus = ms.findAllMenusByMenuType('C');
			List<Menu> FoodMenus = ms.findAllMenusByMenuType('F');
			
			//직원정보, 오늘날짜, 모든메뉴 셋팅
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("mPosition",mPosition);
			mav.addObject("nowDate",nowDate);
			mav.addObject("BeverageMenus",BeverageMenus);
			mav.addObject("CoffeeMenus",CoffeeMenus);
			mav.addObject("FoodMenus",FoodMenus);
			mav.setViewName("order/order");
			
			return mav;
		}
		
		
		/**
		 * 메뉴 누를 때
		 *		1 - 이미 받아온 결과 값을 보여준다.
		 * 	(script에서 처리)	
		 */
		
		
		/**
		 * 메뉴 사진 누르면
		 * 		1 - 좌측에 상품명 / 수량 / 단가 / 금액		업데이트
		 * 		2 - 좌측에 총 수량 / 총 금액 				업데이트
		 * 		3 - 좌측에 실 판매금액 / 받은 금액 			업데이트
		 * 
		 *
		 */
		
		/* 1. 좌측에 상품명 / 수량 / 단가 / 금액 업데이트*/	
		
		@PostMapping("/orderMenuList")
		@ResponseBody
		public HashMap<String,Object> orderMenuList(@RequestBody HashMap<String,Object> menuList){
		    HashMap<String,Object> addMenuList = new HashMap<String, Object>();
			
		    
			//메뉴 아이디받고
			long id = Long.parseLong(menuList.get("id").toString());
			//메뉴 이름 받고
			String menuName = menuList.get("menuName").toString();
			//메뉴 가격 받고
			double menuPrice = Double.parseDouble(menuList.get("menuPrice").toString());
			//수량 받고
			int quantity = Integer.parseInt(menuList.get("quantity").toString());
			//수량 더하기 인지 빼기 인지 판별
			String checkQuantity = menuList.get("checkQuantity").toString();
			
			//order에 있는지 검사하고 있으면 추가 x, 없으면 추가 하고 1개값 리턴
			boolean duple=false;
			
			for(Product product : order) {
				if(id==product.getMenu().getId()) {
					duple=true;
					if(checkQuantity.equals("up")) {
						product.setQuantity(product.getQuantity()+1);
					}
					else {
						if(product.getQuantity()>1) {
							product.setQuantity(product.getQuantity()-1);
						}
						
					}
					
					break;
				}
			}
			
			//장바구니에 담기
			if(!duple) {
				Menu menu = new Menu();
				menu.setId(id);
				menu.setMenuName(menuName);
				menu.setMenuPrice(menuPrice);
				order.add(new Product(menu,quantity));
			}		
			
			//화면에 뿌려주기
			/*
			addMenuList.put("id", id);
			addMenuList.put("menuName", menuName);
			addMenuList.put("menuPrice", menuPrice);
			addMenuList.put("quantity", quantity);
		    */
			
			addMenuList.put("order",order);
		    return addMenuList;
			
			
		}
		
		
		
		
		@Autowired
		private UserService us ;
		
		
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
		
		//고객 찾기 팝업창
		@GetMapping("findUserForPointForm")
		public String findUserForPointForm() {
			return "order/findUserForPoint";
		}
		
		//폰 번호 받아오기 		
		@PostMapping("findUserForPoint")
		public ModelAndView findUserForPoint(@RequestParam String popUpPhoneValue) {
			List<User> user = us.findUsersByPhone(popUpPhoneValue);
			List<Customer> cust = new ArrayList<Customer>();
			for(User user1 :user) {
				cust.add((Customer)user1);
			}
			ModelAndView mav = new ModelAndView();
			mav.addObject("cust",cust);
			mav.setViewName("order/findUserResultForPoint");
			
			return mav;
		}
		Customer customer = new Customer();
		//고객 정보 보관하기
		@PostMapping("saveUserPoint")
		@ResponseBody
		public void saveUserPoint(@RequestBody HashMap<String,Object> map) {
			System.out.println("saveUserPoint");	
			customer.setId(Long.parseLong(map.get("userId").toString()));
			customer.setCustomerName(map.get("userName").toString());
			customer.setPhone(map.get("userPhone").toString());
			customer.setBirth(map.get("userBirth").toString());
			customer.setPoint(Double.parseDouble(map.get("userPoint").toString()));
			return;
		}
		
		//회원가입 팝업창
		@PostMapping("joinUserForPoint")
		public String joinUserForPointForm() {
			
			return "order/joinUserForPoint";
		}
			
		
		//회원가입 후 결과창
		@PostMapping("joinUserResultForPoint")
		@ResponseBody
		public void joinUserResultForPointForm(@RequestBody HashMap<String,Object> map) {
			String name = map.get("userName").toString();
			String phone = map.get("userPhone").toString();;
			String birth = map.get("userBirth").toString();;
			User user = new Customer(name,phone,birth,0);
			System.out.println(name+phone+birth);
			us.addUser(user);
			return;
		}
		
		
		
		
		
		
		/**
		 * 포인트 누르면
		 * 		1 - 주문최소금액 5,000원 이상이면 포인트 사용 팝업창
		 * 			1-1 사용가능 포인트 / 사용할 포인트 입력창 ( 안내사항 포인트 최소 5,000원 부터, 100원 단위 1000원 이상 사용가능 )
		 * 			1-2 포인트 사용하면
		 * 				1-2-1 포인트 금액 / 받을 금액 			업데이트 
		 *			1-3 포인트 사용취소
		 * 		2 - 주문최스금액 5,000원 이하면 경고창
		 */
		
		//포인트 사용 팝업창 띄우기
		@GetMapping("usePointPopup")
		public ModelAndView usePointPopupForm() {
			ModelAndView mav = new ModelAndView();
			mav.addObject("userPoint", customer.getPoint());
			mav.setViewName("order/usePoint");
			return mav;
		}
		
		
		//포인트 사용하기
		@PostMapping("usePoint")
		public void usePointPopup() {
			
			
		}
		
		
		
		
		
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
		
		
		
		@PostMapping("compOrder")
		public ModelAndView complete_order(@ModelAttribute OrderCommand order) {
			
			
			
			
			ModelAndView mav = new ModelAndView();
			return mav;
		}
		
		
		
		
		
		
		
		
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
