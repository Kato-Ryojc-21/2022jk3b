<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.GakuseiDataBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>一覧</title>
<style type="text/css">
table {
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

.linkStyle {
	display: inline-block;
	padding: 10px;
	color: #0000ff;
}

.noLinkStyle {
	display: line-block;
	padding: 10px;
	color: #999999;
}
</style>
</head>
<body>
	<header>
		<h1>データの編集</h1>
	</header>
	<main>
		<form class="formarea" method="get" action="select">

			<input type="text" name="keyword">
			<button type="submit" name="submit" value="search">検索</button>


			<table>
				<tr>
					<th>選択</th>
					<th>ID</th>
					<th>NAME</th>
				</tr>
				<%
				int cnt = 0;
				List<GakuseiDataBean> data = (ArrayList) request.getAttribute("data");
				for (GakuseiDataBean bean : data) {
					cnt++;
				%>
				<tr>
					<td><input type="radio" name="id" value="<%=bean.getId()%>"
						id="radio<%=cnt%>"></td>
					<td><label for="radio<%=cnt%>"><%=bean.getId()%></label></td>
					<td><label for="radio<%=cnt%>"><%=bean.getGakusei_name()%></td>
					<td><label for="radio<%=cnt%>"><%=bean.getGakusei_nameH()%></td>
				</tr>
				<%
				}
				%>
			</table>
			<!-- ページの表示をする -->
			<%
			//--- 現在のページを取得
			int currentPage = (Integer) request.getAttribute("page");
			//--- 全ページを取得
			int allPage = (Integer) request.getAttribute("allpage");
			//--- キーワードを取得
			String keyword = (String) request.getAttribute("keyword");
			if (keyword == null) {
				keyword = "";
			}
			//--- データが存在する場合にページを表示する
			if (allPage > 0) {
				//--- 現在のページの２ページ前を表示の最初とするが、なければ１を最初とする。
				int startPage = currentPage - 2;
				if (startPage < 1) {
					startPage = 1;
				}
				//--- 開始ページの４ページ後を表示の最後とするが、なければ最終ページとする。
				int endPage = startPage + 4;
				if (endPage > allPage) {
					endPage = allPage;
				}
				//---必ず５ページ分になるように、表示数の調整
				while (startPage > 1 && endPage - startPage < 4) {
					startPage--;
				}
				//---"前へ"を表示　ただし、現在ページが１ならリンクは設定しない
				if (currentPage > 1) {
			%>
			<a class="linkStyle"
				href="displayall?page=<%=(currentPage - 1)%> &keyword=<%=keyword%>">前へ</a>
			<%
			} else {
			%>
			<span class="noLinkStyle">前へ</span>
			<%
			}
			//---ページ表示　現在のページの場合はリンクは設定しない
			for (int i = startPage; i <= endPage; i++) {
			if (i == currentPage) {
			%>
			<span class="noLinkStyle"><%=i%></span>
			<%
			} else {
			%>
			<a class="linkStyle"
				href="displayall?page=<%=i%> &keyword=<%=keyword%>"><%=i%></a>
			<%
			}
			}
			//--- "次へ"の表示　ただし、現在のページが最終ページならリンクは設定しない
			if (currentPage < allPage) {
			%>
			<a class="linkStyle"
				href="displayall?page=<%=(currentPage + 1)%>&keyword=<%=keyword%>">次へ</a>
			<%
			} else {
			%>
			<span class="noLinkStyle">次へ</span>
			<%
			}
			}
			%>
			<div class="buttonarea">
				<button type="submit" name="submit" value="update">編集</button>
			</div>
		</form>
	</main>
</body>
</html>