package org.itliu.study.spring.ioc.overview.dependency.inject;

import org.itliu.study.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @desc 依赖注入示例
 * <p>
 * 1.通过名称、类型来查找
 * @auther itliu
 * @date 2020/7/30
 */
public class DependencyInjectDemo {
    public static void main(String[] args) {
        /**
         * 1.BeanDefinition从哪里读
         * 2.启动Spring上下文
         */
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("META-INF/dependency-inject-context.xml");
//        BeanFactory beanFactory =
//                new ClassPathXmlApplicationContext("META-INF/dependency-inject-context.xml");
        // 依赖来源一：自定义 Bean
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
//        System.out.println(userRepository);

        // 依赖来源二：依赖注入（內建依赖）
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == applicationContext);

        ObjectFactory userFactory = userRepository.getObjectFactory();
        System.out.println(userRepository.getObjectFactory());
        System.out.println(userFactory.getObject() == applicationContext);


        //依赖查找(此处会发生错误)
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        // 依赖来源三：依赖注入（內建Bean）
        Environment environment = applicationContext.getBean(Environment.class);
        System.out.println(environment);
    }


}
