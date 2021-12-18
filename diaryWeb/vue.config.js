module.exports = {
    publicPath: process.env.NODE_ENV === 'production'
      ? '/diary/'
      : '/diary/',
    outputDir: 'dist',  //build输出目录
    assetsDir: 'assets',//静态资源目录（js, css, img）
    //修改或新增html-webpack-plugin的值，在index.html里面能读取htmlWebpackPlugin.options.title
    chainWebpack: config =>{
        config.plugin('html')
        .tap(args => {
            args[0].title = "记录每一天";
            return args;
        })
    },
    runtimeCompiler: true,
    devServer:{
        open: true,
        port: 8080,
        proxy: {
            '^/': {
                target:  'http://localhost:9606/diary/server',// 本地
                // target:  'http://localhost:62335',// 本地
                // ws: true, // 是否启用websockets
                changeOrigin: true, //是否允许跨越
                pathRewrite: {
                    '^/': ''  //将你的地址代理位这个 / 接下来请求时就使用这个/来代替你的地址
                }
            }
        }
    }
}