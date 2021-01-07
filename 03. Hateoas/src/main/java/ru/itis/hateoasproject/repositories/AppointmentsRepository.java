package ru.itis.hateoasproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.hateoasproject.models.Appointment;

@RepositoryRestResource
public interface AppointmentsRepository extends PagingAndSortingRepository<Appointment,Long> {
}
