package cafe.pj.jvx330.domain;

public class Menu {
	private long id;
	private String imagepath;
	private char menuType;
	private String menuName;
	private double menuPrice;
	private boolean stock;
	
	public Menu() {
		
	}
	
	public Menu(long id) {
		this.id = id;
	}
	
	public Menu(char menuType, String menuName, double menuPrice, boolean stock) {
		this.setMenuType(menuType);
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.stock = stock;
	}
	
	public Menu(long id, char menuType, String menuName, double menuPrice, boolean stock) {
		this.id = id;
		this.menuType = menuType;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.stock = stock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public double getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}

	public boolean isStock() {
		return stock;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	public char getMenuType() {
		return menuType;
	}

	public void setMenuType(char menuType) {
		this.menuType = menuType;
	}
}
