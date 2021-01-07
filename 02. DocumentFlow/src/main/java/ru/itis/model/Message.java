package ru.itis.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Message {
    String name;
    String surname;
    String passport;
    Integer age;
    List<String> filesPath;
    String email;
}
