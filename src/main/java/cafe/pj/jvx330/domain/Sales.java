package cafe.pj.jvx330.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sales {
	private long id;
	private User user;
	private String orderNumber;
	private char place;
	private double pays;
	private double userPoint;
	private List<Product> order;

	private Date regDate;

	public Sales() {
		
	} 
	

	public Sales(User user, String orderNumber, char place, double pays, double userPoint, String productList) {
		this.user = user;
		this.orderNumber = orderNumber;
		this.place = place;
		this.pays = pays;
		this.userPoint = userPoint;

		this.order = makeOrder(productList);

	}
	
	public Sales(long id, User user, String orderNumber, char place, double pays,
			double userPoint, String productList, Date regDate) {

		this.id = id;
		this.user = user;
		this.orderNumber = orderNumber;
		this.place = place;
		this.pays = pays;
		this.userPoint = userPoint;

		this.order = makeOrder(productList);

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
	public double getPays() {
		return pays;
	}
	public void setPays(double pays) {
		this.pays = pays;
	}
	public double getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(double userPoint) {
		this.userPoint = userPoint;
	}
	public List<Product> getOrders() {
		return order;
	}

	public void setOrders(List<Product> order) {
		this.order = order;

	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
