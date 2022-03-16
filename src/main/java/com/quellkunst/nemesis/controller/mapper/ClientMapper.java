package com.quellkunst.nemesis.controller.mapper;

import com.quellkunst.nemesis.model.Client;
import com.quellkunst.nemesis.model.Client_;
import com.quellkunst.nemesis.model.Employee_;
import com.quellkunst.nemesis.service.dto.ClientDto;
import com.quellkunst.nemesis.service.dto.ClientReferenceDto;
import com.quellkunst.nemesis.service.dto.GenericPersonDto;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.mapstruct.*;

@Mapper(
    config = QuarkusMappingConfig.class,
    uses = {
      GenericPersonMapper.class,
      ClientContractMapper.class,
      ClientDocumentMapper.class,
      ProofOfIdentityMapper.class,
      EmployeeMapper.class
    })
public interface ClientMapper {
  @Mappings({
    @Mapping(target = Client_.SUPERVISOR, source = Client_.SUPERVISOR + "." + Employee_.NAME)
  })
  ClientDto toDto(Client client);

  GenericPersonDto toGenericDto(Client client);

  @Mappings({
    @Mapping(target = Client_.SUPERVISOR, source = Client_.SUPERVISOR + "." + Employee_.NAME)
  })
  ClientReferenceDto toReferenceDto(Client client);

  @Mappings({
    @Mapping(target = Client_.SUPERVISOR, qualifiedBy = EmployeeMapper.GetEmployeeByContext.class),
    @Mapping(target = Client_.CONTRACTS, ignore = true),
    @Mapping(target = Client_.DOCUMENTS, ignore = true),
    @Mapping(target = Client_.PROOF_OF_IDENTITIES, ignore = true)
  })
  Client newEntity(ClientDto dto);

  @Mappings({
    @Mapping(target = Client_.CLIENT_NUMBER, ignore = true),
    @Mapping(target = Client_.SUPERVISOR, ignore = true),
    @Mapping(
        target = Client_.PARTNER,
        qualifiedBy = GenericPersonMapper.GetOrCreateGenericPerson.class),
    @Mapping(
        target = Client_.CHILDREN,
        qualifiedBy = GenericPersonMapper.GetOrCreateGenericPerson.class),
    @Mapping(target = Client_.CONTRACTS, ignore = true),
    @Mapping(target = Client_.DOCUMENTS, ignore = true),
    @Mapping(target = Client_.PROOF_OF_IDENTITIES, ignore = true),
  })
  void updateEntity(ClientDto dto, @MappingTarget Client client);

  @GetClientById
  default Client getByClientId(long clientId) {
    return Client.findById(clientId);
  }

  @Qualifier
  @Target(ElementType.METHOD)
  @Retention(RetentionPolicy.CLASS)
  @interface GetClientById {}
}
