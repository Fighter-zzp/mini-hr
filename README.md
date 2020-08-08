# 微HR系统
> 模仿并制作分布式Hr管理系统（未做完）
### 技术栈
#### 后台
* SpringBoot 2.29
* SpringCloudAlibaba 2.2.1.RELEASE
* 分布式注册中心 nacos 1.3.0
* 服务间通信模块 dubbo 2.2.7、feign 2.2.3
* 分布式安全服务 SpringCloudOuth2
* 熔断器 sentinel（暂未使用）
* 消息工具 RocketMq（暂未使用）
* 邮件 spring-boot-starter-mail（暂未使用）
* 任务调度 Scheduled（暂未使用）
* 缓存 spring-boot-starter-cache、spring-boot-starter-data-redis
* 云服务器：oss 3.6.0
* 报表工具：poi 4.1.2
* websocket spring-boot-starter-websocket（SockJs方式）
* 其他工具 okhttp 3.14.9、Jackson、 bitwalker 1.21
#### 容器
docker：部署了nacos、rocketmq
#### 数据库
- redis
* mysql
#### 前端
- Vue
- 异步通信 axios
- 状态管理 vuex
- 路由 vue-router
- SockJs node-sass
- Ui组件 ElementUI
- cookie vue-cookies  

**...**

### 实现内容
- 分布式认证服务器
- 基于权限的管理系统（不同权限的角色能访问的页面不同）
- 内容管理：员工管理、Hr管理、权限管理、部门管理、薪资管理（薪资处理、账单报表）、角色管理
- 员工报表导入与导出
- Hr消息查看与Hr聊天室
- Hr（登录者）信息修改


