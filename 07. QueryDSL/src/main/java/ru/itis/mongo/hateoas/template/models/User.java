package ru.itis.mongo.hateoas.template.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collation = "users")
public class User {
    @Id
    private String _id;

    private String firstName;
    private String lastName;

    private LocalDateTime createdAt;

    private MedicalCard medicalCard;

    private List<Appointment> appointments;
}
