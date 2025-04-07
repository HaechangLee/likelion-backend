package com.inspire12.likelionbackend.module.order.application.service;

import com.inspire12.likelionbackend.module.order.application.dto.OrderRequest;
import com.inspire12.likelionbackend.module.order.application.dto.OrderResponse;
import com.inspire12.likelionbackend.module.order.application.port.out.PaymentPort;
import com.inspire12.likelionbackend.module.order.domain.Order;
import com.inspire12.likelionbackend.module.order.domain.OrderRepository;
import com.inspire12.likelionbackend.module.order.enums.OrderStatus;
import com.inspire12.likelionbackend.module.order.support.mapper.OrderMapper;
import org.springframework.stereotype.Service;


@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentPort paymentPort;

    public OrderService(OrderRepository orderRepository, PaymentPort paymentPort) {
        this.orderRepository = orderRepository;
        this.paymentPort = paymentPort;
    }


    public Order confirmOrderPayment(OrderRequest orderRequest) {
        // 주문 도메인 객체를 만들고
        Order order = orderRepository.getOrderByCustomerId(orderRequest.customerId());
        // 결제를 요청해 처리하고
        boolean isPaymentSuccess = paymentPort.processPayment(order);
        // TODO payment 성공 여부에 따라 Order 상태가 변화하는 걸 order 도메인 객체에 옮기자
        //  서비스에서는 로직 진행만 볼 수 있게 만들고, 세부 로직 처리는 각 도메인에서 처리하도록 리팩토링 하였음.
        order.approvePayment(isPaymentSuccess);
//        if(isPaymentSuccess) {
//            order.setOrderStatus(OrderStatus.SUCCESS_PAYMENT);
//        } else {
//            order.setOrderStatus(OrderStatus.FAIL_PAYMENT);
//        }

        return order;
    }

    public OrderResponse getOrderByOrderId(Long orderId) {
        Order orderByOrderId = orderRepository.getOrderByOrderId(orderId);
        return OrderMapper.toResponse(orderByOrderId);
    }
}
