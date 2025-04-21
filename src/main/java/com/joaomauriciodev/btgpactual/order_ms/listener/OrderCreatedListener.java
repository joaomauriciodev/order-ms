package com.joaomauriciodev.btgpactual.order_ms.listener;

import com.joaomauriciodev.btgpactual.order_ms.dto.OrderCreatedEvent;
import com.joaomauriciodev.btgpactual.order_ms.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.joaomauriciodev.btgpactual.order_ms.config.RabbitmqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService){
        this.orderService = orderService;
    }

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message){
       logger.info("Message consumed: {}", message);
       orderService.save(message.getPayload());

    }
}
