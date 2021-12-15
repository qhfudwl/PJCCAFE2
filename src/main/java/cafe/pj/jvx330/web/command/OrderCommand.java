package cafe.pj.jvx330.web.command;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderCommand {
	public String usePoint;
	private CustomerCommand customer;
	private List<OrderItemsCommand> nowOrder;

}
