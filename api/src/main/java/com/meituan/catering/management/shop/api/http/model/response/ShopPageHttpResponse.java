package com.meituan.catering.management.shop.api.http.model.response;

import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.shop.api.http.model.common.AuditingHttpDTO;
import com.meituan.catering.management.shop.api.http.model.common.ContactDTO;
import com.meituan.catering.management.shop.api.http.model.common.DateRangeDTO;
import com.meituan.catering.management.shop.api.http.model.common.DescribableEnumDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dulinfeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("门店分页Http返回体")
public class ShopPageHttpResponse {

    /**
     * 分页信息
     */
    private PaginationHttpResponse pagination;

    /**
     * 排序信息
     */
    private List<SortFieldHttpResponse> sortFields;

    /**
     * 当前页内的记录列表
     */
    private List<Record> records;

    /**
     * 分页记录
     */
    @Data
    public static class Record {

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
}