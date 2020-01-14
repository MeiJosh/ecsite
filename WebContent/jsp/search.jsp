<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>

<div align="center">
<form action ="http://localhost:8080/shopsite/search" method="post">
<h1>検索</h1>

<input type="search" name="search">
<br><br>
カテゴリ
<select name="category">
<option value="0">選択してください</option>
<option value="1">家電</option>
<!-- queryの実行 -->

<option value="2">パソコン用品</option>
<!-- query2の実行 -->

<option value="3">書籍</option>
<!-- query3の実行 -->

</select>

<br><br>
<input type="submit" value="検索">
<!-- 検索すると表とデータを表示する -->


<!-- 詳細でproducr.jspに飛ぶ -->
<input type="button" onclick="location.href='./product.jsp'" value="詳細">
</form>
</div>


</body>
</html>