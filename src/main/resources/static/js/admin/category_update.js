function updateCategory(btn, id) {

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

    const url = '/api/category/update';

    $.post(url, {id: id, parent_id: parentId, name: name}, function (result) {
        if (result.code === 1 && result.data) {
            loadPage('/admin/category.html')
        } else {
            msg.text(result.msg);
            msg.show();
        }
    });


}
