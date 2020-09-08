package org.itliu.study.spring.ioc.overview.repository;

import lombok.Data;
import lombok.ToString;
import org.itliu.study.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @desc
 * @auther itliu
 * @date 2020/8/3
 */
@Data
public class UserRepository {
    Collection<User> users;
    BeanFactory beanFactory;
    ObjectFactory<ApplicationContext> objectFactory;
}
