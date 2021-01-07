package ru.itis.hateoasproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoasproject.models.MedicalCard;
import ru.itis.hateoasproject.repositories.MedicalCardsRepository;
import ru.itis.hateoasproject.services.MedicalCardService;

import java.util.Optional;

@Service
public class MedicalCardServiceImpl implements MedicalCardService {
    @Autowired
    private MedicalCardsRepository medicalCardsRepository;

    @Override
    public MedicalCard confirm(Long medicalCardId) {
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
