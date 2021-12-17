package cafe.pj.jvx330.sales.controller;

import java.net.http.HttpRequest;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.sales.service.SalesService;
import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.user.service.UserServiceImpl;
import cafe.pj.jvx330.web.command.CustomerCommand;
import cafe.pj.jvx330.web.command.OrderCommand;
import cafe.pj.jvx330.web.command.OrderItemsCommand;
import cafe.pj.jvx330.web.controller.CafeController;
import cafe.pj.jvx330.web.util.OrderStorage;
/**
 * 
 * @author 윤효
 *
 */ 
@Controller
public class OrderController extends SalesController{
		@Resource(name="menuService")
		MenuService ms;
		
		@Resource(name="userService")
		UserService cs;
		
		@Resource(name="salesService")
		SalesService ss;
		
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
			session.setAttribute("contentName", "주문하기");
			
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
			
			ms.findNewMenus();
			List<OrderStorage> os = ss.findOrderRecordForMenu('M','T');
			Collections.sort(os);
			String thismonth = os.get(0).getWeekDate();
			for(OrderStorage data: os ) {
				
				if(data.getMenuName().equals(thismonth)) {
					System.out.println(data.getMenuName());
				}
			}
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
		Customer customer;
		//고객 정보 보관하기
		@PostMapping("saveUserPoint")
		@ResponseBody
		public HashMap<String,Object> saveUserPoint(@RequestBody HashMap<String,Object> map) {
			/*System.out.println("saveUserPoint");	
			customer.setId(Long.parseLong(map.get("userId").toString()));
			customer.setCustomerName(map.get("userName").toString());
			customer.setPhone(map.get("userPhone").toString());
			customer.setBirth(map.get("userBirth").toString());
			customer.setPoint(Double.parseDouble(map.get("userPoint").toString()));
			return;
			
			*/
			
			HashMap<String,Object> save = map;
			customer = (Customer)cs.findUserById(Long.parseLong(map.get("userId").toString()));
			
			
			
			
			return save;
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
		
		ModelAndView mav = new ModelAndView();
		
		
		@PostMapping("saveOrder")
		@ResponseBody
		public HashMap<String,Object> save_order(@ModelAttribute("order") OrderCommand order, HttpServletRequest request) {
			System.out.println(order.getNowOrder().get(0).getMenuId());
			
			
			//주문번호 구하기
			String orderNumber = returnOrderNumber();
			
			//매장or포장 구하기
			char place = order.getPlace();
			
			//주문한 메뉴 추가하기
			List<Product> menuItems = new ArrayList<Product>();
			for(OrderItemsCommand oic:order.getNowOrder()) {
				Menu menu = ms.findMenuById(oic.getMenuId());
				int quantity = oic.getQuantity();
				menuItems.add(new Product(orderNumber,menu,quantity,new Date()));
				
			}

			//사용한 포인트
			double usePoint = Double.parseDouble(order.getUsePoint());
			
			
			//총합산(amount) 구하기
			double amount=0;
			for(Product product : menuItems) {
				amount += product.getMenu().getMenuPrice()*product.getQuantity();
			}
			
			
			//받을 금액
			amount -= usePoint;
				
			//포인트 계산하기
			double savePoint = amount * 0.1;
			
			//고객 구하기
			User user;
			//회원일 경우 회원 정보 저장
			if(order.getCustomer().getId()!=1) {
				user = cs.findUserById(order.getCustomer().getId());
				Customer customer = (Customer)user;
				customer.setPoint(savePoint);
				user = customer;
				//cs.updatePointById(user);
			}
			//비회원일 경우 id값은 1
			else {
				user = new Customer();
				user.setId(order.getCustomer().getId());
			}
			 
			Sales sales = new Sales();
			sales.setUser(user);
			sales.setOrderNumber(orderNumber);
			sales.setPlace(place);
			sales.setAmount(amount);
			sales.setUsePoint(usePoint);
			sales.setOrder(menuItems);
			sales.setRegDate(new Date());
			
			//세션에 자료 보내기 
			HttpSession session = request.getSession();
			
			TreeMap<String,Sales> salesMap = (TreeMap<String, Sales>) session.getAttribute("sales");
			if(validator.isEmpty(salesMap)) {
				salesMap = new TreeMap<String, Sales>();
			}
			salesMap.put(orderNumber, sales);
			session.setAttribute("sales", salesMap);
			
			
			mav.addObject("sales", sales);
			/*
			System.out.println("sales id"+sales.getId());
			System.out.println("sales orderNumber"+sales.getOrderNumber());
			System.out.println("sales place"+sales.getPlace());
			System.out.println("sales amount"+sales.getAmount());
			System.out.println("sales usePoint"+sales.getUsePoint());
			System.out.println("sales regDate"+sales.getRegDate());
			System.out.println("sales user getid"+sales.getUser().getId());
			System.out.println("sales user getregdate"+sales.getUser().getRegDate());
			for(Product pro : sales.getOrder()) {
				System.out.println("sales order ordernumber"+pro.getOrderNumber());
				System.out.println("sales order quantity "+pro.getQuantity());
				System.out.println("sales order regDate"+pro.getRegDate());
				System.out.println("sales order menu id"+pro.getMenu().getId());
				System.out.println("sales order menu imgpath"+pro.getMenu().getImgPath());
				System.out.println("sales order menu menuname"+pro.getMenu().getMenuName());
				System.out.println("sales order menu menuprice"+pro.getMenu().getMenuPrice());
				System.out.println("sales order menu menutype"+pro.getMenu().getMenuType());
				System.out.println("sales order menu regdate"+pro.getMenu().getRegDate());


			}
			System.out.println("sales user totalprice function "+sales.getTotalPrice());

			*/
			
			

			HashMap<String,Object> fakeMap = new HashMap<String, Object>();
			return fakeMap;
			
			
		
		}
		
		@PostMapping("compOrder")
		public ModelAndView complete_order() {
			
			mav.setViewName("order/completeOrder");
			return mav;
		}

		/**
		 * 취소 누를 때
		 * 		1 - 좌측창에서 미리 메뉴를 눌렀을 경우
		 * 			1-1 선택한 메뉴 취소
		 * 		2 - 좌측창에서 미리 메뉴를 안눌렀을 경우
		 * 			2-1 아무 반응 없음
		 */
		
		
		@PostMapping("/order")
		public String cancelOrder(HttpServletRequest request, @RequestParam("leastOrderNumber") String leastorderNumber) {
			HttpSession session = request.getSession();
			TreeMap<String,Sales> map = (TreeMap<String, Sales>) session.getAttribute("sales");
			map.remove(leastorderNumber);
			return "redirect:/order";
		}
				
		
		/**
		 * 인기메뉴/신메뉴/모든메뉴 누를 때
		 * 		1 - 커피, 핫아이스 창은 선택된거 취소하게 만듬
		 * 		2 - 비동기방식으로 인기메뉴/신메뉴를 불러옴
		 * 
		 */
		
		
		
		
		
		
		
		
		
		
}
