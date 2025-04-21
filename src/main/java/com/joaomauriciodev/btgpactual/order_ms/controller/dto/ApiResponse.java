package com.joaomauriciodev.btgpactual.order_ms.controller.dto;

import java.util.List;

public record ApiResponse<T>(List<T> data, PaginationResponse pagination) {
}
