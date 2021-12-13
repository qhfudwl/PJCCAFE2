package cafe.pj.jvx330.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sales {
	private long id;
	private User user;
	private String orderNumber;
	private char place;
	private double amount;
	private double usePoint;
	private List<Product> order;

	private Date regDate;
	
	public Sales() {
		
	} 
	

	public Sales(User user, String orderNumber, char place, double amount, double userPoint, List<Product> order) {
		this.user = user;
		this.orderNumber = orderNumber;
		this.place = place;
		this.amount = amount;
		this.usePoint = userPoint;
		this.order = order;

	}
	
	public Sales(long id, User user, String orderNumber, char place, double amount,
			double userPoint, List<Product> order, Date regDate) {

		this.id = id;
		this.user = user;
		this.orderNumber = orderNumber;
		this.place = place;
		this.amount = amount;
		this.usePoint = userPoint;
		this.order = order;
		this.regDate = regDate;
	}
	
	private List<Product> makeOrderList(String orderList) {
//		String[] order = orderList.split("/");
//		String[] 
//		Menu menu = null;
//		long test;
//		
//		for(String temp : order) {
//			temp.split(","); 
//			
//		}
		
		
		
		return null;
	}

	/**
	 * DB에서 받은 문자열을 이용해서 order 만들기
	 * @param productList
	 * @return
	 * @author 김보령, 성지원
	 */
	private List<Product> makeOrder(String productList) {
		
		List<Product> order = new ArrayList<>();
		
		String[] productsArr = productList.trim().split("/");
		
		for (String p : productsArr) {
			String[] productArr = p.trim().split(",");
			long menuId = Long.parseLong(productArr[0]);
			int quantity = Integer.parseInt(productArr[1]);
			Product product = new Product(new Menu(menuId), quantity);
			order.add(product);
		}
		
		return order;
	}
	
	/**
	 * 한 영수증의 총 가격 반환
	 * @return
	 * @author 김보령
	 */
	public double getTotalPrice() {
		
		double totalPrice = 0;
		for (Product p : this.order) {
			totalPrice += (p.getMenu().getMenuPrice() * p.getQuantity()); 
		}
		
		return totalPrice;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public char getPlace() {
		return place;
	}
	public void setPlace(char place) {
		this.place = place;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getUsePoint() {
		return usePoint;
	}
	public void setUsePoint(double userPoint) {
		this.usePoint = userPoint;
	}
	public List<Product> getOrder() {
		return order;
	}

	public void setOrder(List<Product> order) {
		this.order = order;

	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
