package com.eventrix.base.feature;

import com.eventrix.service.strategy.OperationStrategy;
import com.eventrix.service.strategy.StrategyContextInterface;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractService {
    private final Map<Class<? extends StrategyContextInterface>,
            OperationStrategy<? extends StrategyContextInterface, ?>> strategies = new HashMap<>();

    protected <Context extends StrategyContextInterface, Result> void registerStrategy(Class<Context> contextClass,
                                                                                       OperationStrategy<Context, Result> strategy) {
        strategies.put(contextClass, strategy);
    }

    protected <Context extends StrategyContextInterface, Result> Result executeOperation(Context request) {
        @SuppressWarnings("unchecked")
        OperationStrategy<Context, Result> strategy = (OperationStrategy<Context, Result>) strategies.get(request.getClass());
        if (strategy == null) {
             throw new RuntimeException(); //TODO
        }
        return strategy.execute(request);
    }
}