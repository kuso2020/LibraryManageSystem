<template>
  <div>
    <div class="card" style="margin-bottom: 10px;">
      <el-input style="width: 200px; margin-right: 10px;" v-model="data.name" placeholder="请输入要查询的姓名" size="" clearable prefix-icon="Search" ></el-input>
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
    <el-dialog v-model="data.dialogFormVisible" title="请输入添加管理员的信息" width="500" destroy-on-close>
      <el-form ref = "formRef" :rules = "data.rules" :model="data.form" style = "padding-right: 50px; padding-top: 30px;">
        <el-form-item prop="username" label="用户名" :label-width="formLabelWidth">
          <el-input v-model="data.form.username" autocomplete="off" placeholder = "请输入用户名"/>
        </el-form-item>
<!--        <el-form-item label="密码" :label-width="formLabelWidth">-->
<!--          <el-input v-model="data.form.password" autocomplete="off" placeholder = "请输入密码" show-password/>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="角色" :label-width="formLabelWidth">-->
<!--          <el-input v-model="data.form.role" autocomplete="off" placeholder = "请输入角色"/>-->
<!--        </el-form-item>-->
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" placeholder = "请输入姓名"/>
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

const formRef = ref()

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
  rules: {
    username: [
      { required: true, message: '请输入用户名', trigger: 'blur' },
      { min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
    ],
    name: [
      { required: true, message: '请输入姓名', trigger: 'blur' },
      { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
    ],
  },
});

const columns = [
  {
    id: 'username',
    label: '用户名',
    width: '100',
  },
  {
    id: 'name',
    label: '姓名',
    width: '100',
  },
]

const load = ()=>{
  request.get('admin/selectPage', {
        params : {
          pageNum: data.currentPage,
          pageSize: data.pageSize,
          name: data.name,
        }
      }).then(res => {
    data.tableData = res.data.list;

    data.total = res.data.total;
  })
}

const reset = () => {
  data.name = null;
  data.currentPage = 1;
  load()
}

const save = () => { //新增和编辑
  formRef.value.validate((valid) => {
    if (valid) {
      data.form.id ? edit() : add()
    }
  })
}

const edit = () => {
  request.put('admin/update', data.form).then(res => {
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
  request.post('admin/Post', data.form).then(res => {
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
  ElMessageBox.confirm('删除后将无法恢复，您确认要删除该管理员吗？', '确认删除', {type: 'warning'}).then(() => {
    request.delete('admin/delete', {params: {id: row.id}}).then(res => {
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

  //从rows中取出id，组成数组，传给后台，后台根据id删除相应数据
  data.ids = rows.map(item => item.id)
}

const delteBatch = () => {
  if (data.ids.length === 0) {
    ElMessage.warning('请选择要删除的员工')
    return
  }
  ElMessageBox.confirm('删除后将无法恢复，您确认要删除所选员工吗？', '确认删除', {type: 'warning'}).then(() => {
    request.delete('admin/deleteBatch', {data: data.ids}).then(res => {
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