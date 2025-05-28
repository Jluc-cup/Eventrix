package com.eventrix.base.feature.command;

import com.eventrix.base.errors.ExceptionFactory;
import com.eventrix.base.errors.ExceptionType;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommandManager implements ApplicationContextAware {

    private final Map<String, Command<?, ?>> commandRegistry = new HashMap<>();
    private final Map<String, Class<?>> contextTypes = new HashMap<>();
    private final Map<String, Class<?>> returnTypes = new HashMap<>();
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() {
        final Map<String, Object> beans = applicationContext.getBeansWithAnnotation(CommandDefinition.class);
        for (Object bean : beans.values()) {
            final CommandDefinition definition = bean.getClass().getAnnotation(CommandDefinition.class);
            final String commandName = definition.name();
            final Class<?> contextType = definition.contextType();
            final Class<?> returnType = definition.returnType();
            registerCommand(commandName, contextType, returnType, (Command<?, ?>) bean);
        }
    }

    private void registerCommand(String commandName, Class<?> contextType, Class<?> returnType, Command<?, ?> command) {
        commandRegistry.put(commandName, command);
        contextTypes.put(commandName, contextType);
        returnTypes.put(commandName, returnType);
    }


    public <T, R> R executeCommand(String commandName, T context) {
        final Class<?> expectedContextType = contextTypes.get(commandName);
        final Class<?> expectedReturnType = returnTypes.get(commandName);


        if (expectedContextType == null) throw ExceptionFactory.create(ExceptionType.INTERNAL_SERVER_ERROR);
        if (!expectedContextType.isInstance(context)) throw ExceptionFactory.create(ExceptionType.INTERNAL_SERVER_ERROR);
        if (expectedReturnType == null) throw ExceptionFactory.create(ExceptionType.INTERNAL_SERVER_ERROR);


        @SuppressWarnings("unchecked")
        final Command<T, R> command = (Command<T, R>) commandRegistry.get(commandName);
        return command.execute(context);
    }

    @Autowired
    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}