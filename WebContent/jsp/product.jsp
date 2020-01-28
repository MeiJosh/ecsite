<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="java.util.*" %>
    <%@page import="controller.*" %><!-- importするservlet, beanの入ったpackage-name -->

    <%

    DetailBean db = new DetailBean();
    ArrayList<DetailBean> gopd = (ArrayList<DetailBean>)session.getAttribute("detail");
    CateBean ct = new CateBean();
    ArrayList<CateBean> ctl = (ArrayList<CateBean>)session.getAttribute("cate");


    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>


<div style="text-align:center">

<h1>商品紹介</h1>
</div>



<%for (int j = 0; j < gopd.size(); j++){ %>
<% db = gopd.get(j); %><%ct = ctl.get(j);%>

<div>
<img border="0" src="image/<%=db.getImage() %>" width="300" height="300" align="left" alt="<%=db.getImage() %>">
</div>


<div align="center">
<table border="1" width="500">

<tr><td>商品名</td><td ><%=db.getProname() %></td></tr>

<tr><td>カテゴリ</td><td><%=ct.getCatname() %></td></tr>

<tr><td>価格</td><td>&yen;<%=db.getProprice() %></td></tr>

<tr><td>在庫</td><td><%=db.getStockno() %></td></tr>

<tr><td>商品紹介</td><td><%=db.getMessage() %></td></tr>


<%} %>

</table>
</div>


<!-- <form action="http://localhost:8080" method="post"> -->
<div align="center">
<form action ="http://localhost:8080/shopsite/cart" method="post">

<!-- hiddenで非表示にしてprocdをcart Servletに送信 -->
<input type="hidden" name="procd" value="<%=db.getProcd()%>">

<%int quant = Integer.parseInt(db.getStockno()); %>
個数
<select name="quantity"><!-- カート画面の数量とつなげる -->
<%for(int i = 1; i<=quant; i++) {%>
<option value=<%=i %>><%=i%></option>
<%} %>

</select>

<input type="submit"name="nextpage"  value="カートへ">

<input type="submit" name="nextpage" value="戻る">

</form>
</div>
</body>
</html>