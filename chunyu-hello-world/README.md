- [第一个`SpringBoot`项目](#第一个springboot项目)
  - [在线网站创建](#在线网站创建)
  - [`IDEA`中使用`Spring Initializr`创建](#idea中使用spring-initializr创建)
  - [`IDEA`中使用`maven`创建](#idea中使用maven创建)
  - [编写启动类](#编写启动类)
  - [编写测试类](#编写测试类)
  - [启动`Spring Boot`](#启动spring-boot)

# 第一个`SpringBoot`项目

本文主要介绍创建一个`Spring Boot`项目的方式，有以下方式可供选择：

1. 在线网站：导出压缩包后导入`IDEA`。
    - `Spring`：[`https://start.spring.io`](https://start.spring.io)
    - `Aliyun`：[`https://start.aliyun.com`](https://start.aliyun.com)
2. `IDEA`：集成在线网站或从零搭建
    - `Spring Initializr`
    - `maven`

## 在线网站创建

`Spring`官方：[`https://start.spring.io`](https://start.spring.io)

![start.spring.io](https://posts-cdn.lilu.org.cn/2022/05/1920a9be1340a33f4518a40910da6702b6e88fc2.png)

`Aliyun`：[`https://start.aliyun.com`](https://start.aliyun.com)

![start.aliyun.com](https://posts-cdn.lilu.org.cn/2022/05/1920a96fe7c583bda3cdfc78a4bc96b39fc3faae.png)

这种方式只需要填好相关配置信息点击下载就可以，下载的压缩包里面是一个完整的项目工程文件，还包含一些`maven`相关的文件，个人不是很推荐这种方式。

## `IDEA`中使用`Spring Initializr`创建

`Spring Initializr`：

![spring-initializr](https://posts-cdn.lilu.org.cn/2022/05/192023a6cf9802d71d4ef0417806652390e6c5f6.png)

修改`Server URL`即可修改在线地址。点击`Next`可进入下一步：

`start.spring.io`：

![spring-initializr-next](https://posts-cdn.lilu.org.cn/2022/05/19203f19e691eb03e7f02bc47d4794f7416f8900.png)

`start.aliyun.com`：

![aliyun-initializr-next](https://posts-cdn.lilu.org.cn/2022/05/192073dd84dabc7c58d81ce128615abce82cf222.png)

可以看到`Aliyun`的集成了一些阿里商业化组件。这个只是集成在线网站的功能，个人不太推荐使用。

## `IDEA`中使用`maven`创建

下面介绍使用`maven`从零创建`Spring Boot`项目，个人常用。

创建`maven`空模板项目：

![maven-module](https://posts-cdn.lilu.org.cn/2022/05/1920ab4092d827ea5343cd3fff07ce202cff03f1.png)

点击`Next`填写相关信息：

![maven-module-next](https://posts-cdn.lilu.org.cn/2022/05/1920b6540519d12c0b6fa46540a3207ca30f3ac6.png)

创建好的项目工程结构如下：

![maven-module-dir-struct](https://posts-cdn.lilu.org.cn/2022/05/19207c50ed38b71b0ec9bc7dfaae9f9a2b54b7c2.png)

可以看到是非常干净的`maven`项目结构。打开`pom.xml`文件添加`Spring Boot`的依赖，这里有`2`种方式：

第一种是直接使用`parent`标签继承`spring-boot-starter-parent`，完整`pom`内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.6.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>chunyu-helloworld</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
</project>
```

第二种是使用`maven`的预定义依赖引入`spring-boot-dependencies`，实际企业开发中很可能是多模块项目或者有其它`parent`需要继承，使用预定义依赖更显优雅。完整`pom`如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>com.sunchaser.chunyu</groupId>
        <artifactId>sunchaser-chunyu</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>chunyu-helloworld</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <springboot.version>2.6.4</springboot.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${springboot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>
</project>
```

## 编写启动类

手动创建包`com.sunchaser.chunyu.helloworld`，并在包中创建`Spring Boot`的启动类`ChunYuHelloWorldApplication.java`：

```java
package com.sunchaser.chunyu.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * hello world 项目启动类
 *
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2022/3/6
 */
@SpringBootApplication
public class ChunYuHelloWorldApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ChunYuHelloWorldApplication.class, args);
    }
}
```

`main`方法中使用的是`SpringApplication.run`方法启动的`Spring Boot`；这里`Spring Boot`还提供了使用建造者模式的类`org.springframework.boot.builder.SpringApplicationBuilder`，另外一种（优雅）写法为：

```java
package com.sunchaser.chunyu.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * hello world 项目启动类
 *
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2022/3/6
 */
@SpringBootApplication
public class ChunYuHelloWorldApplication {
    
    public static void main(String[] args) {
        // SpringApplication.run(ChunYuHelloWorldApplication.class, args);
        new SpringApplicationBuilder(ChunYuHelloWorldApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
```

## 编写测试类

创建`controller`包编写`HelloController.java`类：

```java
package com.sunchaser.chunyu.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunchaser admin@lilu.org.cn
 * @since JDK8 2022/3/6
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot";
    }
}
```

## 启动`Spring Boot`

运行`ChunYuHelloWorldApplication#main`方法，浏览器中访问[`http://localhost:8080/hello`](http://localhost:8080/hello)即可进行测试。完整代码可查看 [`Github`](https://github.com/sunchaser-lilu/sunchaser-chunyu/tree/master/chunyu-hello-world)。
