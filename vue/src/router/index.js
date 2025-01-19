import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
        path: '/',
        redirect: '/manager/home'
    },
    {
      path: '/manager',
      name: 'manager',
      component: () => import('@/views/Manager.vue'),
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
  document.title = to.meta.title
  next()
})

export default router
