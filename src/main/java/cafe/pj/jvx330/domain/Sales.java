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
	private List<Order> orders;
	private Date regDate;

	public Sales() {
		
	}
	
	
	
	
	public Sales(User user, String orderNumber, char place, double pays, double userPoint, List<Order> orders) {
		this.user = user;
		this.orderNumber = orderNumber;
		this.place = place;
		this.pays = pays;
		this.userPoint = userPoint;
		this.orders = orders;
	}




	public Sales(long id, User user, String orderNumber, char place, double pays, double userPoint, List<Order> orders,
			Date regDate) {
		this.id = id;
		this.user = user;
		this.orderNumber = orderNumber;
		this.place = place;
		this.pays = pays;
		this.userPoint = userPoint;
		this.orders = orders;
		this.regDate = regDate;
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
