package com.meituan.catering.management.shop.biz.model.response;

import lombok.Data;

/**
 * 分页Biz返回体
 *
 * @author dulinfeng
 */
@Data
public class PaginationBizResponse {

    /**
     * 分页码（不会超过数据集的最大分页码）
     */
    private Integer index;

    /**
     * 分页大小
     */
    private Integer size;

    /**
     * 符合条件的记录总数
     */
    private Integer totalCount;

    /**
     * 符合条件的记录最大分页数
     */
    private Integer totalPageCount;
}
