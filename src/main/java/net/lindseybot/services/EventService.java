package net.lindseybot.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.notfab.eventti.Event;
import net.notfab.eventti.EventManager;
import net.notfab.eventti.Listener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventService {

    private final ObjectMapper objectMapper;
    private final EventManager eventManager;

    public EventService() {
        this.objectMapper = new ObjectMapper();
        this.eventManager = new EventManager();
    }

    /**
     * Fires an event locally.
     *
     * @param event Event to fire.
     */
    public void fire(Event event) {
        this.eventManager.fire(event);
    }

    public void onMessage(String payload) {
        Event event;
        try {
            event = this.objectMapper.readValue(payload, Event.class);
        } catch (JsonProcessingException ex) {
            log.error("Error during event deserialization", ex);
            return;
        }
        this.eventManager.fire(event);
    }

    public void addListener(Listener listener) {
        this.eventManager.addListener(listener);
    }

}
