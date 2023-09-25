package GUI;
public class Order {
	private String order;

	
	public Order() {
		this.order = "";
	}
	
	public Order(String order) {
		this.order = order;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public String toString() {
		return this.order;
	}

}