<template>
  <div>
    <div class="card" style="margin-bottom: 10px;">
      <el-input style="width: 200px; margin-right: 10px;" v-model="data.name" placeholder="请输入要查询的姓名" size="normal" clearable prefix-icon="Search" ></el-input>
      <el-button type="primary" size="default" @click="load">查询</el-button>
      <el-button type="default" size="default" @click="reset">重置</el-button>


    </div>
    <div class="card" style="margin-bottom: 10px;">
      <el-button type="primary" size="default" @click="handleAdd">新增</el-button>
      <el-button type="danger" size="default" @click="delteBatch">批量删除</el-button>
<!--      <el-button type="default" size="default" @click="">导入</el-button>-->
<!--      <el-button type="default" size="default" @click="">导出</el-button>-->


    </div>
    <div class="card" style="margin-bottom: 10px;">
      <el-table :data="data.tableData" stripe  @selection-change="handleSelectionChange">

        <el-table-column type="selection" style="width: 100%"/>

        <el-table-column v-for="col in columns"
                         :prop="col.id"
                         :key="col.id"
                         :label="col.label"
                         :width="col.width">
        </el-table-column>
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button type="primary"  @click="handleEdit(scope.row)" :icon="Edit" circle></el-button>
            <el-button type="danger"  @click="handleDelete(scope.row)" :icon="Delete" circle></el-button>
          </template>
        </el-table-column>

      </el-table>
      <div style="margin-top: 10px;">
        <el-pagination
            @change = 'load'
            v-model:current-page="data.currentPage"
            v-model:page-size="data.pageSize"
            :page-sizes="[5, 10, 20, 40]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="data.total"
            background
        />
      </div>


    </div>
    <el-dialog v-model="data.dialogFormVisible" title="请输入添加员工的信息" width="500">
      <el-form :model="data.form" style = "padding-right: 50px; padding-top: 30px;">
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="data.form.name" autocomplete="off" placeholder = "请输入姓名"/>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
         <el-radio-group v-model="data.form.gender">
           <el-radio value = "男" label = "男"></el-radio>
           <el-radio value = "女" label = "女"></el-radio>
         </el-radio-group>
        </el-form-item>
        <el-form-item label="NO" :label-width="formLabelWidth">
          <el-input v-model="data.form.no" autocomplete="off" placeholder = "请输入NO" />
        </el-form-item>
        <el-form-item label="年龄" :label-width="formLabelWidth">
          <el-input-number style="width: 200px ;" :min = "18" v-model="data.form.age" autocomplete="off" placeholder = "请输入年龄" />
        </el-form-item>
        <el-form-item label="个人简介" :label-width="formLabelWidth">
          <el-input type="textarea" v-model="data.form.description" autocomplete="off" placeholder = "请输入简介" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="data.dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="save">
            保存
          </el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import {reactive, ref} from 'vue';
import { Search, Delete, Edit} from '@element-plus/icons-vue';
import request from "@/utils/request.js";
import { ElMessage, ElMessageBox } from 'element-plus';


const formLabelWidth = '140px'
const data = reactive({
  name: null,
  pageSize: 5,
  currentPage: 1,
  total: 100,
  tableData: [],
  dialogFormVisible: false,
  form: {},
  ids: null,
});

const columns = [
  {
    id: 'name',
    label: '姓名',
    width: '100',
  },
  {
    id: 'gender',
    label: '性别',
    width: '100',
  },
  {
    id: 'no',
    label: 'NO',
    width: '100',
  },
  {
    id: 'age',
    label: '年龄',
    width: '100',
  },
  {
    id: 'description',
    label: '简介',
    width: '400',
  },
  {
    id: 'department',
    label: '部门',
    width: '300',
  },
]

const load = ()=>{
  request.get('staff/selectPage', {
        params : {
          pageNum: data.currentPage,
          pageSize: data.pageSize,
          name: data.name,
        }
      }).then(res => {
    data.tableData = res.data.list;
    // console.log(res.data);

    data.total = res.data.total;
  })
}

const reset = () => {
  data.name = null;
  data.currentPage = 1;
  load()
}

const save = () => { //新增和编辑
  data.form.id ? edit() : add()
}

const edit = () => {
  request.put('staff/update', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('修改成功')
      data.dialogFormVisible = false
      load()
    }else {
      ElMessage.error(res.msg)
    }

  })
}

const add = () => {
  request.post('staff/Post', data.form).then(res => {
    if (res.code === '200') {
      ElMessage.success('添加成功')
      data.dialogFormVisible = false
      load()
    }else {
      ElMessage.error(res.msg)
    }

  })

}

const handleAdd = () => {
  data.dialogFormVisible = true
  data.form = {}
}

const handleEdit = (row) => {
  data.dialogFormVisible = true
  //深拷贝
  data.form = JSON.parse(JSON.stringify(row))

}

const handleDelete = (row) => {
  ElMessageBox.confirm('删除后将无法恢复，您确认要删除该员工吗？', '确认删除', {type: 'warning'}).then(() => {
    request.delete('staff/delete', {params: {id: row.id}}).then(res => {
      if (res.code === '200') {
        ElMessage.success('删除成功')
        load()
      } else {
        ElMessage.error(res.msg)
      }

    })
  }).catch()
}


const handleSelectionChange = (rows) => {
  console.log(rows)
  //从rows中取出id，组成数组，传给后台，后台根据id删除相应数据
  data.ids = rows.map(item => item.id)
}

const delteBatch = () => {
  // console.log(Array.isArray(data.ids));
  if (data.ids.length === 0) {
    ElMessage.warning('请选择要删除的员工')
    return
  }
  ElMessageBox.confirm('删除后将无法恢复，您确认要删除所选员工吗？', '确认删除', {type: 'warning'}).then(() => {
    request.delete('staff/deleteBatch', {data: data.ids}).then(res => {
      if (res.code === '200') {
        ElMessage.success('删除成功')
        load()
      } else {
        ElMessage.error(res.msg)
      }
    })
  }).catch()
}

load()




</script>