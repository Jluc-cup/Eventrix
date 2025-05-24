package com.eventrix.base.feature.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class TransactionWrapper {

    private final TransactionTemplate transactionTemplate;

    public <T> T execute(TransactionalOperation<T> operation) {
        try {
            return transactionTemplate.execute(status -> operation.execute());
        } catch (DataAccessException e) {
           // throw new TaskTopicTransactionException("Database connection or operation failed", e);
            throw new RuntimeException(e); // todo add custom exception and  logs
        }
    }

    public void executeWithoutResult(Runnable operation) {
        try {
            transactionTemplate.executeWithoutResult(status -> operation.run());
        } catch (DataAccessException e) {
            // throw new TaskTopicTransactionException("Database connection or operation failed", e);
            throw new RuntimeException(e); // todo add custom exception and  logs
        }
    }

}