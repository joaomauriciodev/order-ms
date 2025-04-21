package com.joaomauriciodev.btgpactual.order_ms.service;

import com.joaomauriciodev.btgpactual.order_ms.dto.OrderCreatedEvent;
import com.joaomauriciodev.btgpactual.order_ms.entity.OrderEntity;
import com.joaomauriciodev.btgpactual.order_ms.entity.OrderItem;
import com.joaomauriciodev.btgpactual.order_ms.listener.OrderCreatedListener;
import com.joaomauriciodev.btgpactual.order_ms.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void save(OrderCreatedEvent event){

        var entity = new OrderEntity();
        entity.setOrderId(event.orderId());
        entity.setCustomerId(event.customerId());
        entity.setItems(getOrderItems(event));

        entity.setTotal(getTotal(event));

        orderRepository.save(entity);
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
