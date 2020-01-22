package controller;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductBean implements Serializable {


	private int procd;
	private String proname;
	private int proprice;
//	private int catid;
//	private String catname;
//	private String image;
//	private String message;

	private ArrayList<String> product = new ArrayList<String>();



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
	public ArrayList<String> getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product.add(product);
		}
//	public int getCatid() {
//		return catid;
//	}
//	public void setCatid(int catid) {
//		this.catid = catid;
//	}
//	public String getCatname() {
//		return catname;
//	}
//	public void setCatname(String catname) {
//		this.catname = catname;
//	}
//	public String getImage() {
//		return image;
//	}
//	public void setImage(String image) {
//		this.image = image;
//	}
//	public String getMessage() {
//		return message;
//	}
//	public void setMessage(String message) {
//		this.message = message;
//	}



}
