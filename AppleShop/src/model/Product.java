package model;

public class Product {

	//attributes
	private String model; //e.g., iPad Pro 12.9
	private String finish; //e.g., Silver, Gold
	private int storage; //in unit of GB
	private boolean hasCellularConnectivity; //e.g., false (only wifi), true (wifi + cellular)
	private double originalPrice; 
	private double discountValue;
	
	public Product() {
		//does nothing
	}
	
	public Product(String model, double originalPrice) {
		this.model = model;
		this.originalPrice = originalPrice;
	}
	
	//accessor
	public String getModel() {
		return this.model;
	}
	
	//mutator
	public void setModel(String model) {
		this.model = model;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public int getStorage() {
		return storage;
	}

	public void setStorage(int storage) {
		this.storage = storage;
	}

	public boolean hasCellularConnectivity() {
		return hasCellularConnectivity;
	}

	public void setHasCellularConnectivity(boolean hasCellularConnectivity) {
		this.hasCellularConnectivity = hasCellularConnectivity;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}
	
	public double getPrice() {
		double price = 0.0;
		
		price = this.originalPrice - this.discountValue;
		
		return price;
	}
	
	public String toString() {
		String s = "";
		
		s += model + " " + finish + " " + storage + "GB " 
				+ "(cellular connectivity: " + hasCellularConnectivity + "): $(" 
					+ String.format("%.2f", originalPrice) + " - " + String.format("%.2f", discountValue) + ")"; 
		
		return s;
	}
}
