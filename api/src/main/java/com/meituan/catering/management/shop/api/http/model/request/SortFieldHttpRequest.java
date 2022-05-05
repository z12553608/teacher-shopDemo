package com.meituan.catering.management.shop.api.http.model.request;

import com.meituan.catering.management.infra.model.enumeration.SortColumnEnum;
import com.meituan.catering.management.infra.model.enumeration.SortDirectionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 排序字段Http请求体
 *
 * @author dioufdu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("排序字段Http请求体")
public class SortFieldHttpRequest<F extends SortColumnEnum> {

    @ApiModelProperty(value = "排序列名", example = "LAST_MODIFIED_AT")
    private F column;

    @ApiModelProperty(value = "排序方向", example = "DESC")
    private SortDirectionEnum direction;
}