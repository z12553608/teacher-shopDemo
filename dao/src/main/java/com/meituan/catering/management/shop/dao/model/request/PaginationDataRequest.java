package com.meituan.catering.management.shop.dao.model.request;

import lombok.Data;

/**
 * 分页数据请求
 */
@Data
public class PaginationDataRequest {

    /**
     * 跳过多少条记录
     */
    private int skip;

    /**
     * 抓取多少条记录
     */
    private int limit;

    public PaginationDataRequest(int index, int size) {
        if (index < 1) {
            throw new IllegalArgumentException("分页请求index必须大于0");
        }
        if (size < 0) {
            throw new IllegalArgumentException("分页请求size必须大于0");
        }
        this.skip = (index - 1) * size;
        this.limit = size;
    }
}
