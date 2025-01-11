<template>
  <div style="margin-bottom : 20px;">
    <RouterLink to = "/test">Test</RouterLink> |
    <a href="/test">通过a跳转</a>
    <!-- 通过按钮跳转 -->
    
  </div>

  <div style="margin-bottom: 20px;">
    <el-button type="primary" size="default" @click="router.push('/manger/test')">跳转到新页面</el-button>
  </div>

  <div>
    <el-button type="primary" size="default" @click="router.push('/manger/test?id=1&name=hello')">
      有传参的跳转（id = 1）
    </el-button>
    
  </div>

  <div>
    <div style="margin-bottom: 20px;">
        <el-input v-model="data.input" placeholder="please input" size="normal" clearable style="width: 300px;" :prefix-icon="Search"></el-input>
        <!-- test -->
        <el-input v-model="data.input2" placeholder="要在输入框中添加图标，你可以简单地使用 prefix-icon 和 suffix-icon 属性。 另外， prefix 和 suffix 命名的插槽也能正常工作。s要在输入框中添加图标，你可以简单地使用 prefix-icon 和 suffix-icon 属性。 另外， prefix 和 suffix 命名的插槽也能正常工作。Us" size="normal" clearable @change="" style="width: 400px;" type="textarea" ></el-input>
        
    </div>

    <div style="margin-bottom: 30px;">
        <el-select
      v-model="data.value"
      placeholder="Select"
      size="large"
      style="width: 240px"
      multiple
      collapse-tags
      collapse-tags-tooltip
      :max-collapse-tags="2"
    >
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
      />
    </el-select>
        

    </div>


    <div>
        just a test for git
    </div>


  </div>

  <div style="margin-bottom: 15px;">
    <p>this is a test for radio</p>
    <el-radio-group v-model="data.radio1" style="margin-right: 20px;">
      <el-radio value=1 size="large">香蕉</el-radio>
      <el-radio value=2 size="large">菠萝</el-radio>
    </el-radio-group>
    所选的水果为：{{ res[data.radio1-1] }}
  </div>
  
  <div>
    <el-radio-group v-model="data.radio2" size = "large" style="margin-right: 20px;">
        <el-radio-button  label="New York" value = 'New York'></el-radio-button>
        <el-radio-button  label="Washington" value = 'Washington'></el-radio-button>
        
    </el-radio-group>

    <p>Your city is: {{ data.radio2 }}</p>
    
    
  </div>

  <div>
    <el-checkbox-group v-model="data.checkbox" disabled>
        <el-checkbox v-for="item in checkboxOptions" :key="item.id" :label="item.label" :value="item.value"></el-checkbox>
        
    </el-checkbox-group>
    <p>Your selected options are: {{ data.checkbox }}</p>
  </div>

  <div style="margin-bottom: 20px;">
    <p >原生的img</p>
    <img src="@/assets/logo.svg" alt="" style="width:  300px;">
    <p>使用element-plus的img</p>
    <!-- 当使用el-image时，src使用本地图片要导入 -->
     <!-- srcList 是一个数组，可以预览多张图片 -->
    <el-image 
    :src="img" style="width: 300px; margin-left: 100;"
    :preview-src-list="srcList"
    ></el-image>
  </div>

  <div style="margin-bottom: 20px;"> 轮播图

    <el-carousel height="150px" style="width: 600px;">
      <el-carousel-item v-for="item in 4" :key="item">
        <h3 class="small justify-center" text="2xl">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
  
  </div>

  <div style="margin-bottom: 20px;">
    <div class="block">
      <span class="demonstration">Default</span>
      <el-date-picker
        v-model="data.value1"
        type="datetime"
        placeholder="Select date and time"
        format="YYYY-MM-DD"
        value-format="YYYY-MM-DD"
      />
    </div>
    当前的时间为{{ data.value1 }}

  </div>
  <!-- table -->

  <div style="margin-bottom: 20px; width: 1000px;">
    <el-table :data="tableData" style="width: 100%" stripe border height="500px">
        <el-table-column fixed prop="date" label="Date" width="150" sortable/>
    <el-table-column prop="name" label="Name" width="120" />
    <el-table-column prop="state" label="State" width="120" />
    <el-table-column prop="city" label="City" width="320" />
    <el-table-column prop="address" label="Address" width="600" />
    <el-table-column prop="zip" label="Zip" />
    <el-table-column fixed="right" label="Operations" min-width="120">
      <template #default="scope">
        <el-button
          link
          type="primary"
          size="small"
          @click.prevent="deleteRow(scope.$index)"
        >
          Remove
        </el-button>
      </template>
    </el-table-column>
    </el-table>
    
  
  <div style="margin: 10px 0;">
    <el-pagination
      v-model:current-page="currentPage4"
      v-model:page-size="pageSize4"
      :page-sizes="[10, 20, 30, 40]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="tableData.length"
      background
    />
  </div>

  <el-button class="mt-4" style="width: 100%" @click="onAddItem">
    Add Item
  </el-button>

  <div>
    <el-button  @click="dialogVisible = true" type="primary">
      <el-icon><Edit /></el-icon>
    </el-button>

    <el-dialog
    v-model="dialogVisible"
    title="Tips"
    width="500"
    :before-close="handleClose"
  >
    <span>This is a message</span>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogVisible = false">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
  </div>
  </div>

</template>


<script setup>
import { ref, reactive } from 'vue'
import { Search } from '@element-plus/icons-vue';
import img from '@/assets/logo.svg'
import dayjs from 'dayjs'
import { RouterLink } from 'vue-router';
import router from '@/router/index';

const data = reactive({
  input: null,
  input2: null,
  value: null,
  radio1: null,
  radio2: null,
  checkbox: [],
  value1: '',
  currentPage4: 1,
  pageSize4: 10,
})
//控制弹窗显示的变量
const dialogVisible = ref(false)

const handleClose = (done) => {
  ElMessageBox.confirm('Are you sure to close this dialog?')
    .then(() => {
      done()
    })
    .catch(() => {
      // catch error
    })
}

const options = [
  { value: 'Option1', label: 'Option1' },
  { value: 'Option2', label: 'Option2' },
  { value: 'Option3', label: 'Option3' },
  { value: 'Option4', label: 'Option4' },
  { value: 'Option5', label: 'Option5' },
]

const checkboxOptions = [
  { id: 1, value: 'hju', label: '花' },
  { id: 2, value: '2', label: '艹' },
  { id: 3, value: '3', label: '树' },
]
const srcList = [
  'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
  'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
  'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg',
  'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg',
  'https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg',
  'https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg',
  'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg',
]

const tableData = ref([
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },{
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },{
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
])

const res = ['香蕉', '菠萝']

const deleteRow = (index) => {
  tableData.value.splice(index, 1)
}

const now = new Date()

const onAddItem = () => {
  now.setDate(now.getDate() + 1)
  tableData.value.push({
    date: dayjs(now).format('YYYY-MM-DD'),
    name: 'Tom',
    state: 'California',
    city: 'Los Angeles',
    address: 'No. 189, Grove St, Los Angeles',
    zip: 'CA 90036',
  })
}

</script>

<style scoped>
.demonstration {
  color: var(--el-text-color-secondary);
}

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #7c90ad;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>