# 需求分析

## 一、用户管理

### 1、用户注册

### 2、用户登录

### 3、用户信息编辑

### 4、用户列表

### 5、用户信息详情查询

### 6、用户删除

## 二、核心模块

### 1、文档列表树结构查询

#### （1）日记

- 递归查询，由根节点我的日记开始，以父节点id向下递归即可，页面展示不同的类型采用不同的图标标识即可

#### （2）文档

- 递归查询，由根节点文档资料开始，以父节点id向下递归即可，页面展示不同的类型采用不同的图标标识即可

注意：回收站直接查询所有状态为删除的节点以及其子节点展示

#### （3）备忘录

- 直接查询限定时间范围内的所有数据，并按创建时间进行分组，展示标题和描述

### 2、文档详情查询

#### （1）日记

- 文档类型为年份时，查询当前年份的所有日记【文档类型为日记】，以列表的形式展示时间以及日记摘要

- 文档类型为月份时，查询当前年、月份的所有日记【文档类型为日记】，以列表的形式展示时间以及日记摘要

- 文档类型为日记时，直接获取x年x月x日的日记信息

#### （2）文档

- 文档类型为文件夹时，查询父节点为当前文件夹的所有子文件、子文件夹的文件名、文件夹名和摘要

- 文件类型为文件时，直接展示文件的信息

这些接口均可拆分，拆分后划分在不同的服务下

#### （3）备忘录

- 直接根据id查询标题和描述

### 3、文档修改

#### （1）日记

- 文档类型为年份时，不允许修改

- 文档类型为月份时，不允许修改

- 文档类型为日记时，只允许修改富文本内容【回收站中的日记也是这样】

#### （2）文档

- 文档类型为文件夹时，仅允许重命名【回收站中的文件夹不允许操作】

- 文件类型为文件时，可以重命名、修改富文本内容【回收站的文件不允许重命名】

#### （3）备忘录

- 直接根据id修改标题和描述

### 4、文档删除（移入回收站）

#### （1）日记

- 文档类型为年份时，不允许删除

- 文档类型为月份时，不允许删除

- 文档类型为日记时，可以移入回收站，但不保留数据结构，移入回收站要保留原父级id，保证在彻底进行物理删除之前可以进行还原

#### （2）文档

- 文档类型为文件夹时，将文件夹以及下属的文件夹文件移入回收站

- 文件类型为文件时，将文件移入回收站,移入回收站要保留原父级id，保证在彻底进行物理删除之前可以进行还原

### 5、回收站数据还原

#### 日记与文档

- 日记和文档保持一致，直接修改父级id还原回去即可

### 6、文档删除【物理删除】

#### 日记与文档以及备忘录

- 根据id进行删除即可

### 7、清空回收站【物理删除】

#### （1）、日记

- 清空数据类型为日记状态为删除的所有数据

#### （2）、文档

- 清空数据类型为文件夹、文档状态为删除的所有数据

### 8、新增文档

#### （1）日记

- 新增日记时，检查是否有当前年月的上级目录

- 没有年份则默认创建当前年份的文档【文档类型为年份】

- 没有月份则默认创建当前月份的文档【文档类型为月份】以及选择的时间的日记【文档类型为日记】

#### （2）文档

- 文档创建时，若没有指定父级目录则直接存入根节点下

- 若指定了父级目录则存入指定的父级目录下即可

- 文档创建接口也可以创建文件夹、子级文件夹【文档类型为目录】--注意：此处建议单独开一个创建文件夹的接口

数据库初始化时，需要为日记以及文档分别指定一个默认的根节点，分别命名为：我的日记/文档资料

#### （3）备忘录

- 必须有标题即可创建，创建时由前端指定创建时间。创建时间必须是选中的时间块

## 三、文档管理

### 1、新增文档/文件夹

### 2、修改文档内容

### 3、文档/文件夹重命名

### 4、文档/文件夹移入回收站

### 5、个人文档树结构查询

### 6、文档/文件夹详情

### 7、文档/文件夹还原

### 8、文档/文件夹删除【物理删除】

### 9、清空回收站【物理删除】

## 四、日记管理

### 1、新增日记

### 2、修改日记内容

### 3、日记移入回收站

### 4、个人日记树结构查询

### 5、日记（年/月）详情

### 6、日记（日）详情

### 7、日记还原

### 8、日记删除【物理删除】

### 9、清空回收站【物理删除】

## 五、备忘录管理

### 1、创建备忘录

### 2、修改备忘录

### 3、删除备忘录

### 4、查看备忘录详情

### 5、备忘录列表

## 六、API服务（直接入接口）

### 接口列表：

#### 1、数据库数据备份【查询所有数据，进行备份，备份的位置是自己的电脑】

## 七、util工具

### 1、验证码生成

# 数据库设计

## 1、文档表

<table height="100%">
    <tr >
        <td>字段</td>
        <td>类型</td>
        <td>说明</td>
        <td>示例</td>
    </tr>
    <tr >
        <td>id_</td>
        <td>string</td>
        <td>主键</td>
        <td>32位的uuid</td>
    </tr>
    <tr >
        <td>account_</td>
        <td>string</td>
        <td>用户账号</td>
        <td>dongxingyu</td>
    </tr>
    <tr >
        <td>user_name</td>
        <td>string</td>
        <td>用户名</td>
        <td>董星宇</td>
    </tr>
    <tr >
        <td>parent_id</td>
        <td>string</td>
        <td>父级id</td>
        <td>32位的uuid</td>
    </tr>
    <tr >
        <td>create_date</td>
        <td>date</td>
        <td>创建时间</td>
        <td>2021-11-08 11:32:00</td>
    </tr>
    <tr >
        <td>update_date</td>
        <td>date</td>
        <td>修改时间</td>
        <td>2021-11-08 11:32:00</td>
    </tr>
    <tr >
        <td>summary_</td>
        <td>String</td>
        <td>文档摘要</td>
        <td>文档摘要1245646545555</td>
    </tr>
    <tr >
        <td>detail_</td>
        <td>string</td>
        <td>详情：存储富文本</td>
        <td></td>
    </tr>
    <tr >
        <td>status_</td>
        <td>string</td>
        <td>状态：Y/N 可使用/不可使用</td>
        <td>Y</td>
    </tr>
    <tr >
        <td>type_</td>
        <td>string</td>
        <td>类型：folder/documentation/年 文件夹/文件/年份/月份/日记</td>
        <td>documentation</td>
    </tr>
    <tr >
        <td>lastTime_parent_id</td>
        <td>string</td>
        <td>上次父节点id</td>
        <td>32位的uuid</td>
    </tr>
</table>
## 2、用户表

## 3、操作记录表
