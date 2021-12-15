package cafe.pj.jvx330.domain;

import java.util.Date;

public class Product {
	
	private Menu menu;
	private int quantity;
	private String orderNumber;
	private Date regDate;
	
	public Product() {
		
	}

	public Product(Menu menu, int quantity) {
		this.menu = menu;
		this.quantity = quantity;
	}
	
	public Product(String orderNumber, Menu menu, int quantity, Date regDate) {
		this.orderNumber = orderNumber;
		this.menu = menu;
		this.quantity = quantity;
		this.regDate = regDate;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return menu.getId() + "," + quantity + "/";
	}
	
	
}
