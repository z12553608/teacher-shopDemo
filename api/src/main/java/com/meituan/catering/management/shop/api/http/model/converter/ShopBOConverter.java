package com.meituan.catering.management.shop.api.http.model.converter;

import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.infra.validation.ValidationHelper;
import com.meituan.catering.management.shop.api.http.model.request.*;
import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.biz.model.request.SearchShopBizRequest;
import com.meituan.catering.management.shop.biz.model.request.UpdateShopStatusBizRequest;

import java.util.Date;

/**
 * 从其他数据模型向门店BO的转换器
 *
 * @author dulinfeng
 */
public abstract class ShopBOConverter {

    public static ShopBO fromHttpRequest(UserContextHttpRequest userContext, CreateShopHttpRequest createShopHttpRequest) {
        if (userContext == null || createShopHttpRequest == null) {
            return null;
        }
        ShopBO shopBO = fromSaveShopHttpRequest(userContext, createShopHttpRequest);
        shopBO.getAuditing().setCreatedBy(userContext.getUserId());
        shopBO.getAuditing().setCreatedAt(new Date());
        shopBO.setEnabled(EnabledStatusEnum.ENABLED);
        ValidationHelper.validate(userContext);
        return shopBO;
    }

    public static ShopBO fromHttpRequest(UserContextHttpRequest userContext, FullyUpdateShopHttpRequest updateShopHttpRequest) {
        ShopBO updatingShopBO = fromHttpRequest(userContext, (PartialUpdateShopHttpRequest) updateShopHttpRequest);
        updatingShopBO.setEnabled(updateShopHttpRequest.getEnabledStatus());
        return updatingShopBO;
    }

    public static ShopBO fromHttpRequest(UserContextHttpRequest userContext, PartialUpdateShopHttpRequest updateShopHttpRequest) {
        if (userContext == null || updateShopHttpRequest == null) {
            return null;
        }
        ShopBO updatingShopBO = fromSaveShopHttpRequest(userContext, updateShopHttpRequest);
        updatingShopBO.getAuditing().setLastModifiedBy(userContext.getUserId());
        updatingShopBO.getAuditing().setLastModifiedAt(new Date());
        updatingShopBO.setVersion(updateShopHttpRequest.getVersion());
        return updatingShopBO;
    }

    public static SearchShopBizRequest fromHttpRequest(SearchShopHttpRequest httpRequest) {
        if (httpRequest == null) {
            return null;
        }
        return new SearchShopBizRequest(
                PageBizRequestConverter.fromHttpRequest(httpRequest.getPagination()),
                PageBizRequestConverter.fromHttpRequest(httpRequest.getSortFields()),
                fromHttpRequest(httpRequest.getCondition())
        );
    }

    public static SearchShopBizRequest.Condition fromHttpRequest(SearchShopHttpRequest.Condition httpRequest) {
        if (httpRequest == null) {
            return null;
        }
        SearchShopBizRequest.Condition bizRequest = new SearchShopBizRequest.Condition();
        bizRequest.setKeyword(httpRequest.getKeyword());
        bizRequest.setManagementTypes(httpRequest.getManagementTypes());
        bizRequest.setBusinessTypes(httpRequest.getBusinessTypes());
        bizRequest.setEnabledStatusSet(httpRequest.getEnabledStatusSet());
        return bizRequest;
    }

    private static ShopBO fromSaveShopHttpRequest(UserContextHttpRequest userContext, SaveShopHttpRequest saveShopHttpRequest) {
        if (userContext == null || saveShopHttpRequest == null) {
            return null;
        }
        ShopBO savingShopBO = new ShopBO();
        // Convert - User Context
        savingShopBO.setTenantId(userContext.getTenantId());
        // Convert - Shop
        savingShopBO.setSuperiorId(saveShopHttpRequest.getSuperiorId());
        savingShopBO.setName(saveShopHttpRequest.getName());
        savingShopBO.setBusinessType(saveShopHttpRequest.getBusinessType());
        savingShopBO.setManagementType(saveShopHttpRequest.getManagementType());
        savingShopBO.getContact().setTelephone(saveShopHttpRequest.getContact().getTelephone());
        savingShopBO.getContact().setCellphone(saveShopHttpRequest.getContact().getCellphone());
        savingShopBO.getContact().setName(saveShopHttpRequest.getContact().getName());
        savingShopBO.getContact().setAddress(saveShopHttpRequest.getContact().getAddress());
        savingShopBO.getOpeningHours().setStart(saveShopHttpRequest.getOpeningHours().getStart());
        savingShopBO.getOpeningHours().setEnd(saveShopHttpRequest.getOpeningHours().getEnd());
        savingShopBO.setBusinessArea(saveShopHttpRequest.getBusinessArea());
        savingShopBO.setComment(saveShopHttpRequest.getComment());
        // Return
        return savingShopBO;
    }

    public static UpdateShopStatusBizRequest fromHttpRequest(OpenShopHttpRequest httpRequest) {
        if (httpRequest == null) {
            return null;
        }
        return new UpdateShopStatusBizRequest(
                EnabledStatusEnum.ENABLED,
                httpRequest.getVersion());
    }

    public static UpdateShopStatusBizRequest fromHttpRequest(CloseShopHttpRequest httpRequest) {
        if (httpRequest == null) {
            return null;
        }
        return new UpdateShopStatusBizRequest(
                EnabledStatusEnum.DISABLED,
                httpRequest.getVersion());
    }
}
