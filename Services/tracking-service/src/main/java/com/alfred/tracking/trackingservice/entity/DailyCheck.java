package com.alfred.tracking.trackingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "daily-checks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DailyCheck {
    @Id
    @SequenceGenerator(
            name = "dailyCheck_id_sequence",
            sequenceName = "dailyCheck_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dailyCheck_id_sequence"
    )
    private Integer id;

    @Column(nullable = false)
    private Integer patientId;


    private int calorieIntake;

    private double waterIntake;

    private String activityLevel;

    private double weight;

    @Temporal(TemporalType.DATE)
    private LocalDate date;
}
