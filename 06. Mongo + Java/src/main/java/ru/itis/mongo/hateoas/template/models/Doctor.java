package ru.itis.mongo.hateoas.template.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "doctors")
@Builder
public class Doctor {
    @Id
   private Long id;

    private String firstName;
    private String lastName;

    private List<Appointment> appointments;

    private Hospital hospital;

    private String office;

    private Specialty specialty;

    public static enum Specialty {
        DENTIST, DERMATOLOGIST, UROLOGIST
    }
}
