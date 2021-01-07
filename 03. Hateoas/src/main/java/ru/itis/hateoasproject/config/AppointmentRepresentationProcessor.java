package ru.itis.hateoasproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoasproject.controllers.AppointmentController;
import ru.itis.hateoasproject.controllers.MedicalCardController;
import ru.itis.hateoasproject.models.Appointment;
import ru.itis.hateoasproject.models.MedicalCard;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AppointmentRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Appointment>> {
    @Autowired
    private RepositoryEntityLinks links;
    @Override
    public EntityModel<Appointment> process(EntityModel<Appointment> model) {
        Appointment appointment = model.getContent();
        if (appointment != null && appointment.getCanceled() == false){
            model.add(linkTo(methodOn(AppointmentController.class).cancel(appointment.getId())).withRel("cancel"));
        }
        return model;
    }
}
