package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.GenericPerson;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class GenericPersonDto extends AbstractPersonDto<GenericPerson> {

  protected GenericPersonDto(GenericPerson entity) {
    super(entity);
  }

  public static GenericPersonDto of(GenericPerson entity) {
    var dto = new GenericPersonDto(entity);
    mapEntityToDto(dto, entity);
    return dto;
  }

  @Override
  protected GenericPerson prepareNewEntity() {
    return mapPersonValues(new GenericPerson());
  }

  @Override
  protected GenericPerson prepareUpdateEntity() {
    return mapPersonValues(GenericPerson.byId(id));
  }
}
