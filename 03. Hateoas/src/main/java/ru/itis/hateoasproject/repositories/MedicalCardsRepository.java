package ru.itis.hateoasproject.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoasproject.models.MedicalCard;

@RepositoryRestResource
public interface MedicalCardsRepository extends PagingAndSortingRepository<MedicalCard, Long>  {
    @RestResource(path = "not_confirmed", rel = "not_confirmed")
    @Query("from MedicalCard  card where card.status = 'NOT_CONFIRMED'")
    Page<MedicalCard> findAllNotConfirmed(Pageable pageable);
}
