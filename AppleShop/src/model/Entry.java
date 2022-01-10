package model;

public class Entry {
	private String serialNumber;
	private Product product;
	
	public Entry(String serialNumber, Product product) {
		this.serialNumber = serialNumber;
		this.product = product;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void setProduct(String model, double originalPrice) {
		this.product = new Product(model, originalPrice);
	}
	
	public String toString() {
		return "[" + this.serialNumber + "]" + " " + this.product.toString();
	}
}
