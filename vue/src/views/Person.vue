<template>
  <div class="card" style="width: 40%; margin: 0; padding: 40px 20px">
    <el-form ref = "formRef" :rules = "data.rules" :model="data.form" style = "padding-right: 50px; padding-top: 30px;">
      <el-form-item label="头像" :label-width="formLabelWidth" prop="avatar" style="display: flex; align-items: center;">

        <el-upload
            class="avatar-uploader"
            action="http://localhost:8080/files/upload"
            :show-file-list="false"
            :before-upload="beforeAvatarUpload"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="data.form.avatar" :src="data.form.avatar" class="avatar" alt="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>

      </el-form-item>
      <el-form-item  prop="username" label="用户名" :label-width="formLabelWidth">
        <el-input disabled v-model="data.form.username" autocomplete="off" placeholder = "请输入用户名"/>
      </el-form-item>
      <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
        <el-input v-model="data.form.name" autocomplete="off" placeholder = "请输入姓名"/>
      </el-form-item>

      <div v-if="data.user.role === 'EMP'">
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-radio-group v-model="data.form.gender">
            <el-radio value = "男" label = "男"></el-radio>
            <el-radio value = "女" label = "女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="NO" :label-width="formLabelWidth" prop="no">
          <el-input v-model="data.form.no" disabled autocomplete="off" placeholder = "请输入NO" />
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth">
          <el-input-number style="width: 200px ;" :min = "18" v-model="data.form.age" autocomplete="off" placeholder = "请输入年龄" />
        </el-form-item>
        <el-form-item label="个人简介" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="data.form.description" autocomplete="off" placeholder = "请输入简介" />
        </el-form-item>
      </div>

    </el-form>

    <div style="text-align: center;">
      <el-button type="primary" @click="update" style="padding: 20px 30px;">
        更新个人信息
      </el-button>
    </div>

  </div>
</template>

<script setup>
import {ref, reactive, nextTick} from 'vue'
import request from "@/utils/request.js";
const formRef = ref()
import {ElMessage} from 'element-plus'

const emit = defineEmits(['updateUser'])

const formLabelWidth = '140px'

const data = reactive({
  user: JSON.parse(localStorage.getItem('token')),
  form: {
    avatar: '',
  },
  rules: {
    username: [
      { required: true, message: '请输入用户名', trigger: 'blur' },
      { min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
    ],
    name: [
      { required: true, message: '请输入姓名', trigger: 'blur' },
      { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
    ],
    no: [
      { required: true, message: '请输入NO', trigger: 'blur' },
      { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
    ],
  },
})


if (data.user.role === 'EMP'){
  request.get('staff/selectById', {params: {id: data.user.id}}).then(res => {
    data.form = res.data[0]
  }).catch()
}else if (data.user.role === 'ADMIN'){
  data.form = data.user
}

const update = () => {
  if (data.user.role === 'EMP') {
    request.put('staff/update', data.form).then(res => {
      if(res.code === '200'){
        localStorage.setItem('token', JSON.stringify(data.form))
        emit('updateUser')
        ElMessage.success('更新成功')

      }else{
        ElMessage.error(res.msg)
      }
    }).catch()
  }else if (data.user.role === 'ADMIN'){
    request.put('admin/update', data.form).then(res => {
      if(res.code === '200'){
        //更新token
        localStorage.setItem('token', JSON.stringify(data.form))
        ElMessage.success('更新成功')
        emit('updateUser')
      }else{
        ElMessage.error(res.msg)
      }
    }).catch()
  }
}

const beforeAvatarUpload = (file) => {
  const isJPG = file.type === 'image/jpeg'
  const isPNG = file.type === 'image/png'
  const isLt2M = file.size / 1024 / 1024 < 2

  if (!isJPG && !isPNG) {
    ElMessage.error('上传头像图片只能是 JPG 或者 PNG 格式!')
    return false
  }
  if (!isLt2M) {
    ElMessage.error('上传头像图片大小不能超过 2MB!')
    return false
  }
  return true
}

const handleAvatarSuccess = (res) => {
  data.form.avatar = res.data
}



</script>

<style scoped>

</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
}

.avatar {
  width: 100px;
  height: 100px;
  object-fit: cover;
}
</style>