package com.example.testjenkins.spi;

import java.util.List;

/**
 * TODO 简要描述
 *
 * @author wangxulong duanxian0402@126.com
 * @version 20200115000
 */
public interface IRefundSeason {

    default List<String> invoke(){
        List<String> refundSeasons = getRefundSeasonList();

        return refundSeasons;
    }

    List<String> getRefundSeasonList();
}
