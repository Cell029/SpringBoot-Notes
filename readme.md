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

### @Configuration

被此注解标注的类代表配置类，它的作用本质上就是将一个普通的 Java 类标记为 Spring 的配置类，类似于早期 XML 配置中的 <beans> 标签，通常与 @Bean 结合使用

@Configuration 本身不会产生 Bean，而是其内部带有 @Bean 注解的方法会交给 Spring 容器去执行，并将返回对象注册成 Bean

```java
@Configuration
public class MyConfig {
    @Bean
    public MyService myService() {
        return new MyService();
    }
}
```

需要注意的是：使用 @Configuration 的类会被 Spring 通过 CGLIB 动态代理增强，从而确保 @Bean 方法调用时不会创建多个实例

```java
@Configuration
public class MyConfig {
    @Bean
    public A a() {
        return new A();
    }
    @Bean
    public B b() {
        return new B(a()); // Spring 会自动拿容器中的 A，而不是 new 一个新的
    }
}
```

如果上面的注解使用的是普通的 @Component，那么这种情况下 a() 不会被代理，多次调用会创建多个实例，当然也可也通过添加属性(@Configuration(proxyBeanMethods = false)，默认值为 true)来不使用 CGLIB 动态代理

### @EnableConfigurationProperties 与 @ConfigurationPropertiesScan

@EnableConfigurationProperties 是用来启用 @ConfigurationProperties 注解的配置类，并将其作为 Spring Bean 注入容器，被此注解标注的配置类的存放路径随意，不需要被 Spring 扫描包路径覆盖到，
即存放路径与主入口程序无关


```java
// 不加 @Component！
@ConfigurationProperties(prefix = "myapp")
public class UserProperties {
    private String name;
    private Integer age;
    // Getter、Setter
}
```

Spring Boot 会将 UserProperties 注册为一个 Bean，并从配置文件中自动绑定属性，但 AppConfig 配置类还是得放在 Spring 能扫描到的位置

```java
@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class AppConfig {
    @Autowired
    UserProperties userProperties;
}
```

@ConfigurationPropertiesScan 是用来自动扫描并注册所有标注了 @ConfigurationProperties 的类为 Bean，
这样就不需要再去加 @Component 和 @EnableConfigurationProperties（即不需要被扫描到），Spring 会自动扫描并注册为 Bean。

```java
@SpringBootApplication
// 在主程序类上使用，如果配置类就在主类包或子包中，也可以省略 basePackages
@ConfigurationPropertiesScan(basePackages = "com.cell.first.springboot.config")
public class MyApplication {
}
```

```java
@ConfigurationProperties(prefix = "app.user")
public class UserProperties { ... }

@ConfigurationProperties(prefix = "app.db")
public class DbProperties { ... }
```



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
### 5.3 YAML

#### 1. 概述

SpringBoot 采用集中式配置管理，所有的配置都编写到一个配置文件中：application.properties，如果配置非常多，层级就会不够分明，
因此 SpringBoot 为了提高配置文件可读性，也支持了 YAML 格式的配置文件：application.yml

YAML（YAML Ain't Markup Language）是一种人类可读的数据序列化格式，它通常用于配置文件，在各种编程语言中作为一种存储或传输数据的方式。
YAML 的设计目标是易于阅读和编写，同时保持足够的表达能力来表示复杂的数据结构，文件后缀可以是 .yaml 或 .yml

常见的数据存储和交换格式：

`properties`、`XML`、`JSON`、`YAML` 这几种格式是用来存储和交换数据的常见方式，但它们各有特点和适用场景：

**Properties**

+ 这种格式主要用于 Java 应用程序中的配置文件。它是键值对的形式，每一行是一个键值对，使用等号或冒号分隔键和值
+ 特点是简单易懂，但在处理复杂结构的数据时能力较为有限

**XML**

+ XML是一种标记语言，用来描述数据的格式，它支持复杂的数据结构，包括嵌套和属性
+ XML文档具有良好的结构化特性，适合传输和存储结构化的数据。但是，XML文档通常体积较大，解析起来也比较耗资源

**JSON**

+ JSON是一种轻量级的数据交换格式，易于人阅读和编写，同时也易于机器解析和生成。它是基于JavaScript的一个子集，支持多种数据类型，如数字、字符串、布尔值、数组和对象
+ JSON因为简洁和高效而广泛应用于Web应用程序之间进行数据交换

**YAML**

+ YAML设计的目标之一就是让人类更容易阅读，它支持类似JSON的数据序列化，但提供了更多的灵活性，例如缩进来表示数据结构
+ YAML非常适合用来编写配置文件，因为它允许以一种自然的方式组织数据，并且可以包含注释和其他人类可读的元素

****
#### 2. 语法规则

1. 数据结构：YAML支持多种数据类型，包括：

- 字符串、数字、布尔值 
- 数组、list集合 
- map键值对等

2. YAML使用一个空格来分隔属性名和属性值，例如：

- `properties`文件中这样的配置：name=jack
- `yaml`文件中需要这样配置：name: jack

3. YAML用 换行+空格 来表示层级关系。注意不能使用tab，必须是空格，空格数量无要求，大部分建议2个或4个空格。例如：

- `properties`文件中这样的配置：myapp.name=mall
- `yaml`文件中就需要这样配置：

```yaml
myapp:
  name: mall
```

4. 同级元素左对齐。例如：

- `properties`文件中有这样的配置：

```properties
myapp.name=mall
myapp.count=10
```

- `yaml`文件中就应该这样配置：

```yaml
myapp:
  name: mall
  count: 10
```

5. 键必须是唯一的：在一个映射中，键必须是唯一的 
6. 注释：使用`#`进行注释
7. 大小写敏感

需要注意的是：

第一：普通文本也可以使用单引号或双引号括起来：（当然普通文本也可以不使用单引号和双引号括起来。）

+ 单引号括起来：单引号内所有的内容都被当做普通文本，不转义（例如字符串中有\n，则\n被当做普通的字符串）
+ 双引号括起来：双引号中有 \n 则会被转义为换行符

第二：保留文本格式

+ |      将文本写到这个符号的下层，会自动保留格式。

第三：文档切割

+ --- 这个符号下面的配置可以认为是一个独立的yaml文件。便于庞大文件的阅读。

****
### 5.4 配置文件合并

一个项目中所有的配置全部编写到`application.properties`文件中，会导致配置臃肿，不易维护，有时我们会将配置编写到不同的文件中，
例如：`application-mysql.properties`专门配置mysql的信息，`application-redis.properties`专门配置redis的信息，最终将两个配置文件合并到一个配置文件中。

#### 1. 合并 .properties

application-mysql.properties：

```properties
spring.datasource.username=root
spring.datasource.password=123456
```

application-redis.properties：

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

application.properties：

```properties
# optional 表示可选的，如果没有找到对应的配置文件也不会报错
spring.config.import=optional:classpath:/config/application-mysql.properties,optional:classpath:/config/application-redis.properties
```

测试：[UserServiceMulti.java](./Demo1-first_code/src/main/java/com/cell/first/springboot/service/UserServiceMulti.java)

****
#### 2. 合并 .yaml

application-mysql.yml:

```yaml
spring:
  datasource:
    username: root
    password: 789789
```

application-redis.yml：

```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
```

application.yml：

```yaml
spring:
  config:
    import:
      - classpath:application-mysql.yml
      - classpath:application-redis.yml
```

测试：UserServiceMultiYaml.java](./Demo1-first_code/src/main/java/com/cell/first/springboot/service/UserServiceMultiYaml.java)。
因为这两种文件的表示内容一致，在使用时要注意使用的是哪个文件中的数据

****
### 5.5 多环境切换

在 Spring Boot 中，多环境切换是指在一个应用程序中支持多种运行环境配置的能力。这通常用于区分开发（development）、测试（testing）、预生产（staging）和生产（production）等不同阶段的环境。
这种功能使得开发者能够在不同的环境中使用不同的配置，比如数据库连接信息、服务器端口、环境变量等，而不需要更改代码。这对于维护一个可移植且易于管理的应用程序非常重要。

1. 开发环境的配置文件名一般叫做：`application-dev.properties`

```properties
spring.datasource.username=dev
spring.datasource.password=dev123
spring.datasource.url=jdbc:mysql://localhost:3306/dev
```

2. 测试环境的配置文件名一般叫做：`application-test.properties`

```properties
spring.datasource.username=test
spring.datasource.password=test123
spring.datasource.url=jdbc:mysql://localhost:3306/test
```

3. 预生产环境的配置文件名一般叫做：`application-preprod.properties`

```properties
spring.datasource.username=preprod
spring.datasource.password=preprod123
spring.datasource.url=jdbc:mysql://localhost:3306/preprod
```

4. 生产环境的配置文件名一般叫做：`application-prod.properties`

```properties
spring.datasource.username=prod
spring.datasource.password=prod123
spring.datasource.url=jdbc:mysql://localhost:3306/prod
```

可通过以下操作修改使用的配置文件：

+ 第一种方式：在 `application.properties` 文件中添加这个配置：`spring.profiles.active=prod`，yaml 中则为：

```yaml
spring:
  profiles:
    active: prod
```

+ 第二种方式：在命令行参数上添加：`--spring.profiles.active=prod`

需要注意的是：

就算没有合并或引入相关文件，

```properties
spring.config.import=optional:classpath:/config/application-mysql.properties,optional:classpath:/config/application-redis.properties
--spring.profiles.active=prod
```

但项目中存在该文件，

```text
config/
├── application.properties
├── application-mysql.properties
├── application-redis.properties
├── application-prod.properties  ← 存在！
```

那么 Spring Boot 就会：

1. 先加载主配置 application.properties 
2. 再自动加载 spring.config.import 引入的 application-mysql.properties 和 application-redis.properties 
3. 接着根据 spring.profiles.active=prod，自动加载 application-prod.properties 
4. 如果 application-prod.properties 中也写了 spring.config.import，它也会继续导入更多文件

当然，如果配置了多个环境，且文件中存在相同的内容，那么最后加载的文件中的内容会覆盖前面的内容：

1. application.properties（主配置）
2. spring.config.import 指定的文件（如 mysql/redis）
3. application-{profile}.properties（如 application-prod.properties）
4. 命令行参数、系统环境变量、@TestPropertySource 等

****
### 5.6 将配置绑定到bean

#### 1. 绑定简单 bean

SpringBoot 配置文件中的信息可以通过使用 @ConfigurationProperties(prefix = "配置文件中属性前缀") 将配置信息一次性赋值给 Bean 对象的属性，例如[EasyBeanConfig.java](./Demo1-first_code/src/main/java/com/cell/first/springboot/config/EasyBeanConfig.java)

1. 配置文件中的各种属性要和 bean 对象的属性名对应上（属性名相同）
2. 这样的 bean 需要使用 `@Component` 注解进行标注，纳入IoC容器的管理。`@Component`注解负责创建Bean对象，`@ConfigurationProperties(prefix = "app")`注解负责给bean对象的属性赋值
3. bean 的属性需要是非 static 的属性
4. 底层通过调用对应的 setter 方法进行数据的绑定

****
#### 2. 绑定嵌套 bean

编写一个 [User](./Demo1-first_code/src/main/java/com/cell/first/springboot/bean/User.java)，一个 [Address](./Demo1-first_code/src/main/java/com/cell/first/springboot/bean/Address.java)，Address 作为 User 的嵌套字段，
编写配置文件:

```yaml
app:
  xyz:
    username: Jack
    age: 60
    email: jack@123.com
    address:
      city: BJ
      street: ChaoYang
      zipcode: 123456
```

作为嵌套字段，由 User 类中的属性名作为上一级，Address 的属性作为下一级，但需要注意的是，Address 类不需要使用 @Component 注解纳入 IoC 容器管理。
因为 Address 是作为字段而存在的，它不属于 Bean 的范畴，由 Spring 通过反射机制和类型推断自动创建和绑定，通过无参构造创建，setter 方法赋值

****
#### 3. 将配置赋值到 Map/List/Array 属性上

绑定这些属性和绑定普通的一样，不过是在一个属性名下写多个：

```yaml
#数组
names:
  - jackson
  - lucy
  - lili

#List集合
products: 
  - name: 西瓜
    price: 3.0
  - name: 苹果
    price: 2.0

#Map集合
vips:
  vip1:
    name: 张三
    age: 20
  vip2:
    name: 李四
    age: 22
```

```properties
# 数组（也可以看作字符串列表）
names[0]=jackson
names[1]=lucy
names[2]=lili

# List 集合（嵌套对象）
products[0].name=西瓜
products[0].price=3.0
products[1].name=苹果
products[1].price=2.0

# Map 集合（key 是 vip1、vip2）
vips.vip1.name=张三
vips.vip1.age=20
vips.vip2.name=李四
vips.vip2.age=22
```

****
#### 4. 将配置绑定到第三方对象

假设一个第三方库[AliyunSmsProperties](./Demo1-first_code/src/main/java/com/cell/first/properties/AliyunSmsProperties.java)，通过注解显示注册它，
通过一个[SmsConfig](./Demo1-first_code/src/main/java/com/cell/first/springboot/config/SmsConfig.java)配置文件使用它。

如果这个第三方库无法添加修改代码，那么可以写一个包装类来达到修改的目的：

```java
@ConfigurationProperties(prefix = "sms")
public class SmsPropertiesWrapper {
    private AliyunSmsProperties props = new AliyunSmsProperties();
    // 通过调用这个配置类的 get 方法获取第三方库的信息
    public AliyunSmsProperties getProps() {
        return props;
    }
    public void setProps(AliyunSmsProperties props) {
        this.props = props;
    }
}
```

****
#### 5. 指定数据来源

使用 @PropertySource 注解指定配置文件的位置，这个配置文件可以是 `.properties`，也可以是 `.xml`，但不支持 yaml 格式的文件。

```java
@Configuration
@ConfigurationProperties(prefix = "group")
// 这种方式的命名可以随意
@PropertySource("classpath:a/b/group-info.properties")
public class Group {
}
```

****
#### 6. @ImportResource 注解

创建Bean的三种方式总结：

+ 第一种方式：编写applicationContext.xml文件，在该文件中注册Bean，Spring容器启动时实例化配置文件中的Bean对象
+ 第二种方式：@Configuration注解结合@Bean注解
+ 第三种方式：@Component、@Service、@Controller、@Repository等注解

在 SpringBoot 中可以通过此注解完成第一种方式，需要在主入口程序类上添加 @ImportResource("classpath:applicationContext.xml")，[测试](./Demo1-first_code/src/test/java/com/cell/first/springboot/test/XmlTest.java)

****
#### 7. Environment

SpringBoot 框架在启动时会将系统配置、环境信息全部封装到对象中，如果要获取这些环境信息，可以调用 Environment 接口的方法，
Environment 接口由 Spring 框架提供，Spring Boot 应用程序通常会使用 Spring 提供的实现类 AbstractEnvironment 及其子类来实现具体的环境功能

Environment 对象封装的主要数据包括：

1. Active Profiles: 当前激活的配置文件列表。Spring Boot允许应用程序定义不同的环境配置文件（如开发环境、测试环境和生产环境），通过激活不同的配置文件来改变应用程序的行为
2. System Properties: 系统属性，通常是操作系统级别的属性，比如操作系统名称、Java版本等
3. System Environment Variables: 系统环境变量，这些变量通常是由操作系统提供的，可以在启动应用程序时设置特定的值
4. Command Line Arguments: 应用程序启动时传递给主方法的命令行参数
5. Property Sources: Environment 还包含了一个 PropertySource 列表，这个列表包含了从不同来源加载的所有属性。PropertySource 可以来自多种地方，比如配置文件、系统属性、环境变量等

[测试](./Demo1-first_code/src/main/java/com/cell/first/springboot/config/EnvironmentConfig.java)

****
## 6. SpringBoot 中进行 AOP 开发

Spring Boot 的 AOP 编程和 Spring 框架中 AOP 编程的唯一区别是：引入依赖的方式不同，其他内容完全一样。Spring Boot中 AOP 编程需要引入 aop 启动器：

```xml
<!--aop启动器-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

不同类型的通知使用：

- @Before：前置通知，方法执行前执行
- @Around：环绕通知，方法执行前后都可执行
- @After：后置通知，方法执行后执行（不论是否抛异常）
- @AfterReturning：返回通知，方法成功返回后执行
- @AfterThrowing：异常通知，抛出异常时执行

步骤：

- 第一步：定义切面类 [LogAspect](./Demo1-first_code/src/main/java/com/cell/first/springboot/aop/LogAspect.java)
- 第二步：开启 AOP 自动代理支持：

```java
// Spring Framework 本身不会自动开启 AOP，需要手动加上此注解，但 Spring Boot 会自动开启
// 强制使用 CGLIB 代理，不管目标类有没有接口
@EnableAspectJAutoProxy(proxyTargetClass = true)
```

- 第三步：定义业务类 [OrderServiceImpl](./Demo1-first_code/src/main/java/com/cell/first/springboot/service/OrderServiceImpl.java)
- 第四步：测试

****
# 三. SSM 整合

## 1. 整合持久层框架 MyBatis

### 编写数据源配置

```properties
# springboot 框架自动生成
spring.application.name=Demo2-ssm

# mybatis 连接数据库的数据源
# HikariCP 是 Spring Boot 默认的连接池
spring.datasource.type=com.zaxxer.hikari.HikariDataSource 
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springboot-notes
spring.datasource.username=root
spring.datasource.password=123
```

### 编写 Mapper 接口

使用 Mapper 接口查询数据，需要在主程序入口上添加 Mapper 包扫描

```java
// 只处理 Java 接口，不处理 XML 文件
@MapperScan(basePackages = {"com.cell.first_ssm.mapper"})
// 或者在每个 Mapper 接口上添加 @Mapper
```

然后配置 XML 映射文件路径：

```properties
# 告诉 mybatis 在哪找 Mapper 映射文件，这个配置必须指定，否则 MyBatis 找不到对应的 SQL 映射文件，调用 Mapper 接口会报错
mybatis.mapper-locations=classpath:mapper/*.xml
```

字段自动映射：

当使用MyBatis作为ORM框架时，默认情况下它会将SQL查询结果映射到Java对象的属性上。如果数据库中的字段名与Java对象的属性名不一致，
那么就需要手动为每个字段指定相应的属性名，或者使用某种方式来自动转换这些名称：

```properties
# 将下划线分隔的字段名转换成驼峰式命名
mybatis.configuration.map-underscore-to-camel-case=true
```

### 测试

[MybatisTest.java](./Demo2-ssm/src/test/java/com/cell/first_ssm/test/MybatisTest.java)

****
## 2. Lombok 库

Lombok 是一个 Java 库，它可以通过注解的方式减少 Java 代码中的样板代码。Lombok 可以自动生成构造函数、getter、setter、equals、hashCode、toString 方法等。
Lombok 只是一个编译阶段的库，能够自动补充代码，在 Java 程序运行阶段并不起作用，因此 Lombok 库并不会影响 Java 程序的执行效率

### 2.1 Lombok 的主要注解

@Data：

+ 等价于 `@ToString` + `@EqualsAndHashCode` + `@Getter` + `@Setter` + `@RequiredArgsConstructor`
+ 用于生成：必要参数的构造方法（仅包含 final 字段）、getter、setter、toString、equals 和 hashcode 方法

@Getter / @Setter：

+ 分别用于生成所有的 getter 和 setter 方法
+ 可以作用于整个类，也可以作用于特定的字段

@NoArgsConstructor：

+ 生成一个无参构造方法

@AllArgsConstructor：

+ 生成一个包含所有实例变量的构造器

@RequiredArgsConstructor：

- 生成包含所有被 `final` 修饰符修饰的实例变量的构造方法
- 如果没有包含 final 的实例变量，则自动生成无参数构造方法

@ToString / @EqualsAndHashCode：

+ 用于生成 toString 和 equals/hashCode 方法
+ 这两个注解都有 exclude 属性，通过这个属性可以定制 toString、hashCode、equals 方法，将标记的字段排除

```java
@ToString(exclude = {"password"})
public class User {
    private String username;

    // 或者使用此方式排除
    // @ToString.Exclude
    private String password;
}
```

****
### 2.2 Lombok 其他常用注解

#### 1. @Value

该注解会给所有属性添加 `final`，给所有属性提供 `getter` 方法，自动生成 `toString`、`hashCode`、`equals`，通过这个注解可以创建不可变对象。
使用此注解会生成全参数构造方法、getter方法、hashCode、equals、toString方法（因为字段加了 finale，所以没有setter方法）

#### 2. @Builder

##### GoF23 种设计模式之一：建造模式

建造者模式是一种创建型设计模式，它将一个复杂对象的构建过程与它的表示分离，使得同样的构建过程可以创建不同的表示，可用于解决对象创建时参数过多的问题，
它通过将对象的构造过程与其表示分离，使得构造过程可以逐步完成，而不是一次性提供所有参数

适用场景：

1. 对象的创建过程复杂（有很多参数或依赖关系）
2. 需要创建的对象具有多种配置方式或组合方式
3. 构造函数参数太多，参数顺序容易混淆
4. 希望构建过程可读性强、可扩展、可链式调用

建造者模式主要包含以下四个角色：

1. Product（产品类）：是要构建的复杂对象
2. Builder（抽象建造者）：是构建 Product 的规范（接口/抽象类）
3. ConcreteBuilder（具体建造者）：实现 Builder 接口，完成具体构建逻辑
4. Director（指挥者）：控制建造流程，将各个步骤组合成完整的产品

举例：

- 构建产品类 [Computer](./Demo2-ssm/src/main/java/com/cell/first_ssm/builder/bean/Computer.java)

Computer 是最终要创建的对象，是建造者模式中的产品类（Product），它本身没有复杂的构建逻辑，只是数据容器，通过不同的 Builder 设置它的各个部件，如 CPU、内存、硬盘，
例如：

```java
Computer c = new Computer();
c.setCpu("i9");
c.setRam("32GB");
c.setStorage("2TB");
```

建造者模式的作用就是把这些设置过程抽象出来封装管理

- 创建抽象建造者 [ComputerBuilder](./Demo2-ssm/src/main/java/com/cell/first_ssm/builder/bean/ComputerBuilder.java)

定义了创建一个 Computer 的步骤标准，包括建 CPU、建 RAM、建硬盘三个步骤。
多个建造者类（比如游戏电脑、办公电脑、家用电脑）都要构建 Computer，但步骤逻辑不同，这个抽象类是规范，确保所有建造者都实现这些步骤:

```java
public abstract void buildCpu();
public abstract void buildRam();
public abstract void buildStorage();
```

- 创建具体建造者 [GamingComputerBuilder](./Demo2-ssm/src/main/java/com/cell/first_ssm/builder/bean/GamingComputerBuilder.java)

这个类真正决定了如何构建一台游戏电脑：用什么 CPU、内存多大、用 SSD 还是机械硬盘。因为不同的 builder 构造策略不同，如果要构建办公电脑，就可以创建一个 OfficeComputerBuilder。
不同构建逻辑封装在不同类中，遵守开闭原则

- 创建指挥者 [Director](./Demo2-ssm/src/main/java/com/cell/first_ssm/builder/bean/Director.java)

控制构建的顺序，即告诉建造者“现在该构建 CPU，再构建 RAM，然后是硬盘”。如果有多个 builder 但想统一一套构建顺序，就需要这个 Director，否则每个 builder 都要重复控制构建顺序的逻辑。

```java
public Computer construct() {
    builder.buildCpu();
    builder.buildRam();
    builder.buildStorage();
    return builder.getResult();
}
```

- 使用

```java
ComputerBuilder builder = new GamingComputerBuilder();
Director director = new Director(builder);
Computer computer = director.construct();
System.out.println(computer);
```

****
#### 使用 @Builder 注解自动生成建造模式的代码

通过此注解，只需要保留一个 Computer 类即可，@Builder 自动生成一个静态内部类 ComputerBuilder，里面有 cpu()、ram()、storage() 方法和 build() 方法

```java
@Builder
@ToString
public class Computer {
    private String cpu;
    private String ram;
    private String storage;
}
```

使用：

```java
Computer computer = Computer.builder()
                            .cpu("AMD Ryzen 9") // 等价于 buildCpu
                            .ram("64GB DDR5") // 等价于 buildRam
                            .storage("2TB NVMe SSD") // 的国家与 buildStorage
                            .build(); // 等价于 Director#construct
System.out.println(computer);
```

****
#### @Singular

@Singular 注解是辅助 @Builder 注解的，当被建造的对象的属性是一个集合，这个集合属性使用 @Singular 注解进行标注的话，可以连续调用集合属性对应的方法完成多个元素的添加（不支持数组）。
如果没有这个注解，则无法连续调用方法完成多个元素的添加

```java
@Builder
public class Team {
    private String name;

    @Singular
    private List<String> members;
}
```

```java
Team team = Team.builder()
                .name("开发组")
                .member("Alice") // 添加单个成员
                .member("Bob")
                .build();

// 也可也一次性添加，即未使用 @Singular
Team.builder()
    .name("开发组")
    .members(Arrays.asList("Alice", "Bob")) 
    .build();
```

此注解大致会生成如下的代码：

```java
public static class TeamBuilder {
    private List<String> members = new ArrayList<>();
    // @Singular 默认通过字段名生成方法名，List<String> members 生成方法：member() / members()
    public TeamBuilder member(String member) {
        this.members.add(member);
        return this;
    }
    public TeamBuilder members(Collection<? extends String> members) {
        this.members.addAll(members);
        return this;
    }
    public Team build() {
        team.members = Collections.unmodifiableList(new ArrayList<>(members));
        return team;
    }
}
```

****
#### @Slf4j

Lombok 支持多种日志框架的注解，可以根据你使用的日志框架选择合适的注解，以下是 Lombok 提供的部分日志注解及其对应的日志框架：

1. `@Log4j`：
    - 自动生成一个 `org.apache.log4j.Logger` 对象
    - 适用于 Apache Log4j 1.x 版本
2. `@Slf4j`：
    - 自动生成一个 `org.slf4j.Logger` 对象
    - 适用于 SLF4J（Simple Logging Facade for Java），这是一种日志门面，可以与多种实际的日志框架（如 Logback、Log4j 等）集成
3. `@Log4j2`：
    - 自动生成一个 `org.apache.logging.log4j.Logger` 对象
    - 适用于 Apache Log4j 2.x 版本

选择哪个注解取决于使用的日志框架，例如：

+ 如果使用的是 SLF4J，可以选择 `@Slf4j`
+ 如果使用的是 Log4j 1.x，可以选择 `@Log4j`
+ 如果使用的是 Log4j 2.x，可以选择 `@Log4j2`

需要注意的是：确保在使用这些注解之前，已经在项目中引入了相应的日志框架依赖。例如，如果使用 SLF4J，就需要在项目中添加 SLF4J 的依赖，以及一个具体的日志实现（如 Logback），对于其他日志框架，也需要相应地添加依赖。

****
## 3. MyBatis 逆向工程

MyBatis Generator（MBG）是一款自动根据数据库表结构生成代码的工具，可以快速生成实体类（POJO）、Mapper 接口和 Mapper 映射 XML 文件，使用时需下载插件 Free MyBatis Tool。

****
## 4. 整合 SpringMVC（SSM整合）

SSM整合：Spring + SpringMVC + MyBatis

Spring Boot 项目本身就是基于 Spring 框架实现的，因此 SSM 整合时只需要整合 MyBatis 框架之后，引入 web 启动器即可完成 SSM 整合

****
# 四. Spring Boot 自动配置

## 1. 自动配置概述

### 1.1 SpringBoot 的两大核心

1. **启动器（Starter）**：

Spring Boot 提供了一系列的 Starter POMs，它们是一组预定义的依赖关系，在项目中引入一个 Starter POM 时，它会自动包含所有必要的 Spring 组件以及合理的默认设置。
开发者不需要手动管理复杂的依赖关系，也不需要担心版本冲突的问题，减少了配置上的出错可能

2. **自动配置（Auto-Configuration）**：  

当添加了特定的 Starter POM 后，Spring Boot 会根据类路径上存在的 jar 包来自动配置 Bean（自动配置相关组件），
比如：SpringBoot 发现类路径上存在 mybatis 相关的类，例如 SqlSessionFactory.class，那么 SpringBoot 将自动配置 mybatis 相关的所有 Bean

在没使用 Spring Boot 框架的时候，用 Spring 集成 MyBatis 框架，需要进行大量的配置：

```xml
<!-- 数据源配置 -->
<bean id="dataSource" class=""/>
<!-- SqlSessionFactory -->
<bean id="sqlSessionFactory" class=""/>
<!-- Mapper 扫描器 -->
<!-- 事务管理器 -->
...
```

使用了 Spring Boot 框架之后，这些配置都不需要提供了，自动配置机制可以全部按照默认的方式自动化完成，只需要在 `application.yml` 中提供以下的配置即可：

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/springboot
    username: root
    password: 123
    type: com.zaxxer.hikari.HikariDataSource
```

****
### 1.2 默认的包扫描规则

springboot 默认情况下只扫描主入口类所在包及子包下的类，这是因为 @SpringBootApplication 注解被 @ComponentScan 标注，代替 spring 以前的这个配置：`<context:component-scan base-packages="主入口类所在包"/>`。
当然也可也通过以下方式扫描其他包：

+ 第一种：@SpringBootApplication(scanBasePackages = "com")
+ 第二种：@ComponentScan("com")

```java
// 通过以下注解达到类似 @SpringBootApplication 的效果
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com")
public class TestApplication {}
```

****
### 1.3 默认配置

springboot 为功能的实现提供了非常多的默认配置，例如：

- tomcat 服务器端口号在没有配置的情况下默认是 8080，但也可以在 application.properties 文件中进行重新配置：server.port=8081
- 配置 thymeleaf 的模板引擎时，默认的模板引擎前缀是 classpath:/templates/，默认的后缀是 `.html`，这些信息也可也修改：

```properties
spring.thymeleaf.prefix=classpath:/templates/html
spring.thymeleaf.suffix=.html
```

springboot 中提供了大量的 XxxProperties 属性类，这些类会使用 @ConfigurationProperties 注解来接收配置的属性信息

****
### 1.4 条件注解

SpringBoot 提供了非常多的自动配置类，但是这些自动配置并不是全部生效，它是按需加载的，导入了哪个启动器，则该启动器对应的自动配置类才会被加载。
任何启动器都会关联引入一个 `spring-boot-starter` 启动器，它是 springboot 框架最核心的启动器。
而 `spring-boot-starter` 又关联引入了 `spring-boot-autoconfigure`，所有的自动配置类都在这里

而按需处理则依靠 SpringBoot 框架中的条件注解来实现的，Spring Boot框架中的 @ConditionalOnXxx 系列注解属于条件注解（Conditional Annotations），
它们用于基于某些条件来决定是否应该创建一个或一组 Bean。这些注解通常用在自动配置类上，当注解中的条件成立时才会执行相应的操作，以确保只有在特定条件满足时才会应用相应的配置。

+ @ConditionalOnClass：当指定的类存在时，才创建Bean
+ @ConditionalOnMissingClass：当指定的类不存在时，才创建Bean
+ @ConditionalOnBean：当容器中存在指定的Bean时，才创建Bean
+ @ConditionalOnMissingBean：当容器中不存在指定的Bean时，才创建Bean
+ @ConditionalOnProperty：当配置文件中存在指定的属性时，才创建Bean。也可以设置属性值需要匹配的值
+ @ConditionalOnResource：当指定的资源存在时，才创建Bean
+ @ConditionalOnWebApplication：当应用程序是Web应用时，才创建Bean
+ @ConditionalOnNotWebApplication：当应用程序不是Web应用时，才创建Bean

如果IoC容器当中存在 A Bean，就创建 B Bean：

```java
@Configuration
public class AppConfig {

    @Bean
    public A a(){
        return new A();
    }

    @ConditionalOnBean(A.class)
    @Bean
    public B b(){
        return new B();
    }
}
```

如果IoC容器当中不存在 A Bean，就创建 B Bean：

```java
@Configuration
public class AppConfig {

    @Bean
    public A a(){
        return new A();
    }

    @ConditionalOnMissingBean(A.class)
    @Bean
    public B b(){
        return new B();
    }
}
```

当类路径当中存在 DispatcherServlet 类，则启用配置，反之则不启用配置：

```java
@ConditionalOnClass(name = {"org.springframework.web.servlet.DispatcherServlet"})
@Configuration
public class MyConfig {
    @Bean
    public A getA(){
        return new A();
    }
}
```

****
## 2. 自动配置实现原理

程序从 main 方法进入执行，主入口类上使用 @SpringBootApplication 进行了标注，而 @SpringBootApplication 是一个复合注解，它里面包含 @EnableAutoConfiguration 注解，
代表启动了自动配置，可以将一些需要用到的配置类全部加载。同样的 @EnableAutoConfiguration 也是个复合注解，它被 @Import(AutoConfigurationImportSelector.class) 标注，
而 @Import 注解就是把 AutoConfigurationImportSelector 作为一个 Bean 加载到 IoC 容器中，而这个 Bean 主要就是负责收集和选择所有符合条件的自动配置类。

在 AutoConfigurationImportSelector 类中的 getAutoConfigurationEntry() 方法就是用来加载并筛选自动配置类的：

```java
 protected AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
     if (!this.isEnabled(annotationMetadata)) {
         return EMPTY_ENTRY;
     } else {
         // 获取 @EnableAutoConfiguration 的所有属性（比如 exclude）
         AnnotationAttributes attributes = this.getAttributes(annotationMetadata);
         // 获取待选的自动配置类，即引入依赖时关联的所有自动配置类
         List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
         // 去重，确保自动配置类不重复
         configurations = this.removeDuplicates(configurations);
         // 获取注解属性 exclude 指定排除的配置类
         Set<String> exclusions = this.getExclusions(annotationMetadata, attributes);
         this.checkExcludedClasses(configurations, exclusions);
         // 移除那些被选择排除的配置类
         configurations.removeAll(exclusions);
         // 筛选需要用到的自动配置类（条件过滤），会检查 @ConditionalXxx 注解
         configurations = this.getConfigurationClassFilter().filter(configurations);
         this.fireAutoConfigurationImportEvents(configurations, exclusions);
         return new AutoConfigurationEntry(configurations, exclusions);
     }
 }
```

- List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);

```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
     List<String> configurations = ImportCandidates.load(AutoConfiguration.class, this.getBeanClassLoader()).getCandidates();
     Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports. If you are using a custom packaging, make sure that file is correct.");
     return configurations;
 }
```

该方法里有个 load() 方法，它把 AutoConfiguration.class 作为参数传递进去

```java
public static ImportCandidates load(Class<?> annotation, ClassLoader classLoader) {
     Assert.notNull(annotation, "'annotation' must not be null");
     ClassLoader classLoaderToUse = decideClassloader(classLoader);
     String location = String.format("META-INF/spring/%s.imports", annotation.getName());
     Enumeration<URL> urls = findUrlsInClasspath(classLoaderToUse, location);
     List<String> importCandidates = new ArrayList();
     while(urls.hasMoreElements()) {
         URL url = (URL)urls.nextElement();
         importCandidates.addAll(readCandidateConfigurations(url));
     }
     return new ImportCandidates(importCandidates);
 }
```

annotation.getName() 是用来获取  AutoConfiguration 的全类名，即 org.springframework.boot.autoconfigure.AutoConfiguration，
然后通过 format() 方法拼接获取到引入依赖时关联的所有配置类的路径，然后返回，接着通过一系列的过滤，选出实际开发中要用到的一些自动配置类，导入 IoC 容器管理

在自动配置类中，它们会通过 @ConditionalXxx 注解筛选条件，例如 DispatcherServletConfiguration：

```java
@AutoConfigureOrder(Integer.MIN_VALUE)
@AutoConfiguration(after = {ServletWebServerFactoryAutoConfiguration.class})
@ConditionalOnWebApplication(type = Type.SERVLET) // 必须是一个 Web 应用
@ConditionalOnClass({DispatcherServlet.class}) // 必须含有 DispatcherServlet 类（SpringMVC 底层封装了）
public class DispatcherServletAutoConfiguration {
}
```

有些自动配置类也会使用 @EnableConfigurationProperties(xxx.class) 来引入一些配置属性类，让 IoC 容器进行管理，然后就可以通过 @ConfigurationProperties(prefix = "...") 来绑定指定前缀的数据信息，
所以有些 application 文件中的默认值需要更改时通常需要指定前缀，例如修改端口号： server.port = 8081

****













