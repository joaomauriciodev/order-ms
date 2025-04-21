package com.joaomauriciodev.btgpactual.order_ms.controller;

import com.joaomauriciodev.btgpactual.order_ms.controller.dto.ApiResponse;
import com.joaomauriciodev.btgpactual.order_ms.controller.dto.OrderResponse;
import com.joaomauriciodev.btgpactual.order_ms.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    public final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize){

        return ResponseEntity.ok(null);
    }
}
