package com.eventrix.base.feature;

public interface OperationStrategy<Req, Result> {
    Result execute(Req request);
}