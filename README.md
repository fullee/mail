# README

## 介绍
封装了最新版本的javamail,一行代码发送邮件

## 使用
* dependency
```$xslt
<dependency>
    <groupId>io.github.oskworker</groupId>
    <artifactId>osk-mail</artifactId>
    <version>0.1</version>
</dependency>
```

```$xslt
EM.configure().make("标题", "正文内容").sendTo("icngor@126.com","1192577322@qq.com");
```
## 特性
* 支持从mail.properties文件中读取配置信息
* 使用极简的方式在代码中配置
* 使用极简的方式配置邮件类
* 优雅的发件接口
* 自动识别smtp服务器地址(目前支持qq,新浪,163,126)
