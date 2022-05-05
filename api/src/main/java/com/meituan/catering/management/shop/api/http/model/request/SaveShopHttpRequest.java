package com.meituan.catering.management.shop.api.http.model.request;

import com.meituan.catering.management.infra.model.enumeration.BusinessTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.ManagementTypeEnum;
import com.meituan.catering.management.shop.api.http.model.common.ContactDTO;
import com.meituan.catering.management.shop.api.http.model.common.DateRangeDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 保存门店Http请求体
 *
 * @author dulinfeng
 */
@Data
@ApiModel("保存门店Http请求体")
public class SaveShopHttpRequest {

    @ApiModelProperty("上级门店ID")
    private Long superiorId;

    @NotBlank
    @ApiModelProperty("门店名")
    private String name;

    @NotNull
    @ApiModelProperty("业态类型")
    private BusinessTypeEnum businessType;

    @NotNull
    @ApiModelProperty("管理类型")
    private ManagementTypeEnum managementType;

    @ApiModelProperty("营业时间段")
    private final DateRangeDTO openingHours = new DateRangeDTO();

    @ApiModelProperty("联系人信息")
    private final ContactDTO contact = new ContactDTO();

    @ApiModelProperty("营业面积")
    private String businessArea;

    @ApiModelProperty("门店介绍")
    private String comment;
}