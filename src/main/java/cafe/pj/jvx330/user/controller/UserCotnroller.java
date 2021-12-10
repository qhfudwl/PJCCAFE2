package cafe.pj.jvx330.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.web.command.CustomerCommand;

	@Controller("user.controller.userJoinController")
	public class UserCotnroller {

		@Resource(name="userSerivce")
		 UserService us;
		
		@RequestMapping(value="/user/join", method = POST)
		public void addCustomer(HttpServletRequest request) {
			String userName = request.getParameter("name");
			String phone = request.getParameter("phone");
			String birth = request.getParameter("birth");
			
			CustomerCommand cC = new CustomerCommand();
			cC.setCustomerName(userName);
			cC.setPhone(phone);
			cC.setBirth(birth);
			//us.addUser(cC);
			//스크립트로 창 닫히게 해주기
		}
		
		@RequestMapping(value="/user/revise", method = POST)
		public void findCustomer(HttpServletRequest request) {
			String phone = request.getParameter("phone");
			
			
		}
		
	
}
