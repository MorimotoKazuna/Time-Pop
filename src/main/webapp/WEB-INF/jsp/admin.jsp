<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理者画面</title>
    <script>
        function setPressedTime() {
            const now = new Date().toISOString();
            document.getElementById("createdAt").value = now;
        }
    </script>
        <!-- CSS読み込み -->
     <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/style.css">
    <!--Google Fonts-->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&family=Noto+Sans+JP:wght@100..900&family=Zen+Kaku+Gothic+Antique&family=Zen+Kaku+Gothic+New&family=Zen+Maru+Gothic&display=swap" rel="stylesheet">
    <!-- CDN 読み込み -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="js/script.js" defer></script>
</head>
<body>
	<button onclick="registerUser()">利用者登録</button>
	<div>
	    <h1>利用者登録</h1>
	    <form action="RegisterUserServlet" method="post" onsubmit="setPressedTime()">
	        ID: <input type="text" name="id"><br>
	        名前: <input type="text" name="name"><br>
	        名前（ふりがな）: <input type="text" name="nameFurigana"><br>
	        <input type="hidden" name="createdAt" id="createdAt">
	        <input type="submit" value="登録">
	    </form>
    </div>
   	<button onclick="deleteUser()">利用者登録削除</button>
	<div>
	    <h1>利用者登録削除</h1>
	    <form action="RegisterUserServlet" method="post">
	        ID: <input type="text" name="id"><br>
	        名前: <input type="text" name="name"><br>
	        名前（ふりがな）: <input type="text" name="nameFurigana"><br>
	        <input type="hidden" name="createdAt" id="createdAt">
	        <input type="submit" value="削除">
	    </form>
    </div>
    
</body>
</html>
