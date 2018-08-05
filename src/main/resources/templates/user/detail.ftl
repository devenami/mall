<html>
<header>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/user/detail.css"/>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/js/user/detail.js"></script>
</header>
<body class="container">
<div class="row">
    <div class="col-sm">
        <!-- 展示商品图片 -->
        <img src="${product.image}" class="img-thumbnail">
    </div>
    <div class="col-sm">
        <div class="operate">
            <div>
                名称: ${product.name}
            </div>
            <div>
                价格: ￥${product.price?c}
                <input type="hidden" id="price" value="${product.price}"/>
            </div>
            <div>
                剩余数量: <span id="less">${(product.total - product.sell)?c}</span>
            </div>
            <div>
                购买数量:
                <button class="btn btn-sm" id="sub">-</button>
                <input type="number" id="order-number" value="1"
                       onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                       onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/>
                <button class="btn btn-sm" id="add">+</button>
                &nbsp;&nbsp;&nbsp;
            </div>
            <div>
                总价格: ￥<span id="order-total-price">${product.price?c}</span>
            </div>
            <div>
                <button id="to-order" class="btn btn-success" onclick="addToCar(${product.id})">加入购物车</button>
            </div>
        </div>

    </div>
</div>
<div class="desc">
    产品描述：<br/>
${product.description}
</div>
<div class="float-msg" id="id-float-msg"></div>

</body>
</html>