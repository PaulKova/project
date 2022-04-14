package com.amr.project.service.abstracts;

import com.amr.project.model.dto.ContactFormDto;

import java.util.List;

public interface ContactFormService {
    List<ContactFormDto> getAllContactForms();

    ContactFormDto getContactFormById(Long id);

    void saveContactForm(ContactFormDto contactFormDto);

    void deleteContactFormById(Long id);

    void updateContactForm(ContactFormDto contactFormDto);
}
