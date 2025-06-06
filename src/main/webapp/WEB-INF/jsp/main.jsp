<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <header class="main-header">
        <div class="head-into">
            <p class="logo">Time Pop</p>
			<p>出退勤登録</p>
        </div>
        <div class="head-into">
	        <button onclick="showMonthlyReportDialog()" class="header-btn">月報出力</button>
            <button onclick="showLogin()" class="header-btn">ログイン</button>
            <a href="Logout"><input type="button" value="終了" class="header-btn-out"></a>
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
                    <th>お名前</th>
                    <th>出勤時間</th>
                    <th>退勤時間</th>
                </tr>

            <!-- ▼データベースから情報を取ってきて、利用者番号昇順で表示 -->
			<% for(User user : userList) { 
			     String nameFurigana = user.getNameFurigana().replace("'", "\\'");
			     // todayStatusをつかって、出勤しているかのtrue/falseの情報をstatusに格納
			     Boolean status = todayStatus.get(user.getId());
			     // nullをチェックしながら、statusがtrueだった場合、 isDisabledにtrueを返す（出勤済みだったらボタンを無効にするための判定）
			     boolean isDisabled = status != null && status;
			     
			     // timeMapからそのユーザーidをもつユーザーの出退勤時間（配列）を取り出す
			     // times[0]=clockIn times[1]=clockOut
			     // なければtimesはnull
			     String[] times = timeMap.get(user.getId());
			     // timesがnullではない、かつ出勤時間も記録されていたらそれを使う、なければ"_"と表示
			     String clockIn = (times != null && times[0] != null) ? times[0] : "-";
			     // 上記の退勤時間ver.
			     String clockOut = (times != null && times[1] != null) ? times[1] : "-";
			%>
			    <tr>
			        <td><%= user.getId() %></td>
			        <td>
                        <div class="button_solid004">
                            <a href="#"
                                onclick="showWork(<%= user.getId() %>, '<%= nameFurigana %>', <%= status != null && status ? "true" : "false" %>)"
                                <%= status != null && status ? "disabled class='disabled-btn'" : "" %>>
                                <%= user.getNameFurigana() %>
                            </a>
                        </div>
			        </td>
			        <td><%= clockIn %></td>
			        <td><%= clockOut %></td>
			    </tr>
			<% } %>           
			 <!-- ▲ここまで -->
            </table>
        </div>

    </main>
<script>
  const users = [
    <% if (userList != null) {
         for (int i = 0; i < userList.size(); i++) {
             User u = userList.get(i); %>
      {
        id: <%= u.getId() %>,
        name: "<%= u.getNameFurigana() %>"
      }<%= (i != userList.size() - 1) ? "," : "" %>
    <%   }
       } %>
  ];
</script>
</body>
</html>