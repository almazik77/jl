package ru.itis.hateoasproject.services;

import ru.itis.hateoasproject.models.MedicalCard;

public interface MedicalCardService {
    MedicalCard confirm(Long medicalCardId);
}
