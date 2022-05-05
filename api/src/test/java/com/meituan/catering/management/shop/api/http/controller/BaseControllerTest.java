package com.meituan.catering.management.shop.api.http.controller;

import com.meituan.catering.management.CateringManagementApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Web集成测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CateringManagementApplication.class})
@ActiveProfiles({"test"})
@WebAppConfiguration
public abstract class BaseControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); //构造MockMvc
    }
}
