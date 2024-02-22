package com.example.apachekafkademo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaConsumerService {

    private final List<String> processedEvents = new ArrayList<>();

//    @KafkaListener(
//            topics = "example",
//            groupId = "my-group",
//            containerFactory = "kafkaListenerContainerFactory")
//    public void listen(String message) {
//        processedEvents.add(message);
//    }

    @KafkaListener(
            topics = "example",
            groupId = "my-group",
            containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        processedEvents.add(message);
    }

    public List<String> getProcessedEvents() {
        return processedEvents;
    }

}
