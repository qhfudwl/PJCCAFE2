package cafe.pj.jvx330.sales.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.sales.util.SalesStorage;
import cafe.pj.jvx330.web.command.SalesCommand;
import cafe.pj.jvx330.web.util.OrderStorage;

@Controller("sales.controller.salesListController")
public class SalesListController extends SalesController{
	
	@GetMapping("/sales/viewOrderRecordByMenu")
	public ModelAndView viewOrderRecordByMenu(@RequestParam("periodType") char period, @RequestParam("menuType") char menu,
	HttpServletRequest request, HttpSession session) throws IllegalStateException, IOException{
		List<OrderStorage> osList = ss.findOrderRecordForMenu(period, menu);
		
		for(OrderStorage os : osList) {
			System.out.println(os.getMenuName() + " : " + os.getWeekDate());
		}
		System.out.println("--------------------------");
		
		ModelAndView mav = new ModelAndView();
		session.setAttribute("contentName", "메뉴별 판매내역");
		mav.addObject("osList", osList);
		mav.addObject("periodType", period);
		mav.addObject("menuType", menu);

		mav.setViewName("sales/orderRecordByMenu");
	
		return mav;
	}
	/**
	 * nav로 접근하기
	 * @param session
	 * @return
	 */
	@GetMapping("/sales/viewSalesMain")
	public ModelAndView viewSalesMain(HttpSession session) {
		List<SalesStorage> sList = ss.findSaleRecord('D');
		ModelAndView mav = new ModelAndView();
		session.setAttribute("contentName", "판매내역");
		/*,@RequestParam("datePicker") char datePicker
		mav.addObject("D",datePicker);*/
		mav.addObject("sList",sList);
		mav.setViewName("sales/sales_view_main");
		
		return mav;
	}
	
	@GetMapping("/sales/viewSalesType")
	public ModelAndView viewSalesType(@RequestParam("datePicker") char type) {
		List<SalesStorage> sList = ss.findSaleRecord(type);
		ModelAndView mav = new ModelAndView();
		System.out.println(type);
		mav.addObject("sList",sList);
		//System.out.println(sList);
		mav.addObject("datePicker", type);
		mav.setViewName("sales/sales_view_main");
		
		return mav;
	}
	
	@GetMapping("/sales/searchOrderRecordByMenu")
	public ModelAndView searchOrderRecordBymenu(@RequestParam("periodType") char period, @RequestParam("menuType") char menu, @RequestParam("searchTxt") String txt, 
	HttpServletRequest request, HttpSession session) throws IllegalStateException, IOException{
		List<OrderStorage> osList = ss.findOrderRecordForMenu(period, menu);
		
		ModelAndView mav = new ModelAndView();
		session.setAttribute("contentName", "메뉴별 판매내역");
		mav.addObject("osList", osList);
		mav.addObject("periodType", period);
		mav.addObject("menuType", menu);
		mav.addObject("searchTxt", txt);
		
		mav.setViewName("sales/orderRecordByMenu");
	
		return mav;
	}// 물어바.
	

	
}
