package com.meituan.catering.management.shop.api.http.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;
import com.meituan.catering.management.infra.model.enumeration.*;
import com.meituan.catering.management.shop.api.http.model.request.*;
import com.meituan.catering.management.shop.api.http.model.response.ShopDetailHttpResponse;
import com.meituan.catering.management.shop.api.http.model.response.ShopPageHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.meituan.catering.management.shop.api.http.controller.ShopControllerTest.RequestConstant.*;
import static com.meituan.catering.management.shop.api.http.controller.ShopControllerTest.UserContextConstant.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * 门店管理的集成测试
 */
@Slf4j
@Transactional
@Rollback
@Sql({"/schema-h2.sql", "/data-h2.sql"})
public class ShopControllerTest extends BaseControllerTest {

    private static final ObjectMapper OM = new ObjectMapper();

    @Test
    public void testCreate() throws Exception {
        Date startTime = new Date();
        CreateShopHttpRequest httpRequest = buildCreateShopHttpRequest();
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post(
                "/shop");
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest);
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    Date endTime = new Date();
                    ShopDetailHttpResponse response = getHttpResponseEntity(result, ShopDetailHttpResponse.class);
                    assertCreateShopHttpResponse(startTime, endTime, response);
                });
    }

    @Test
    public void testFullyUpdate() throws Exception {
        Date startTime = new Date();
        FullyUpdateShopHttpRequest httpRequest = buildFullyUpdateShopHttpRequest();
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.put(
                "/shop/" + SaveShopConstant.UPDATING_BUSINESS_NO);
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest);
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    Date endTime = new Date();
                    ShopDetailHttpResponse response = getHttpResponseEntity(result, ShopDetailHttpResponse.class);
                    assertUpdateShopHttpResponse(startTime, endTime, response);
                });
    }

    @Test
    public void testPartialUpdate() throws Exception {
        Date startTime = new Date();
        PartialUpdateShopHttpRequest httpRequest = buildPartialUpdateShopHttpRequest();
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.patch(
                "/shop/" + SaveShopConstant.UPDATING_BUSINESS_NO);
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest);
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    Date endTime = new Date();
                    ShopDetailHttpResponse response = getHttpResponseEntity(result, ShopDetailHttpResponse.class);
                    assertUpdateShopHttpResponse(startTime, endTime, response);
                });
    }

    @Test
    public void testSearchForPage() throws Exception {
        SearchShopHttpRequest httpRequest = buildSearchShopHttpRequest();
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post(
                "/shop/searchForPage");
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest);
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    ShopPageHttpResponse response = getHttpResponseEntity(result, ShopPageHttpResponse.class);
                    assertShopPageHttpResponse(response, TOTAL_COUNT, TOTAL_PAGE_COUNT);
                });
    }

    @Test
    public void testSearchForPageWithPartialPrivilegedShop() throws Exception {
        SearchShopHttpRequest httpRequest = buildSearchShopHttpRequest();
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post(
                "/shop/searchForPage");
        postRequest
                .param("tenantId", TENANT_ID.toString())
                .param("shopId", SUBORDINATE_ID.toString())
                .param("userId", USER_ID.toString())
                .param("userName", USER_NAME);
        appendHeaders(postRequest);
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    ShopPageHttpResponse response = getHttpResponseEntity(result, ShopPageHttpResponse.class);
                    assertShopPageHttpResponse(response, 3, 1);
                });
    }

    @Test
    public void testFindByBusinessNo() throws Exception {
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.get(
                "/shop/" + SaveShopConstant.UPDATING_BUSINESS_NO);
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest);
        mvc.perform(postRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    ShopDetailHttpResponse response = getHttpResponseEntity(result, ShopDetailHttpResponse.class);
                    assertFindShopHttpResponse(response);
                });
    }

    @Test
    public void testOpenForDisabledShop() throws Exception {
        OpenShopHttpRequest httpRequest = new OpenShopHttpRequest();
        httpRequest.setVersion(1);
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post(
                "/shop/" + ChangeShopEnabledConstant.DISABLED_SHOP_BUSINESS_NO + "/open");
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest.content(JSON.toJSONString(httpRequest)));
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    ShopDetailHttpResponse response = getHttpResponseEntity(result, ShopDetailHttpResponse.class);
                    assertOpenShopHttpResponse(response);
                });
    }

    @Test
    public void testOpenForEnabledShop() throws Exception {
        OpenShopHttpRequest httpRequest = new OpenShopHttpRequest();
        httpRequest.setVersion(3);
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post(
                "/shop/" + ChangeShopEnabledConstant.ENABLED_SHOP_BUSINESS_NO + "/open");
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest.content(JSON.toJSONString(httpRequest)));
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testCloseForEnabledShop() throws Exception {
        CloseShopHttpRequest httpRequest = new CloseShopHttpRequest();
        httpRequest.setVersion(1);
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post(
                "/shop/" + ChangeShopEnabledConstant.ENABLED_SHOP_BUSINESS_NO + "/close");
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest);
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(result -> {
                    ShopDetailHttpResponse response = getHttpResponseEntity(result, ShopDetailHttpResponse.class);
                    assertCloseShopHttpResponse(response);
                });
    }

    @Test
    public void testCloseForDisabledShop() throws Exception {
        CloseShopHttpRequest httpRequest = new CloseShopHttpRequest();
        httpRequest.setVersion(1);
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post(
                "/shop/" + ChangeShopEnabledConstant.DISABLED_SHOP_BUSINESS_NO + "/close");
        appendUserContextToParams(postRequest);
        appendHeaders(postRequest);
        mvc.perform(postRequest.content(JSON.toJSONString(httpRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    private CreateShopHttpRequest buildCreateShopHttpRequest() {
        return buildSaveShopHttpRequest(new CreateShopHttpRequest());
    }

    private FullyUpdateShopHttpRequest buildFullyUpdateShopHttpRequest() {
        FullyUpdateShopHttpRequest httpRequest = buildSaveShopHttpRequest(new FullyUpdateShopHttpRequest());
        httpRequest.setVersion(2);
        httpRequest.setEnabledStatus(EnabledStatusEnum.ENABLED);
        return httpRequest;
    }

    private PartialUpdateShopHttpRequest buildPartialUpdateShopHttpRequest() {
        PartialUpdateShopHttpRequest httpRequest = buildSaveShopHttpRequest(new PartialUpdateShopHttpRequest());
        httpRequest.setVersion(2);
        return httpRequest;
    }

    private <T extends SaveShopHttpRequest> T buildSaveShopHttpRequest(T httpRequest) {
        httpRequest.setSuperiorId(SaveShopConstant.SUPERIOR_ID);
        httpRequest.setName(SaveShopConstant.NAME);
        httpRequest.setBusinessType(SaveShopConstant.BUSINESS_TYPE);
        httpRequest.getContact().setTelephone(SaveShopConstant.CONTACT_TELEPHONE);
        httpRequest.getContact().setCellphone(SaveShopConstant.CONTACT_CELLPHONE);
        httpRequest.getContact().setName(SaveShopConstant.CONTACT_NAME);
        httpRequest.getContact().setAddress(SaveShopConstant.CONTACT_ADDRESS);
        httpRequest.setManagementType(SaveShopConstant.MANAGEMENT_TYPE);
        httpRequest.getOpeningHours().setStart(SaveShopConstant.OPENING_HOURS_START);
        httpRequest.getOpeningHours().setEnd(SaveShopConstant.OPENING_HOURS_END);
        httpRequest.setBusinessArea(SaveShopConstant.BUSINESS_AREA);
        httpRequest.setComment(SaveShopConstant.COMMENT);
        return httpRequest;
    }

    private SearchShopHttpRequest buildSearchShopHttpRequest() {
        SearchShopHttpRequest httpRequest = new SearchShopHttpRequest();
        // Page
        httpRequest.getPagination().setIndex(PAGE_INDEX);
        httpRequest.getPagination().setSize(PAGE_SIZE);
        // Condition
        SearchShopHttpRequest.Condition condition = httpRequest.getCondition();
        condition.setKeyword("37");
        condition.setManagementTypes(ImmutableSet.of(ManagementTypeEnum.DIRECT_SALES));
        condition.setEnabledStatusSet(ImmutableSet.of(EnabledStatusEnum.ENABLED));
        // Sort Fields
        httpRequest.getSortFields().add(new SortFieldHttpRequest<>(ShopSortColumnEnum.BUSINESS_NO, SortDirectionEnum.ASC));
        return httpRequest;
    }

    private void appendUserContextToParams(MockHttpServletRequestBuilder postRequest) {
        postRequest
                .param("tenantId", TENANT_ID.toString())
                .param("shopId", SHOP_ID.toString())
                .param("userId", USER_ID.toString())
                .param("userName", USER_NAME);
    }

    private void appendHeaders(MockHttpServletRequestBuilder postRequest) {
        postRequest
                .contentType(APPLICATION_JSON_VALUE)
                .characterEncoding(UTF_8.name())
                .accept(APPLICATION_JSON);
    }

    private <T> T getHttpResponseEntity(MvcResult result, Class<T> entityType) throws Exception {
        result.getResponse().setCharacterEncoding("UTF-8");
        return OM.readValue(
                result.getResponse().getContentAsString(),
                entityType);
    }

    private void assertCreateShopHttpResponse(Date startTime, Date endTime, ShopDetailHttpResponse response) {
        assertSaveShopHttpResponse(response);
        assertEquals(USER_ID, response.getAuditing().getCreatedBy());
        assertTrue(response.getAuditing().getCreatedAt().after(startTime));
        assertTrue(response.getAuditing().getCreatedAt().before(endTime));
        assertNull(response.getAuditing().getLastModifiedAt());
        assertNull(response.getAuditing().getLastModifiedBy());
        assertEquals((Integer) 1, response.getVersion());
    }

    private void assertUpdateShopHttpResponse(Date startTime, Date endTime, ShopDetailHttpResponse response) {
        assertSaveShopHttpResponse(response);
        assertEquals(SaveShopConstant.UPDATING_BUSINESS_NO, response.getBusinessNo());
        assertEquals(USER_ID, response.getAuditing().getCreatedBy());
        assertTrue(response.getAuditing().getCreatedAt().before(startTime));
        assertEquals(USER_ID, response.getAuditing().getLastModifiedBy());
        assertTrue(response.getAuditing().getLastModifiedAt().after(startTime));
        assertTrue(response.getAuditing().getLastModifiedAt().before(endTime));
        assertEquals((Integer) 3, response.getVersion());
    }

    private void assertSaveShopHttpResponse(ShopDetailHttpResponse response) {
        assertNotNull(response);
        assertTrue(response.getId() > 0L);
        assertEquals(TENANT_ID, response.getTenantId());
        assertTrue(NumberUtils.isDigits(response.getBusinessNo()));
        assertEquals(10, StringUtils.length(response.getBusinessNo()));
        assertEquals(SaveShopConstant.NAME, response.getName());
        assertEquals((Integer) SaveShopConstant.BUSINESS_TYPE.getCode(), response.getBusinessType().getCode());
        assertEquals(SaveShopConstant.BUSINESS_TYPE.getName(), response.getBusinessType().getName());
        assertEquals(SaveShopConstant.CONTACT_TELEPHONE, response.getContact().getTelephone());
        assertEquals(SaveShopConstant.CONTACT_CELLPHONE, response.getContact().getCellphone());
        assertEquals(SaveShopConstant.CONTACT_NAME, response.getContact().getName());
        assertEquals(SaveShopConstant.CONTACT_ADDRESS, response.getContact().getAddress());
        assertEquals((Integer) SaveShopConstant.MANAGEMENT_TYPE.getCode(), response.getManagementType().getCode());
        assertEquals(SaveShopConstant.MANAGEMENT_TYPE.getName(), response.getManagementType().getName());
        assertEquals(SaveShopConstant.OPENING_HOURS_START, response.getOpeningHours().getStart());
        assertEquals(SaveShopConstant.OPENING_HOURS_END, response.getOpeningHours().getEnd());
        assertEquals(SaveShopConstant.BUSINESS_AREA, response.getBusinessArea());
        assertEquals(SaveShopConstant.COMMENT, response.getComment());
        assertEquals(EnabledStatusEnum.ENABLED, response.getEnabled());
    }

    private void assertFindShopHttpResponse(ShopDetailHttpResponse response) {
        assertNotNull(response);
        assertEquals(FindShopConstant.ID, response.getId());
        assertEquals(TENANT_ID, response.getTenantId());
        assertEquals(FindShopConstant.BUSINESS_NO, response.getBusinessNo());
        assertEquals(FindShopConstant.NAME, response.getName());
        assertEquals((Integer) FindShopConstant.BUSINESS_TYPE.getCode(), response.getBusinessType().getCode());
        assertEquals(FindShopConstant.BUSINESS_TYPE.getName(), response.getBusinessType().getName());
        assertEquals(FindShopConstant.CONTACT_TELEPHONE, response.getContact().getTelephone());
        assertEquals(FindShopConstant.CONTACT_CELLPHONE, response.getContact().getCellphone());
        assertEquals(FindShopConstant.CONTACT_NAME, response.getContact().getName());
        assertEquals(FindShopConstant.CONTACT_ADDRESS, response.getContact().getAddress());
        assertEquals((Integer) FindShopConstant.MANAGEMENT_TYPE.getCode(), response.getManagementType().getCode());
        assertEquals(FindShopConstant.MANAGEMENT_TYPE.getName(), response.getManagementType().getName());
        assertEquals(FindShopConstant.OPENING_HOURS_START, response.getOpeningHours().getStart());
        assertEquals(FindShopConstant.OPENING_HOURS_END, response.getOpeningHours().getEnd());
        assertEquals(FindShopConstant.BUSINESS_AREA, response.getBusinessArea());
        assertEquals(FindShopConstant.COMMENT, response.getComment());
        assertEquals(EnabledStatusEnum.ENABLED, response.getEnabled());
        assertEquals(FindShopConstant.VERSION, response.getVersion());
        assertEquals(FindShopConstant.AUDITING_CREATED_BY, response.getAuditing().getCreatedBy());
        assertEquals(FindShopConstant.AUDITING_CREATED_AT, (Long) response.getAuditing().getCreatedAt().getTime());
        assertEquals(FindShopConstant.AUDITING_LAST_MODIFIED_BY, response.getAuditing().getLastModifiedBy());
        assertEquals(FindShopConstant.AUDITING_LAST_MODIFIED_AT, (Long) response.getAuditing().getLastModifiedAt().getTime());
        assertEquals((Integer) 2, response.getVersion());
    }

    private void assertShopPageHttpResponse(ShopPageHttpResponse response, Integer totalCount, Integer totalPageCount) {
        assertNotNull(response);
        assertEquals(PAGE_INDEX, response.getPagination().getIndex());
        assertEquals(PAGE_SIZE, response.getPagination().getSize());
        assertEquals(totalCount, response.getPagination().getTotalCount());
        assertEquals(totalPageCount, response.getPagination().getTotalPageCount());
        List<ShopPageHttpResponse.Record> records = response.getRecords();
        assertNotNull(records);
        int recordSize = records.size();
        assertEquals(PAGE_SIZE, (Integer) recordSize);
        records.forEach(record -> {
            assertNotNull(record);
            assertNotNull(record.getId());
            assertNotNull(record.getTenantId());
            assertTrue(isNotBlank(record.getBusinessNo()));
            assertTrue(isNotBlank(record.getName()));
            assertNotNull(record.getBusinessType().getCode());
            assertNotNull(record.getBusinessType().getName());
            assertNotNull(record.getContact());
            assertNotNull(record.getContact().getTelephone());
            assertNotNull(record.getContact().getCellphone());
            assertNotNull(record.getContact().getName());
            assertNotNull(record.getContact().getAddress());
            assertNotNull(record.getManagementType().getCode());
            assertNotNull(record.getManagementType().getName());
            assertNotNull(record.getOpeningHours().getStart());
            assertNotNull(record.getOpeningHours().getEnd());
            assertNotNull(record.getBusinessArea());
            assertNotNull(record.getComment());
            assertNotNull(record.getEnabled());
            assertNotNull(record.getVersion());
        });
        for (int i = 0; i < recordSize - 1; i++) {
            assertTrue(Long.parseLong(records.get(i).getBusinessNo()) < Long.parseLong(records.get(i + 1).getBusinessNo()));
        }
    }

    private void assertOpenShopHttpResponse(ShopDetailHttpResponse response) {
        assertNotNull(response);
        assertEquals(EnabledStatusEnum.ENABLED, response.getEnabled());
    }

    private void assertCloseShopHttpResponse(ShopDetailHttpResponse response) {
        assertNotNull(response);
        assertEquals(EnabledStatusEnum.DISABLED, response.getEnabled());
    }


    interface RequestConstant {

        Integer PAGE_INDEX = 1;

        Integer PAGE_SIZE = 3;

        Integer TOTAL_PAGE_COUNT = 2;

        Integer TOTAL_COUNT = 4;
    }

    interface UserContextConstant {

        Long TENANT_ID = 500L;

        Long SHOP_ID = 100L;

        Long SUBORDINATE_ID = 103L;

        Long USER_ID = 11000L;

        String USER_NAME = "Jim Lee";
    }

    interface SaveShopConstant {

        Long SUPERIOR_ID = 100L;

        String NAME = "江小龙火锅";

        String UPDATING_BUSINESS_NO = "1234567890";

        BusinessTypeEnum BUSINESS_TYPE = BusinessTypeEnum.HOT_POT;

        String CONTACT_TELEPHONE = "021-83734613";

        String CONTACT_CELLPHONE = "13882618275";

        String CONTACT_NAME = "江小龙";

        String CONTACT_ADDRESS = "楚西市耀辉路389号";

        ManagementTypeEnum MANAGEMENT_TYPE = ManagementTypeEnum.DIRECT_SALES;

        String OPENING_HOURS_START = "11:00";

        String OPENING_HOURS_END = "23:00";

        String BUSINESS_AREA = "120平米";

        String COMMENT = "当地出名的重庆火锅";
    }

    interface FindShopConstant {

        Long ID = 100L;

        String NAME = "海棠川菜馆";

        String BUSINESS_NO = "1234567890";

        BusinessTypeEnum BUSINESS_TYPE = BusinessTypeEnum.DINNER;

        String CONTACT_TELEPHONE = "028-02938102";

        String CONTACT_CELLPHONE = "15827617283";

        String CONTACT_NAME = "刘先生";

        String CONTACT_ADDRESS = "汉东市昌平路192号";

        ManagementTypeEnum MANAGEMENT_TYPE = ManagementTypeEnum.DIRECT_SALES;

        String OPENING_HOURS_START = "10:00";

        String OPENING_HOURS_END = "22:00";

        String BUSINESS_AREA = "80平米";

        String COMMENT = "主营经典川菜系列";

        Integer VERSION = 2;

        Long AUDITING_CREATED_BY = 11000L;

        Long AUDITING_CREATED_AT = 1627788720000L;

        Long AUDITING_LAST_MODIFIED_BY = 11000L;

        Long AUDITING_LAST_MODIFIED_AT = 1627791120000L;
    }

    interface ChangeShopEnabledConstant {

        String ENABLED_SHOP_BUSINESS_NO = "1234567895";

        String DISABLED_SHOP_BUSINESS_NO = "1234567898";

    }
}
