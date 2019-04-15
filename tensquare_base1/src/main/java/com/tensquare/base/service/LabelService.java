/**
 * Copyright ©2018 上海屹通. All rights reserved.
 *
 * @Title: LabelService.java
 * @Prject: yitong-mp-pers-recharge
 * @Description:
 * @Package: com.tensquare.base.service
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-26 17:38
 * @version: V1.0
 */
package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

/**
 * @ClassName: LabelService
 * @Description:
 * @author: 张凯达（zhangkaida@yitong.com.cn）
 * @date: 2019-03-26 17:38
 */
@Service("labelService")
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }


    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }
}
