package com.meituan.catering.management.shop.api.http.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 当前登录用户上下文Http请后体
 *
 * @author dulinfeng
 */
@Data
@Valid
@ApiModel("当前登录用户上下文Http请后体")
public class UserContextHttpRequest {

    @NotNull
    @Min(100)
    @Max(999)
    @ApiModelProperty(value = "租户ID", example = "500")
    private Long tenantId;

    @NotNull
    @Min(100)
    @Max(999)
    @ApiModelProperty(value = "门店ID", example = "100")
    private Long shopId;

    @NotNull
    @Min(10000)
    @Max(99999)
    @ApiModelProperty(value = "用户ID", example = "11000")
    private Long userId;

    @NotBlank
    @ApiModelProperty(value = "用户名", example = "test user")
    private String userName;
}
