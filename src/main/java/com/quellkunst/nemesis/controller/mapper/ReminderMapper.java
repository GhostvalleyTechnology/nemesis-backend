package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.Reminder;
import com.quellkunst.nemesis.service.dto.ReminderDto;
import org.mapstruct.Mapper;

@Mapper(
    config = QuarkusMappingConfig.class,
    uses = {ClientMapper.class})
public interface ReminderMapper {

  ReminderDto toDto(Reminder entity);
}
