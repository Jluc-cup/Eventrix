package com.eventrix.testV1.feature;

import com.eventrix.base.errors.CustomException;
import com.eventrix.base.errors.ExceptionType;
import com.eventrix.base.feature.transaction.TransactionWrapper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionWrapperV1Test {

    @Mock
    private TransactionTemplate transactionTemplate;

    @InjectMocks
    private TransactionWrapper transaction;


    @Test
    public void executeWithConnectionLossThrowsInternalServerError() {

        final JDBCConnectionException jdbcException = new JDBCConnectionException("Database connection lost",
                new SQLException("Connection timed out"));
        when(transactionTemplate.execute(any())).thenThrow(new DataAccessException("DB error", jdbcException) {
        });

        CustomException exception = assertThrows(CustomException.class, () ->
                transaction.execute(() -> 42)
        );

        assertThat(exception.getType()).isEqualTo(ExceptionType.INTERNAL_SERVER_ERROR);
        assertThat(exception.getCustomDetails()).contains("Database operation failed");
        assertThat(exception.getCause()).isInstanceOf(DataAccessException.class);
        assertThat(exception.getCause().getCause()).isInstanceOf(JDBCConnectionException.class);
    }

    @Test
    public void executeWithConstraintViolationThrowsBadRequest() {

        final ConstraintViolationException constraintException = new ConstraintViolationException("Unique constraint violated",
                new SQLException("Connection timed out"), "constraint_name");

        when(transactionTemplate.execute(any())).thenThrow(new DataAccessException("Constraint error", constraintException) {
        });

        final CustomException exception = assertThrows(CustomException.class, () -> transaction.execute(() -> 42));

        assertThat(exception.getType()).isEqualTo(ExceptionType.BAD_REQUEST);
        assertThat(exception.getCustomDetails()).contains("Data integrity violation");
        assertThat(exception.getCause()).isInstanceOf(DataAccessException.class);
        assertThat(exception.getCause().getCause()).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void executeWithTransactionFailureThrowsInternalServerError() {
        when(transactionTemplate.execute(any())).thenThrow(new TransactionException("Transaction failed") {});

        final CustomException exception = assertThrows(CustomException.class, () -> transaction.execute(() -> 42));

        assertThat(exception.getType()).isEqualTo(ExceptionType.INTERNAL_SERVER_ERROR);
        assertThat(exception.getCustomDetails()).contains("Transaction failure");
        assertThat(exception.getCause()).isInstanceOf(TransactionException.class);
    }

}