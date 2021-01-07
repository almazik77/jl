package ru.itis.hateoasproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usr")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name="med_card_id")
    private MedicalCard medicalCard;

    @OneToMany(mappedBy = "user")
    private List<Appointment> appointments;
}
