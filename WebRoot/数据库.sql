/*
Navicat MySQL Data Transfer

Source Server         : Demo
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : storedb

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-03-25 10:41:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_account`
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `ID` varchar(32) NOT NULL,
  `USERNAME` varchar(20) default NULL,
  `PASSWORD` varchar(32) default NULL,
  `REAL_NAME` varchar(20) default NULL,
  `LINK_PHONE` varchar(20) default NULL,
  `REG_DATE` datetime default NULL,
  `SEX` varchar(2) default NULL,
  `AGE` int(11) default NULL,
  `ADDRESS` varchar(200) default NULL,
  `ID_CARD` varchar(20) default NULL,
  `ROLE_LEVEL` varchar(10) default NULL,
  `STATE` varchar(10) default NULL,
  `NOTE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
INSERT INTO `tb_account` VALUES ('40289a813f90e233013f90e2342b0001', 'admin', '21232F297A57A5A743894A0E4A801FC3', '管理员', '13888888888', '2013-06-30 01:01:51', '男', '18', '四川省成都市', '510111111111111111', '系统管理员', '1', null);

-- ----------------------------
-- Table structure for `tb_atstore`
-- ----------------------------
DROP TABLE IF EXISTS `tb_atstore`;
CREATE TABLE `tb_atstore` (
  `ID` varchar(32) NOT NULL default '',
  `RELATION_ID` varchar(32) default NULL,
  `GOODS_ID` varchar(32) default NULL,
  `AMOUNT` int(11) default NULL,
  `NOTE` varchar(200) default NULL,
  `GOODS_NAME` varchar(50) default NULL,
  `UINT` varchar(10) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_atstore
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_goods`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) default NULL,
  `CODE` varchar(32) default NULL,
  `TYPE` varchar(20) default NULL,
  `SPECIFICATION` varchar(20) default NULL,
  `PRODUCTION_DATE` date default NULL,
  `SHELF_LIFE` varchar(20) default NULL,
  `UNIT` varchar(20) default NULL,
  `PRICE` varchar(20) default NULL,
  `NOTE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES ('402881be4c4ec158014c4ec769c90001', '可口可乐', 'cola', '饮料', '500ML', '2015-03-25', '12', '瓶', '3', '好喝的东西');
INSERT INTO `tb_goods` VALUES ('402881be4c4ec158014c4ec813720002', '肉松饼', 'rsb', '饼子', '40克', '2015-03-05', '12', '个', '2', '董弱鸡代言');

-- ----------------------------
-- Table structure for `tb_instore`
-- ----------------------------
DROP TABLE IF EXISTS `tb_instore`;
CREATE TABLE `tb_instore` (
  `ID` varchar(32) NOT NULL default '',
  `GOODS_ID` varchar(32) default NULL,
  `PROVIDER_ID` varchar(32) default NULL,
  `USER_ID` varchar(32) default NULL,
  `IN_DATE` datetime default NULL,
  `PRICE` varchar(20) default NULL,
  `AMOUNT` int(11) default NULL,
  `NOTE` varchar(200) default NULL,
  `GOODS_NAME` varchar(50) default NULL,
  `PROVIDER_NAME` varchar(50) default NULL,
  `USERNAME` varchar(32) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_instore
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_outstore`
-- ----------------------------
DROP TABLE IF EXISTS `tb_outstore`;
CREATE TABLE `tb_outstore` (
  `ID` varchar(32) NOT NULL default '',
  `GOODS_ID` varchar(32) default NULL,
  `CONSUMER_ID` varchar(32) default NULL,
  `USER_ID` varchar(32) default NULL,
  `OUT_DATE` datetime default NULL,
  `PRICE` varchar(20) default NULL,
  `AMOUNT` int(11) default NULL,
  `NOTE` varchar(200) default NULL,
  `GOODS_NAME` varchar(50) default NULL,
  `CONSUMER_NAME` varchar(50) default NULL,
  `USERNAME` varchar(50) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_outstore
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_partner`
-- ----------------------------
DROP TABLE IF EXISTS `tb_partner`;
CREATE TABLE `tb_partner` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) default NULL,
  `TYPE` varchar(20) default NULL,
  `PROPERTY` varchar(20) default NULL,
  `ADDRESS` varchar(100) default NULL,
  `LINK_MAN` varchar(20) default NULL,
  `LINK_PHONE` varchar(20) default NULL,
  `TELPHONE` varchar(20) default NULL,
  `NOTE` varchar(200) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_partner
-- ----------------------------
INSERT INTO `tb_partner` VALUES ('402881be4c4ec158014c4ec92a1f0003', '董弱鸡', 'provider', '个体', '召唤师峡谷', '董弱鸡', '13888888888', '028-88888888', '吃了弱鸡的小吃立马超神');
INSERT INTO `tb_partner` VALUES ('402881be4c4ec158014c4ec9dc8f0004', '红旗连锁', 'consumer', '超市', '成都市总府路', '盖伦', '13788888888', '028-77777777', '阿斯顿发送到发送到');
INSERT INTO `tb_partner` VALUES ('402881be4c4ec158014c4ecab77e0005', '可口可乐-成都', 'provider', '公司', '成都市新都区可口可乐园区', '王大花', '13666667777', '028-99887766', '撒打发士大夫');
INSERT INTO `tb_partner` VALUES ('402881be4c4ec158014c4ecbd4770006', '舞东风', 'consumer', '超市', '富土康三号流水车间质检部', '张全蛋', '13333334444', '028-22223333', 'Nokla');
