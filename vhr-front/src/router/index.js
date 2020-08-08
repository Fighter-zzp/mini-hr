import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/Login'
import HrInfo from '../views/HrInfo'
import Home from '../views/Home'
import FriendChat from "../views/chat/FriendChat";
import store from "../store";
// 加载菜单初始化
import {initMenu} from "../utils/menu";

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden: true
    }, {
      path: '/home',
      name: 'Home',
      component: Home,
      hidden: true,
      meta: {
        roles: ['admin', 'user']
      },
      children: [
        {
          path: '/chat',
          name: '在线聊天',
          component: FriendChat,
          hidden: true
        }, {
          path: '/hrinfo',
          name: '个人中心',
          component: HrInfo,
          hidden: true
        }
      ]
    }, {
      path: '*',
      redirect: '/home'
    }
  ]
})

/** 全局路由守卫 **/
router.beforeEach((to, from, next) => {
  if (to.path === '/') {
    next();
  } else {
    if (window.sessionStorage.getItem("user")) {
      initMenu(router, store);
      next();
    } else {
      next('/?redirect=' + to.path);
    }
  }
})

export default router;
