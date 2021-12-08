package cafe.pj.jvx330.domain;

public class Product {
	private Menu menu;
	private int quantity;
	
	public Product() {
		
	}
//	         erase this222222.                                      
	public Product(Menu menu, int quantity) {
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
		return menu.getId()+ "," + quantity + "/";
	}
}
