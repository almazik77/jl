package ru.itis.mongo.hateoas.jpa.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.mongo.hateoas.jpa.models.MedicalCard;
import ru.itis.mongo.hateoas.jpa.repositories.MedicalCardRepository;
import ru.itis.mongo.hateoas.jpa.services.MedicalCardService;

import java.util.Optional;

@Service
public class MedicalCardServiceImpl implements MedicalCardService {
    @Autowired
    private MedicalCardRepository medicalCardsRepository;

    @Override
    public MedicalCard confirm(String medicalCardId) {
        Optional<MedicalCard> medicalCardOptional = medicalCardsRepository.findById(medicalCardId);
        if (medicalCardOptional.isPresent()) {
            MedicalCard medicalCard = medicalCardOptional.get();
            medicalCard.setStatus(MedicalCard.Status.CONFIRMED);
            medicalCardsRepository.save(medicalCard);
            return medicalCard;
        }
        throw new IllegalArgumentException("no medical card with id=" + medicalCardId);
    }
}
