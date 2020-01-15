package controller;

import java.io.Serializable;

public class DataBean implements Serializable  {
	//ログイン情報の入れとく場所
	private String logincd;
	private String loginpw;




	public String getLogincd() {
		return logincd;
	}
	public void setLogincd(String logincd) {
		this.logincd = logincd;
	}
	public String getLoginpw() {
		return loginpw;
	}
	public void setLoginpw(String loginpw) {
		this.loginpw = loginpw;
	}



}
