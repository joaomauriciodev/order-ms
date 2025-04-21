package com.joaomauriciodev.btgpactual.order_ms.listener;

import com.joaomauriciodev.btgpactual.order_ms.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.joaomauriciodev.btgpactual.order_ms.config.RabbitmqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message){

    }
}
