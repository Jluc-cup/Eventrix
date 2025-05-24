package com.eventrix.service.strategy;

public interface OperationStrategy<Context extends StrategyContextInterface, Result> {
    Result execute(Context request);
}