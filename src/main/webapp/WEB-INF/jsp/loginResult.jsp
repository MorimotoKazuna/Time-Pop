<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>

<%
// セッションスコープからユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Time Pop</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"> 
<!--Google Fonts-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Noto+Sans+JP:wght@100..900&family=Zen+Kaku+Gothic+Antique&family=Zen+Kaku+Gothic+New&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
</head>
<body class="login-result">
	<div class="square-container">
		<div class="main2 subtitle">
			<h1>Time Pop</h1>
		</div>
		<div class="main3">
			<% if (loginUser != null) { %>
				<p class="result-word">ログインに成功しました</p>
				<p class="result-word-sub">おはようございます</p>
				<a href="Main"><input type="button" value="出退勤登録画面へ"  class="index-btn"></a>
			<% } else {%>
				<p class="result-word">ログインに失敗しました</p>
				<a href="index.jsp"><input type="button" value="TOPへ"  class="index-sndbtn"></a>
			<% } %>
		</div>
	</div>
</body>
</html>