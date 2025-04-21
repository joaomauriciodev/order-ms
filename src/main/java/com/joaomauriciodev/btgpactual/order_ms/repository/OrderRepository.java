package com.joaomauriciodev.btgpactual.order_ms.repository;

import com.joaomauriciodev.btgpactual.order_ms.controller.dto.OrderResponse;
import com.joaomauriciodev.btgpactual.order_ms.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
    Page<OrderEntity> findAllByCustomerId(Long customerId, PageRequest pageRequest);
}
