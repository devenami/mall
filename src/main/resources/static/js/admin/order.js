$(function () {
    loadOrderList();
});

function loadOrderList() {
    const orderContainer = $('#order-list');
    orderContainer.empty();
    const url = '/api/order/get/page/admin';
    $.get(url, {page_no: 0, page_size: 10000}, function (result) {
        if (result.code === 1 && result.data) {
            let content = '';
            let orderList = result.data;
            for (let i = 0; i < orderList.length; i++) {
                let order = orderList[i];
                content += '<a href="javascript:" onclick="loadOrderDetail(';
                content += order.id;
                content += ')" class="list-group-item list-group-item-action">';
                content += '编号：';
                content += order.id;
                content += ' 商品名：';
                content += order.productName;
                content += ' 状态：';
                if (order.status === 0) {
                    content += '未付款';
                }
                if (order.status === 1) {
                    content += '已付款未发货';
                }
                if (order.status === 2) {
                    content += '已发货';
                }
                if (order.status === 3) {
                    content += '已收货';
                }
                if (order.status === 4) {
                    content += '取消';
                }
                content += '</a>\n';
            }
            orderContainer.html(content);
        }
    })
}

function loadOrderDetail(id) {
    loadPage('/admin/order_detail.html?id=' + id);
}