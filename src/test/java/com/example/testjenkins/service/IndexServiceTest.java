package com.example.testjenkins.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

 import static org.hamcrest.MatcherAssert.assertThat;
 import static org.hamcrest.Matchers.*;
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
        assertThat(indexService.add(1,1),equalTo(2));
    }

    @Test
    public void testBig(){
        int param  = 10;
        assertThat(indexService.save(param),equalTo("big"));

    }

    @Test
    public void testSmall(){
        int param  = 9;
        Assert.isTrue(indexService.save(param).equals("small"),"小的");
    }

}