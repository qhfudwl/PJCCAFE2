package cafe.pj.jvx330.web.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItemsCommand {
	long menuId;
	int quantity;
	
	public OrderItemsCommand() {
		
	}
}
