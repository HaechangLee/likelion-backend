package com.inspire12.likelionbackend.module.order.domain;

import com.inspire12.likelionbackend.module.order.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    @Test
    void 우선_실패하는테스트_만들기() {
        // TODO junit/assertj 를 통해 실패하게 만들기
        Order testOrder = new Order(1L, 123L, 7L,"abcd123",10000, OrderStatus.ORDERED, LocalDateTime.now());
        Order result = testOrder.changeAmount(20000);
        assertThat(result.getTotalAmount()).isEqualTo(20000);

    }

}
