package com.amr.project.service.impl;

import com.amr.project.converter.CycleAvoidingMappingContext;
import com.amr.project.converter.mappers.PersonalDataMapper;
import com.amr.project.dao.PersonalDataRepository;
import com.amr.project.dao.UserRepository;
import com.amr.project.model.dto.PersonalDataDto;
import com.amr.project.model.entity.PersonalData;
import com.amr.project.model.enums.PersonalDataStatus;
import com.amr.project.service.abstracts.PersonalDataService;
import com.amr.project.service.email.MailSender;
import com.amr.project.util.EmailUserAssistant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
class PersonalDataServiceImplTest {
    @Mock
    private PersonalDataRepository personalDataRepository;
    @Mock
    private PersonalDataMapper personalDataMapper;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MailSender mailSender;
    @Mock
    private EmailUserAssistant emailUserAssistant;

    private final PersonalDataService personalDataService;

    public PersonalDataServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.personalDataService = new PersonalDataServiceImpl(personalDataRepository, personalDataMapper,
                userRepository, mailSender, emailUserAssistant);

    }

    @ParameterizedTest
    @ValueSource(strings = {"WAITING", "CONFIRMED", "REJECTED"})
    void getByStatus(PersonalDataStatus input) {

        PersonalData personalData1 = PersonalData.builder()
                .id(1L)
                .passport(1234567890)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("МВД Москвы")
                .placeOfBirth("Москва")
                .listOfImages(null)
                .status(input)
                .build();
        PersonalData personalData2 = PersonalData.builder()
                .id(2L)
                .passport(1234567891)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("Мвд Новосибирска")
                .placeOfBirth("Новосибирск")
                .listOfImages(null)
                .status(input)
                .build();

        List<PersonalData> personalDataList = new ArrayList<>(List.of(personalData1, personalData2));

        PersonalDataDto personalDataDto1 = PersonalDataDto.builder()
                .id(1L)
                .passport(1234567890)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("МВД Москвы")
                .placeOfBirth("Москва")
                .listOfImages(null)
                .status(input)
                .build();
        PersonalDataDto personalDataDto2 = PersonalDataDto.builder()
                .id(2L)
                .passport(1234567891)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("Мвд Новосибирска")
                .placeOfBirth("Новосибирск")
                .listOfImages(null)
                .status(input)
                .build();

        List<PersonalDataDto> personalDataDtoList = new ArrayList<>(List.of(personalDataDto1, personalDataDto2));

        given(personalDataRepository.getByStatus(input))
                .willReturn(personalDataList);

        given(personalDataMapper.toDtoList(eq(personalDataList), any(CycleAvoidingMappingContext.class)))
                .willReturn(personalDataDtoList);

        assertThat(personalDataService.getByStatus(input)).containsExactlyInAnyOrderElementsOf(new ArrayList<>(List.of(personalDataDto1, personalDataDto2)));
        assertThat(personalDataService.getByStatus(input)).contains(personalDataDto1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3"})
    void getPersonalDataById(Long input) {

        PersonalData personalData1 = PersonalData.builder()
                .id(input)
                .passport(1234567890)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("МВД Москвы")
                .placeOfBirth("Москва")
                .listOfImages(null)
                .status(PersonalDataStatus.WAITING)
                .build();

        PersonalDataDto personalDataDto1 = PersonalDataDto.builder()
                .id(input)
                .passport(1234567890)
                .dateOfIssue(new Date(System.currentTimeMillis()))
                .authority("МВД Москвы")
                .placeOfBirth("Москва")
                .listOfImages(null)
                .status(PersonalDataStatus.WAITING)
                .build();

        given(personalDataRepository.getById(input))
                .willReturn(personalData1);


        given(personalDataMapper.toDto(eq(personalData1), any(CycleAvoidingMappingContext.class)))
                .willReturn(personalDataDto1);


        assertThat(personalDataService.getPersonalDataById(input))
                .isEqualTo(personalDataDto1);

    }
}
