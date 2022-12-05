<%@ page language= "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "bean.GakuseiDataBean"%>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧</title>
</head>
<body>
<header>
	<h1>学生情報</h1>
</header>
<form class="formarea" method="get" action="select">
	<input type="text" name ="keyword">
	<button type="submit" name="submit" value="searchk">漢字検索</button>
	<input type="text" name ="keyword">
	<button type="submit" name="submit" value="searchh">ひらがな検索</button>
	
	<table>
		<tr>
			<th>選択</th>
			<th>ID</th>
			<th>学生名（漢字）</th>
			<th>学生名（かな）</th>
			
			<%
		int cnt = 0;
		//-----受け取ったデータをテーブルに表示する
		List<GakuseiDataBean> data = (ArrayList) request.getAttribute("data");
		for (GakuseiDataBean bean : data) {
			cnt++;
		%>
		
		<tr>
			<td><input type="radio" name="id" value="<%= bean.getId()%>" id ="radio<%= cnt %>"></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getId()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getName()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getHuri()%></label></td>
		</tr>
		<%  //-----繰り返しを閉じるところ
		}%>
		
	</table>
</form>
</body>
</html>