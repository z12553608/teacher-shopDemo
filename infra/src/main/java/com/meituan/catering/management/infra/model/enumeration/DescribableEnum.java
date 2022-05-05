package com.meituan.catering.management.infra.model.enumeration;

import java.util.Objects;
import java.util.function.Function;

/**
 * 可自描述的枚举类型的基类
 *
 * @author dulinfeng
 */
public interface DescribableEnum {

    /**
     * @return 获取数字型的代码
     */
    int getCode();

    /**
     * @return 获取该枚举的名称
     */
    String getName();

    static <T extends DescribableEnum> T getByCode(Class<T> enumClazz, Integer code) {
        for (T candidate : enumClazz.getEnumConstants()) {
            if (Objects.equals(candidate.getCode(), code)) {
                return candidate;
            }
        }
        return null;
    }

    static <T extends DescribableEnum, P> T getByProperty(Class<T> enumClazz, Function<T, P> property, P value) {
        for (T candidate : enumClazz.getEnumConstants()) {
            if (Objects.equals(property.apply(candidate), value)) {
                return candidate;
            }
        }
        return null;
    }
}
