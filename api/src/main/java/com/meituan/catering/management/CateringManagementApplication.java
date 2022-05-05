package com.meituan.catering.management;

import com.meituan.catering.management.shop.dao.mapper.BaseMapper;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author dulinfeng
 */
@SpringBootApplication
@MapperScan(basePackageClasses = BaseMapper.class)
@EnableTransactionManagement
@EnableSwagger2Doc
public class CateringManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CateringManagementApplication.class, args);
    }

}
