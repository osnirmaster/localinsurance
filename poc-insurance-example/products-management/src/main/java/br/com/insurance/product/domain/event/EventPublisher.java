package br.com.insurance.product.domain.event;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {

    private List<Listener> listeners = new ArrayList<>();

    public void add(Listener listener) {
        this.listeners.add(listener);
    }

    public void publish(Event evento) {
        this.listeners.forEach(o -> {
            o.process(evento);
        });
    }
}
