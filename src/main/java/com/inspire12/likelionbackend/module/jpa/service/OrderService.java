package com.inspire12.likelionbackend.module.jpa.service;

import com.inspire12.likelionbackend.module.jpa.model.entity.OrderEntity;
import com.inspire12.likelionbackend.module.jpa.model.mapper.OrderMapper;
import com.inspire12.likelionbackend.module.jpa.model.request.OrderRequest;
import com.inspire12.likelionbackend.module.jpa.model.response.OrderResponse;
import com.inspire12.likelionbackend.module.jpa.repository.OrderJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderJpaRepository orderJpaRepository;


    @Transactional(readOnly = true)
    public OrderResponse getOrder(Long orderId) {
        // TODO
        Optional<OrderEntity> order = orderJpaRepository.findById(orderId);
        if(order.isPresent()) {
            return OrderMapper.fromEntity(order.get());
        }
        throw new EntityNotFoundException("주문 없음");
    }

    @Transactional
    public OrderResponse saveOrder(OrderRequest request) {
        // TODO
        OrderEntity order = OrderMapper.toEntity(request);
        OrderEntity save = orderJpaRepository.save(order);
        return OrderMapper.fromEntity(save);
//        throw new EntityNotFoundException("주문 없음");
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        // TODO
        orderJpaRepository.deleteById(orderId);
//        throw new EntityNotFoundException("주문 없음");

    }

    @Transactional
    public OrderResponse updateTotalAmount(Long orderId, Integer newAmount) {
        // TODO
        Optional<OrderEntity> order = orderJpaRepository.findById(orderId);
        if(order.isEmpty()) {
            throw new EntityNotFoundException("주문 없음");
        }
        order.get().changeTotalAmount(newAmount);
        return OrderMapper.fromEntity(order.get());
    }
}
