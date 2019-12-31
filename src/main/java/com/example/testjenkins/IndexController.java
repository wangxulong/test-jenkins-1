package com.example.testjenkins;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20191231000
 */
@RestController
public class IndexController {

    @GetMapping
    public String idnex(){
        return "hello";
    }
}
