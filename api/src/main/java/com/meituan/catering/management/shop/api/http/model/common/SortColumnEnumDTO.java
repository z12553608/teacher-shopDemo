package com.meituan.catering.management.shop.api.http.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排序列枚举VO返回体
 *
 * @author dulinfeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("排序列枚举")
public class SortColumnEnumDTO {

    @ApiModelProperty("排序列明")
    private String name;

    @ApiModelProperty("描述")
    private String description;
}
