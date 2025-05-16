<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Mutter, java.util.List" %>

<% 
// セッションスコープに保存された情報を取得
User loginUser = (User) session.getAttribute("loginUser");
// アプリケーションスコープに保存されたつぶやきリストを取得
List<Mutter> mutterList = (List<Mutter>)request.getAttribute("mutterList");
// リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<!--Google Fonts-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Noto+Sans+JP:wght@100..900&family=Zen+Kaku+Gothic+Antique&family=Zen+Kaku+Gothic+New&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
</head>
<body>
<h1>どこつぶメイン</h1>
<p>
<%= loginUser.getName() %> さん、ログイン中<br>
<a href="Logout">ログアウト</a>
</p>
<p><a href="Main">更新</a></p>
<form action="Main" method="post">
<input type="text" name="text" placeholder="何をつぶやきますか？" >
<input type="submit" value="つぶやく">
</form>
<div class="toukou">
		<!--<% if (errorMsg != null ) { %>-->
		<!--<p><%= errorMsg %></p>-->
		<!--<% } %>-->
		<!--<% for (Mutter mutter : mutterList) {%>-->
		<!--<p><%= mutter.getUserName() %>：<%= mutter.getText() %></p>-->
		<!--<% } %>-->

  <% if (errorMsg != null ) { %>
    <p><%= errorMsg %></p>
  <% } %>

  <% for (Mutter mutter : mutterList) {
	  // 投稿が自分のものかの真偽を判定し、isMyPostに格納
       boolean isMyPost = mutter.getUserName().equals(loginUser.getName());
	  // isMyPostが真ならmy-postを、偽ならother-postを"cssClass"に代入
       String cssClass = isMyPost ? "my-post" : "other-post";
  %>
    <div class="mutter <%= cssClass %>">
      <p><strong><%= mutter.getUserName() %></strong><br><%= mutter.getText() %></p>
    </div>
  <% } %>
</div>
</body>
</html>