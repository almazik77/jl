package ru.itis.mongo.hateoas.jpa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.mongo.hateoas.jpa.models.MedicalCard;

public interface MedicalCardRepository extends MongoRepository<MedicalCard, String> {
    @RestResource(path = "not_confirmed", rel = "not_confirmed")
    @Query("from MedicalCard  card where card.status = 'NOT_CONFIRMED'")
    Page<MedicalCard> findAllNotConfirmed(Pageable pageable);
}
