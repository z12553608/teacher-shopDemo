package com.meituan.catering.management.shop.api.http.controller;

import com.meituan.catering.management.infra.model.enumeration.ShopSortColumnEnum;
import com.meituan.catering.management.shop.api.http.model.converter.ShopBOConverter;
import com.meituan.catering.management.shop.api.http.model.converter.ShopVOConverter;
import com.meituan.catering.management.shop.api.http.model.converter.UserContextBOConverter;
import com.meituan.catering.management.shop.api.http.model.request.*;
import com.meituan.catering.management.shop.api.http.model.response.ShopDetailHttpResponse;
import com.meituan.catering.management.shop.api.http.model.response.ShopPageHttpResponse;
import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.biz.model.response.PageBizResponse;
import com.meituan.catering.management.shop.biz.service.ShopBizOperateService;
import com.meituan.catering.management.shop.biz.service.ShopBizQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Digits;

/**
 * 门店管理HttpAPI服务
 *
 * @author dioufdu
 */
@Api(tags = {"门店管理HttpAPI服务"})
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private ShopBizOperateService shopBizOperateService;

    @Resource
    private ShopBizQueryService shopBizQueryService;

    @ApiOperation("分页搜索")
    @PostMapping("/searchForPage")
    public ShopPageHttpResponse searchForPage(
            @ApiParam("用户上下文") @Valid UserContextHttpRequest userContext,
            @ApiParam("搜索请求体") @Valid @RequestBody SearchShopHttpRequest request) {
        PageBizResponse<ShopBO, ShopSortColumnEnum> shopPageBizResponse = shopBizQueryService.searchForPage(
                UserContextBOConverter.fromHttpRequest(userContext),
                ShopBOConverter.fromHttpRequest(request));
        return ShopVOConverter.fromPageBO(shopPageBizResponse);
    }

    @ApiOperation("按照业务号查看门店详情")
    @GetMapping("/{businessNo}")
    public ShopDetailHttpResponse findByBusinessNo(
            @ApiParam("用户上下文") @Valid UserContextHttpRequest userContext,
            @ApiParam("门店业务号") @Valid @Digits(integer = 10, fraction = 0) @PathVariable String businessNo) {
        ShopBO shopBO = shopBizQueryService.findByBusinessNo(
                UserContextBOConverter.fromHttpRequest(userContext),
                businessNo);
        return ShopVOConverter.fromBO(shopBO);
    }


    @ApiOperation("创建新门店")
    @PostMapping
    public ShopDetailHttpResponse create(
            @ApiParam("用户上下文") @Valid UserContextHttpRequest userContext,
            @ApiParam("门店信息") @Valid @RequestBody CreateShopHttpRequest request) {
        ShopBO shopBO = shopBizOperateService.create(
                UserContextBOConverter.fromHttpRequest(userContext),
                ShopBOConverter.fromHttpRequest(userContext, request));
        return ShopVOConverter.fromBO(shopBO);
    }

    @ApiOperation("按照业务号全量更新门店信息（需要传全部字段）")
    @PutMapping("/{businessNo}")
    public ShopDetailHttpResponse fullyUpdate(
            @ApiParam("用户上下文") @Valid UserContextHttpRequest userContext,
            @ApiParam("门店业务号") @PathVariable String businessNo,
            @ApiParam("门店信息") @Valid @RequestBody FullyUpdateShopHttpRequest request) {
        ShopBO shopBO = shopBizOperateService.fullyUpdate(
                UserContextBOConverter.fromHttpRequest(userContext),
                businessNo,
                ShopBOConverter.fromHttpRequest(userContext, request));
        return ShopVOConverter.fromBO(shopBO);
    }

    @ApiOperation("按照业务号部分更新门店信息（只传需要修改的字段）")
    @PatchMapping("/{businessNo}")
    public ShopDetailHttpResponse partialUpdate(
            @ApiParam("用户上下文") @Valid UserContextHttpRequest userContext,
            @ApiParam("门店业务号") @PathVariable String businessNo,
            @ApiParam("门店信息") @Valid @RequestBody PartialUpdateShopHttpRequest request) {
        ShopBO shopBO = shopBizOperateService.partialUpdate(
                UserContextBOConverter.fromHttpRequest(userContext),
                businessNo,
                ShopBOConverter.fromHttpRequest(userContext, request));
        return ShopVOConverter.fromBO(shopBO);
    }


    @ApiOperation("按照业务号启用一家门店")
    @PostMapping("/{businessNo}/open")
    public ShopDetailHttpResponse open(
            @ApiParam("用户上下文") @Valid UserContextHttpRequest userContext,
            @ApiParam("门店业务号") @PathVariable String businessNo,
            @ApiParam("启用请求体") @Valid @RequestBody OpenShopHttpRequest request) {
        ShopBO shopBO = shopBizOperateService.updateShopStatus(
                UserContextBOConverter.fromHttpRequest(userContext),
                businessNo,
                ShopBOConverter.fromHttpRequest(request));
        return ShopVOConverter.fromBO(shopBO);
    }

    @ApiOperation("按照业务号关闭一家门店")
    @PostMapping("/{businessNo}/close")
    public ShopDetailHttpResponse close(
            @ApiParam("用户上下文") @Valid UserContextHttpRequest userContext,
            @ApiParam("门店业务号") @PathVariable String businessNo,
            @ApiParam("关闭请求体") @Valid @RequestBody CloseShopHttpRequest request) {
        ShopBO shopBO = shopBizOperateService.updateShopStatus(
                UserContextBOConverter.fromHttpRequest(userContext),
                businessNo,
                ShopBOConverter.fromHttpRequest(request));
        return ShopVOConverter.fromBO(shopBO);
    }

}
