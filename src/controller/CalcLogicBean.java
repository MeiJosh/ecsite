package controller;

public class CalcLogicBean {

	 public CalcRsBean calc(double price){//値が入っているbeanを開いた
	//jdbcからリストもらってくる
	//消費税10%==(*0.1)==tax
	CalcRsBean crb = new CalcRsBean();
	double bclc = crb.getPrice();

	//System.out.println(bclc+"getしてきた");
	double taxrate = 0.10;

	double tax = bclc * taxrate;
	//System.out.println(tax);

	double total = tax + bclc;
	//System.out.println(total+"totalだした");

	crb.setPrice(total);
	//System.out.println(crb.getPrice()+"トータルでbeanに入れなおしたやつを新しくget");

	return crb;//閉じる
}
}
