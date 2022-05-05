package com.meituan.catering.management.shop.biz.model.bo;

import com.meituan.catering.management.shop.biz.model.common.AuditingBO;
import lombok.Data;

/**
 * 所有业务BO实例基类
 *
 * @author dulinfeng
 */
@Data
public abstract class BaseBO {

    /**
     * 物理ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 审计信息
     */
    private final AuditingBO auditing = new AuditingBO();
}
