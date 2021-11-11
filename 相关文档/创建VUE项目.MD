- ##  安装VUE脚手架

  ```shell
  npm install --global vue-cli
  ```

  （-global 意思是全局安装 Vue会安装到安装node 的文件夹下 否则会安装到当前目录）

  ![image-20211111094740039](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211111094740039.png)

- ##  验证安装成功

  ```shell
  Vue –version
  ```

  出现版本号即表示成功

  ![image-20211111094812468](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211111094812468.png)

- ## 进入预创建VUE项目的文件夹

  ```shell
  cd 目录
  ```

- ## 创建vue项目

  ```shell
  E:\MyselfPro\diary>vue init webpack project
  
  'git' �����ڲ����ⲿ���Ҳ���ǿ����еĳ���
  �������ļ�
  ? Project name project-web #项目名
  ? Project description 项目前端 #备注
  ? Author auther #作者
  ? Vue build standalone #Vue公司的建立（使用箭头键）>运行时+编译器：大多数用户推荐运行时间：约6kb轻民+ gzip，但模板（或任何Vue具体HTML）只允许在。VUE文件渲染功能是必需的其他地方。（按enter）
  ? Install vue-router? Yes #安装路由 建议安装
  ? Use ESLint to lint your code? No #ESLint语法检测，如果熟悉ESLint语法可以使用，不熟悉建议不用，会报错
  ? Set up unit tests No #单元测试 看自己是否使用吧
  ? Setup e2e tests with Nightwatch? No #Nightwatch建立端到端的测试 
  ? Should we run `npm install` for you after the project has been created? (recommended) npm
  ```

  见到下图，项目即创建成功

  ![image-20211111100809189](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211111100809189.png)

- ## 使用webstorm打开刚刚创建的项目

- ## 执行命令，下载依赖

  ```shell
   npm install
  ```

- ## 下载完依赖后启动项目

  ```shell
  npm run dev
  ```

  ![image-20211111102023404](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211111102023404.png)

- ## 访问上图的链接

  ![image-20211111102121781](C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20211111102121781.png)

  如此，项目创建成功