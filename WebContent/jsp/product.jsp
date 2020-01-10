<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

<form action ="http://localhost:8080/shopsite/cart" method="post">
<div style="text-align:center">

<h1>商品紹介</h1>
</div>

<div>
<img border="0" src="pro_image" width="128" height="128" align="left" alt="イラスト1">
</div>


<div align="center">
<table border="1"width="300"  bordercolor="#333333">
<tr>
<td>商品名</td>
<th>pro_name</th>
</tr>

<tr>
<td>カテゴリ</td>
<th>cat_name</th>
</tr>

<tr>
<td>価格</td>
<th>pro_price</th>
</tr>

<tr>
<td>在庫</td>
<th>stock_no</th>
</tr>

<tr>
<td>商品紹介</td>
<th>pro_msg</th>
</tr>
</table>


<!-- <form action="http://localhost:8080" method="post"> -->


<select name="個数">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
</select>

<input type="submit" value="カートへ">
<input type="button" onclick="location.href='./search.jsp'" value="戻る">


</div>
</form>

</body>
</html>