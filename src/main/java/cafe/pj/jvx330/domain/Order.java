package cafe.pj.jvx330.domain;

public class Order {
	private Menu menu;
	private int quantity;
	
	public Order() {
		
	}
	
	public Order(Menu menu, int quantity) {
		this.menu = menu;
		this.quantity = quantity;
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

	@Override
	public String toString() {
		return menu + "," + quantity + "/";
	}
}
