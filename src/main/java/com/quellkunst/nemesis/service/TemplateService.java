package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Template;
import com.quellkunst.nemesis.security.Guard;
import com.quellkunst.nemesis.service.dto.TemplateDto;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/template")
public class TemplateService {
  @Inject Guard guard;

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  @Transactional
  @Path("/add")
  public Response add(@MultipartForm TemplateDto input) {
    guard.asAdmin(() -> addTemplate(input));
    return AppResponse.ok();
  }

  private void addTemplate(TemplateDto input) {
    var fileId = FileUpload.persist(input);
    Template.builder()
        .fileId(fileId)
        .fileName(input.fileName)
        .adminOnly(input.adminOnly)
        .build()
        .persist();
  }

  @GET
  @Path("/list")
  public List<Template> list() {
    return Template.listAll();
  }

  @GET
  @Path("/get/{templateId}")
  @Produces(MediaType.APPLICATION_OCTET_STREAM)
  @Transactional
  public Response get(@PathParam long templateId) {
    Template template = Template.byId(templateId);
    if (template.adminOnly) {
      return guard.asAdmin(() -> AppResponse.fileDownload(template));
    }
    return AppResponse.fileDownload(template);
  }
}
