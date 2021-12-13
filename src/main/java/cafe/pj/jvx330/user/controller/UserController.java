package cafe.pj.jvx330.user.controller;

import javax.annotation.Resource;

import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.web.controller.CafeController;

public class UserController extends CafeController{
	@Resource(name="userService")
	UserService us;
	
	
}
