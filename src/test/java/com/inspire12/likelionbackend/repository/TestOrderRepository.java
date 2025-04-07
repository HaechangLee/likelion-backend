package com.inspire12.likelionbackend.repository;

import com.inspire12.likelionbackend.module.order.domain.Order;
import com.inspire12.likelionbackend.module.order.application.service.OrderRepository;

public class TestOrderRepository implements OrderRepository {

    @Override
    public Order getOrderByOrderId(Long orderId) {
        return null;
    }

    @Override
    public Order getOrderByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public Order getOrderByOrderNumber(String orderNumber) {
        return null;
    }
}
