package cafe.pj.jvx330.web.command;

import java.util.List;

public class SalesCommand {
	private long id;
	private long userId;
	private String orderNumber;
	private double amount;
	private double usePoint;
	private char place;
	private List<ProductCommand> order;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public void setUsePoint(double usePoint) {
		this.usePoint = usePoint;
	}
	public List<ProductCommand> getOrder() {
		return order;
	}
	public void setOrder(List<ProductCommand> order) {
		this.order = order;
	}
	
}
