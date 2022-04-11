package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.ContactFormMapper;
import com.amr.project.dao.ContactFormRepository;
import com.amr.project.model.dto.ContactFormDto;
import com.amr.project.model.entity.ContactForm;
import com.amr.project.service.abstracts.ContactFormService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailContactFormAssistant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ContactFormServiceImpl implements ContactFormService {

    private final ContactFormRepository contactFormRepository;
    private final ContactFormMapper contactFormMapper;
    private final MailSender mailSender;
    private final EmailContactFormAssistant emailContactFormAssistant;

    @Override
    public List<ContactFormDto> getAllContactForms() {
        List<ContactForm> contactForms = contactFormRepository.findAll();
        return contactFormMapper.toDtoList(contactForms, new CycleAvoidingMappingContext());
    }

    @Override
    public ContactFormDto getContactFormById(Long id) {
        ContactForm contactForm = contactFormRepository.getById(id);
        return contactFormMapper.toDto(contactForm, new CycleAvoidingMappingContext());
    }

    @Override
    public void saveContactForm(ContactFormDto contactFormDto) {
        ContactForm contactForm = contactFormMapper.toEntity(contactFormDto, new CycleAvoidingMappingContext());
        contactForm.setDateTime(LocalDate.now().atTime(LocalTime.now()));
        contactFormRepository.saveAndFlush(contactForm);
    }

    @Override
    public void deleteContactFormById(Long id) {
        contactFormRepository.deleteById(id);

    }
    @Override
    public void updateContactForm(ContactFormDto contactFormDto) {
        ContactForm contactForm = contactFormMapper.toEntity(contactFormDto, new CycleAvoidingMappingContext());
        if (contactForm.getAnswer() != null) {
            contactForm.setModerated(true);
        } else {
            contactForm.setModerated(false);
        }
        mailSender.send(emailContactFormAssistant.trackedEmailContactFormUpdate(contactForm));
        contactFormRepository.saveAndFlush(contactForm);
    }
}
