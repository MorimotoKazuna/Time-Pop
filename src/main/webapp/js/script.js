// ▼ main.jsp 現在の年月日何時何分かの表示
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


// ▼ main.jsp ログインボタン押下時のモーダルウィンド
function showLogin() {
Swal.fire({
    title: '管理者ログイン',
    html:
    '<form action="Login" method="post">' +
        '<input type="text" id="username" email="email" class="swal2-input" placeholder="メールアドレス">' +
        '<input type="password" id="password" name="pass" class="swal2-input" placeholder="パスワード">' +
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
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    return { username, password };
    }
}).then((result) => {
    if (result.isConfirmed) {
    console.log('ログイン情報:', result.value);
    }
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