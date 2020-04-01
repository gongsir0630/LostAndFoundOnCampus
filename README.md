### 西柚失物招领小程序

<hr/>

#### 项目地址
项目类似于前后端分离开发，前端小程序界面使用vue渲染，数据来源于后端API。

前端地址：https://github.com/Irisssr/LostAndFound

后端API地址：https://github.com/gongsir0630/LostAndFoundOnCampus

#### 体验地址

QQ小程序：搜索 【西柚失寻】

微信小程序：暂未上线，可联系我们申请体验！

后端API地址：https://gongsir.club/swagger-ui.html

#### 系统运行环境（具体文档可参见doc目录有详细说明）

小程序是基于微信开发的嵌入式App，前端界面基于微信，运行在微信中，
小程序数据由个人开发的后端服务器提供服务，运行环境：
- 小程序前端服务端环境：微信服务器（代码的运行，编译）
- 小程序客户端（用户端）：微信App（基于Android 或者 iOS）
- 小程序后端服务：个人云服务器（基于CentOS 7的操作系统）
- Web服务器：Tomcat 9

### 系统配置要求

1.	微信服务端无需配置，将前端小程序代码打包上传至微信服务器即可（基于微信开发者工具）
2.	客户端，用户需要在Android或者iOS设备中安装最新版 微信App
3.	后端服务器：一台云服务器（操作系统推荐CentOS 7），服务器安装一下服务以供API正常运行：
- Java运行环境：JDK 1.8
- Web服务器：Tomcat 9
- 数据库：MySQL 5.7或以上版本
- Redis数据库：Redis缓存数据库

#### 系统配置方法及步骤

##### 小程序前端环境配置

1、首先，在微信开发者工具完成代码调试，编译，无任何问题后，点击代码上传，将代码上传至微信服务器：
![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/kzv5BIoREgdf.png?imageslim)
 
2、登录微信公众平台（https://mp.weixin.qq.com/），可以看到刚刚提交的开发版本，下拉提交审核，可设置体验版本：

 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/E0zu4dUiGCbW.png?imageslim)
 
 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/T6Yq5Qpfjzr9.png?imageslim)
 
##### 小程序后端环境配置
1、在本地IDEA中调试、编译代码，没有任何问题时，使用mvn将项目直接打包成jar包：
推荐使用maven中的clean和install命令，即可打包，生成的jar在项目的target目录中：

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/jqoqmgEDGsQ1.png?imageslim)
 
2、购买服务器（阿里、腾讯都可，我使用的是阿里云的），购买域名（有免费的，我的是www.gongsir.club），在阿里云提交域名备案，域名备案流程不再赘述，可以参考阿里云官方教程，域名备案一般15天左右

3、安装服务环境，安装Java环境、MySQL、Redis这三个必须环境：

 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/VIUtVFsvznSQ.png?imageslim)
 
 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/tSJzHRNLOA8i.png?imageslim)
 
 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/Cm3vQ5fCpEp0.png?imageslim)
 
 
4、将jar上传至云服务器（之所以使用云服务器，因为微信不能低啊用本地API，而且服务器必须域名备案，并且项目需要配置https），使用命令让服务后台运行：
命令：nohub java -jar wxapp-0.0.1-SNAPSHOT.jar
此时会在当前目录下生成一个nohub.out文件，实时存储日志输出，可以使用tail -f nohub.out查看：

 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/hoCIWUGtD7Cw.png?imageslim)
 
##### 小程序数据服务配置
1、登录微信公众平台（https://mp.weixin.qq.com/），进行开发域名配置：

 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/vqa3i11nodTj.png?imageslim)
 
2、填写我们已经实名备案的域名，注意项目要配置https：

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/nvDmdL5qyXaq.png?imageslim)
 
#### 小程序使用介绍
一、获取授权并实名绑定学号

 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/28t139OzPtV2.png?imageslim)
 
二、信息发布

 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/spVsObRnL7bv.png?imageslim)
 
三、信息浏览

1.主界面浏览

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/5K1oICLcIoix.png?imageslim)
 
2. 搜索功能

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/VGW1g2KvHgmj.png?imageslim)
 
3.分类查看

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/GUSAJvakv36A.png?imageslim)
 
四、信息详情

1.详情页面

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/wigmmx7l9uXP.png?imageslim)
 
2.分享功能
 
 ![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/J3c7y38dGpDW.png?imageslim)

3.生成海报

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/mmIHv6Eb2RJx.png?imageslim)
 
五、证件信息发布 

1.OCR光学文字识别功能

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/c4iyvYYFhGJC.png?imageslim)
 
2.证件监听

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/LMn45qYJMxPD.png?imageslim)
 
监听匹配信息，发送小程序通知

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/6ugR3s4AcVYK.png?imageslim)
 
六、物品认领
 
![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/B2iyJrDmQKx7.png?imageslim)
 
七、我的功能界面

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/hH6pLtEc7j9K.png?imageslim)

#### 更新

2020.3 ----->> 提供了Web可视化管理

![mark](http://q7y42mue4.bkt.clouddn.com/blog/20200401/NRkcmROSGbUF.png?imageslim)
 
#### 联系我们

个人主页有联系方式：http://www.gongsir.club
