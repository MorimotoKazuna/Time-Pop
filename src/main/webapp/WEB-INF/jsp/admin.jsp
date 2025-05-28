<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="model.User, java.util.List, java.util.Map" %>
<% 
// セッションスコープに保存された情報を取得
User loginUser = (User) session.getAttribute("loginUser");
// アプリケーションスコープに保存されたユーザーリストを取得
List<User> userList = (List<User>)request.getAttribute("userList");
// リクエストスコープに保存されたエラーメッセージを取得
//String errorMsg = (String)request.getAttribute("errorMsg");
// todayStatusを受け取り、使う
Map<Integer, Boolean> todayStatus = (Map<Integer, Boolean>) request.getAttribute("todayStatus");
// timeMapを受け取り、使う
Map<Integer, String[]> timeMap = (Map<Integer, String[]>) request.getAttribute("timeMap");

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
    <script>
	    const users = <%= new com.google.gson.Gson().toJson(userList) %>;
	</script>	
</head>
<body>

    <button onclick="registerUser()">利用者登録</button><br>
    <button onclick="showStateChangeDialog()">利用者状態変更</button>


    <form action="Main" method="get">
	    <button type="submit">戻る</button>
	</form>
    

<script>
  const users = [
    <% for (int i = 0; i < userList.size(); i++) {
         User u = userList.get(i); %>
      {
        id: <%= u.getId() %>,
        name: "<%= u.getNameFurigana() %>"
      }<%= (i != userList.size() - 1) ? "," : "" %>
    <% } %>
  ];
</script>
</body>
</html>