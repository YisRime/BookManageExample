<template>
  <div class="home">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>图书列表</span>
          <el-button type="primary" @click="goToAddBook">
            <el-icon><Plus /></el-icon>
            新增图书
          </el-button>
        </div>
      </template>

      <!-- 搜索栏 -->
      <div class="search-bar">
        <el-input
          v-model="searchForm.name"
          placeholder="请输入书名搜索"
          style="width: 300px"
          clearable
          @keyup.enter="handleSearch"
        >
          <template #append>
            <el-button @click="handleSearch">
              <el-icon><Search /></el-icon>
            </el-button>
          </template>
        </el-input>
        <el-button @click="resetSearch" style="margin-left: 10px">重置</el-button>
      </div>

      <!-- 表格 -->
      <el-table
        :data="tableData"
        v-loading="loading"
        stripe
        border
        style="width: 100%"
      >
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="image" label="封面" width="100">
          <template #default="scope">
            <img v-if="scope.row.image" :src="scope.row.image" style="width: 60px; height: 80px; object-fit: cover;" />
            <span v-else>无图片</span>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="书名" min-width="150" />
        <el-table-column prop="author" label="作者" width="120" />
        <el-table-column prop="publisher" label="出版社" width="150" />
        <el-table-column prop="price" label="价格" width="100">
          <template #default="scope">
            ¥{{ scope.row.price }}
          </template>
        </el-table-column>
        <el-table-column prop="description" label="描述" min-width="200" show-overflow-tooltip />
        <el-table-column label="操作" width="160" fixed="right">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <div class="pagination">
        <el-pagination
          v-model:current-page="pagination.current"
          v-model:page-size="pagination.size"
          :page-sizes="[10, 20, 50, 100]"
          :total="pagination.total"
          layout="total, sizes, prev, pager, next, jumper"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Search } from '@element-plus/icons-vue'
import { bookApi } from '@/api'

export default {
  name: 'HomeView',
  components: {
    Plus,
    Search
  },
  setup() {
    const router = useRouter()
    const loading = ref(false)
    const tableData = ref([])    // 搜索表单
    const searchForm = reactive({
      name: ''
    })

    // 分页配置
    const pagination = reactive({
      current: 1,
      size: 10,
      total: 0
    })

    // 获取图书列表
    const getBookList = async () => {
      loading.value = true
      try {
        const params = {
          currentPage: pagination.current,
          pageSize: pagination.size
        }
        
        if (searchForm.name) {
          params.name = searchForm.name
        }

        const response = await bookApi.getBooks(params)
        tableData.value = response.data.records || []
        pagination.total = response.data.total || 0
      } catch (error) {
        console.error('获取图书列表失败:', error)
        ElMessage.error('获取图书列表失败')
      } finally {
        loading.value = false
      }
    }

    // 搜索
    const handleSearch = () => {
      pagination.current = 1
      getBookList()
    }    // 重置搜索
    const resetSearch = () => {
      searchForm.name = ''
      pagination.current = 1
      getBookList()
    }

    // 页面大小改变
    const handleSizeChange = (val) => {
      pagination.size = val
      pagination.current = 1
      getBookList()
    }

    // 当前页改变
    const handleCurrentChange = (val) => {
      pagination.current = val
      getBookList()
    }

    // 跳转到新增页面
    const goToAddBook = () => {
      router.push('/add-book')
    }

    // 编辑图书
    const handleEdit = (row) => {
      router.push(`/edit-book/${row.id}`)
    }

    // 删除图书
    const handleDelete = async (row) => {
      try {
        await ElMessageBox.confirm(
          `确定要删除图书"${row.name}"吗？`,
          '提示',
          {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }
        )

        await bookApi.deleteBook(row.id)
        ElMessage.success('删除成功')
        getBookList()
      } catch (error) {
        if (error !== 'cancel') {
          console.error('删除图书失败:', error)
          ElMessage.error('删除失败')
        }
      }
    }

    onMounted(() => {
      getBookList()
    })

    return {
      loading,
      tableData,
      searchForm,
      pagination,
      handleSearch,
      resetSearch,
      handleSizeChange,
      handleCurrentChange,
      goToAddBook,
      handleEdit,
      handleDelete
    }
  }
}
</script>

<style scoped>
.home {
  max-width: 1200px;
  margin: 0 auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.search-bar {
  margin-bottom: 20px;
}

.pagination {
  margin-top: 20px;
  text-align: center;
}
</style>