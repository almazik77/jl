package ru.itis.hateoasproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoasproject.models.Doctor;

import java.util.List;

public interface DoctorsRepository extends JpaRepository<Doctor,Long> {
    @RestResource(path = "bySpecialty", rel = "specialty")
    List<Doctor> findAllBySpecialty(Doctor.Specialty specialty);
}
