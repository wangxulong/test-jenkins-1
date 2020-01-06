package com.example.testjenkins.service;

import org.springframework.stereotype.Service;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200106000
 */
@Service
public class IndexService {

    public int add(int a, int b) {
        return a + b;
    }

    String save(int a){
        if(a<10){
            return "small";
        }else {
            return "big";
        }
    }
}
