package com.eventrix.model.entity;

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
}