<template>
  <div class="add-book">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>新增图书</span>
          <el-button @click="goBack">
            <el-icon><ArrowLeft /></el-icon>
            返回列表
          </el-button>
        </div>
      </template>

      <el-form
        ref="formRef"
        :model="bookForm"
        :rules="rules"
        label-width="100px"
        style="max-width: 600px"
      >
        <el-form-item label="书本图片" prop="image">
          <el-upload
            class="image-uploader"
            :action="uploadAction"
            :show-file-list="false"
            :on-success="handleImageSuccess"
            :before-upload="beforeImageUpload"
            :headers="uploadHeaders"
          >
            <img v-if="bookForm.image" :src="bookForm.image" class="image" />
            <el-icon v-else class="image-uploader-icon"><Plus /></el-icon>
          </el-upload>
          <div class="upload-tip">
            只能上传jpg/png格式的图片，且不超过500kb
          </div>
        </el-form-item>

        <el-form-item label="书名" prop="name">
          <el-input v-model="bookForm.name" placeholder="请输入书名" />
        </el-form-item>

        <el-form-item label="作者" prop="author">
          <el-input v-model="bookForm.author" placeholder="请输入作者" />
        </el-form-item>

        <el-form-item label="介绍" prop="description">
          <el-input
            v-model="bookForm.description"
            type="textarea"
            :rows="4"
            placeholder="请输入图书介绍"
          />
        </el-form-item>

        <el-form-item label="定价" prop="price">
          <el-input-number
            v-model="bookForm.price"
            :precision="2"
            :step="0.1"
            :min="0"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="出版社" prop="publisher">
          <el-input v-model="bookForm.publisher" placeholder="请输入出版社" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm" :loading="loading">
            保存
          </el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { ArrowLeft, Plus } from '@element-plus/icons-vue'
import { bookApi } from '@/api'

export default {
  name: 'AddBookView',
  components: {
    ArrowLeft,
    Plus
  },
  setup() {
    const router = useRouter()
    const formRef = ref()
    const loading = ref(false)    // 表单数据
    const bookForm = reactive({
      name: '',
      author: '',
      description: '',
      price: 0,
      publisher: '',
      image: ''
    })    // 表单验证规则
    const rules = {
      name: [
        { required: true, message: '请输入书名', trigger: 'blur' }
      ],
      author: [
        { required: true, message: '请输入作者', trigger: 'blur' }
      ],
      description: [
        { required: true, message: '请输入图书介绍', trigger: 'blur' }
      ],
      price: [
        { required: true, message: '请输入价格', trigger: 'blur' },
        { type: 'number', min: 0, message: '价格必须大于等于0', trigger: 'blur' }
      ],
      publisher: [
        { required: true, message: '请输入出版社', trigger: 'blur' }
      ]
    }

    // 上传配置
    const uploadAction = '/api/upload/image'
    const uploadHeaders = {}

    // 图片上传成功处理
    const handleImageSuccess = (response) => {
      if (response.code === 200) {
        bookForm.image = response.data
        ElMessage.success('图片上传成功')
      } else {
        ElMessage.error(response.msg || '图片上传失败')
      }
    }

    // 图片上传前检查
    const beforeImageUpload = (file) => {
      const isImage = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt500K = file.size / 1024 < 500

      if (!isImage) {
        ElMessage.error('只能上传 JPG/PNG 格式的图片!')
        return false
      }
      if (!isLt500K) {
        ElMessage.error('图片大小不能超过 500KB!')
        return false
      }
      return true
    }

    // 提交表单
    const submitForm = async () => {
      if (!formRef.value) return

      try {
        await formRef.value.validate()
        loading.value = true

        await bookApi.addBook(bookForm)
        ElMessage.success('新增图书成功')
        router.push('/')
      } catch (error) {
        if (error !== false) {
          console.error('新增图书失败:', error)
          ElMessage.error('新增图书失败')
        }
      } finally {
        loading.value = false
      }
    }

    // 重置表单
    const resetForm = () => {
      if (!formRef.value) return
      formRef.value.resetFields()
      bookForm.image = ''
    }

    // 返回列表
    const goBack = () => {
      router.push('/')
    }

    return {
      formRef,
      loading,
      bookForm,
      rules,
      uploadAction,
      uploadHeaders,
      handleImageSuccess,
      beforeImageUpload,
      submitForm,
      resetForm,
      goBack
    }
  }
}
</script>

<style scoped>
.add-book {
  max-width: 800px;
  margin: 0 auto;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.image-uploader {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: all 0.3s;
}

.image-uploader:hover {
  border-color: #409eff;
}

.image-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 148px;
  height: 148px;
  line-height: 148px;
  text-align: center;
}

.image {
  width: 148px;
  height: 148px;
  display: block;
  object-fit: cover;
}

.upload-tip {
  margin-top: 8px;
  color: #999;
  font-size: 12px;
}
</style>