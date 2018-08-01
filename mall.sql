/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : mall

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-02 07:43:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `name` varchar(30) NOT NULL COMMENT '收件人',
  `phone` varchar(20) NOT NULL COMMENT '收件方电话',
  `address` varchar(100) NOT NULL COMMENT '收件方地址',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for car_item
-- ----------------------------
DROP TABLE IF EXISTS `car_item`;
CREATE TABLE `car_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `car_id` bigint(20) NOT NULL COMMENT '购物车Id',
  `product_id` bigint(20) NOT NULL COMMENT '产品Id',
  `total` int(11) NOT NULL DEFAULT '1' COMMENT '单个产品的选购数量',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级分类的Id',
  `name` varchar(30) NOT NULL COMMENT '分类的名称',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for hot
-- ----------------------------
DROP TABLE IF EXISTS `hot`;
CREATE TABLE `hot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '产品Id',
  `sell` int(11) NOT NULL DEFAULT '0' COMMENT '卖出的数量',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address_id` bigint(20) NOT NULL COMMENT '收货地址Id',
  `status` varchar(255) NOT NULL DEFAULT '0' COMMENT '订单状态，0：未付款，1：已付款未发货，2：已发货，3：已收货',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL COMMENT '订单Id',
  `total` int(11) NOT NULL COMMENT '产品数量',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '产品名称',
  `image` varchar(100) DEFAULT NULL COMMENT '产品图地址',
  `price` decimal(10,0) NOT NULL COMMENT '产品价格',
  `description` varchar(200) DEFAULT NULL COMMENT '商品描述',
  `is_down` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:未下架，1：已下架',
  `total` int(11) NOT NULL COMMENT '产品总数量',
  `sell` int(11) NOT NULL DEFAULT '0' COMMENT '已卖数量',
  `category_id` bigint(20) NOT NULL COMMENT '所属分类id',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` int(5) NOT NULL DEFAULT '0' COMMENT '用户身份 0：普通用户，1：管理员',
  `create_time` datetime NOT NULL COMMENT '字典',
  `update_time` datetime NOT NULL COMMENT '字典',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
