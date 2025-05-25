package com.eventrix.model.entity;

import com.eventrix.base.util.DateTimeUtil;
import com.eventrix.model.localobj.TaskTopicCreateObj;
import com.eventrix.model.localobj.TaskTopicUpdateObj;
import com.eventrix.model.localobj.TaskTopicUpdateStatusObj;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

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

    @Column(name = "deleted")
    private Instant deleted;

    public TaskTopicEntity(TaskTopicCreateObj taskTopicCreateObj) {
        name = taskTopicCreateObj.name();
        description = taskTopicCreateObj.description();
        isActive = false;
        priority = taskTopicCreateObj.priority();
    }


    public TaskTopicEntity update(TaskTopicUpdateObj obj) {
        return new TaskTopicEntity(
                this.id,
                obj.name(),
                obj.description(),
                this.isActive,
                obj.priority(),
                this.deleted
        );
    }

    public TaskTopicEntity updateStatus(TaskTopicUpdateStatusObj obj) {
        return new TaskTopicEntity(
                this.id,
                this.name,
                this.description,
                obj.isActive(),
                this.priority,
                this.deleted
        );
    }

    public TaskTopicEntity delete() {
        return new TaskTopicEntity(
                this.id,
                this.name,
                this.description,
                this.isActive,
                this.priority,
                DateTimeUtil.getCurrentTime()
        );
    }
}
