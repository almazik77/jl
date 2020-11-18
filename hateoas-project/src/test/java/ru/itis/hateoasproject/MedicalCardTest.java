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
import ru.itis.hateoasproject.models.MedicalCard;
import ru.itis.hateoasproject.services.MedicalCardService;

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
public class MedicalCardTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalCardService medicalCardService;

    @BeforeEach
    public void setUp() {
        when(medicalCardService.confirm(1L)).thenReturn(confirmedCard());
    }

    @Test
    public void cardConfirmTest() throws Exception {
        mockMvc.perform(put("/medicalCards/1/confirm")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info").value(confirmedCard().getInfo()))
                .andExpect(jsonPath("$.status").value(confirmedCard().getStatus().toString()))
                .andDo(document("confirm_med_card", responseFields(
                        fieldWithPath("info").description("Информация из карты"),
                        fieldWithPath("status").description("status")
                )));
    }

    private MedicalCard confirmedCard() {
        return MedicalCard.builder()
                .id(1L)
                .info("info")
                .status(MedicalCard.Status.CONFIRMED)
                .build();
    }

}
