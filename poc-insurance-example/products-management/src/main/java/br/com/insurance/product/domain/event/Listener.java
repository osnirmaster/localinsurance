package br.com.insurance.product.domain.event;

public abstract class Listener {

    public void process(Event event) {
        if (this.eventToProcess(event)) {
            this.reactTo(event);
        }
    }

    protected abstract void reactTo(Event event);
    protected abstract boolean eventToProcess(Event evento);
}
