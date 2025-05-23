//【▼ main.jsp 現在の年月日何時何分かの表示】 
function updateDateTime() {
    const now = new Date();
    const y = now.getFullYear();
    const m = String(now.getMonth() + 1).padStart(2, '0');
    const d = String(now.getDate()).padStart(2, '0');
    const h = String(now.getHours()).padStart(2, '0');
    const min = String(now.getMinutes()).padStart(2, '0');
    const s = String(now.getSeconds()).padStart(2, '0');

    // 曜日の取得
    const days = ['日', '月', '火', '水', '木', '金', '土'];
    const w = days[now.getDay()];   // 0（日）～ 6（土）

    const formatted = `
        <div class="now-date">
        <span class="big">${y}</span><span class="small">年</span>
        <span class="big">${m}</span><span class="small">月</span>
        <span class="big">${d}</span><span class="small">日</span>
        <span class="small">(${w})</span>
        <br>
        <span class="tobig">${h}:${min}</span><span class="big">:${s}</span>
        </div>
        `;
    document.getElementById('datetime').innerHTML = formatted;
    }

    // 初回実行 + 毎秒更新
    updateDateTime();
    setInterval(updateDateTime, 1000);


// 【▼ main.jsp ログインボタン押下時の管理者ログイン】
function showLogin() {
Swal.fire({
    title: '管理者ログイン',
    html:
    '<form action="LoginToAdmin" method="post">' +
        '<input type="email" name="email" id="email" class="swal2-input" placeholder="メールアドレス">' +
        '<input type="password" name="password" id="password" name="pass" class="swal2-input" placeholder="パスワード">' +
    '</form>',
    showCancelButton: true,
    confirmButtonText: 'ログイン',
    cancelButtonText: '閉じる',
    allowOutsideClick: false,   // ← 背景クリックで閉じない
    allowEscapeKey: false,      // ← Escキーで閉じない
    customClass: {
    confirmButton: 'my-confirm-btn',
    cancelButton: 'my-cancel-btn'
    },
    buttonsStyling: false, // ← SweetAlert2のデフォルトボタンスタイルを無効にする
    
    preConfirm: () => {
        const email = document.getElementById('email').value.trim();        // id = email
        const password = document.getElementById('password').value.trim();  // id = password

        if (!email || !password) {
            Swal.showValidationMessage('メールアドレスとパスワードを入力してください');
            return false;
        }
        return { email, password };
    }
 }).then((result) => {
        if (result.isConfirmed && result.value) {
            const { email, password } = result.value;

             //  サーバーへログイン情報を送信
            fetch('LoginToAdmin', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: `email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}` // 左辺：リクエストパラメータ用　右辺：const
            })
            .then(response => {
                if (!response.ok) throw new Error('ログイン失敗');
                return response.text(); // サーバーからの応答（例：「success」など）
            })
            .then(data => {
                if (data === 'success') {
                    Swal.fire('ログイン成功', '', 'success').then(() => {
                        window.location.href = 'AdminPage'; // ← 遷移先
                    });
                } else {
                    Swal.fire('エラー', 'メールアドレスまたはパスワードが間違っています', 'error');
                }
            })
            .catch(error => {
                console.error('ログインエラー:', error);
                Swal.fire('通信エラー', 'ログインに失敗しました', 'error');
            });
        }
    });
}


// 【管理者画面　利用者登録ボタン押下時】
function registerUser() {
Swal.fire({
    title: '利用者登録',
    html:
        '<div class="register-action">' +
            '<form>' +
                '  ID: <input type="text" name="id" id="id"><br>' +
                ' 名前: <input type="text" name="name" id="name"><br>' +
                ' 名前（ふりがな）: <input type="text" name="nameFurigana" id="nameFurigana"><br>' +
                ' <input type="hidden" name="createdAt" id="createdAt">' +
            '</form>' +
        '</div>',

    showCancelButton: true,
    confirmButtonText: '登録',
    cancelButtonText: '閉じる',  // ★バツの画像を右上に貼り付けで対応した方がいいかも
    allowOutsideClick: false,   // ← 背景クリックで閉じない
    allowEscapeKey: false,      // ← Escキーで閉じない
    customClass: {
    confirmButton: 'my-confirm-btn',
    cancelButton: 'my-cancel-btn'
    },
    buttonsStyling: false, // ← SweetAlert2のデフォルトボタンスタイルを無効にする

    preConfirm: () => {
      const id = document.getElementById('id').value.trim();
      const name = document.getElementById('name').value.trim();
      const nameFurigana = document.getElementById('nameFurigana').value.trim();

   　 if (!id || !name || !nameFurigana) {
        Swal.showValidationMessage('すべての項目を入力してください'); 
        return false;
      }
      if (!/^[\u3040-\u309Fー\s]+$/.test(nameFurigana)) {
    　　Swal.showValidationMessage('ふりがなはひらがなで入力してください');
        return false;
    　}

    // 🔽 登録確定時点の時刻を取得
    const createdAt = new Date().toISOString();

      // モーダルを閉じずに次の処理へ
      return { id, name, nameFurigana, createdAt };
    }
    }).then((result) => {
        if (result.isConfirmed && result.value) {
        const { id, name, nameFurigana, createdAt } = result.value;

         // 🔽 成功時の表示（サーバー送信しない場合はfetch以降のコメント化で表示可能）
        Swal.fire({
            icon: 'success',
            title: '登録完了',
            html: `
            <p>登録日時: ${createdAt}</p>
            <p>ID: ${id}</p>
            <p>名前: ${name}</p>
            <p>ふりがな: ${nameFurigana}</p>
            <p>を登録しました</p>
            `
        });

        // サーバー送信
        fetch('RegisterUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `id=${encodeURIComponent(id)}&name=${encodeURIComponent(name)}&nameFurigana=${encodeURIComponent(nameFurigana)}&createdAt=${encodeURIComponent(createdAt)}`
        })
        .then(response => {
        if (!response.ok) throw new Error('サーバーエラー');
        return response.text(); // Servletからのレスポンス（テキスト）を取得
        })
        .then(data => {
        console.log('サーバー応答:', data);
        if (data === '登録失敗') {
            Swal.fire('登録失敗', 'そのIDはすでに使われています', 'warning');
        } else if (data === '登録成功') {
            Swal.fire('登録完了', 'ユーザーが正常に登録されました', 'success');
        } else {
            Swal.fire('エラー', '登録に失敗しました', 'error');
        }
        })
        .catch(error => {
        console.error('通信エラー:', error);
        Swal.fire('エラー', '通信に失敗しました', 'error');
        });
    }
　});
}

// 【admin.jsp 利用者登録削除 state = disableに変更】
function deleteUser() {
Swal.fire({
    title:'利用者登録削除利用者登録',
    html:
        '<div>' +
        '<form>' +
                '  ID: <input type="text" name="id" id="id"><br>' +
                ' 名前: <input type="text" name="name" id="name"><br>' +
                ' 名前（ふりがな）: <input type="text" name="nameFurigana" id="nameFurigana"><br>' +
                ' <input type="hidden" name="createdAt" id="createdAt">' +
            '</form>' +
        '</div>',

    showCancelButton: true,
    confirmButtonText: '削除',
    cancelButtonText: '閉じる',  // ★バツの画像を右上に貼り付けで対応した方がいいかも
    allowOutsideClick: false,   // ← 背景クリックで閉じない
    allowEscapeKey: false,      // ← Escキーで閉じない
    customClass: {
    confirmButton: 'my-confirm-btn',
    cancelButton: 'my-cancel-btn'
    },
    buttonsStyling: false, // ← SweetAlert2のデフォルトボタンスタイルを無効にする
　});
}

// ▼ main.jsp 各名前押下時のモーダルウィンド
function showWork() {
Swal.fire({
    title: '出退勤記録',
    html:
        '<div id="clock" style="font-size:16px; margin-bottom:10px;"></div>' +  // 時計表示用エリア追加
        '<div class="register-action">' +
            '<form action="Inter" method="post">' +
                '<input type="hidden" name="action" value="start">' +
                '<input type="submit" class="inbtn" value="出勤" name="inter">' +
            '</form>' +
            '<form action="Enter" method="post">' +
               ' <input type="hidden" name="action" value="end">' +
                '<input type="submit" class="outbtn" value="退勤" name="enter">' +
            '</form>' +
        '</div>',
    showCancelButton: true,
    confirmButtonText: '登録',
    cancelButtonText: '閉じる',  // ★バツの画像を右上に貼り付けで対応した方がいいかも
    allowOutsideClick: false,   // ← 背景クリックで閉じない
    allowEscapeKey: false,      // ← Escキーで閉じない
    customClass: {
    confirmButton: 'my-confirm-btn',
    cancelButton: 'my-cancel-btn'
    },
    buttonsStyling: false, // ← SweetAlert2のデフォルトボタンスタイルを無効にする
 didOpen: () => {
      // ▼ 時計を更新する関数
      function updateClock() {
        const now = new Date();
        const h = String(now.getHours()).padStart(2, '0');
        const m = String(now.getMinutes()).padStart(2, '0');
        const s = String(now.getSeconds()).padStart(2, '0');
        document.getElementById('clock').textContent = `現在時刻: ${h}時${m}分${s}秒`;
      }
      updateClock();  // 即時実行
      // 1秒ごとに時計を更新
      const intervalId = setInterval(updateClock, 1000);

      // モーダルが閉じられたら時計の更新を止める
      Swal.getPopup().addEventListener('remove', () => clearInterval(intervalId));
    }
    // preConfirm: () => {
    // const username = document.getElementById('username').value;
    // const password = document.getElementById('password').value;
    // return { username, password };
    // }
// }).then((result) => {
//     if (result.isConfirmed) {
//     console.log('ログイン情報:', result.value);
//     }
});
}