package com.quellkunst.nemesis.repository;

import com.quellkunst.nemesis.model.ClientContract;
import com.quellkunst.nemesis.model.ClientContract_;
import com.quellkunst.nemesis.model.Client_;
import com.quellkunst.nemesis.security.AppContext;
import java.util.stream.Stream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ClientContractRepository extends BaseRepository<ClientContract> {
  @Inject AppContext context;

  public Stream<ClientContract> listOfAllClients() {
    return stream(
        "from "
            + ClientContract.class.getSimpleName()
            + " where "
            + ClientContract_.CLIENT
            + "."
            + Client_.SUPERVISOR
            + "=?1",
        context.getCurrentEmployee());
  }
}
