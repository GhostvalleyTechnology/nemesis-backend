package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.service.dto.ReminderDto;
import java.util.List;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.jboss.resteasy.spi.NotImplementedYetException;

@Transactional
@Path("/reminder")
public class ReminderService {
  @GET
  public List<ReminderDto> list() {
    throw new NotImplementedYetException();
  }
}
