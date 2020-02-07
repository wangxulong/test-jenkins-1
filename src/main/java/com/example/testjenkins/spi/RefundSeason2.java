package com.example.testjenkins.spi;

import java.util.Arrays;
import java.util.List;

/**
 * TODO 简要描述testjenkins
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200115000
 */
public class RefundSeason2 implements IRefundSeason{

    @Override
    public List<String> getRefundSeasonList() {
        return Arrays.asList("不想要了","七天无理由","其他");
    }
}
