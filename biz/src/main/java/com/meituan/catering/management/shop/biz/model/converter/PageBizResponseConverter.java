package com.meituan.catering.management.shop.biz.model.converter;

import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import com.meituan.catering.management.shop.biz.model.request.PaginationBizRequest;
import com.meituan.catering.management.shop.biz.model.request.SortFieldBizRequest;
import com.meituan.catering.management.shop.biz.model.response.PaginationBizResponse;
import com.meituan.catering.management.shop.biz.model.response.SortFieldBizResponse;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * 从其他数据模型向分页BO的转换器
 *
 * @author dioufdu
 */
public abstract class PageBizResponseConverter {

    public static PaginationBizResponse fromBizRequest(PaginationBizRequest bizRequest, int totalCount) {
        if (bizRequest == null) {
            return null;
        }
        PaginationBizResponse shopPageBO = new PaginationBizResponse();
        shopPageBO.setTotalCount(totalCount);
        shopPageBO.setSize(bizRequest.getSize());
        shopPageBO.setTotalPageCount(totalCount / bizRequest.getSize() + (totalCount % bizRequest.getSize() == 0 ? 0 : 1));
        if (bizRequest.getIndex() > shopPageBO.getTotalPageCount()) {
            shopPageBO.setIndex(shopPageBO.getTotalPageCount());
        } else {
            shopPageBO.setIndex(bizRequest.getIndex());
        }
        return shopPageBO;
    }

    public static List<SortFieldBizResponse<ShopSortColumnEnum>> fromBizRequest(List<SortFieldBizRequest<ShopSortColumnEnum>> sortFields) {
        if (isEmpty(sortFields)) {
            return emptyList();
        }
        return sortFields.stream()
                .map(bizRequest -> new SortFieldBizResponse<>(bizRequest.getColumn(), bizRequest.getDirection()))
                .collect(toList());
    }
}
