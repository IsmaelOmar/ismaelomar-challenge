package com.mixam.ismaelomarchallenge.controller;

import com.mixam.ismaelomarchallenge.domain.FormData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void loadContactFormPage_success() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("contact"))
                .andExpect(MockMvcResultMatchers.model().attribute("fragmentLocation", "fragments/form"))
                .andExpect(MockMvcResultMatchers.model().attribute("fragmentName", "form"));
    }

    @Test
    void loadValidatedFormPage_withOnlyRequiredFields() throws Exception {
        FormData formData = FormData.builder()
                .name("Ismael")
                .email("ismaelomar.work@gmail.com")
                .phoneNumber("15555555555")
                .message("My Order did not arrive")
                .orderNumber("")
                .companyName("")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/validateForm").flashAttr("formData", formData))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("contact"))
                .andExpect(MockMvcResultMatchers.model().attribute("fragmentLocation", "fragments/form_submitted"))
                .andExpect(MockMvcResultMatchers.model().attribute("fragmentName", "form_submitted"));
    }

    @Test
    void loadValidatedFormPage_withAllFields() throws Exception {
        FormData formData = FormData.builder()
                .name("Ismael")
                .email("ismaelomar.work@gmail.com")
                .phoneNumber("15555555555")
                .message("My Order did not arrive")
                .orderNumber("1234")
                .companyName("Mixam")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/validateForm").flashAttr("formData", formData))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("contact"))
                .andExpect(MockMvcResultMatchers.model().attribute("fragmentLocation", "fragments/form_submitted"))
                .andExpect(MockMvcResultMatchers.model().attribute("fragmentName", "form_submitted"));
    }
}
