package cafe.pj.jvx330.sales.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;

@Component("sales.controller.SalesListController")
public class SalesListController extends SalesController{
	private List<Product> order = new ArrayList<Product>();
	
	@GetMapping("/Sales/findSaleRecord")
	public ModelAndView findSalesRecord(@RequestParam("menuType") char type, @RequestParam("periodType") char period) {
		
		
		
		
//		List<Sales> sales = ss.findSalesByDate(type, date1, date2);
		
//		order = ss.extractProductList(sales); 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("order", order);
		mav.setViewName("Sales/salesByMenu");
		
		return mav;
	}
	
}
