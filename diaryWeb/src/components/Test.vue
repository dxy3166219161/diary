<template>
  <div id="editor" style="height: 800px;margin-top: 200px"></div>
</template>

<script>
import E from "wangeditor"

export default {
  data() {
    return {
      editor: ''
    }
  },
  methods: {
    setWangEditor() {
      // 创建编辑器
      this.editor = new E('#editor')
      this.editor.create()
    }
  },
  mounted() {
    this.setWangEditor()
  }
}
</script>
