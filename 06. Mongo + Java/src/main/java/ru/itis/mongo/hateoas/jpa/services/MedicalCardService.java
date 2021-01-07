package ru.itis.mongo.hateoas.jpa.services;


import ru.itis.mongo.hateoas.jpa.models.MedicalCard;

public interface MedicalCardService {
    MedicalCard confirm(String medicalCardId);
}
