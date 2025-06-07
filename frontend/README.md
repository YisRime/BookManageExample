# 图书管理系统前端

基于Vue 3 + Element Plus的图书管理系统前端项目。

## 项目特性

- **现代化技术栈**：Vue 3 Composition API + Element Plus
- **响应式设计**：适配不同屏幕尺寸
- **完整功能**：图书增删改查、搜索、分页
- **图片上传**：支持书籍封面上传
- **用户体验**：加载状态、消息提示、表单验证

## 技术栈

- **前端框架**：Vue 3 (Composition API)
- **UI组件库**：Element Plus
- **路由管理**：Vue Router 4
- **状态管理**：Vuex 4
- **HTTP请求**：Axios
- **构建工具**：Vue CLI 5

## 快速开始

### 1. 环境准备

- Node.js 14+
- npm 6+ 或 yarn

### 2. 安装依赖

```bash
cd frontend
npm install
```

### 3. 启动开发服务器

```bash
npm run serve
```

项目启动后访问：http://localhost:3000

### 4. 构建生产版本

```bash
npm run build
```

## 项目结构

```
frontend/
├── public/
│   └── index.html          # HTML模板
├── src/
│   ├── api/
│   │   └── index.js        # API接口
│   ├── router/
│   │   └── index.js        # 路由配置
│   ├── store/
│   │   └── index.js        # Vuex状态管理
│   ├── utils/
│   │   └── request.js      # HTTP请求工具
│   ├── views/
│   │   ├── HomeView.vue    # 首页列表
│   │   ├── AboutView.vue   # 关于页面
│   │   ├── AddBookView.vue # 新增图书
│   │   └── EditBookView.vue # 编辑图书
│   ├── App.vue             # 根组件
│   └── main.js             # 入口文件
├── babel.config.js         # Babel配置
├── vue.config.js           # Vue CLI配置
└── package.json            # 项目配置
```

## 功能说明

### 1. 图书列表页面 (/)
- 📋 分页表格展示所有图书
- 🔍 按书名搜索功能
- ➕ 新增图书按钮
- ✏️ 编辑图书功能
- 🗑️ 删除图书功能（确认弹窗）
- 📄 自定义分页大小（10/20/50/100）

### 2. 新增图书页面 (/add-book)
- 📝 完整的图书信息表单
- 📸 图书封面上传（jpg/png，500kb限制）
- ✅ 表单验证
- 💾 提交后自动跳转

### 3. 编辑图书页面 (/edit-book/:id)
- 📄 自动回填原有数据
- 🔒 图书ID只读显示
- 📸 支持重新上传封面
- ✅ 修改后跳转首页

### 4. 关于页面 (/about)
- 📖 系统介绍和技术栈展示

## API接口

项目通过代理连接后端服务（http://localhost:8080）：

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | /books | 分页查询图书列表 |
| GET | /books/{id} | 获取图书详情 |
| POST | /books | 新增图书 |
| PUT | /books | 更新图书 |
| DELETE | /books/{id} | 删除图书 |
| POST | /upload/image | 上传图片 |

## 开发配置

### 代理配置
在 `vue.config.js` 中配置了开发环境的代理：

```javascript
proxy: {
  '/api': {
    target: 'http://localhost:8080',
    changeOrigin: true,
    pathRewrite: {
      '^/api': ''
    }
  }
}
```

### 请求拦截器
在 `src/utils/request.js` 中配置了统一的请求和响应处理。

## 部署说明

1. 构建项目：`npm run build`
2. 将 `dist` 目录部署到Web服务器
3. 配置代理转发API请求到后端服务

## 浏览器支持

- Chrome >= 87
- Firefox >= 78
- Safari >= 14
- Edge >= 88