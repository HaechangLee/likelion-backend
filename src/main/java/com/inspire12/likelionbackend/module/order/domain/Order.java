package com.inspire12.likelionbackend.module.order.domain;

import com.inspire12.likelionbackend.module.order.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private Long customerId;
    private Long storeId;
    private String orderNumber;
    private Integer totalAmount;
    @Setter
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;

    public boolean isCancelable() {
        return this.orderStatus == OrderStatus.ORDERED;
    }
    public Order cancel() {
        if (!isCancelable()) throw new IllegalStateException("이미 처리된 주문은 취소할 수 없습니다.");
        return new Order(id, customerId, storeId, orderNumber, totalAmount, OrderStatus.CANCELED, createdAt);
    }

    public Order changeAmount(int newAmount) {
        return new Order(id, customerId, storeId, orderNumber, newAmount, orderStatus, createdAt);
    }


    public void approvePayment(boolean isPaymentSuccess) {
        // TODO
        if(isPaymentSuccess) {
            setOrderStatus(OrderStatus.SUCCESS_PAYMENT);
        } else {
            setOrderStatus(OrderStatus.FAIL_PAYMENT);
        }
    }
}
