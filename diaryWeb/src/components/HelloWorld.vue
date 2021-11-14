<template>
  <div class="hello">
    <editor ref="testEditor"></editor>
    <div>
      <el-button @click="getContent">Default</el-button>
    </div>
  </div>
</template>

<script>
import editor from './Test'
export default {
  name: 'HelloWorld',
  components:{
    editor
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  methods: {
    getContent(){
      this.$refs.testEditor.getText()
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
