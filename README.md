<h1 align="center"> 西柚失物招领小程序 </h1>

<p align="center">
 	<a href="https://github.com/gongsir0630/LostAndFoundOnCampus/releases">
		<img src="https://img.shields.io/badge/version-2.2.0-brightgreen.svg"
			 alt="Version">
	</a>
 	<a href="https://api.swzl.gongsir.club">
		<img src="https://img.shields.io/badge/status-updating-success.svg"
			 alt="Status">
	</a>
</p>

### 项目介绍
👉基于微信小程序的校园失物招领平台，提供OCR识别证件、失物招领消息订阅、web后台可视化数据管理等

### 项目特色
* ocr证件识别
* 小程序消息订阅
* 后台管理短信验证登录
* 邮箱验证

#### 项目地址

项目采用前后端分离开发，小程序使用vue开发，后端数据API采用Java、Spring-Boot开发。

前端代码地址：https://github.com/Irisssr/LostAndFound.git

后端API代码地址：https://github.com/gongsir0630/LostAndFoundOnCampus.git

#### 体验地址

QQ小程序：搜索【西柚失寻】QQ小程序v1.0版本

微信小程序：暂未上线，可联系我们申请体验！

演示视频：[在线演示视频](https://cdn.gongsir.club/video/LostAndFoundVideo.mp4)

#### 使用说明

##### 1. clone本仓库到本地
```sh
git clone https://github.com/gongsir0630/LostAndFoundOnCampus.git
```
##### 2. 执行sql脚本
* sql脚本在`LostAndFoundOnCampus/src/main/resources/lostAndFound.sql`
* 使用Navicat等工具导入执行即可

##### 3. 修改配置文件
* 数据库连接
* redis连接
* 资源上传位置
* 端口
* ~~ssl证书~~(正式环境使用nginx代理)
##### 4. maven编译打包
```sh
mvn clean package
```
##### 5. 本地运行
```sh
java -jar xxx.jar
```
##### 6. 修改小程序中base_api即可（如需上线体验版，则需要配置https域名，详见：微信公众平台）

#### 开发日志

1、2019.10-2019.11 v1开发

2、2019.11 QQ小程序【西柚失寻】上线

3、2020.3 消息通知由 模板消息推送----->> 订阅消息

4、2020.3 增加web可视化管理

5、... ...

#### 联系我们

开发者主页：https://gongsir.club

