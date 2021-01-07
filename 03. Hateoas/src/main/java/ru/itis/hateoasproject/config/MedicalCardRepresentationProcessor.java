package ru.itis.hateoasproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoasproject.controllers.MedicalCardController;
import ru.itis.hateoasproject.models.MedicalCard;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MedicalCardRepresentationProcessor implements RepresentationModelProcessor<EntityModel<MedicalCard>> {
    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<MedicalCard> process(EntityModel<MedicalCard> model) {
        MedicalCard medicalCard = model.getContent();
        if (medicalCard != null && medicalCard.getStatus() == MedicalCard.Status.NOT_CONFIRMED){
            model.add(linkTo(methodOn(MedicalCardController.class).confirm(medicalCard.getId())).withRel("confirm"));
        }
        return model;
    }
}
