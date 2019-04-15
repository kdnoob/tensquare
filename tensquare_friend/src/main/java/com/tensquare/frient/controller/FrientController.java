package com.tensquare.frient.controller;

/**
 * 添加好友
 *
 * @param friendid 对方用户ID
 * @param type 1：喜欢 0：不喜欢
 * @return
 */

import com.tensquare.frient.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/friend")
public class FrientController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims == null) {
            return new Result(false, StatusCode.ACCESSERROR, "无权访问");
        }
        //如果是喜欢
        if (type.equals("1")) {
            if (friendService.addFriend(claims.getId(), friendid) == 0) {
                return new Result(false, StatusCode.REPERROR, "已经添加此好友");
            }
        } else {
            //不喜欢
            friendService.addNoFriend(claims.getId(), friendid);//向不喜欢列表中添加记录
        }
        return new Result(true, StatusCode.OK, "操作成功");
    }
}

