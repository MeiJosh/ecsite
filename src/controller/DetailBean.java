package controller;

import java.io.Serializable;
import java.util.ArrayList;

public class DetailBean implements Serializable {

	private String procd;
	private String proname;
	private String proprice;
	private String stockno;
	private String image;
	private String message;

	private ArrayList<String> detail = new ArrayList<String>();


	public ArrayList<String> getDetail() {
		return detail;
	}
	public void setDetail(ArrayList<String> detail) {
		this.detail = detail;

	}
	public String getProcd() {
		return procd;
	}
	public void setProcd(String procd) {
		this.procd = procd;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getProprice() {
		return proprice;
	}
	public void setProprice(String proprice) {
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
	public String getStockno() {
		return stockno;
	}
	public void setStockno(String stockno) {
		this.stockno = stockno;
	}

}
