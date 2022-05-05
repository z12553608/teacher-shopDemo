package com.meituan.catering.management.shop.biz.service.impl;

import com.google.common.collect.ImmutableSet;
import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.biz.model.common.UserContextBO;
import com.meituan.catering.management.shop.biz.model.converter.PageBizResponseConverter;
import com.meituan.catering.management.shop.biz.model.converter.ShopBOConverter;
import com.meituan.catering.management.shop.biz.model.converter.ShopDOConverter;
import com.meituan.catering.management.shop.biz.model.request.SearchShopBizRequest;
import com.meituan.catering.management.shop.biz.model.response.PageBizResponse;
import com.meituan.catering.management.shop.biz.service.ShopBizQueryService;
import com.meituan.catering.management.shop.dao.mapper.ShopMapper;
import com.meituan.catering.management.shop.dao.model.entity.ShopDO;
import com.meituan.catering.management.shop.dao.model.request.SearchShopDataRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

/**
 * {@link ShopBizQueryService}的核心实现
 *
 * @author dulinfeng
 */
@Service
public class ShopBizQueryServiceImpl implements ShopBizQueryService {

    @Resource
    private ShopMapper shopMapper;

    @Override
    public ShopBO findByBusinessNo(UserContextBO userContext, String businessNo) {
        ShopDO existingShopDO = shopMapper.findByBusinessNo(userContext.getTenantId(), businessNo);
        if (existingShopDO == null) {
            throw new IllegalArgumentException("无法找到businessNo：" + businessNo + " 的门店");
        }
        return ShopBOConverter.fromDO(existingShopDO);
    }

    @Override
    public PageBizResponse<ShopBO, ShopSortColumnEnum> searchForPage(UserContextBO userContext, SearchShopBizRequest request) {
        SearchShopDataRequest dataRequest = ShopDOConverter.fromBizRequest(userContext, request, findPrivilegedShopIds(userContext));
        int totalCount = shopMapper.countForPage(dataRequest.getCondition());
        PageBizResponse<ShopBO, ShopSortColumnEnum> shopPageBizResponse = new PageBizResponse<>(
                PageBizResponseConverter.fromBizRequest(request.getPagination(), totalCount),
                PageBizResponseConverter.fromBizRequest(request.getSortFields()));
        if (totalCount > 0) {
            List<ShopDO> shopDOs = shopMapper.searchForPage(dataRequest);
            shopPageBizResponse.getRecords().addAll(shopDOs.stream().map(ShopBOConverter::fromDO).collect(toList()));
        }
        return shopPageBizResponse;
    }

    @Override
    public Set<Long> findPrivilegedShopIds(UserContextBO userContext) {
        Set<Long> privilegedShopIds = new LinkedHashSet<>();
        Set<Long> subordinateIds = ImmutableSet.of(userContext.getShopId());
        while (isNotEmpty(subordinateIds)) {
            privilegedShopIds.addAll(subordinateIds);
            subordinateIds = shopMapper.findSubordinateIds(userContext.getTenantId(), subordinateIds);
        }
        return privilegedShopIds;
    }

}
