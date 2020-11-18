package ru.itis.hateoasproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoasproject.models.*;
import ru.itis.hateoasproject.repositories.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class HateoasProjectApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasProjectApplication.class, args);
       /* MedicalCardsRepository medicalCardsRepository = context.getBean(MedicalCardsRepository.class);
        DoctorsRepository doctorsRepository = context.getBean(DoctorsRepository.class);
        HospitalsRepository hospitalsRepository = context.getBean(HospitalsRepository.class);
        AppointmentsRepository appointmentsRepository = context.getBean(AppointmentsRepository.class);
        UsersRepository usersRepository = context.getBean(UsersRepository.class);

        User user1 = User.builder()
                .firstName("user1FirstName")
                .lastName("user1LastName")
                .createdAt(LocalDateTime.now())
                .build();

        usersRepository.save(user1);

        MedicalCard medicalCard1 = MedicalCard.builder()
                .user(user1)
                .status(MedicalCard.Status.CONFIRMED)
                .info("info")
                .build();

        medicalCardsRepository.save(medicalCard1);

        user1.setMedicalCard(medicalCard1);


        User user2 = User.builder()
                .firstName("user2FirstName")
                .lastName("user2LastName")
                .createdAt(LocalDateTime.now())
                .build();

        usersRepository.save(user2);

        MedicalCard medicalCard = MedicalCard.builder()
                .user(user2)
                .status(MedicalCard.Status.NOT_CONFIRMED)
                .info("info")
                .build();

        medicalCardsRepository.save(medicalCard);

        user2.setMedicalCard(medicalCard);

        Hospital hospital = Hospital.builder()
                .address("address")
                .name("hospital1")
                .build();

        hospitalsRepository.save(hospital);

        Doctor doctor1 = Doctor.builder()
                .firstName("doctor1FirstName")
                .lastName("doctor1LastName")
                .office("1a")
                .specialty(Doctor.Specialty.DENTIST)
                .hospital(hospital)
                .build();

        Doctor doctor2 = Doctor.builder()
                .firstName("doctor2FirstName")
                .lastName("doctor2LastName")
                .office("2b")
                .specialty(Doctor.Specialty.UROLOGIST)
                .hospital(hospital)
                .build();

        doctorsRepository.save(doctor1);
        doctorsRepository.save(doctor2);

        Appointment appointmentDoctor1 = Appointment.builder()
                .doctor(doctor1)
                .user(user1)
                .startDateTime(LocalDateTime.of(2020, 11, 1, 15, 0))
                .endDateTime(LocalDateTime.of(2020,11,1,15,30))
                .build();
        Appointment appointmentDoctor2 = Appointment.builder()
                .doctor(doctor2)
                .user(user1)
                .startDateTime(LocalDateTime.of(2020, 11, 2, 15, 0))
                .endDateTime(LocalDateTime.of(2020, 11, 2, 15, 30))
                .build();
        appointmentsRepository.save(appointmentDoctor1);
        appointmentsRepository.save(appointmentDoctor2);

        hospital.setDoctors(List.of(doctor1, doctor2));
        user1.setAppointments(List.of(appointmentDoctor1, appointmentDoctor2));

        doctor1.setAppointments(Collections.singletonList(appointmentDoctor1));
        doctor2.setAppointments(Collections.singletonList(appointmentDoctor2));

        usersRepository.save(user1);
        usersRepository.save(user2);
        doctorsRepository.save(doctor1);
        doctorsRepository.save(doctor2);
        hospitalsRepository.save(hospital);

        appointmentsRepository.save(appointmentDoctor1);
        appointmentsRepository.save(appointmentDoctor2);*/
    }

}
