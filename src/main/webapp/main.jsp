<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<%
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    String today = now.format(dateFormatter);
    String time = now.format(timeFormatter);
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Time Pop メイン</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<!-- ▼ 初期表示はJSPで出力 -->
<div class="sub-area">
    <p>
        <span id="today"><%= today %></span>　
        <span id="clock"><%= time %></span>
    </p>
    <input type="button" value="月報出力">
    <button onclick="showLogin()">ログイン</button>
</div>

<main>
    <!-- 略 -->
</main>


</body>
</html>
