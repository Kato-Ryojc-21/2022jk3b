<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "bean.GakuseiDataBean"%>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧</title>
<style>
	table {
		text-align: center;
		border-collapse: collapse;
	}
	
	table, th, td {
		border: solid 1px #000000;
	}
	
	th, td {
		padding: 5px;
	}
	
	.formarea {
		margin-left: 30px;
	}
	
	.buttonarea {
	
		margin-top: 20px;
	}
</style>
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
			<th>ID</th>
			<th>在籍状態</th>
			<th>在籍状態確定日</th>
			<th>学生氏名（漢字）</th>
			<th>学生氏名（かな）</th>
			<th>生年月日</th>
			<th>本人郵便番号</th>
			<th>本人住所</th>
			<th>本人電話番号</th>
			<th>本人メールアドレス</th>
			<th>保護者氏名（漢字）</th>
			<th>保護者氏名（かな）</th>
			<th>保護者郵便番号</th>
			<th>保護者住所</th>
			<th>保護者電話番号</th>
			<th>保護者メールアドレス</th>
			
			<%
		int cnt = 0;
		
		//-----受け取ったデータをテーブルに表示する
		List<GakuseiDataBean> data = (ArrayList) request.getAttribute("data");
		for (GakuseiDataBean bean : data) {
			cnt++;
			
			int joutai = 0;
			if( bean.getJoutai() == "在学"){
				joutai = 0;
			} else if (bean.getJoutai() == "休学"){
				joutai = 1;
			} else if (bean.getJoutai() == "退学"){
				joutai = 2;
			} else if (bean.getJoutai() == "除籍"){
				joutai = 3;
			}
		
		%>
		<tr>
			<td><input type="radio" name="id" value="<%= bean.getId()%>" id ="radio<%= cnt %>"></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getId()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= joutai%></label>
			<td><label for ="radio<%= cnt %>"><%= bean.getName()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getHuri()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getId()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getName()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getHuri()%></label></td>
		</tr>
		<%  //-----繰り返しを閉じるところ
		}%>
	</table>
	<div class="buttonarea">
		<button type="submit" name="submit" value="delete">削除</button>
		<button type="submit" name="submit" value="insert">新規登録</button>
	</div>
</form>
</body>
</html>