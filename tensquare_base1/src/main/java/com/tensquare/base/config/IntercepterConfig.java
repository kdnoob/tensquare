/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: IntercepterConfig.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.base.config
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-29 15:08
 * @version: V1.0
 */
package com.tensquare.base.config;

import com.tensquare.base.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName: IntercepterConfig
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-29 15:08
 */
@Configuration
public class IntercepterConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
