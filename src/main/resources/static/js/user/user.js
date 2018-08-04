$(function () {

    loadAddressList();

});

function updateUsername() {
    let oldUsername = $('#username-old').val();
    let newUsername = $('#username-new').val();
    const error = $('#username-error');
    error.empty();

    if (oldUsername && newUsername && oldUsername !== newUsername) {
        const url = '/api/user/update/username';

        $.post(url, {username: newUsername, role: 0},
            function (result) {
                if (result.code === 1 && result.data === 1) {
                    error.text('修改成功');
                    error.css('display', 'inline-block');
                    $('#username-new').val('');
                    $('#username-old').val(newUsername);
                    setInterval(function () {
                        error.css('display', 'none');
                    }, 5000);
                } else {
                    error.text(result.msg);
                    error.css('display', 'inline-block');
                }
            });

    } else {
        error.text("用户信息不完整");
        error.css('display', 'inline-block');
    }

}

function checkUsername() {
    let username = $('#username-new').val();
    let url = '/api/user/check/' + username;
    let error_msg = $('#username-error');
    error_msg.empty();
    error_msg.css('display', 'none');
    $.get(url, function (data) {
        if (data.code === 1 && data.data !== 0) {
            error_msg.text('该用户名已存在，请更换');
            error_msg.css('display', 'inline-block');
        }
    });
}

function updatePassword() {

    let oldPassword = $('#password-old').val();
    let newPassword1 = $('#password-new-1').val();
    let newPassword2 = $('#password-new-2').val();

    const msg = $('#password-msg');
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

    $.post(url, {password: newPassword1, role: 0},
        function (result) {

            if (result.code === 1 && result.data === 1) {
                msg.text('修改成功');
                msg.css('display', 'inline-block');

                $('#password-old').val('');
                $('#password-new-1').val('');
                $('#password-new-2').val('');

                setInterval(function () {
                    msg.css('display', 'none');
                }, 5000);
            } else {
                msg.text(result.msg);
                msg.css('display', 'inline-block');
            }
        });
}

function showAddressItemUpdate(addressA) {

    $(addressA).siblings('.address-item-update').toggle();
}

function addAddress() {

    let name = $('#address-name').val();
    let phone = $('#address-phone').val();
    let address = $('#address-address').val();

    const msg = $('#address-msg');
    msg.empty();

    if (!name || !phone || !address) {
        msg.text('请将信息填写完整');
        msg.css('display', 'inline-block');
        return;
    }

    const url = '/api/address/add';

    $.post(url, {
            name: name,
            phone: phone,
            address: address
        },
        function (result) {
            if (result.code === 1 && result.data != null) {
                location.href = '/user/user.html';
            } else {
                msg.text(result.msg);
                msg.css('display', 'inline-block');
            }
        });

}

function loadAddressList() {

    const url = '/api/address/get/list';

    const addressContainer = $('#address-list');
    addressContainer.empty();

    let content = '';

    $.get(url, function (result) {

        let addressList = result.data;

        for (let i = 0; i < addressList.length; i++) {
            let address = addressList[i];

            let id = address.id;
            let name = address.name;
            let phone = address.phone;
            let addr = address.address;

            let show = '收货人：' + name + ' 电话：' + phone + ' 地址：' + addr;

            content += '<div class="address-item">';
            content += '    <a href="javascript:" class="list-group-item list-group-item-action" onclick="showAddressItemUpdate(this)"> ' +
                show + '</a>';
            content += '    <div class="address-item-update">';
            content += '        <div class="input-group mb-3">';
            content += '            <div class="input-group-prepend">';
            content += '                <span class="input-group-text">收货人：</span>';
            content += '            </div>';
            content += '            <input type="text" class="form-control" value="' + name + '"/>';
            content += '        </div>';
            content += '        <div class="input-group mb-3">';
            content += '            <div class="input-group-prepend">';
            content += '                <span class="input-group-text">电话：</span>';
            content += '            </div>';
            content += '            <input type="text" class="form-control" value="' + phone + '"/>';
            content += '        </div>';
            content += '        <div class="input-group mb-3">';
            content += '            <div class="input-group-prepend">';
            content += '                <span class="input-group-text">收货地址：</span>';
            content += '            </div>';
            content += '            <input type="text" class="form-control" value="' + addr + '"/>';
            content += '        </div>';
            content += '        <div class="input-group mb-3 error-wrapper"></div>';
            content += '        <div class="input-group mb-3">';
            content += '            <button class="bg-dark" style="width: 200px" onclick="updateAddress(this, ' + id + ')">修改</button>';
            content += '            <button class="bg-dark" style="width: 200px" onclick="deleteAddress(this, ' + id + ')">删除</button>';
            content += '        </div>';
            content += '    </div>';
            content += '</div>';
        }

        addressContainer.html(content);

    });

}

function updateAddress(btn, id) {
    let name = $(btn).parent().siblings().eq(0).children('input').val();
    let phone = $(btn).parent().siblings().eq(1).children('input').val();
    let address = $(btn).parent().siblings().eq(2).children('input').val();

    const url = '/api/address/update';
    $.post(url, {
        id: id,
        name: name,
        phone: phone,
        address: address
    }, function (result) {
        if (result.code === 1 && result.data === 1) {
            location.href = '/user/user.html';
        } else {
            setAddressItemMsg(btn, result.msg);
        }

    });
}

function deleteAddress(btn, id) {
    let url = '/api/address/delete/' + id;

    $.ajax({
        type: 'delete',
        url: url,
        success: function (result) {
            if (result.code === 1 && result.data === 1) {
                location.href = '/user/user.html';
            } else {
                setAddressItemMsg(btn, result.msg);
            }
        }
    });

}

function setAddressItemMsg(btn, msg) {
    let error = $(btn).parent().siblings('.error-wrapper');
    error.empty();
    error.text(msg);
    error.css('display', 'inline-block');
}