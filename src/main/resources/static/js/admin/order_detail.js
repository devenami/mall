function updateOrderStatus(id, status) {

    const url = '/api/order/update/status';

    $.post(url, {id: id, status: status}, function (result) {
        if (result.code === 1) {
            loadPage('/admin/order.html');
        } else {
            const  msg = $('#order-msg');
            msg.empty();
            msg.text(result.msg);
            msg.show();
        }
    })
}