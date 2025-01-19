
<template>
    <div>
      <div style="margin-bottom: 30px;" >
          <el-button type="primary" @click="router.back()">返回首页</el-button>
      </div>
      <div style="background-color: red; font-size: 25px; font-weight : bolder;color : aqua; text-decoration: underline;">
          你好，欢迎使用图书管理系统！
  
      </div>
      
      <div style="font-size: 35px; color: blue;">
          {{ a }}
  
          {{ data.name }} 的年龄是 {{ data.age }}，性别是 {{ data.sex }}。
      </div>
  
      <!-- 数据的绑定 -->
      <div>
          <input v-model="data.name"/>
      </div>
  
  
      <div style="margin-bottom: 15px;">
          <!-- 当 data.name 等于 '红猪' 时，显示 '小红猪'，否则显示 '小懒猪' -->
          <span style="color: red;" v-if="data.name === '红猪'"> 小红猪 </span>
          <span style="color: blue;" v-if="data.name === '懒猪'"> 小懒猪 </span>
          <span style="color: green;" v-else> 不是红也不是懒猪 </span>
  
      </div>
  
      <div style="display: flex;margin-bottom: 30px;">
          <!-- @click和v-for一起使用 -->
          <div @click="show(item)" v-for="item in data.arr" style="height: 200px;width: 200px; background-color: yellow; margin-bottom: 15px; text-align: center;
              line-height: 200px; font-size: 30px;margin: 15px;" >{{ item }}</div>
  
  
          <div>
              <select style="width: 300px;">
                  <!-- 用for来将选项中的所有元素放入x-option标签中 -->
                  <option v-for="fruit in data.fruits" >{{ fruit }} </option>
              </select>
          </div>
  
      </div>
  
      <div style="color: hotpink; font-size: 20px; margin-bottom: 10px;
      background-color: bisque;text-align: center;height: 300px;width: 300px;line-height: 300px;">
          {{ data.cnt }}
      </div>
  
      <div>
          <!-- submit按钮的点击事件 -->
           <!-- click为自定义函数 -->
          <button @click="click">提交</button>
      </div>
  
      <div :style="data.box">test</div>
      
      <!-- 动态引用图片    -->
      <div style="margin-bottom: 30px;">
          <img :src="data.img" alt="">
      </div>
  
      <!-- el-button的使用 -->
      <div style="margin-bottom: 30px;">
          <el-button>Default</el-button>
          <el-button @click="click" type="success" plain  >Success</el-button>
          <el-button @click="clear" type="info">Clear</el-button>
      </div>
  
  
      <div style="margin-bottom: 30px;">
          <el-icon :size = "20">
              <Edit />
          </el-icon>
  
  
          <span style="margin-left: 10px; color: red;">
              <el-icon :size = "20" style="top: 4px;"><View /></el-icon> 100
          </span>
  
          <el-button type="primary">
              <el-icon style="vertical-align: middle">
              <Delete />
              </el-icon>
              <span style="vertical-align: middle"> Delete </span> 
          </el-button >
  
          <el-button type="primary">
              <el-icon style="vertical-align: middle">
              <Search />
              </el-icon>
              <span style="vertical-align: middle"> Search </span>
          </el-button>
      </div>
  
  
      <div style="margin-bottom: 30px;">
          <el-input
      v-model="input"
      style="width: 240px"
      placeholder="Please input"
      clearable/>
      </div>
  
      <div style="margin-bottom: 30px;">
          <el-input
          v-model="input2"
          style="width: 240px"
          type="password"
          placeholder="Please input password"
          show-password
          />
      </div>
  
      <div style="margin-bottom: 30px;">
          <el-input
        v-model="input3"
        style="width: 240px"
        placeholder="请输入搜索内容"
        :prefix-icon="Search"
      />
      </div>
  
  
      <div style="margin-bottom: 30px;">
          <el-button type="primary">提交</el-button>
          <el-button type="primary" size="default" @click="">hello</el-button>
          
          
      </div>
  
      <div style="margin-bottom: 30px;">
          <el-input v-model="data.input" placeholder=""  clearable style="width: 300px;"></el-input>
      </div>
      
    </div>
  </template>
  
  
<script setup>
  import { Edit, View, Delete, Search } from '@element-plus/icons-vue';
  import { SignatureKind } from 'typescript';
  import { reactive, ref } from 'vue';
  import { useRouter } from 'vue-router';
  const router = useRouter();

  // 两种方式声明变量
  // import { ref } from 'vue'
  const input = ref('')
  const input2 = ref('')
  const input3 = ref('')
  const a = ref(1);
  
  const data = reactive    ({
    id : router.currentRoute.value.query.id,
    name: '张三',
    age: 20,
    sex: '男',
    arr: [1, 2, 3, 15],
    fruits: ['apple', 'banana', 'orange'],
    cnt : 0,
    box : {
      color: 'red',
      fontSize : '16px',
      backgroundColor : 'blue',
      width : '100px',
      height : '100px',
      lineHeight : '100px',
      textAlign : 'center',
      

    },
  
    img : 'https://www.baidu.com/img/flexible/logo/pc/result.png',
    input : '',
  });
  
  // console.log('传递的参数为', data.id)

  const click = () => {
    data.cnt++;
  }
  
  const show = (value) => {
    alert(value)
  }
  
  const clear = () => {
    data.cnt = 0;
  }
</script>
  