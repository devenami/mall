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

<header>

    <div class="content">
        <span class="title">Welcome Visiting My Store</span>
        <div class="input-group">
            <input type="text" class="form-control">
            <div class="input-group-append">
                <button class="btn btn-outline-primary" type="button">搜索</button>
            </div>
        </div>

        <div class="user">
            <a href="/user/car.html">购物车</a>
            <a href="/user/order.html">订单</a>
        <#if Session.user??>
            <a href="/user/user.html">${Session.user.username}</a>
        <#else>
            <a href="/user/register.html">注册</a>
            <a href="/user/login.html">登录</a>
        </#if>
        </div>
    </div>
</header>

<section class="category">
    <div class="bg-primary cat-group">
        <span>一级分类：</span>
        <select id="cat-0" class="bg-primary" onchange="selectCategory(this)">
        </select>
    </div>
    <div class="bg-primary cat-group">
        <span>二级分类：</span>
        <select id="cat-1" class="bg-primary" onchange="selectCategory(this)">
        </select>
    </div>
    <div class="bg-primary cat-group">
        <span>三级分类：</span>
        <select id="cat-2" class="bg-primary" onchange="selectCategory(this)">
        </select>
    </div>
</section>

<section class="hot">
    <hr/>
    <span class="hint">热卖：</span>
    <div class="products" id="hot-list">

    </div>
</section>

<footer>

</footer>

</body>
</html>