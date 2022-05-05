package com.meituan.catering.management.shop.biz.model.request;

import com.meituan.catering.management.infra.model.enumeration.SortColumnEnum;
import com.meituan.catering.management.infra.model.enumeration.SortDirectionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排序字段Biz请求体
 *
 * @author dulinfeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortFieldBizRequest<F extends SortColumnEnum> {

    /**
     * 排序列
     */
    private F column;

    /**
     * 排序方向
     */
    private SortDirectionEnum direction;
}