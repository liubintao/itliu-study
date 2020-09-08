package org.itliu.study.spring.ioc.overview.iocContainer;

import org.itliu.study.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @desc
 * @auther itliu
 * @date 2020/8/3
 */
public class BeanFactoryAsIOCContainerDemo {
    public static void main(String[] args) {
        //可不可以创建BeanFactory呢
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //ClassPathXmlApplicationContext中是通过XmlBeanDefinitionReader去加载配置的
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String location = "META-INF/dependency-lookup-context.xml";
        //加载配置
        int beanDefinitions = reader.loadBeanDefinitions(location);
        System.out.println(beanDefinitions);

        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beans = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("集合按类型查找：" + beans);
        }
    }
}
