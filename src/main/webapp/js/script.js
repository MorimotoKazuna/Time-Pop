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



// 【▼ main.jsp 月報出力ボタン押下時の出力画面】
function showMonthlyReportDialog() {
        // alert("ボタンは押されました！");
    let optionsHtml = users.map(u => `<option value="${u.id}">${u.name}（ID: ${u.id}）</option>`).join('');

    Swal.fire({
        title: '月報出力',
        html:
            `<select id="userId" class="swal2-select">${optionsHtml}</select>` +
            '<input id="startDate" type="date" class="swal2-input" placeholder="開始日">' +
            '<input id="endDate" type="date" class="swal2-input" placeholder="終了日">',
        showCancelButton: true,
        confirmButtonText: '出力',
        cancelButtonText: '閉じる',
        allowOutsideClick: false,   // ← 背景クリックで閉じない
        allowEscapeKey: false,      // ← Escキーで閉じない
        customClass: {
        confirmButton: 'my-confirm-btn',
        cancelButton: 'my-cancel-btn'
        },
        buttonsStyling: false, // ← SweetAlert2のデフォルトボタンスタイルを無効にする

        // ▼
        preConfirm: () => {
            const userId = document.getElementById('userId').value;
            const startDate = document.getElementById('startDate').value;
            const endDate = document.getElementById('endDate').value;

            if (!userId || !startDate || !endDate) {
                Swal.showValidationMessage('すべての項目を入力してください');
                return false;
            }

            // フォームを作ってPOST送信
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'MonthlyReport';

            const inputs = { userId, startDate, endDate };
            for (const name in inputs) {
                const input = document.createElement('input');
                input.type = 'hidden';
                input.name = name;
                input.value = inputs[name];
                form.appendChild(input);
            }

            document.body.appendChild(form);
            form.submit();
        }
        // ▲
    });
}


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
                ' 名前: <input type="text" name="name" id="name" placeholder="姓　名"><br>' +
                ' 名前（ふりがな）: <input type="text" name="nameFurigana" id="nameFurigana" placeholder="せい　めい"><br>' +
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
     // 🔽 名前を分割してスペースで結合
        const nameParts = name.split(/\s+/); // スペースで分割（複数スペースも対応）
        if (nameParts.length !== 2) {
            Swal.showValidationMessage('姓と名の間にはスペースを入力してください');
            return false;
        }
        const namePartsF = nameFurigana.split(/\s+/); // スペースで分割（複数スペースも対応）
        if (namePartsF.length !== 2) {
            Swal.showValidationMessage('ふりがなの姓と名の間にはスペースを入力してください');
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


// 【admin.jsp 利用者登録変更 stateの変更】
function showStateChangeDialog() {
    let optionsHtml = users.map(u => 
        `<option value="${u.id}">${u.name}（ID: ${u.id}）</option>`).join('');

    Swal.fire({
        title: '利用者状態変更',
        html:
            `<select id="userId" class="swal2-select">${optionsHtml}</select>` +
            `<select id="newState" class="swal2-select">
                <option value="1">使用中</option>
                <option value="0">未使用</option>
            </select>`,
        showCancelButton: true,
        confirmButtonText: '変更',
        cancelButtonText: '閉じる',
        allowOutsideClick: false,
        allowEscapeKey: false,
        customClass: {
            confirmButton: 'my-confirm-btn',
            cancelButton: 'my-cancel-btn'
        },
        buttonsStyling: false,
        preConfirm: () => {
            const userId = document.getElementById('userId').value;
            const newState = document.getElementById('newState').value;

            if (!userId) {
                Swal.showValidationMessage('ユーザーを選択してください');
                return false;
            }

            const form = document.createElement('form');
            form.method = 'POST';
            form.action = 'ChangeState'; // ← Servlet名

            const input1 = document.createElement('input');
            input1.type = 'hidden';
            input1.name = 'userId';
            input1.value = userId;

            const input2 = document.createElement('input');
            input2.type = 'hidden';
            input2.name = 'newState';
            input2.value = newState;

            form.appendChild(input1);
            form.appendChild(input2);

            document.body.appendChild(form);
            form.submit();
        }
    });
}




// ▼ main.jsp 各名前押下時のモーダルウィンド
function showWork(userId, nameFurigana, isAlreadyClockedIn) {
    const createdAt = new Date().toISOString().slice(0, 19).replace("T", " ");

    Swal.fire({
        title: '出退勤記録',
        html:
            `<p>ID：${userId}</p>` +
            `<p>${nameFurigana}さん</p>` +
            '<div id="clock" style="font-size:16px; margin-bottom:10px;"></div>' +
            '<div id="messageBox" style="font-size:14px; color:#444; margin-bottom:10px;"></div>' +
            '<div class="register-action">' +
                `<form action="Inter" method="post" id="startForm">
                    <input type="hidden" name="user_id" value="${userId}">
                    <input type="hidden" name="created_at" value="${createdAt}">
                    <input type="hidden" name="action" value="start">
                    <button type="button" class="inbtn" id="startBtn">出勤</button>
                </form>` +
                `<form action="Enter" method="post" id="endForm">
                    <input type="hidden" name="user_id" value="${userId}">
                    <input type="hidden" name="created_at" value="${createdAt}">
                    <input type="hidden" name="action" value="end">
                    <button type="button" class="outbtn" id="endBtn">退勤</button>
                </form>` +
            '</div>',
        showCancelButton: true,
        showConfirmButton: false,
        cancelButtonText: '閉じる',
        allowOutsideClick: false,
        allowEscapeKey: false,
        customClass: {
            cancelButton: 'my-cancel-btn'
        },
        buttonsStyling: false,
        
 didOpen: () => {
            const startBtn = document.getElementById("startBtn");
            const endBtn = document.getElementById("endBtn");

             if (isAlreadyClockedIn) {
                startBtn.disabled = true;
                startBtn.classList.add("disabled-btn");
                document.getElementById("messageBox").textContent = `${nameFurigana}さんは本日すでに出勤済みです。`;
            } else {
                startBtn.addEventListener("click", () => {
                    document.getElementById("messageBox").textContent = `${nameFurigana}さん、おはようございます。`;
                    document.getElementById("startForm").submit(); // フォーム送信
                    setTimeout(() => location.reload(), 1000); // 1秒後に画面リロード（送信完了を待つ簡易対応）
                });
            }

            endBtn.addEventListener("click", () => {
                document.getElementById("messageBox").textContent = `${nameFurigana}さん、お疲れさまでした。`;
                document.getElementById("endForm").submit(); // フォーム送信
                setTimeout(() => location.reload(), 1000); // 同上
            });
        }
    });
}
//       // ▼ 出勤ボタンにイベントリスナー追加
//       document.getElementById("startForm").addEventListener("submit", function(e) {
//         e.preventDefault(); // フォーム送信を止める

//         Swal.fire({
//           icon: 'success',
//           title: `${user.getNameFurigana()}さん、おはようございます！`,
//           text: '今日も一日頑張りましょう！',
//           timer: 1500,
//           showConfirmButton: false
//         }).then(() => {
//           // メッセージ表示後に出勤処理を送信
//           this.submit(); // フォームを送信
//         });
//       });
//     }
//     //   // 🔽 登録確定時点の時刻を取得
//     //   const createdAt = new Date().toISOString();

//     //   // モーダルを閉じずに次の処理へ
//     //   return { id, createdAt };
//     // }    
// //     }).then((result) => {
// //         if (result.isConfirmed && result.value) {
// //         const { id, createdAt } = result.value;

// //         // サーバー送信
// //         fetch('InterUser', {
// //         method: 'POST',
// //         headers: {
// //             'Content-Type': 'application/x-www-form-urlencoded'
// //         },
// //         body: `id=${encodeURIComponent(id)}&name=${encodeURIComponent(name)}&nameFurigana=${encodeURIComponent(nameFurigana)}&createdAt=${encodeURIComponent(createdAt)}`
// //         })
// //         .then(response => {
// //         if (!response.ok) throw new Error('サーバーエラー');
// //         return response.text(); // Servletからのレスポンス（テキスト）を取得
// //         })
// //         .then(data => {
// //         console.log('サーバー応答:', data);
// //         if (data === '登録失敗') {
// //             Swal.fire('登録失敗', 'そのIDはすでに使われています', 'warning');
// //         } else if (data === '登録成功') {
// //                    Swal.fire({
// //             icon: 'success',
// //             title: '登録完了',
// //             html: `
// //             <p>登録日時: ${createdAt}</p>
// //             <p>ID: ${id}</p>
// //             <p>名前: ${name}</p>
// //             <p>ふりがな: ${nameFurigana}</p>
// //             <p>を登録しました</p>
// //             `
// //         });
// //         } else {
// //             Swal.fire('エラー', '登録に失敗しました', 'error');
// //         }
// //         })
// //         .catch(error => {
// //         console.error('通信エラー:', error);
// //         Swal.fire('エラー', '通信に失敗しました', 'error');
// //         });
// //     }
// // 　});
// // }



//       // モーダルが閉じられたら時計の更新を止める
//     //   Swal.getPopup().addEventListener('remove', () => clearInterval(intervalId));
