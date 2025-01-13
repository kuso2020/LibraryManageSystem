<template>
  <div>
    <div class="card">
      <el-input style="width: 200px;" v-model="data.searchText" placeholder="请输入名称查询" size="normal" clearable prefix-icon="Search"></el-input>
    </div>
  </div>
</template>


<script setup>
import { reactive } from 'vue';
import { Search } from '@element-plus/icons-vue';
const data = reactive({
  searchText: '',
});
</script>


<style scoped>

</style>