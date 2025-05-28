package com.eventrix.base.feature.strategy;

public interface OperationStrategy<Context extends StrategyContextInterface, Result> {
    Result execute(Context context);
}