# 一. First Spring Boot

## 1. 概述

Spring Boot 倡导约定优于配置，将简化开发发挥到极致，使用 Spring Boot 框架可以快速构建 Spring 应用，再也不需要大量的繁琐的的各种配置。
Spring Boot 框架设计的目标是：让程序员关注业务逻辑就行了，环境搭建方面交给 Spring Boot 

Spring Boot 特性：

1. 快速创建独立的 Spring 应用程序（Spring支持的SpringBoot都支持，也就是说SpringBoot全方位支持IoC，AOP等）
2. 嵌入式的 Tomcat、Jetty、Undertow 容器（web服务器本身就是几个jar包，Spring Boot框架自动嵌入了）
3. 需要什么功能时只需要引入对应的 starter 启动器即可（启动器可以自动管理这个功能相关的依赖，自动管理依赖版本的控制）
4. 尽最大努力，最大可能的自动配置 Spring 应用和第三方库（例如：如果要进行事务的控制，不用做任何事务相关的配置，只需要在 service 类上添加 @Transactional 注解即可）
5. 没有代码生成，没有 XML 配置（Spring Boot 的应用程序在启动后不会动态地创建新的 Java 类，所有逻辑都是在编译期就已经确定好的）
6. 提供了生产监控的支持，例如健康检查，度量信息，跟踪信息，审计信息等。也支持集成外部监控系统

Spring Boot 的开箱即用和约定优于配置：

- 开箱即用：Spring Boot 框架设计得非常便捷，开发者能够在几乎不需要任何复杂的配置的情况下，快速搭建并运行一个功能完备的 Spring 应用
- 约定优于配置：这是一种软件设计哲学，核心思想是通过提供一组合理的默认行为来减少配置的数量，从而简化开发流程。例如：Spring Boot 默认约定了使用某个事务管理器，在事务方面不需要做任何配置，只需要在对应代码的位置上使用 `@Transactional` 注解即可

****
## 2. 入门程序

[springboot](./Demo1-first_code/src/main/java/com/cell/first/springboot)

要使用 Spring Boot3 就需要继承它的开源项目，然后在 pom 文件中添加相关依赖

```xml
<!--继承Spring Boot 3.3.3开源项目-->
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>3.3.3</version>
</parent>
```

```xml
<!--引入Spring Boot web启动器依赖-->
<!--它会自动将 web 开发相关的所有依赖全部引入，例如：json、tomcat、springmvc等，包括它们的版本，也会自动管理-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

每个 Spring Boot 都需要一个主入口程序来开启，所以需要单独创建一个类，并且给这个类加上 @SpringBootApplication 注解，
Controller 就是对应 SpringMVC 中的控制器，用来实现页面的跳转，但需要注意的是，它必须放在与主入口程序同级或其子目录下，不然无法被识别为 Spring Boot 的组件程序

****
## 3. 便捷的部署方式

Spring Boo t提供了打包插件，可以将 Spring Boot 项目打包为可执行 jar 包。Web 服务器（Tomcat）也会连同一块打入 jar 包中。
只要电脑上安装了 Java 的运行环境（JDK），就可以启动 Spring Boot 项目。

引入相关插件：

```xml
<build>
	<plugins>
		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>
	</plugins>
</build>
```

点击编译器右侧的 Maven 功能，在 Lifecycle 中找到 package，双击进行项目的打包，打包完成后可以在 target 目录下看到对应的文件，
可以单独的将这个 jar 包可以拷贝到任何位置运行，通过 `java -jar 包名.jar` 命令来启动 Spring Boot 项目：

```text
PS E:\test> java -jar Demo1-first_code.jar
...
```

另外 Spring Boot 框架提供了非常灵活的配置，在可执行 jar 包的同级目录下新建配置文件：application.properties，并配置以下信息：

```properties
server.port=8888
```

然后就可以通过 8888 端口号访问

****
## 4. SpringBoot 的 jar 包和普通 jar 包的区别

Spring Boot 打包成的 JAR 文件与传统的 Java 应用程序中的 JAR 文件的区别主要体现在`依赖管理`和`可执行性`上：

**依赖管理**：

- Spring Boot 的 JAR 包通常包含了应用程序运行所需的所有依赖项，也就是说它是一个“fat jar”（胖 JAR 包），这种打包方式使得应用可以独立运行，而不需要外部的类路径或应用服务器上的其他依赖
- 普通的 JAR 文件一般只包含一个类库的功能，并且需要依赖于特定的类路径来找到其他的类库或者框架（第三方依赖），这些依赖项通常在部署环境中已经存在，比如在一个应用服务器中

**可执行性**：

- Spring Boot 的 JAR 文件可以通过直接执行这个 JAR 文件来启动应用程序，也就是说它是一个可执行的 JAR 文件，通过 `java -jar 包名.jar` 命令就可以直接运行应用程序
- 而普通的 JAR 文件通常是不可直接执行的，需要通过指定主类（main class）的方式或者其他方式来启动一个应用程序，例如使用 `-cp` 或 `-classpath` 加上类路径以及主类名来执行

Spring Boot 的这些特性使得部署和运行变得更加简单和方便，特别是在微服务架构中，每个服务都可以被打包成独立的 JAR 文件并部署到任何支持 Java 的地方

****
## 5. 脚手架

软件开发中的脚手架：

在软件开发领域，“脚手架”指的是用于快速创建项目基本结构的工具或模板，它帮助开发者初始化项目，设置必要的目录结构、文件模板以及依赖项

Spring Boot脚手架：

Spring Boot 脚手架（Scaffold）可以帮助开发者快速搭建一个 Spring Boot 项目结构，让开发者只专注于业务逻辑的开发，而不是在项目的初始阶段花费大量时间来配置环境或者解决依赖关系

### 5.1 常见的 Spring Boot 脚手架工具和方法：

+ **Spring Initializr：**

这是 Spring 官方提供的工具，可以在 [https://start.spring.io](https://start.spring.io) 上找到，它允许开发者选择所需的依赖、Java 版本、构建工具（Maven 或 Gradle）以及其他配置选项来生成一个新的 Spring Boot 项目。

+ **IntelliJ IDEA 内置支持：**

IntelliJ IDEA 集成了 Spring Initializr 的功能，可以在 IDE 内直接创建 Spring Boot 项目。

+ **Start Alibaba Cloud：**

阿里云提供的 Start Alibaba Cloud 增强版工具，除了基本的 Spring Boot 模块外，还集成了阿里云服务和中间件的支持。

+ **JHipster：**

JHipster 是一个流行的脚手架工具，用于生成完整的 Spring Boot 应用程序，包括前端（Angular, React 或 Vue.js）和后端，它还包括用户管理和认证等功能。

+ **Yeoman Generators：**

Yeoman 是一个通用的脚手架工具，它有一个庞大的插件生态系统，其中包括用于生成 Spring Boot 项目的插件。

+ **Bootify：**

Bootify 是另一个用于生成 Spring Boot 应用程序的脚手架工具，提供了一些预定义的应用模板。

+ **Spring Boot CLI：**

Spring Boot CLI 是一个命令行工具，允许用户通过命令行来编写和运行 Spring Boot 应用。

+ **Visual Studio Code 插件：**

Visual Studio Code 社区提供了多个插件，如 Spring Boot Extension Pack，可以帮助开发者生成 Spring Boot 项目的基本结构。

+ **GitHub Gist 和 Bitbucket Templates：**

在 GitHub 和 Bitbucket 上，有很多开发者分享了用于生成 Spring Boot 项目的脚本或模板。

+ **自定义脚手架：**

很多开发者也会根据自己的需求定制自己的脚手架工具，比如使用 Bash 脚本、Gradle 或 Maven 插件等。

****
### 5.2 使用官方脚手架生成 Spring Boot 项目

```text
┌───────────────────────────────────────────────────────────────┬───────────────────────────────────────────────────────────────┐
│                            Project                            │                         Dependencies  | ADD DEPENDENCIES      │
├───────────────────────────────────────────────────────────────┼───────────────────────────────────────────────────────────────┤
│ Project // 工程构建工具                                         │ ADD DEPENDENCIES... CTRL + B                                 │
│   ◉ Gradle - Groovy                                          │ No dependency selected                                       │
│   ○ Gradle - Kotlin                                          │  // 做 web 项目一般就添加 Spring WEB 依赖                         │
│   ○ Maven                                                    │                                                                │
│                                                               │                                                               │
│ Language                                                     │                                                               │
│   ◉ Java                                                     │                                                               │
│   ○ Kotlin                                                   │                                                               │
│   ○ Groovy                                                   │                                                               │
│                                                              │                                                               │
│ Spring Boot // Spring Boot 版本选择                           │                                                               │
│   ○ 4.0.0 (SNAPSHOT)                                         │                                                               │
│   ○ 3.5.1 (SNAPSHOT)                                         │                                                               │
│   ◉ 3.5.0                                                    │                                                               │
│   ○ 3.4.7 (SNAPSHOT)                                         │                                                               │
│   ○ 3.4.6                                                    │                                                               │
│   ○ 3.3.13                                                   │                                                               │
│                                                              │                                                               │
│ Project Metadata // 工程信息描述                               │                                                               │
│   Group: com.example                                         │                                                               │
│   Artifact: demo // Maven 项目坐标的一部分，用于标识项目，打包文件名│                                                               │
│   Name: demo // 项目的描述性名称，主要用于人类阅读，一般不出现在项目中│                                                               │
│   Description: Demo project for Spring Boot                  │                                                               │
│   Package name: com.example.demo // 工程根包，主程序入口所在包   │                                                               │
│   Packaging: ◉ Jar   ○ War                                   │                                                               │
│   Java: ○ 24   ◉ 21   ○ 17                                   │                                                               │
│                                                               │                                                               │
│                                                               │                                                               │
│                ┌───────────────┐   ┌───────────────┐          │
│                │ GENERATE      │   │ EXPLORE       │          │
│                │ CTRL + ↵      │   │ CTRL + SPACE  │          │
│                └───────────────┘   └───────────────┘          │
└───────────────────────────────────────────────────────────────┴───────────────────────────────────────────────────────────────┘
```

****
### 5.3 使用 idea 工具的脚手架插件

在创建项目时，勾选 spring boot 那个模块，然后和上面的配置差不多，也是看做什么项目引入什么依赖，例如 web、lomback、mybatis等，因为它实际上还是引用的官方的脚手架

****




