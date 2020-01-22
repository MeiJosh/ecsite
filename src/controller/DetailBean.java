package controller;

import java.io.Serializable;
import java.util.ArrayList;

public class DetailBean implements Serializable {

	private int procd;
	private String proname;
	private int proprice;
	private int stockno;
	private String image;
	private String message;

	private ArrayList<String> detail = new ArrayList<String>();


	public ArrayList<String> getDetail() {
		return detail;
	}
	public void setDetail(ArrayList<String> detail) {
		this.detail = detail;

	}
	public int getProcd() {
		return procd;
	}
	public void setProcd(int procd) {
		this.procd = procd;
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
	public void setProprice(int proprice) {
		this.proprice = proprice;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStockno() {
		return stockno;
	}
	public void setStockno(int stockno) {
		this.stockno = stockno;
	}

}
