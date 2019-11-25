# [ 成为架构师系列 ] 3. Spring 框架从入门到精通: Ioc 概念

---

# 1. Spring 框架是什么?


Spring是一个开源框架，Spring是于2003 年兴起的一个轻量级的Java 开发框架，由Rod Johnson 在其著作《Expert One-On-One J2EE Development and Design》中阐述的部分理念和原型衍生而来。


### 典型应用场景

Spring 可以应用到许多场景，从最简单的标准 Java SE 程序到企业级应用程序都能使用 Spring 来构建。以下介绍几个比较流行的应用场景：

*   典型 Web 应用程序应用场景：

    ![ Spring 概述](https://upload-images.jianshu.io/upload_images/1233356-e98b0952baf41a2d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

*   远程访问应用场景：

![ Spring 概述](https://upload-images.jianshu.io/upload_images/1233356-d97b2befa1389594.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

*   EJB 应用场景：

![ Spring 概述](https://upload-images.jianshu.io/upload_images/1233356-1be3433bf4076e79.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



# 2. Spring 解决了什么问题?

为了解决企业应用开发的复杂性
管理对象的生命周期
解耦对象之间的依赖

# 3. Spring 的核心思想: Ioc 与 AOP

Spring 基本概念:

### 应用程序：

能完成我们所需要功能的成品，比如购物网站、OA 系统。

### 框架：
能完成一定功能的半成品，比如我们可以使用框架进行购物网站开发；框架做一部分功能，我们自己做一部分功能，辅助高效工作。而且框架规定了你在开发应用程序时的整体架构，提供了一些基础功能，还规定了类和对象的如何创建、如何协作等，从而简化我们的代码编写，让我们专注于业务逻辑开发。

### 非侵入式设计：
从框架角度可以这样理解，无需继承框架提供的类，这种设计就可以看作是非侵入式设计，如果继承了这些框架类，就是侵入设计，如果以后想更换框架，之前写过的代码几乎无法重用，如果非侵入式设计则之前写过的代码仍然可以继续使用。

### 轻量级与重量级：
轻量级是相对于重量级而言的，轻量级一般就是非入侵性的、所依赖的东西非常少、资源占用非常少、部署简单等等，其实就是比较容易使用，而重量级正好相反。

### POJO ： 
POJO （ Plain Ordinary Java Object ）简单的 Java 对象。它可以包含业务逻辑或持久化逻辑，但不担当任何特殊角色且不继承或不实现任何其它 Java 框架的类或接口。

### 容器：
在日常生活中容器就是一种盛放东西的器具，从程序设计角度看就是装对象的的对象，因为存在放入、拿出等操作，所以容器还要管理对象的生命周期。

### 控制反转：
即 Inversion of Control ，缩写为 IoC ，控制反转还有一个名字叫做依赖注入（ Dependency Injection ），就是由容器控制程序之间的关系，而非传统实现中，由程序代码直接操控。


### Bean ：

一般指容器管理对象，在 Spring 中指 Spring IoC 容器管理对象。

#4. 分层架构

Spring框架是分层架构的,它包含了一系列的功能要素:

![](https://upload-images.jianshu.io/upload_images/1233356-56b93279e1ed92b3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


该框架的主要优势之一就是其分层架构，分层架构允许使用者选择使用哪一个组件，同时为 J2EE 应用程序开发提供集成的框架。Spring使用基本的JavaBean来完成以前只可能由EJB完成的事情。然而，Spring的用途不仅限于服务器端的开发。从简单性、可测试性和松耦合的角度而言，任何Java应用都可以从Spring中受益。

Spring的核心是控制反转（IoC）和面向切面（AOP）。

简单来说，Spring是一个分层的JavaSE/EE full-stack(一站式)轻量级开源框架。

轻量级：与EJB对比，依赖资源少，消耗的资源少。
分层：full-stack（一站式），每一个层都提供解决方案。
web层：struts，spring-MVC
service层：spring
dao层：hibernate，mybatis，jdbcTemplate --> spring-data


# 5. 依赖注入 Ioc 的例子

创建 Maven 项目:
![](https://upload-images.jianshu.io/upload_images/1233356-b36d6554c9eb19a0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

示例代码:
```
package com.light.sword.service;

import com.light.sword.dao.HelloDao;
import com.light.sword.dao.HelloDaoImpl;

/**
 * @author: Jack
 * 2019-11-25 23:09
 */
public class HelloServiceImpl implements HelloService {
    // 依赖注入的思想,就是把这句代码放到容器中单独管理,而不是写死在代码里.
    // private HelloDao helloDao = new HelloDaoImpl();
    private HelloDao helloDao;

    public HelloDao getHelloDao() {
        return helloDao;
    }

    public void setHelloDao(HelloDao helloDao) {
        this.helloDao = helloDao;
    }

    @Override
    public String service1(String name) {
        return helloDao.say(name);
    }

    @Override
    public String service2(String name) {
        return helloDao.say("$" + name);
    }

}

```

消费者调用服务代码:
```
package com.light.sword;

import com.light.sword.dao.HelloDaoImpl;
import com.light.sword.service.HelloService;
import com.light.sword.service.HelloServiceImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        // 依赖注入的思想,就是把这句代码放到容器中单独管理,而不是写死在代码里.
        ((HelloServiceImpl) helloService).setHelloDao(new HelloDaoImpl());

        String s1 = helloService.service1("Jack");
        String s2 = helloService.service2("Jobs");
        System.out.println(s1);
        System.out.println(s2);
    }
}

```
可以看到, 我们在代码行`((HelloServiceImpl) helloService).setHelloDao(new HelloDaoImpl());` 里维护了 `HelloDaoImpl` 对象的依赖.

当对象变得庞大起来, 这种对象之间的依赖关系维护/修改起来会是一个庞大的工程, 对象之间的耦合关系像面条一样揉在一起. 能否在这个千丝万缕的关系中拎出一条线来?

这就是 Spring Ioc 容器做的事情.
具体操作步骤如下:

#### 1.配置管理 bean 关系的 xml 文件
beans.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="helloDao" class="com.light.sword.dao.HelloDaoImpl"></bean>
    <bean id="helloService" class="com.light.sword.service.HelloServiceImpl">
        <property name="helloDao" ref="helloDao"></property>
    </bean>
</beans>

```

#### 2.代码中初始化ApplicationContext, 从应用上下文中获取 Bean 对象

```
    private static void withSpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        String s1 = helloService.service1("Jack");
        String s2 = helloService.service2("Jobs");
        System.out.println(s1);
        System.out.println(s2);
    }
```

#### ApplicationContext 接口说明

ApplicationContext，它是BeanFactory的子接口，也称为应用上下文，ApplicationContext接口除了包含BeanFactory的所有功能以外，还添加了对国际化、资源访问、事件传播等内容的支持。

```
/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context;

import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.lang.Nullable;

/**
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 *
 * <p>An ApplicationContext provides:
 * <ul>
 * <li>Bean factory methods for accessing application components.
 * Inherited from {@link org.springframework.beans.factory.ListableBeanFactory}.
 * <li>The ability to load file resources in a generic fashion.
 * Inherited from the {@link org.springframework.core.io.ResourceLoader} interface.
 * <li>The ability to publish events to registered listeners.
 * Inherited from the {@link ApplicationEventPublisher} interface.
 * <li>The ability to resolve messages, supporting internationalization.
 * Inherited from the {@link MessageSource} interface.
 * <li>Inheritance from a parent context. Definitions in a descendant context
 * will always take priority. This means, for example, that a single parent
 * context can be used by an entire web application, while each servlet has
 * its own child context that is independent of that of any other servlet.
 * </ul>
 *
 * <p>In addition to standard {@link org.springframework.beans.factory.BeanFactory}
 * lifecycle capabilities, ApplicationContext implementations detect and invoke
 * {@link ApplicationContextAware} beans as well as {@link ResourceLoaderAware},
 * {@link ApplicationEventPublisherAware} and {@link MessageSourceAware} beans.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see ConfigurableApplicationContext
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.core.io.ResourceLoader
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
		MessageSource, ApplicationEventPublisher, ResourcePatternResolver {

	/**
	 * Return the unique id of this application context.
	 * @return the unique id of the context, or {@code null} if none
	 */
	@Nullable
	String getId();

	/**
	 * Return a name for the deployed application that this context belongs to.
	 * @return a name for the deployed application, or the empty String by default
	 */
	String getApplicationName();

	/**
	 * Return a friendly name for this context.
	 * @return a display name for this context (never {@code null})
	 */
	String getDisplayName();

	/**
	 * Return the timestamp when this context was first loaded.
	 * @return the timestamp (ms) when this context was first loaded
	 */
	long getStartupDate();

	/**
	 * Return the parent context, or {@code null} if there is no parent
	 * and this is the root of the context hierarchy.
	 * @return the parent context, or {@code null} if there is no parent
	 */
	@Nullable
	ApplicationContext getParent();

	/**
	 * Expose AutowireCapableBeanFactory functionality for this context.
	 * <p>This is not typically used by application code, except for the purpose of
	 * initializing bean instances that live outside of the application context,
	 * applying the Spring bean lifecycle (fully or partly) to them.
	 * <p>Alternatively, the internal BeanFactory exposed by the
	 * {@link ConfigurableApplicationContext} interface offers access to the
	 * {@link AutowireCapableBeanFactory} interface too. The present method mainly
	 * serves as a convenient, specific facility on the ApplicationContext interface.
	 * <p><b>NOTE: As of 4.2, this method will consistently throw IllegalStateException
	 * after the application context has been closed.</b> In current Spring Framework
	 * versions, only refreshable application contexts behave that way; as of 4.2,
	 * all application context implementations will be required to comply.
	 * @return the AutowireCapableBeanFactory for this context
	 * @throws IllegalStateException if the context does not support the
	 * {@link AutowireCapableBeanFactory} interface, or does not hold an
	 * autowire-capable bean factory yet (e.g. if {@code refresh()} has
	 * never been called), or if the context has been closed already
	 * @see ConfigurableApplicationContext#refresh()
	 * @see ConfigurableApplicationContext#getBeanFactory()
	 */
	AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException;

}

```
实现类有:

![](https://upload-images.jianshu.io/upload_images/1233356-26b01a0d62971b1b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


创建ApplicationContext接口实例通常有三种方法：

1、 通过ClassPathXmlApplicationContext创建

2、 通过FileSystemXmlApplicatonContext创建

3、 通过Web服务器实例化ApplicationContext容器

 
#### ClassPathXmlApplicationContext.java

```
/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Standalone XML application context, taking the context definition files
 * from the class path, interpreting plain paths as class path resource names
 * that include the package path (e.g. "mypackage/myresource.txt"). Useful for
 * test harnesses as well as for application contexts embedded within JARs.
 *
 * <p>The config location defaults can be overridden via {@link #getConfigLocations},
 * Config locations can either denote concrete files like "/myfiles/context.xml"
 * or Ant-style patterns like "/myfiles/*-context.xml" (see the
 * {@link org.springframework.util.AntPathMatcher} javadoc for pattern details).
 *
 * <p>Note: In case of multiple config locations, later bean definitions will
 * override ones defined in earlier loaded files. This can be leveraged to
 * deliberately override certain bean definitions via an extra XML file.
 *
 * <p><b>This is a simple, one-stop shop convenience ApplicationContext.
 * Consider using the {@link GenericApplicationContext} class in combination
 * with an {@link org.springframework.beans.factory.xml.XmlBeanDefinitionReader}
 * for more flexible context setup.</b>
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see #getResource
 * @see #getResourceByPath
 * @see GenericApplicationContext
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

	@Nullable
	private Resource[] configResources;


	/**
	 * Create a new ClassPathXmlApplicationContext for bean-style configuration.
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public ClassPathXmlApplicationContext() {
	}

	/**
	 * Create a new ClassPathXmlApplicationContext for bean-style configuration.
	 * @param parent the parent context
	 * @see #setConfigLocation
	 * @see #setConfigLocations
	 * @see #afterPropertiesSet()
	 */
	public ClassPathXmlApplicationContext(ApplicationContext parent) {
		super(parent);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext, loading the definitions
	 * from the given XML file and automatically refreshing the context.
	 * @param configLocation resource location
	 * @throws BeansException if context creation failed
	 */
	public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
		this(new String[] {configLocation}, true, null);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext, loading the definitions
	 * from the given XML files and automatically refreshing the context.
	 * @param configLocations array of resource locations
	 * @throws BeansException if context creation failed
	 */
	public ClassPathXmlApplicationContext(String... configLocations) throws BeansException {
		this(configLocations, true, null);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files and automatically
	 * refreshing the context.
	 * @param configLocations array of resource locations
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 */
	public ClassPathXmlApplicationContext(String[] configLocations, @Nullable ApplicationContext parent)
			throws BeansException {

		this(configLocations, true, parent);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext, loading the definitions
	 * from the given XML files.
	 * @param configLocations array of resource locations
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh) throws BeansException {
		this(configLocations, refresh, null);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files.
	 * @param configLocations array of resource locations
	 * @param refresh whether to automatically refresh the context,
	 * loading all bean definitions and creating all singletons.
	 * Alternatively, call refresh manually after further configuring the context.
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 * @see #refresh()
	 */
	public ClassPathXmlApplicationContext(
			String[] configLocations, boolean refresh, @Nullable ApplicationContext parent)
			throws BeansException {

		super(parent);
		setConfigLocations(configLocations);
		if (refresh) {
			refresh();
		}
	}


	/**
	 * Create a new ClassPathXmlApplicationContext, loading the definitions
	 * from the given XML file and automatically refreshing the context.
	 * <p>This is a convenience method to load class path resources relative to a
	 * given Class. For full flexibility, consider using a GenericApplicationContext
	 * with an XmlBeanDefinitionReader and a ClassPathResource argument.
	 * @param path relative (or absolute) path within the class path
	 * @param clazz the class to load resources with (basis for the given paths)
	 * @throws BeansException if context creation failed
	 * @see org.springframework.core.io.ClassPathResource#ClassPathResource(String, Class)
	 * @see org.springframework.context.support.GenericApplicationContext
	 * @see org.springframework.beans.factory.xml.XmlBeanDefinitionReader
	 */
	public ClassPathXmlApplicationContext(String path, Class<?> clazz) throws BeansException {
		this(new String[] {path}, clazz);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext, loading the definitions
	 * from the given XML files and automatically refreshing the context.
	 * @param paths array of relative (or absolute) paths within the class path
	 * @param clazz the class to load resources with (basis for the given paths)
	 * @throws BeansException if context creation failed
	 * @see org.springframework.core.io.ClassPathResource#ClassPathResource(String, Class)
	 * @see org.springframework.context.support.GenericApplicationContext
	 * @see org.springframework.beans.factory.xml.XmlBeanDefinitionReader
	 */
	public ClassPathXmlApplicationContext(String[] paths, Class<?> clazz) throws BeansException {
		this(paths, clazz, null);
	}

	/**
	 * Create a new ClassPathXmlApplicationContext with the given parent,
	 * loading the definitions from the given XML files and automatically
	 * refreshing the context.
	 * @param paths array of relative (or absolute) paths within the class path
	 * @param clazz the class to load resources with (basis for the given paths)
	 * @param parent the parent context
	 * @throws BeansException if context creation failed
	 * @see org.springframework.core.io.ClassPathResource#ClassPathResource(String, Class)
	 * @see org.springframework.context.support.GenericApplicationContext
	 * @see org.springframework.beans.factory.xml.XmlBeanDefinitionReader
	 */
	public ClassPathXmlApplicationContext(String[] paths, Class<?> clazz, @Nullable ApplicationContext parent)
			throws BeansException {

		super(parent);
		Assert.notNull(paths, "Path array must not be null");
		Assert.notNull(clazz, "Class argument must not be null");
		this.configResources = new Resource[paths.length];
		for (int i = 0; i < paths.length; i++) {
			this.configResources[i] = new ClassPathResource(paths[i], clazz);
		}
		refresh();
	}


	@Override
	@Nullable
	protected Resource[] getConfigResources() {
		return this.configResources;
	}

}

```

支持 concrete files like "/myfiles/context.xml",  Ant-style patterns 等文件匹配.
```
 * <p>The config location defaults can be overridden via {@link #getConfigLocations},
 * Config locations can either denote concrete files like "/myfiles/context.xml"
 * or Ant-style patterns like "/myfiles/*-context.xml" (see the
 * {@link org.springframework.util.AntPathMatcher} javadoc for pattern details).

```


如果文件路径有多个, multiple config locations, 后面的 bean 定义覆盖前面的 bean 定义:

```
 * <p>Note: In case of multiple config locations, later bean definitions will
 * override ones defined in earlier loaded files. This can be leveraged to
 * deliberately override certain bean definitions via an extra XML file.
```

在Spring中实现IoC容器的方法是依赖注入，依赖注入的作用是在使用Spring框架创建对象时动态地将其所依赖的对象（例如属性值）注入Bean组件中，Spring框架的依赖注入通常有两种实现方式，一种是使用构造方法注入，另一种是使用属性的setter方法注入。

```
package com.light.sword;

import com.light.sword.dao.HelloDaoImpl;
import com.light.sword.service.HelloService;
import com.light.sword.service.HelloServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        withoutSpring();
        withSpring();
    }

    private static void withoutSpring() {
        HelloService helloService = new HelloServiceImpl();

//    依赖注入的思想,就是把这句代码
        ((HelloServiceImpl) helloService).setHelloDao(new HelloDaoImpl());

//    放到容器中单独管理,而不是写死在代码里.
//    <bean id="helloDao" class="com.light.sword.dao.HelloDaoImpl"></bean>
//    <bean id="helloService" class="com.light.sword.service.HelloServiceImpl">
//        <property name="helloDao" ref="helloDao"></property>
//    </bean>

        String s1 = helloService.service1("Jack");
        String s2 = helloService.service2("Jobs");
        System.out.println(s1);
        System.out.println(s2);
    }

    private static void withSpring() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        String s1 = helloService.service1("Jack");
        String s2 = helloService.service2("Jobs");
        System.out.println(s1);
        System.out.println(s2);
    }

}

```

其中, beans.xml 这段配置
```
    <bean id="helloDao" class="com.light.sword.dao.HelloDaoImpl"></bean>
    <bean id="helloService" class="com.light.sword.service.HelloServiceImpl">
        <property name="helloDao" ref="helloDao"></property>
    </bean>
```
就相当于

```
((HelloServiceImpl) helloService).setHelloDao(new HelloDaoImpl());
```

Spring 会根据该配置,在运行时使用setter, getter 方法注入:

`<bean id="helloDao" class="com.light.sword.dao.HelloDaoImpl"></bean>`

在下一篇文章中,我们来分析 Spring 容器背后的实现原理,启动过程,容器中Bean的管理和生命周期.

# 示例工程源码

[https://github.com/to-be-architect/springdemo](https://github.com/to-be-architect/springdemo)


# 参考资料

[https://www.cnblogs.com/zyx110/p/11022891.html](https://www.cnblogs.com/zyx110/p/11022891.html)

