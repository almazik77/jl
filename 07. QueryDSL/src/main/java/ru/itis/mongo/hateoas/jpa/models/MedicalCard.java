package ru.itis.mongo.hateoas.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@Document(collection = "medical_cards")
@Builder
public class MedicalCard {
    @Id
    private String _id;

    private String info;

    private User user;

    private Status status;

    public static enum Status {
        CONFIRMED, NOT_CONFIRMED
    }
}
