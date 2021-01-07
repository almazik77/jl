package ru.itis.hateoasproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoasproject.services.AppointmentService;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;


    @RequestMapping(value = "/appointments/{appointment-id}/cancel", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> cancel(@PathVariable("appointment-id") Long appointmentId) {
        return ResponseEntity.ok(
                EntityModel.of(appointmentService.cancel(appointmentId)));
    }
}
