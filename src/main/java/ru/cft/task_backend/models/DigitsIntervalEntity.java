package ru.cft.task_backend.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "digits_interval_entity")
public class DigitsIntervalEntity {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "start_value", nullable = false)
    private Integer start;

    @Column(name = "end_value", nullable = false)
    private Integer end;
}