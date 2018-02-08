package com.jasonfly.helloworld.rabbit.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JasonSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(int i) {
        String context = "spring boot jason queue " + " ***** " + i;
        System.out.println("Sender 1 : " + context);
        this.rabbitTemplate.convertAndSend("jason", context);
    }
}
