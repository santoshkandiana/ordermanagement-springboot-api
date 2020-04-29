package com.lowes.ordermanagement.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSaveResonse {

    private  Integer status;
    private  String message;
    private  String source;
    private  Order  order;
}
