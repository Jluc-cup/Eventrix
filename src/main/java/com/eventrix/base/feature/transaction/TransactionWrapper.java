package com.eventrix.base.feature.transaction;

import com.eventrix.base.errors.ExceptionFactory;
import com.eventrix.base.errors.ExceptionType;
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
            throw ExceptionFactory.create(ExceptionType.INTERNAL_SERVER_ERROR, e.getCause());
        }
    }

    public void executeWithoutResult(Runnable operation) {
        try {
            transactionTemplate.executeWithoutResult(status -> operation.run());
        } catch (DataAccessException e) {
            throw ExceptionFactory.create(ExceptionType.INTERNAL_SERVER_ERROR, e.getCause());
        }
    }

}