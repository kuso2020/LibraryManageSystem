<template>
  <div>
    <div class="card" style="margin-bottom: 10px;">
      <el-input style="width: 200px; margin-right: 10px;" v-model="data.searchText" placeholder="请输入名称查询" size="normal" clearable prefix-icon="Search" ></el-input>
      <el-button type="primary" size="default" @click="">查询</el-button>
      <el-button type="default" size="default" @click="">重置</el-button>
      
      
    </div>
    <div class="card" style="margin-bottom: 10px;">
      <el-button type="primary" size="default" @click="">新增</el-button>
      <el-button type="warning" size="default" @click="">批量删除</el-button>
      <el-button type="default" size="default" @click="">导入</el-button>
      <el-button type="default" size="default" @click="">导出</el-button>
      
      
    </div>
    <div class="card" style="margin-bottom: 10px;">
      <el-table :data="tableData" stripe>
        <el-table-column v-for="col in columns"
          :prop="col.id"
          :key="col.id"
          :label="col.label"
          :width="col.width">
        </el-table-column> 

      </el-table>
      <div style="margin-top: 10px;">
        <el-pagination
          v-model:current-page="data.currentPage"
          v-model:page-size="data.pageSize"
          :page-sizes="[10, 20, 30, 40]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="data.total"
          background
        />
      </div>
      
      
    </div>
  </div>
</template>


<script setup>
import { reactive } from 'vue';
import { Search } from '@element-plus/icons-vue';
const data = reactive({
  searchText: '',
  pageSize: 10,
  currentPage: 1,
  total: 100,
});

const columns = [
  {
    id: 'date',
    label: '日期',
    width: '150',
  },
  {
    id: 'name',
    label: '姓名',
    width: '150',
  },
  {
    id: 'address',
    label: '地址',
    width: '300',
  },
]

const tableData = [
  {
    id: 1,
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    id: 2,
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    id: 3,
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
</script>


<style scoped>

</style>