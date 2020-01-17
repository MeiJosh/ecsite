package controller;

import java.io.Serializable;

public class ProductBean implements Serializable {


	private int procd;
	private String proname;
	private int proprice;


	public int getProcd() {
		return procd;
	}
	public void setProcd(int i) {
		this.procd = i;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public int getProprice() {
		return proprice;
	}
	public void setProprice(int i) {
		this.proprice = i;
	}


}
