package com.eventrix.model.entity;

import com.eventrix.model.localobj.TaskTopicCreateObj;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_topic")
public class TaskTopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true, nullable = false)
    @Access(value = AccessType.PROPERTY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @Column(name = "priority")
    private Long priority;

    public TaskTopicEntity(TaskTopicCreateObj taskTopicCreateObj) {
        name = taskTopicCreateObj.name();
        description = taskTopicCreateObj.description();
        isActive = false;
        priority = taskTopicCreateObj.priority();
    }
}
