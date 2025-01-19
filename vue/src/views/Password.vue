<template>
  <div class="card" style="width: 40%; margin: 0; padding: 40px 20px">
    <el-form ref = "formRef" :rules = "data.rules" :model="data.form" style = "padding-right: 50px; padding-top: 30px;">
      <el-form-item  prop="username" label="用户名" :label-width="formLabelWidth">
        <el-input disabled v-model="data.form.username" autocomplete="off" placeholder = "请输入用户名"/>
      </el-form-item>
      <el-form-item  prop="oldPassword" label="原密码" :label-width="formLabelWidth">
        <el-input v-model="data.form.oldPassword" show-password clearable autocomplete="off" :prefix-icon="Lock" placeholder = "请输入原密码"/>
      </el-form-item>
      <el-form-item prop="newPassword" label="新密码" :label-width="formLabelWidth">
        <el-input v-model="data.form.newPassword" show-password clearable placeholder="请新输入密码" :prefix-icon="Lock" size="large"></el-input>
      </el-form-item>
      <el-form-item prop="confirmPassNew" label="确认新密码" :label-width="formLabelWidth">
        <el-input v-model="data.form.confirmPassNew" show-password clearable placeholder="再次确认新密码" :prefix-icon="Lock" size="large"></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center;">
      <el-button type="primary" @click="updatePassword" style="padding: 20px 30px;">
        立即修改密码
      </el-button>
    </div>
  </div>
</template>

<script setup>

import {reactive, ref} from "vue";
import  {User, Lock} from "@element-plus/icons-vue"
import request from "@/utils/request.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const formRef = ref()
const formLabelWidth = '100px'

const validatePass2 = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== data.form.newPassword) {
    callback(new Error("两次输入密码不一致!"))
  } else {
    callback()
  }
}

const data = reactive({
  token: JSON.parse(localStorage.getItem('token')),
  form: {
    username: JSON.parse(localStorage.getItem('token')).username,
    oldPassword: "",
    newPassword: "",
    confirmPassNew: "",
    role: "",
  },
  rules: {
    oldPassword: [
      { required: true, message: '请输入密码', trigger: 'blur' },
      { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
    ],
    newPassword: [
      { required: true, message: '请输入密码', trigger: 'blur' },
      { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
    ],
    confirmPassNew: [
      { validator: validatePass2, trigger: 'blur' }
    ],
  }

})



const updatePassword = () => {

  formRef.value.validate((valid) => {
    if (valid) {
      data.form.role = data.token.role
      console.log(data.form)
      // 发送请求修改密码
      request.put('/changePassword', data.form).then(
        res => {
          if (res.code === "200") {
            ElMessage.success('修改密码成功')
            // 清除token
            localStorage.removeItem('token')
            // 跳转到登录页面
            setTimeout(() => {
              router.push('/login')
            }, 1000)

          } else {
            ElMessage.error(res.msg)
          }
        })
    } else {
      ElMessage.error('请检查输入密码是否正确')
    }
  })
}


</script>

<style scoped>
</style>