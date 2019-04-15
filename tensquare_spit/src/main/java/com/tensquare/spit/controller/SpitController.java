package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable String parentid,
                                 @PathVariable int page,
                                 @PathVariable int size) {
        Page<Spit> spitPage = spitService.findByParentid(parentid, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Spit>(spitPage.getTotalElements(), spitPage.getContent()));
    }

    @GetMapping
    public Result search() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(id));
    }

    /**
     * 增加
     *
     * @param spit
     */
    @PostMapping
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改
     *
     * @param spit
     */
    @PutMapping("/{id}")
    public Result updateById(@RequestBody Spit spit, @PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result updateById(@PathVariable String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PutMapping("/thumbup/{spitId}")
    public Result updateThumbup(@PathVariable String spitId) {
        //判断用户是否点过赞
        String userid = "zkd";// 后边我们会修改为当前登陆的用户
        if (redisTemplate.opsForValue().get("thumbup_" + userid + "_" + spitId) != null) {
            return new Result(false, StatusCode.REPERROR, "你已经点过赞了");
        }
        spitService.updateThumbup(spitId);
        redisTemplate.opsForValue().set( "thumbup_"+userid+"_"+ spitId,"1");
        return new Result(true, StatusCode.OK, "点赞成功");
    }

}
