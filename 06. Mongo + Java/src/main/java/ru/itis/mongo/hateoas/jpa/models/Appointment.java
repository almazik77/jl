package ru.itis.mongo.hateoas.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Document(collection = "appointments")
@Builder
public class Appointment {
    @Id
    private String _id;

    private User user;

    private Doctor doctor;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private String message;

    private Boolean canceled;
}
