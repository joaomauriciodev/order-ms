package com.joaomauriciodev.btgpactual.order_ms.repository;

import com.joaomauriciodev.btgpactual.order_ms.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
}
