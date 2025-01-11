import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [

    {
        path: '/',
        redirect: 'manger'
    },
    {
      path: '/manger',
      name: 'manger',
      component: () => import('@/views/Manger.vue'),
      children: [
        {
          path: 'home',
          name: 'home',
          component: () => import('@/views/Home.vue')
        },
        {
          path: 'test',
          name: 'test',
          component: () => import('@/views/test.vue')
        },

      ]
    },
    
    
    
  ],
})

export default router
