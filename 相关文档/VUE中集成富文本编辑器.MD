# 一、集成wangeditor

## 1、下载安装wangeditor包

```shell
npm install wangeditor
```

## 2、进入vue项目中，在App.vue中编辑如下

```vue
<template>
  <div id="editor" style="height: 800px"></div>
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
```

```http
官网：https://www.wangeditor.com/doc/pages/01-%E5%BC%80%E5%A7%8B%E4%BD%BF%E7%94%A8/01-%E5%9F%BA%E6%9C%AC%E4%BD%BF%E7%94%A8.html
```

