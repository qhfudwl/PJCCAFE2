package cafe.pj.jvx330.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.web.command.CustomerCommand;

@Controller
public class UserListController extends UserController{
	
	/**
	 * 헤더에서 유저목록 기본화면으로 들어가기
	 */
	@GetMapping("/user/viewUserMain")
	public ModelAndView viewUserMain(HttpSession session) {
		List<User> users = us.findAllUsers();
		ModelAndView mav = new ModelAndView();
		session.setAttribute("contentName", "회원목록");
		mav.addObject("users", users);
		mav.setViewName("user/user_list");
		return mav;
	}
	
	/**
	 * 유저 목록메인에서 유저 추가 팝법창으로 보내주기
	 * @param customerCommand
	 * @return
	 */
	@GetMapping("/user/addUserbtn")
	public ModelAndView addUserbtn(CustomerCommand customerCommand) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/popup_add_user");
		return mav;
	}
	
	/**
	 * 유저 추가 팝업창에서 유저 목록으로 보내주기
	 * @param customerCommand
	 * @return
	 */
	@PostMapping("/user/addUser")
	public ModelAndView addUser(@ModelAttribute CustomerCommand customerCommand) {
		
		User user = new Customer(customerCommand.getCustomerName(),customerCommand.getPhone(),
					customerCommand.getBirth());
		
		us.addUser(user);
		//List<User> users = us.findAllUsers();
		ModelAndView mav = new ModelAndView();
		mav.addObject("close","close");
		
		mav.setViewName("user/popup_add_user");
		
		return mav;
	}
	/**
	 * 유저 목록 메인에서 수정 팝업창으로 보내주기
	 * @param customerCommand
	 * @return
	 */
	@GetMapping("/user/updateUserbtn")
	public ModelAndView updateUserbtn(@RequestParam("usersId") long usersId) {
		User user = us.findUserById(usersId);
		
		System.out.println(user.getId());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("user" ,user);
		mav.setViewName("user/popup_revise_user");
		
		return mav;
	}
	
	/**
	 * 유저 수정 팝업창에서 수정 완료 후 유저 리스트 메인으로
	 * @param customerCommand
	 * @return
	 */
	@PostMapping("/user/updateUser")
	public ModelAndView updateUser(@ModelAttribute CustomerCommand customerCommand) {
		
		User user;		
		user = new Customer(customerCommand.getId(),customerCommand.getCustomerName(),
				customerCommand.getPhone(),customerCommand.getBirth(),customerCommand.getPoint(),
				customerCommand.getRegDate());
		us.updateUserById(user);
		System.out.println(user);
		//List<User> users =us.findAllUsers();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("close" ,"close");
		mav.setViewName("user/popup_revise_user");
		
		return mav;
		
	}
	
	/**
	 * 유저 아이디를 누르면 유저 삭제하기
	 * @param userid
	 * @return
	 */
	@GetMapping("/user/removeUser")
	public ModelAndView removeUser(@RequestParam("usersId") long usersId ) {
		
		User user = us.findUserById(usersId);
		us.removeUserId(user.getId());
		
		List<User> users = us.findAllUsers();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("users", users);
		mav.setViewName("user/user_list");
		
		return mav;
	}
	
	
	
	@GetMapping("/user/userSaleslist")
	public ModelAndView userSaleslist(@RequestParam("usersId") long usersId) {
		List<Sales> slist = ss.findSalesByCustomerId(usersId);
		User user = us.findUserById(usersId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("slist", slist);
		mav.addObject("user", user);
		mav.setViewName("user/user_sales_list");
		return mav;
	}
	
	/**
	 * 검색어로 검색해서 유저목록 보여주기!
	 * 이름, 폰번호, 생일
	 * 현재 팝업창으로 할지, 유저리스트화면에 만들지 고민중!
	 * @param search
	 * @return
	 */
	@GetMapping("/user/userSearch")
	public ModelAndView userSearch(@RequestParam("keyword") String keyword,
			@RequestParam("column") String column) {
		
		if(column.equals("birth")) {
			if(keyword == ""|| keyword == null) {
				List<User> users = us.findAllUsers();
				ModelAndView mav = new ModelAndView();
				mav.addObject("users", users);
				mav.setViewName("user/user_list");
				return mav;
			}
			List<User> users = us.findUserByBirth(keyword);
		
			ModelAndView mav = new ModelAndView();
			mav.addObject("users", users);
			mav.setViewName("user/user_list");
			return mav;
			
		}else if(column.equals("phone")) {
			if(keyword == ""|| keyword == null) {
				List<User> users = us.findAllUsers();
				ModelAndView mav = new ModelAndView();
				mav.addObject("users", users);
				mav.setViewName("user/user_list");
				return mav;
			}
			List<User> users = us.findUsersByPhone(keyword);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("users", users);
			mav.setViewName("user/user_list");
			return mav;
			
		}else if(column.equals("name")) {
			if(keyword == ""|| keyword == null) {
				List<User> users = us.findAllUsers();
				ModelAndView mav = new ModelAndView();
				mav.addObject("users", users);
				mav.setViewName("user/user_list");
				return mav;
			}
			List<User> users = us.findUserByName(keyword);
			
			ModelAndView mav = new ModelAndView();
			mav.addObject("users", users);
			mav.setViewName("user/user_list");
			return mav;
		}else
			return null;
	}
	
	
}
