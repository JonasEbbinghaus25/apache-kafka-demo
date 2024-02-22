package com.example.apachekafkademo.controller;

import com.example.apachekafkademo.service.EventService;
import com.example.apachekafkademo.service.KafkaConsumerService;
import com.example.apachekafkademo.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<String>> loadEvents() {
        List<String> events = kafkaConsumerService.getProcessedEvents();
        return ResponseEntity.ok(events);
    }

    @PostMapping("/send")
    public ResponseEntity<String> publishEvent(@RequestBody String event) {
        event = eventService.adaptMessage(event);
        if (!event.isBlank()) {
            System.out.println(event);
            kafkaProducerService.sendMessage(event);
            return ResponseEntity.ok("Message send successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
