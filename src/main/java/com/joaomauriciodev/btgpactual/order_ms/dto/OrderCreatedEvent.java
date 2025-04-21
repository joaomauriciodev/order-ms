package com.joaomauriciodev.btgpactual.order_ms.dto;

import java.util.List;

public record OrderCreatedEvent(
        Long orderId,
        Long customerId,
        List<OrderItemEvent> items
) {
}
