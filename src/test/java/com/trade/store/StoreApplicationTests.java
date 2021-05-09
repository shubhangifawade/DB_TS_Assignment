package com.trade.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trade.store.common.CommonConstants;
import com.trade.store.model.entity.TradeStore;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.trade.store.common.CommonConstants.ADD_TSTORE_POST_URL;
import static com.trade.store.common.CommonConstants.DATE_TIME_FORMATTER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = StoreApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@Rollback(true)
@Transactional
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoreApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void _0_AddTrade() throws Exception {

        String todaysDate = LocalDate.now().format(CommonConstants.DATE_TIME_FORMATTER);
        LocalDate maturityDate = LocalDate.parse("20/05/2020", DATE_TIME_FORMATTER);
        TradeStore tradeStore = new TradeStore();
        tradeStore.setTradeId("T1");
        tradeStore.setVersion(1);
        tradeStore.setCounterPartyId("CP-1");
        tradeStore.setBookId("B1");
        tradeStore.setMaturityDate(maturityDate);
        tradeStore.setCreatedDate(LocalDate.parse(todaysDate, DATE_TIME_FORMATTER));
        tradeStore.setExpired("N");

        String requestBodyStr = objectMapper.writeValueAsString(tradeStore);
        MockHttpServletRequestBuilder request = post(ADD_TSTORE_POST_URL);
        request.content(requestBodyStr);
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        log.info("Status : " + mvcResult.getResponse().getStatus());
        log.info("Response : " + mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void _1_LowerVersion() throws Exception {

        String todaysDate = LocalDate.now().format(CommonConstants.DATE_TIME_FORMATTER);
        LocalDate maturityDate = LocalDate.parse("20/05/2020", DATE_TIME_FORMATTER);
        TradeStore tradeStore = new TradeStore();
        tradeStore.setTradeId("T1");
        tradeStore.setVersion(0);
        tradeStore.setCounterPartyId("CP-1");
        tradeStore.setBookId("B1");
        tradeStore.setMaturityDate(maturityDate);
        tradeStore.setCreatedDate(LocalDate.parse(todaysDate, DATE_TIME_FORMATTER));
        tradeStore.setExpired("N");

        String requestBodyStr = objectMapper.writeValueAsString(tradeStore);
        MockHttpServletRequestBuilder request = post(ADD_TSTORE_POST_URL);
        request.content(requestBodyStr);
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        log.info("Status : " + mvcResult.getResponse().getStatus());
        log.info("Response : " + mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }


    @Test
    public void _2_SameVersion() throws Exception {
        String todaysDate = LocalDate.now().format(CommonConstants.DATE_TIME_FORMATTER);
        LocalDate maturityDate = LocalDate.parse("20/05/2020", DATE_TIME_FORMATTER);
        TradeStore tradeStore = new TradeStore();
        tradeStore.setTradeId("T1");
        tradeStore.setVersion(1);
        tradeStore.setCounterPartyId("CP-1");
        tradeStore.setBookId("B1");
        tradeStore.setMaturityDate(maturityDate);
        tradeStore.setCreatedDate(LocalDate.parse(todaysDate, DATE_TIME_FORMATTER));
        tradeStore.setExpired("N");

        String requestBodyStr = objectMapper.writeValueAsString(tradeStore);
        MockHttpServletRequestBuilder request = post(ADD_TSTORE_POST_URL);
        request.content(requestBodyStr);
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        log.info("Status : " + mvcResult.getResponse().getStatus());
        log.info("Response : " + mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void _3_DifferentVersion() throws Exception {
        String todaysDate = LocalDate.now().format(CommonConstants.DATE_TIME_FORMATTER);
        LocalDate maturityDate = LocalDate.parse("20/05/2021", DATE_TIME_FORMATTER);
        TradeStore tradeStore = new TradeStore();
        tradeStore.setTradeId("T2");
        tradeStore.setVersion(2);
        tradeStore.setCounterPartyId("CP-2");
        tradeStore.setBookId("B1");
        tradeStore.setMaturityDate(maturityDate);
        tradeStore.setCreatedDate(LocalDate.parse(todaysDate, DATE_TIME_FORMATTER));
        tradeStore.setExpired("N");

        String requestBodyStr = objectMapper.writeValueAsString(tradeStore);
        MockHttpServletRequestBuilder request = post(ADD_TSTORE_POST_URL);
        request.content(requestBodyStr);
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        log.info("Status : " + mvcResult.getResponse().getStatus());
        log.info("Response : " + mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void _4_MaturityLessThanTodaysDateNotAllow() throws Exception {
        String todaysDate = LocalDate.now().format(CommonConstants.DATE_TIME_FORMATTER);
        LocalDate maturityDate = LocalDate.parse("20/05/2014", DATE_TIME_FORMATTER);
        TradeStore tradeStore = new TradeStore();
        tradeStore.setTradeId("T3");
        tradeStore.setVersion(3);
        tradeStore.setCounterPartyId("CP-3");
        tradeStore.setBookId("B1");
        tradeStore.setMaturityDate(maturityDate);
        tradeStore.setCreatedDate(LocalDate.parse(todaysDate, DATE_TIME_FORMATTER));
        tradeStore.setExpired("Y");

        String requestBodyStr = objectMapper.writeValueAsString(tradeStore);
        MockHttpServletRequestBuilder request = post(ADD_TSTORE_POST_URL);
        request.content(requestBodyStr);
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        log.info("Status : " + mvcResult.getResponse().getStatus());
        log.info("Response : " + mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void _5_UpdateExpireFlagIfCrossesMaturityDate() throws Exception {
        LocalDate maturityDate = LocalDate.parse("20/05/2021", DATE_TIME_FORMATTER);
        LocalDate createdDate = LocalDate.parse("14/03/2015", DATE_TIME_FORMATTER);
        TradeStore tradeStore = new TradeStore();
        tradeStore.setTradeId("T2");
        tradeStore.setBookId("B1");
        tradeStore.setVersion(1);
        tradeStore.setCreatedDate(createdDate);
        tradeStore.setMaturityDate(maturityDate);
        tradeStore.setCounterPartyId("CP-1");
        tradeStore.setExpired("N");

        String requestBodyStr = objectMapper.writeValueAsString(tradeStore);
        MockHttpServletRequestBuilder request = post(ADD_TSTORE_POST_URL);
        request.content(requestBodyStr);
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        log.info("Status : " + mvcResult.getResponse().getStatus());
        log.info("Response : " + mvcResult.getResponse().getContentAsString());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
