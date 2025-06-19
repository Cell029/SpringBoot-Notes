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
# 二. Spring Boot 核心机制

## 1. 为什么要用继承的方式引入 SpringBoot

一般情况下需要用到什么框架直接引入对应的依赖即可，但是 SpringBoot 项目则推荐采用继承的方式

**继承父工程的优势**

+ 依赖管理：可以在父工程中定义依赖的版本，子模块可以直接引用而不必指定版本号
+ 插件管理：可以在父工程中配置常用的插件及其版本，子模块可以直接使用这些配置
+ 属性设置：可以在父工程中定义一些通用的属性，如项目编码、Java 版本等
+ 统一配置：可以统一多个子模块的构建配置，确保一致性

继承依赖后，可以在 Maven 中看到它直接引入了开发中需要使用的其他依赖，Spring Boot 预先对它们进行了版本的统一管理

**直接引入依赖的局限性**（如果不使用继承父工程的方式，而是通过直接引入依赖的方式来管理项目，那么将失去上述的一些优势）

+ 依赖版本管理：每个子模块都需要单独指定依赖的版本，这会导致大量的重复配置，并且难以维护
+ 插件配置：每个子模块都需要单独配置插件及其版本，无法共享父工程中的插件配置
+ 属性设置：每个子模块都需要单独设置通用的属性，如项目编码、Java 版本等
+ 构建配置：每个子模块的构建配置需要单独维护，难以保证一致性

**总结：选择哪种方式取决于具体需求**

+ 如果希望多个项目之间共享构建配置，则推荐使用继承的方式
+ 如果只是想在项目之间共享代码，那么应该使用依赖关系

### 依赖统一管理的好处

Spring Boot 框架的一个重要特性就是简化了项目依赖管理，它通过提供一个叫做“依赖管理”的功能来帮助开发者更容易地管理和使用第三方库和其他 Spring 组件。
具体来说，Spring Boot 提供了一个包含多个 Spring 和其他常用库的依赖版本配置文件（通常是在 `spring-boot-dependencies` 文件中），
这使得开发者不需要在自己的项目中显式指定这些依赖的版本号。

1. 简化依赖声明

开发者只需要在 `pom.xml` 文件中声明需要的依赖而不需要指定其版本号，因为 Spring Boot 已经为这些依赖指定了版本。如果需要使用 mysql 驱动，只需要添加相应的依赖声明而不需要关心版本。

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <!--也可也强制使用自己想用的版本-->
    <!--<version>8.2.0</version>-->
</dependency>
```

2. 避免版本冲突

当多个库之间存在依赖关系的时候，如果手动管理版本可能会导致版本之间的冲突（即“依赖地狱”）。Spring Boot 提供的统一版本管理可以减少这种冲突的可能性

3. 易于升级

当 Spring Boot 发布新版本时，通常会更新其依赖库到最新稳定版。因此，当升级 Spring Boot 版本时，它所管理的所有依赖也会随之更新到兼容的版本

4. 减少配置错误

由于 Spring Boot 自动处理了依赖的版本，就有效减少了手动输入版本号可能引入的拼写或格式错误

****
## 2. Starter 启动器

>Starter 是一组 Maven 依赖的聚合器，封装了一类功能所需的全部依赖，只需要添加一个 starter，Spring Boot 会自动引入它所依赖的所有库，并进行自动配置。
>如果想做web开发，只需要引入web启动器，web启动器会自动引入web开发所需要的子依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

上面这行代码不仅引入了 Spring MVC，还自动引入了 Spring Boot 核心模块、Jackson JSON 解析库、嵌入式 Tomcat、日志组件（SLF4J + Logback）

**启动器实现原理**

1. 依赖聚合

每个启动器通常对应一个特定的功能集或者一个完整的应用模块，如 `spring-boot-starter-web` 就包含了构建 Web 应用所需的所有基本依赖项，如 Spring MVC, Tomcat 嵌入式容器等

2. 依赖传递

在项目中引入一个启动器时，它不仅会把自身作为依赖加入到项目中，还会把它所有的直接依赖项（transitive dependencies）也加入进来，这意味着不需要单独声明这些依赖项，它们会自动成为项目的一部分

3. 版本管理

启动器内部已经指定了所有依赖项的具体版本，这些版本信息存储在一个公共的 BOM（Bill of Materials，物料清单）文件中，通常是 `spring-boot-dependencies`。当引入启动器时，实际上也间接引用了这个 BOM，从而确保了所有依赖项版本的一致性

4. 自动配置

许多启动器还提供了自动配置（Auto-configuration），这是一种机制，允许 Spring Boot 根据类路径上的可用组件自动设置应用程序。
例如： 如果类路径上有 Spring MVC 和嵌入式 Tomcat，则 Spring Boot 会自动配置它们，并准备好一个 web 应用程序

官方提供的启动器：启动器命名特点：spring-boot-starter-*

非官方提供的启动器：启动器命名特点：*-spring-boot-starter

****
## 3. 核心注解

### @SpringBootApplication 注解

Spring Boot 的主入口程序被 `@SpringBootApplication` 注解标注，它本质上是一个组合注解，使用该注解标注在启动类上，可一键启用配置类、组件扫描和自动配置

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public @interface SpringBootApplication {
    ...
}
```

### @SpringBootConfiguration

作用类似于 @Configuration，是 Spring Boot 的专属配置注解，是对 @Configuration 的进一步封装，用于 Spring Boot 启动类，语义更清晰

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {
}
```

在 @SpringBootApplication 有此注解，证明主入口程序是一个配置类，也就是说主入口中的方法可以被 @Bean 注解标注，被 @Bean 注解的标注的方法会被 Spring 容器自动调用，
并且将该方法的返回对象纳入 IoC 容器的管理

```java
@Bean
public Date getNowDate(){ // 方法名作为bean的id
    return new Date();
}
```

```java
@Controller
@ResponseBody
public class FirstController {

    @Autowired
    private Date date;

    @RequestMapping("date")
    public String date(){
        return date.toString();
    }
}
```

在主入口程序中可以把 Date 注册成 Spring 管理的 bean，然后通过 @Autowired 注解可以获取到 IoC 容器管理的 bean 并完成自动注入，获取当前时间

### @EnableAutoConfiguration（开启自动配置）

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
    ...
}
```

Spring Boot 会根据引入的 Starter 或依赖，在后台自动注册 Bean、配置类、属性文件等，不用再手动配置 XML 或 Java Bean，例如，如果在 SpringBoot 项目中进行了如下配置：

```text
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springboot-notes
spring.datasource.username=root
spring.datasource.password=123
```

并且在依赖中引入了 mybatis 依赖或 mybatis 启动器，那么 SpringBoot 框架将自动化配置以下 bean：

+ SqlSessionFactory: MyBatis 的核心工厂 SqlSessionFactory 会被自动配置，这个工厂负责创建 SqlSession 实例，执行映射文件中的 SQL 语句
+ TransactionManager: DataSourceTransactionManager 会被自动配置来管理与数据源相关的事务

### @ComponentScan

它是 Spring 的原始注解，用于扫描 @Component、@Controller、@Service、@Repository 等注解标注的类

****
## 4. Spring Boot 的单元测试

Spring Boot 是基于 JUnit（默认 JUnit 5）进行测试，并通过 spring-boot-starter-test 提供一整套自动配置和依赖集，可以单独创建一个测试类，然后让 spring 自动注入 service 类，
直接调用它里面的方法即可，需要注意的是，虽然测试类只能放在 test 目录下，但是必须让它的路径和主入口程序一致或者为子目录，不然无法自动注入

```java
@SpringBootTest
class UserServiceTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        userService.save();
    }
}
```

### @SpringBootTest

@SpringBootTest 会创建一个完整的 Spring 应用程序上下文（Application Context），这个上下文包含了应用程序的所有组件和服务，以下是它做的一些主要工作：

1. 创建 ApplicationContext

@SpringBootTest 会模拟执行 SpringApplication.run(...) 启动整个 Spring Boot 应用，这意味着它会加载应用程序的主配置类和其他相关的配置类。
在上面程序中，UserService 就是从 IoC 容器中注入的，整个容器在测试运行前已经启动完毕

2. 加载配置文件

它会查找并加载默认的配置文件，如 `application.properties`

3. 自动配置

如果应用程序依赖于 Spring Boot 的自动配置特性，`@SpringBootTest` 会确保这些自动配置生效。这意味着它会根据可用的类和bean来自动配置一些组件，
如 Web 组件（MVC）、数据库连接(MyBatis)、消息队列等

4. 注入依赖

使用 `@SpringBootTest` 创建的应用程序上下文允许你在测试类中使用 `@Autowired` 注入需要的 bean，就像在一个真实的 Spring Boot 应用程序中一样

****
## 5. 外部化配置

### 5.1 概述

外部化配置（Externalized Configuration）指的是：把配置信息（如数据库连接、端口号、日志路径等）从代码中分离出来，存放在外部文件或系统环境中，
Spring Boot 支持多种形式的外部化配置来源，包括：

- application.properties / application.yml 
- 命令行参数 
- 系统环境变量
- @Value 注入 

外部化配置的优势：

1. 灵活性：配置文件可以独立于应用程序部署，这使得可以根据运行环境的不同来调整配置，而无需修改代码
2. 易于维护：配置变更不需要重新构建和部署应用程序，降低了维护成本
3. 安全性：敏感信息如数据库密码、API密钥等可以存储在外部，并且可以限制谁有权限访问这些配置信息
4. 共享性：多实例或多服务可以共享相同的配置信息，减少重复配置的工作量
5. 版本控制：配置文件可以存放在版本控制系统中，便于跟踪历史版本和回滚配置

与传统配置对比：

在传统的 SSM 三大框架中，如果修改 XML 的配置后，需要对应用重新打包，重新部署。
但使用 Spring Boot 框架的外部化配置修改配置后，不需要对应用重新打包，也不需要重新部署，因为这些文件是放在 jar 包的外面的，只改配置文件，然后重启应用即可

****
### 5.2 application.properties 文件

Spring Boot 是约定大于配置的框架，虽然大部分功能可以默认启用，但有时候需要修改端口、日志级别、数据库信息等系统级参数，这些都可以通过 application.properties 文件完成。
application.properties 配置文件是 SpringBoot 默认的配置文件，它不是必须的，对于应用程序来说，都提供了一套默认配置，
application.properties 是用来覆盖默认配置的，当需要修改默认行为时才需要手写它

Spring Boot 框架在启动时会尝试从以下位置加载 application.properties 配置文件（优先级）：

1. file:./config/：首先在Spring Boot 当前工作目录下的 config 文件夹中查找。

注意：如果没有找到 application.properties 会继续找 application.yml，如果这两个都没有找到，才会进入以下位置查找，以此类推

2. file:./：如果在当前工作目录下 config 目录中找不到时，再从当前工作目录中查找
3. classpath:/config/：如果从工作目录中找不到，会从类路径中找，先从类路径的 /config/ 目录下寻找配置文件
4. classpath:/：如果在 /config/ 下没有找到，它会在类路径的根目录下查找

如果想要指定其他的配置文件位置或者改变默认的行为，可以通过使用命令行 `--spring.config.location=` 后跟路径的方式来指定配置文件的具体位置，
这个命令行参数将来会传进 main 方法的 (String[] args) 参数上

```text
// 绝对路径前要加一个 '/'
java -jar Demo1-first_code.jar --spring.config.location=file:///E:\a\b\application.properties
```

可以在 main 方法里输出命令行参数

```text
--spring.config.location=file:///E:\BaiduNetdiskDownload\document\test\a\b\application.properties
```

@Value 注解可以将 application.properties/application.yml 文件中的配置信息注入/绑定到 java 对象的属性上：@Value("${key}")。
且 @Value 获取的值取决于配置文件的优先级，哪个配置源的优先级高，哪个就会覆盖掉其他，
或者使用 @PropertySource("classpath:myconfig.properties") 注解标注在 AppConfig 配置类上，来显示表名要使用哪个

****









