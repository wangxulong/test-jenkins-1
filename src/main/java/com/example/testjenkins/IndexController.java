package com.example.testjenkins;

import com.example.testjenkins.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20191231000
 */
@RestController
public class IndexController {
    private final IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping
    public String index(){
        return "hello";
    }

    @GetMapping("/index")
    public String ddd(){
        return "index";
    }
    @PostMapping
    public String post(){
        return indexService.save(123);

    }
}
