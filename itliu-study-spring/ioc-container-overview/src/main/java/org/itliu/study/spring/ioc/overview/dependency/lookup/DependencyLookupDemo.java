package org.itliu.study.spring.ioc.overview.dependency.lookup;

import org.itliu.study.spring.ioc.overview.annotation.Super;
import org.itliu.study.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @desc 依赖查找示例
 *
 * 1.通过名称、类型来查找
 *
 * @auther itliu
 * @date 2020/7/30
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        /**
         * 1.BeanDefinition从哪里读
         * 2.启动Spring上下文
         */
        BeanFactory beanFactory =
                new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        //根据名称获取
        lookupInRealTime(beanFactory);
        lookupByType(beanFactory);
        lookupInLazy(beanFactory);
        lookupCollectionByType(beanFactory);
        lookupByAnnotationType(beanFactory);

    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("集合按注解查找：" + beans);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("集合按类型查找：" + beans);
        }
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> user = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        System.out.println("延时查找：" + user.getObject());
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时类型查找：" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);
    }
}
