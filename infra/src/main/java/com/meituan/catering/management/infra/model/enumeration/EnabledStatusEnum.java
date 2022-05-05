package com.meituan.catering.management.infra.model.enumeration;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 启停状态
 *
 * @author dulinfeng
 */
@Getter
@RequiredArgsConstructor
@ApiModel("启停类型")
public enum EnabledStatusEnum implements DescribableEnum {

    /**
     * 启用
     */
    @ApiModelProperty("启用")
    ENABLED(1, "启用"),

    /**
     * 停用
     */
    @ApiModelProperty("停用")
    DISABLED(2, "停用"),

    ;

    private final int code;

    private final String name;
}
