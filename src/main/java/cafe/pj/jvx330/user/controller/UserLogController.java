package cafe.pj.jvx330.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import cafe.pj.jvx330.domain.Employee;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.web.command.EmployeeCommand;

@Controller("user.controller.userLoginController")
public class UserLogController extends UserController {
	
	@GetMapping("/user/viewLogin")
	public ModelAndView viewLogin() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("employeeCommand", new EmployeeCommand());
		mav.setViewName("user/login");
		return mav;
	}
	
	@GetMapping("/user/logout")
	public ModelAndView logout(HttpSession session) {

		session.removeAttribute("admin");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/viewLogin");
		
		return mav;
	}
	
	@PostMapping("/user/checkAdmin")
	public ModelAndView checkAdmin(@ModelAttribute("employeeCommand") EmployeeCommand employeeCommand, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		Map<String, String> errMsg = new HashMap<>();
		
		if (validator.isEmpty(employeeCommand.getEid())) {
			errMsg.put("idErr", "아이디를 입력해주세요.");
			mav.addObject("errMsg", errMsg);
			mav.addObject(employeeCommand);
			mav.setViewName("user/login");
			return mav;
		}
		if (!us.isEmployee(employeeCommand.getEid())) {
			errMsg.put("idErr", "아이디가 존재하지 않습니다.");
			mav.addObject("errMsg", errMsg);
			mav.addObject(employeeCommand);
			mav.setViewName("user/login");
			return mav;
		}
		if (validator.isEmpty(employeeCommand.getPasswd())) {
			errMsg.put("pwErr", "비밀번호를 입력해주세요.");
			mav.addObject("errMsg", errMsg);
			mav.addObject(employeeCommand);
			mav.setViewName("user/login");
			return mav;
		}
		
		User user = us.findEmployeeByEid(employeeCommand.getEid());
		Employee inputEp = convertEmployeeCommandToEmployee(employeeCommand);
		
		Employee admin = (Employee)user;
		
		
		if (!inputEp.getPasswd().equals(admin.getPasswd())) {
			errMsg.put("pwErr", "비밀번호 불일치");
			mav.addObject("errMsg", errMsg);
			mav.addObject(employeeCommand);
			mav.setViewName("user/login");
			return mav;
		}
		
		mav.setViewName("redirect:/indexView");
		session.setAttribute("admin", admin);
		session.setMaxInactiveInterval(-1);
		
		return mav;
	}
}
