<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a {
		text-decoration: none;
	}
	
	a.tou{
		border: 1px solid #ccc;
		padding: 10px;
		background-color: 192,192,192;
	}
	
	a.iti{
		border: 1px solid #ccc;
		padding: 10px;
		background-color: 192,192,192;
	}
	
	.buttonImage {
		display: inline-block;
		font-size: 0.8em;
		background-color: #eeeeee;
		border: solid 1px #333333;
		border-radius: 3px;
		color: #000000;
		width: fit-content;
		padding: 2px 5px;
		text-align: center;
		text-decoration: none;
		cursor: arrow;
	}
	
	.buttonImage:hover {
		background-color: #dddddd;
	}
</style>
</head>
<body>
	<%
	 List<String> list = (ArrayList<String>) request.getAttribute("message");
	if(list != null){
		for (String mess :list){
	%>
	
	<p><%= mess %></p>	
	
	<% 	
		}
	}
	%>
	<a href="Insert.html" class="tou"><span class="buttom">登録画面へ戻る</span></a>
	<a href="displayall" class="iti"><span class="buttom">一覧表示へ戻る</span></a>
</body>
</html>