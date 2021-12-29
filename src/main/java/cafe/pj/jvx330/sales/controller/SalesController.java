package cafe.pj.jvx330.sales.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cafe.pj.jvx330.menu.domain.Menu;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.sales.domain.Product;
import cafe.pj.jvx330.sales.domain.Sales;
import cafe.pj.jvx330.sales.service.SalesService;
import cafe.pj.jvx330.user.domain.User;
import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.web.command.ProductCommand;
import cafe.pj.jvx330.web.command.SalesCommand;
import cafe.pj.jvx330.web.util.Validator;

/**
 * Sales 관련 부모 컨트롤러
 * @author 김보령
 *
 */
public class SalesController {
	@Resource(name="validator")
	protected Validator validator;
	
	@Resource(name="salesService")
	SalesService ss;
	
	@Resource(name="menuService")
	MenuService ms;

	@Resource(name="userService")
	UserService us;
	
	public static String order_number = "";
	/**
	 * 주문 번호 반환
	 * @return
	 * @author 김보령
	 */
	protected String returnOrderNumber() {
		
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
	
	/**
	 * orderCommand 를 order로 변환
	 * @param orderCommand
	 * @return
	 */
	protected List<Product> convertProductCommandToProduct(List<ProductCommand> orderCommand){
		List<Product> order = new ArrayList<>();
		for (ProductCommand pc : orderCommand) {
			Menu menu = ms.findMenuById(pc.getMenu().getId());
			Product p = new Product(menu, pc.getQuantity());
			order.add(p);
		}
		return order;
	}
	
	/**
	 * session 내 order 를 반환
	 * @param session
	 * @return
	 */
	protected Map<String, List<Product>> checkOrderInSession(HttpSession session) {
		Map<String, List<Product>> order = (Map<String, List<Product>>)session.getAttribute("order");
		// order 가 null 이면 실행
		if (validator.isEmpty(order)) {
			Map<String, List<Product>> makeOrder = new HashMap<>();
			session.setAttribute("order", makeOrder);
			
			order = (Map<String, List<Product>>) session.getAttribute("order");
		}
		return order;
	}

	/**
	 * session 내 compSales 반환
	 * @param session
	 * @return
	 */
	protected Map<String, Sales> checkCompSalesInSession(HttpSession session) {
		Map<String, Sales> compSales = (Map<String, Sales>)session.getAttribute("compSales");
		if (validator.isEmpty(compSales)) {
			Map<String, Sales> makeCompSales = new TreeMap<>();
			session.setAttribute("compSales", makeCompSales);
			
			compSales = (Map<String, Sales>) session.getAttribute("compSales");
		}
		return compSales;
	}
}
