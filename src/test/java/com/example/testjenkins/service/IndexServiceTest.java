package com.example.testjenkins.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200106000
 */

@SpringBootTest
class IndexServiceTest {
    @Autowired
    private IndexService indexService;
    @Test
    public void add(){
        int result = 10;
        Assert.isTrue(indexService.add(5,5)== result,"add fil");
    }

    @Test
    public void testBig(){
        int param  = 10;
        Assert.isTrue(indexService.save(param).equals("big"),"打的");
    }

    @Test
    public void testSmall(){
        int param  = 9;
        Assert.isTrue(indexService.save(param).equals("small"),"小的");
    }

}