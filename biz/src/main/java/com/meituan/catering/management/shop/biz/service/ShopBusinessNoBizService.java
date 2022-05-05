package com.meituan.catering.management.shop.biz.service;

/**
 * 门店商户号服务
 *
 * @author dulinfeng
 */
public interface ShopBusinessNoBizService {

    /**
     * 生成新的商户号
     *
     * @return 新的商户号
     */
    String generate();
}
