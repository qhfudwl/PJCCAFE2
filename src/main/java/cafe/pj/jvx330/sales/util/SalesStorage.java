package cafe.pj.jvx330.sales.util;

public class SalesStorage {
	
	private String totalDate;
	private int totalorder;
	private double totalAmount;
	private double totalusePoint;
	private double totalPrice;
	
	@Override
	public String toString() {
		return "SalesStorage [totalDate=" + totalDate + ", totalorder=" + totalorder + ", totalAmount=" + totalAmount
				+ ", totalusePoint=" + totalusePoint + ", totalPrice=" + totalPrice + "]";
	}


	public SalesStorage() {
		
	}
	

	public SalesStorage(String totalDate, int totalOrder, double totalAmount, double totalusePoint, double totalPrice) {
		this.setTotalorder(totalorder);
		this.totalAmount = totalAmount;
		this.totalusePoint = totalusePoint;
		this.totalDate = totalDate;
		this.setTotalPrice(totalPrice);
	}


	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalusePoint() {
		return totalusePoint;
	}

	public void setTotalusePoint(double totalusePoint) {
		this.totalusePoint = totalusePoint;
	}

	public String getTotalDate() {
		return totalDate;
	}

	public void setTotalDate(String totalDate) {
		this.totalDate = totalDate;
	}


	public int getTotalorder() {
		return totalorder;
	}


	public void setTotalorder(int totalorder) {
		this.totalorder = totalorder;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
