package ru.itis.mongo.hateoas.jpa.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.mongo.hateoas.jpa.models.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
}
