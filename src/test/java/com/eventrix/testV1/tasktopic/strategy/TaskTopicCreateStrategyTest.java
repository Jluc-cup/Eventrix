package com.eventrix.testV1.tasktopic.strategy;

import com.eventrix.base.feature.transaction.TransactionWrapper;
import com.eventrix.base.feature.transaction.TransactionalOperation;
import com.eventrix.dao.TaskTopicDao;
import com.eventrix.model.entity.TaskTopicEntity;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import com.eventrix.service.strategy.tasktopic.TaskTopicCreateStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskTopicCreateStrategyTest {

    @Mock
    private TaskTopicDao taskTopicDao;

    @Mock
    private TransactionWrapper transaction;

    @InjectMocks
    private TaskTopicCreateStrategy strategy;

    private TaskTopicCreateObj createObj;

    @BeforeEach
    void setUp() {
        createObj = new TaskTopicCreateObj();
        doAnswer(invocation -> {
            TaskTopicEntity savedEntity = invocation.getArgument(0);
            savedEntity.setId(1);
            return null;
        }).when(taskTopicDao).save(any(TaskTopicEntity.class));
    }

    @Test
    void executeSuccessfulCreationReturnsEntityId() {
        when(transaction.execute(any())).thenAnswer(invocation -> {
            final TransactionalOperation<TaskTopicEntity> operation = invocation.getArgument(0);
            return operation.execute();
        });

        final Integer result = strategy.execute(createObj);

        assertThat(result).isEqualTo(1);
        verify(taskTopicDao).save(any(TaskTopicEntity.class));
        verify(transaction).execute(any());
    }
}