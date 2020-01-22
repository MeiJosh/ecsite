<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <!-- 宣言==使えるようにする java.utilとかjavaの使いたい機能をimport -->

    <%@page import="java.util.*" %>
    <%@page import="controller.*" %>

    <%CateBean ctbean = new CateBean();
    LoginServlet login = new LoginServlet();
    ProductBean pbean = new ProductBean();//ProductBeanの内容を呼ぶ


    ArrayList<CateBean> a = (ArrayList<CateBean>)session.getAttribute("cate");
    ArrayList<ProductBean> pd = (ArrayList<ProductBean>)session.getAttribute("pdt");



  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<div align="center">
<h1>検索結果</h1>

<form action ="http://localhost:8080/shopsite/search" method="post">

<input type="text" name="keyword">
<br><br>
カテゴリ
<select name="category">
<option selected>選択してください</option>

<!-- for statementでselectのプルダウンとcat_idを一致させる -->
<% for (int i = 0; i<a.size(); i++){%>
<%ctbean = a.get(i); %>
<option value ="<%=ctbean.getCatid()%>"><%=ctbean.getCatname()%></option>
<%} %>

</select>
<br><br>
<input type="submit" value="検索">
</form>
</div>
<br>
<div>


<form  action ="http://localhost:8080/shopsite/product" method="post">
<table border="1" width="500" align = "center" >
<tr>
<th>商品名</th>
<th>価格</th>
<th>詳細ボタン</th>
</tr>


<%for (int j = 0; j < pd.size(); j++){ %>
<% pbean = pd.get(j); %>

<tr>
<td><%=pbean.getProname() %></td>
<td>&yen;<%=pbean.getProprice()%></td>
<td align = "center">
<input type = "submit" name="productcode" value ="<%=pbean.getProcd()%>"></td>
</tr>
<%} %>


</table>
</form>
</div>

</body>
</html>