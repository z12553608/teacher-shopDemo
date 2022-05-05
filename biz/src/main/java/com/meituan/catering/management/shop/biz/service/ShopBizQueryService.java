package com.meituan.catering.management.shop.biz.service;

import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.biz.model.common.UserContextBO;
import com.meituan.catering.management.shop.biz.model.request.SearchShopBizRequest;
import com.meituan.catering.management.shop.biz.model.response.PageBizResponse;

import java.util.Set;

/**
 * 门店查询业务服务
 *
 * @author dulinfeng
 */
public interface ShopBizQueryService {

    /**
     * 根据商户号获取商家详情
     *
     * @param userContext 操作用户上下文
     * @param businessNo  商户号
     * @return 门店详情
     */
    ShopBO findByBusinessNo(UserContextBO userContext, String businessNo);

    /**
     * 搜索查询门店列表
     *
     * @param userContext 操作用户上下文
     * @param request     查询门店的请求
     * @return 门店分页信息
     */
    PageBizResponse<ShopBO, ShopSortColumnEnum> searchForPage(UserContextBO userContext, SearchShopBizRequest request);

    /**
     * 根据当前登录用户的门店ID确定有权限操作的门店ID范围（包括当前门店和下属门店）
     *
     * @param userContext 操作用户上下文
     * @return 有权限操作的门店ID范围（包括当前门店和下属门店）
     */
    Set<Long> findPrivilegedShopIds(UserContextBO userContext);
}
