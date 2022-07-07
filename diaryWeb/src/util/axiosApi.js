import axios from 'axios'

// 创建axios实例
const axiosApi = axios.create({
    baseURL: '/',
    timeout: 10000, // 请求超时时间
    headers: {
        'Content-Type': 'application/json'
    }
})

// 请求发出前拦截
axiosApi.interceptors.request.use(
    config => {
        // /* if (store.getters.token) {
        //
        // } */
        config.headers['Authorization'] = sessionStorage.getItem("authorization"); //getToken() // 让每个请求携带自定义token 请根据实际情况自行修改

        return config
    },
    request => {
        // // request.setHeader("Authorization",window.sessionStorage.getItem("msaAuthorization"))
        // if (request.method == 'post') {
        //   request.params = {}
        // }
        return request
    },
    error => {
        Promise.reject(error)
    }
)

// 收到请求结果拦截
axiosApi.interceptors.response.use(
    response => {
        var authorization = response.headers.authorization;
		console.log(authorization);
        if(authorization != null && authorization != ""){
            sessionStorage.setItem("authorization",authorization)
        }

        var authorizationRefresh = response.headers.AuthorizationRefresh;
        if(authorizationRefresh != null && authorizationRefresh != ""){
            sessionStorage.setItem("authorization",authorizationRefresh)
        }



        let res = response.data
        res.msg = '<script type="text/html" style="display:block;">' + res.msg + '</script>'
        // // debugger
        // if (res.code === 200) {
        //   return res
        // } else if (res.code == 401) {
        //   //token过期
        //   ElementUI.Message.error("用户token已过期,请重新登录！")
        //   sessionStorage.clear()
        //   router.push('/')
        // } else {
        return res
        // }
    },
    error => {
        return Promise.reject(error)
    }
)

export default axiosApi
