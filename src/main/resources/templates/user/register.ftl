<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css"/>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <style>
        section {
            display: block;
            margin: 200px auto 0;
            height: 300px;
            width: 500px;
        }

        .span-width {
            width: 100px;
            background-color: white;
            text-align: right;
        }

        .input-group {
            margin-left: 50px;
            margin-top: 15px;
            width: 400px;
        }

        .btn-success {
            width: 400px;
            display: block;
            margin: 15px auto 0;
        }

        .error-msg {
            color: red;
            display: none;
            margin: 0 auto;
            font-size: 20px;
            text-align: center;
        }

    </style>
</head>
<body class="container">

<section>
    <div class="input-group">
        <div class="input-group-prepend">
            <span class="input-group-text span-width">用户名：</span>
        </div>
        <input id="id_username" type="text" class="form-control" onblur="checkUsername()">
    </div>

    <div class="input-group">
        <div class="input-group-prepend">
            <span class="input-group-text span-width">密  码：</span>
        </div>
        <input id="id_password" type="password" class="form-control">
    </div>

    <button onclick="btn_submit()" id="id_submit" type="button" class="btn btn-success">注册</button>

    <span id="id_msg" class="error-msg">error msg</span>
</section>

</body>
</html>
<script>

    var isCanUse = 0;

    function checkUsername() {
        var username = $('#id_username').val();
        var url = '/api/user/check/' + username;
        var error_msg = $('#id_msg');
        error_msg.text('');
        error_msg.css('display', 'none');
        $.get(url, function (data) {
            if (data.code === 1 && data.data !== 0) {
                error_msg.css('display', 'block');
                error_msg.text('该用户名已存在，请更换');
            } else {
                isCanUse = 1;
            }
        });
    }

    function btn_submit() {

        if (!isCanUse) {
            return;
        }

        var username = $('#id_username').val();
        var password = $('#id_password').val();
        var id_msg = $('#id_msg');
        id_msg.text('');
        id_msg.css('display', 'none');
        if (!username || !password) {
            id_msg.css('display', 'block');
            id_msg.text('请输入完整信息');
            return;
        }

        var url = '/api/user/register';
        $.post(url, {
                    username: username,
                    password: password,
                    role: 0
                },
                function (result) {
                    if (result.code === 1 && result.data) {
                        url = '/user/login.html';
                        window.location.href = url;
                    } else {
                        id_msg.css('display', 'block');
                        id_msg.text(result.msg);
                    }
                });

    }
</script>