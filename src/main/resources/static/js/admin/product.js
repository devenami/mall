$(function () {
    loadProductList();
    // fullCategory();
});

function addProduct() {
    let name = $('#product-name').val();
    let price = $('#product-price').val();
    let total = $('#product-total').val();
    let categoryId = $('#product-category').val();
    let desc = $('#product-desc').val();
    const msg = $('#product-msg');
    msg.empty();
    msg.hide();

    if (!name || !price || !total || !categoryId) {
        msg.text('请将信息填写完整');
        msg.show();
        return;
    }

    ajax_upload(function (result) {
        if (result.code === 1) {
            let image = result.data;
            if (image) {
                const url = '/api/product/add';
                $.post(url,
                    {
                        name: name,
                        image: image,
                        price: price,
                        description: desc,
                        total: total,
                        category_id: categoryId
                    },
                    function (ret) {
                        if (ret.code === 1 && ret.data != null) {
                            $('#product-name').val('');
                            $('#product-price').val('');
                            $('#product-total').val('');
                            $('#product-category').val('');
                            $('#product-desc').val('');
                            msg.text('');
                            msg.hide();
                            // 刷新列表
                            loadProductList();
                        }
                    });
            }
        } else {
            msg.text(result.msg);
            msg.show();
        }
    })
}

/**
 * 加载产品列表
 */
function loadProductList() {
    $('.product-item-update').hide();
    const url = '/api/product/get/all';
    $.get(url, function (result) {
        if (result.code === 1) {
            let content = '';
            for (let i = 0; i < result.data.length; i++) {
                let product = result.data[i];
                content += ' <div class="product-item"> ';
                content += '    <a href="javascript:" class="list-group-item list-group-item-action" ';
                content += '        onclick="showOrHidden(this, \'product-item-update\')">';
                content += product.name;
                content += ' : ';
                content += product.price;
                content += '    </a> ';
                content += '    <div class="product-item-update" style="margin-top: 10px"> ';
                content += '        <div class="input-group mb-3"> ';
                content += '            <div class="input-group-prepend"> ';
                content += '                <span class="input-group-text">名称：</span> ';
                content += '            </div> ';
                content += '            <input type="text" class="form-control" value="';
                content += product.name;
                content += '"/> ';
                content += '        </div> ';
                content += '        <div class="input-group mb-3"> ';
                content += '            <div class="input-group-prepend"> ';
                content += '                <span class="input-group-text">价格：</span> ';
                content += '            </div> ';
                content += '            <input type="number" class="form-control" value="';
                content += product.price;
                content += '"/> ';
                content += '        </div> ';
                content += '        <div class="input-group mb-3"> ';
                content += '            <div class="input-group-prepend"> ';
                content += '                <span class="input-group-text">数量：</span> ';
                content += '            </div> ';
                content += '            <input type="number" class="form-control" value="';
                content += product.total;
                content += '"/> ';
                content += '        </div> ';
                content += '        <div class="input-group mb-3"> ';
                content += '            <div class="input-group-prepend"> ';
                content += '                <span class="input-group-text">类别：</span> ';
                content += '            </div> ';
                content += '            <select class="form-control category-all"> ';
                content += '                <option value="1">1</option> ';
                content += '                <option value="2">2</option> ';
                content += '                <option value="3">3</option> ';
                content += '            </select> ';
                content += '        </div> ';
                content += '        <div class="input-group mb-3"> ';
                content += '            <div class="input-group-prepend"> ';
                content += '                <span class="input-group-text">描述：</span> ';
                content += '            </div> ';
                content += '            <textarea class="form-control">';
                content += product.description;
                content += '</textarea> ';
                content += '        </div> ';
                content += '        <div class="input-group mb-3 error-wrapper"></div> ';
                content += '        <div class="input-group mb-3"> ';
                content += '            <button class="btn btn-success" onclick="updateProduct(this, ';
                content += product.id;
                content += ')" style="width: 50%">修改</button> ';
                content += '            <button class="btn btn-warning" onclick="deleteProduct(this, ';
                content += product.id;
                content += ')" style="width: 50%">删除</button> ';
                content += '        </div> ';
                content += '    </div> ';
                content += '</div> ';
            }
            const productList = $('#product-list');
            productList.empty();
            productList.html(content);
            fullCategory();
        } else if (result.code === 4) {
            location.href = '/admin/login.html'
        }
    })
}

function deleteProduct(btn, id) {
    let url = '/api/product/delete/' + id;
    $.ajax({
        url: url,
        type: 'delete',
        success: function (result) {
            if (result.code === 1) {
                $(btn).parent().parent().parent().hide();
            }
        }
    })
}

function updateProduct(btn, id) {
    let name = $(btn).parent().siblings().eq(0).children('input').val();
    let price = $(btn).parent().siblings().eq(1).children('input').val();
    let total = $(btn).parent().siblings().eq(2).children('input').val();
    let categoryId = $(btn).parent().siblings().eq(3).children('select').val();
    let desc = $(btn).parent().siblings().eq(4).children('textarea').val();
    const msg = $(btn).parent().siblings().eq(5);
    msg.hide();

    if (!name || !price || !total || !categoryId) {
        msg.text('请将信息填写完整');
        msg.show();
        return;
    }

    const url = '/api/product/update';
    $.post(url,
        {
            id: id,
            name: name,
            price: price,
            description: desc,
            total: total,
            category_id: categoryId
        },
        function (ret) {
            if (ret.code === 1 && ret.data != null) {
                msg.text('');
                msg.hide();
                const a = $(btn).parent().parent().siblings().eq(0);
                a.empty();
                a.text(name + ' : ' + price)
            } else {
                msg.text(ret.msg);
                msg.show();
            }
        });

}


function fullCategory() {
    getAllCategory(function (categoryList) {
        let content = '';
        for (let i = 0; i < categoryList.length; i++) {
            let category = categoryList[i];
            content += '<option value="' + category.id + '">' + category.name + '</option>';
        }
        let selects = $('.category-all');
        for (let i = 0; i < selects.length; i++) {
            let select = $(selects[i]);
            select.empty();
            select.html(content);
        }
    })
}

function createXHR() {
    let xhr = null;
    if (window.XMLHttpRequest)  //要是支持XMLHttpRequest的则采用XMLHttpRequest生成对象
        xhr = new XMLHttpRequest();
    else if (window.ActiveXobject)//要是支持win的ActiveXobject则采用ActiveXobject生成对象。
        xhr = new ActiveXobject('Microsoft.XMLHTTP');
    return xhr;
}

function ajax_upload(callback) {
    let xhr = createXHR();
    let formData = new FormData();
    let file = document.getElementById('product-image').files[0];//获取文件列表中的第一个文件，html5中支持多个文件上传
    const msg = $('#product-msg');
    msg.empty();
    msg.hide();
    if (!file) {
        msg.text('图片不能为空');
        msg.show();
        return;
    }
    formData.append('file', file);//加载图片文件
    xhr.open('POST', '/api/product/file/upload', true);
    xhr.send(formData);
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            callback(JSON.parse(this.responseText));
        }

    }
}