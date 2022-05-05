package com.meituan.catering.management.shop.api.http.model.response;

import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.shop.api.http.model.common.AuditingHttpDTO;
import com.meituan.catering.management.shop.api.http.model.common.ContactDTO;
import com.meituan.catering.management.shop.api.http.model.common.DateRangeDTO;
import com.meituan.catering.management.shop.api.http.model.common.DescribableEnumDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门店详情的Http返回体
 *
 * @author dulinfeng
 */
@Data
@ApiModel("门店详情Http返回体")
public class ShopDetailHttpResponse {

    @ApiModelProperty("物理ID")
    private Long id;

    @ApiModelProperty("上级门店ID")
    private Long superiorId;

    @ApiModelProperty("租户ID")
    private Long tenantId;

    @ApiModelProperty("商户号")
    private String businessNo;

    @ApiModelProperty("审计信息")
    private final AuditingHttpDTO auditing = new AuditingHttpDTO();

    @ApiModelProperty("门店名")
    private String name;

    @ApiModelProperty("业态类型")
    private DescribableEnumDTO businessType;

    @ApiModelProperty("管理类型")
    private DescribableEnumDTO managementType;

    @ApiModelProperty("联系人信息")
    private final ContactDTO contact = new ContactDTO();

    @ApiModelProperty("营业时间段")
    private final DateRangeDTO openingHours = new DateRangeDTO();

    @ApiModelProperty("营业面积")
    private String businessArea;

    @ApiModelProperty("门店介绍")
    private String comment;

    @ApiModelProperty("启停状态")
    private EnabledStatusEnum enabled;

    @ApiModelProperty("目标门店的版本号")
    private Integer version;
}