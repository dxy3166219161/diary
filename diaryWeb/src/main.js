import { createApp } from 'vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import axiosApi from "./util/axiosApi";

const app = createApp(App)
app.use(ElementPlus)
app.use(router)
app.use(axiosApi)
app.mount('#app')
