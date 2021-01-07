package ru.itis.mongo.hateoas.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDto {
    private String firstName;
    private String lastName;


}
