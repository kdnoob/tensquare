/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: JwtInterceptor.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.base.interceptor
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-29 15:00
 * @version: V1.0
 */
package com.tensquare.base.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: JwtInterceptor
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-29 15:00
 */
@Configuration
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println( "  经过 了了拦截器 ....... ");
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {
                    if ("admin".equals(claims.get("roles"))) {
                        request.setAttribute("admin_claims",claims);
                    }
                    if ("user".equals(claims.get("roles"))) {
                        request.setAttribute("user_claims",claims);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("令牌不正确！！");
            }
        }

        return true;
    }
}
