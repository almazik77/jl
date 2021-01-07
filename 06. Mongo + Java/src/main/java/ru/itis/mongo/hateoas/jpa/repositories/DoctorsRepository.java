package ru.itis.mongo.hateoas.jpa.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.itis.mongo.hateoas.jpa.models.Doctor;

public interface DoctorsRepository extends MongoRepository<Doctor, String>, QuerydslPredicateExecutor<Doctor> {
}
