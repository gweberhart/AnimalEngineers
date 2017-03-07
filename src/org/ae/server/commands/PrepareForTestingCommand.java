package org.ae.server.commands;

import java.util.Collection;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.server.domain.AnimalBO;
import org.ae.server.frame.AECommand;
import org.ae.server.frame.PMF;

import com.google.gwt.core.client.GWT;

public class PrepareForTestingCommand extends AECommand {

  @SuppressWarnings("unchecked")
  public RPCResponse execute(RPC<?> action, HttpServletRequest request)
      throws AEServicesException {
    boolean devMode = GWT.getPermutationStrongName().contains("HostedMode");
    if (devMode) {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      try {
        Query q = pm.newQuery(AnimalBO.class);
        Collection<AnimalBO> c = (Collection<AnimalBO>) q.execute();
        pm.deletePersistentAll(c);
      } finally {
        pm.close();
      }
    }
    return new RPCResponse("Database Reset");
  }

}