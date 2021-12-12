package cafe.pj.jvx330.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.web.command.CustomerCommand;

public class UserListController extends UserController{
	
	/**
	 * 유저 기본화면
	 */
	@PostMapping("/user/userMain")
	public ModelAndView viewMain() {
		List<User> users = us.findAllUsers();
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", users);
		mav.setViewName("user/user_list");
		return mav;
	}
	
	/**
	 * 우저 추가 팝업창
	 * @param customerCommand
	 * @return
	 */
	@PostMapping("/user/addUser")
	public ModelAndView adduser(CustomerCommand customerCommand) {
		
		User user = new Customer(customerCommand.getCustomerName(),customerCommand.getPhone(),
					customerCommand.getBirth());
		us.addUser(user);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("user",user);
		mav.setViewName("user/user_success");
		
		return mav;
	}
	
	/**
	 * 유저 수정 팝업창
	 * @param customerCommand
	 * @return
	 */
	@PostMapping("/user/updateUser")
	public ModelAndView updateUser(CustomerCommand customerCommand) {
		
		User user = new Customer(customerCommand.getId(),customerCommand.getCustomerName(),
				customerCommand.getPhone(),customerCommand.getBirth(),customerCommand.getPoint(),
				customerCommand.getRegDate());
		
		us.updateUserById(user);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("user" ,user);
		mav.setViewName("user/user_success");
		
		return mav;
		
	}
	/**
	 * 검색어로 검색해서 유저목록 보여주기!
	 * 이름, 폰번호, 생일
	 * 현재 팝업창으로 할지, 유저리스트화면에 만들지 고민중!
	 * @param search
	 * @return
	 */
	/*
	@PostMapping("/user/userSearch")
	public ModelAndView SearchUser(@RequestParam("search") String search) {
		String column = @RequestParam("column");
		if (column.equals(column) == "name") {
			List<User> users = us.findUsersByname(search);
			ModelAndView mav = new ModelAndView();
			mav.addObject("users" ,users);
			mav.setViewName("user/user_list");
			return mav;
			
		}else if (column.equals(column) == "phone") {
			List<User> users = us.findUsersByPhone(search);
			ModelAndView mav = new ModelAndView();
			mav.addObject("users" ,users);
			mav.setViewName("user/user_list");
			return mav;
			
		}else if (column.equals(column) == "birth") {
			List<User> users = us.findUsersByBirth(search);
			ModelAndView mav = new ModelAndView();
			mav.addObject("users" ,users);
			mav.setViewName("user/user_list");
			return mav;
		}
	}
	*/
	/*
	@PostMapping("/user/removeUser")
	public ModelAndView removeUser(CustomerCommand customerCommand) {
		
		
		
		return null;
	}*/
}
