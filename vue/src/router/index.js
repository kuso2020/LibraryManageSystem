import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
        path: '/',
        redirect: '/login'
    },{
        path: '/login',
        name: 'login',
        meta: { title: '登录' },
        component: () => import('@/views/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      meta: { title: '注册' },
      component: () => import('@/views/Register.vue')
    },
    {
      path: '/manager',
      name: 'manager',
      component: () => import('@/views/Manager.vue'),
      meta: { title: '管理', requiresAuth: true },
      children: [
        {
          path: 'home',
          name: 'home',
          meta: { title: '首页' },
          component: () => import('@/views/Home.vue')
        },
        {
          path: 'test',
          name: 'test',
          meta: { title: '测试页面' },
          component: () => import('@/views/test.vue')
        },
        {
          path: 'data',
          name: 'data',
          meta: { title: '数据展示页面' },
          component: () => import('@/views/data.vue')
        },
        {
          path: 'Staff',
          name: 'Staff',
          meta: { title: '员工信息表' },
          component: () => import('@/views/Staff.vue')
        },
        {
          path: 'Admin',
          name: 'Admin',
          meta: { title: '管理员信息表' },
          component: () => import('@/views/Admin.vue')
        },
        {
          path: 'Person',
          name: 'Person',
          meta: { title: '个人中心' },
          component: () => import('@/views/Person.vue')
        },
        {
          path: 'updatePassword',
          name: 'updatePassword',
          meta: { title: '个人中心' },
          component: () => import('@/views/Password.vue')
        },
      ]
    },
    {
      path: '/404',
      name: 'notFound',
      meta: { title: '出错了' },
      component: () => import('@/views/404.vue')
  },



  // 404 page must be placed at the end
  {
    path: '/:pathMatch(.*)*',
    redirect: '/404'
  }
    
    
  ],
})

router.beforeEach((to, from, next) => {
  document.title = to.meta.title || '管理系统'
  const isLoggedIn = checkIfUserIsLoggedIn(); // 检查用户是否登录
  if (to.meta.requiresAuth && !isLoggedIn) {
    // 如果需要登录且用户未登录，重定向到登录页面
    next({
      path: '/login',
      query: { redirect: to.fullPath }, // 保存目标路由
    });
  } else {
    // 否则，继续导航
    next();
  }
})

function checkIfUserIsLoggedIn() {
  const token = localStorage.getItem('token');
  if (!token) return false;

  // 如果需要验证 token 的有效性，可以调用后端接口
  // 例如：axios.get('/api/validateToken', { headers: { Authorization: token } })
  // 根据返回结果判断 token 是否有效

  return true; // 暂时直接返回 true
}

export default router
