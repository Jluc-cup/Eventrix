package com.eventrix.model.entity;

import com.eventrix.model.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_id_seq")
    @SequenceGenerator(name = "task_id_seq", sequenceName = "task_id_seq", allocationSize = 100)
    @Column(name = "id", unique = true, nullable = false)
    @Access(value = AccessType.PROPERTY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_topic_id")
    private TaskTopicEntity topic;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "queue_name")
    private String queueName;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "logic")
    private String logic;

    @Column(name = "parameters")
    private String parameters;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Column(name = "priority", nullable = false)
    private long priority = 0;

    @Column(name = "retry_count", nullable = false)
    private int retryCount = 1;

    @Column(name = "delay_ms")
    private Long delayMs;

    @Column(name = "period_ms")
    private Long periodMs;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "last_execution_time")
    private Instant lastExecutionTime;

    @Column(name = "execution_count", nullable = false)
    private long executionCount = 0;

    @Column(name = "timeout_ms")
    private Long timeoutMs;

    @Column(name = "created", nullable = false)
    private Instant created;

    @Column(name = "updated")
    private Instant updated;

}
