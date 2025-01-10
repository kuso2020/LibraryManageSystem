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


</template>


<script setup>
import { ref, reactive } from 'vue'
import { Search } from '@element-plus/icons-vue';
const data = reactive({
  input: null,
  input2: null,
  value: null,
  radio1: null,
})


const options = [
  { value: 'Option1', label: 'Option1' },
  { value: 'Option2', label: 'Option2' },
  { value: 'Option3', label: 'Option3' },
  { value: 'Option4', label: 'Option4' },
  { value: 'Option5', label: 'Option5' },
]

const res = ['香蕉', '菠萝']

</script>