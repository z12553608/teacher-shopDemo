package com.meituan.catering.management.shop.biz.model.request;

import lombok.Data;

/**
 * 分页Biz请求体
 *
 * @author dulinfeng
 */
@Data
public class PaginationBizRequest {

    /**
     * 分页码
     */
    private final Integer index;

    /**
     * 分页大小
     */
    private final Integer size;
}
