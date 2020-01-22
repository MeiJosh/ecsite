<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!-- 宣言==使えるようにする java.utilとかjavaの使いたい機能をimport -->

    <%@page import="java.util.*" %>
    <%@page import="controller.*" %><!-- importするservlet, beanの入ったpackage-name -->

    <!-- importを宣言して初めて、java要素のbean, ArrayListの宣言が有効化される -->
    <%CateBean ctbean = new CateBean();
    LoginServlet login = new LoginServlet();
    ArrayList<CateBean> listcategory = new ArrayList<CateBean>();
    ArrayList<CateBean> a = (ArrayList<CateBean>)session.getAttribute("cate");
    String er = (String)request.getAttribute("error");
    //ctbean = a.get(i)
  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

<div align="center">
<form action ="http://localhost:8080/shopsite/search" method="post">

<%if(er!=null){%>
<%=er %>
<%} %><br><br>
<br><br>
<h1>検索</h1>

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

</body>
</html>