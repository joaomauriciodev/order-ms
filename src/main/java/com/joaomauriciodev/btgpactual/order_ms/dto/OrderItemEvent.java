package com.joaomauriciodev.btgpactual.order_ms.dto;

import java.math.BigDecimal;

public record OrderItemEvent(
        String product,
        Integer quantity,
        BigDecimal price
) {
}
