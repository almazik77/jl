package ru.itis.mongo.hateoas.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.mongo.hateoas.jpa.services.MedicalCardService;

@RepositoryRestController
public class MedicalCardController {
    @Autowired
    private MedicalCardService medicalCardService;

    @RequestMapping(value = "/medicalCards/{medical-card-id}/confirm", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> confirm(@PathVariable("medical-card-id") String cardId) {
        return ResponseEntity.ok(
                EntityModel.of(medicalCardService.confirm(cardId)));
    }

}
