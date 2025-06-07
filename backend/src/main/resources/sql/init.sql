-- 创建数据库
CREATE DATABASE IF NOT EXISTS book_manage CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE book_manage;

-- 删除已存在的表
DROP TABLE IF EXISTS `book`;

-- 创建图书表
CREATE TABLE IF NOT EXISTS `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `image` varchar(255) DEFAULT NULL COMMENT '图书封面',
  `name` varchar(100) NOT NULL COMMENT '图书名称',
  `description` varchar(500) DEFAULT NULL COMMENT '图书描述',
  `publisher` varchar(100) DEFAULT NULL COMMENT '出版社',
  `author` varchar(100) DEFAULT NULL COMMENT '作者',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `deleted` tinyint(1) DEFAULT 0 COMMENT '删除标志(0:未删除,1:已删除)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`),
  KEY `idx_author` (`author`),
  KEY `idx_publisher` (`publisher`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图书信息表';

-- 插入示例数据
INSERT INTO `book` (`name`, `description`, `publisher`, `author`, `price`) VALUES
('Spring Boot实战', 'Spring Boot框架实战指南，包含大量实用案例和最佳实践', '人民邮电出版社', '张三', 89.00),
('Java核心技术', 'Java编程语言核心技术详解，适合进阶学习', '机械工业出版社', '李四', 98.50),
('MySQL必知必会', '数据库管理系统MySQL的入门到精通教程', '清华大学出版社', '王五', 75.00),
('算法导论', '计算机科学经典教材，算法设计与分析', 'MIT出版社', '赵六', 120.00),
('设计模式', '面向对象设计模式的经典著作', '电子工业出版社', '孙七', 85.80);