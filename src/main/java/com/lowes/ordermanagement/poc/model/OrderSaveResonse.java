package com.lowes.ordermanagement.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
