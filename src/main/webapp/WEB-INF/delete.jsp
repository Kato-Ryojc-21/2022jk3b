<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "bean.GakuseiDataBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>削除の確認</title>
</head>
<body>
	<h1>データの削除</h1>
	<%
		GakuseiDataBean bean = (GakuseiDataBean) request.getAttribute("data");
	
	%>
	<table>
		<tr>
			<th>学生</th>
			<th>保護者</th>
		</tr>
		<tr>
			<th>学籍番号<%= bean.getId() %></th>
			<th></th>
		</tr>
		<tr>
			<th>在籍状態<%= bean.getId() %></th>
			<th></th>
		</tr>
		<tr>
			<th>在籍状態確定日<%= bean.getId() %></th>
			<th></th>
		</tr>
		<tr>
			<th>学生氏名（漢字）<%= bean.getName() %></th>
			<th>保護者氏名（漢字）<%= bean.getId() %></th>
		</tr>
		<tr>
			<th>学生氏名（かな）<%= bean.getName() %></th>
			<th>保護者氏名（かな）<%= bean.getId() %></th>
		</tr>
		<tr>
			<th>生年月日<%= bean.getId() %></th>
			<th></th>
		</tr>
		<tr>
			<th>本人郵便番号<%= bean.getName() %></th>
			<th>保護者郵便番号<%= bean.getId() %></th>
		</tr>
		<tr>
			<th>本人住所<%= bean.getName() %></th>
			<th>保護者住所<%= bean.getId() %></th>
		</tr>
		<tr>
			<th>本人電話番号<%= bean.getName() %></th>
			<th>保護者電話番号<%= bean.getId() %></th>
		</tr>
		<tr>
			<th>本人メールアドレス<%= bean.getName() %></th>
			<th>保護者メールアドレス<%= bean.getId() %></th>
		</tr>
	</table>
	<p>ID <%= bean.getId() %></p>
	<p>氏名<%= bean.getName() %></p>
	<p>このデータを削除しますか？</p>
	<form method="get" action="deletego">
		<input type="hidden" name="id" value="<%= bean.getId() %>">
		<button type="submit" name="check" value="1">削除する</button>
		<button type="submit" name="check" value="2">キャンセル</button>
	</form>
</body>
</html>