package com.meituan.catering.management.shop.biz.service.impl;

import com.meituan.catering.management.shop.biz.service.ShopBusinessNoBizService;
import com.meituan.catering.management.shop.dao.mapper.ShopMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * {@link ShopBusinessNoBizService}的核心实现
 *
 * @author dulinfeng
 */
@Service
public class ShopBusinessNoServiceImpl implements ShopBusinessNoBizService {

    private static final int MAX_RETRY_COUNT = 10;

    @Resource
    private ShopMapper shopMapper;

    @Override
    public String generate() {
        int index = 0;
        while (index++ < MAX_RETRY_COUNT) {
            // 这里用一个简单随机数生成算法，现实情况是会调用专门的类似于snowflake发号器服务
            String businessNo = RandomStringUtils.randomNumeric(10);
            boolean hasConflict = shopMapper.countByBusinessNo(businessNo) > 0;
            if (!hasConflict) {
                return businessNo;
            }
        }
        throw new IllegalStateException("经过" + MAX_RETRY_COUNT + "次随机选取也无法获取不重复的商户号");
    }
}
