package controller;

import java.io.Serializable;
import java.util.ArrayList;

public class CateBean implements Serializable {

	private int catid;
	private ArrayList<String> catname = new ArrayList<String>();


	public int getCatid() {
		return catid;
	}
	public void setCatid(int catid) {
		this.catid = catid;
	}
	public ArrayList<String> getCatname() {
		return catname;
	}
	public void setCatname(String string) {
		this.catname.add (string);
	}



}

