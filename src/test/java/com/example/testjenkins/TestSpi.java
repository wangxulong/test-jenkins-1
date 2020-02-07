package com.example.testjenkins;

import com.example.testjenkins.spi.IRefundSeason;

import java.util.List;
import java.util.ServiceLoader;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200115000
 */
public class TestSpi {

    public static void main(String[] args) {
        ServiceLoader<IRefundSeason>  loader  = ServiceLoader.load(IRefundSeason.class);

        loader.forEach( i ->{
            List<String> resons = i.invoke();
            System.out.println(resons);

        });
    }
}
