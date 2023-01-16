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
	h1{
		text-align:center;
		border-bottom: double 5px #FFC778;
		padding: 0.5em;/*文字周りの余白*/
		color: #010101;/*文字色*/
		background: #fff3ff;
	}
	table {
		text-align: center;
		border-collapse: collapse;
		background-color: #cdefff;
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
	<button type="submit" name="submit" value="syosai">詳細表示</button>
	
	<input type="text" name ="keyword">
	<button type="submit" name="submit" value="searchk">漢字検索</button>
	<input type="text" name ="keyword">
	<button type="submit" name="submit" value="searchh">ひらがな検索</button>
	
	<table>
		<tr>
			<th>選択</th>
			<th>学籍番号</th>
			<th>在籍状態</th>
			<th>学生氏名（漢字）</th>
			<th>学生氏名（かな）</th>
		</tr>
			<%
		int cnt = 0;
		
		//-----受け取ったデータをテーブルに表示する
		List<GakuseiDataBean> data = (ArrayList) request.getAttribute("data");
		for (GakuseiDataBean bean : data) {
			cnt++;
			
			if( bean.getJoutai() == "在学"){
				int joutai = 0;
			} else if (bean.getJoutai() == "休学"){
				int joutai = 1;
			} else if (bean.getJoutai() == "退学"){
				int joutai = 2;
			} else if (bean.getJoutai() == "除籍"){
				int joutai = 3;
			}
		
		%>
		<tr>
			<td><input type="radio" name="id" value="<%= bean.getId()%>" id ="radio<%= cnt %>"></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getId()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getJoutai()%></label>
			<td><label for ="radio<%= cnt %>"><%= bean.getGakusei_name()%></label></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getGakusei_nameH()%></label></td>
		</tr>
		<%  //-----繰り返しを閉じるところ
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
		<button type="submit" name="submit" value="insert">新規登録</button>
	</div>
</form>
</body>
</html>