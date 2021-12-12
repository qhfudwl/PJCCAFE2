package cafe.pj.jvx330.sales.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.web.command.SalesCommand;

@Controller("web.controller.indexViewController")
public class IndexViewController extends SalesController {
	@Autowired
	private MenuService ms;
	@Autowired
	private UserService us;
	@GetMapping("/indexView")
	public ModelAndView indexView(HttpServletRequest request) {

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
		
		
		HttpSession session = request.getSession();
		Sales sales1 = new Sales(user1, "A01", 'I', 7000, 1000, order1);
		Sales sales2 = new Sales(user1, "A02", 'O', 7000, 1000, order2);
		Sales sales3 = new Sales(user2, "A03", 'I', 7000, 1000, order1);
		Sales sales4 = new Sales(user2, "A04", 'O', 7000, 1000, order2);
		sales1.setRegDate(today);
		sales2.setRegDate(today);
		sales3.setRegDate(today);
		sales4.setRegDate(today);
		
		Map<String, Sales> sales = new HashMap<>();
		sales.put(sales1.getOrderNumber(), sales1);
		sales.put(sales2.getOrderNumber(), sales2);
		sales.put(sales3.getOrderNumber(), sales3);
		sales.put(sales4.getOrderNumber(), sales4);
		
		Map<String, List<Product>> order = new HashMap<>();
		order.put(sales1.getOrderNumber(), order1);
		order.put(sales2.getOrderNumber(), order2);
		order.put(sales3.getOrderNumber(), order1);
		order.put(sales4.getOrderNumber(), order2);
		
		session.setAttribute("sales", sales);
		session.setAttribute("order", order);
		
		List<Sales> compSales = ss.findSalesByDate(today);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("compSales", compSales);
		mav.setViewName("index");
		
		return mav;
	}
	
	@PostMapping("/addSales")
	public ModelAndView addSales(@ModelAttribute SalesCommand salesCommand) {
		
		Sales sales = new Sales();
		User user = us.findUserById(salesCommand.getUserId());
		sales.setOrderNumber(salesCommand.getOrderNumber());
		sales.setUser(user);
		sales.setAmount(salesCommand.getAmount());
		sales.setUsePoint(salesCommand.getUsePoint());
		sales.setPlace(salesCommand.getPlace());
		
		ss.addSales(sales);
		
		Date today = java.sql.Timestamp.valueOf(LocalDateTime.now());
		
		List<Sales> compSales = ss.findSalesByDate(today);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("compSales", compSales);
		mav.setViewName("index");
		
		return mav;
	}
}
