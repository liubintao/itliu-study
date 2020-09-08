package org.itliu.study.spring.ioc.overview.domain;

import lombok.Data;
import lombok.ToString;
import org.itliu.study.spring.ioc.overview.annotation.Super;

/**
 * @desc
 * @auther itliu
 * @date 2020/7/30
 */
@Super
@Data
@ToString(callSuper = true)
public class SuperUser extends User {
    String address;
}
