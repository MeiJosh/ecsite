<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@page import="java.util.*" %>
    <%@page import="controller.*" %><!-- importするservlet, beanの入ったpackage-name -->

    <%
    DetailBean db = new DetailBean();//入ってるのproname,catname,price,stock#,message
    ArrayList<DetailBean> gopd = (ArrayList<DetailBean>)session.getAttribute("detail");

    CartBean calc = new CartBean();//入ってるのproname,price,
    ArrayList<CartBean> goca = (ArrayList<CartBean>)session.getAttribute("calc");

  	//入ってるのproname,price,proname,catname,price,stock#,message
    ArrayList<DetailBean> cartadd =(ArrayList<DetailBean>)session.getAttribute("cartadd");
  	String er = (String)session.getAttribute("error");

    String quant = (String)session.getAttribute("quantity");
    int total = ((Integer)(session.getAttribute("totalprice"))).intValue();
    int tax = ((Integer)(session.getAttribute("tax"))).intValue();

    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



</head>
<body>
<form action="http://localhost:8080/shopsite/confirm" method="post">
<div align="center">
<h1>カート</h1></div>

<table border="1" width = "500" align = "center" >

<tr>
<th>商品名</th>
<th>単価</th>
<th>数量</th>
</tr>
<!-- for statement -->
<%for (int j = 0; j < goca.size(); j++){ %>
<% calc = goca.get(j); %>
<tr>
<td><%=calc.getName() %></td>
<td>&yen;<%=calc.getPrice() %></td>
<td><%=calc.getQuantity() %></td>
</tr>
<%} %>
<!-- end for statement -->


<tr>
<td colspan=2 align="center">消費税</td>
<td>&yen;<%=tax%></td></tr>
<tr>
<td colspan=2 align="center">合計金額</td>
<td>&yen;<%= total%></td>
</tr>

</table>
<br><br>
<div align="center">
<!-- back to search jsp -->
<input type="submit" name="nextpage" value="買い物を続ける">
<input type ="submit" name="nextpage" value="購入">
<!-- </form> -->
</div>
</form>

</body>
</html>