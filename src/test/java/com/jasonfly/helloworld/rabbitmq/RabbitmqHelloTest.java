package com.jasonfly.helloworld.rabbitmq;

import com.jasonfly.helloworld.rabbit.fanout.FanoutSender;
import com.jasonfly.helloworld.rabbit.hello.HelloSender;
import com.jasonfly.helloworld.rabbit.many.JasonSender;
import com.jasonfly.helloworld.rabbit.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqHelloTest {
    @Autowired
    private HelloSender helloSender;
    @Autowired
    private JasonSender jasonSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
        Thread.sleep(1000l);
    }

    @Test
    public void oneToMany() throws Exception {
        for (int i = 0; i < 100; i++) {
            jasonSender.send(i);
        }
        Thread.sleep(10000l);
    }

    @Test
    public void topic1() throws Exception {
        topicSender.send1();
        Thread.sleep(1000l);
    }

    @Test
    public void topic2() throws Exception {
        topicSender.send2();
        Thread.sleep(1000l);
    }

    @Test
    public void fanout() throws Exception {
        fanoutSender.send();
        Thread.sleep(1000l);
    }
}
