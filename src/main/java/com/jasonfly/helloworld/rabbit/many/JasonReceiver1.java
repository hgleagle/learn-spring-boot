package com.jasonfly.helloworld.rabbit.many;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "jason")
public class JasonReceiver1 {
    @RabbitHandler
    public void process(String jason) {
        System.out.println("Receiver 1: " + jason);
    }
}
