package org.ae.server.commands;

import javax.jdo.PersistenceManager;
import javax.servlet.http.HttpServletRequest;

import org.ae.client.frame.AEServicesException;
import org.ae.client.frame.RPC;
import org.ae.client.frame.RPCResponse;
import org.ae.client.model.Animal;
import org.ae.client.rpc.DeleteAnimalRequest;
import org.ae.server.domain.AnimalBO;
import org.ae.server.frame.AECommand;
import org.ae.server.frame.PMF;
import org.ae.server.frame.ServerUtils;

public class DeleteAnimalCommand extends AECommand {

  public RPCResponse execute(RPC<?> action, HttpServletRequest request)
      throws AEServicesException {
    checkLoggedIn();
    DeleteAnimalRequest daa = (DeleteAnimalRequest) action;
    Animal animal = daa.getAnimal();
    PersistenceManager pm = PMF.get().getPersistenceManager();
    AnimalBO model = null;
    try {
      model = pm.getObjectById(AnimalBO.class, animal.getKey());
      model.setDeleted(true);
      model.prePersistEvent(animal, request);
      pm.makePersistent(model);
    } finally {
      pm.close();
    }
    ServerUtils.sendNotifications("Deleted", model);
    return new RPCResponse("Animal deleted.");
  }

}