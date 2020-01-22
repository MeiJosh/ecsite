package controller;

import java.io.Serializable;

public class CalcRsBean implements Serializable {

	private double price;
	private String proname;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}


}
