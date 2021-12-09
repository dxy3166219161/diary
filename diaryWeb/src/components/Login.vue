<template>
  <div id="all">
    <div class="login_bgimg">
            <img src="/static/backgroundImg/loginBackground.jpg"/>
    </div>
    <div class="login_info">

      <el-form ref="form" :model="form"  label-width="4vw" class="login_info_form">
        <el-form-item label="账号" >
          <el-col :span="20">
            <el-input v-model="form.userAccount"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="密码">
          <el-col :span="20">
           <el-input v-model="form.password" type="password"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 10vw" @click="login" >登录</el-button>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {
        userAccount: "",
        password: ""
      }
    }
  },
  methods:{
    login(){

    }
  }

}
</script>

<style scoped>
#all {
  /*background-image: url("/static/backgroundImg/loginBackground.jpg");*/
  /*!*background-repeat:no-repeat;*!*/
  /*width: 100vw;*/
  /*height: 100vh;*/
  /*background-size: cover;*/
}

.login_bgimg {
  position: fixed;
  width: 100%;
  height: 100%;
  z-index: -1;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}

.login_info {
  width: 20vw;
  height: 50vh;
  background-color: rgba(255,255,255,0.3);
  margin-top: 15vh;
  margin-left: 70vw;
  border: 0px solid;
  border-radius: 0.5vw;
}

.login_info_form {
  width: 18vw;
  height: 25vh;
  margin-left: 1vw;
  padding-top: 15vh;
}
</style>
