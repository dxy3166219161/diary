<template>
  <div id="all">
    <div class="login_info">
      <el-form ref="form" :model="form"  label-width="8vw" class="login_info_form">
        <el-form-item label="账号" >
            <el-input v-model="form.userAccount"></el-input>
        </el-form-item>
        <el-form-item label="密码">
           <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" style="width: 20vw;margin-left: -3vw"  @click="login" >登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
// import { login } from '../api/user'
// import JSEncrypt from '../assets/js/jsencrypt.min.js'
import JSEncrypt from 'jsencrypt/bin/jsencrypt'
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
      const PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhibKzrXY9jAHHCCS7iZy/t2hsTfUrEfC\n" +
          "+scPflBXt3XZHbKLgrY4ffsJCVgINPYVK3y5hTzM/rWiLOiw4IwGyRRY4dafQcuMFpcjPg18GsBh\n" +
          "MRLo2nBRASNGYX1QM5WVRhRlMiJlBaP/JTtqQhhxN+9trsm5GFunnm0OFFeBlA+fid9Aav13b2ZA\n" +
          "iVTeccwCL6ul7ytDc4LX+4DYfu2orFn+Gjt3Yr04amh2RGW0bb+iQ7L7y/7DofABngNpo0/WPqVw\n" +
          "pbUNcf1UYhDiKIqbN+4SrG3kkwBPsVd8qnLjpSji3chooHI14ybTXC/1535Wp6GzKdP3POyFf3qz\n" +
          "gPqsCwIDAQAB"
      const encrypt = new JSEncrypt()
      encrypt.setPublicKey(PUBLIC_KEY)
      // JSON.stringify(this.form).
      let result = encrypt.encrypt("{'username':'dongxingyu','password':'LIE886lie'}")

      // result.assign
      console.log(result)
      // login(this.form)
    }
  }

}
</script>

<style scoped>
#all{
  background: url(../assets/backgroundImg/loginBackground.jpg) no-repeat;
  -moz-background-size: 100% 100%;
  background-size: 100% 100%;
  height: 100vh;
  background-size: cover;
}
.login_info {
  width: 30vw;
  height: 50vh;
  background-color: rgba(255,255,255,0.3);
  margin-top: 20vh;
  margin-left: 65vw;
  border: 0px solid;
  position:absolute;
  border-radius: 0.5vw;
}
.login_info_form {
  width: 25vw;
  height: 25vh;
  padding-top: 15vh;
}
</style>
