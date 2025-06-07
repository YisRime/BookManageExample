import request from '@/utils/request'

// 图书API接口
export const bookApi = {
  // 分页查询图书列表
  getBooks(params) {
    const { currentPage, pageSize, name } = params
    let url = `/books/${currentPage}/${pageSize}`
    if (name) {
      url += `?name=${encodeURIComponent(name)}`
    }
    return request({
      url: url,
      method: 'get'
    })
  },

  // 根据ID获取图书详情
  getBookById(id) {
    return request({
      url: `/books/${id}`,
      method: 'get'
    })
  },

  // 新增图书
  addBook(data) {
    return request({
      url: '/books',
      method: 'post',
      data
    })
  },

  // 修改图书
  updateBook(data) {
    return request({
      url: '/books',
      method: 'put',
      data
    })
  },

  // 删除图书
  deleteBook(id) {
    return request({
      url: `/books/${id}`,
      method: 'delete'
    })
  }
}

// 文件上传API
export const uploadApi = {
  // 上传图片
  uploadImage(file) {
    const formData = new FormData()
    formData.append('file', file)
    return request({
      url: '/upload/image',
      method: 'post',
      data: formData,
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  }
}