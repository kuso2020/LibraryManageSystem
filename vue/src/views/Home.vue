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


</template>


<script setup>
import { ref, reactive } from 'vue'
import { Search } from '@element-plus/icons-vue';
const data = reactive({
  input: null,
  input2: null,
  value: null,
})


const options = [
  { value: 'Option1', label: 'Option1' },
  { value: 'Option2', label: 'Option2' },
  { value: 'Option3', label: 'Option3' },
]

</script>