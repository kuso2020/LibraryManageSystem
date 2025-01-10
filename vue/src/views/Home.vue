<template>

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

</template>


<script setup>
import { ref, reactive } from 'vue'
import { Search } from '@element-plus/icons-vue';
import img from '@/assets/logo.svg'
const data = reactive({
  input: null,
  input2: null,
  value: null,
  radio1: null,
  radio2: null,
  checkbox: [],
  value1: '',
})


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

const res = ['香蕉', '菠萝']

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