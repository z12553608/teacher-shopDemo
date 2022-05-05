package com.meituan.catering.management.shop.biz.model.bo;

import com.meituan.catering.management.infra.model.enumeration.BusinessTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.infra.model.enumeration.ManagementTypeEnum;
import com.meituan.catering.management.shop.biz.model.common.ContactBO;
import com.meituan.catering.management.shop.biz.model.common.DateRangeBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 门店BO定义
 *
 * @author dulinfeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopBO extends BaseBO {

    /**
     * 上级门店ID
     */
    private Long superiorId;

    /**
     * 商户号
     */
    @NotBlank
    private String businessNo;

    /**
     * 门店名
     */
    @NotBlank
    private String name;

    /**
     * 业态类型
     */
    @NotNull
    private BusinessTypeEnum businessType;

    /**
     * 管理类型
     */
    @NotNull
    private ManagementTypeEnum managementType;

    /**
     * 联系人信息
     */
    private final ContactBO contact = new ContactBO();

    /**
     * 营业时间段
     */
    private final DateRangeBO openingHours = new DateRangeBO();

    /**
     * 营业面积
     */
    private String businessArea;

    /**
     * 门店介绍
     */
    private String comment;

    /**
     * 启停状态
     */
    @NotNull
    private EnabledStatusEnum enabled;

}