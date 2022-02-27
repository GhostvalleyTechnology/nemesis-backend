package com.quellkunst.nemesis.service;

import com.quellkunst.nemesis.service.dto.ReminderDto;
import org.jboss.resteasy.spi.NotImplementedYetException;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Transactional
@Path("/reminder")
public class ReminderService {
    @GET
    public List<ReminderDto> list() {
        throw new NotImplementedYetException();
    }
}
