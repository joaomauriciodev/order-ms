package com.joaomauriciodev.btgpactual.order_ms.controller.dto;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId, Long customerId, BigDecimal total
) {
}
