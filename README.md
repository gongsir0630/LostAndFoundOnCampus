<h1 align="center"> 西柚失物招领小程序 | API源码 </h1>

<p align="center">
 	<a href="https://github.com/gongsir0630/LostAndFoundOnCampus/releases">
		<img src="https://img.shields.io/badge/version-2.0.0-brightgreen.svg"
			 alt="Version">
	</a>
 	<a href="https://swzl.gongsir.club/api/swagger-ui.html">
		<img src="https://img.shields.io/badge/status-updating-success.svg"
			 alt="Status">
	</a>
</p>

### 项目介绍 :book:
👉基于微信小程序的校园失物招领平台. 含平台主体微信小程序和web后台数据管理平台.
* 失物招领信息一览/关键字检索
* 信息发布(支持图片上传)
* OCR证件识别(免输入)
* 证件消息订阅(监听自己遗失的证件信息,及时收到小程序通知)
* 阿里云短信验证码

### 项目技术栈 :star:
* 小程序: Vue/Uni-app/微信小程序
* 后端: Java/SpringBoot/MySQL/Redis/百度云/微信小程序API 

#### 项目地址 :link:

项目采用前后端分离开发模式，小程序使用vue开发，后端数据API采用Java、Spring-Boot开发.

小程序端code地址：https://github.com/gongsir0630/LostAndFound.git

后端code地址：https://github.com/gongsir0630/LostAndFoundOnCampus.git

web管理端code地址: https://github.com/Irisssr/laf-manager.git

#### 体验地址

QQ小程序：搜索【西柚失寻】QQ小程序v1.0版本

微信小程序：暂未上线，可联系我们申请体验！

演示视频：[在线演示视频](https://cdn.gongsir.club/video/LostAndFoundVideo.mp4)

#### 使用说明

##### 0. 小程序部署: [戳这里👈](https://github.com/gongsir0630/LostAndFound.git)

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
* 订阅消息模板ID(修改`UserConstantInterface.java`中的小程序 APPID 和订阅消息模板 ID)
* 阿里云短信(修改`AliMsgSend.java`中的 key 和 secret)
* 百度云 OCR 识别(修改`OCRUtil.java`中的AppID,key,secret)
##### 4. maven编译运行
```sh
mvn spring-boot:run
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

