<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
<body>

    <button onclick="registerUser()">利用者登録</button><br>
    <button onclick="deleteUser()">利用者登録削除</button>
    <form action="Main" method="get">
	    <button type="submit">戻る</button>
	</form>
    


</body>
</html>