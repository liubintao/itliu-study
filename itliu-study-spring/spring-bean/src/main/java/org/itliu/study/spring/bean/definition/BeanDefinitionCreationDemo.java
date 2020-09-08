package org.itliu.study.spring.bean.definition;

import org.itliu.study.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @desc
 * @auther itliu
 * @date 2020/8/3
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        builder.addPropertyValue("id", 18);
        builder.addPropertyValue("name", "itliu");

        System.out.println(builder.getBeanDefinition());

        //2.通过GenericBeanDefinition创建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id", 17));
        propertyValues.addPropertyValue(new PropertyValue("name", "laowang"));
        genericBeanDefinition.setPropertyValues(propertyValues);

        System.out.println(genericBeanDefinition);
    }
}
