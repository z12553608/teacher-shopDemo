package com.meituan.catering.management.shop.api.http.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 时间段DTO
 *
 * @author dulinfeng
 */
@Data
@ApiModel("时间区间")
public class DateRangeDTO {

    @ApiModelProperty("起始时间")
    private String start;

    @ApiModelProperty("结束时间")
    private String end;

}
