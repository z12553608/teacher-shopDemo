package com.meituan.catering.management.shop.api.http.model.converter;

import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import com.meituan.catering.management.shop.api.http.model.request.PaginationHttpRequest;
import com.meituan.catering.management.shop.api.http.model.request.SortFieldHttpRequest;
import com.meituan.catering.management.shop.biz.model.request.PaginationBizRequest;
import com.meituan.catering.management.shop.biz.model.request.SortFieldBizRequest;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

/**
 * 从其他数据模型向公共BO的转换器
 *
 * @author dulinfeng
 */
public abstract class PageBizRequestConverter {

    public static PaginationBizRequest fromHttpRequest(PaginationHttpRequest httpRequest) {
        if (httpRequest == null) {
            return null;
        }
        return new PaginationBizRequest(
                httpRequest.getIndex(),
                httpRequest.getSize()
        );
    }

    public static List<SortFieldBizRequest<ShopSortColumnEnum>> fromHttpRequest(List<SortFieldHttpRequest<ShopSortColumnEnum>> httpRequests) {
        if (isEmpty(httpRequests)) {
            return emptyList();
        }
        return httpRequests.stream()
                .map(bizRequest -> new SortFieldBizRequest<>(bizRequest.getColumn(), bizRequest.getDirection()))
                .collect(toList());
    }

}
