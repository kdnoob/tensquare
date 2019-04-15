/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: Customer.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.rabbitmq
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-28 12:53
 * @version: V1.0
 */
package com.tensquare.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: Customer
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-28 12:53
 */
@Component
@RabbitListener(queues = "sms")
public class Customer {

    @RabbitHandler
    public void showSms(Map<String, String> map) {
        System.out.println(map.get("code"));
        System.out.println(map.get("mobile"));
    }

}
