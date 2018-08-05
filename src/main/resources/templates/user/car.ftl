<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/user/car.css"/>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/js/user/car.js"></script>
</head>
<body class="container">

<span style="display: block;font-size: 30px; text-align: center; line-height: 30px; margin: 20px">购物车详情列表</span>

<div class="list-group" id="address-list">

    <#list list as car>
        <div class="address-item">
            <a href="javascript:" class="list-group-item list-group-item-action"
               onclick="htmlToggle(this)">${car.name}</a>
            <div class="car-item-update">
                <div class="input-group mb-3">
                    <div class="row">
                        <div class="col-sm">
                            <!-- 展示商品图片 -->
                            <img src="${car.image}" class="img-thumbnail">
                        </div>
                        <div class="col-sm">
                            <div class="operate">
                                <div>
                                    名称: ${car.name}
                                </div>
                                <div>
                                    价格: ￥${car.price?c}
                                    <input type="hidden" value="${car.price}"/>
                                </div>
                                <div>
                                    剩余数量: <span">${(car.productTotal - car.sell)?c}</span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">数量：</span>
                    </div>
                    <input type="number" class="form-control" value="${car.total}" onchange="changeTotal(this)"
                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
                           onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">总价：</span>
                    </div>
                    <input type="text" disabled class="form-control" value="${(car.total * car.price)?c}"/>
                </div>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text">收货地址：</span>
                    </div>
                <#--<input type="text" class="form-control"/>-->
                    <select>
                        <#list address as addr>
                            <option value="${addr.id}">${addr.name} - ${addr.phone} - ${addr.address}</option>
                        </#list>
                    </select>
                </div>
                <div class="input-group mb-3">
                    <button class="btn btn-success" style="width: 200px" onclick="addToOrder(this, ${car.id})">下单
                    </button>
                    <button class="btn btn-danger" style="width: 200px" onclick="delFromCar(this, ${car.id})">删除
                    </button>
                </div>
            </div>
        </div>
    </#list>
</div>

<div class="float-msg" id="id-float-msg"></div>

</body>
</html>