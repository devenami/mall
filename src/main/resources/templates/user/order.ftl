<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/user/car.css"/>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/js/user/car.js"></script>
</head>
<body class="container">

<span style="display: block;font-size: 30px; text-align: center; line-height: 30px; margin: 20px">订单列表</span>

<div class="list-group" id="address-list">

    <#list list as order>
        <div class="address-item">
            <a href="javascript:" class="list-group-item list-group-item-action">
                收货人：${order.name}
                - 电话：${order.phone}
                - 商品名称${order.productName}
                - 下单时间${(order.createTime)?string('yyyy-MM-dd hh:mm:ss')}
                <br/>
                状态：
                <div style="display: inline">
                <#if order.status == 1>
                    已付款未发货
                <button class="btn btn-success btn-sm" onclick="cancel(this, ${order.id})">取消订单</button>
                </#if>
                 <#if order.status == 2>
                    已发货
                 <button class="btn btn-success btn-sm" onclick="sure(this, ${order.id})">确认收货</button>
                 </#if>
                 <#if order.status == 3>
                    已收货
                 </#if>
                 <#if order.status == 4>
                    已取消
                 </#if>
                </div>
            </a>
        </div>
    </#list>
</div>

</body>
</html>
<script>
    function sure(btn, id) {
        const url = '/api/order/update/status';
        $.post(url, {id: id, status: 3},
                function (result) {
                    if (result.code === 1 && result.data === 1) {
                        const html = $(btn).parent();
                        html.empty();
                        html.text('已收货');
                    }
                });
    }

    function cancel(btn, id) {
        const url = '/api/order/update/status';
        $.post(url, {id: id, status: 4},
                function (result) {
                    if (result.code === 1 && result.data === 1) {
                        const html = $(btn).parent();
                        html.empty();
                        html.text('已取消');
                    }
                });
    }
</script>