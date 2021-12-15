package cafe.pj.jvx330.sales.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.web.command.ProductCommand;
import cafe.pj.jvx330.web.command.SalesCommand;

@Controller("web.controller.indexViewController")
public class IndexViewController extends SalesController {
	@GetMapping("/indexView")
	public ModelAndView indexView(HttpSession session) {
		Map<String, List<Product>> order = checkOrderInSession(session);
		
		// 여기서부터는 나중에 삭제해야한다.---------------------------------------
		Date today = java.sql.Timestamp.valueOf(LocalDateTime.now());
		User user1 = us.findUserById(1);
		User user2 = us.findUserById(2);
		
		Menu menu1 = ms.findMenuById(1);
		Menu menu2 = ms.findMenuById(2);
		Menu menu3 = ms.findMenuById(3);
		
		List<Product> order1 = new ArrayList<>();
		Product p1 = new Product(menu1, 3);
		Product p2 = new Product(menu2, 2);
		Product p3 = new Product(menu3, 5);
		order1.add(p1);
		order1.add(p2);
		order1.add(p3);

		List<Product> order2 = new ArrayList<>();
		Product p4 = new Product(menu1, 3);
		Product p5 = new Product(menu2, 2);
		Product p6 = new Product(menu3, 5);
		order2.add(p4);
		order2.add(p5);
		order2.add(p6);
		
		
		Sales sales1 = new Sales(user1, "A01", 'I', 7000, 1000, order1);
		Sales sales2 = new Sales(user1, "A02", 'O', 7000, 1000, order2);
		Sales sales3 = new Sales(user2, "A03", 'I', 7000, 1000, order1);
		Sales sales4 = new Sales(user2, "A04", 'O', 7000, 1000, order2);
		sales1.setRegDate(today);
		sales2.setRegDate(today);
		sales3.setRegDate(today);
		sales4.setRegDate(today);
		
		Sales sales5 = new Sales(user1, "A05", 'I', 7000, 1000, order1);
		Sales sales6 = new Sales(user1, "A06", 'O', 7000, 1000, order2);
		Sales sales7 = new Sales(user2, "A07", 'I', 7000, 1000, order1);
		Sales sales8 = new Sales(user2, "A08", 'O', 7000, 1000, order2);
		sales5.setRegDate(today);
		sales6.setRegDate(today);
		sales7.setRegDate(today);
		sales8.setRegDate(today);
		
		Sales sales9 = new Sales(user1, "A09", 'I', 7000, 1000, order1);
		Sales sales10 = new Sales(user1, "A10", 'O', 7000, 1000, order2);
		Sales sales11 = new Sales(user2, "A11", 'I', 7000, 1000, order1);
		Sales sales12 = new Sales(user2, "A12", 'O', 7000, 1000, order2);
		sales9.setRegDate(today);
		sales10.setRegDate(today);
		sales11.setRegDate(today);
		sales12.setRegDate(today);
		
		Map<String, Sales> sales = new TreeMap<>();
		sales.put(sales1.getOrderNumber(), sales1);
		sales.put(sales2.getOrderNumber(), sales2);
		sales.put(sales3.getOrderNumber(), sales3);
		sales.put(sales4.getOrderNumber(), sales4);
		sales.put(sales5.getOrderNumber(), sales5);
		sales.put(sales6.getOrderNumber(), sales6);
		sales.put(sales7.getOrderNumber(), sales7);
		sales.put(sales8.getOrderNumber(), sales8);
		sales.put(sales9.getOrderNumber(), sales9);
		sales.put(sales10.getOrderNumber(), sales10);
		sales.put(sales11.getOrderNumber(), sales11);
		sales.put(sales12.getOrderNumber(), sales12);
		
		session.setAttribute("sales", sales);
		// 여기까지 나중에 삭제해야한다.---------------------------------------

		ModelAndView mav = new ModelAndView();
		
		// session 내 order 길이가 1 이상일 때(원소가 하나라도 있을 때)
		// compSales를 만들어주고, mav에 넣어준다.
		
		if (order.size() > 0){
			List<Sales> compSales = ss.findSalesByDate(today);
			for (Sales s : compSales) {
				s.setOrder(order.get(s.getOrderNumber()));
			}
			mav.addObject("compSales", compSales);
		}
		
		mav.setViewName("index");
		
		return mav;
	}
	
	@PostMapping("/addSales")
	public ModelAndView addSales(@ModelAttribute SalesCommand salesCommand, 
			HttpSession session) {
		// 현재 session 에 저장되어있는 Sales 들
		Map<String, Sales> salesList = (Map<String, Sales>) session.getAttribute("sales");
		
		// session 내 salesList 의 해당 주문번호의 sales 반환
		Sales sales = salesList.get(salesCommand.getOrderNumber());
		
		// SalesRecord 에 추가
		ss.addSales(sales);
		
		// 오늘 날짜
		Date today = java.sql.Timestamp.valueOf(LocalDateTime.now());
		
		// session에 order 가 있는지 확인하고 있으면 그걸 반환 / 없으면 새로 만들어서 넣은 후 그걸 다시 받아서 반환
		Map<String, List<Product>> order = checkOrderInSession(session);
		order.put(sales.getOrderNumber(), sales.getOrder());
		
		// session 내 order에 추가 후 해당 orderNumber sales 삭제
		salesList.remove(sales.getOrderNumber());
		
		// 오늘 날짜에 대한 모든 SalesRecord 찾기
		List<Sales> compSales = ss.findSalesByDate(today);
		for (Sales s : compSales) {
			s.setOrder(order.get(s.getOrderNumber()));
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("compSales", compSales);
		mav.setViewName("index");
		
		return mav;
	}
	
	@GetMapping("/addOrderRecordByBatch")
	public ModelAndView addOrderRecordByBatch(HttpSession session) {
		Map<String, List<Product>> order = checkOrderInSession(session);
		List<Product> finalOrder = null;
		List<Product> temp_order = new ArrayList<Product>();
		
		order.forEach((key, productList) -> {
			temp_order.addAll(productList);
		});
		
		finalOrder = ss.sumOrder(temp_order); // quantity 더하기
		
		ss.addOrderRecord(finalOrder);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		
		return mav;
	}
}
