package com.ecom.springboot.poc.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLine {
    private Integer lineNumber;
    private String itemId;
    private Integer quantity;
    private String status;
}
