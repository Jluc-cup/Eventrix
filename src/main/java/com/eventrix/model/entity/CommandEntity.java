package com.eventrix.model.entity;

import com.eventrix.base.util.DateTimeUtil;
import com.eventrix.model.localobj.CommandCreateObj;
import com.eventrix.model.localobj.CommandUpdateObj;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "command")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "command_id_seq")
    @SequenceGenerator(name = "command_id_seq", sequenceName = "command_id_seq", allocationSize = 100)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "logic", nullable = false)
    private String logic;

    @Column(name = "target_service", nullable = false)
    private String targetService;

    @Column(name = "description")
    private String description;

    @Column(name = "created", nullable = false)
    private Instant created;

    @Column(name = "updated")
    private Instant updated;

    @Column(name = "deleted")
    private Instant deleted;

    public CommandEntity(CommandCreateObj obj) {
        logic = obj.logic();
        targetService = obj.targetService();
        description = obj.description();
        created = DateTimeUtil.getCurrentTime();
    }

    public CommandEntity update(CommandUpdateObj obj) {
        return new CommandEntity(
                this.id,
                obj.logic(),
                obj.targetService(),
                obj.description(),
                this.created,
                DateTimeUtil.getCurrentTime(),
                this.deleted
        );
    }

    public CommandEntity delete() {
        return new CommandEntity(
                this.id,
                this.logic,
                this.targetService,
                this.description,
                this.created,
                this.updated,
                DateTimeUtil.getCurrentTime()
        );
    }
}