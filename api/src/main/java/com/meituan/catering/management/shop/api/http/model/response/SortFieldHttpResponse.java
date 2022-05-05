package com.meituan.catering.management.shop.api.http.model.response;

import com.meituan.catering.management.infra.model.enumeration.SortDirectionEnum;
import com.meituan.catering.management.shop.api.http.model.common.SortColumnEnumDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排序字段Http返回体
 *
 * @author dioufdu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("排序字段Http返回体")
public class SortFieldHttpResponse {

    @ApiModelProperty("排序列名")
    private SortColumnEnumDTO column;

    @ApiModelProperty("排序方向")
    private SortDirectionEnum direction;
}