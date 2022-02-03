package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.PartnerLogin;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class PartnerLoginDto extends AbstractEntityDto<PartnerLogin> {
  String link;
  String username;
  String password;
  boolean adminOnly;

  protected PartnerLoginDto(PartnerLogin entity) {
    super(entity);
  }

  public static PartnerLoginDto of(PartnerLogin entity) {
    var dto = new PartnerLoginDto(entity);
    dto.link = entity.link;
    dto.username = entity.username;
    dto.password = entity.password;
    dto.adminOnly = entity.adminOnly;
    return dto;
  }

  @Override
  public PartnerLogin prepareNewEntity() {
    return mapValues(new PartnerLogin());
  }

  @Override
  public PartnerLogin prepareUpdateEntity() {
    return mapValues(PartnerLogin.byId(id));
  }

  @Override
  public PartnerLogin getEntity() {
    return PartnerLogin.byId(id);
  }

  private PartnerLogin mapValues(PartnerLogin entity) {
    entity.link = link;
    entity.username = username;
    entity.password = password;
    entity.adminOnly = adminOnly;
    return entity;
  }
}
