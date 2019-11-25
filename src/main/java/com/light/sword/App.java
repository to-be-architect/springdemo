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
