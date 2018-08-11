$(function () {
    fullCategory();
    fullCategoryList();
});

function fullCategory() {
    getAllCategory(function (categoryList) {
        let content = '<option value="0"></option>';
        for (let i = 0; i < categoryList.length; i++) {
            let category = categoryList[i];
            content += '<option value="' + category.id + '">' + category.name + '</option>';
        }
        const select = $('#category-parent-id');
        select.empty();
        select.html(content);
    })
}

function addCategory() {

    let name = $('#category-name').val();
    let parentId = $('#category-parent-id').val();

    const msg = $('#category-msg');
    msg.hide();
    msg.empty();

    if (!name || !parentId) {
        msg.text('请填写分类名称');
        msg.show();
        return;
    }

    const url = '/api/category/add';

    $.post(url, {parent_id: parentId, name: name}, function (result) {
        if (result.code === 1 && result.data) {
            loadPage('/admin/category.html')
        } else {
            msg.text(result.msg);
            msg.show();
        }
    });

}


// 启动时填充所有分类
function fullCategoryList() {
    const url = '/api/category/get';

    $.get(url, function (result) {
        const data = result.data;
        setFirstCategory(data);

    });
}


// 用户选择产品分类的触发的函数
function selectCategory(span, id) {
    let listId = $(span).parent().parent().attr('id');

    const url = '/api/category/get';
    $.get(url,
        {
            id: id
        },
        function (result) {
            let categories = result.data;

            if (listId === 'c1-list') {
                setSecondCategory(categories);
            }
            if (listId === 'c2-list') {
                setThirdCategory(categories)
            }

        });
}

function deleteCategory(btn, id) {
    let url = '/api/category/delete/' + id;
    $.ajax({
        url: url,
        type: 'delete',
        success: function (result) {
            if (result.code === 1) {
                loadPage('/admin/category.html');
            } else {
                const msg = $('#id-float-msg');
                msg.empty();
                msg.text(result.msg);
                msg.show();
                setInterval(function () {
                    msg.hide();
                }, 5000)
            }
        }
    })
}

function updateCategory(btn, id) {
    let url = '/admin/category_update.html?id=' + id;
    loadPage(url);
}

// 填充一级分类，同时填充子分类
function setFirstCategory(firstCategories) {
    const firstList = $('#c1-list');
    firstList.empty();
    let content = '一级分类：';
    for (let i = 0; i < firstCategories.length; i++) {
        const firstCategory = firstCategories[i];
        content += '<div class="list-group-item list-group-item-action">';
        content += '    <span onclick="selectCategory(this, ';
        content += firstCategory.id;
        content += ')">';
        content += firstCategory.name;
        content += '</span>';
        content += '    <button class="btn btn-warning btn-sm" onclick="deleteCategory(this, ';
        content += firstCategory.id;
        content += ')">删除</button>';
        content += '    <button class="btn btn-success btn-sm" onclick="updateCategory(this, ';
        content += firstCategory.id;
        content += ')">修改</button>';
        content += '</div> ';
    }
    firstList.html(content);

    // 填充二级分类
    let firstCategory = firstCategories[0];
    if (firstCategory) {
        let secondCategories = firstCategory.subCategories;
        setSecondCategory(secondCategories);
    }
}

// 填充二级分类，同时填充子分类
function setSecondCategory(secondCategories) {
    const secondSelect = $('#c2-list');
    secondSelect.empty();
    let content = '二级分类';
    for (let i = 0; i < secondCategories.length; i++) {
        let secondCategory = secondCategories[i];
        content += '<div class="list-group-item list-group-item-action">';
        content += '    <span onclick="selectCategory(this, ';
        content += secondCategory.id;
        content += ')">';
        content += secondCategory.name;
        content += '</span>';
        content += '    <button class="btn btn-warning btn-sm" onclick="deleteCategory(this, ';
        content += secondCategory.id;
        content += ')">删除</button>';
        content += '    <button class="btn btn-success btn-sm" onclick="updateCategory(this, ';
        content += secondCategory.id;
        content += ')">修改</button>';
        content += '</div> ';
    }
    secondSelect.html(content);

    // 填充三级分类
    let secondCategory = secondCategories[0];
    if (secondCategory) {
        let thirdCategories = secondCategory.subCategories;
        setThirdCategory(thirdCategories);
    }
}

// 填充三级分类
function setThirdCategory(thirdCategories) {
    let thirdSelect = $('#c3-list');
    thirdSelect.empty();
    let content = '三级分类：';
    for (let i = 0; i < thirdCategories.length; i++) {
        let thirdCategory = thirdCategories[i];
        content += '<div class="list-group-item list-group-item-action">';
        content += '    <span>';
        content += thirdCategory.name;
        content += '</span>';
        content += '    <button class="btn btn-warning btn-sm" onclick="deleteCategory(this, ';
        content += thirdCategory.id;
        content += ')">删除</button>';
        content += '    <button class="btn btn-success btn-sm" onclick="updateCategory(this, ';
        content += thirdCategory.id;
        content += ')">修改</button>';
        content += '</div> ';
    }
    thirdSelect.html(content);
}
