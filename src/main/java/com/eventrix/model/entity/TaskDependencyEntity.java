package com.eventrix.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_dependency")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDependencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_dependency_id_seq")
    @SequenceGenerator(name = "task_dependency_id_seq", sequenceName = "task_dependency_id_seq", allocationSize = 100)
    @Column(name = "id", unique = true, nullable = false)
    @Access(value = AccessType.PROPERTY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;

    @ManyToOne
    @JoinColumn(name = "depends_on_task_id", nullable = false)
    private TaskEntity dependsOnTask;
}