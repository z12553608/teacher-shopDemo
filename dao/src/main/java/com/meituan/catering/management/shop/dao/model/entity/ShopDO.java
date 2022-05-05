package com.meituan.catering.management.shop.dao.model.entity;

import com.meituan.catering.management.infra.model.enumeration.BusinessTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.infra.model.enumeration.ManagementTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 门店DO定义
 *
 * @author dulinfeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopDO extends BaseDO {

    /**
     * 上级门店ID
     */
    private Long superiorId;

    /**
     * 商户号
     */
    private String businessNo;

    /**
     * 商户名
     */
    private String name;

    /**
     * 主营业态{@link BusinessTypeEnum#getCode()}
     */
    private Integer businessTypeCode;

    /**
     * 联系方式-座机
     */
    private String contactTelephone;

    /**
     * 联系方式-手机
     */
    private String contactCellphone;

    /**
     * 联系方式-联系人名称
     */
    private String contactName;

    /**
     * 联系方式-地址
     */
    private String contactAddress;

    /**
     * 管理类型{@link ManagementTypeEnum#getCode()}
     */
    private Integer managementTypeCode;

    /**
     * 营业时间-开始时间点
     */
    private String openingHoursStart;

    /**
     * 营业时间-结束时间点
     */
    private String openingHoursEnd;

    /**
     * 经营面积
     */
    private String businessArea;

    /**
     * 门店介绍
     */
    private String comment;

    /**
     * 门店状态{@link EnabledStatusEnum#getCode()}
     */
    private Integer enabled;

}