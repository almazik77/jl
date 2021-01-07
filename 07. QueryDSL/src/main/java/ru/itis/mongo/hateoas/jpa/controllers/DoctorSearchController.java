package ru.itis.mongo.hateoas.jpa.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.mongo.hateoas.jpa.dto.DoctorDto;
import ru.itis.mongo.hateoas.jpa.repositories.DoctorsRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class DoctorSearchController {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @GetMapping("/doctors/search")
    public ResponseEntity<List<DoctorDto>> searchByPredicate(Predicate predicate) {
        return ResponseEntity.ok(
                StreamSupport.stream(doctorsRepository.findAll(predicate).spliterator(), true)
                        .map(doctor -> DoctorDto.builder()
                                .firstName(doctor.getFirstName())
                                .lastName(doctor.getLastName())
                                .build())
                        .collect(Collectors.toList())
        );
    }
}
