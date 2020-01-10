<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Shopping site</title>
</head>
<body>

<form action ="http://localhost:8080/shopsite/login" method="post">

<h1>ログイン</h1>
<div>username
<input type = "text" name = "username"></div>
<div>password
<input type ="password" name="password"></div>
<dir>
<input type ="submit" value="LOGIN"></dir>


</form>
<!-- if below -->

<font color =#FF0000>

<!-- when session doesnt get open, nullが表示されるので-->


<%String msg =(String)request.getAttribute("errormsg");%>

<%if(msg!=null){%>
<%=msg %>
<%} %><br><br>

<%String noent =(String)request.getAttribute("noentry");%>

<%if(noent!=null){%>
<%=noent %>
<%} %><br><br>
</font>


</body>
</html>