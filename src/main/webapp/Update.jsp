<%@page import="bean.GakuseiDataBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	h1 {
		border-bottom: double 5px #FFC778;
		padding: 0.5em;/*文字周りの余白*/
		color: #010101;/*文字色*/
		background: #fff3ff;
		text-align: center;
	}
	table {
		margin: 3px auto;
		border-collapse: collapse;
		background-color: #cdeffd;
		table-layout: fixed;
		width:  50%;
	}
	
	table, th, td {
		border: solid 1px #000000;
		margin: auto;
		padding: 7px;
	}
	
	th, td {
		width:  100%;
	}
	
	p{
		text-align: left;
	}
	
	.button{
		margin-top: 20px;
	}
	
	.formarea {
		margin-left: 25px;
		text-align: center;
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
	
</style>
</head>
<body>
	<%
	//データの取得
	GakuseiDataBean bean = (GakuseiDataBean)request.getAttribute("data");
	if(bean == null){
		response.sendRedirect("displayall");
		return;
	}
	%>
	<h1>データ編集ページ</h1>
	<form class="formarea" method="get" action="updatego">
	<table>
		<tr>
			<th>学生用</th>
			<th>保護者用</th>
		</tr>
		<tr>
			<th><p>学生番号<%=bean.getId() %></p></th>
			
		</tr>
		<tr>
			<th><p>在籍状態<select name="joutai">
					<option><%=bean.getJoutai()%></option>
					<option>在学</option>
					<option>休学</option>
					<option>退学</option>
					<option>除籍</option>
					</select>
			</p></th>
			<th></th>
		</tr>
		<tr>
			<th><p>在籍状態確定日<input type="text" name="kakuteibi" value="<%=bean.getKakuteibi()%>"></p></th>
			<th></th>
		</tr>
		<tr>
			<th><p>学生氏名（漢字）<input type="text" name="gakusei_name" value="<%=bean.getGakusei_name() %>"></p></th>
			<th><p>保護者氏名（漢字）<input type="text" name="hogosya_name" value="<%=bean.getHogosya_name() %>"></p></th>
		</tr>
		<tr>
			<th><p>学生氏名（かな）<input type="text" name="gakusei_nameH" value="<%=bean.getGakusei_nameH() %>"></p></th>
			<th><p>保護者氏名（かな）<input type="text" name="hogosya_nameH" value="<%=bean.getHogosya_nameH() %>"></p></th>
		</tr>
		<tr>
			<th><p>生年月日<input type="text" name="born" value="<%=bean.getBorn() %>"></p></th>
			<th> </th>
		</tr>
		<tr>
			<th><p>本人郵便番号<input type="text" name="yuubinnbanngou" value="<%=bean.getYuubinnbanngou() %>"></p></th>
			<th><p>保護者郵便番号<input type="text" name="hogosya_yuubinnbanngou" value="<%=bean.getHogosya_yuubinnbanngou() %>"></p></th>
		</tr>
		<tr>
			<th><p>本人住所<input type="text" name="gakusei_juusyo" value="<%=bean.getGakusei_juusyo()%>"></p></th>
			<th><p>保護者住所<input type="text" name="hogosya_juusyo" value="<%=bean.getHogosya_juusyo()%>"></p></th>
		</tr>
		<tr>
			<th><p>本人電話番号<input type="text" name="gakusei_phone" value="<%=bean.getGakusei_phone()%>"></p></th>
			<th><p>保護者電話番号<input type="text" name="hogosya_phone" value="<%=bean.getHogosya_phone()%>"></p></th>
		</tr>
		<tr>
			<th><p>本人メールアドレス<input type="text" name="gakusei_mail" value="<%=bean.getGakusei_mail()%>"></p></th>
			<th><p>保護者メールアドレス<input type="text" name="hogosya_mail" value="<%=bean.getHogosya_mail()%>"></p></th>
		</tr>
	</table>
		<input type="hidden" name="id" value="<%=bean.getId() %>">
		<div class="button">
			<button type="submit" name="submit" value="1" class="btn03 pushdown">変更</button>
			<button type="submit" name="submit" value="2" class="btn03 pushdown">キャンセル</button>
			<button type="reset" class="btn03">リセット</button>
		</div>
	</form>
</body>
</html>