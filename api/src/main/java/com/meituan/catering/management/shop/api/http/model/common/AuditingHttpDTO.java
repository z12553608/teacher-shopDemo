package com.meituan.catering.management.shop.api.http.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 业务对象的审计信息DTO
 *
 * @author dulinfeng
 */
@Data
@ApiModel("审计信息")
public class AuditingHttpDTO {

    @ApiModelProperty("创建人ID")
    private Long createdBy;

    @ApiModelProperty("创建时间")
    private Date createdAt;

    @ApiModelProperty("最后修改人ID")
    private Long lastModifiedBy;

    @ApiModelProperty("最后修改时间")
    private Date lastModifiedAt;
}
