package com.meituan.catering.management.shop.biz.service;

import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.biz.model.common.UserContextBO;
import com.meituan.catering.management.shop.biz.model.request.UpdateShopStatusBizRequest;

/**
 * 门店操作业务服务
 *
 * @author dulinfeng
 */
public interface ShopBizOperateService {

    /**
     * 创建一个新的门店
     *
     * @param userContext 操作用户上下文
     * @param shopBO      门店BO实例
     * @return 门店详情
     */
    ShopBO create(UserContextBO userContext, ShopBO shopBO);

    /**
     * 更新一个新的门店
     *
     * @param userContext 操作用户上下文
     * @param businessNo  商户号
     * @param shopBO      门店BO实例
     * @return 门店详情
     */
    ShopBO fullyUpdate(UserContextBO userContext, String businessNo, ShopBO shopBO);

    /**
     * 更新一个新的门店
     *
     * @param userContext 操作用户上下文
     * @param businessNo  商户号
     * @param shopBO      门店BO实例
     * @return 门店详情
     */
    ShopBO partialUpdate(UserContextBO userContext, String businessNo, ShopBO shopBO);

    /**
     * 根据商户号启用门店
     *
     * @param userContext 操作用户上下文
     * @param businessNo  商户号
     * @param request     更新门店状态的Biz请求
     * @return 门店详情
     */
    ShopBO updateShopStatus(UserContextBO userContext, String businessNo, UpdateShopStatusBizRequest request);
}
