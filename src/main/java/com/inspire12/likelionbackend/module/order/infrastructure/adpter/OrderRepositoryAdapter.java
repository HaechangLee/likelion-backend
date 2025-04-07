package com.inspire12.likelionbackend.module.order.infrastructure.adpter;

import com.inspire12.likelionbackend.exception.OrderNotExistException;
import com.inspire12.likelionbackend.module.order.application.port.out.StoreStatusPort;
import com.inspire12.likelionbackend.module.order.domain.Order;
import com.inspire12.likelionbackend.module.order.application.service.OrderRepository;
import com.inspire12.likelionbackend.module.order.infrastructure.adpter.dto.StoreAssertStatusResponse;
import com.inspire12.likelionbackend.module.order.infrastructure.repository.OrderJpaRepository;
import com.inspire12.likelionbackend.module.order.infrastructure.repository.entity.OrderEntity;
import com.inspire12.likelionbackend.module.order.support.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final StoreStatusPort statusPort;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository, StoreStatusPort statusPort) {
        this.orderJpaRepository = orderJpaRepository;
        this.statusPort = statusPort;
    }

    public Order getOrderByOrderId(Long orderId) {
        // TODO:1 가계 도메인한테(책임을 위임해서) 가계가 열렸는지 확인한다
        // TODO:2 가계가 열렸으면 orderId로 주문을 가져와 도메인 객체로 변환해 반환한다
//        OrderEntity orderEntity  = orderJpaRepository.findById(orderId).orElse(null);
        OrderEntity orderEntity  = orderJpaRepository.findById(orderId).orElseThrow(OrderNotExistException::new);
//        Long storeId = orderEntity.getStoreId();
//        boolean isOpen = statusPort.getStoreOpenStatus(storeId);
//        if(!isOpen) {
//            throw new OrderNotExistException();
//        }
//        return OrderMapper.fromEntity(orderEntity);
//        StoreAssertStatusResponse response = new
        if(statusPort.getStoreOpenStatus(orderEntity.getStoreId())) {
            return OrderMapper.fromEntity(orderEntity);
        }
        throw new OrderNotExistException();
    }


    @Override
    public Order getOrderByCustomerId(Long customerId) {
        return OrderMapper.fromEntity(orderJpaRepository.findByCustomerId(customerId));
    }

    @Override
    public Order getOrderByOrderNumber(String orderNumber) {

        throw new OrderNotExistException();
    }
}
