<link rel="stylesheet" href="/css/admin/order_detail.css"/>


<div class="order-item-update">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">商品名称：</span>
        </div>
        <input type="text" class="form-control" disabled value="${order.productName}"/>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">商品价格：</span>
        </div>
        <input type="text" class="form-control" disabled value="${order.price}"/>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">商品数量：</span>
        </div>
        <input type="text" class="form-control" disabled value="${order.number}"/>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">商品总价：</span>
        </div>
        <input type="text" class="form-control" disabled value="${order.total}"/>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">收货人：</span>
        </div>
        <input type="text" class="form-control" disabled value="${order.name}"/>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">联系电话：</span>
        </div>
        <input type="text" class="form-control" disabled value="${order.phone}"/>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text">收货地址：</span>
        </div>
        <input type="text" class="form-control" disabled value="${order.address}"/>
    </div>
    <div class="input-group mb-3 error-wrapper" id="order-msg"></div>
    <div class="input-group mb-3">
        <button class="btn btn-success" onclick="updateOrderStatus(${order.id}, 2)" style="width: 50%">发货
        </button>
        <button class="btn btn-success" onclick="updateOrderStatus(${order.id}, 4)" style="width: 50%">取消
        </button>
    </div>
</div>


<script src="/js/admin/order_detail.js"></script>
