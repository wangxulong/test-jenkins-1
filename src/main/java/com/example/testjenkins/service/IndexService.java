package com.example.testjenkins.service;

import org.springframework.stereotype.Service;

/**
 * 首页内容
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200106000
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
@Service
public class IndexService {
    static int  parma = 10;
    public int add(int a, int b) {
        return a + b;
    }

   public String save(int a){
        if(a<parma){
            return "small";
        }else {
            return "big";
        }
    }
}
