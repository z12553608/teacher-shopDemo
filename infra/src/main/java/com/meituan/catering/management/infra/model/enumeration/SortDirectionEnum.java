package com.meituan.catering.management.infra.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 排序方向
 *
 * @author dulinfeng
 */
@Getter
@RequiredArgsConstructor
public enum SortDirectionEnum implements DescribableEnum {

    /**
     * 升序
     */
    ASC(10, "升序", "asc"),

    /**
     * 降序
     */
    DESC(20, "降序", "desc"),


    ;

    private final int code;

    private final String name;

    private final String sql;
}
