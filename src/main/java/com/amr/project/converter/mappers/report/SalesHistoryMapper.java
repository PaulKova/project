package com.amr.project.converter.mappers.report;

import com.amr.project.converter.MapperInterface;
import com.amr.project.model.dto.report.SalesHistoryDto;
import com.amr.project.model.entity.report.SalesHistory;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface SalesHistoryMapper extends MapperInterface<SalesHistoryDto, SalesHistory> {

}
