package com.meituan.catering.management.shop.biz.model.converter;

import com.meituan.catering.management.infra.model.enumeration.BusinessTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.DescribableEnum;
import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.infra.model.enumeration.ManagementTypeEnum;
import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.dao.model.entity.ShopDO;

/**
 * 从其他数据模型向门店BO的转换器
 *
 * @author dioufdu
 */
public abstract class ShopBOConverter {

    public static ShopBO fromDO(ShopDO shopDO) {
        if (shopDO == null) {
            return null;
        }
        // Convert
        ShopBO shopBO = new ShopBO();
        // Convert - Base
        shopBO.setId(shopDO.getId());
        shopBO.setTenantId(shopDO.getTenantId());
        shopBO.setSuperiorId(shopDO.getSuperiorId());
        shopBO.getAuditing().setCreatedBy(shopDO.getCreatedBy());
        shopBO.getAuditing().setCreatedAt(shopDO.getCreatedAt());
        shopBO.getAuditing().setLastModifiedBy(shopDO.getLastModifiedBy());
        shopBO.getAuditing().setLastModifiedAt(shopDO.getLastModifiedAt());
        // Convert - Shop
        shopBO.setBusinessNo(shopDO.getBusinessNo());
        shopBO.setName(shopDO.getName());
        shopBO.setBusinessType(DescribableEnum.getByCode(BusinessTypeEnum.class, shopDO.getBusinessTypeCode()));
        shopBO.getContact().setTelephone(shopDO.getContactTelephone());
        shopBO.getContact().setCellphone(shopDO.getContactCellphone());
        shopBO.getContact().setName(shopDO.getContactName());
        shopBO.getContact().setAddress(shopDO.getContactAddress());
        shopBO.setManagementType(DescribableEnum.getByCode(ManagementTypeEnum.class, shopDO.getManagementTypeCode()));
        shopBO.getOpeningHours().setStart(shopDO.getOpeningHoursStart());
        shopBO.getOpeningHours().setEnd(shopDO.getOpeningHoursEnd());
        shopBO.setBusinessArea(shopDO.getBusinessArea());
        shopBO.setComment(shopDO.getComment());
        shopBO.setEnabled(DescribableEnum.getByCode(EnabledStatusEnum.class, shopDO.getEnabled()));
        shopBO.setVersion(shopDO.getVersion());

        return shopBO;
    }

}
