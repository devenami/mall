<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css"/>
    <link rel="stylesheet" href="/css/admin/index.css"/>
    <script src="/webjars/jquery/3.3.1-1/jquery.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.bundle.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
    <script src="/js/admin/index.js"></script>
</head>
<body>

<header class="bg-222">

    <div class="content">
        <span class="title">商城后台管理系统</span>

        <div class="user">
        <#if Session.user??>
            <a href="javascript:" onclick="userLogout()">退出</a>

            <a href="javascript:">${Session.user.username}</a>

            <a href="javascript:" onclick="loadPage('/admin/product.html')">商品管理</a>
            <a href="javascript:" onclick="loadPage('/admin/order.html')">订单管理</a>
            <a href="javascript:" onclick="loadPage('/admin/category.html')">分类管理</a>

        <#else>
            <a href="/admin/login.html">登录</a>
        </#if>
        </div>
    </div>
</header>

<div id="content">

</div>

<div class="float-msg" id="id-float-msg"></div>

<footer class="bg-222">
    Copyright &copy;2018-2020 MY Corporation, All Rights Reserved
</footer>

</body>
</html>