<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/user/user.css"/>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/js/user/user.js"></script>
</head>
<body class="container">
<section class="base">
    <div style="margin: 10px">基本信息管理：</div>
    <div class="row">
        <div class="col-sm">
            <!-- 修改用户名 -->
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">原用户名：</span>
                </div>
                <input type="text" class="form-control" id="username-old" disabled value="${Session.user.username}"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">新用户名：</span>
                </div>
                <input type="text" class="form-control" id="username-new" onchange="checkUsername()">
            </div>
            <div class="input-group mb-3 error-wrapper" id="username-error">
            </div>
            <div class="input-group mb-3">
                <button class="bg-dark" onclick="updateUsername()">确定</button>
            </div>
        </div>
        <div class="col-sm">
            <!-- 修改密码 -->
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">原密码：</span>
                </div>
                <input type="password" class="form-control" id="password-old"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">新密码：</span>
                </div>
                <input type="password" class="form-control" id="password-new-1"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">确认密码：</span>
                </div>
                <input type="password" class="form-control" id="password-new-2">
            </div>
            <div class="input-group mb-3 error-wrapper" id="password-msg">
            </div>
            <div class="input-group mb-3">
                <button class="bg-dark" onclick="updatePassword()">确定</button>
            </div>

        </div>
    </div>
</section>


<section class="address">

    <div style="margin: 10px">收件地址管理：</div>

    <div>
        <button class="btn btn-dark" style="width: 100%; margin-bottom: 10px" onclick="showAddressItemUpdate(this)">新增
        </button>
        <div class="address-item-update">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">收货人：</span>
                </div>
                <input type="text" class="form-control" id="address-name"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">电话：</span>
                </div>
                <input type="text" class="form-control" id="address-phone"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">收货地址：</span>
                </div>
                <input type="text" class="form-control" id="address-address"/>
            </div>
            <div class="input-group mb-3 error-wrapper" id="address-msg"></div>
            <div class="input-group mb-3">
                <button class="bg-dark" style="width: 200px" onclick="addAddress()">增加</button>
            </div>
        </div>
    </div>

    <div class="list-group" id="address-list">
        <div class="address-item">
            <a href="javascript:" class="list-group-item list-group-item-action" onclick="showAddressItemUpdate(this)">Cras
                justo odio</a>
            <div class="address-item-update">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">收货人：</span>
                    </div>
                    <input type="text" class="form-control"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">电话：</span>
                    </div>
                    <input type="text" class="form-control"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">收货地址：</span>
                    </div>
                    <input type="text" class="form-control"/>
                </div>
                <div class="input-group mb-3">
                    <button class="bg-dark" style="width: 200px" onclick="updateAddress(this, id)">修改</button>
                    <button class="bg-dark" style="width: 200px" onclick="deleteAddress(id)">删除</button>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>