package com.alfred.tracking.trackingservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diet_plans")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class DietPlan {
    @Id
    @SequenceGenerator(
            name = "plan_id_sequence",
            sequenceName = "plan_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plan_id_sequence"
    )
    private Integer id;

    @Column(nullable = false)
    private Integer patientId;

    private String meal;

    private int calories;

    private int carbohydrates;

    private int proteins;

    private int fats;


}
