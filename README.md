# ChouXiangAPI

[![forthebadge](https://img.shields.io/badge/PosererdBy-NAServer-red)](https://github.com/KeKe12030/NullAtomHttpServer)  ![](https://img.shields.io/badge/license-MIT-blue)  ![Open Source Love](https://camo.githubusercontent.com/d41b9884bd102b525c8fb9a8c3c8d3bbed2b67f0/68747470733a2f2f6261646765732e66726170736f66742e636f6d2f6f732f76312f6f70656e2d736f757263652e7376673f763d313033)

---

![forthebadge](https://forthebadge.com/images/badges/fuck-it-ship-it.svg)  ![forthebadge](https://forthebadge.com/images/badges/uses-git.svg)

> 基于NullAtomHttpServer开发的一款抽象话转换器API
>
> ​      亚      文      化      

---

### #演示API

> 地址：http://api.mcplugin.cn/cxapi

---

### # 使用方法：
+ 下载ChouXiangAPI.jar之后，直接`java -jar ChouXiangAPI.jar 服务器运行端口 拼音词库文件绝对目录`

  注：参数必须填，而且必为两个，顺序不得颠倒

  下载地址：https://github.com/KeKe12030/ChouXiangAPI/releases
  
  词库文件地址：https://github.com/KeKe12030/ChouXiangAPI/blob/master/ciku/pinyin.properties
  
  把词库文件下载到本地，然后放在jar的第二个参数中运行

---

### # 支持的请求方式：

+ GET请求，请求参数为`msg`，


  如：`http://api.mcplugin.cn/cxapi?msg=你吼那么大声干什么嘛？`



+ POST请求，请求参数为`msg`

  如：（以CURL为例）

  ```shell
  curl -d "msg=那你去物管啊！" http://api.mcplugin.cn/cxapi
  ```

  

---

### # * 特别注意：

由于编码问题，所以运行的服务器目前**仅支持Linux服务器**！

由于编码问题，所以运行的服务器目前**仅支持Linux服务器**！

由于编码问题，所以运行的服务器目前**仅支持Linux服务器**！

---

### # 运行时图片展示：

![image.png](https://pic.rmb.bdstatic.com/bjh/e8db6c5e226f2b4b80b56fb34c5819c6.png)

![M7LIVBPFW0P@YY`L42`M}~R.png](https://pic.rmb.bdstatic.com/bjh/699c3db6dc0c116d3d577dd839607b8f.png)

---

### 特别鸣谢：

> 感谢@chenxuuu 大神的 https://github.com/chenxuuu/chouxianghua 项目，提供了词库

---

### #TODO（咕咕咕？）

+ 支持整个词语转换（代码里已经写道PullWrod接口了，但是一直没时间⛏）
+ 支持多种词库（已经在收集了，一个一个做适配也行，就是没时间）

---

