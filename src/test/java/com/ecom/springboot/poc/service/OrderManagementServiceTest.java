package com.ecom.springboot.poc.service;

import com.ecom.springboot.poc.model.Order;
import com.ecom.springboot.poc.model.OrderLine;
import com.ecom.springboot.poc.repository.OrderManagementRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderManagementServiceTest {

  @Mock
  private OrderManagementRepository orderManagementRepository;

  private Order expectedOrder;

  private String orderId ="111l";
  private String orderStatus ="Created";

  private List<Order> expectedOrders;

  private OrderManagementService orderService;

  @Before
  public void setUp(){
    initMocks(this);
    //Arrange
    expectedOrders = Collections.singletonList(Order.builder()
            .id("111l")
            .customerNumber(123)
            .phoneNumber("123-456-7890")
            .orderLines(Collections.singletonList(OrderLine.builder()
                    .quantity(1)
                    .itemId("123ItemId")
                    .lineNumber(456)
                    .status("Created")
                    .build()))
            .build());

    orderService = new OrderManagementService(orderManagementRepository);
  }

  @Test
  public void testGetOrder() {

    //Arrange
    when(orderManagementRepository.findByOrderNoAndOrderLinesStatus(orderId,orderStatus)).thenReturn(expectedOrders);

    //Act
    List<Order> actualOrders = orderService.retrieveOrderByStatus(orderId,orderStatus);

    //Assert
    assertEquals(1 , actualOrders.size());

  }



}