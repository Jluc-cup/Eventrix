package com.eventrix.base.feature.transaction;

import com.eventrix.base.errors.ExceptionFactory;
import com.eventrix.base.errors.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class TransactionWrapper {

    private final TransactionTemplate transactionTemplate;

    public <T> T execute(TransactionalOperation<T> operation) {
        try {
            return transactionTemplate.execute(status -> operation.execute());
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    public void executeWithoutResult(Runnable operation) {
        try {
            transactionTemplate.executeWithoutResult(status -> operation.run());
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    private RuntimeException handleException(Exception e) {
        if (e instanceof DataAccessException dataAccessException) {
            if (dataAccessException.getCause() instanceof ConstraintViolationException) {
                return ExceptionFactory.create(ExceptionType.BAD_REQUEST,
                        "Data integrity violation: " + e.getMessage(), e);
            }
            return ExceptionFactory.create(ExceptionType.INTERNAL_SERVER_ERROR,
                    "Database operation failed: " + e.getMessage(), e);
        }
        if (e instanceof TransactionException) {
            return ExceptionFactory.create(ExceptionType.INTERNAL_SERVER_ERROR,
                    "Transaction failure: " + e.getMessage(), e);
        }
        return new RuntimeException(e);
    }

}