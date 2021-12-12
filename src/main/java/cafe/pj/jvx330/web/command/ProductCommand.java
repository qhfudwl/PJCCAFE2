package cafe.pj.jvx330.web.command;

import java.util.Date;

import cafe.pj.jvx330.domain.Menu;
/*
 * 211212 임시 
 * 사용안
 * 
 * 
 */
public class ProductCommand {
	private MenuCommand menu;
	private int quantity;
		

	private String orderNumber;
	private Date regDate;
	
	public ProductCommand() {
		
	}


	public MenuCommand getMenu() {
		return menu;
	}

	public void setMenu(MenuCommand menu) {
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
