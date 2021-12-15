package cafe.pj.jvx330.web.command;


public class OrderItemsCommand {
	String menuId;
	String quantity;
	
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderItemsCommand [menuId=" + menuId + ", quantity=" + quantity + "]";
	}
	
}
