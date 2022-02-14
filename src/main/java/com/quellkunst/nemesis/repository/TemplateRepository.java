package com.quellkunst.nemesis.repository;

import com.quellkunst.nemesis.model.Template;
import com.quellkunst.nemesis.model.Template_;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TemplateRepository extends BaseRepository<Template> {
  public Stream<Template> streamTemplatesForEmployees() {
    return stream(Template_.ADMIN_ONLY, false);
  }
}
