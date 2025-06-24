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
# 五. Spring Boot 的 Web 开发

## 1. SpringBoot 的 web 自动配置

web 配置依赖原理同上

### 1.1 通过 web 自动配置类逆推 web 配置的 prefix

在自动配置列表中可以找到 web 自动配置相关的类：

```text
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration
org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration
org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration
```

通过跳转到这些配置类，查看它们用 @EnableConfigurationProperties 注册了哪些配置属性类，然后再到这些类中查看它们使用的属性前缀是什么，常用的如下：

```properties
# SpringMVC相关配置
spring.mvc.

# web开发通用配置
spring.web.

# 文件上传配置
spring.servlet.multipart.

# 服务器配置
server.
```

****
### 1.2 Web 自动配置的默认配置

Spring Boot 的 Web 自动配置默认配置了：Spring MVC 核心组件、静态资源、Jackson、异常处理、参数转换、视图解析、格式化、跨域支持等

1. DispatcherServlet

Spring Boot 会自动注册 DispatcherServlet 到 Servlet 容器中，默认映射为 `/`

2. 静态资源自动映射

Spring Boot 会默认从这些路径加载静态资源：

```text
/static
/public
/resources
/META-INF/resources
```

3. 视图解析器

默认使用 InternalResourceViewResolver，根据引入的模板依赖决定视图的前后缀，例如 thymeleaf 模板：前缀：classpath:/templates/；后缀：.html

4. 消息转换器（HttpMessageConverters）

自动支持 JSON、XML、字符串等请求/响应数据类型转换

5. 错误处理

默认处理所有异常并返回 /error 页面或 JSON

6. WebMvcConfigurer 支持

Spring Boot 提供了一个扩展点 WebMvcConfigurerComposite，可以通过实现 WebMvcConfigurer 接口，自定义 Web 配置（如拦截器、跨域、视图等），但是在默认配置的基础上进行额外的添加

****
## 2. WebMvcAutoConfiguration 

当使用 Spring Boot 开发 Web 应用时，它可以自动配置 Spring MVC 的各种默认设置，让开发者不需要编写大量样板配置代码

### 2.1 WebMvc 自动配置是否生效的条件

```java
@AutoConfiguration(after = {DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class, ValidationAutoConfiguration.class})
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class})
@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})
@AutoConfigureOrder(-2147483638)
@ImportRuntimeHints({WebResourcesRuntimeHints.class})
public class WebMvcAutoConfiguration {
}
```

+ @AutoConfiguration(after = { DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,ValidationAutoConfiguration.class })

ebMvcAutoConfiguration自动配置类**加载顺序在以上自动配置类加载后加载

+ @ConditionalOnWebApplication(type = Type.SERVLET)
   
WebMvcAutoConfiguration自动配置类只在 servlet 环境中生效

+ @ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
  
类路径中必须存在 `Servlet.class`、`DispatcherServlet.class`、`WebMvcConfigurer.class`类，WebMvcAutoConfiguration 自动配置类才会生效

+ @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)

   - 类路径中不存在 `WebMvcConfigurationSupport.class` 时 WebMvcAutoConfiguration 自动配置类才会生效
   - 注意：当使用 @EnableWebMvc 注解后，类路径中就会注册一个 WebMvcConfigurationSupport 这样的 bean，这会导致 Spring Boot 的 MVC 自动配置完全失效

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({DelegatingWebMvcConfiguration.class})
public @interface EnableWebMvc {
}

public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
}
```

但该容器中存在 EnableWebMvcConfiguration，且它继承 WebMvcConfigurationSupport，但并没有导致此自动配置类失效，因为 EnableWebMvcConfiguration 是内部类，
在 WebMvcAutoConfiguration 进行加载的时候，EnableWebMvcConfiguration 这个内部类还没有加载，
因此这个时候在容器中还不存在 WebMvcConfigurationSupport 的 Bean，所以 WebMvcAutoConfiguration 仍然会生效

```java
@EnableConfigurationProperties({WebProperties.class})
 public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration implements ResourceLoaderAware {
 }
```

+ @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10) 不重要
  
指定 WebMvcAutoConfiguration 自动配置类的加载顺序

+ @ImportRuntimeHints(WebResourcesRuntimeHints.class) 不重要

运行时引入 WebResourcesRuntimeHints 这个类，这个类的作用是给JVM或者其他组件提示信息的，提示一下系统应该如何处理类和资源

总结来说，WebMvcAutoConfiguration 类在满足以下所有条件时生效：

1. 应用程序是一个Servlet类型的Web应用
2. 环境中有Servlet、DispatcherServlet和WebMvcConfigurer类
3. 容器中没有WebMvcConfigurationSupport的bean

****
### 2.2 WebMvc 自动配置生效后引入的两个 Filter Bean

HiddenHttpMethodFilter Bean：

浏览器的 <form> 表单原生只支持 GET 和 POST 请求。但很多 RESTful 风格接口需要使用 PUT、DELETE、PATCH 等方法。
Spring 提供了 HiddenHttpMethodFilter 来“伪装”这些请求，效果与配置 xml 文件一样，
但默认是不启用的，需要在配置文件中设置启用：`spring.mvc.hiddenmethod.filter.enabled=true`

```java
    @Bean
    @ConditionalOnMissingBean({HiddenHttpMethodFilter.class})
    @ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = {"enabled"})
    public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter();
    }
```

```html
<form method="POST" action="/users/1">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete</button>
</form>

```

FormContentFilter Bean：

默认情况下，Servlet 规范只会对 POST + 表单格式自动解析参数，PUT 和 DELETE 请求不会自动解析 request body 中的表单数据，该过滤器就可以解析，
并注入到 @RequestParam、@RequestBody、HttpServletRequest.getParameter()，默认自动开启，也可禁用：`spring.mvc.formcontent.filter.enabled=false`

```java
    @Bean
    @ConditionalOnMissingBean({FormContentFilter.class})
    @ConditionalOnProperty(prefix = "spring.mvc.formcontent.filter", name = {"enabled"}, matchIfMissing = true)
    public OrderedFormContentFilter formContentFilter() {
        return new OrderedFormContentFilter();
    }
```

****
### 2.3 WebMvc 自动配置生效后引入的 WebMvcConfigurer 接口的实现类

```java
 @Configuration(proxyBeanMethods = false)
 @Import({EnableWebMvcConfiguration.class})
 @EnableConfigurationProperties({WebMvcProperties.class, WebProperties.class})
 @Order(0)
 public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer, ServletContextAware {
 }
```

SpringBoot 通过实现 Spring MVC 的 WebMvcConfigurer 接口在 `WebMvcAutoConfigurationAdapter` 中进行了一系列的 Spring MVC 相关配置，如果后续需要对Spring MVC的相关配置进行修改，
就可以编写一个类继承 WebMvcConfigurer，然后重写对应的方法，以此达到修改与扩展的目的。

在 WebMvcConfigurer 接口中提供了很多方法，需要改变 Spring MVC 的哪个行为，则重写对应的方法即可：

```java
public interface WebMvcConfigurer {
    // 配置请求路径的匹配规则，比如是否后缀匹配、是否大小写敏感、是否保留尾部 /
   default void configurePathMatch(PathMatchConfigurer configurer) {}
    // 控制返回 JSON/XML/HTML 等数据格式的优先级策略
   default void configureContentNegotiation(ContentNegotiationConfigurer configurer) {}
    // 设置异步请求的超时、线程池、自定义拦截器等
   default void configureAsyncSupport(AsyncSupportConfigurer configurer) {} 
   // 配置静态资源是否交由容器默认 Servlet 处理（如 Tomcat 的 DefaultServlet）
   default void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {}
    // 注册自定义格式化器或类型转换器
   default void addFormatters(FormatterRegistry registry) {}
    // 注册 Spring MVC 拦截器（HandlerInterceptor）
   default void addInterceptors(InterceptorRegistry registry) {}
    // 自定义静态资源映射，如将 /img/** 映射到磁盘路径
   default void addResourceHandlers(ResourceHandlerRegistry registry) {}
    // 为不同路径设置跨域策略
   default void addCorsMappings(CorsRegistry registry) {}
    // 设置 URL → 视图名的直接映射（适合跳转首页、登录页等）
   default void addViewControllers(ViewControllerRegistry registry) {}
    // 自定义视图解析策略
   default void configureViewResolvers(ViewResolverRegistry registry) {}
    // 添加自定义参数解析器，用于 @Controller 方法参数扩展
   default void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {}
    // 添加自定义返回值处理器，用于增强控制器返回值处理能力
   default void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {}
    // 替换 Spring MVC 的消息转换器（如 JSON 处理用的 Jackson）
   default void configureMessageConverters(List<HttpMessageConverter<?>> converters) {}
    // 在默认消息转换器的基础上扩展
   default void extendMessageConverters(List<HttpMessageConverter<?>> converters) {}
    // 用于定制 Spring MVC 如何处理控制器方法中发生的异常，并提供相应的错误处理逻辑
   default void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {}
    // 用于定制 Spring MVC 如何处理控制器方法中抛出的异常，允许添加额外的异常处理逻辑
   default void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {}
}
```

因为 `WebMvcAutoConfigurationAdapter` 是 Spring Boot 框架提供的，这个类上又使用了  @EnableConfigurationProperties({WebMvcProperties.class, WebProperties.class}) 注解，
证明有些默认属性是绑定在属性类上的，所以可以通过获取这两个属性类的前缀来进行修改，它们两个对应的前缀是：`spring.mvc` 和 `spring.mvc`

****
## 3. 自动配置中的静态资源处理

web 中的静态资源指的是：js、css、图片等

### 3.1 分析路径

```java
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // isAddMappings 返回一个 addMappings，而它是 Resources 的一个属性，在对象的实例化时自动赋值为 true
    // 检查 resourceProperties 中的 addMappings 属性是否为 false，如果为 false，则表示不启用默认的静态资源映射处理
    // 在配置文件中进行 spring.web.resources.add-mappings=false 配置，可以将其设置为 false
   if (!this.resourceProperties.isAddMappings()) {
       logger.debug("Default resource handling disabled");
   } else {
      // 配置 WebJars 的静态资源处理
      // this.mvcProperties.getWebjarsPathPattern()的执行结果是：/webjars/**
      // 也就是说，如果请求路径是 http://localhost:8080/webjars/** ，则自动去类路径下的 /META-INF/resources/webjars/ 目录中找静态资源
      // 但 webjars 相关资源的路径不能改变，因为它已经在代码中写死了
      // 如果要改变这个默认的配置，需要在配置文件中文件中进行配置：spring.mvc.webjars-path-pattern=...
       this.addResourceHandler(registry, this.mvcProperties.getWebjarsPathPattern(), "classpath:/META-INF/resources/webjars/");
      // 配置普通静态资源处理
      // this.mvcProperties.getStaticPathPattern() 的执行结果是：/**
      // this.resourceProperties.getStaticLocations() 的执行结果是：{ "classpath:/META-INF/resources/","classpath:/resources/", "classpath:/static/", "classpath:/public/" }
      // 也就是说，如果请求路径是：http://localhost:8080/**，根据控制器方法优先原则，会先去找合适的控制器方法，
      // 如果没有合适的控制器方法，静态资源处理才会生效，则自动去类路径下的/META-INF/resources/、/resources/、/static/、/public/ 4个位置找
      // 因为这两个路径都没有写死，所以都可以根据配置文件修改
      // 如果要改变这个默认的配置，需要进行如下的两个配置：
      //    配置URL：spring.mvc.static-path-pattern=...
      //    配置物理路径：spring.web.resources.static-locations=...,...,...,...
       this.addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
           registration.addResourceLocations(this.resourceProperties.getStaticLocations());
           if (this.servletContext != null) {
               ServletContextResource resource = new ServletContextResource(this.servletContext, "/");
               registration.addResourceLocations(new Resource[]{resource});
           }
       });
   }
}
```

****
### 3.2 WebJars 静态资源处理

WebJars 是一种将常用的前端库（如 jQuery、Bootstrap、Font Awesome 等）打包成 JAR 文件的形式，方便在 Java 应用程序中使用。
WebJars 提供了一种标准化的方式来管理前端库，使其更容易集成到 Java 项目中，并且可以利用 Maven 的依赖管理功能。

可以通过添加 webjars 的依赖测试一下：

```xml
<!--添加 Vue 的 webjars-->
<dependency>
    <groupId>org.webjars.npm</groupId>
    <artifactId>vue</artifactId>
    <version>3.5.12</version>
</dependency>
```

默认规则是：当请求路径是 `/webjars/**`，则会去 `classpath:/META-INF/resources/webjars/` 找

通过访问：http://localhost:8080/webjars/vue/3.5.12/index.js 可以获取到该 js 文件中的对应内容

****
### 3.3 普通静态资源处理

当请求路径是 `http://localhost:8080/**` ，根据控制器方法优先原则，会先去找合适的控制器方法，如果没有合适的控制器方法，静态资源处理才会生效，则自动去类路径下的以下4个位置查找：

+ classpath:/META-INF/resources/
+ classpath:/resources/
+ classpath:/static/
+ classpath:/public/ 

### 3.4 静态资源缓存处理

不管是 webjars 的静态资源还是普通静态资源，统一都会执行 addResourceHandler 方法，这个方法最后几行代码就是关于静态资源的缓存处理方式

```java
private void addResourceHandler(ResourceHandlerRegistry registry, String pattern, Consumer<ResourceHandlerRegistration> customizer) {
   if (!registry.hasMappingForPattern(pattern)) {
       ResourceHandlerRegistration registration = registry.addResourceHandler(new String[]{pattern});
       customizer.accept(registration);
       // 以下三个方法都绑定了配置属性类，所以可以通过配置文件的方式进行配置相关信息
       // 设置缓存过期时间
       registration.setCachePeriod(this.getSeconds(this.resourceProperties.getCache().getPeriod()));
       // 设置详细的缓存控制，也同样可以设置缓存时间
       registration.setCacheControl(this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl());
       // 启用 Last-Modified 响应头支持，即最后修改时间的比对
       registration.setUseLastModified(this.resourceProperties.getCache().isUseLastModified());
       this.customizeResourceHandlerRegistration(registration);
   }
}
```

registration.setCachePeriod(getSeconds(this.resourceProperties.getCache().getPeriod()));

- 设置缓存的过期时间（如果没有指定单位，默认单位是秒）
- 浏览器会根据响应头中的缓存控制信息决定是否从本地缓存中加载资源，而不是每次都从服务器重新请求，假设配置了静态资源缓存过期时间为 1 小时（3600 秒），那么浏览器在首次请求某个静态资源后，会在接下来的一小时内从本地缓存加载该资源，而不是重新请求服务器
- 可以通过`application.properties`的来修改默认的过期时间，例如：spring.web.resources.cache.period=3600 或者 spring.web.resources.cache.period=1h

registration.setCacheControl(this.resourceProperties.getCache().getCacheControl().toHttpCacheControl());

- 设置静态资源的 Cache-Control HTTP 响应头，告诉浏览器如何去缓存这些资源
- 常见的 Cache-Control 指令包括：

```text
max-age= ：表示响应在多少秒内有效
public：表示响应可以被任何缓存机制（如代理服务器）缓存
private：表示响应只能被用户的浏览器缓存
no-cache：表示在使用缓存的资源之前必须重新发送一次请求进行验证
no-store：表示不缓存任何响应的资源
例如：max-age=3600, public：表示响应在 3600 秒内有效，并且可以被任何缓存机制缓存
可以通过`spring.web.resources.cache.cachecontrol.max-age=3600`以及`spring.web.resources.cache.cachecontrol.cache-public=true`进行重新配置
```

registration.setUseLastModified(this.resourceProperties.getCache().isUseLastModified());

- 设置静态资源在响应时，是否在响应头中添加资源的最后一次修改时间，SpringBoot 默认配置的是：在响应头中添加响应资源的最后一次修改时间
- 浏览器发送请求时，会将缓存中的资源的最后修改时间和服务器端资源的最后一次修改时间进行比对，如果没有变化，服务端判断资源没更新，就返回 304 Not Modified，仍然从缓存中获取
- 可以通过 `spring.web.resources.cache.use-last-modified=false` 来关闭配置（默认为 true）

****
### 3.5 web 应用的欢迎页面

在四个静态资源路径下都创建 index.html，启动项目，最终访问的首页是 classpath:/META_INF/resources，因为它是数组的首个元素

```java
CLASSPATH_RESOURCE_LOCATIONS = new String[]{"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"}
```

在 WebMvcAutoConfiguration 类中有一个内部类 EnableWebMvcConfiguration，这个类中有这样一段代码：

```java
@Bean
public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
    // 创建一个 WelcomePageHandlerMapping 实例，用来将 / 请求映射到 index.html 页面
    return (WelcomePageHandlerMapping)this.createWelcomePageHandlerMapping(applicationContext, mvcConversionService, mvcResourceUrlProvider, WelcomePageHandlerMapping::new);
}

private <T extends AbstractUrlHandlerMapping> T createWelcomePageHandlerMapping(ApplicationContext applicationContext, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider, WelcomePageHandlerMappingFactory<T> factory) {
   TemplateAvailabilityProviders templateAvailabilityProviders = new TemplateAvailabilityProviders(applicationContext);
   // 获取静态 url，默认为 spring.mvc.static-path-pattern=/static/**
   String staticPathPattern = this.mvcProperties.getStaticPathPattern();
   T handlerMapping = factory.create(templateAvailabilityProviders, applicationContext, this.getIndexHtmlResource(), staticPathPattern);
   handlerMapping.setInterceptors(this.getInterceptors(mvcConversionService, mvcResourceUrlProvider));
   handlerMapping.setCorsConfigurations(this.getCorsConfigurations());
   return handlerMapping;
}

private Resource getIndexHtmlResource() {
   // 获取静态资源路径数组
   String[] var1 = this.resourceProperties.getStaticLocations();
   int var2 = var1.length;
   // 遍历所有路径
   for(int var3 = 0; var3 < var2; ++var3) {
      String location = var1[var3];
      // 这里就是尝试每个路径都访问 location + "index.html"
      Resource indexHtml = this.getIndexHtmlResource(location);
      if (indexHtml != null) {
         return indexHtml;
      }
   }
   ServletContext servletContext = this.getServletContext();
   if (servletContext != null) {
      return this.getIndexHtmlResource((Resource)(new ServletContextResource(servletContext, "/")));
   } else {
      return null;
   }
}

// 使用 ResourceLoader 将字符串路径（如 "classpath:/static/"）变成 Resource 对象
private Resource getIndexHtmlResource(String location) {
   return this.getIndexHtmlResource(this.resourceLoader.getResource(location));
}

// 
private Resource getIndexHtmlResource(Resource location) {
   try {
      // 获取到 index.html 文件，封装成 Resource 对象
      Resource resource = location.createRelative("index.html");
      if (resource.exists() && resource.getURL() != null) {
         return resource;
      }
   } catch (Exception var3) {
   }
   // 如果没找到就返回空
   return null;
}
```

所以，只要请求路径是 `/**` 的，会依次去 `{ "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" }` 这四个位置找 `index.html` 页面作为欢迎页

**favorite icon**：

favicon（也称为“收藏夹图标”或“网站图标”）是大多数现代网页浏览器的默认行为之一，当用户访问一个网站时，
浏览器通常会尝试从该网站的根目录下载名为 favicon.ico 的文件，并将其用作标签页的图标。

如果网站没有提供 favicon.ico 文件，浏览器可能会显示一个默认图标，或者根本不显示任何图标。
为了确保良好的用户体验，网站开发者通常会在网站的根目录下放置一个 favicon.ico 文件。

****
## 4. web 的手动配置（静态资源处理）

如果对 SpringBoot 默认的静态资源处理方式不满意，则可以通过以下方式来改变这些默认的配置：

- 第一种：修改配置文件方式，通过修改`application.properties`或`application.yml` 来添加`spring.mvc`和`spring.web`相关的配置
- 第二种：编写代码方式，SpringMVC 框架提供了 `WebMvcConfigurer` 接口，可以实现它，并对应重写接口中的方法即可改变默认的配置行为

### 4.1 修改配置文件

```properties
# Spring MVC的相关配置
# 1. 设置webjars静态资源的请求路径的前缀
spring.mvc.webjars-path-pattern=/wjs/**
# 2. 设置普通静态资源的请求路径的前缀
spring.mvc.static-path-pattern=/static/**
# 3. 修改静态资源存放位置
spring.web.resources.static-locations=classpath:/static1/,classpath:/static2/
```

修改后就不能访问到默认路径下的 index 页面了，但是通过 `http://localhost:8080/static/index.html或者其他资源` 可以访问到 classpath:/META-INF/resources 目录下的资源，
证明这个路径是默认加载路径，不受手动配置影响

****
### 4.2 编写实现类

想要定制Spring MVC的行为，也可以编写类实现Spring MVC框架提供的一个接口 WebMvcConfigurer，而编写的类只要纳入IoC容器的管理就可以实现配置的修改，因此有两种实现方式：

+ 第一种：编写类实现 WebMvcConfigurer 接口，重写对应的方法
+ 第二种：以组件的形式存在：编写一个方法，用 @Bean 注解标注

#### 编写实现类并重写方法

对于 web 开发来说，配置类一般起名为： WebConfig ，配置类一般存放到 config 包下，
因此在 SpringBoot 主入口程序同级目录下新建 config 包，在 config 包下新建 [WebConfig](./Demo2-ssm/src/main/java/com/cell/web/config/WebConfig.java) 类

****
#### 使用 @Bean

通过 @Bean 的方式，以函数式风格注册 WebMvcConfigurer 实例，核心是返回一个 WebMvcConfigurer 实例，但并不推荐这么用

```java
@Bean
 public WebMvcConfigurer addResourceHandlers(){
     return new WebMvcConfigurer() {
         @Override
         public void addResourceHandlers(ResourceHandlerRegistry registry) {
             registry.addResourceHandler("/static/**")
                     .addResourceLocations("classpath:/static1/", "classpath:/static2/");
         }
     };
 }
```

****
### 4.3 配置生效问题

只要写一个类实现了 WebMvcConfigurer 接口，并被 Spring 容器管理，Spring Boot 会自动发现它，并在 MVC 初始化时合并配置；默认的配置同理，也会自动发现并初始化

WebMvcAutoConfiguration 中有个核心内部类 EnableWebMvcConfiguration，它继承了 DelegatingWebMvcConfiguration，这个类的作用是在不禁用自动配置的前提下，
收集所有用户自定义的 WebMvcConfigurer 实现并委托调用，它里面定义了一个组合对象

```java
// 创建组合对象
private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();
```

也同时定义了一个自动注入的 setter 方法,因为它在该方法上使用了 @Autowired 注解，所以 Spring 会从容器中查找所有实现了 WebMvcConfigurer 接口的 Bean 实例，
然后封装成一个集合，前提是这些实现类使用了相应的注解，例如 @Configuration

```java
@Autowired(required = false)
 public void setConfigurers(List<WebMvcConfigurer> configurers) {
     if (!CollectionUtils.isEmpty(configurers)) {
         this.configurers.addWebMvcConfigurers(configurers);
     }
 }
```

这里面又调用了组合对象的一个组合方法，而该组合对象中又有一个 List 集合，专门存放在 DelegatingWebMvcConfiguration 中注入的 configurers，后续可以通过集合拿出对应的实现类，
然后调用对应的方法

```java
private final List<WebMvcConfigurer> delegates = new ArrayList();

public void addWebMvcConfigurers(List<WebMvcConfigurer> configurers) {
     if (!CollectionUtils.isEmpty(configurers)) {
         // 讲 configurers 结合中的所有元素添加进 delegates 集合
         this.delegates.addAll(configurers);
     }
 }
```

例如 addInterceptors() 组合方法，它通过遍历集合中的所有实现类，依次调用 addInterceptors() 方法，最终会调用到重写的对应的方法，配置的拦截器方法就是被调用在这里

```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    for (WebMvcConfigurer delegate : this.delegates) {
        delegate.addInterceptors(registry);
    }
}
```

WebMvcConfigurer 接口中的所有方法都是 default 方法，默认有空实现，所以并不会导致有些方法不存在导致程序报错，
而这些方法最终是由 DelegatingWebMvcConfiguration 的父类 WebMvcConfigurationSupport 调用的，
这些调用发生在 Spring MVC 初始化过程中，由 Spring 框架自己驱动执行

需要注意的是：如果实现的是 configureXXX() 方法，那会影响默认配置（因为自己构建的是完整配置），如果实现的是 extendXXX() 或 addXXX()，那只是追加，不会影响默认配置。

对于 WebMvcConfigurerComposite 类的代码来说，它是一个非常典型的组合模式，是一种结构型设计模式：

1. 组合多个 WebMvcConfigurer 实例：WebMvcConfigurerComposite 通过 delegates 列表组合了多个 WebMvcConfigurer 实例
2. 统一接口：WebMvcConfigurerComposite 实现了 WebMvcConfigurer 接口，因此可以像一个单一的 WebMvcConfigurer 一样被使用
3. 代理调用：在实现 WebMvcConfigurer 接口的方法时，WebMvcConfigurerComposite 会遍历 delegates 列表，调用每个 WebMvcConfigurer 实例的相应方法

****
## 5. web 请求的路径匹配

在 Spring Boot3 中，对 web 请求的路径匹配提供了两种规则：

+ 第一种：AntPathMatcher（Ant风格），较旧
+ 第二种：PathPatternParser（从 Spring5.3、SpringBoot2.4 中引入的），效率比Ant高，一般新项目中使用


### 5.1 AntPathMatcher

SpringBoot3 中默认使用的是 PathPatternParser，不需要任何配置，如果要使用 AntPathMatcher，就需要进行如下的配置：

```properties
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
```

匹配规则：

- `*`:匹配任意数量的字符，但不跨目录（即不匹配 /），例如：/foo/*.html 匹配 /foo/a.html, /foo/b.html，不匹配 /foo/bar/b.html
- `**`:匹配任意层级的路径，包括 / ，例如：/foo/** 匹配 /foo/, /foo/a, /foo/a/b/c
- `?`:匹配单个任意字符（不包含 /），例如：/data?.json 匹配 /data1.json, /datab.json，不匹配 /data12.json
- `[]`:匹配字符集合或范围中的任意一个字符，例如：/foo[a-c]bar 匹配 /fooabar, /foobbar, /foocbar，不匹配 /foodbar
- `{}`:匹配路径变量，占位符，实际匹配时提取值，例如：/users/{id} 匹配 /users/42，提取 id=42，不匹配 /users/（变量不能为空）

****
### 5.2 PathPatternParser

项目中不做配置，或者按照以下方式配置，都是 PathPatternParser：spring.mvc.pathmatch.matching-strategy=path_pattern_parser。
PathPatternParser 风格是兼容 Ant 风格的，只有一个地方不支持，Ant 支持。在 Ant 风格中，`**` 可以出现在任意位置，
在 PathPatternParser 中只允许 `**` 出现在路径的末尾

****
## 6. 内容协商

内容协商机制是指服务器根据客户端的请求来决定返回资源的最佳表示形式，即客户端要什么格式的数据，后端就应该返回什么格式的数据，在实际开发中可能会要求不同格式的数据：

+ 遗留的老客户端系统，仍然处理的是XML格式的数据
+ 要求处理速度快的这种客户端系统，一般要求返回JSON格式的数据
+ 要求安全性高的客户端系统，一般要求返回XML格式的数据

### 6.1 实现内容协商的方式

通常通过 HTTP 请求头（如 Accept）或请求参数（如 format）来指定客户端偏好接收的内容类型（如JSON、XML等），服务器会根据这些信息选择最合适的格式进行响应。

#### 通过HTTP请求头（如 Accept）

SpringBoot 框架中，在程序员不做任何配置的情况下，优先考虑的是这种方式。
服务器会根据客户端发送请求时提交的请求头中的"Accept: application/json" 或 "Accept: application/xml" 或 "Accept: text/html"来决定响应什么格式的数据。

客户端发送请求给服务器的时候，如何设置请求头的 Accept？有以下几种常见实现方式：

+ 写代码
   - fetch API
   - ajax的XMLHttpRequest
   - axios库
   - jQuery库......
+ 用工具
   - 接口测试工具，例如：Postman、Apifox 等
   - 命令行工具：curl

第一步：引入一个依赖

```xml
<!--将 java 对象转换成 xml 格式的字符串-->
<dependency>
   <groupId>com.fasterxml.jackson.dataformat</groupId>
   <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

第二步：在实体类上添加一个注解

```java
// 添加这个注解可以将 java 对象转换成 xml 格式字符串
@JacksonXmlRootElement
public class User {
}
```

第三步：在黑窗口中输入 `curl -H "Accept: application/xml" http://localhost:8080/detail`

```text
<User>
    <username>jack</username>
    <age>30</age>
</User>

// json 格式则为
{
    "username":"jack",
    "age":30
}
```

****
#### 通过请求参数（如 format）

要使用这种方式就需要通过配置文件开启： 

```properties
# 内容协商时，优先考虑请求参数format方式。
spring.mvc.contentnegotiation.favor-parameter=true
```   

然后输入命令行参数：

```text
curl http://localhost:8080/detail?format=xml
curl http://localhost:8080/detail?format=json
// 输出
<User><username>jack</username><age>30</age></User>
{"username":"jack","age":30}
```

也可也通过配置文件修改 format 的名字，例如修改为 type：

```properties
# 内容协商时，设置请求参数的名字，默认为format
spring.mvc.contentnegotiation.parameter-name=type
```

****
### 6.2 HttpMessageConverter 接口

#### 1. 常见的消息转换器

内置的常见的 HttpMessageConverter 的实现类包括：

+ 【请求】提交的表单（form）数据转换成Java对象的主要任务是由 FormHttpMessageConverter 消息转换器完成的
+ 【请求】提交的 JSON 数据转换成 Java 对象的主要任务是由 MappingJackson2HttpMessageConverter 消息转换器完成的（通常使用 @RequestBody 注解）
+ 【响应】将 Java 对象转换成 JSON 格式的数据，并将其写入 HTTP 响应体的任务是由 MappingJackson2HttpMessageConverter 消息转换器完成（通常使用 @ResponseBody 注解)
+ 【响应】将 Java 对象转换成 XML 格式的数据，并将其写入 HTTP 响应体的任务通常由 Jaxb2RootElementHttpMessageConverter 消息转换器完成
+ 【响应】将 String 直接写入到响应体的任务是由 StringHttpMessageConverter 消息转换器完成

响应时通常根据以下条件来确定使用哪个消息转换器：

1. 请求提交时，请求头上的 Accept 字段 ：

Spring MVC 会检查客户端请求的 Accept 字段，以确定客户端期望的响应格式（例如 application/json、application/xml 等）

2. 方法返回值类型：

控制器方法的返回值类型（例如 @ResponseBody）

例如1：@ResponseBody + 控制器方法的返回值是 String，则使用 StringHttpMessageConverter 转换器（将字符串直接写入响应体）

例如2：@ResponseBody + 控制器方法的返回值是 User，则使用 MappingJackson2HttpMessageConverter 转换器（将 java 对象转换成 json 格式的字符串写入到响应体）

#### 2. 系统默认提供的消息转换器

1. **ByteArrayHttpMessageConverter:**

用于将字节数组(byte[])与HTTP消息体之间进行转换。这通常用于处理二进制数据，如图片或文件。

2. **StringHttpMessageConverter:**

用于将字符串(String)与HTTP消息体之间进行转换。它支持多种字符集编码，能够处理纯文本内容。

3. **ResourceHttpMessageConverter:**

用于将Spring的Resource对象与HTTP消息体之间进行转换。Resource是Spring中表示资源的接口，可以读取文件等资源。这个转换器对于下载文件或发送静态资源有用。

4. **ResourceRegionHttpMessageConverter:**

用于处理资源的部分内容（即“Range”请求），特别是当客户端请求大文件的一部分时。这对于实现视频流媒体等功能很有用。

5. **AllEncompassingFormHttpMessageConverter:**

用于处理表单，是一个比较全面的form消息转换器。处理标准的application/x-www-form-urlencoded格式的数据，以及包含文件上传的multipart/form-data格式的数据。

6. **MappingJackson2HttpMessageConverter:**

使用Jackson库来序列化和反序列化JSON数据，可以将Java对象转换为JSON格式的字符串，反之亦然。

****
#### 3. 自定义消息转换器

因为默认的转换器中没有处理 yaml 格式的，所以可以通过自定一个转换器来实现：

第一步：引入能够处理 yaml 格式的依赖

```xml
<dependency>
  <groupId>com.fasterxml.jackson.dataformat</groupId>
  <artifactId>jackson-dataformat-yaml</artifactId>
</dependency>
```

第二步：新增一种媒体类型 yaml

springboot 默认支持 xml 和 json 两种媒体类型，要支持 yaml 格式就需要新增一个 yaml 媒体类型，在 springboot 的配置文件中进行配置：spring.mvc.contentnegotiation.media-types.yaml=text/yaml。
注意，以上 `types` 后面的 `yaml` 是媒体类型的名字，名字随意，如果媒体类型起名为`xyz`，那么发送请求时的路径应该是这样的：http://localhost:8080/detail?format=xyz

第三步：自定义 HttpMessageConverter

编写类 [YamlHttpMessageConverter](./Demo2-ssm/src/main/java/com/cell/web/config/YamlHttpMessageConverter.java) 继承 AbstractHttpMessageConverter：

```java

```

第四步：配置消息转换器

重写 WebMvcConfigurer 接口的 configureMessageConverters 方法：

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlHttpMessageConverter());
    }
}
```

第五步：黑窗口输入：curl -H "Accept: text/yaml" http://localhost:8080/detail

```yaml
username: "jack"
age: 30
```

****
## 7. SpringBoot 整合 Thymeleaf

### 7.1 传统 web 应用和前后端分离 

现代开发大部分应用都会采用前后端分离的方式进行开发，前端是一个独立的系统，后端也是一个独立的系统，后端系统只给前端系统提供数据（JSON数据），不需要后端解析模板页面，
前端系统拿到后端提供的数据之后，前端负责填充数据即可

传统的 WEB 应用（非前后端分离）：浏览器页面上展示成什么效果，后端服务器说了算，这是传统 web 应用最大的特点。

前后端分离的应用：前端是一个独立的系统，后端也是一个独立的系统，后端系统不再负责页面的渲染，后端系统只负责给前端系统提供开放的API接口，后端系统只负责数据的收集，
然后将数据以 JSON/XML 等格式响应给前端系统，前端系统拿到接口返回的数据后，将数据填充到页面上。

前后端分离的好处：

+ 职责清晰：前端专注于用户界面和用户体验，后端专注于业务逻辑和数据处理。
+ 开发效率高：前后端可以并行开发，互不影响，提高开发速度。
+ 可维护性强：代码结构更清晰，便于维护和扩展。
+ 技术栈灵活：前后端可以独立选择最适合的技术栈。
+ 响应式设计：前端可以更好地处理不同设备和屏幕尺寸。
+ 性能优化：前后端可以独立优化，提升整体性能。
+ 易于测试：前后端接口明确，便于单元测试和集成测试。

****
### 7.2 SpringBoot 支持的模板

1. **Thymeleaf**：
   - **特点**：Thymeleaf 是一个现代的服务器端Java模板引擎，它支持HTML5，XML，TEXT，JAVASCRIPT，CSS等多种模板类型。它能够在浏览器中预览，这使得前端开发更加便捷。Thymeleaf 提供了一套强大的表达式语言，可以轻松地处理数据绑定、条件判断、循环等。
   - **优势**：与Spring框架集成良好，也是SpringBoot官方推荐的
2. **FreeMarker**：
   - **特点**：FreeMarker 是一个用Java编写的模板引擎，主要用来生成文本输出，如HTML网页、邮件、配置文件等。它不依赖于Servlet容器，可以在任何环境中运行。
   - **优势**：模板语法丰富，灵活性高，支持宏和函数定义，非常适合需要大量定制化的项目。
3. **Velocity**：
   - **特点**：Velocity 是另一个强大的模板引擎，最初设计用于与Java一起工作，但也可以与其他技术结合使用。它提供了简洁的模板语言，易于学习和使用。
   - **优势**：轻量级，性能优秀，特别适合需要快速生成静态内容的应用。
4. **Mustache**：
   - **特点**：Mustache 是一种逻辑无感知的模板语言，可以用于多种编程语言，包括Java。它的设计理念是让模板保持简单，避免模板中出现复杂的逻辑。
   - **优势**：逻辑无感知，确保模板的简洁性和可维护性，易于与前后端开发人员协作。
5. **Groovy Templates**：
   - **特点**：Groovy 是一种基于JVM的动态语言，它可以作为模板引擎使用。Groovy Templates 提供了非常灵活的模板编写方式，可以直接嵌入Groovy代码。
   - **优势**：对于熟悉Groovy语言的开发者来说，使用起来非常方便，可以快速实现复杂逻辑。

这些模板技术各有千秋，选择哪一种取决于项目的具体需求和个人偏好。Spring Boot 对这些模板引擎都提供了良好的支持，通常只需要在项目中添加相应的依赖，然后按照官方文档配置即可开始使用。

****
### 7.3 整合 Thymeleaf

SpringBoot内嵌了Servlet容器（例如：Tomcat、Jetty等），使用SpringBoot不太适合使用JSP模板技术，因为SpringBoot项目最终打成jar包之后，放在jar包中的jsp文件不能被Servlet容器解析。
所以更推荐使用Thymeleaf

第一步：引入thymeleaf启动器

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

第二步：编写配置文件，指定前后缀（以下配置也是默认的配置）

```properties
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
```

然后设置控制器和前端页面

****
### 7.4 Thymeleaf 核心语法

注意：在根标签 `<html>` 中引入 `xmlns:th="http://www.thymeleaf.org"`，在编写 `th:` 语法时有智能提示，

- th:text 替换标签体内容

```html
<div th:text="${name}">我是一个DIV</div>
```

标签体中的内容即使是一段HTML代码，也只是会被当做普通文本对待，然后被 th:text 中的内容替换掉，所以实际显示是：request 域中名为 name 的内容

- th:utext 替换标签体内容

作用和 `th:text` 一样，只不过 `th:utext` 会将内容当做一段 HTML 代码解析并替换

```html
<div th:utext="${htmlCode}">我是一个DIV</div>
```

- th:任意属性名 动态替换该属性的值

```java
// 向域中存储一个html标签的某个属性的值
model.addAttribute("company", "哈哈");
model.addAttribute("hrefValue", "http://www.baidu.com");
```

```html
<a th:href="${hrefValue}" href="https://www.baidu.com" th:text="${company}">百度</a>
```

最终显示结果是：哈哈（百度链接）

- th:attr 属性合并设置

分开设置：

```java
model.addAttribute("hrefValue", "http://www.baidu.com");
model.addAttribute("style", "color:red");
```

```html
<a th:href="${hrefValue}" th:style="${style}">哈哈</a>
```

最终显示结果是：哈哈（红色字体的百度链接）

合并设置，使用 th:attr：

```java
model.addAttribute("hrefValue", "http://www.baidu.com");
model.addAttribute("style", "color:red");
```

```html
<a th:attr="href=${hrefValue},style=${style}">哈哈</a>
```

最终显示结果是：哈哈（红色字体的百度链接）

- th:指令

指令非常多，具有代表性的例如：`th:if`，该指令用来控制元素隐藏和显示。

```html
<!--会渲染在页面-->
<img src="1.jpg" th:if="true">
<!--不会渲染在页面-->
<img src="2.jpg" th:if="false">
```

- @{} 表达式

`${}`表达式语法是专门用来获取`model`中绑定的数据的；`@{}`表达式语法是专门用来维护URL请求路径的，它可以动态设置项目的根路径。SpringBoot中默认的项目根路径是：`/`，编写这样的模板代码：

```java
model.addAttribute("imgUrl", "/1.jpg");
```

```html
<img th:src="${imgUrl}">
```

此时是可以正常显示的，但如果将web应用的根路径进行了修改，将其配置为 `/myweb`：server.servlet.context-path=/myweb，此时就无法访问到该图片了，但使用 @{} 后：

```html
<img th:src="@{${imgUrl}}">
```

图片可以正常显示

****
#### Thymeleaf 的内置工具

内置工具很多，可以参考官方文档：[https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#strings](https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html#strings)

例如：

```html
<!--如果name中包含jack就显示图片-->
<img th:src="@{${imgUrl}}" th:if="${#strings.contains(name,'jack')}">
```

****
#### Thymeleaf 的运算符和字符串拼接

当用户名长度大于 6 时才显示图片

```html
<img th:src="@{${imgUrl}}" th:if="${#strings.length(name) > 6}">
```

```html
<span th:text="${user.gender ? '男' : '女'}"></span>
```

字符串拼接：用 `''` 包裹的被视为字符串

- 使用 `+`

```html
<div th:text="${'姓名：' + name + '，年龄：18'}"></div>
```

- 使用 `||`

```html
<div th:text="|姓名：${name}，年龄：18|"></div>
```

****
#### 循环遍历

```html
<tr th:each="user : ${users}">
  <td th:text="${user.name}"></td>
  <td th:text="${user.age}"></td>
  <td th:text="${user.gender}"></td>
  <td th:text="${user.desc}"></td>
  <td th:text="${user.location}"></td>
</tr>
```

+ ${users}：代表集合
+ user：代表集合中的每个元素
+ ${user.name}：元素的name属性值

遍历时也可以添加状态对象：

```html
<tr th:each="user,state : ${userList}">
    <td th:text="${state.count}">1</td>
    <td th:text="${user.name}">张三</td>
    <td th:text="${user.age}">20</td>
    <td th:text="${user.gender}"></td>
    <td th:text="${user.desc}"></td>
    <td th:text="${user.location}"></td>
    <td>
        thymeleaf的内联表达式：<br>
        下标：[[${state.index}]]<br>
        序号：[[${state.count}]]<br>
        当前对象：[[${state.current}]]<br>
        元素总数：[[${state.size}]]<br>
        是否为偶数行：[[${state.even}]]<br>
        是否为奇数行：[[${state.odd}]]<br>
        是否第一个元素：[[${state.first}]]<br>
        是否最后一个元素：[[${state.last}]]<br>
    </td>
</tr>
```

另外，状态对象 `state` 的属性包括：

+ index：下标
+ count：序号
+ current：当前对象
+ size：元素总数
+ even：是否为偶数行
+ odd：是否为奇数行
+ first：是否为第一个元素
+ last：是否为最后一个元素

****
#### 条件判断

- th:if

```html
<td th:if="${#strings.isEmpty(user.desc)}" th:text="'你比较懒没有留下任何介绍信息'"></td>
<td th:if="${not #strings.isEmpty(user.desc)}" th:text="${user.desc}"></td>
```

- th:switch

如果城市编号001则显示北京，002则显示上海，003则显示广州，004则显示深圳，其他值显示未知

```html
<td th:switch="${user.location}">
  <span th:case="001">北京</span>
  <span th:case="002">上海</span>
  <span th:case="003">广州</span>
  <span th:case="004">深圳</span>
  <span th:case="*">未知</span>
</td>
```

****
#### Thymeleaf 的属性优先级

以下是 Thymeleaf 属性的优先级从高到低的列表：

| 优先级 | 属性             | 描述                               |
| ------ | ---------------- | ---------------------------------- |
| 1      | `th:if`          | 如果条件为真，则渲染该元素。       |
| 2      | `th:unless`      | 如果条件为假，则渲染该元素。       |
| 3      | `th:with`        | 定义局部变量。                     |
| 4      | `th:switch`      | 开始一个 switch 语句。             |
| 5      | `th:case`        | 定义 switch 语句中的 case 分支。   |
| 6      | `th:each`        | 遍历列表，用于循环。               |
| 7      | `th:remove`      | 移除元素或其属性。                 |
| 8      | `th:attr`        | 设置或修改元素的属性。             |
| 9      | `th:classappend` | 追加 CSS 类。                      |
| 10     | `th:styleappend` | 追加内联样式。                     |
| 11     | `th:src`         | 设置元素的 `src` 属性。            |
| 12     | `th:href`        | 设置元素的 `href` 属性。           |
| 13     | `th:value`       | 设置元素的 `value` 属性。          |
| 14     | `th:text`        | 设置元素的文本内容。               |
| 15     | `th:utext`       | 设置元素的未转义文本内容。         |
| 16     | `th:html`        | 设置元素的 HTML 内容。             |
| 17     | `th:fragment`    | 定义模板片段。                     |
| 18     | `th:insert`      | 插入一个模板片段。                 |
| 19     | `th:replace`     | 替换当前元素为一个模板片段。       |
| 20     | `th:include`     | 包含一个模板片段的内容。           |
| 21     | `th:block`       | 用于逻辑分组，不产生任何HTML输出。 |


总结：先控制，再遍历，后操作，末内容。

1. **先控制**：`th:if` 和 `th:unless` 用于条件控制，决定是否渲染元素。
2. **再遍历**：`th:each` 用于遍历列表，生成多个元素。
3. **后操作**：`th:with`、`th:switch`、`th:case`、`th:remove`、`th:attr` 等用于局部变量定义、条件分支、属性操作等。
4. **末内容**：`th:text`、`th:utext`、`th:html` 等用于设置元素的内容。

****
#### *{...} 表达式

`*{...}` 主要用于在上下文中访问对象的属性，这种表达式通常在表单处理和对象绑定场景中使用。

1. 表单绑定

```html
<form th:object="${user}" method="post" action="/submit">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" th:field="*{name}" /> 
    <label for="age">Age:</label>
    <input type="number" id="age" name="age" th:field="*{age}" />
    <button type="submit">Submit</button>
</form>
```

+ `th:object="${user}"` 将 `user` 对象设置为当前上下文对象。
+ `th:field="*{name}"` 和 `th:field="*{age}"` 分别绑定到 `user` 对象的 `name` 和 `age` 属性。
+ 也就是把 `<tr th:each="user,state : ${userList}">` 中 each 的 user 放到外面用 `th:object="${user}"` 接收，下面的属性就不需要再用 `user.` 语法格式了

2. 对象属性访问

```html
<div th:object="${user}">
    <p>Name: <span th:text="*{name}">Default Name</span></p>
    <p>Age: <span th:text="*{age}">Default Age</span></p>
</div>
```

+ `th:object="${user}"` 将 `user` 对象设置为当前上下文对象。
+ `*{name}` 和 `*{age}` 分别访问 `user` 对象的 `name` 和 `age` 属性。

**与 **`**${...}**`** 的区别**

+ `${...}`：标准表达式，用于访问模型中的变量和执行简单的表达式。
+ `*{...}`：属性选择表达式，用于在上下文中访问对象的属性，通常与 `th:object` 一起使用

****
#### 代码片段共享

片段是 Thymeleaf 中用于代码复用的基本机制，可以将共享的部分提取到单独的 HTML 文件中，然后在其他模板中引用这些片段。

页面中公共的 header.html：

```html
<header th:fragment="h">
    <nav>
        <ul>
            <li><a th:href="@{/a}">Home</a></li>
            <li><a th:href="@{/b}">About</a></li>
        </ul>
    </nav>
</header>
```

页面中公共的 footer.html：

```html
<footer th:fragment="f">
  <p>&copy; 2025</p>
</footer>
```

创建页面引入头与脚：

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>主页</title>
</head>
<body>
<div th:replace="~{header :: h}"></div>
<main>
    <h1>欢迎来到主页</h1>
    <p>这是主页的主要内容.</p>
</main>
<div th:replace="~{footer :: f}"></div>
</body>
</html>
```

配置视图控制器实现快速跳转：

```java
@Override
public void addViewControllers(ViewControllerRegistry registry) {
  registry.addViewController("/a").setViewName("a");
  registry.addViewController("/b").setViewName("b");
}
```

****
#### Thymeleaf 页面修改如何立即生效

第一步：引入 springboot 提供的 `dev tools`

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-devtools</artifactId>
  <scope>runtime</scope>
  <optional>true</optional>
</dependency>
```

第二步：禁用类路径监控与自动重启，如果不关闭会导致每一次修改 java 代码后立即重启应用。

```properties
spring.devtools.restart.enabled=false
```

****
## 8. 异常处理

在 controller 层如果程序出现了异常，并且这个异常未被捕获，springboot 提供的异常处理机制将生效。Spring Boot 提供异常处理机制主要是为了提高应用的健壮性和用户体验。它的好处包括：

1. **统一错误响应**：可以定义全局异常处理器来统一处理各种异常，确保返回给客户端的错误信息格式一致，便于前端解析。
2. **提升用户体验**：能够优雅地处理异常情况，避免直接将技术性错误信息暴露给用户，而是显示更加友好的提示信息。
3. **简化代码**：开发者不需要在每个可能抛出异常的方法中重复编写异常处理逻辑，减少冗余代码，使业务代码更加清晰简洁。
4. **增强安全性**：通过控制异常信息的输出，防止敏感信息泄露，增加系统的安全性。
5. **自适应的错误处理机制**：springboot 会根据请求头的 Accept 字段来决定错误的响应格式，这种机制的好处就是客户端设备自适应，可以提高用户的体验。

### 1. SpringMVC 的错误处理方案

如果使用了 SpringMVC 的错误处理方案，SpringBoot 的错误处理方案就不生效。

- 局部控制 @ExceptionHandler

在控制器当中编写一个方法并使用 @ExceptionHandler 注解进行标注，凡是这个控制器当中出现了对应的异常，则走这个方法来进行异常的处理，局部生效，其他控制器中发生异常不会执行以下内容。

```java
@ExceptionHandler(IllegalArgumentException.class)
public String handler(IllegalArgumentException e){
  return "错误信息：" + e.getMessage();
}
```

- 全局控制 @ControllerAdvice + @ExceptionHandler

也可以把以上局部生效的方法单独放到一个类当中，这个类使用 @ControllerAdvice 注解标注，任何控制器当中出现了对应的异常，则都走这个方法来进行异常的处理，全局生效。

```java
@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(IllegalArgumentException.class)
   @ResponseBody
   public String handler(IllegalArgumentException e){
      return "错误信息：" + e.getMessage();
   }
}
```

****
### 2. SpringBoot 的错误处理方案

如果 SpringMVC 没有对应的处理方案，会开启 SpringBoot 默认的错误处理方案，SpringBoot 默认的错误处理方案如下：

1. 如果客户端要的是json，则直接响应json格式的错误信息。
2. 如果客户端要的是html页面，则按照下面方案：

   + 第一步（精确错误码文件）：去`classpath:/templates/error/`目录下找`404.html`、`500.html`等 `精确错误码.html` 文件；如果找不到，则去静态资源目录下的/error目录下找；如果还是找不到，才会进入下一步。
   + 第二步（模糊错误码文件）：去`classpath:/templates/error/`目录下找`4xx.html`、`5xx.html`等 `模糊错误码.html` 文件；如果找不到，则去静态资源目录下的/error目录下找；如果还是找不到，才会进入下一步。
   + 第三步（通用错误页面）：去找`classpath:/templates/error.html`如果找不到则进入下一步。
   + 第四步（默认错误处理）：如果上述所有步骤都未能找到合适的错误页面，Spring Boot 会使用内置的默认错误处理机制，即 `/error` 端点。

****
### 3. 在错误页面获取错误信息

Spring Boot 默认会在模型 Model 中放置以下信息：

+ timestamp: 错误发生的时间戳
+ status: HTTP 状态码
+ error: 错误类型（如 "Not Found"）
+ exception: 异常类名
+ message: 错误消息
+ trace: 堆栈跟踪
+ path: 触发错误的请求路径

注意：springboot 默认未绑定 `exception``message``trace`，若想获取则需要开启以下三个配置（高版本可能会默认绑定）：

```properties
server.error.include-stacktrace=always
server.error.include-exception=true
server.error.include-message=always
```

****
## 9. 国际化

在 Spring Boot 中实现国际化（i18n）是一个常见的需求，它允许应用程序根据用户的语言和地区偏好显示不同的文本。

### 1. 实现国际化

第一步：创建资源文件

创建包含不同语言版本的消息文件，这些文件通常放在`src/main/resources`目录下，并且以`.properties`为扩展名。例如：

+ `messages.properties` (默认语言，如英语)
+ `messages_zh_CN.properties` (简体中文)
+ `messages_fr.properties` (法语)

第二步：在模板文件中取出消息，语法格式为：`#{welcome.message}`

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1 th:text="#{welcome.message}"></h1>
</body>
</html>
```

****
## 10. 定制 web 容器

### 1. web 服务器切换为 jetty

修改 `pom.xml` 文件：在 `pom.xml` 中，确保使用 `spring-boot-starter-web` 并排除 Tomcat，然后添加 Jetty 依赖

```xml
<!-- 排除 Tomcat -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- 添加 Jetty 依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

****
### 2. web 服务器优化

```properties
# 这个参数决定了 Tomcat 在接收请求时，如果在指定的时间内没有收到完整的请求数据，将会关闭连接。这个超时时间是从客户端发送请求开始计算的。
# 防止长时间占用资源：如果客户端发送请求后，长时间没有发送完所有数据，Tomcat 会在这个超时时间内关闭连接，从而释放资源，避免资源被长时间占用。
server.tomcat.connection-timeout=20000

# 设置 Tomcat 服务器处理请求的最大线程数为 200。
# 如果超过这个数量的请求同时到达，Tomcat 会将多余的请求放入一个等待队列中。
# 如果等待队列也满了（由 server.tomcat.accept-count 配置），新的请求将被拒绝，通常会返回一个“503 Service Unavailable”错误。
server.tomcat.max-threads=200

# 用来设置等待队列的最大容量
server.tomcat.accept-count=100

# 设置 Tomcat 服务器在空闲时至少保持 10 个线程处于活动状态，以便快速响应新的请求。
server.tomcat.min-spare-threads=10

# 允许 Tomcat 服务器在关闭后立即重新绑定相同的地址和端口，即使该端口还在 TIME_WAIT 状态
# 当一个网络连接关闭时，操作系统会将该连接的端口保持在 TIME_WAIT 状态一段时间（通常是 2-4 分钟），以确保所有未完成的数据包都能被正确处理。在这段时间内，该端口不能被其他进程绑定。
server.tomcat.address-reuse-enabled=true

# 设置 Tomcat 服务器绑定到所有可用的网络接口，使其可以从任何网络地址访问。
server.tomcat.bind-address=0.0.0.0

# 设置 Tomcat 服务器使用 HTTP/1.1 协议处理请求。
server.tomcat.protocol=HTTP/1.1

# 设置 Tomcat 服务器的会话(session)超时时间为 30 分钟。具体来说，如果用户在 30 分钟内没有与应用进行任何交互，其会话将被自动注销。
server.tomcat.session-timeout=30

# 设置 Tomcat 服务器的静态资源缓存时间为 3600 秒（即 1 小时），这意味着浏览器会在 1 小时内缓存这些静态资源，减少重复请求。
server.tomcat.resource-cache-period=3600

# 解决get请求乱码。对请求行url进行编码。
server.tomcat.uri-encoding=UTF-8

# 设置 Tomcat 服务器的基础目录为当前工作目录（. 表示当前目录）。这个配置指定了 Tomcat 服务器的工作目录，包括日志文件、临时文件和其他运行时生成的文件的存放位置。 生产环境中可能需要重新配置。
server.tomcat.basedir=.
```

****
# 六. SpringBoot 实用技术整合

## 1. 控制台的 logo 设置

- 配置文件方式：

通过修改配置文件关闭 logo：spring.main.banner-mode=off

- 打代码方式：

第一种：

```java
@SpringBootApplication
public class Springboot322WebServerApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Springboot322WebServerApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
```

第二种：流式编程

```java
new SpringApplicationBuilder()
                .sources(Springboot322WebServerApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
```

- 修改 logo 图标

在`src/main/resources`目录下存放一个`banner.txt`文件，文件名固定。

利用一些网站生成图标：

[https://www.bootschool.net/ascii](https://www.bootschool.net/ascii) （支持中文、英文）

[http://patorjk.com/software/taag/](http://patorjk.com/software/taag/) （只支持英文）

[https://www.degraeve.com/img2txt.php](https://www.degraeve.com/img2txt.php) （只支持图片）

获取图标粘贴到 `banner.txt` 文件中运行程序即可。

****
## 2. PageHelper 整合

官网地址：[https://pagehelper.github.io/](https://pagehelper.github.io/)

相关依赖：

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>2.1.0</version>
</dependency>
```

使用：

```java
@GetMapping("/list/{pageNo}")
public PageInfo<Vip> list(@PathVariable("pageNo") Integer pageNo) {
   // 1.设置当前页码和每页显示的记录条数
   PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
   // 2.获取数据（PageHelper会自动给SQL语句添加limit）
   List<Vip> vips = vipService.findAll();
   // 3.将分页数据封装到PageInfo
   PageInfo<Vip> vipPageInfo = new PageInfo<>(vips);
   return vipPageInfo;
}
```

****
## 3. web 层响应结果的封装

对于前后端分离的系统来说，为了降低沟通成本，有必要给前端系统开发人员返回统一格式的JSON数据，多数开发团队一般都会封装一个 [R](./Demo3-sprinboot_merge/src/main/java/com/cell/notes/web/result/R.java) 对象来解决统一响应格式的问题。

****
## 4. 事务管理

Service 层是典型的业务逻辑层，事务应该围绕一个完整业务操作，而 Service 调用多个 Mapper、多个 DAO 方法，事务可以统一控制Spring 的事务 AOP 也是基于 Service 的代理对象生成的，
所以 @Transactional 放这里才能生效

测试：[转账](./Demo3-sprinboot_merge/src/main/java/com/cell/notes/web/controller/AccountController.java)

****
## 5. SpringBoot 打 war 包

第一步：将打包方式设置为 war

```xml
<packaging>war</packaging>
```

第二步：排除内嵌 tomcat

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!--内嵌的tomcat服务器排除掉-->
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

第三步：添加 servlet api 依赖（引入tomcat，但scope设置为provided，这样这个tomcat服务器就不会打入war包了）

```xml
<!--额外添加一个tomcat服务器，实际上是为了添加servlet api。scope设置为provided表示这个不会被打入war包当中。-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <scope>provided</scope>
</dependency>
```

第四步：修改主类

```java
@MapperScan(basePackages = "...")
@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MyApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

}
```

第五步：使用 Maven 打包

在项目根目录执行：

```java
mvn clean package
```

或者通过 Maven 的快捷键，先 clean 再 package

第六步：部署 war 包到外部 Tomcat

启动 Tomcat 后访问页面：`http://localhost:8080/项目名`

****
## 6. Spring Boot 的日志处理

### 1. 概述

日志记录是一种关键的技术实践，用于捕捉软件运行时的信息、警告、错误以及其他重要数据。通过分析日志文件，开发人员、测试工程师和运维团队可以深入了解应用程序的行为，快速定位并解决出现的问题，同时对系统性能进行监控与优化。
虽然在开发初期或简单的应用场景下，使用 System.out.println("记录日志...") 这样的方式来输出信息非常直接且易于实现，但在生产环境中的大型或复杂应用中，这种方式存在诸多不足：

1. 性能问题：频繁地调用 System.out.println() 会占用额外的CPU资源，尤其是在高并发场景下，可能会影响应用程序的性能。 
2. 日志管理不便：控制台输出的日志不易于长期保存和检索，也不便于与其他工具集成以进行更高级的日志分析。 
3. 缺乏灵活性：通过 System.out.println() 输出的信息通常难以调整其格式、级别（如DEBUG, INFO, WARN, ERROR）或输出目标（例如文件、数据库、远程服务器等）。 
4. 安全性和隐私：在某些情况下，直接打印敏感信息可能会泄露用户的个人数据或公司的商业秘密。

因此，在实际的线上项目中，推荐采用专业的日志框架，它们提供了更为强大和灵活的日志处理能力：

- 支持多种日志级别，允许开发者根据需要过滤日志信息。 
- 可配置的日志输出格式，使日志更加规范易读。 
- 支持将日志写入不同的目的地，如文件、数据库、网络服务等。 
- 提供异步日志功能，减少日志记录对应用性能的影响。 
- 能够与各种监控系统集成，支持实时告警和问题追踪。

****
### 2. Java 中的日志框架

#### 2.1 抽象的日志框架

编译阶段可以使用这些抽象的日志框架，并能够正常编译通过，但运行阶段必须提供具体的日志框架，这样做的目的是让具体的日志框架可灵活切换。

- SLF4J (Simple Logging Facade for Java)
   - 简介：SLF4J不是一个具体的日志实现，而是一个简单的抽象层，允许开发者在应用程序中使用统一的日志API，而实际的日志实现可以在部署时决定。
   - 特点：提供了统一的日志记录接口，使得日志框架的选择更加灵活，易于切换不同的日志实现，减少了应用程序对特定日志框架的依赖。 
   - 运行时可绑定的具体日志框架：Log4j、Log4j2、Logback、JUL。
- Commons Logging (Jakarta Commons Logging)
  - 简介：Commons Logging是Apache Jakarta项目的一部分。也是一种日志层面的抽象。
  - 特点：允许在运行时动态选择日志实现，但因类加载机制的问题，有时会导致难以预料的行为，因此不如SLF4J受欢迎。
  - 运行时可绑定的具体日志框架：Log4j、Log4j2、Logback、JUL。

****
#### 2.2 具体的日志框架

- Log4j（太老，没人用了）
   - 简介：Log4j是Apache软件基金会的一个开源项目，它提供了强大的日志记录功能，支持多种日志级别（如DEBUG, INFO, WARN, ERROR, FATAL）、灵活的日志输出格式和目的地配置。 
   - 特点：易于配置和使用，支持日志滚动策略，适用于中小规模的应用。
- Log4j 2
   - 简介：Log4j 2是Log4j的下一代版本，旨在解决原版的一些性能瓶颈和设计缺陷。
   - 特点：性能更优，支持插件化配置，增强了安全性，支持异步日志记录，减少了线程间的竞争，提高了日志记录的速度。
- Logback
   - 简介：Logback是Log4j的创始人Ceki Gülcü开发的另一个日志框架，设计之初就考虑到了性能和可靠性。 
   - 特点：与SLF4J无缝集成，提供了比Log4j更好的性能表现，支持自动重载配置文件，具有更简洁的API和更少的bug。
- JUL (Java Util Logging)
   - 简介：JUL是Java SE平台自带的日志框架，从Java 1.4版本开始提供。 
   - 特点：不需要额外的库文件，配置相对简单，适合小型应用。然而，相对于其他流行的日志框架，JUL的功能较为有限，配置不够灵活。

这些日志框架各有优势，选择哪一种取决于项目的具体需求、团队的偏好以及现有的技术栈。例如：对于需要高性能和灵活配置的应用，Logback或Log4j 2可能是更好的选择；而对于希望减少对外部库依赖的小型项目，JUL可能更为合适。
而SpringBoot框架默认使用的日志框架是：Logback。

****
### 3. 日志级别

在Java的日志框架中，日志级别（Log Level）是用来指定日志记录的严重程度的一个标准。不同的日志级别代表了不同的重要性和紧急程度，这有助于开发者根据实际需要来过滤和关注特定类型的日志信息。常见的日志级别从低到高通常包括以下几种：

1. TRACE：这是最低级别的日志信息，用于记录最详细的信息，通常是在调试应用时使用。 
2. DEBUG：用于记录程序运行时的详细信息，比如变量的值、进入或退出某个方法等，主要用于开发阶段的调试。 
3. INFO：记录应用程序的一般信息，如系统启动、服务初始化完成等，表示程序运行正常。 
4. WARN：警告信息，表示可能存在问题或者异常的情况，但是还不至于导致应用停止工作。 
5. ERROR：错误信息，表示发生了一个错误事件，该错误可能会导致某些功能无法正常工作。

通过设置不同的日志级别，可以在生产环境中控制输出的日志量，从而减少对性能的影响。例如：

- 在生产环境中，一般会将日志级别设置为INFO或更高级别，以避免输出过多的调试信息；
- 而在开发或测试环境中，则可以将日志级别降低至DEBUG甚至TRACE，以便于进行详细的错误追踪和调试。

级别越低，打印的日志信息越多，SpringBoot默认的日志级别是INFO，生产环境下要严格控制，因为日志级别越低，打印的信息越多，磁盘空间受不了。而SpringBoot默认将日志打印到控制台（通过配置可以改变打印到日志文件）

****
### 4. 更改日志级别

测试日志级别：使用 Lombok 的 @Slf4j 注解自动维护一个 log 对象，SpringBoot 默认的日志隔离级别是INFO，因此 INFO，WARN，ERROR 三个级别的日志都会打印

```java
@Slf4j // 这是 lombok 的注解，用来维护一个 log 日志对象
@MapperScan("com.cell.notes.web.mapper")
@SpringBootApplication
public class MySpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
        log.trace("trace级别日志");
        log.debug("debug级别日志");
        log.info("info级别日志");
        log.warn("warn级别日志");
        log.error("error级别日志");
    }
}
```

可以通过配置文件开启剩下的日志级别：logging.level.root=DEBUG，换成 TRACE 则是最低级别，换成 ERROR 则只会打印 ERROR 相关内容

****
### 5. 丰富启动日志

- debug=true：启用Spring Boot的调试模式，增加启动日志的详细程度并显示自动配置报告。
- trace=true：启用Spring Boot的启动跟踪，生成包含启动信息的详细跟踪文件，内容是包含debug=true输出的内容的。

这两个配置项主要影响Spring Boot框架本身的启动日志和跟踪信息，不影响在应用程序中编写的日志记录，并且在SpringBoot框架启动完成之后，不再有任何作用。
而上面设置的日志等级是一直起作用的。

****
### 6. 日志的粗粒细度

```properties
# 设置根日志级别为 INFO（整个项目都使用INFO日志级别，这是粗粒度的配置。）
logging.level.root=INFO

# 为特定的包设置日志级别为 DEBUG
logging.level.com.cell=DEBUG

# 为特定的类设置日志级别为 TRACE
logging.level.com.cell....MyService=TRACE
```

默认的 INFO 级别是不会显示 SQL 的，但又不想让低级别的日志在全局生效，就可以在 mapper 包下让它生效，这样就可以在项目中打印 sql 语句:

```properties
logging.level.root=INFO
logging.level.com.cell.notes.web.mapper=DEBUG
```

****
### 7. 日志输出到文件

```properties
logging.file.path=./log/
```

以上配置表示将日志文件输出到当前工程根目录下的 log 目录当中，文件名默认叫做 spring.log，并且文件名不可设置。

```properties
logging.file.name=my.log
```

以上的配置表示在工程的根目录下直接生成 my.log 文件，也可以设置路径：logging.file.name=./log/custom.log

注意：以上两个配置同时存在时，只有 `logging.file.name` 配置生效，本质上两个配置不可共存。

****
### 8. 滚动日志

滚动日志是一种日志管理机制，用于防止日志文件无限增长，通过将日志文件分割成多个文件，每个文件只包含一定时间段或大小的日志记录。

```properties
# 日志文件达到多大时进行归档
logging.logback.rollingpolicy.max-file-size=100MB

# 所有的归档日志文件总共达到多大时进行删除，默认是0B表示不删除
logging.logback.rollingpolicy.total-size-cap=50GB

# 归档日志文件最多保留几天
logging.logback.rollingpolicy.max-history=60

# 启动项目时是否要清理归档日志文件
logging.logback.rollingpolicy.clean-history-on-start=false

# 归档日志文件名的格式
logging.logback.rollingpolicy.file-name-pattern=${LOG_FILE}.%d{yyyy-MM-dd}.%i.gz
```

以上配置只适用与 springboot 默认的日志 logback，只有它可以在配置文件中这样配置

****
### 9. 日志框架切换

在实际开发中也可能会切换日志框架，这时候就需要先排除掉，再添加：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <!--排除掉默认的日志依赖-->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<!--使用Log4j2日志依赖-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```

****



