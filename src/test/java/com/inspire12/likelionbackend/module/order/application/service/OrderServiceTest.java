package com.inspire12.likelionbackend.module.order.application.service;

import com.inspire12.likelionbackend.module.order.application.dto.OrderRequest;
import com.inspire12.likelionbackend.module.order.application.port.out.PaymentPort;
import com.inspire12.likelionbackend.module.order.domain.Order;
import com.inspire12.likelionbackend.module.order.enums.OrderStatus;
import com.inspire12.likelionbackend.module.payment.application.service.PaymentProcessOrderUsecase;
import com.inspire12.likelionbackend.testure.OrderMockScenario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderServiceTest {

    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @BeforeEach
    void setup() {
//        OrderRepository orderRepository = new TestOrderRepository();
        PaymentPort paymentPort = new PaymentProcessOrderUsecase();
        orderService = new OrderService(orderRepository, paymentPort);
    }

    @Test
    void 결제가_성공한_후_주문상태_확인() {
        // Given
        OrderRequest orderRequest = OrderMockScenario.createDeliveryOrderRequest();
        // When
        // 테스트가 실패, 왜?
        Order order = orderService.confirmOrderPayment(orderRequest);
        // Then
        assertEquals(OrderStatus.SUCCESS_PAYMENT, order.getOrderStatus());
    }

    @Test
    void 결제가_성공한_후_주문상태_확인_mock() {
        // Given
        OrderRequest orderRequest = OrderMockScenario.createDeliveryOrderRequest();
        // When
        // 테스트가 실패, 왜?
        Order order = orderService.confirmOrderPayment(orderRequest);
        // Then
        assertEquals(OrderStatus.SUCCESS_PAYMENT, order.getOrderStatus());
    }

    @Test
    void 결제가_실패한_후_주문상태_확인() {
        // Given
        OrderRequest orderRequest = OrderMockScenario.createDeliveryOrderRequest();
        // When
        Order order = orderService.confirmOrderPayment(orderRequest);
        // Then
        assertEquals(OrderStatus.FAIL_PAYMENT, order.getOrderStatus());
    }
}