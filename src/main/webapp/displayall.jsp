<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import= "bean.GakuseiDataBean"%>
<%@ page import= "java.util.List"%>
<%@ page import= "java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!--==============レイアウトを制御する独自のCSSを読み込み===============-->
<link rel="stylesheet" type="text/css" href="https://coco-factory.jp/ugokuweb/wp-content/themes/ugokuweb/data/reset.css">
<link rel="stylesheet" type="text/css" href="https://coco-factory.jp/ugokuweb/wp-content/themes/ugokuweb/data/7-2-1/css/7-2-1.css">

<title>一覧</title>
<style>
	body{
		background-color: white;
	}

	/*検索窓のエリア*/
	#search-wrap {
		margin-left;
		z-index: 2;/*最前面に設定。数字は変更可*/
		top:10px;
		right:10px;
	}

	/*テキスト入力input設定*/
	#search-text{
		width: 45px;/*テキスト入力エリアが伸びる前の横幅*/
		height: 45px;
		padding: 20px;
		border: solid 1px;
		background:#fff url("https://coco-factory.jp/ugokuweb/wp-content/themes/ugokuweb/data/7-2-3/img/icon_search.svg") no-repeat 10px center;/*虫眼鏡アイコンを背景に表示*/
		background-size: 25px 25px;
		transition: all 0.5s;/*transitionを使ってスムースに伸ばす*/
		outline: black;
		cursor: pointer;/*カーソルを指マークに*/
	}

		/*テキスト入力inputにフォーカスした時の形状*/
		#search-text:focus {
		width: 250px;/*テキスト入力エリアが伸びる後の横幅*/
		padding: 20px 0 20px 60px;
		box-shadow: 0 2px rgba(6,0,1,.26);
	}
	
	button{
		width: 90px;
		padding: 5px;
		
	}
	
	.btn03{
		border: 1px solid #ccc;
		background: #f1e767;
		background: -webkit-gradient(linear, left top, left bottom, from(#fdfbfb), to(#ebedee));
		background: -webkit-linear-gradient(top, #fdfbfb 0%, #ebedee 100%);
		background: linear-gradient(to bottom, #fdfbfb 0%, #ebedee 100%);
		-webkit-box-shadow: inset 1px 1px 1px #fff;
		box-shadow: inset 1px 1px 1px #fff;
	}

	.btn03:hover {
		background: -webkit-gradient(linear, left bottom, left top, from(#fdfbfb), to(#ebedee));
		background: -webkit-linear-gradient(bottom, #fdfbfb 0%, #ebedee 100%);
		background: linear-gradient(to top, #fdfbfb 0%, #ebedee 100%);
	}
	
	
	h1{
		text-align:center;
		border-bottom: double 5px #FFC778;
		padding: 0.5em;/*文字周りの余白*/
		color: #010101;/*文字色*/
		background: #fff3ff;
	}
	table {
		margin: 3px auto;
		border-collapse: collapse;
		background-color: #cdeffd;
		table-layout: fixed;
		width:  60%;
	}
	
	table, th, td {
		border: solid 1px #000000;
		margin: auto;
		padding: 7px;
	}
	
	th, td {
		width:  100%;
	}
	
	.menu_style{
		width: 350px;
		height: 70px;
		margin-top: 5px;
		float: reft;
	}
	
	/*.textBox {
		position: relative;
		width: 100%;
		padding-top: 10px;
		height: 45px;
		display: inline-flex;
		margin-bottom: 3vh;
	}

	.textBox ::-webkit-input-placeholder { 
		color: #2B2B2B;
		font-size: 20px;
		font-weight: 200;
		font-family: 'Roboto', sans-serif;
	}
	*/
	
	.formarea {
		margin-left: 25px;
		text-align: center;
	}
	
	
</style>
</head>
<body>
<header>
	<h1>学生情報</h1>
</header>
<form class="formarea" method="get" action="select">
	<div class="menu_style">
	<div class="textbox">
		<input id="search-text"class="text" type="text" name ="keyword" placeholder=" 学生番号、氏名で検索">
		<button type="submit" name="submit" value="searchk" class="btn03">検索</button>
	</div>
	</div>
	<table>
		<tr>
			<th>選択</th>
			<th>学籍番号</th>
			<th>学生氏名（漢字）</th>
			<th>学生氏名（かな）</th>
		</tr>
			<%
		int cnt = 0;
		
		//-----受け取ったデータをテーブルに表示する
		List<GakuseiDataBean> data = (ArrayList) request.getAttribute("data");
		for (GakuseiDataBean bean : data) {
		%>
		<tr>
			<td><input type="radio" name="id" value="<%= bean.getId()%>" id ="radio<%= cnt %>"></td>
			<td><label for ="radio<%= cnt %>"><%= bean.getId()%></label></td>
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
		<button type="submit" name="submit" value="update" class="btn03 pushdown">編集</button>
		<button type="submit" name="submit" value="insert" class="btn03 pushdown">新規登録</button>
	</div>
</form>
</body>
</html>