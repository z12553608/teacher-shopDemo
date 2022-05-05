package com.meituan.catering.management.shop.biz.model.response;

import com.meituan.catering.management.infra.model.enumeration.SortColumnEnum;
import com.meituan.catering.management.infra.model.enumeration.SortDirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排序字段Biz返回体
 *
 * @author dulinfeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortFieldBizResponse<C extends SortColumnEnum> {

    /**
     * 排序列名
     */
    private C column;

    /**
     * 排序方向
     */
    private SortDirectionEnum direction;
}