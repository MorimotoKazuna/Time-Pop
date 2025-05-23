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
<body>
<div class="main">
	<div class="main2 subtitle">
		<h1>Time Popログイン</h1>
	</div>
	<div class="main2">
		<% if (loginUser != null) { %>
			<p>ログインに成功しました</p>
			<p>ようこそ</p>
			<a href="Main"><input type="button" value="出退勤登録画面へ"></a>
		<% } else {%>
			<p>ログインに失敗しました</p>
			<a href="index.jsp"><input type="button" value="TOPへ"></a>
		<% } %>
	</div>
</div>
</body>
</html>