package com.example.testjenkins.spi;

import java.util.Arrays;
import java.util.List;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200115000
 */
public class RefundSeason1  implements IRefundSeason{

    @Override
    public List<String> getRefundSeasonList() {
        return Arrays.asList("商品降价","商品无货","其他");
    }
}
