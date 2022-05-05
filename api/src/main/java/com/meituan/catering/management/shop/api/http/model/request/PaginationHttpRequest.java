package com.meituan.catering.management.shop.api.http.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 分页Http请求体
 *
 * @author dioufdu
 */
@Data
@Valid
@ApiModel("分页Http请求体")
public class PaginationHttpRequest {

    @NotNull
    @Min(1)
    @Max(500)
    @ApiModelProperty(value = "分页码：从1开始计算，最多500页", example = "1")
    private Integer index;

    @NotNull
    @Min(1)
    @Max(500)
    @ApiModelProperty(value = "分页大小：最多支持500条每页", example = "10")
    private Integer size;
}
