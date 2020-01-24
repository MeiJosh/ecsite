package controller;

import java.io.Serializable;

public class CartBean implements Serializable {
	private String name;
	private int price;
	private int quantity;
	private int cd;

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCd() {
		return cd;
	}
	public void setCd(int cd) {
		this.cd = cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int i) {
		this.price = i;
	}
//	CartBean getCart(HttpServletRequest request) {
//		final String  attrId = "CartBean";
//
//		HttpSession session = request.getSession(true);
//		Object o = session.getAttribute(attrId);
//
//		if(o==null) {
//			final CartBean bean = new CartBean();
//			session.setAttribute(attrId, bean);
//			return bean;
//		}else {
//			return (CartBean) o;//cast
}

