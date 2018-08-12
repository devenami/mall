function addAdmin() {
    let username = $('#new-username').val();
    let password = $('#new-password').val();
    const msg = $('#new-error');
    msg.empty();
    if (!username || !password) {
        msg.text('请将信息填写完整');
        msg.show();
        return;
    }


    let url = '/api/user/register';
    $.post(url, {
            username: username,
            password: password,
            role: 1
        },
        function (result) {
            if (result.code === 1 && result.data) {
                msg.text('添加成功');
                msg.css('display', 'inline-block');

                $('#new-username').val('');
                $('#new-password').val('');

                setInterval(function () {
                    msg.css('display', 'none');
                }, 5000);
            } else {
                msg.css('display', 'block');
                msg.text(result.msg);
            }
        });
}


function updatePassword() {

    let oldPassword = $('#curr-password-old').val();
    let newPassword1 = $('#curr-password-new-1').val();
    let newPassword2 = $('#curr-password-new-2').val();

    const msg = $('#curr-msg');
    msg.empty();

    if (!oldPassword) {
        msg.text('请输入原密码');
        msg.css('display', 'inline-block');
        return;
    }

    if (!newPassword1 && !newPassword2) {
        msg.text('新密码不能为空');
        msg.css('display', 'inline-block');
        return;
    }

    if (newPassword1 !== newPassword2) {
        msg.text('两次输入的密码不一致');
        msg.css('display', 'inline-block');
        return;
    }

    if (oldPassword === newPassword1) {
        msg.text('新旧密码不能相同');
        msg.css('display', 'inline-block');
        return;
    }

    const url = '/api/user/update/password';

    $.post(url, {password: newPassword1, role: 2},
        function (result) {

            if (result.code === 1 && result.data === 1) {
                msg.text('修改成功');
                msg.css('display', 'inline-block');

                $('#curr-password-old').val('');
                $('#curr-password-new-1').val('');
                $('#curr-password-new-2').val('');

                setInterval(function () {
                    msg.css('display', 'none');
                }, 5000);
            } else {
                msg.text(result.msg);
                msg.css('display', 'inline-block');
            }
        });
}


function resetPassword() {
    let username = $('#reset-username').val();
    let password = $('#reset-password').val();
    const msg = $('#reset-error');
    msg.empty();
    if (!username || !password) {
        msg.text('请将信息填写完整');
        msg.show();
        return;
    }

    const url = '/api/user/update/password/reset';
    $.post(url,
        {
            username: username,
            password: password,
            role: 1
        },
        function (result) {
            if (result.code === 1) {
                msg.text('修改成功');
                msg.css('display', 'inline-block');

                $('#reset-username').val('');
                $('#reset-password').val('');

                setInterval(function () {
                    msg.css('display', 'none');
                }, 5000);
            } else {
                msg.text(result.msg);
                msg.show();
            }
        });
}