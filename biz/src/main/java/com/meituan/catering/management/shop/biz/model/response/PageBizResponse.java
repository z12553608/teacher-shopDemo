package com.meituan.catering.management.shop.biz.model.response;

import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * 业务对象的分页Biz返回体
 *
 * @author dulinfeng
 */
@Data
public class PageBizResponse<T, C extends ShopSortColumnEnum> {

    /**
     * 分页信息
     */
    private final PaginationBizResponse pagination;

    /**
     * 排序字段
     */
    private final List<SortFieldBizResponse<C>> sortFields;

    /**
     * 请求页的记录列表
     */
    private final List<T> records = new LinkedList<>();

}
