package com.meituan.catering.management.shop.api.http.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页Http返回体
 *
 * @author dulinfeng
 */
@Data
@ApiModel("分页Http返回体")
public class PaginationHttpResponse {

    @ApiModelProperty("分页码（不会超过数据集的最大分页码）")
    private Integer index;

    @ApiModelProperty("分页大小")
    private Integer size;

    @ApiModelProperty("符合条件的记录总数")
    private Integer totalCount;

    @ApiModelProperty("符合条件的记录最大分页数")
    private Integer totalPageCount;
}
