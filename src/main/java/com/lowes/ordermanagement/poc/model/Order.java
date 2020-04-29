package com.lowes.ordermanagement.poc.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private String id;
    private String orderNo;
    private Integer customerNumber;
    private String phoneNumber;
    private List<OrderLine> orderLines;
}
