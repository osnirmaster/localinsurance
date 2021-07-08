package br.com.insurance.command.bus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QueryProvider<H extends QueryHandler<?, ?>> {

    private final AnnotationConfigApplicationContext applicationContext;
    private final Class<H> type;

    public QueryProvider(AnnotationConfigApplicationContext applicationContext, Class<H> type) {
        this.applicationContext = applicationContext;
        this.type = type;
    }

    H get() {
        return applicationContext.getBean(type);
    }
}
