package cafe.pj.jvx330.web.util;

public class OrderStorage implements Comparable<OrderStorage>{
	private String weekDate;
	private String menuName;
	private int quantity;
	private double price;
	private Long menuId;
	
	
	public OrderStorage() {
		
	}
	
	public OrderStorage(String menuName, int quantity, double price) {
		this.menuName = menuName;
		this.quantity = quantity;
		this.price = price;
		
	}

	public String getWeekDate() {
		return weekDate;
	}

	public void setWeekDate(String weekDate) {
		this.weekDate = weekDate;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	@Override
	public int compareTo(OrderStorage os) {
	if (os.quantity < quantity) {
	return 1;
	} else if (os.quantity > quantity) {
	return -1;
	}
	return 0;
	}

	
	
	
}
