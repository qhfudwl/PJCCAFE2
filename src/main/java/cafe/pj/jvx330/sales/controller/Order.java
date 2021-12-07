package cafe.pj.jvx330.sales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Order {
	
		@RequestMapping(value="/sayHello",method=RequestMethod.GET)
		public ModelAndView sayHello() {

			ModelAndView mav = new ModelAndView();
			mav.setViewName("order/order");
			return mav;
		}
}
