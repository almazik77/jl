package ru.itis;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Input {
    String type;
    String name;
    String placeholder;
}
