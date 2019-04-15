/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: LabelController.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.base.controller
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-26 17:16
 * @version: V1.0
 */
package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LabelController
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-26 17:16
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Result login() {

        String token = jwtUtil.createJWT("1", "zkd", "admin");
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", "admin");
        return new Result(true, StatusCode.OK, "登录成功....",map);

    }


    @DeleteMapping("/token/{id}")
    public Result deleteById(@PathVariable String id) {

        Claims claims = (Claims) request.getAttribute("admin_claims");
        if (claims == null) {
            return new Result(false,StatusCode.ERROR,"shiabi ");
        }

        return new Result(true,StatusCode.OK,"删除成功");
    }



    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功",labelService.findAll());
    }


    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return new Result(true, StatusCode.OK, "save 成功");
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId,@RequestBody Label label) {
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "update成功");
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "delete 成功");
    }
}
