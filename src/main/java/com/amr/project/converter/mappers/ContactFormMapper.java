package com.amr.project.converter.mappers;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.ContactFormDto;
import com.amr.project.model.entity.ContactForm;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface ContactFormMapper extends MapperInterface<ContactFormDto, ContactForm> {

}