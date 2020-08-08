<template>
  <div>
    <el-container>
      <el-header class="homeHeader">
        <div class="title">微人事</div>
        <div>
          <el-button icon="el-icon-bell" type="text" style="margin-right: 8px;color: #000000;" size="normal"
                     @click="goChat"></el-button>
          <el-dropdown class="userInfo" @command="commandHandler">
            <span class="el-dropdown-link" style="color: #F2F6FC">
              {{user.name}}<i><img :src="user.userface" alt=""></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
              <el-dropdown-item command="setting">设置</el-dropdown-item>
              <el-dropdown-item command="logout" divided>注销登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside width="222px">
          <el-button @click="changeCollapse(false)" plain size="mini" type="success" v-if="isShow"><i class="el-icon-d-arrow-right"></i></el-button>
          <el-button @click="changeCollapse(true)" plain v-else-if="!isShow" size="mini" type="info" style="margin-left: 80%"><i class="el-icon-d-arrow-left"></i></el-button>
            <el-menu router unique-opened
                     :collapse="isShow"
                     background-color="#545c64"
                     text-color="#fff"
                     active-text-color="#ffd04b">
              <el-submenu :index="index+''" v-for="(item,index) in routes" v-if="!item.hidden" :key="index">
                <template slot="title">
                  <i style="margin-right: 5px" :class="item.iconCls"></i>
                  <span>{{item.name}}</span>
                </template>
                <el-menu-item :index="child.path" v-for="(child,indexj) in item.children" :key="indexj">
                  {{child.name}}
                </el-menu-item>
              </el-submenu>
            </el-menu>
        </el-aside>
        <el-main>
          <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path!=='/home'">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
          </el-breadcrumb>
          <div class="homeWelcome" v-if="this.$router.currentRoute.path==='/home'">
            欢迎来到微人事！
          </div>
          <router-view class="homeRouterView"/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import VueCookies from "vue-cookies";

  export default {
    name: "Home",
    data() {
      return {
        isShow: true,
        // user: JSON.parse(window.sessionStorage.getItem("user"))
      }
    },
    computed: {
      routes() {
        return this.$store.getters.getRoutes;
      },
      user:function() {
        return this.$store.getters.getCurrentHr;
      }
    },
    methods: {
      changeCollapse(b){
        this.isShow = b;
      },
      goChat() {
        this.$router.push("/chat");
      },
      commandHandler(cmd) {
        if (cmd === 'logout') {
          this.$confirm('此操作将注销登录, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.getToLogout('/auth/logout')
            window.sessionStorage.removeItem("user")
            this.$store.commit('initRoutes', []);
            this.$router.replace("/");
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消操作'
            });
          });
        } else if (cmd === 'userinfo') {
          this.$router.push('/hrinfo');
        }
      }
    }
  }
</script>

<style>
  .homeRouterView {
    margin-top: 10px;
  }

  .homeWelcome {
    text-align: center;
    font-size: 30px;
    font-family: 华文行楷;
    color: #409eff;
    padding-top: 50px;
  }

  .homeHeader {
    background-color: #545c64;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0px 15px;
    box-sizing: border-box;
  }

  .homeHeader .title {
    font-size: 30px;
    font-family: 华文行楷;
    color: #ffffff
  }

  .homeHeader .userInfo {
    cursor: pointer;
  }

  .el-dropdown-link img {
    width: 48px;
    height: 48px;
    border-radius: 24px;
    margin-left: 8px;
  }

  .el-dropdown-link {
    display: flex;
    align-items: center;
  }

  /** 侧边栏站整个面高度 **/
  .el-aside {
    display: block;
    position: relative;
    background-color: #545c64 !important;
    height: 870px;
  }

</style>
