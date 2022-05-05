package com.meituan.catering.management.shop.dao.mapper;

import com.meituan.catering.management.shop.dao.model.entity.ShopDO;
import com.meituan.catering.management.shop.dao.model.request.SearchShopDataRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * 门店对应的MyBatis Mapper
 *
 * @author dulinfeng
 */
@Mapper
public interface ShopMapper extends BaseMapper {

    /**
     * 根据租户ID找到门店DO实例
     *
     * @param tenantId 租户ID
     * @return 该租户下的所有门店DO实例
     */
    List<ShopDO> findAll(Long tenantId);

    /**
     * 根据物理ID找到门店DO实例
     *
     * @param tenantId 租户ID
     * @param id       物理ID
     * @return 门店DO实例
     */
    ShopDO findById(Long tenantId, Long id);

    /**
     * 根据商户号找到门店DO实例
     *
     * @param tenantId   租户ID
     * @param businessNo 商户号
     * @return 门店DO实例
     */
    ShopDO findByBusinessNo(Long tenantId, String businessNo);

    /**
     * 查询符合指定商户号的数量有多少
     *
     * @param businessNo 商户号
     * @return 符合指定商户号的数量有多少
     */
    int countByBusinessNo(String businessNo);

    /**
     * 为分页查询计算符合查询条件的总条目数
     *
     * @param condition 查询过滤条件
     * @return 总条目数
     */
    int countForPage(SearchShopDataRequest.Condition condition);

    /**
     * 为分页查询符合当前分页码的门店列表
     *
     * @param dataRequest 查询数据请求
     * @return 当前分页码的门店列表
     */
    List<ShopDO> searchForPage(SearchShopDataRequest dataRequest);

    /**
     * 插入新的门店DO实例
     *
     * @param shopDO 新的门店DO实例
     * @return 插入条数
     */
    int insert(ShopDO shopDO);

    /**
     * 根据商户号全量更新已有的门店DO实例
     *
     * @param updatingShopDO 需要更新的门店DO实例（包含门店所有的字段）
     * @return 更新条数
     */
    int fullyUpdateByBusinessNo(ShopDO updatingShopDO);

    /**
     * 根据商户号部分更新已有的门店DO实例
     *
     * @param updatingShopDO 需要更新的门店DO实例（只包含待更新的字段）
     * @return 更新条数
     */
    int partialUpdateByBusinessNo(ShopDO updatingShopDO);

    /**
     * 根据物理ID修改门店启用状态
     *
     * @param tenantId 租户ID
     * @param id       物理ID
     * @param enabled  启用-1，停用-2
     * @param version  乐观锁版本号
     * @return 更新条数
     */
    int changeEnabledStatusById(Long tenantId, Long id, Integer enabled, Integer version);

    /**
     * 根据上下级门店ID关系查找下级门店ID集合
     *
     * @param superiorIds 上级门店ID集合
     * @return 下级门店ID集合
     */
    Set<Long> findSubordinateIds(Long tenantId, Set<Long> superiorIds);
}
