package com.meituan.catering.management.shop.biz.model.converter;

import com.meituan.catering.management.infra.model.enumeration.BusinessTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.infra.model.enumeration.ManagementTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.biz.model.common.UserContextBO;
import com.meituan.catering.management.shop.biz.model.request.SearchShopBizRequest;
import com.meituan.catering.management.shop.biz.model.request.SortFieldBizRequest;
import com.meituan.catering.management.shop.dao.model.entity.ShopDO;
import com.meituan.catering.management.shop.dao.model.request.PaginationDataRequest;
import com.meituan.catering.management.shop.dao.model.request.SearchShopDataRequest;
import com.meituan.catering.management.shop.dao.model.request.SortFieldDataRequest;
import org.apache.commons.collections4.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 从其他数据模型向门店DO的转换器
 *
 * @author dulinfeng
 */
public abstract class ShopDOConverter {

    private static final String FUZZY_MATCH_PATTERN = "%{0}%";

    public static ShopDO fromBO(ShopBO shopBO) {
        if (shopBO == null) {
            return null;
        }
        // Convert
        ShopDO shopDO = new ShopDO();
        // Convert - Base
        shopDO.setId(shopBO.getId());
        shopDO.setSuperiorId(shopBO.getSuperiorId());
        shopDO.setTenantId(shopBO.getTenantId());
        shopDO.setCreatedBy(shopBO.getAuditing().getCreatedBy());
        shopDO.setCreatedAt(shopBO.getAuditing().getCreatedAt());
        shopDO.setLastModifiedBy(shopBO.getAuditing().getLastModifiedBy());
        shopDO.setLastModifiedAt(shopBO.getAuditing().getLastModifiedAt());
        // Convert - Shop
        shopDO.setBusinessNo(shopBO.getBusinessNo());
        shopDO.setName(shopBO.getName());
        if (shopBO.getBusinessType() != null) {
            shopDO.setBusinessTypeCode(shopBO.getBusinessType().getCode());
        }
        if (shopBO.getManagementType() != null) {
            shopDO.setManagementTypeCode(shopBO.getManagementType().getCode());
        }
        shopDO.setContactTelephone(shopBO.getContact().getTelephone());
        shopDO.setContactCellphone(shopBO.getContact().getCellphone());
        shopDO.setContactName(shopBO.getContact().getName());
        shopDO.setContactAddress(shopBO.getContact().getAddress());
        shopDO.setOpeningHoursStart(shopBO.getOpeningHours().getStart());
        shopDO.setOpeningHoursEnd(shopBO.getOpeningHours().getEnd());
        shopDO.setBusinessArea(shopBO.getBusinessArea());
        shopDO.setComment(shopBO.getComment());
        if (shopBO.getEnabled() != null) {
            shopDO.setEnabled(shopBO.getEnabled().getCode());
        }
        shopDO.setVersion(shopBO.getVersion());
        return shopDO;
    }

    public static SearchShopDataRequest fromBizRequest(
            UserContextBO userContext,
            SearchShopBizRequest request,
            Set<Long> privilegedShopIds) {
        // 分页条件
        PaginationDataRequest pagination = new PaginationDataRequest(
                request.getPagination().getIndex(),
                request.getPagination().getSize());
        // 排序条件
        List<SortFieldDataRequest> sortFields = (
                CollectionUtils.isEmpty(request.getSortFields())
                        ? SearchShopBizRequest.DEFAULT_SORT_FIELDS
                        : request.getSortFields())
                .stream()
                .map(ShopDOConverter::fromBizRequest)
                .collect(toList());
        // 过滤条件
        SearchShopDataRequest.Condition condition = new SearchShopDataRequest.Condition();
        condition.setTenantId(userContext.getTenantId());
        condition.setPrivilegedShopIds(privilegedShopIds);
        if (isNotEmpty(request.getCondition().getManagementTypes())) {
            condition.setManagementTypeCodes(request.getCondition().getManagementTypes()
                    .stream()
                    .map(ManagementTypeEnum::getCode)
                    .collect(toSet()));
        }
        if (isNotEmpty(request.getCondition().getBusinessTypes())) {
            condition.setBusinessTypeCodes(
                    request.getCondition().getBusinessTypes()
                            .stream()
                            .map(BusinessTypeEnum::getCode)
                            .collect(toSet()));
        }
        if (isNotEmpty(request.getCondition().getEnabledStatusSet())) {
            condition.setEnabledStatusCodeSet(request.getCondition().getEnabledStatusSet()
                    .stream()
                    .map(EnabledStatusEnum::getCode)
                    .collect(toSet()));
        }
        // NOTE: 模糊搜索是非常消耗性能的，实际场景中会交给ES去完成，这里只是为了做功能演示而使用。
        if (isNotBlank(request.getCondition().getKeyword())) {
            condition.setHasKeywords(true);
            condition.setName(MessageFormat.format(FUZZY_MATCH_PATTERN, request.getCondition().getKeyword()));
            condition.setContactTelephone(MessageFormat.format(FUZZY_MATCH_PATTERN, request.getCondition().getKeyword()));
            condition.setContactCellphone(MessageFormat.format(FUZZY_MATCH_PATTERN, request.getCondition().getKeyword()));
            condition.setContactName(MessageFormat.format(FUZZY_MATCH_PATTERN, request.getCondition().getKeyword()));
            condition.setContactAddress(MessageFormat.format(FUZZY_MATCH_PATTERN, request.getCondition().getKeyword()));
        }
        // 构建返回
        return new SearchShopDataRequest(pagination, sortFields, condition);
    }

    private static SortFieldDataRequest fromBizRequest(SortFieldBizRequest<ShopSortColumnEnum> sortField) {
        if (sortField == null) {
            return null;
        }
        return new SortFieldDataRequest(sortField.getColumn().getSqlName(), sortField.getDirection());
    }
}
