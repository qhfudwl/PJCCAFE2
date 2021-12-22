package cafe.pj.jvx330.sales.controller;

import java.net.http.HttpRequest;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.http.ResponseEntity;
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
import cafe.pj.jvx330.web.util.OrderStorage;

/**
 * 
 * @author 윤효
 *
 */
@Controller
public class OrderController extends SalesController {
	@Resource(name = "menuService")
	MenuService ms;

	@Resource(name = "salesService")
	SalesService ss;

	@Resource(name = "userService")
	private UserService us;

	List<Product> order = new ArrayList<>();

	/**
	 * 주문 화면 불러오기 - 직원 직책 - 오늘 날짜 - 모든 메뉴
	 */

	@GetMapping("/order")
	public ModelAndView orderForm(HttpServletRequest request) {
		// 직원 정보 불러오기
		// HttpSession session = request.getSession();
		// Employee employee= (Employee)session.getAttribute("manager");
		// String Mposition = employee.getPosition();
		HttpSession session = request.getSession();
		session.setAttribute("contentName", "주문하기");

		// 직원 정보 임시
		String mPosition = "Manager";

		// 오늘 날짜 불러오기
		LocalDate nowDate = LocalDate.now();

		// 모든메뉴 불러오기
		List<Menu> BeverageMenus = ms.findAllMenusByMenuType('B');
		List<Menu> CoffeeMenus = ms.findAllMenusByMenuType('C');
		List<Menu> FoodMenus = ms.findAllMenusByMenuType('F');

		// 직원정보, 오늘날짜, 모든메뉴 셋팅
		ModelAndView mav = new ModelAndView();
		mav.addObject("mPosition", mPosition);
		mav.addObject("nowDate", nowDate);
		mav.addObject("BeverageMenus", BeverageMenus);
		mav.addObject("CoffeeMenus", CoffeeMenus);
		mav.addObject("FoodMenus", FoodMenus);
		mav.setViewName("order/order");

		return mav;
	}

	/* 1. 좌측에 상품명 / 수량 / 단가 / 금액 업데이트 */

	@PostMapping("/orderMenuList")
	@ResponseBody
	public HashMap<String, Object> orderMenuList(@RequestBody HashMap<String, Object> menuList) {
		HashMap<String, Object> addMenuList = new HashMap<String, Object>();

		// 메뉴 아이디받고
		long id = Long.parseLong(menuList.get("id").toString());
		// 메뉴 이름 받고
		String menuName = menuList.get("menuName").toString();
		// 메뉴 가격 받고
		double menuPrice = Double.parseDouble(menuList.get("menuPrice").toString());
		// 수량 받고
		int quantity = Integer.parseInt(menuList.get("quantity").toString());
		// 수량 더하기 인지 빼기 인지 판별
		String checkQuantity = menuList.get("checkQuantity").toString();

		// order에 있는지 검사하고 있으면 추가 x, 없으면 추가 하고 1개값 리턴
		boolean duple = false;

		for (Product product : order) {
			if (id == product.getMenu().getId()) {
				duple = true;
				if (checkQuantity.equals("up")) {
					product.setQuantity(product.getQuantity() + 1);
				} else {
					if (product.getQuantity() > 1) {
						product.setQuantity(product.getQuantity() - 1);
					}

				}

				break;
			}
		}

		// 장바구니에 담기
		if (!duple) {
			Menu menu = new Menu();
			menu.setId(id);
			menu.setMenuName(menuName);
			menu.setMenuPrice(menuPrice);
			order.add(new Product(menu, quantity));
		}

		addMenuList.put("order", order);
		return addMenuList;

	}

	/**
	 * 고객선택 누르면 1 - 폰번호입력창 1-1 회원가입하기 1-2 포인트 적립하기 2 - 고객이면 2-1 좌측에 고객명 / 전화번호 / 포인트
	 * / 생년월일 업데이트
	 * 
	 * 
	 */

	// 고객 찾기 팝업창
	@GetMapping("findUserForPointForm")
	public String findUserForPointForm() {
		return "order/findUserForPoint";
	}

	// 폰 번호 받아오기
	@PostMapping("findUserForPoint")
	public ModelAndView findUserForPoint(@RequestParam String popUpPhoneValue) {
		List<User> user = us.findUsersByPhone(popUpPhoneValue);
		List<Customer> cust = new ArrayList<Customer>();
		for (User user1 : user) {
			cust.add((Customer) user1);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("cust", cust);
		mav.setViewName("order/findUserResultForPoint");

		return mav;
	}

	Customer customerForPoint;

	// 고객 선택 시 포인트 사용하기 위해서 고객 정보 미리 보관하기
	@PostMapping("saveUserPoint")
	@ResponseBody
	public HashMap<String, Object> saveUserPoint(@RequestBody HashMap<String, Object> map) {

		HashMap<String, Object> save = map;
		customerForPoint = (Customer) us.findUserById(Long.parseLong(map.get("userId").toString()));

		return save;
	}

	/**
	 * 회원가입하기
	 * 
	 */

	// 회원가입 팝업창
	@PostMapping("joinUserForPoint")
	public String joinUserForPointForm() {

		return "order/joinUserForPoint";
	}

	// 회원가입 후 결과창
	@PostMapping("joinUserResultForPoint")
	@ResponseBody
	public void joinUserResultForPointForm(@RequestBody HashMap<String, Object> map) {
		String name = map.get("userName").toString();
		String phone = map.get("userPhone").toString();
		;
		String birth = map.get("userBirth").toString();
		;
		User user = new Customer(name, phone, birth, 0);
		us.addUser(user);
		return;
	}

	/**
	 * 포인트 누르면 1 - 주문최소금액 5,000원 이상이면 포인트 사용 팝업창 1-1 사용가능 포인트 / 사용할 포인트 입력창 ( 안내사항
	 * 포인트 최소 5,000원 부터, 100원 단위 1000원 이상 사용가능 ) 1-2 포인트 사용하면 1-2-1 포인트 금액 / 받을 금액
	 * 업데이트 1-3 포인트 사용취소 2 - 주문최스금액 5,000원 이하면 경고창
	 */

	// 포인트 사용 팝업창 띄우기
	@GetMapping("usePointPopup")
	public ModelAndView usePointPopupForm() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userPoint", customerForPoint.getPoint());
		mav.setViewName("order/usePoint");
		return mav;
	}

	
	
	/**
	 * 주문하기 버튼 누르면 주문 정보 다 받아오기
	 * 		- 모든 정보를 받아온다.	
	 * 		- 정보를 session에 저장한다 ( 주문현황에서 쓰일 수 있도록 ) 
	 * 
	 */
	
	//주문하기 버튼과 , 주문 하기가 끝난후 영수증 페이지로 이동할 때 값을 저장한다.
	ModelAndView mavForOrder = new ModelAndView();

	@PostMapping("saveOrder")
	@ResponseBody
	public HashMap<String, Object> save_order(@ModelAttribute("order") OrderCommand order, HttpServletRequest request) {

		// 주문번호 구하기
		String orderNumber = returnOrderNumber();

		// 매장or포장 구하기
		char place = order.getPlace();

		// 주문한 메뉴 추가하기
		List<Product> menuItems = new ArrayList<Product>();
		for (OrderItemsCommand oic : order.getNowOrder()) {
			Menu menu = ms.findMenuById(oic.getMenuId());
			int quantity = oic.getQuantity();
			menuItems.add(new Product(orderNumber, menu, quantity, new Date()));

		}

		// 사용한 포인트
		double usePoint = Double.parseDouble(order.getUsePoint());

		// 총합산(amount) 구하기
		double amount = 0;
		for (Product product : menuItems) {
			amount += product.getMenu().getMenuPrice() * product.getQuantity();
		}

		// 받을 금액
		amount -= usePoint;

		// 포인트 계산하기
		double savePoint = amount * 0.1;

		// 고객 구하기
		User user;
		// 회원일 경우 회원 정보 저장
		if (order.getCustomer().getId() != 1) {
			user = us.findUserById(order.getCustomer().getId());
			Customer customer = (Customer) user;
			customer.setPoint(customer.getPoint() + savePoint - usePoint);
			user = customer;
			us.updatePointById(user);
		}
		// 비회원일 경우 id값은 1
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

		// 세션에 자료 보내기
		HttpSession session = request.getSession();

		TreeMap<String, Sales> salesMap = (TreeMap<String, Sales>) session.getAttribute("sales");
		if (validator.isEmpty(salesMap)) {
			salesMap = new TreeMap<String, Sales>();
		}
		salesMap.put(orderNumber, sales);
		session.setAttribute("sales", salesMap);
		
		//영수증 화면에 보낼 자료 넣기
		mavForOrder.addObject("sales", sales);

		HashMap<String, Object> fakeMap = new HashMap<String, Object>();
		return fakeMap;

	}
	/**
	 * 영수증 화면으로 보내기
	 * 
	 */
	@GetMapping("/compOrder")
	public ModelAndView complete_order() {

		mavForOrder.setViewName("order/completeOrder");
		return mavForOrder;
	}

	/**
	 * 최종주문확인(영수증) 창에서 취소 누를 때 
	 * 
	 */

	@PostMapping("/order")
	public String cancelOrder(HttpServletRequest request, @RequestParam("leastOrderNumber") String leastorderNumber) {
		HttpSession session = request.getSession();
		TreeMap<String, Sales> map = (TreeMap<String, Sales>) session.getAttribute("sales");
		map.remove(leastorderNumber);
		return "redirect:/order";
	}

	/*
	 * 
	 * 인기메뉴 불러오기 (바로 지난달 판매내역을 불러올 수 있도록 리팩토링 필요)
	 */
	@PostMapping("/bestmenu")
	@ResponseBody
	public ResponseEntity<?> getBestMenu() {
		List<Menu> bestMenu = new ArrayList<Menu>();

		// 월별 판매내역 (주문내역테이블)
		List<OrderStorage> os = ss.findOrderRecordForMenu('M', 'T');

		// 해당달 판매내역
		List<OrderStorage> lastMSalesList = new ArrayList<OrderStorage>();
		for (OrderStorage data : os) {
			if (data.getWeekDate().equals(os.get(0).getWeekDate())) {
				lastMSalesList.add(data);
			}
		}

		// 판매수량별로 오름차순정렬
		Collections.sort(lastMSalesList);

		// 정렬한 데이터를 메뉴리스트로 순서대로 변환
		for (OrderStorage data : lastMSalesList) {
			bestMenu.add(ms.findMenuById(data.getMenuId()));
		}

		return ResponseEntity.ok(bestMenu);
	}

	/*
	 * 신메뉴 불러오기 - 이번 달에 새롭게 등록된 메뉴 리스트 불러오기
	 */

	@PostMapping("/newmenu")
	@ResponseBody
	public ResponseEntity<?> getnewmenu() {

		List<Menu> newMenu = ms.findNewMenus();
		return ResponseEntity.ok(newMenu);
	}

}
