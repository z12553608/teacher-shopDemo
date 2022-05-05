package com.meituan.catering.management.shop.api.http.model.converter;

import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import com.meituan.catering.management.shop.api.http.model.response.ShopDetailHttpResponse;
import com.meituan.catering.management.shop.api.http.model.response.ShopPageHttpResponse;
import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.biz.model.response.PageBizResponse;

import static java.util.stream.Collectors.toList;

/**
 * 从其他数据模型向门店VO模型的转换器
 *
 * @author dulinfeng
 */
public abstract class ShopVOConverter {

    public static ShopDetailHttpResponse fromBO(ShopBO shopBO) {
        if (shopBO == null) {
            return null;
        }
        // Convert
        ShopDetailHttpResponse httpResponse = new ShopDetailHttpResponse();
        // Convert - Base
        httpResponse.setId(shopBO.getId());
        httpResponse.setTenantId(shopBO.getTenantId());
        httpResponse.getAuditing().setCreatedBy(shopBO.getAuditing().getCreatedBy());
        httpResponse.getAuditing().setCreatedAt(shopBO.getAuditing().getCreatedAt());
        httpResponse.getAuditing().setLastModifiedBy(shopBO.getAuditing().getLastModifiedBy());
        httpResponse.getAuditing().setLastModifiedAt(shopBO.getAuditing().getLastModifiedAt());
        // Convert - Shop
        httpResponse.setSuperiorId(shopBO.getSuperiorId());
        httpResponse.setBusinessNo(shopBO.getBusinessNo());
        httpResponse.setName(shopBO.getName());
        httpResponse.setBusinessType(EnumVOConverter.fromEnum(shopBO.getBusinessType()));
        httpResponse.getContact().setTelephone(shopBO.getContact().getTelephone());
        httpResponse.getContact().setCellphone(shopBO.getContact().getCellphone());
        httpResponse.getContact().setName(shopBO.getContact().getName());
        httpResponse.getContact().setAddress(shopBO.getContact().getAddress());
        httpResponse.getOpeningHours().setStart(shopBO.getOpeningHours().getStart());
        httpResponse.getOpeningHours().setEnd(shopBO.getOpeningHours().getEnd());
        httpResponse.setManagementType(EnumVOConverter.fromEnum(shopBO.getManagementType()));
        httpResponse.setBusinessArea(shopBO.getBusinessArea());
        httpResponse.setComment(shopBO.getComment());
        httpResponse.setEnabled(shopBO.getEnabled());
        httpResponse.setVersion(shopBO.getVersion());
        return httpResponse;
    }

    public static ShopPageHttpResponse fromPageBO(PageBizResponse<ShopBO, ShopSortColumnEnum> bizResponse) {
        if (bizResponse == null) {
            return null;
        }
        return new ShopPageHttpResponse(
                PageVOConverter.fromBizRequest(bizResponse.getPagination()),
                PageVOConverter.fromBizRequest(bizResponse.getSortFields()),
                bizResponse.getRecords().stream()
                        .map(ShopVOConverter::fromPageRecord)
                        .collect(toList())
        );
    }

    public static ShopPageHttpResponse.Record fromPageRecord(ShopBO shopBO) {
        if (shopBO == null) {
            return null;
        }
        ShopPageHttpResponse.Record record = new ShopPageHttpResponse.Record();
        // Convert - Base
        record.setId(shopBO.getId());
        record.setTenantId(shopBO.getTenantId());
        record.getAuditing().setCreatedBy(shopBO.getAuditing().getCreatedBy());
        record.getAuditing().setCreatedAt(shopBO.getAuditing().getCreatedAt());
        record.getAuditing().setLastModifiedBy(shopBO.getAuditing().getLastModifiedBy());
        record.getAuditing().setLastModifiedAt(shopBO.getAuditing().getLastModifiedAt());
        // Convert - Shop
        record.setSuperiorId(shopBO.getSuperiorId());
        record.setBusinessNo(shopBO.getBusinessNo());
        record.setName(shopBO.getName());
        record.setBusinessType(EnumVOConverter.fromEnum(shopBO.getBusinessType()));
        record.getContact().setTelephone(shopBO.getContact().getTelephone());
        record.getContact().setCellphone(shopBO.getContact().getCellphone());
        record.getContact().setName(shopBO.getContact().getName());
        record.getContact().setAddress(shopBO.getContact().getAddress());
        record.getOpeningHours().setStart(shopBO.getOpeningHours().getStart());
        record.getOpeningHours().setEnd(shopBO.getOpeningHours().getEnd());
        record.setManagementType(EnumVOConverter.fromEnum(shopBO.getManagementType()));
        record.setBusinessArea(shopBO.getBusinessArea());
        record.setComment(shopBO.getComment());
        record.setEnabled(shopBO.getEnabled());
        record.setVersion(shopBO.getVersion());
        return record;
    }
}
