package com.meituan.catering.management.shop.biz.model.request;

import com.google.common.collect.ImmutableList;
import com.meituan.catering.management.infra.model.enumeration.BusinessTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.infra.model.enumeration.ManagementTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import lombok.Data;

import java.util.List;
import java.util.Set;

import static com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum.LAST_MODIFIED_AT;
import static com.meituan.catering.management.infra.model.enumeration.SortDirectionEnum.DESC;

/**
 * 搜索门店Biz请求
 *
 * @author dulinfeng
 */
@Data
public class SearchShopBizRequest {

    /**
     * 如果前端不传排序列表，后端默认采取的排序策略
     */
    public static final List<SortFieldBizRequest<ShopSortColumnEnum>> DEFAULT_SORT_FIELDS = ImmutableList.of(
            new SortFieldBizRequest<>(LAST_MODIFIED_AT, DESC)
    );

    /**
     * 分页条件
     */
    private final PaginationBizRequest pagination;

    /**
     * 排序字段列表
     */
    private final List<SortFieldBizRequest<ShopSortColumnEnum>> sortFields;

    /**
     * 查询过滤条件
     */
    private final Condition condition;

    @Data
    public static class Condition {

        /**
         * 关键字，支持
         * <ol>
         * <li>门店名称，</li>
         * <li>门店联系方式（座机，手机，地址，联系人名）</li>
         * </ol>
         * 进行模糊搜索
         */
        private String keyword;

        /**
         * 按照指定{@link ManagementTypeEnum}进行过滤
         */
        private Set<ManagementTypeEnum> managementTypes;

        /**
         * 按照指定{@link BusinessTypeEnum}进行过滤
         */
        private Set<BusinessTypeEnum> businessTypes;

        /**
         * 按照指定{@link EnabledStatusEnum} ()}进行过滤
         */
        private Set<EnabledStatusEnum> enabledStatusSet;

    }


}