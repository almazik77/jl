package ru.itis.mongo.hateoas.jpa.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.itis.mongo.hateoas.jpa.models.User;

public interface UserRepository extends MongoRepository<User, String> {
}
