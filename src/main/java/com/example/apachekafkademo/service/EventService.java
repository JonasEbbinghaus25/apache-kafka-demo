package com.example.apachekafkademo.service;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EventService {

    public Optional<String> trimMessage(String message) {
        if (!message.isBlank()) {
            try {
                message = message.replaceAll("^\"|\"$", "");

                String[] parts = message.split("\"");
                String inputText = parts.length > 3 ? parts[3] : "";
                return Optional.of(inputText);
            } catch (Exception e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public String adaptMessage(String message) {
        Optional<String> event = trimMessage(message);
        return event.isPresent() ? event.get() : "";
    }

}
