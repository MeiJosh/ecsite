package controller;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductBean implements Serializable {


	private String procd;
	private String proname;
	private String proprice;
	private int catid;
	private String catname;
	private String image;
	private String message;
	private String stockno;

	private ArrayList<String> product = new ArrayList<String>();


	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
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
	public void setProduct(ArrayList<String> product) {
		this.product = product;
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
	public ArrayList<String> getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product.add(product);
		}




}
