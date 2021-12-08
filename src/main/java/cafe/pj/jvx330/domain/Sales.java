package cafe.pj.jvx330.domain;

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
//	private String orderList;
	private Date regDate;

	public Sales() {
		
	} 
	
	public Sales(User user, String orderNumber, char place, double pays, double userPoint, List<Product> order) {
		this.user = user;
		this.orderNumber = orderNumber;
		this.place = place;
		this.pays = pays;
		this.userPoint = userPoint;
		this.order = order;
	}




//	public Sales(long id, User user, String orderNumber, char place, double pays, double userPoint, List<Order> orders,
//			Date regDate) {
//		this.id = id;
//		this.user = user;
//		this.orderNumber = orderNumber;
//		this.place = place;
//		this.pays = pays;
//		this.userPoint = userPoint;
//		this.orders = orders;
//		this.regDate = regDate;
//	}
	
//	Sales sales = new Sales(rs.getLong("id"), user, rs.getString("orderNumber"), rs.getString("place").charAt(0), 
//	rs.getDouble("amount"), rs.getDouble("usePoint"), rs.getString("orderList"), rs.getDate("regDate"));
	public Sales(long id, User user, String orderNumber, char place, double pays, double userPoint, String orderList,
			Date regDate) {
		this.id = id;
		this.user = user;
		this.orderNumber = orderNumber;
		this.place = place;
		this.pays = pays;
		this.userPoint = userPoint;
		this.order = makeOrderList(orderList);
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
	public void setOrders(List<Product> orders) {
		this.order = orders;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
