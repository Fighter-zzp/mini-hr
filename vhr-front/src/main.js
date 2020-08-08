// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router'
import store from './store'
import VueRouter from 'vue-router'

// ElmentUI的组件
import ElementUI from 'element-ui'
// ElementUI的样式
import 'element-ui/lib/theme-chalk/index.css'

import 'font-awesome/css/font-awesome.min.css'
Vue.use(ElementUI)
Vue.prototype.$ELEMENT = {size: 'small', zIndex: 3000};
Vue.prototype.$alert = ElementUI.MessageBox.alert
Vue.prototype.$confirm = ElementUI.MessageBox.confirm

/** utils **/
import {
  postRequest,
  putRequest,
  postKeyValueRequest,
  deleteRequest,
  getRequest,
  getToLogout,
} from './utils/api'
// 全局设置api的方法
Vue.prototype.postRequest = postRequest;
Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.getToLogout = getToLogout;

/** utils end **/

Vue.use(VueRouter)
Vue.use(VueAxios, axios);


Vue.config.productionTip = false

// 默认的url地址
axios.defaults.baseURL = 'http://localhost:8081/api'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

