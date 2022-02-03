package com.quellkunst.nemesis.service.dto;

import com.quellkunst.nemesis.model.ProofOfIdentity;
import com.quellkunst.nemesis.model.ProofOfIdentityType;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RegisterForReflection
public class ProofOfIdentityDto extends AbstractEntityDto<ProofOfIdentity> {
  ProofOfIdentityType type;

  protected ProofOfIdentityDto(ProofOfIdentity entity) {
    super(entity);
  }

  public static ProofOfIdentityDto of(ProofOfIdentity entity) {
    var dto = new ProofOfIdentityDto(entity);
    dto.type = entity.type;
    return dto;
  }

  @Override
  protected ProofOfIdentity prepareNewEntity() {
    var entity = new ProofOfIdentity();
    entity.type = type;
    return entity;
  }

  @Override
  protected ProofOfIdentity prepareUpdateEntity() {
    var entity = ProofOfIdentity.byId(id);
    entity.type = type;
    return entity;
  }

  @Override
  public ProofOfIdentity getEntity() {
    return ProofOfIdentity.byId(id);
  }
}
