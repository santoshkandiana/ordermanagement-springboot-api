package com.ecom.springboot.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    private String orderNo;
    private Integer customerNumber;
    private String phoneNumber;
    private List<OrderLine> orderLines;

}
