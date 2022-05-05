package com.meituan.catering.management.infra.model.enumeration;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 可以进行排序的字段
 *
 * @author dulinfeng
 */
@Getter
@RequiredArgsConstructor
public enum ShopSortColumnEnum implements SortColumnEnum {
    /**
     * 商户号
     */
    @ApiModelProperty("商户号")
    BUSINESS_NO("商户号", "business_no"),

    /**
     * 最后更新的时间
     */
    @ApiModelProperty("最后更新的时间")
    LAST_MODIFIED_AT("最后更新的时间", "last_modified_at"),
    /**
     * 启用状态
     */
    @ApiModelProperty("启用状态")
    ENABLED("启用状态", "enabled"),

    ;

    private final String description;

    private final String sqlName;

    @Override
    public String getName() {
        return name();
    }
}
