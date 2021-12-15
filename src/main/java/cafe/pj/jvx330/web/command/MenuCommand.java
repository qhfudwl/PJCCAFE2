package cafe.pj.jvx330.web.command;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString
public class MenuCommand {
	private long id;
	private char menuType;
	private String menuName;
	private String menuPrice;
	private boolean stock;
	private String imgPath;
	
	public MenuCommand() {
		
	}
	
	public MenuCommand(long id, String menuName, String menuPrice) {
		this.id = id;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public char getMenuType() {
		return menuType;
	}
	public void setMenuType(char menuType) {
		this.menuType = menuType;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}
	public boolean isStock() {
		return stock;
	}
	public void setStock(boolean stock) {
		this.stock = stock;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
