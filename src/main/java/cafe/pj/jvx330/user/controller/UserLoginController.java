package cafe.pj.jvx330.user.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.web.controller.CafeController;

@Controller("user.controller.userLoginController")
public class UserLoginController extends CafeController {
	@Resource(name="userService")
	UserService us;
	
	@GetMapping("/user/loginView")
	public String loginView() {
		return "user/login";
	}
}
