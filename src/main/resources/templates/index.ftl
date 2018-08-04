<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/index.css"/>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.bundle.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/js/index.js"></script>
</head>
<body>

<header class="bg-222">

    <div class="content">
        <span class="title">Welcome Visiting My Store</span>
        <div class="input-group">
            <input type="text" class="form-control" id="search_keyword"/>
            <div class="input-group-append">
                <button class="btn btn-outline-primary" type="button" onclick="searchProduct()">搜索</button>
            </div>
        </div>

        <div class="user">
            <a href="/user/car.html" target="_blank">购物车</a>
            <a href="/user/order.html" target="_blank">订单</a>
        <#if Session.user??>
            <a href="/user/user.html" target="_blank">${Session.user.username}</a>
        <#else>
            <a href="/user/register.html" target="_blank">注册</a>
            <a href="/user/login.html" target="_blank">登录</a>
        </#if>
        </div>
    </div>
</header>

<section class="category">
    <div class="bg-222 cat-group">
        <span>一级分类：</span>
        <select id="cat-0" class="bg-222" onchange="selectCategory(this)">
        </select>
    </div>
    <div class="bg-222 cat-group">
        <span>二级分类：</span>
        <select id="cat-1" class="bg-222" onchange="selectCategory(this)">
        </select>
    </div>
    <div class="bg-222 cat-group">
        <span>三级分类：</span>
        <select id="cat-2" class="bg-222" onchange="selectCategory(this)">
        </select>
    </div>
</section>

<section class="hot">
    <hr/>
    <span class="hint">热卖：</span>
    <div class="products" id="hot-list"></div>
</section>

<section class="product">
    <hr/>
    <span class="hint">产品：</span>
    <div class="products" id="product-list"></div>
    <div class="page">
        <button class="btn-sm bg-222" onclick="jumpToPage(0)">上一页</button>
        &nbsp;&nbsp;
        当前第
        <span class="curr-page" id="page-no">1</span>
        页
        &nbsp;&nbsp;
        共
        <span class="total-page" id="page-total">1</span>
        页
        &nbsp;&nbsp;
        <button class="btn-sm bg-222" onclick="jumpToPage(1)">下一页</button>
    </div>
</section>

<footer class="bg-222">
    Copyright &copy;2018-2020 MY Corporation, All Rights Reserved
</footer>

</body>
</html>