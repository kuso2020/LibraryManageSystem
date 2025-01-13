<template>
  <!-- <div style="font-size: 30px; font-weight: bold; color: red; padding: 20px;">
    这是一个父级页面
  </div>
  

  <div  style="border:  1px solid #cfe; margin: 30px auto; padding: 20px; background-color: azure; width: 60%;">
    <router-view/>
  </div> -->
  <div>
    <!-- 头部开始 -->
    <div style="height: 40px; background-color: #19f;">

    </div>
    <!-- 头部结束 -->

    <!-- 下面部分开始-->
    <div style="display: flex;;">
      <!-- 菜单栏开始 -->
      <div style="width: 200px; border-right: 1px solid #ccc;height: calc(100vh - 40px);">
        <el-menu
        style="border: 0;"
      >
        <el-menu-item index="1">
          <el-icon><icon-menu /></el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <span>数据统计</span>
        </el-menu-item>
        <el-menu-item index="3" >
          <el-icon><document /></el-icon>
          <span>Navigator Three</span>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon><setting /></el-icon>
          <span>设置</span>
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


<script>
import { RouterView } from 'vue-router';


</script>