<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
    body {
      display: flex;
      justify-content: center; /* 横中央 */
      align-items: center;     /* 縦中央 */
      height: 100vh;           /* 画面高さに合わせる */
      margin: 0;               /* デフォルト余白リセット */
      /* 他のスタイルあればここに追加 */
    }
  </style>
<meta charset="UTF-8">
<title>Time Pop</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css"> 
<!--Google Fonts-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Noto+Sans+JP:wght@100..900&family=Zen+Kaku+Gothic+Antique&family=Zen+Kaku+Gothic+New&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
<script src="<%= request.getContextPath() %>/js/script.js" defer></script>
</head>
<body>
<div class="main">
	<div  id="current-time" class="main2 subtitle">
		<h1>Time Pop</h1>
		<h2>管理者ログイン</h2>
	</div>
	<div class="main2">
		<form action="Login" method="post">
			<input type="email" name="email" placeholder="メールアドレス"><br>
			<input type="text" name="password" placeholder="パスワード"><br>
			<input type="submit" value="ログイン" class="header-btn">
		</form>
		<form action="${pageContext.request.contextPath}/NewAdminPage" method="get">
			<input type="submit" value="新規管理者登録" class="header-btn">
		</form>
	</div>
</div>
</body>
</html>