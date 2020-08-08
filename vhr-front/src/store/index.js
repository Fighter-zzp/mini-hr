import Vue from 'vue'
import Vuex from 'vuex'

import hr from './modules/hr'
import chat from './modules/chat'

Vue.use(Vuex);

 const store = new Vuex.Store({
  modules: {
    hr,
    chat,
  }
});
store.watch(function (state) {
  return state.sessions
}, function (val) {
  if (val){
    console.log(val)
    console.log(typeof(val))
    localStorage.setItem('vue-chat-session', JSON.stringify(val));
  }
}, {
  deep: true/*这个貌似是开启watch监测的判断,官方说明也比较模糊*/
})

export default store;
