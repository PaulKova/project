package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.ContactFormMapper;
import com.amr.project.dao.ContactFormRepository;
import com.amr.project.model.dto.ContactFormDto;
import com.amr.project.model.entity.ContactForm;
import com.amr.project.service.abstracts.ContactFormService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailContactFormAssistant;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class ContactFormServiceImplTest {
    @Mock
    private ContactFormRepository contactFormRepository;
    @Mock
    private ContactFormMapper contactFormMapper;
    @Mock
    private MailSender mailSender;
    @Mock
    private EmailContactFormAssistant emailContactFormAssistant;

    private final ContactFormService contactFormService;

    public ContactFormServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.contactFormService = new ContactFormServiceImpl(contactFormRepository, contactFormMapper, mailSender, emailContactFormAssistant);
    }

    @Test
    void getAllContactForms() {

        ContactForm contactForm1 = ContactForm.builder()
                .id(1L)
                .name("Oleg")
                .email("Oleg@mail.ru")
                .text("i have a suggestion")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();
        ContactForm contactForm2 = ContactForm.builder()
                .id(2L)
                .name("Dima")
                .email("Dima@mail.ru")
                .text("i have a complaint")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();

        List<ContactForm> contactFormList = new ArrayList<>(List.of(contactForm1, contactForm2));

        ContactFormDto contactFormDto1 = ContactFormDto.builder()
                .id(1L)
                .name("Oleg")
                .email("Oleg@mail.ru")
                .text("i have a suggestion")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();
        ContactFormDto contactFormDto2 = ContactFormDto.builder()
                .id(2L)
                .name("Dima")
                .email("Dima@mail.ru")
                .text("i have a complaint")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();

        List<ContactFormDto> contactFormDtoList = new ArrayList<>(List.of(contactFormDto1, contactFormDto2));

        given(contactFormRepository.findAll())
                .willReturn(contactFormList);

        given(contactFormMapper.toDtoList(eq(contactFormList), any(CycleAvoidingMappingContext.class)))
                .willReturn(contactFormDtoList);

        assertThat(contactFormService.getAllContactForms()).containsExactlyInAnyOrderElementsOf(new ArrayList<>(List.of(contactFormDto1, contactFormDto2)));
        assertThat(contactFormService.getAllContactForms()).contains(contactFormDto1);
    }

    @Test
    void getContactFormById() {

        ContactForm contactForm1 = ContactForm.builder()
                .id(1L)
                .name("Oleg")
                .email("Oleg@mail.ru")
                .text("i have a suggestion")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();
        ContactForm contactForm2 = ContactForm.builder()
                .id(2L)
                .name("Dima")
                .email("Dima@mail.ru")
                .text("i have a complaint")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();

        ContactFormDto contactFormDto1 = ContactFormDto.builder()
                .id(1L)
                .name("Oleg")
                .email("Oleg@mail.ru")
                .text("i have a suggestion")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();
        ContactFormDto contactFormDto2 = ContactFormDto.builder()
                .id(2L)
                .name("Dima")
                .email("Dima@mail.ru")
                .text("i have a complaint")
                .dateTime(LocalDate.now().atTime(LocalTime.now()))
                .answer(null)
                .isModerated(false)
                .build();

        given(contactFormRepository.getById(1L))
                .willReturn(contactForm1);

        given(contactFormRepository.getById(2L))
                .willReturn(contactForm2);

        given(contactFormMapper.toDto(eq(contactForm1), any(CycleAvoidingMappingContext.class)))
                .willReturn(contactFormDto1);

        given(contactFormMapper.toDto(eq(contactForm2), any(CycleAvoidingMappingContext.class)))
                .willReturn(contactFormDto2);

        assertThat(contactFormService.getContactFormById(1L))
                .isEqualTo(contactFormDto1);
        assertThat(contactFormService.getContactFormById(2L))
                .isEqualTo(contactFormDto2);
    }
}
