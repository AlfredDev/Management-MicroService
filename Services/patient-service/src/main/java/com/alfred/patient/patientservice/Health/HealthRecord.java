package com.alfred.patient.patientservice.Health;

import com.alfred.patient.patientservice.patient.Patient;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EntityListeners(EntityListeners.class)
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private double weight;
    private double height;
    private String chronicCondition;
    @LastModifiedDate
    @Column(insertable = false, updatable = false)
    private LocalDateTime updateAt;

    @Transient
    public double getBmi() {
        if (height > 0) {
            return weight / (height * height);
        } else {
            return 0;
        }
    }

    @ManyToMany
    @JoinTable(
            name = "record_allergics",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "allergic_id")
    )
    private Set<Allergic> allergics;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
