package com.alfred.tracking.trackingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ActivityLog {
    @Id
    @SequenceGenerator(
            name = "activity_id_sequence",
            sequenceName = "activity_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activity_id_sequence"
    )
    private Integer id;


    @Column(name = "patient_id", nullable = false)
    private Integer patientId;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Column(nullable = false)
    private int duration;

    @Column(name = "calories_burned",
            nullable = false)
    private int caloriesBurned;
}
