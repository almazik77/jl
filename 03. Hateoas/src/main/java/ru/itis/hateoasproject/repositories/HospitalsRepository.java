package ru.itis.hateoasproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasproject.models.Hospital;

public interface HospitalsRepository extends JpaRepository<Hospital,Long> {
}
