package com.joaomauriciodev.btgpactual.order_ms.service;

import com.joaomauriciodev.btgpactual.order_ms.controller.dto.OrderResponse;
import com.joaomauriciodev.btgpactual.order_ms.dto.OrderCreatedEvent;
import com.joaomauriciodev.btgpactual.order_ms.entity.OrderEntity;
import com.joaomauriciodev.btgpactual.order_ms.entity.OrderItem;
import com.joaomauriciodev.btgpactual.order_ms.listener.OrderCreatedListener;
import com.joaomauriciodev.btgpactual.order_ms.repository.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate){
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void save(OrderCreatedEvent event){

        var entity = new OrderEntity();
        entity.setOrderId(event.orderId());
        entity.setCustomerId(event.customerId());
        entity.setItems(getOrderItems(event));

        entity.setTotal(getTotal(event));

        orderRepository.save(entity);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest ){
        var orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponse::fromEntity);
    }

    public BigDecimal findTotalOrdersByCustomerId(Long customerId){
        var aggregations = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(aggregations, "tb_orders", Document.class);
        Object totalObj = response.getUniqueMappedResult().getOrDefault("total", BigDecimal.ZERO);
        BigDecimal total;

        if (totalObj instanceof org.bson.types.Decimal128) {
            total = ((org.bson.types.Decimal128) totalObj).bigDecimalValue();
        } else if (totalObj instanceof BigDecimal) {
            total = (BigDecimal) totalObj;
        } else {
            total = BigDecimal.ZERO;
        }
        return total;
    }

    private BigDecimal getTotal(OrderCreatedEvent event) {
        return event.items()
                .stream()
                .map(i -> i.price().multiply(  BigDecimal.valueOf(i.quantity())  ))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.items().stream()
                .map(i -> new OrderItem(i.product(), i.quantity(), i.price()))
                .toList();
    }
}
