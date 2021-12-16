package cafe.pj.jvx330.sales.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.sales.util.SalesStorage;
import cafe.pj.jvx330.web.util.OrderStorage;

@Controller("sales.controller.SalesListController")
public class SalesListController extends SalesController{
	
	@GetMapping("/order/viewOrderRecordByMenu")
	public ModelAndView viewOrderRecordByMenu(@RequestParam("menuType") char type, @RequestParam("periodType") char period) {
		List<OrderStorage> osList = ss.findOrderRecordForMenu(period);
		// menuType 파트까지 완성 해야 함
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("osList", osList);
		mav.setViewName("order/orderRecordByMenu");
	
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
		mav.addObject("sList",sList);
		mav.setViewName("Sales/sales_view_main");
		
		return mav;
	}
	
	@GetMapping("/sales/viewSalesType")
	public ModelAndView viewSalesType(@RequestParam("datePicker") char type) {
		List<SalesStorage> sList = ss.findSaleRecord(type);
		ModelAndView mav = new ModelAndView();
		mav.addObject("sList",sList);
		//System.out.println(sList);
		mav.setViewName("Sales/sales_view_main");
		
		return mav;
	}
	
}
