package com.meituan.catering.management.shop.api.http.model.converter;

import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import com.meituan.catering.management.shop.api.http.model.common.SortColumnEnumDTO;
import com.meituan.catering.management.shop.api.http.model.response.PaginationHttpResponse;
import com.meituan.catering.management.shop.api.http.model.response.SortFieldHttpResponse;
import com.meituan.catering.management.shop.biz.model.response.PaginationBizResponse;
import com.meituan.catering.management.shop.biz.model.response.SortFieldBizResponse;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * 从其他数据模型向分页VO模型的转换器
 *
 * @author dulinfeng
 */
public abstract class PageVOConverter {

    public static PaginationHttpResponse fromBizRequest(PaginationBizResponse bizResponse) {
        if (bizResponse == null) {
            return null;
        }
        PaginationHttpResponse httpResponse = new PaginationHttpResponse();
        httpResponse.setIndex(bizResponse.getIndex());
        httpResponse.setSize(bizResponse.getSize());
        httpResponse.setTotalCount(bizResponse.getTotalCount());
        httpResponse.setTotalPageCount(bizResponse.getTotalPageCount());
        return httpResponse;
    }

    public static List<SortFieldHttpResponse> fromBizRequest(List<SortFieldBizResponse<ShopSortColumnEnum>> sortFields) {
        if (isEmpty(sortFields)) {
            return emptyList();
        }
        return sortFields.stream().map(PageVOConverter::fromBizRequest).collect(toList());
    }

    private static SortFieldHttpResponse fromBizRequest(SortFieldBizResponse<ShopSortColumnEnum> bizResponse) {
        return new SortFieldHttpResponse(
                fromBizRequest(bizResponse.getColumn()),
                bizResponse.getDirection());
    }

    private static SortColumnEnumDTO fromBizRequest(ShopSortColumnEnum column) {
        return new SortColumnEnumDTO(column.getName(), column.getDescription());
    }
}
