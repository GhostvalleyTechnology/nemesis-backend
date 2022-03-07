package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.controller.TemplateController;
import com.quellkunst.nemesis.controller.mapper.TemplateMapper;
import com.quellkunst.nemesis.model.Template;
import com.quellkunst.nemesis.repository.TemplateRepository;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.TemplateDto;
import com.quellkunst.nemesis.service.dto.TemplateUploadDto;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Transactional
@Path("/template")
public class TemplateService {
  @Inject Guard guard;
  @Inject AppResponse appResponse;
  @Inject TemplateRepository repository;
  @Inject TemplateController controller;
  @Inject TemplateMapper mapper;

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/add")
  public Response add(@MultipartForm TemplateUploadDto input) {
    guard.asAdmin(() -> controller.add(input));
    return appResponse.ok();
  }

  @GET
  @Path("/list")
  public List<TemplateDto> list() {
    Stream<Template> stream;
    if (guard.isAdmin()) {
      stream = repository.streamAll();
    } else {
      stream = repository.streamTemplatesForEmployees();
    }
    return stream.map(mapper::toDto).collect(Collectors.toList());
  }

  @GET
  @Path("/get/{templateId}")
  public Response get(@PathParam long templateId) {
    Template template = repository.byId(templateId);
    if (template.adminOnly) {
      return guard.asAdmin(() -> appResponse.fileDownload(template.file));
    }
    return appResponse.fileDownload(template.file);
  }

  @POST
  @Path("/update")
  public Response update(TemplateDto template) {
    guard.asAdmin(() -> controller.update(template));
    return appResponse.ok();
  }

  @DELETE
  @Path("/delete/{templateId}")
  public Response delete(@PathParam long templateId) {
    guard.asAdmin(() -> repository.byId(templateId).delete());
    return appResponse.ok();
  }
}
