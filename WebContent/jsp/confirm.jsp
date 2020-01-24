<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <%@page import="java.util.*" %>
    <%@page import="controller.*" %><!-- importするservlet, beanの入ったpackage-name -->

    <%
    DetailBean db = new DetailBean();
    ArrayList<DetailBean> gopd = (ArrayList<DetailBean>)session.getAttribute("detail");

    CartBean calc = new CartBean();
    ArrayList<CartBean> goca = (ArrayList<CartBean>)session.getAttribute("calc");


    String quant = (String)session.getAttribute("quantity");
    int times = Integer.parseInt(quant);
    double sumtax;
    double taxrate = 0.10;
    double totalsum;
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>



<div align="center">
<h1>購入してよろしいでしょうか？？</h1></div>

<table border="1" width = "500" align = "center" >

<tr>
<th>商品名</th>
<th>単価</th>
<th>数量</th>
</tr>
<!-- for statement -->
<%for (int j = 0; j < gopd.size(); j++){ %>
<% db = gopd.get(j); %>
<tr>
<td><%=db.getProname() %></td><td>&yen;<%=db.getProprice() %></td><td><%= times %></td>
</tr>
<%} %>
<!-- end for statement -->


<tr>
<td colspan=2 align="center">消費税</td>
<td><!-- for statement -->&yen;
<%for (int k =0; k<gopd.size(); k++){%>
<%=sumtax = (db.getProprice()) * taxrate * times%></td></tr>
<tr>
<td colspan=2 align="center">合計金額</td><td>&yen;
<%= totalsum=times*(sumtax+(db.getProprice()))%></td>
</tr>
<%} %>
</table>
<br><br>
<div align="center"><!-- back to search jsp -->
<form action="http://localhost:8080/shopsite/logout" method="post">

<input type="submit" name="nextpage" value="いいえ">


<input type ="submit" name="nextpage" value="はい">
<!-- </form> -->
</form>
</div>
</body>
</html>