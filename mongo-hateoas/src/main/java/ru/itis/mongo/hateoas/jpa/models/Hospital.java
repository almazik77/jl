package ru.itis.mongo.hateoas.jpa.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "hospitals")
@Builder
public class Hospital {
    @Id
    private String _id;

    private String address;
    private String name;

    private List<Doctor> doctors;
}
