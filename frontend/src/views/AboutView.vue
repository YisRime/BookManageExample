<template>
  <div class="about">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>关于系统</span>
        </div>
      </template>

      <div class="content">
        <h2>图书管理系统</h2>
        <div class="version-info">
          <el-tag type="success">版本: v1.0.0</el-tag>
          <el-tag type="info" style="margin-left: 10px">开发者：无</el-tag>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'AboutView'
}
</script>

<style scoped>
.about {
  max-width: 800px;
  margin: 0 auto;
}

.content {
  line-height: 1.6;
}

.content h2 {
  color: #409eff;
  margin-bottom: 16px;
}

.content h3 {
  color: #333;
  margin: 24px 0 12px 0;
}

.content ul {
  padding-left: 20px;
}

.content li {
  margin-bottom: 8px;
}

.version-info {
  margin-top: 30px;
  padding-top: 20px;
  border-top: 1px solid #eee;
}
</style>