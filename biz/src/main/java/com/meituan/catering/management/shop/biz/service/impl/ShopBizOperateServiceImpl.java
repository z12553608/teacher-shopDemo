package com.meituan.catering.management.shop.biz.service.impl;

import com.meituan.catering.management.shop.biz.model.bo.ShopBO;
import com.meituan.catering.management.shop.biz.model.common.UserContextBO;
import com.meituan.catering.management.shop.biz.model.converter.ShopBOConverter;
import com.meituan.catering.management.shop.biz.model.converter.ShopDOConverter;
import com.meituan.catering.management.shop.biz.model.request.UpdateShopStatusBizRequest;
import com.meituan.catering.management.shop.biz.service.ShopBizOperateService;
import com.meituan.catering.management.shop.biz.service.ShopBizQueryService;
import com.meituan.catering.management.shop.biz.service.ShopBusinessNoBizService;
import com.meituan.catering.management.shop.dao.mapper.ShopMapper;
import com.meituan.catering.management.shop.dao.model.entity.ShopDO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Set;

/**
 * {@link ShopBizOperateService}的核心实现
 *
 * @author dulinfeng
 */
@Service
public class ShopBizOperateServiceImpl implements ShopBizOperateService {

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private ShopBusinessNoBizService shopBusinessNoBizService;

    @Resource
    private ShopBizQueryService shopBizQueryService;

    @Resource
    private ShopMapper shopMapper;

    @Override
    public ShopBO create(UserContextBO userContext, ShopBO creatingShopBO) {
        String businessNo = shopBusinessNoBizService.generate();
        creatingShopBO.setBusinessNo(businessNo);
        creatingShopBO.setVersion(1);
        checkShopPrivilege(userContext, creatingShopBO);
        ShopDO creatingShopDO = ShopDOConverter.fromBO(creatingShopBO);
        ShopDO createdShopDO = transactionTemplate.execute(transactionStatus -> {
            int insertRowCount = shopMapper.insert(creatingShopDO);
            if (insertRowCount != 1) {
                transactionStatus.setRollbackOnly();
                throw new IllegalStateException("保存门店失败");
            }
            return shopMapper.findByBusinessNo(userContext.getTenantId(), businessNo);
        });
        return ShopBOConverter.fromDO(createdShopDO);
    }

    @Override
    public ShopBO fullyUpdate(UserContextBO userContext, String businessNo, ShopBO updatingShopBO) {
        checkShopPrivilege(userContext, updatingShopBO);
        ShopDO updatingShopDO = ShopDOConverter.fromBO(updatingShopBO);
        ShopDO updatedShopDO = transactionTemplate.execute(transactionStatus -> {
            updatingShopDO.setBusinessNo(businessNo);
            int insertRowCount = shopMapper.fullyUpdateByBusinessNo(updatingShopDO);
            if (insertRowCount != 1) {
                transactionStatus.setRollbackOnly();
                throw new IllegalStateException("保存门店失败");
            }
            return shopMapper.findByBusinessNo(userContext.getTenantId(), businessNo);
        });
        return ShopBOConverter.fromDO(updatedShopDO);
    }

    @Override
    public ShopBO partialUpdate(UserContextBO userContext, String businessNo, ShopBO updatingShopBO) {
        checkShopPrivilege(userContext, updatingShopBO);
        ShopDO updatingShopDO = ShopDOConverter.fromBO(updatingShopBO);
        ShopDO updatedShopDO = transactionTemplate.execute(transactionStatus -> {
            updatingShopDO.setBusinessNo(businessNo);
            int insertRowCount = shopMapper.partialUpdateByBusinessNo(updatingShopDO);
            if (insertRowCount != 1) {
                transactionStatus.setRollbackOnly();
                throw new IllegalStateException("保存门店失败");
            }
            return shopMapper.findByBusinessNo(userContext.getTenantId(), businessNo);
        });
        return ShopBOConverter.fromDO(updatedShopDO);
    }

    @Override
    public ShopBO updateShopStatus(UserContextBO userContext, String businessNo, UpdateShopStatusBizRequest request) {
        ShopBO shopBO = shopBizQueryService.findByBusinessNo(userContext, businessNo);
        if (Objects.equals(request.getEnabledStatus(), shopBO.getEnabled())) {
            throw new IllegalStateException(MessageFormat.format("该门店状态已经处于[{0}]状态", shopBO.getEnabled().getName()));
        }
        ShopDO updatedShopDO = transactionTemplate.execute(transactionStatus -> {
            int insertRowCount = shopMapper.changeEnabledStatusById(
                    userContext.getTenantId(),
                    shopBO.getId(),
                    request.getEnabledStatus().getCode(),
                    request.getVersion());
            if (insertRowCount != 1) {
                transactionStatus.setRollbackOnly();
                throw new IllegalStateException("更新门店状态失败");
            }
            return shopMapper.findByBusinessNo(userContext.getTenantId(), businessNo);
        });
        return ShopBOConverter.fromDO(updatedShopDO);
    }

    private void checkShopPrivilege(UserContextBO userContext, ShopBO creatingShopBO) {
        if (creatingShopBO.getSuperiorId() == null) {
            creatingShopBO.setSuperiorId(userContext.getShopId());
        } else {
            checkSuperiorPrivilege(userContext, creatingShopBO.getSuperiorId());
        }
    }

    private void checkSuperiorPrivilege(UserContextBO userContext, Long creatingSuperiorId) {
        Set<Long> privilegedShopIds = shopBizQueryService.findPrivilegedShopIds(userContext);
        if (!privilegedShopIds.contains(creatingSuperiorId)) {
            throw new IllegalArgumentException("您无权操作该门店");
        }
    }
}
