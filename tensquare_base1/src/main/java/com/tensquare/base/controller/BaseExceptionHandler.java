/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: BaseExceptionHandler.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.base.controller
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-27 09:37
 * @version: V1.0
 */
package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: BaseExceptionHandler
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-27 09:37
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e) {
//        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
