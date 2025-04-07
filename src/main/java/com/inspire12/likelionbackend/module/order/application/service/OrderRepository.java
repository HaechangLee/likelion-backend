package com.inspire12.likelionbackend.module.order.application.service;


import com.inspire12.likelionbackend.module.order.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {
    Order getOrderByOrderId(Long orderId);
    Order getOrderByCustomerId(Long customerId);

    Order getOrderByOrderNumber(String orderNumber);
}
