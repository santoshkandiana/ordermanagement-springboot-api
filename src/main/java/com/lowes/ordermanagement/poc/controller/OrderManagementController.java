package com.lowes.ordermanagement.poc.controller;


import com.lowes.ordermanagement.poc.exception.InvalidInputException;
import com.lowes.ordermanagement.poc.model.OrderRequest;
import com.lowes.ordermanagement.poc.model.OrderRetrieveResponse;
import com.lowes.ordermanagement.poc.model.OrderSaveResonse;
import com.lowes.ordermanagement.poc.service.OrderManagementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/ordermanagement")
public class OrderManagementController {

    private static final Logger logger = LoggerFactory.getLogger(OrderManagementController.class);

    private OrderManagementService orderManagementService;


    @Autowired
    public OrderManagementController(OrderManagementService orderManagementService){
        this.orderManagementService = orderManagementService;
    }

    @ApiOperation(
            value = "POST An Order",
            notes = "Saves a new  order to database",
            response = OrderSaveResonse.class
    )
    @ApiResponses({
            @ApiResponse(code=200, message = "Post successful"),
            @ApiResponse(code=400, message = "bad request")
    })
    @PostMapping
    public OrderSaveResonse saveOrder(@RequestBody OrderRequest orderRequest)  {

        logger.debug("Save Order Request Invoked");
        return OrderSaveResonse.builder()
                .order(orderManagementService.saveOrder(orderRequest))
                .message("Order created successfully")
                .status(200)
                .source("lowes-ordermanagement-poc").build();

    }

    @ApiOperation(
            value = "GET Order Details",
            notes = "Gets Order details for an order for given status",
            response = OrderRetrieveResponse.class
    )
    @ApiResponses({
            @ApiResponse(code=200, message = "retrieve successful"),
            @ApiResponse(code=400, message = "bad request"),
            @ApiResponse(code=404, message = "Order does not exist in database"),
            @ApiResponse(code=404, message = "Order Lines does not exist in database for the given order and status"),

    })
    @GetMapping("{orderNumber}/{orderStatus}")
    public OrderRetrieveResponse getOrder(@PathVariable String orderNumber , @PathVariable String orderStatus) {

        logger.debug("Retrieve Order details by order line item status Request Invoked");

        if (!ObjectUtils.isEmpty(orderNumber) && !ObjectUtils.isEmpty(orderStatus)) {
            return OrderRetrieveResponse.builder()
                    .orders(orderManagementService.retrieveOrderByStatus(orderNumber, orderStatus))
                    .message("Order retrieved successfully")
                    .status(200)
                    .source("lowes-ordermanagement-poc").build();
        }
        throw new InvalidInputException("Order Number or Order Status cannot be empty");
    }

}
