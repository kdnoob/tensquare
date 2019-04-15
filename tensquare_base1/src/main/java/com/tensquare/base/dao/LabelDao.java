/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: LabelDao.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.base.dao
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-26 17:34
 * @version: V1.0
 */
package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName: LabelDao
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-26 17:34
 */
public interface LabelDao extends JpaRepository<Label, String> ,JpaSpecificationExecutor<Label>{


}
