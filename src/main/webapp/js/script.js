function showLogin() {
Swal.fire({
    title: '管理者ログイン',
    html:
    '<form action="Login" method="post">' +
        '<input type="text" id="username" name="name" class="swal2-input" placeholder="ユーザーID">' +
        '<input type="password" id="password" name="pass" class="swal2-input" placeholder="パスワード">' +
    '</form>',
    confirmButtonText: 'ログイン',
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