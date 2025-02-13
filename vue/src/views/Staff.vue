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
        <el-table-column label="头像" width="100">
          <template #default="scope">
            <img v-if="scope.row.avatar" :src="scope.row.avatar" style="width: 40px; height: 40px; border-radius: 50%;" />
          </template>
         </el-table-column>
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
    <el-dialog v-model="data.dialogFormVisible" title="请输入添加员工的信息" width="500" destroy-on-close>
      <el-form ref = "formRef" :rules = "data.rules" :model="data.form" style = "padding-right: 50px; padding-top: 30px;">
        <el-form-item prop="username"  label="用户名" :label-width="formLabelWidth">
          <el-input v-model="data.form.username" disabled autocomplete="off" placeholder = "请输入用户名"/>
        </el-form-item>
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
        <el-form-item label="姓名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="data.form.name" autocomplete="off" placeholder = "请输入姓名"/>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
         <el-radio-group v-model="data.form.gender">
           <el-radio value = "男" label = "男"></el-radio>
           <el-radio value = "女" label = "女"></el-radio>
         </el-radio-group>
        </el-form-item>
        <el-form-item label="NO" :label-width="formLabelWidth" prop="no">
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
    no: [
      { required: true, message: '请输入NO', trigger: 'blur' },
      { min: 1, max: 50, message: '长度在 1 到 50 个字符', trigger: 'blur' }
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
    width: '300',
  },
  {
    id: 'department',
    label: '部门',
    width: '200',
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

  //从rows中取出id，组成数组，传给后台，后台根据id删除相应数据
  data.ids = rows.map(item => item.id)
}

const delteBatch = () => {
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