import Vue from 'vue'
import Vuex from 'vuex'
import {Notification} from 'element-ui';
import {getRequest} from '../../utils/api';
import '../../utils/stomp'
import '../../utils/sockjs'
import VueCookies from 'vue-cookies'
import store from "../index";

Vue.use(Vuex)
const chat = {
  namespace: true,
  state: {
    filterKey: '',
    stomp: null,
    isDot: {},
    sessions: {},
  },
  getters: {
    stomp:state=>{
      return state.stomp
    },
    isDot:state=>{
      return state.isDot
    },
    sessions:state=>{
      return state.sessions
    },
  },
  mutations: {
    changeIsDot(state,username){
      Vue.set(state.isDot,username,false)
    },
    setIsDot(state,data){
      state.isDot = data
    },
    INIT_DATA(state) {
      //浏览器本地的历史聊天记录可以在这里完成
      let data = localStorage.getItem('vue-chat-session');
      if (data) {
        state.sessions = JSON.parse(data);
      }
    },
    addMessage(state, msg) {
      let mss = state.sessions[store.getters.getCurrentHr.username + '#' + msg.to];
      if (!mss) {
        Vue.set(state.sessions, store.getters.getCurrentHr.username + '#' + msg.to, []);
      }
      state.sessions[store.getters.getCurrentHr.username + '#' + msg.to].push({
        content: msg.content,
        date: new Date(),
        self: !msg.notSelf
      })
    },
  },
  actions: {
    connect(context) {
      let token = "Bearer " + VueCookies.get('token')
      let ch = context.getters.getCurrentHr;
      context.state.stomp = Stomp.over(new SockJS('http://localhost:9004/ws/ep'))
      context.state.stomp.connect({}, success => {
        context.state.stomp.subscribe('/user/queue/chat', msg => {
          let receiveMsg = JSON.parse(msg.body);
          console.log(receiveMsg)
          if (!context.state.currentSession || receiveMsg.from !== context.state.currentSession.username) {
            Notification.info({
              title: '【' + receiveMsg.fromNickname + '】发来一条消息',
              message: receiveMsg.content.length > 10 ? receiveMsg.content.substr(0, 10) : receiveMsg.content,
              position: 'bottom-right'
            })
            Vue.set(context.state.isDot, ch.username + '#' + receiveMsg.from, true);
          }
          receiveMsg.notSelf = true;
          receiveMsg.to = receiveMsg.from;
          context.commit('addMessage', receiveMsg);
        })
      }, error => {
        console.log("连接失败!")
      })
    },
    initData(context) {
      let id = JSON.parse(window.sessionStorage.getItem("user")).id
      context.commit('INIT_DATA')
      getRequest("/system/chat/hrs?id="+id).then(resp => {
        if (resp) {
          context.commit('initHr', resp);
        }
      })
    }
  }
}
export default chat
