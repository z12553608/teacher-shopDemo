package com.meituan.catering.management.shop.biz.model.common;

import lombok.Data;

import java.util.Date;

/**
 * 业务对象的审计信息
 *
 * @author dulinfeng
 */
@Data
public class AuditingBO {

    private Long createdBy;

    private Date createdAt;

    private Long lastModifiedBy;

    private Date lastModifiedAt;
}
