package com.alfred.patient.patientservice.patient;

import com.alfred.patient.patientservice.Health.HealthRecord;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    @Column(unique = true)
    private String email;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "health_record_id", referencedColumnName = "id")
    private HealthRecord healthRecord;
}
