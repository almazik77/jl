package ru.itis.hateoasproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    private String office;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    public static enum Specialty {
        DENTIST, DERMATOLOGIST, UROLOGIST
    }
}
