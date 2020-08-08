import Vue from 'vue'
import store from "../index";
import da from "element-ui/src/locale/lang/da";

const hr = {
  namespace: true,
  // 因为模块化了，所以解决刷新问题的代码需要改造一下
  state: {
    routes: [],
    hrs: [],
    currentSession: null,
    currentHr: JSON.parse(window.sessionStorage.getItem("user")),
  },
  getters: {
    hrs: state => {
      return state.hrs
    },
    currentSession: state => {
      return state.currentSession
    },
    getCurrentHr(state) {
      return state.currentHr
    },
    getRoutes(state) {
      return state.routes
    }
  },
  mutations: {
    initCurrentHr(state, hr) {
      state.currentHr = hr
    },
    initRoutes(state, data) {
      state.routes = data
    },
    changeCurrentSession(state, currentSession) {
      store.commit('changeIsDot',state.currentHr.username + '#' + currentSession.username)
      state.currentSession = currentSession;
    },
    initHr(state, data) {
      /*let isDot = {}
      let cu = state.currentHr.username*/
      state.hrs = data;
      /*for(let i=0;i<data.length;i++){
        let key = (cu+'#'+data[i].username).toString()
        isDot[key]=false
      }
      store.commit('setIsDot',isDot)*/
    }
  },
  actions: {
    // 异步初始化hr
    asyncInitCurrentHr(context, hr) {
      context.commit('initCurrentHr', hr);
    }
  },
}

export default hr
