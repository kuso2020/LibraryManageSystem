<template>
  <div class="login-container">
    <div class="login-box">
      <div style=" background-color: white;padding: 30px; margin-left: 50px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);">
        <el-form ref="loginForm" :rules="data.rules" :model="data.form" style="width: 300px;margin: 0 auto;">
          <div style = "margin-bottom: 30px; color: #0742b2;font-size: 24px;font-weight: bold;">欢迎使用后台管理系统</div>
          <el-form-item prop="username">
            <el-input v-model="data.form.username" placeholder="请输入用户名" :prefix-icon="User" size="large"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="data.form.password" show-password placeholder="请输入密码" :prefix-icon="Lock" size="large"></el-input>
          </el-form-item>
          <el-form-item prop="role">
            <el-select v-model="data.form.role" size="large">
              <el-option label="管理员" value="ADMIN"></el-option>
              <el-option label="普通用户" value="EMP"></el-option>
            </el-select>
          </el-form-item>

          <div style="margin-bottom: 20px;">
            <el-button type="primary" @click="login" size="large" style="width: 100%;margin-top: 20px;">登录</el-button>
          </div>
          <div style = "text-align: right;">还没有账号？<a href="/register" style="color: #0742b2;text-decoration: none;">立即注册</a></div>
        </el-form>
      </div>
    </div>
  </div>
</template>


<script setup>
import {reactive, ref} from "vue";
import  {User, Lock} from "@element-plus/icons-vue"
import request from "@/utils/request";
import {ElMessage} from "element-plus";
const loginForm = ref()

const data = reactive({
  form: {
    username: "",
    password: "",
    role: "ADMIN",
  },
  rules: {
    username: [
      { required: true, message: '请输入用户名', trigger: 'blur' },
      { min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' },
      { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
    ]
  }

})

const login = () => {
  loginForm.value.validate((valid) => {
    if (valid) {
      request.post('/login', data.form).then(res => {
        if (res.code === '200') {
          ElMessage.success('登录成功')
          localStorage.setItem('token', JSON.stringify(res.data))
          setTimeout(() => {
            location.href = '/manager/home'
          }, 1000)

        } else {
          ElMessage.error(res.msg)
        }
      }).catch(() => {})
      }
  })
}

</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100vh;
  overflow: hidden;
  background-image: url('@/assets/bg.jpg');
  background-size: cover;
}
.login-box {
  position: absolute;
  width: 50%;
  right: 0;
  top: 0;
  height: 100%;
  align-items: center;
  display: flex;
}
</style>