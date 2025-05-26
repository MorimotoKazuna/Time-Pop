<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, java.util.List" %>

<% 
// セッションスコープに保存された情報を取得
User loginUser = (User) session.getAttribute("loginUser");
// アプリケーションスコープに保存されたユーザーリストを取得
List<User> userList = (List<User>)request.getAttribute("userList");
// リクエストスコープに保存されたエラーメッセージを取得
//String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出退勤登録</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
<!--Google Fonts-->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Noto+Sans+JP:wght@100..900&family=Zen+Kaku+Gothic+Antique&family=Zen+Kaku+Gothic+New&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
<!-- CDN 読み込み -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="js/script.js" defer></script>
</head>
<body>
    <header>
        <div class="head-into">
            <p>ロゴ</p>
			<p>出退勤登録</p>
        </div>
        <div class="head-into">
            <input type="button" value="月報出力" class="header-btn">
            <button onclick="showLogin()" class="header-btn">ログイン</button>
            <a href="Logout"><input type="button" value="終了"></a>
        </div>
    </header>
    <div class="sub-area">
        <p id="datetime"></p>

    </div>
    <main>
        <div class="member">

            <table>
                <tr>
                    <th>利用者番号</th>
                    <th>名前</th>
                </tr>

            <!-- ▼データベースから情報を取ってきて、利用者番号昇順で表示 -->
			<% for(User user : userList) { 
			     String nameFurigana = user.getNameFurigana().replace("'", "\\'");
			%>
			    <tr>
			        <td><%= user.getId() %></td>
			        <td>
			            <button onclick="showWork(<%= user.getId() %>, '<%= nameFurigana %>')">
			                <%= user.getNameFurigana() %>
			            </button>
			        </td>
			    </tr>
			<% } %>           
			 <!-- ▲ここまで -->
            </table>
        </div>

    </main>
</body>
</html>