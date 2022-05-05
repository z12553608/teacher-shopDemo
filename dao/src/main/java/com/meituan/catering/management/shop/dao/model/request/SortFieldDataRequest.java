package com.meituan.catering.management.shop.dao.model.request;

import com.meituan.catering.management.infra.model.enumeration.SortDirectionEnum;
import lombok.Data;

/**
 * 排序数据请求
 *
 * @author dulinfeng
 */
@Data
public class SortFieldDataRequest {

    /**
     * 排序列名
     */
    private final String sqlColumn;

    /**
     * 排序方向
     */
    private final SortDirectionEnum direction;
}
