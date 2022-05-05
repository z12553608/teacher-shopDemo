package com.meituan.catering.management.shop.api.http.model.request;

import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * 更新门店的Http请求
 *
 * @author dulinfeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel("全量更新门店Http请求体")
public class FullyUpdateShopHttpRequest extends PartialUpdateShopHttpRequest {

    @NotNull
    @ApiModelProperty("启停状态")
    private EnabledStatusEnum enabledStatus;
}