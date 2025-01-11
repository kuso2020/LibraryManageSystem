<template>
  <div style="text-align: center;">
    <h1>404 Not Found</h1>
    <img src="@/assets/very_sorry.png" alt="" >
    <div>
      <el-button type="primary" size="default" @click="router.push('/manager/home')">返回主页</el-button>
      
    </div>
  </div>
</template>

<script setup>
import router from '@/router';




</script>