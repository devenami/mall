# mall

## 基本介绍
实现一个简单的在线商城系统，主要包含用户端（前端）和管理员端（后台）

## 使用的技术
* SpringBoot 
* Mybatis 
* Freemarker

## 数据库
* mysql

## 做了什么？
* SpringMvc 统一数据返回格式
* SpringMvc 全局异常处理
* SpringMvc 拦截器
* Swagger2 配置
* Swagger 注解
* Mybatis 注解
* Mybatis 拦截器实现分页
* Spring Task 定时任务
* FTP 文件传输
* Nginx 静态资源代理

## BUG FIX
* 修复分页拦截器中，由于try-with-resource结构导致的 SQL 查询时间早于参数赋值时间而产生的BUG。
* 修复分页拦截器中，由于 in 查询导致的sql 无法赋值错误
