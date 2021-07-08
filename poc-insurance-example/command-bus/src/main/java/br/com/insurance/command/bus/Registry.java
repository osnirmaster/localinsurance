package br.com.insurance.command.bus;

import br.com.insurance.command.bus.api.Command;
import br.com.insurance.command.bus.api.Query;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.*;

public class Registry {

    private Map<Class<? extends Command>, CommandProvider> commandProviderMap = new HashMap<>();
    private Map<Class<? extends Query>, QueryProvider> queryProviderMap = new HashMap<>();

    public Registry(AnnotationConfigApplicationContext applicationContext) {
        Map<String,CommandHandler> commandHandlers = applicationContext
                .getBeansOfType(CommandHandler.class);

        commandHandlers.forEach((k,v) -> registerCommand(applicationContext, (Class<CommandHandler>) v.getClass()));


        Map<String,QueryHandler> queryHandlers = applicationContext
                .getBeansOfType(QueryHandler.class);
        queryHandlers.forEach((k,v) -> registerQuery(applicationContext, (Class<QueryHandler>) v.getClass()));
    }

    private void registerQuery(AnnotationConfigApplicationContext applicationContext, Class<QueryHandler> type) {

        Class<QueryHandler> handlerClass = type;
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler.class);
        Class<? extends Query> queryType = (Class<? extends Query>) generics[1];
        queryProviderMap.put(queryType, new QueryProvider(applicationContext, handlerClass));
    }

    private void registerCommand(AnnotationConfigApplicationContext applicationContext, Class<CommandHandler> type) {

        Class<CommandHandler> handlerClass = type;
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
        Class<? extends Command> commandType = (Class<? extends Command>) generics[1];
        commandProviderMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
    }


    <R, C extends Command<R>> CommandHandler<R, C> getCmd(Class<C> commandClass) {
        return commandProviderMap.get(commandClass).get();
    }

    <R, C extends Query<R>> QueryHandler<R, C> getQuery(Class<C> commandClass) {
        return queryProviderMap.get(commandClass).get();
    }

}
