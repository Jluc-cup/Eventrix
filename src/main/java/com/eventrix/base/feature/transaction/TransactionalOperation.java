package com.eventrix.base.feature.transaction;

@FunctionalInterface
public interface TransactionalOperation<T> {
    T execute();
}