package com.ecom.springboot.poc.repository;

import com.ecom.springboot.poc.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderManagementRepository extends CrudRepository<Order, String> {
    List<Order> findByOrderNoAndOrderLinesStatus(String orderNumber, String orderStatus);
}
