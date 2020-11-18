package ru.itis.hateoasproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasproject.models.User;

public interface UsersRepository extends JpaRepository<User,Long> {
}
