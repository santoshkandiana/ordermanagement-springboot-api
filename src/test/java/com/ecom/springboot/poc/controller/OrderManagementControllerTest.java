package com.ecom.springboot.poc.controller;

import com.ecom.springboot.poc.exception.RestControllerAdvise;
import com.ecom.springboot.poc.model.Order;
import com.ecom.springboot.poc.model.OrderLine;
import com.ecom.springboot.poc.model.OrderRequest;
import com.ecom.springboot.poc.model.OrderRetrieveResponse;
import com.ecom.springboot.poc.service.OrderManagementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class OrderManagementControllerTest {


    @Mock
    OrderManagementService orderManagementService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private String uri;


    @Before
    public void setUp() {
        initMocks(this);
        objectMapper = new ObjectMapper();
        this.mockMvc = standaloneSetup(new OrderManagementController(orderManagementService))
                .setControllerAdvice(new RestControllerAdvise())
                .build();
        uri = "/v1/ordermanagement/";
    }


    @Test
    public void testGetOrderSuccess() throws Exception {

        //Arrange
        Order order = Order.builder()
                .id("123OrderId")
                .customerNumber(123)
                .phoneNumber("123-456-7890")
                .orderLines(Collections.singletonList(OrderLine.builder()
                        .quantity(1)
                        .itemId("123ItemId")
                        .lineNumber(456)
                        .status("Created")
                        .build()))
                .build();


        OrderRetrieveResponse expectedorderGetResponse = OrderRetrieveResponse.builder()
                .orders(Collections.singletonList(order))
                .status(200)
                .message("Order retrieved successfully")
                .source("ecom-springboot-poc")
                .build();


        when(orderManagementService.retrieveOrderByStatus(any(),any())).thenReturn(Collections.singletonList(order));

        //Act
        MvcResult mvcResult = mockMvc.perform(
                get(uri +"/" + "123Order" +"/"+ "Created"))
                .andExpect(status().isOk())
                .andReturn();

        OrderRetrieveResponse actualResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), OrderRetrieveResponse.class);
        //Assert
        assertEquals(expectedorderGetResponse.toString(), actualResponse.toString());
        verify(orderManagementService, times(1)).retrieveOrderByStatus(any(),any());

    }

    @Test
    public void testSaveOrderSuccess() throws Exception {

        //Arrange
        OrderRequest order = OrderRequest.builder()
                .orderNo("123OrderId")
                .customerNumber(123)
                .phoneNumber("123-456-7890")
                .orderLines(Collections.singletonList(OrderLine.builder()
                        .quantity(1)
                        .itemId("123ItemId")
                        .lineNumber(456)
                        .status("Created")
                        .build()))
                .build();

        //Arrange
        Order orderResponse = Order.builder()
                .id("123OrderId")
                .customerNumber(123)
                .phoneNumber("123-456-7890")
                .orderLines(Collections.singletonList(OrderLine.builder()
                        .quantity(1)
                        .itemId("123ItemId")
                        .lineNumber(456)
                        .status("Created")
                        .build()))
                .build();

        when(orderManagementService.saveOrder(any())).thenReturn(orderResponse);


        //Act
        mockMvc.perform(
                post(uri)
                        .content(objectMapper.writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        //Assert
        verify(orderManagementService, times(1)).saveOrder(any());
    }

}