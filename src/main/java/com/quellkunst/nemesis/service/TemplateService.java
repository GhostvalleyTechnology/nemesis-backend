package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.model.Template;
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

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/add")
  public Response add(@MultipartForm TemplateUploadDto input) {
    guard.asAdmin(() -> addTemplate(input));
    return appResponse.ok();
  }

  private void addTemplate(TemplateUploadDto input) {
    var file = input.persist();
    Template.builder().file(file).adminOnly(input.adminOnly).build().persist();
  }

  @GET
  @Path("/list")
  public List<TemplateDto> list() {
    Stream<Template> stream;
    if (guard.isAdmin()) {
      stream = Template.streamAll();
    } else {
      stream = Template.stream("from Template where adminOnly = 'false'");
    }
    return stream.map(TemplateDto::of).collect(Collectors.toList());
  }

  @GET
  @Path("/get/{templateId}")
  public Response get(@PathParam long templateId) {
    Template template = Template.byId(templateId);
    if (template.adminOnly) {
      return guard.asAdmin(() -> appResponse.fileDownload(template.file));
    }
    return appResponse.fileDownload(template.file);
  }

  @DELETE
  @Path("/delete/{templateId}")
  public Response delete(@PathParam long templateId) {
    guard.asAdmin(() -> Template.byId(templateId).delete());
    return appResponse.ok();
  }
}
