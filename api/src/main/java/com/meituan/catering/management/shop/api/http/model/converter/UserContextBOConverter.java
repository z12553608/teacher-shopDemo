package com.meituan.catering.management.shop.api.http.model.converter;

import com.meituan.catering.management.shop.api.http.model.request.UserContextHttpRequest;
import com.meituan.catering.management.shop.biz.model.common.UserContextBO;

/**
 * 从其他数据模型向门店BO的转换器
 *
 * @author dulinfeng
 */
public abstract class UserContextBOConverter {

    public static UserContextBO fromHttpRequest(UserContextHttpRequest httpRequest) {
        if (httpRequest == null) {
            return null;
        }
        UserContextBO bo = new UserContextBO();
        bo.setTenantId(httpRequest.getTenantId());
        bo.setShopId(httpRequest.getShopId());
        bo.setUserId(httpRequest.getUserId());
        bo.setUserName(httpRequest.getUserName());
        return bo;
    }
}
