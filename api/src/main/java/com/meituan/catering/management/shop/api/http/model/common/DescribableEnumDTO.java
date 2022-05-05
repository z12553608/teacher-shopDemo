package com.meituan.catering.management.shop.api.http.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 可描述的枚举DTO
 *
 * @author dulinfeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("枚举返回体")
public class DescribableEnumDTO {

    @ApiModelProperty("编码")
    private Integer code;

    @ApiModelProperty("枚举名")
    private String name;

}
