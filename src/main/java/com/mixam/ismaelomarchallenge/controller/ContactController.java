package com.mixam.ismaelomarchallenge.controller;

import com.mixam.ismaelomarchallenge.domain.FormData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {

    private final Logger log = LogManager.getLogger(ContactController.class);

    @GetMapping("/")
    public String contactForm(Model model, @ModelAttribute FormData formData) {
        model.addAttribute("fragmentLocation", "fragments/form");
        model.addAttribute("fragmentName", "form");
        return "contact";
    }

    @PostMapping("/validateForm")
    public String submitForm(Model model, @ModelAttribute FormData formData) {
        log.info("Form Submitted");
        log.info("Name -------- " + formData.getName());
        log.info("Email -------- " + formData.getEmail());
        log.info("Phone Number -------- " + formData.getPhoneNumber());
        log.info("Message -------- " + formData.getMessage());
        if (!formData.getOrderNumber().isEmpty()) {
            log.info("Order No. -------- " + formData.getOrderNumber());
        }
        if (!formData.getCompanyName().isEmpty()) {
            log.info("Company Name -------- " + formData.getCompanyName());
        }
        model.addAttribute("fragmentLocation", "fragments/form_submitted");
        model.addAttribute("fragmentName", "form_submitted");
        return "contact";
    }
}
