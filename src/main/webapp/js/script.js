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



//【▼ index.jsp パスワードの表示・非表示】
function togglePassword() {
      const passwordInput = document.getElementById("password");
	  const iconImg = document.getElementById("togglePasswordIcon");

      const isPassword = passwordInput.type === "password";
      passwordInput.type = isPassword ? "text" : "password";

      // アイコン切り替え
      iconImg.src = isPassword ? "image/icons8-隠す-16.png" : "image/icons8-目に見える-16.png";
    }
	
//【▼ index.jsp 新規管理者登録画面】
function newRegisterAdmin() {
        const createdAt = new Date().toISOString();
Swal.fire({
    title: '管理者登録',
    html:
        '<div class="register-action">' +
            '<div class="form-group">' +
                ' <label for="id">管理者ID</label>' +
                ' <input type="text" name="id" id="id" placeholder="任意の数列">' +
            '</div>' +
            '<div class="form-group">' +
                ' <label for="name">名前</label>' +
                ' <input type="text" name="name" id="name" placeholder="姓　名">' +
            '</div>' +
            '<div class="form-group">' +
                ' <label for="nameFurigana">名前（ふりがな）</label>' +
                ' <input type="text" name="nameFurigana" id="nameFurigana" placeholder="せい　めい">' +
            '</div>' +
            '<div class="form-group">' +
                ' <label for="email">メールアドレス</label>' +
                ' <input type="email" name="email" id="email" placeholder="●●●●@example.com">' +
            '</div>' +
            '<div class="form-group">' +
                ' <label for="password">パスワード</label>' +
                ' <input type="password" name="password" id="password">' +               
            '</div>' +
                ' <input type="hidden" name="createdAt" id="createdAt">' +
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
            function fullTrim(str) {
                return str.replace(/^[\s\u3000]+|[\s\u3000]+$/g, '');
            }

            // 各要素を直接取得
            const idElement = document.getElementById('id');
            const nameElement = document.getElementById('name');
            const nameFuriganaElement = document.getElementById('nameFurigana');
            const emailElement = document.getElementById('email');
            const passwordElement = document.getElementById('password');


            // --- !!! ここにconsole.logを追加します !!! ---
            console.log('idElement:', idElement);
            console.log('nameElement:', nameElement);
            console.log('nameFuriganaElement:', nameFuriganaElement);
            console.log('emailElement:', emailElement);
            console.log('passwordElement:', passwordElement);

            // 各要素の値を取得し、trimする
            const id = fullTrim(idElement?.value || '');
            const name = fullTrim(nameElement?.value || '');
            const nameFurigana = fullTrim(nameFuriganaElement?.value || '');
            const email = fullTrim(emailElement?.value || '');
            const password = fullTrim(passwordElement?.value || '');

            console.log('Trimmed ID:', id);
            console.log('Trimmed Name:', name);
            console.log('Trimmed NameFurigana:', nameFurigana);
            console.log('Trimmed Email:', email);
            console.log('Trimmed Password:', password);
            // --- !!! console.logの追加ここまで !!! ---


    if (!id || !name || !nameFurigana || !email || !password) {
        Swal.showValidationMessage('すべての項目を入力してください');
        return false;
    }

    if (!/^[\u3040-\u309Fー\s\u3000]+$/.test(nameFurigana)) {
        Swal.showValidationMessage('ふりがなはひらがなで入力してください');
        return false;
    }

    const nameParts = name.split(/[\s\u3000]+/);
    if (nameParts.length !== 2) {
        Swal.showValidationMessage('姓と名の間にはスペースを入力してください');
        return false;
    }

    const namePartsF = nameFurigana.split(/[\s\u3000]+/);
    if (namePartsF.length !== 2) {
        Swal.showValidationMessage('ふりがなの姓と名の間にはスペースを入力してください');
        return false;
    }



    return { id, name, nameFurigana, email, password, createdAt };
    }    

    }).then((result) => {
        if (result.isConfirmed && result.value) {
        const { id, name, nameFurigana, email, password, createdAt } = result.value;

        // サーバー送信
        fetch('RegisterAdmin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: `id=${encodeURIComponent(id)}&name=${encodeURIComponent(name)}&nameFurigana=${encodeURIComponent(nameFurigana)}&email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}&createdAt=${encodeURIComponent(createdAt)}`
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
            <p>email: ${email}</p>
            <p>password: ${password}</p>
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
    return false;
}





// 【▼ main.jsp 月報出力ボタン押下時の出力画面】
function showMonthlyReportDialog() {
        // alert("ボタンは押されました！");
    let optionsHtml = users.map(u => `<option value="${u.id}">利用者番号：${u.id}　　名前：${u.name}</option>`).join('');

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
				credentials: 'include',  // セッション維持
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
                '<div class="form-group">' +
                    ' <label for="id">利用者ID</label>' +
                        ' <input type="text" name="id" id="id" placeholder="任意の数列">' +
                    ' <label for="id">名前</label>' +
                        ' <input type="text" name="name" id="name" placeholder="姓　名">' +
                    ' <label for="id">名前（ふりがな）</label>' +
                        ' <input type="text" name="nameFurigana" id="nameFurigana" placeholder="せい　めい">' +
                '</div>' +
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
    const filteredUsers = users.filter(user => user.role === "user");

    const options = filteredUsers.map(user =>
        `<option value="${user.id}">管理者番号：${user.id} 　 名前：${user.name}　</option>`
    ).join("");

    Swal.fire({
        title: '管理者情報変更',
        html: `
            <div class="update-container">
            <select id="userSelect" class="swal2-select select-user">
                <option value="" disabled selected>管理者を選択してください</option>
                ${options}
            </select>
            <div id="detailArea" style="margin-top: 1em; text-align: left;"></div>     
        `,
        showCancelButton: true,
        confirmButtonText: '変更',
        cancelButtonText: '閉じる',
        allowOutsideClick: false,   // ← 背景クリックで閉じない
        allowEscapeKey: false,      // ← Escキーで閉じない
        customClass: {
        confirmButton: 'my-confirm-btn',
        cancelButton: 'my-cancel-btn'
    },
    buttonsStyling: false, // ← SweetAlert2のデフォルトボタンスタイルを無効にする
        didOpen: () => {
            const select = document.getElementById('userSelect');
            const detailArea = document.getElementById('detailArea');

            select.addEventListener('change', () => {
                const selectedId = select.value;
                const selectedUser = filteredUsers.find(user => user.id == selectedId);

                if (selectedUser && detailArea) {
                    detailArea.innerHTML = `
                    <div class="custom-reset">
                    <div class="form-group">
                        <label for="name">名前</label>
                        <input type="text" id="name" value="${selectedUser.name}" autocomplete="off">
                        <label for="name-furigana">ふりがな</label>
                        <input type="text" id="name-furigana" value="${selectedUser.nameFurigana || ""}" autocomplete="off">
                    </div>
                    <div class="user-status">
                        <label for="state" class="state-label">ユーザー状況</label>
                        <select id="state" autocomplete="off" class="state-select">
                            <option value="active" ${selectedUser.state === "active" ? "selected" : ""}>使用中</option>
                            <option value="disable" ${selectedUser.state === "disable" ? "selected" : ""}>未使用</option>
                        </select>
                    </div>
                    </div>
                    </div>                    
                    `;
                } else {
                    detailArea.innerHTML = '';
                }
            });

            if (select.value) {
                select.dispatchEvent(new Event('change'));
            }
        },
        preConfirm: () => {
            const selectedId = document.getElementById('userSelect').value;
            const nameInput = document.getElementById("name");
            const nameFuriganaInput = document.getElementById("name-furigana");
            const updatedState = document.getElementById("state")?.value;

            const name = nameInput?.value?.trim() || "";
            const nameFurigana = nameFuriganaInput?.value?.trim() || "";

            if (!selectedId) {
                Swal.showValidationMessage('管理者を選択してください');
                return false;
            }

            if (!/^[\u3040-\u309Fー\s]+$/.test(nameFurigana)) {
                Swal.showValidationMessage('ふりがなはひらがなで入力してください');
                return false;
            }

            const nameParts = name.split(/\s+/);
            if (nameParts.length !== 2) {
                Swal.showValidationMessage('姓と名の間にはスペースを入力してください');
                return false;
            }

            const namePartsF = nameFurigana.split(/\s+/);
            if (namePartsF.length !== 2) {
                Swal.showValidationMessage('ふりがなの姓と名の間にはスペースを入力してください');
                return false;
            }

            return fetch('UpdateUser', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    id: selectedId,
                    name: name,
                    nameFurigana: nameFurigana,
                    state: updatedState
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("通信エラー");
                }
                return response.json();
            })
            .catch(err => {
                console.error(err);
                Swal.showValidationMessage('更新に失敗しました');
                return false;
            });
        }
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire('完了', '管理者情報を更新しました。', 'success');
        }
    });
}

// 【admin.jsp 管理者登録変更 stateの変更】
function showStateChangeDialogAdmin() {
    const filteredUsers = users.filter(user => user.role === "admin");

    const options = filteredUsers.map(user =>
        `<option value="${user.id}">管理者番号：${user.id} 　 名前：${user.name}　</option>`
    ).join("");

    Swal.fire({
        title: '管理者情報変更',
        html: `
            <div class="update-container">
            <select id="userSelect" class="swal2-select select-user">
                <option value="" disabled selected>管理者を選択してください</option>
                ${options}
            </select>
            <div id="detailArea" style="margin-top: 1em; text-align: left;"></div>     
        `,
        showCancelButton: true,
        confirmButtonText: '変更',
        cancelButtonText: '閉じる',
        allowOutsideClick: false,   // ← 背景クリックで閉じない
        allowEscapeKey: false,      // ← Escキーで閉じない
        customClass: {
        confirmButton: 'my-confirm-btn',
        cancelButton: 'my-cancel-btn'
    },
    buttonsStyling: false, // ← SweetAlert2のデフォルトボタンスタイルを無効にする
        didOpen: () => {
            const select = document.getElementById('userSelect');
            const detailArea = document.getElementById('detailArea');

            select.addEventListener('change', () => {
                const selectedId = select.value;
                const selectedUser = filteredUsers.find(user => user.id == selectedId);

                if (selectedUser && detailArea) {
                    detailArea.innerHTML = `
                    <div class="custom-reset">
                    <div class="form-group">
                        <label for="name">名前</label>
                        <input type="text" id="name" value="${selectedUser.name}" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="name-furigana">ふりがな</label>
                        <input type="text" id="name-furigana" value="${selectedUser.nameFurigana || ""}" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="email">メールアドレス</label>
                        <input type="email" id="email" value="${selectedUser.email}" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="password">パスワード</label>
                        <input type="text" id="password" value="${selectedUser.password}" autocomplete="off">
                    </div>
                    <div class="user-status">
                        <label for="state" class="state-label">ユーザー状況</label>
                        <select id="state" autocomplete="off" class="state-select">
                            <option value="active" ${selectedUser.state === "active" ? "selected" : ""}>使用中</option>
                            <option value="disable" ${selectedUser.state === "disable" ? "selected" : ""}>未使用</option>
                        </select>
                    </div>
                    </div>
                    </div>                    
                    `;
                } else {
                    detailArea.innerHTML = '';
                }
            });

            if (select.value) {
                select.dispatchEvent(new Event('change'));
            }
        },
        preConfirm: () => {
            const selectedId = document.getElementById('userSelect').value;
            const nameInput = document.getElementById("name");
            const nameFuriganaInput = document.getElementById("name-furigana");
            const emailInput = document.getElementById("email");
            const passwordInput = document.getElementById("password");
            const updatedState = document.getElementById("state")?.value;

            const name = nameInput?.value?.trim() || "";
            const nameFurigana = nameFuriganaInput?.value?.trim() || "";
            const email = emailInput?.value?.trim() || "";
            const password = passwordInput?.value?.trim() || "";

            if (!selectedId) {
                Swal.showValidationMessage('管理者を選択してください');
                return false;
            }

            if (!/^[\u3040-\u309Fー\s]+$/.test(nameFurigana)) {
                Swal.showValidationMessage('ふりがなはひらがなで入力してください');
                return false;
            }

            const nameParts = name.split(/\s+/);
            if (nameParts.length !== 2) {
                Swal.showValidationMessage('姓と名の間にはスペースを入力してください');
                return false;
            }

            const namePartsF = nameFurigana.split(/\s+/);
            if (namePartsF.length !== 2) {
                Swal.showValidationMessage('ふりがなの姓と名の間にはスペースを入力してください');
                return false;
            }

            return fetch('UpdateAdmin', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    id: selectedId,
                    name: name,
                    nameFurigana: nameFurigana,
                    email: email,
                    password: password,
                    state: updatedState
                })
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error("通信エラー");
                }
                return response.json();
            })
            .catch(err => {
                console.error(err);
                Swal.showValidationMessage('更新に失敗しました');
                return false;
            });
        }
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire('完了', '管理者情報を更新しました。', 'success');
        }
    });
}

      // ▼ 出勤ボタンにイベントリスナー追加
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
// });
//     });
      //   // 🔽 登録確定時点の時刻を取得
    //   const createdAt = new Date().toISOString();

    //   // モーダルを閉じずに次の処理へ
    //   return { id, createdAt };
    // }    
//     }).then((result) => {
//         if (result.isConfirmed && result.value) {
//         const { id, createdAt } = result.value;

//         // サーバー送信
//         fetch('InterUser', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/x-www-form-urlencoded'
//         },
//         body: `id=${encodeURIComponent(id)}&name=${encodeURIComponent(name)}&nameFurigana=${encodeURIComponent(nameFurigana)}&createdAt=${encodeURIComponent(createdAt)}`
//         })
//         .then(response => {
//         if (!response.ok) throw new Error('サーバーエラー');
//         return response.text(); // Servletからのレスポンス（テキスト）を取得
//         })
//         .then(data => {
//         console.log('サーバー応答:', data);
//         if (data === '登録失敗') {
//             Swal.fire('登録失敗', 'そのIDはすでに使われています', 'warning');
//         } else if (data === '登録成功') {
//                    Swal.fire({
//             icon: 'success',
//             title: '登録完了',
//             html: `
//             <p>登録日時: ${createdAt}</p>
//             <p>ID: ${id}</p>
//             <p>名前: ${name}</p>
//             <p>ふりがな: ${nameFurigana}</p>
//             <p>を登録しました</p>
//             `
//         });
//         } else {
//             Swal.fire('エラー', '登録に失敗しました', 'error');
//         }
//         })
//         .catch(error => {
//         console.error('通信エラー:', error);
//         Swal.fire('エラー', '通信に失敗しました', 'error');
//         });
//     }
// 　});
// }



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
