package com.meituan.catering.management.shop.api.http.model.converter;

import com.meituan.catering.management.infra.model.enumeration.DescribableEnum;
import com.meituan.catering.management.shop.api.http.model.common.DescribableEnumDTO;

/**
 * 从其他数据模型向枚举VO模型的转换器
 *
 * @author dulinfeng
 */
public abstract class EnumVOConverter {

    public static <E extends DescribableEnum> DescribableEnumDTO fromEnum(E enumInstance) {
        if (enumInstance == null) {
            return null;
        }
        return new DescribableEnumDTO(enumInstance.getCode(), enumInstance.getName());
    }
}
