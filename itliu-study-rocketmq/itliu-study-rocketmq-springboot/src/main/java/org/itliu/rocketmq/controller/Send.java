package org.itliu.rocketmq.controller;

import org.itliu.rocketmq.service.MQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc
 * @auther itliu
 * @date 2020/9/7
 */
@RestController
public class Send {

    @Autowired
    MQService service;

    @RequestMapping("/send")
    public void send() {
        service.sendMsg("aaa");
    }
}
