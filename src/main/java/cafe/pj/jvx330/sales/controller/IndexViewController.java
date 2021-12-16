package cafe.pj.jvx330.sales.controller;

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

/**
 * 주문현황
 * @author 김보령
 *
 */
@Controller("web.controller.indexViewController")
public class IndexViewController extends SalesController {
   
   /**
    * 
    * @param session
    * @return
    */
   @GetMapping("/indexView")
   public ModelAndView indexView(HttpSession session) {
      Map<String, List<Product>> order = checkOrderInSession(session);
      Date today = getNowDate();
      session.setAttribute("contentName", "주문현황");

      ModelAndView mav = new ModelAndView();
      
      // session 내 order 길이가 1 이상일 때(원소가 하나라도 있을 때)
      // compSales를 만들어주고, mav에 넣어준다.
      if (order.size() > 0){
         List<Sales> compSales = ss.findSalesByDate(today);
         for (Sales s : compSales) {
            s.setOrder(order.get(s.getOrderNumber()));
         }
//         mav.addObject("compSales", compSales);
         session.setAttribute("compSales", compSales);
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
      Date today = getNowDate();
      
      // session에 order 가 있는지 확인하고 있으면 그걸 반환 / 없으면 새로 만들어서 넣은 후 그걸 다시 받아서 반환
      Map<String, List<Product>> order = checkOrderInSession(session);
      order.put(sales.getOrderNumber(), sales.getOrder()); // order 에 주문 완료된 내역 추가
      
      // session 내 order에 추가 후 해당 orderNumber sales 삭제
      salesList.remove(sales.getOrderNumber());
      
      ModelAndView mav = new ModelAndView();
      
      // controller의 경로를 적어서 그 쪽으로 보낸다. 새로 고침 시 또 추가되는 현상을 막을 수 있다.
      mav.setViewName("redirect:/indexView");
      
      return mav;
   }
   
   @GetMapping("/addOrderRecordByBatch")
	public ModelAndView addOrderRecordByBatch(HttpSession session) {
		Map<String, List<Product>> order = checkOrderInSession(session);
		List<Product> finalOrder = new ArrayList<Product>();
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