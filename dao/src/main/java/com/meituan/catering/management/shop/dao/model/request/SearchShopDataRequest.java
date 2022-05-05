package com.meituan.catering.management.shop.dao.model.request;

import com.meituan.catering.management.infra.model.enumeration.BusinessTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.infra.model.enumeration.ManagementTypeEnum;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * 搜索门店的数据请求
 *
 * @author dulinfeng
 */
@Data
public class SearchShopDataRequest {

    /**
     * 跳过多少条记录
     */
    private final PaginationDataRequest pagination;

    /**
     * 排序字段
     */
    private final List<SortFieldDataRequest> sortFields;

    /**
     * 搜索过滤条件
     */
    private final Condition condition;

    /**
     * 搜索过滤条件
     */
    @Data
    public static class Condition {

        /**
         * 租户ID
         */
        private Long tenantId;

        /**
         * 当前登录用户所在的门店如果是总店，则可以覆盖管理下属的的门店列表
         */
        private Set<Long> privilegedShopIds;

        /**
         * 是否包含关键字搜索
         */
        private Boolean hasKeywords;

        /**
         * 门店名（模糊匹配）
         */
        private String name;

        /**
         * 联系人座机号码（模糊匹配）
         */
        private String contactTelephone;

        /**
         * 联系人座手机号码（模糊匹配）
         */
        private String contactCellphone;

        /**
         * 联系人姓名（模糊匹配）
         */
        private String contactName;

        /**
         * 联系人地址（模糊匹配）
         */
        private String contactAddress;

        /**
         * 管理类型的Code
         *
         * @see ManagementTypeEnum#getCode()
         */
        private Set<Integer> managementTypeCodes;

        /**
         * 业态类型
         *
         * @see BusinessTypeEnum#getCode()
         */
        private Set<Integer> businessTypeCodes;

        /**
         * 启停状态
         *
         * @see EnabledStatusEnum#getCode()
         */
        private Set<Integer> enabledStatusCodeSet;

    }

}
