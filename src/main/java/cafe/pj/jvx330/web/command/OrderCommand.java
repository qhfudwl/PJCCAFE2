package cafe.pj.jvx330.web.command;

import java.util.List;


public class OrderCommand {
	private String usePoint;
	CustomerCommand customer;
	List<OrderItemsCommand> nowOrder;
	
	
	public String getUsePoint() {
		return usePoint;
	}
	public void setUsePoint(String usePoint) {
		this.usePoint = usePoint;
	}
	public List<OrderItemsCommand> getNowOrder() {
		return nowOrder;
	}
	public void setNowOrder(List<OrderItemsCommand> nowOrder) {
		this.nowOrder = nowOrder;
	}
	@Override
	public String toString() {
		return "OrderCommand [usePoint=" + usePoint + ", nowOrder=" + nowOrder + "]";
	}
	
}
