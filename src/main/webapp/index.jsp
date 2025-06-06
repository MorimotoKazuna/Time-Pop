<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body class="index-page">
<div id="currentDateTime"></div>
<div class="container">
	<div  id="current-time" class="left-panel">
		<h1>Time Pop</h1>
		<p class="h1sub">ようこそ</p>
		<p class="description">事業所利用者様用 出退勤管理アプリ</p>
	</div>
	<div class="right-panel">
		<h2>管理者ログイン</h2>
		<div class="index-login">
				<form action="Login" method="post"  >
					<label for="email">メールアドレス</label>
					<input type="email" id="email" name="email" placeholder="●●●●@example.com">
					
					<label for="password">パスワード</label>
					<div class="input-wrapper">
						<input type="password" id="password" name="password" placeholder="12345（数字）">					
						<span onclick="togglePassword()">
						<img id="togglePasswordIcon" src="<%= request.getContextPath() %>/image/icons8-目に見える-16.png" class="toggle-password">
						</span>
					</div>
					<input type="submit" value="ログイン" class="index-btn">
				</form>
				<div>
					<button type="button" class="index-sndbtn" onclick="newRegisterAdmin()">新規管理者登録</button>
				</div>
		</div>
	</div>
</div>
<script src="<%= request.getContextPath() %>/js/script.js" defer></script>
</body>
</html>