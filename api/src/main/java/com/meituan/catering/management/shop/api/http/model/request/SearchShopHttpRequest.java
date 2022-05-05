package com.meituan.catering.management.shop.api.http.model.request;

import com.meituan.catering.management.infra.model.enumeration.BusinessTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.EnabledStatusEnum;
import com.meituan.catering.management.infra.model.enumeration.ManagementTypeEnum;
import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 搜索门店的Http请求
 *
 * @author dulinfeng
 */
@Data
@ApiModel("搜索门店Http请求体")
public class SearchShopHttpRequest {

    @Valid
    @ApiModelProperty("分页条件")
    private final PaginationHttpRequest pagination = new PaginationHttpRequest();

    @Valid
    @ApiModelProperty("排序字段列表")
    private final List<SortFieldHttpRequest<ShopSortColumnEnum>> sortFields = new LinkedList<>();

    @Valid
    @ApiModelProperty("查询过滤条件")
    private final Condition condition = new Condition();

    @Data
    @Valid
    @ApiModel("查询过滤条件")
    public static class Condition {

        @ApiModelProperty(value = "关键字，支持门店名称，门店联系方式（座机，手机，地址，联系人名）进行模糊搜索", example = "菜")
        private String keyword;

        /**
         * 按照指定{@link ManagementTypeEnum}进行过滤
         */
        @ApiModelProperty(value = "管理类型列表", example = "[\"DIRECT_SALES\"]")
        private Set<ManagementTypeEnum> managementTypes;

        /**
         * 按照指定{@link BusinessTypeEnum}进行过滤
         */
        @ApiModelProperty(value = "业态类型列表", example = "[\"DINNER\"]")
        private Set<BusinessTypeEnum> businessTypes;

        /**
         * 按照指定{@link EnabledStatusEnum} ()}进行过滤
         */
        @ApiModelProperty(value = "启停状态列表", example = "[\"ENABLED\"]")
        private Set<EnabledStatusEnum> enabledStatusSet;

    }


}