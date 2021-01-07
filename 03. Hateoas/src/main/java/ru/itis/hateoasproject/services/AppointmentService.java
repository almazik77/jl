package ru.itis.hateoasproject.services;

import ru.itis.hateoasproject.models.Appointment;

public interface AppointmentService {
    Appointment cancel(Long appointmentId);
}
