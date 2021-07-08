package br.com.insurance.command.bus;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CommandProvider<H extends CommandHandler<?, ?>>{

    private final AnnotationConfigApplicationContext applicationContext;
    private final Class<H> type;


    public CommandProvider(AnnotationConfigApplicationContext applicationContext, Class<H> type) {
        this.applicationContext = applicationContext;
        this.type = type;
    }

    H get() {
        return applicationContext.getBean(type);
    }
}
