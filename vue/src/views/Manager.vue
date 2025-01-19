<template>
  <div>
    <!-- 头部开始 -->
    <div style="height: 40px; background-color: #19f; align-items: center; display: flex;">
      <div style="width: 200px; display: flex; align-items: center; margin-left: 20px;">
        <img src="@/assets/logo.png" style="width: 30px; height: 30px; margin-right: 10px;" alt="">
        <span style="font-size: large; color: #fff;">后台管理系统</span>
      </div>
    
    <div style="flex: 1"></div>
    <div style="width: fit-content; display: flex; align-items: center;padding-right: 10px;">
      <!-- 头像 -->
      <el-avatar :size="40" :src="state.circleUrl" />
      <span style="margin-left: 10px; font-size: 16px; color: #fff;">{{users.name}}</span>
    </div>
  </div>
    <!-- 头部结束 -->

    <!-- 下面部分开始-->
    <div style="display: flex;;">
      <!-- 菜单栏开始 -->
      <div style="width: 200px; border-right: 1px solid #ccc;height: calc(100vh - 40px);">
        <el-menu
        style="border: 0;"
        router
        :default-openeds = "['1']"
        :default-active = "router.currentRoute.value.fullPath"
      >
        <el-menu-item index="/manager/home">
          <el-icon><House /></el-icon>
          <span>
            首页
          </span>
        </el-menu-item>
       
        
        <el-sub-menu index="1">
          <template #title>
            <el-icon><User /></el-icon>
            <span>用户管理</span>
          </template>
          <el-menu-item index="">管理员用户信息</el-menu-item>
          <el-menu-item index="/manager/Staff">员工信息</el-menu-item>
          <el-menu-item index="">VIP用户信息</el-menu-item>
          
        </el-sub-menu>

        <el-menu-item index = '/manager/data'>
          <el-icon><DataAnalysis/></el-icon>
          <span>数据统计</span>
        </el-menu-item>
        
        <el-menu-item index="">
          <el-icon><setting /></el-icon>
          <span>设置</span>
        </el-menu-item>
        <el-menu-item index="">
          <el-icon><SwitchButton /></el-icon>
          <span>退出登录</span>
        </el-menu-item>
      </el-menu>
      </div>
      <!-- 菜单栏结束 -->

      <!-- 右侧主体部分开始 -->
    <div style="flex: 1; width: 0; background-color: #f7f5f8; padding: 10px;">
      <RouterView/>
    </div>
    <!-- 右侧主体部分结束 -->

    </div>
    
    <!-- 下面部分结束 -->
  </div>

</template>


<script setup>
import router from '@/router';
import { reactive, toRefs } from 'vue'

const state = reactive({
  circleUrl:
    'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  squareUrl:
    'https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png',
  sizeList: ['small', '', 'large'],
})
const users = reactive({
  name: 'admin',
  avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
  roles: ['admin', 'user'],
})

</script>


<style>
 .el-menu-item.is-active{
    background-color: #ecf5ff !important;
  }
</style>