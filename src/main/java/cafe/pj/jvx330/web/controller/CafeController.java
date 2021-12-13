package cafe.pj.jvx330.web.controller;

import javax.annotation.Resource;

import cafe.pj.jvx330.menu.util.FileAuxiliaryFunction;
import cafe.pj.jvx330.web.util.Validator;

public abstract class CafeController {
	
	public static final String ORDER_NUMBER = "A-001";
	
	@Resource(name="validator")
	protected Validator validator;
	
	private static void increaseOrderNumber() {
		String[] arr = ORDER_NUMBER.trim().split("-");
		char alphabet = arr[0].charAt(0);
		int number = Integer.parseInt(arr[1]);
		System.out.println(alphabet+1);
		System.out.println(number+1);
//		if (number < 1000) {
//			number++;
//		} else if (number == 1000) {
//			
//		}
	}
	
	public static void main(String[] args) {
		increaseOrderNumber();
	}
}
