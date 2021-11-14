# 一、检查是否有mysql安装在自己的linux服务器上

## 1、执行如下命令

```sh
rpm -qa | grep mysql
```

如果已存在，则执行删除命令 后边为Mysql目录

## 2、查询所有Mysql对应的文件夹

```shell
whereis mysqlm

find / -name mysql

删除相关目录或文件

rm -rf /usr/bin/mysql /usr/include/mysql /data/mysql /data/mysql/mysql 

验证是否删除完毕

whereis mysqlm

find / -name mysql
```

## 3、检查mysql用户组和用户是否存在，如果没有，则创建

```shell
cat /etc/group | grep mysql

cat /etc/passwd |grep mysql

groupadd mysql

useradd -r -g mysql mysql
```

# 二、下载安装mysql

## 1、下载mysql安装包

```shell
wget https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz
```

![image-20211114100915980](C:\Users\javaD\AppData\Roaming\Typora\typora-user-images\image-20211114100915980.png)

## 2、解压

```shell
tar -zvxf mysql-5.7.24-linux-glibc2.12-x86_64.tar.gz
```

## 3、将解压后的文件夹重命名为mysql,然后移动到/usr/local/

```shell
mv mysql-5.7.24-linux-glibc2.12-x86_64 mysql
mv mysql /usr/local
```

## 4、进入/usr/local/mysql中，创建data目录

```shell
cd /usr/local/mysql
mkdir data
```

## 5、修改mysql目录以及其下所有的文件的权限

```shell
chown -R mysql:mysql /usr/local/mysql
chmod -R 777 /usr/local/mysql
```

如果出现报错，请检查是否创建了mysql用户组以及用户

## 6、编译安装并初始化mysql,务必记住初始化输出日志末尾的密码

```shell
./bin/mysqld --initialize --user=mysql --datadir=/usr/local/mysql/data --basedir=/usr/local/mysql
```

![image-20211114103124361](C:\Users\javaD\AppData\Roaming\Typora\typora-user-images\image-20211114103124361.png)

注意：root@localhost: tVf#&CHay8uF  一定要记住了，存在txt内也可以

## 7、编辑配置文件my.cnf，添加配置如下

```shell
vim /etc/my.cnf
```

最终配置如下：

```properties
[mysqld]
datadir=/usr/local/mysql/data
port =9605
user=root
sql_mode=NO_ENGINE_SUBSTITUTION,STRICT_TRANS_TABLES
symbolic-links=0
max_connections=400
innodb_file_per_table=1
lower_case_table_names=1
character_set_server=utf8
#datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0
# Settings user and group are ignored when systemd is used.
# If you need to run mysqld under a different user or group,
# customize your systemd unit file for mariadb according to the
# instructions in http://fedoraproject.org/wiki/Systemd

[mysqld_safe]
log-error=/var/log/mariadb/mariadb.log
pid-file=/var/run/mariadb/mariadb.pid

#
# include all files from the config directory
#
!includedir /etc/my.cnf.d
```

# 三、启动配置mysql

## 1、启动mysql

```shell
/usr/local/mysql/support-files/mysql.server start
```

如果启动时出现权限问题，将my.cnf中添加user=root

如果出现缺少文件或者不可创建文件的情况，手动创建一下

![image-20211114110147683](C:\Users\javaD\AppData\Roaming\Typora\typora-user-images\image-20211114110147683.png)

出现下图表示启动成功

## 2、添加软连接，并重启mysql服务

```shell
ln -s /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql 
ln -s /usr/local/mysql/bin/mysql /usr/bin/mysql
service mysql restart
```

![image-20211114110347845](C:\Users\javaD\AppData\Roaming\Typora\typora-user-images\image-20211114110347845.png)

## 3、登录mysql,修改密码

```shell
mysql -u root -p
```

如果启动出现这个报错

![image-20211114111518558](C:\Users\javaD\AppData\Roaming\Typora\typora-user-images\image-20211114111518558.png)

执行如下命令修复即可：

```shell
find / -name mysql.sock
#如果找不到mysql.sock的话，查看链接地址：https://blog.csdn.net/hjf161105/article/details/78850658
#找到的话，执行如下命令
ln -s /usr/local/mysql/sock/mysql.sock /tmp/mysql.sock
```

## 4、修改密码

```shell
set password for root@localhost = password('123456');
```

## 5、开放远程连接

```shell
mysql>use mysql;
msyql>update user set user.Host='%' where user.User='root';
mysql>flush privileges;
```

## 6、设置开机自动启动

1、将服务文件拷贝到init.d下，并重命名为mysql

```shell
cp /usr/local/mysql/support-files/mysql.server /etc/init.d/mysqld
```

2、赋予可执行权限

```shell
chmod +x /etc/init.d/mysqld
```

3、添加服务

```shell
chkconfig --add mysqld
```

4、显示服务列表

```shell
chkconfig --list
```

注意：记得远程访问时，解除防火墙相关的端口限制