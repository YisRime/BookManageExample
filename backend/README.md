# 图书管理系统

基于Spring Boot + MyBatis-Plus + MySQL的图书管理系统后端项目。

## 项目特性

- **前后端分离架构**：提供RESTful API接口
- **分页查询**：支持按书名模糊查询的分页列表
- **文件上传**：支持图书封面图片上传
- **软删除机制**：删除操作只标记，不物理删除数据
- **统一响应格式**：标准化的API响应结构
- **跨域支持**：配置CORS允许前端访问

## 技术栈

- **后端框架**：Spring Boot 2.7.0
- **ORM框架**：MyBatis-Plus 3.5.2
- **数据库**：MySQL 8.0
- **构建工具**：Maven
- **JDK版本**：Java 8

## 快速开始

### 1. 环境准备

- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+

### 2. 数据库配置

1. 创建数据库并执行初始化脚本：

```cmd
mysql -u root -p < src/main/resources/sql/init.sql
```

**或者直接在MySQL客户端中执行:**

```sql
source src/main/resources/sql/init.sql;
```

2. 修改 `application.yml` 中的数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/book_manage
    username: your_username
    password: your_password
```

### 3. 运行项目

```bash
# 编译项目
mvn clean compile

# 运行项目
mvn spring-boot:run
```

项目启动后访问：<http://localhost:8080>

## API接口

### 图书管理

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/books` | 获取所有图书 |
| GET | `/books/{id}` | 根据ID获取图书详情 |
| POST | `/books` | 新增图书 |
| PUT | `/books` | 更新图书信息 |
| DELETE | `/books/{id}` | 删除图书（软删除） |

### 文件上传

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/upload/image` | 上传图片文件 |

## 配置说明

### 文件上传配置

- 上传路径：`d:/uploads/`（可在application.yml中修改）
- 支持格式：图片文件
- 文件大小限制：单个文件最大10MB

### 软删除

- 使用`deleted`字段标记删除状态
- 0：未删除，1：已删除
- 查询时自动过滤已删除记录

## 开发说明

1. 实体类手动编写getter/setter方法
2. 使用MyBatis-Plus提供的BaseMapper和IService
3. 统一使用R类封装API响应
4. 配置了自动填充创建时间和更新时间
5. 支持跨域访问，便于前端开发