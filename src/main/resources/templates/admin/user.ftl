<link rel="stylesheet" href="/css/admin/user.css"/>

<div class="base">
    <div class="row">

        <div class="col-sm">
            <!-- 修改密码 -->
            重置当前用户密码
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">原密码：</span>
                </div>
                <input type="password" class="form-control" id="curr-password-old"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">新密码：</span>
                </div>
                <input type="password" class="form-control" id="curr-password-new-1"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">确认密码：</span>
                </div>
                <input type="password" class="form-control" id="curr-password-new-2">
            </div>
            <div class="input-group mb-3 error-wrapper" id="curr-msg">
            </div>
            <div class="input-group mb-3">
                <button class="bg-dark" onclick="updatePassword()">确定</button>
            </div>
        </div>

        <div class="col-sm">
            <!-- 修改用户名 -->
            添加管理员
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">用户名：</span>
                </div>
                <input type="text" class="form-control" id="new-username"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">密码：</span>
                </div>
                <input type="password" class="form-control" id="new-password">
            </div>
            <div class="input-group mb-3 error-wrapper" id="new-error">
            </div>
            <div class="input-group mb-3">
                <button class="bg-dark" onclick="addAdmin()">确定</button>
            </div>
        </div>

        <div class="col-sm">
            <!-- 修改用户名 -->
            重置管理员密码
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">管理员名称：</span>
                </div>
                <input type="text" class="form-control" id="reset-username"/>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text">密码：</span>
                </div>
                <input type="password" class="form-control" id="reset-password">
            </div>
            <div class="input-group mb-3 error-wrapper" id="reset-error">
            </div>
            <div class="input-group mb-3">
                <button class="bg-dark" onclick="resetPassword()">确定</button>
            </div>
        </div>



    </div>
</div>

<script src="/js/admin/user.js"></script>