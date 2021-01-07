package ru.itis.hateoasproject.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.hateoasproject.models.Appointment;
import ru.itis.hateoasproject.repositories.AppointmentsRepository;
import ru.itis.hateoasproject.services.AppointmentService;

import java.util.Optional;
@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Override
    public Appointment cancel(Long appointmentId) {
        Optional<Appointment> appointment = appointmentsRepository.findById(appointmentId);
        if (appointment.isPresent()) {
            appointment.get().setCanceled(true);
            appointmentsRepository.save(appointment.get());
            return appointment.get();
        }
        throw new IllegalArgumentException("no appointment with id = " + appointmentId);
    }
}
