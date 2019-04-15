/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: DemoController.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.rabbitmq
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-28 12:48
 * @version: V1.0
 */
package com.tensquare.rabbitmq;

import entity.Result;
import entity.StatusCode;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: DemoController
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-28 12:48
 */
@RestController
@CrossOrigin
public class DemoController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostMapping("/sendsms/{mobile}")
    public Result sendsms(@PathVariable String mobile) {

        String code = RandomStringUtils.randomNumeric(6);

        System.out.println(mobile);
        System.out.println(code);

        redisTemplate.opsForValue().set("smscode" + mobile, code + "", 5, TimeUnit.MINUTES);

        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", code);

        rabbitTemplate.convertAndSend("sms", map);

        return new Result(true, StatusCode.OK, "发送成功");
    }


    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public Result add(@RequestBody Map map, @PathVariable String code) {

        String checkCode = redisTemplate.opsForValue().get("smscode" + map.get("mobile")).toString();

        if (code == null) {
            return new Result(false, StatusCode.ERROR, "qign dianji ");
        }

        if( !checkCode.equals(code)){
            return new Result(false, StatusCode.ERROR, "qign dianji buxiangdeng ");
        }
        return new Result(true, StatusCode.OK, "okokokokokok ");


    }

}
