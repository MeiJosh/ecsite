<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


    <!-- 宣言==使えるようにする java.utilとかjavaの使いたい機能をimport -->

    <%@page import="java.util.*" %>
    <%@page import="controller.*" %>

    <%ProductBean pbean = new ProductBean();
    CateBean ctbean = new CateBean();
    ArrayList<ProductBean> listproduct = new ArrayList<ProductBean>();
    listproduct = (ArrayList<ProductBean>)session.getAttribute("product");
    ArrayList<CateBean> listcategory = new ArrayList<CateBean>();
    listcategory = (ArrayList<CateBean>)session.getAttribute("category");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

<h1>検索結果</h1>
<div align="center">
<form action ="http://localhost:8080/shopsite/search" method="post">

<input type="text" name="keyword">
<br><br>
カテゴリ
<select name="category">
<option selected></option>

<!-- for statementでselectのプルダウンとcat_idを一致させる -->
<% for (int i = 0; i<listcategory.size(); i++){%>
<%ctbean = listcategory.get(i); %>
<option value ="<%=ctbean.getCatid() %>"><%=ctbean.getCatname()%>
</option>
<%} %>

</select>

<br><br>
<input type="submit" value="検索">
</form>
</div>

<div>

<table>
<tr>
<th>商品名</th>
<th>価格</th>
<th>詳細ボタン</th>
</tr>

<%for (int i = 0; i<listproduct.size(); i++){ %>
<%pbean =listproduct.get(i); %>
<%} %>
</table>
</div>

</body>
</html>