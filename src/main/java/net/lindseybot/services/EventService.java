package net.lindseybot.services;

import net.notfab.eventti.Event;
import net.notfab.eventti.Listener;

public interface EventService {

    /**
     * Fires an event locally.
     *
     * @param event Event to fire.
     */
    void fire(Event event);

    /**
     * Publish an event to the network.
     *
     * @param to    RabbitMQ Exchange.
     * @param event Event.
     */
    void publish(String to, Event event);

    /**
     * Adds a listener for a specific event type.
     *
     * @param listener Listener.
     */
    void addListener(Listener listener);

    /**
     * Handles events received over the network.
     *
     * @param message Payload.
     */
    void onMessage(String message);

}
