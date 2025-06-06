<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="model.User, java.util.List, java.util.Map" %>
<% 
User loginUser = (User) session.getAttribute("loginUser");
String userName = loginUser != null ? loginUser.getName() : "ゲスト";
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者画面</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS読み込み -->
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/style.css">

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Noto+Sans+JP:wght@100..900&family=Zen+Kaku+Gothic+Antique&family=Zen+Kaku+Gothic+New&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">

    <!-- CDN 読み込み -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="js/script.js" defer></script>
    <script>
        function setPressedTime() {
            const now = new Date().toISOString();
            document.getElementById("createdAt").value = now;
        }
    </script>
</head>
<body class="admin-page">
<span id="dateTime"></span>
	<script> const users = JSON.parse('<%= request.getAttribute("userJson") %>');</script>
    <header class="admin">
        <div class="air">
            <img src="image/s-humanicon.png" alt="ユーザーアイコンイメージ" class="admin-headerimg">
            <span><%= userName %> さんログイン中</span>
        </div>
        <p class="page-name">登録・変更・修正</p>
        <form action="Main" method="get">
            <button type="submit" class="admin-headerimg">
                <img src="image/s-out-w.png" title="ログアウトし出退勤登録画面へ">
            </button>
        </form>
    </header>
    
    <div class="admin-container">
        <div class="panel-name">利用者設定</div>
        <div class="user-panel">
            <button onclick="registerUser()" class="admin-page-btn">
                <img src="image/s-plushuman.png" alt="新規ユーザー登録イメージ">
                <span>新規利用者登録</span>
            </button>
            <button onclick="showStateChangeDialog()" class="admin-page-btn">
                <img src="image/s-humanup-w.png" alt="ユーザー情報変更イメージ">
                <span>利用者情報変更</span>
            </button>
            <button class="admin-page-btn">
                <img src="image/s-writeup-w.png" alt="書類書き換えイメージ">
                <span>出退勤記録修正</span>
            </button>
        </div>
        
        <div class="panel-name">管理者設定</div>
        <div class="admin-panel">
            <button type="button" class="admin-page-btn" onclick="newRegisterAdmin()">
                <img src="image/s-plushuman.png" alt="新規ユーザー登録イメージ">
                <span>新規管理者登録</span>
            </button>
            <button type="button" class="admin-page-btn" onclick="showStateChangeDialogAdmin()">
                <img src="image/s-humanup-w.png" alt="ユーザー情報変更イメージ">
                <span>管理者情報変更</span>
            </button>
        </div>
    </div>
</body>
</html>
