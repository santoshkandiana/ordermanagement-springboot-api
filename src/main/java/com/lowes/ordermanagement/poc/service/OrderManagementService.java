package com.lowes.ordermanagement.poc.service;

import com.lowes.ordermanagement.poc.controller.OrderManagementController;
import com.lowes.ordermanagement.poc.model.Order;
import com.lowes.ordermanagement.poc.model.OrderRequest;
import com.lowes.ordermanagement.poc.repository.OrderManagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderManagementService {

    private static final Logger logger = LoggerFactory.getLogger(OrderManagementService.class);

    private OrderManagementRepository orderManagementRepository;

    @Autowired
    public OrderManagementService(OrderManagementRepository orderManagementRepository){
        this.orderManagementRepository = orderManagementRepository;
    }

    public Order saveOrder(OrderRequest orderRequest){

        logger.debug("Saving an order to database");
        Order order =  Order.builder()
                         .orderNo(orderRequest.getOrderNo())
                         .customerNumber(orderRequest.getCustomerNumber())
                         .PhoneNumber(orderRequest.getPhoneNumber())
                         .orderLines(orderRequest.getOrderLines()).build();

        return orderManagementRepository.save(order);

    }

    public List<Order> retrieveOrderByStatus(String orderNumber , String orderStatus) {

        logger.debug("Retrieving order details based order status for line items");
        return orderManagementRepository.findByOrderNoAndOrderLinesStatus(orderNumber, orderStatus);
    }
}
