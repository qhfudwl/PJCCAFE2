package cafe.pj.jvx330.user.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import cafe.pj.jvx330.sales.service.SalesService;
import org.springframework.web.servlet.ModelAndView;
import cafe.pj.jvx330.domain.Customer;
import cafe.pj.jvx330.domain.Employee;
import cafe.pj.jvx330.domain.User;
import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.web.command.CustomerCommand;
import cafe.pj.jvx330.web.command.EmployeeCommand;
import cafe.pj.jvx330.web.util.Validator;

public class UserController {
	@Resource(name="validator")
	protected Validator validator;
	
	@Resource(name="userService")
	UserService us;
	@Resource(name="salesService")
	SalesService ss;
	
	protected EmployeeCommand convertUserToEmployeeCommand(User user) {
		EmployeeCommand ec = new EmployeeCommand();
		Employee e = null;
		
		if (user instanceof Employee) {
			e = (Employee)user;
		}
		
		String eid = e.getEid();
		String passwd = e.getPasswd();
		String position = e.getPosition();
		
		if (!validator.isEmpty(eid)) {
			ec.setEid(eid);
		}
		if (!validator.isEmpty(passwd)) {
			ec.setPasswd(passwd);
		}
		if (!validator.isEmpty(position)) {
			ec.setPosition(position);
		}
		
		return ec;
	}
	
	protected Employee convertEmployeeCommandToEmployee(EmployeeCommand ec) {
		Employee e = new Employee();
		
		String eid = ec.getEid();
		String passwd = ec.getPasswd();
		String position = ec.getPosition();
		
		if (!validator.isEmpty(eid)) {
			e.setEid(eid);
		}
		if (!validator.isEmpty(passwd)) {
			e.setPasswd(passwd);
		}
		if (!validator.isEmpty(position)) {
			e.setPosition(position);
		}
		
		return e;
	}
	
	protected CustomerCommand convertUserToCustomerCommand(User user) {
		CustomerCommand cc = new CustomerCommand();

		Customer c = null;
		
		if (user instanceof Customer) {
			c = (Customer)user;
		}
		
		long id = c.getId();
		String customerName = c.getCustomerName();
		String phone = c.getPhone();
		String birth = c.getBirth();
		double point = c.getPoint();
		Date regDate = c.getRegDate();
		
		if (!validator.isEmpty(id)) {
			cc.setId(id);
		}
		if (!validator.isEmpty(customerName)) {
			cc.setCustomerName(customerName);
		}
		if (!validator.isEmpty(phone)) {
			cc.setPhone(phone);
		}
		if (!validator.isEmpty(birth)) {
			cc.setBirth(birth);
		}
		if (!validator.isEmpty(point)) {
			cc.setPoint(point);;
		}
		if (!validator.isEmpty(regDate)) {
			cc.setRegDate(regDate);
		}
		
		return cc;
	}
	
	protected Customer convertCustomerCommandToCustomer(CustomerCommand cc) {
		Customer c = new Customer();
		
		long id = cc.getId();
		String customerName = cc.getCustomerName();
		String phone = cc.getPhone();
		String birth = cc.getBirth();
		double point = cc.getPoint();
		Date regDate = cc.getRegDate();
		
		if (!validator.isEmpty(id)) {
			c.setId(id);
		}
		if (!validator.isEmpty(customerName)) {
			c.setCustomerName(customerName);
		}
		if (!validator.isEmpty(phone)) {
			c.setPhone(phone);
		}
		if (!validator.isEmpty(birth)) {
			c.setBirth(birth);
		}
		if (!validator.isEmpty(point)) {
			c.setPoint(point);;
		}
		if (!validator.isEmpty(regDate)) {
			c.setRegDate(regDate);
		}
		
		return c;
	}

	protected ModelAndView setModelAndViewE(ModelAndView mav, Map<String, String> errMsg, EmployeeCommand ec, String path) {
		
		mav.addObject("errMsg", errMsg);
		mav.addObject("employeeCommand", ec);
		mav.setViewName(path);
		
		return mav;
	}

	protected ModelAndView setModelAndViewC(ModelAndView mav, Map<String, String> errMsg, CustomerCommand cc, String path) {
		
		mav.addObject("errMsg", errMsg);
		mav.addObject("customerCommand", cc);
		mav.setViewName(path);
		
		return mav;
	}
}
