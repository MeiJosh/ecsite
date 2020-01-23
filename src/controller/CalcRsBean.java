package controller;

import java.io.Serializable;

public class CalcRsBean implements Serializable {

	private int price;
	private String proname;
	private String quantity;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity= (quantity);
	}


}
