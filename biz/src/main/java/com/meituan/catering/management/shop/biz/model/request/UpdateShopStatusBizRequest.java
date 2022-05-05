package com.meituan.catering.management.shop.biz.model.request;

import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import lombok.Data;

/**
 * 更新门店状态的Biz请求
 *
 * @author dulinfeng
 */
@Data
public class UpdateShopStatusBizRequest {

    /**
     * 目前门店状态
     */
    private final EnabledStatusEnum enabledStatus;

    /**
     * 目标门店的版本号
     */
    private final Integer version;
}