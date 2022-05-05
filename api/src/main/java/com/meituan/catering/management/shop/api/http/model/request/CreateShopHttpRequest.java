package com.meituan.catering.management.shop.api.http.model.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 创建门店的Http请求
 *
 * @author dulinfeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel("创建门店Http请求体")
public class CreateShopHttpRequest extends SaveShopHttpRequest {

}