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
	}
	table {
		border-collapse: collapse;
		background-color: #cdefff;
	}
	
	table, th, td {
		border: solid 1px #000000;
	}
	
	th, td {
		padding: 5px;
	}
	
	p{
		text-align: left;
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
	<form method="get" action="updatego">
	<table>
		<tr>
			<th>学生用</th>
			<th>保護者用</th>
		</tr>
		<tr>
			<th><p>学生番号 : <%=bean.getId() %></p></th>
			
		</tr>
		<tr>
			<th><p>在籍状態<input type="text" name="joutai" value="<%=bean.getJoutai()%>"></p></th>
			<th></th>
		</tr>
		<tr>
			<th><p>在籍状態確定日<input type="date" name="joutaiday" value="<%=bean.getKakuteibi()%>"></p></th>
			<th></th>
		</tr>
		<tr>
			<th><p>学生氏名（漢字） : <input type="text" name="simei" value="<%=bean.getGakusei_name() %>"></p></th>
			<th><p>保護者氏名（漢字）<input type="text" name="oyasimei" value="<%=bean.getHogosya_name() %>"></p></th>
		</tr>
		<tr>
			<th><p>氏名 : <input type="text" name="simei" value="<%=bean.getGakusei_nameH() %>"></p></th>
			<th><p>保護者氏名（かな）<input type="text" name="oyasimeih" value="<%=bean.getHogosya_nameH() %>"></p></th>
		</tr>
		<tr>
			<th><p>生年月日<input type="number" name="birthday" value="<%=bean.getBorn() %>"></p></th>
			<th> </th>
		</tr>
		<tr>
			<th><p>本人郵便番号<input type="number" name="yuubinbangou" value="<%=bean.getYuubinnbanngou() %>"></p></th>
			<th><p>保護者郵便番号<input type="number" name="oyayuubinbangou" value="<%=bean.getHogosya_yuubinnbanngou() %>"></p></th>
		</tr>
		<tr>
			<th><p>本人住所<input type="text" name="jusyo" value="<%=bean.getGakusei_juusyo()%>"></p></th>
			<th><p>保護者住所<input type="text" name="oyajusyo" value="<%=bean.getHogosya_juusyo()%>"></p></th>
		</tr>
		<tr>
			<th><p>本人電話番号<input type="text" name="phone" value="<%=bean.getGakusei_phone()%>"></p></th>
			<th><p>保護者電話番号<input type="text" name="oyaphone" value="<%=bean.getHogosya_phone()%>"></p></th>
		</tr>
		<tr>
			<th><p>本人メールアドレス<input type="text" name="mail" value="<%=bean.getGakusei_mail()%>"></p></th>
			<th><p>保護者メールアドレス<input type="text" name="oyamail" value="<%=bean.getHogosya_mail()%>"></p></th>
		</tr>
	</table>
		<input type="hidden" name="id" value="<%=bean.getId() %>">
		<button type="submit" name="submit" value="1">変更</button>
		<button type="submit" name="submit" value="2">キャンセル</button>
		<button type="reset">リセット</button>
	</form>
</body>
</html>