package ru.itis.hateoasproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoasproject.models.Appointment;
import ru.itis.hateoasproject.services.AppointmentService;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AppointmentTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        when(appointmentService.cancel(1L)).thenReturn(appointmentToCancel());
    }

    @Test
    public void cardConfirmTest() throws Exception {
        mockMvc.perform(put("/appointments/1/cancel")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canceled").value(appointmentToCancel().getCanceled()))
                .andExpect(jsonPath("$.message").value(appointmentToCancel().getMessage()))
                .andDo(document("cancel_appointment", responseFields(
                        fieldWithPath("canceled").description("Информация отменена ли запись"),
                        fieldWithPath("message").description("Информация для доктора из сообщения"),
                        fieldWithPath("id").ignored(),
                        fieldWithPath("user").ignored(),
                        fieldWithPath("doctor").ignored(),
                        fieldWithPath("startDateTime").ignored(),
                        fieldWithPath("endDateTime").ignored()
                )));
    }

    private Appointment appointmentToCancel() {
        return Appointment.builder()
                .id(1L)
                .canceled(true)
                .message("Hurts")
                .build();
    }
}
